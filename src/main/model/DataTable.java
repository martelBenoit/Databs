package model;

import java.sql.Connection;

/**
 * By specifying in the constructor a connection to a database as well as the name of a table, this class allows to create the model of this table.
 * @author Benoît and Alexandre
 * @version 1.3
 */
public class DataTable extends TableModel{
	

	/**
	 * This assignment correspond to the name of the table
	 */
	private String name;

	/**
	 * Constructor of the class ModelTable.
	 * <br>By integrating an instance of this class into a JTable, it is possible to view the entire table.
	 * @param name The name of the table to be modeled.
	 * @param connection The connection used to create the model of a table.
	 */
	public DataTable(String name, Connection connection){

		super(connection);
		
		this.name = name;
		this.setQueryRequest(this.name);
		this.executeQuery();
	}
 
	/**
	 * Retrieves all the data from a table
	 */
	protected void setQueryRequest(String request) {
		this.query = ("SELECT COLUMN_NAME, DATA_TYPE, DATA_LENGTH, NULLABLE from USER_TAB_COLUMNS where TABLE_NAME='"+request+"' order by column_id");
		
	}
	
	/**
	 * Getter
	 * @return The name of the of the table
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Getter
	 * @return The connection
	 */
	public Connection getConnection(){
		return this.connection;
	}

	/**
	 * This method overrides the equals method of Object.
	 * <br>It verifies whether the parameters of the two objects specified in the constructor are the same.
	 * @param obj the object to compare with this.
	 * @return true, if the objects are equal between their parameters. 
	 */
	public boolean equals(Object obj){
		
		if (this == obj)
			return true;
	    if (obj == null)
	    	return false;
	    if (getClass() != obj.getClass())
	    	return false;
	    
	    DataTable other = (DataTable) obj;
	    if (this.connection == null) {
	        if (other.getConnection() != null)
	          return false;
	      } else if (!this.connection.equals(other.getConnection()))
	        return false;
		if (this.name == null) {
	        if (other.getName() != null)
	          return false;
	      } else if (!this.name.equals(other.getName()))
	        return false;
	  
	     return true;
	}
}