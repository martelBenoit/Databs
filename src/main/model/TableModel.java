package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

/**
 * This abstract class corresponds to the model of the table
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.0
 */
public abstract class TableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;

	/**
	 * This assignment corresponds to the connection to the DMBS
	 */
	protected Connection connection;
	
	/**
	 * This assignment corresponds to the query
	 */
	protected String query;
	
	/**
	 * This assignment corresponds to the exception which can be throws
	 */
	protected String exception;
	
	/**
	 * This assignment corresponds to the column represent by a Vector
	 */
	private Vector<Object> column;
	
	/**
	 * This assignment corresponds to the data represent by a Vector
	 */
	protected Vector<Object> data;
	
	/**
	 * This assignment corresponds to the row represent by a Vector
	 */
	private Vector<Object> row;

	/**
	 * The statement
	 */
	protected Statement st;

	/**
	  *
	  */
	protected ResultSet rs;

	/**
	 * Initialize a TableModel object
	 * @param connection The connection to the DBMS
	 */
	public TableModel(Connection connection){
		
		this.connection = connection;
	}
	
	/**
	 * Method abstract
	 * @param request The query
	 */
	protected abstract void setQueryRequest(String request);
	
	/**
	 * This methods allow to execute a query to the data base
	 * @return boolean If the query is execute true or false if she isn't
	 */
	protected boolean executeQuery(){
			
			boolean ret = false;
			this.st = null;
			this.rs = null;
			ResultSetMetaData rsmt = null;
			
			try {
				this.st = connection.createStatement();
				this.rs = st.executeQuery(this.query);
				rsmt = rs.getMetaData();
				
				int size = rsmt.getColumnCount();
				this.column = new Vector<Object>(size);
				
				for(int i = 1; i <= size; i++){
					this.column.add(rsmt.getColumnName(i));
				}
				
				this.data = new Vector<Object>();
			    this.row = new Vector<Object>();
			    
			    while(rs.next()){
			    	this.row = new Vector<Object>(size);
			        
			    	for(int i = 1; i <= size; i++){
			    		this.row.add(rs.getString(i));
			        }
			        this.data.add(row);
			    }
			    this.setDataVector(this.data, this.column);
			    ret = true;
			}
			catch(Exception e){
				//e.printStackTrace();
			}
			finally{
				try{
					st.close();
					rs.close();
				}
				catch(Exception e){
				//	e.printStackTrace();
				}
			}		
			return ret;
		}
	
	/**
	 * This method indicates if the update is excecute or not
	 * @return ret The boolean (True = success, False = error)
	 */
	protected boolean executeUpdate(){
		
		boolean ret = false;
		Statement st = null;
		try {
			st = this.connection.createStatement();
			st.executeUpdate(this.query);
			ret = true;

		} 
		catch (SQLException se) {
			this.exception = se.getMessage();

		}
		catch (Exception e){
			//e.printStackTrace();
		}
		finally{
			try{
				st.close();
			}
			catch(Exception e){
				//e.printStackTrace();
			}
		}
		
		return ret;
		
	}

	/**
	  * The connection
	  * @return the connection
	  */
	public Connection getConnection(){
		return this.connection;
	}
	
	/**
	 * Getter
	 * @return The exception SQL 
	 */
	public String getException(){
		return this.exception;
	}

	/**
	 * Getter
	 * @return The resultat set
	 */
	public ResultSet getResult(){
		return this.rs;
	}
	
	/**
	 * Getter
	 * @return the statement
	 */
	public Statement getStatement(){
		return this.st;
	}

	/**
	  * Query
	  * @return the query
	  */
	public String getQuery(){
		return this.query;
	}
}
