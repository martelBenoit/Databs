package model;

import java.sql.Connection;

/**
 * This class regards to the updtae of the Data Base Oracle
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.0
 */
public class UpdateBase extends TableModel{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * This assignment corresponds to the boolean which indicates if the connection success 
	 */
	private boolean success = false;
	
	/**
	 * Initialize an UpdateBase object
	 * @param connection the connection
	 * @param query the query
	 */
	public UpdateBase(Connection connection, String query) {
		super(connection);
		this.setQueryRequest(query);
		success = this.executeUpdate();
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see model.TableModel#setQueryRequest(java.lang.String)
	 */
	@Override
	protected void setQueryRequest(String request) {
		this.query = request;
		
	}
	
	/**
	 * This method indicate if the connection success or not
	 * @return The boolean (True = success, False = error)
	 */
	public boolean isSucceed(){
		return this.success;
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
	    
	   	UpdateBase other = (UpdateBase) obj;
	    if (this.connection == null) {
	        if (other.getConnection() != null)
	          return false;
	      } else if (!this.connection.equals(other.getConnection()))
	        return false;
		if (this.query == null) {
	        if (other.getQuery() != null)
	          return false;
	      } else if (!this.query.equals(other.getQuery()))
	        return false;
	  
	     return true;
	}

}
