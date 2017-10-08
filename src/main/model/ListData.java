package model;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * This class corresponds to put all the table of the DataBase into an ArrayList
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.0
 * @since 20 juin 2017
 */
public class ListData extends TableModel{
	
	/**
	 * This assignment corresponds to the ArrayList of table
	 */
	private ArrayList<String> tableList;

	/**
	  * The data 
	  */
	private Data dataType;

	/**
	 * Initialize a ListTable object
	 * @param connection The  connection of the DataBase
	 * @param data the type of data use (TABLE, VIEW or TRIGGER)
	 */
	public ListData(Connection connection, Data data) {
		
		super(connection);
		this.dataType = data;
		if(this.dataType == data.TABLE){
			this.query = "SELECT table_name FROM user_tables";
		}
		else if(data == data.VIEW){
			this.query = "SELECT view_name FROM user_views";
		}
		else{
			this.query = "SELECT trigger_name FROM user_triggers";
		}
		
		executeQuery();
		tableList = new ArrayList<String>();
		setTableList();
	}
	
	/**
	 * This method is not used in this class
	 */
	protected void setQueryRequest(String request){};
	
	/**
	 * This methods set the table list
	 * @return true if the good set table list
	 */
	public boolean setTableList(){
		boolean ret = true;
		try{
			for(Object table : this.data){
			if(!table.toString().contains("$") && !table.toString().contains("MVIEW") && !table.toString().contains("LOGMNR"))
				tableList.add(table.toString().replaceAll("\\[|\\]" , ""));
			}
		}
		catch(Exception e){
			ret = false;
		}

		return ret;
		
	}
	 
	/**
	 * Getter of the arraylist of table
	 * @return The ArrayList of table
	 */
	public ArrayList<String> getListTable(){
		return this.tableList;
	}

	/**
	  * Getter data
	  * @return the data
	  */
	public Data getData(){
		return this.dataType;
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
	    
	   	ListData other = (ListData) obj;
	    if (this.connection == null) {
	        if (other.getConnection() != null)
	          return false;
	      } else if (!this.connection.equals(other.getConnection()))
	        return false;
		if (this.dataType == null) {
	        if (other.getData() != null)
	          return false;
	      } else if (!this.dataType.equals(other.getData()))
	        return false;
	  
	     return true;
	}
	
}