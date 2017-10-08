package model;

import java.sql.Connection;

/**
 * This class corresponds to execution of the request on the DataBase
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.0
 */
public class SearchQuery extends TableModel{

	/**
	 * Initialize a SearchQuery object
	 * @param connection  The connection to the DataBase
	 * @param query The query to send to the DataBase
	 */
	public SearchQuery(Connection connection, String query){
		
		super(connection);
		this.setQueryRequest(query);
		executeQuery();
	}
	
	/**
	 * Setter
	 * @param request The request
	 */
	protected void setQueryRequest(String request) {
		this.query = request;		
	}	 
}