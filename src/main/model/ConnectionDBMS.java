package model;

import java.sql.*;

/**
 * This class allows to create a new connection to a database, whatever its type.
 * <br><b>It allows for the moment to connect only to an oracle database. </b>
 * @author Alexandre BOUDET and Benoît MARTEL
 * @version 1.1
 */
public class ConnectionDBMS{
	
	/**
	 * The url for the connection DBMS.
	 */
	private String url;
	
	/**
	 * The name of the user.
	 */
	private String user;
	
	/**
	 * The password of the user.
	 */
	private String password;
	
	/**
	 * The driver for the connection.
	 */
	private String driver;
	
	/**
	 * The connection.
	 */
	private Connection connection;
	
	/**
	 * Constructor of the class ConnectionDBMS
	 * @param driver The driver used for the connection.
	 * @param url The url used for the connection.
	 * @param user The username for the connection.
	 * @param password The password of the user for the connection.
	 */
	public ConnectionDBMS(String driver, String url, String user, String password){
		
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	/**
	 * This method is used to check if a connection is valid.
	 * @return true, if the connection is valid.
	 */
	public boolean isValidConnection(){
		
		if(this.connection==null){ 
		      return false; 
		   } 
		   ResultSet ping = null; 
		   try{ 
		      if(this.connection.isClosed()){return false;} 
		      ping = this.connection.createStatement().executeQuery("SELECT * FROM Clients"); 
		      return ping.next(); 
		   }catch(SQLException sqle){ 
			//  sqle.printStackTrace();
		      return false; 
		   }  
		   finally{ 
		      if(ping!=null){try{ping.close();}catch(Exception e){}} 
		   }   
		}
	
	/**
	 * This method is used to check if disconnection is valid.
	 * @return true, if the disconnection is valid.
	 */
	public boolean isValidDisconnection(){
		
		boolean ret = false;
		
		try {
			if(this.connection.isClosed())
			{
				ret = true;
			}
		} catch (SQLException e) {

			//e.printStackTrace();
		}
		
		return ret;
	}
	
	/**
	 * This class is used to load the driver used to create a connection.
	 * @return true, if the driver has been successfully loaded.
	 */
	public boolean loadDriver(){
		
		boolean ret = false;
		if(this.driver != null){
			try{
				Class.forName(this.driver);
				ret = true;
		
			} 
			catch(ClassNotFoundException e){
			//	e.printStackTrace();
	
			}
			catch(NullPointerException e){
			//	e.printStackTrace();
			}
			catch(Exception e){
			//	e.printStackTrace();
			}
		}
		
		return ret;
	}
	
	/**
	 * This method allows you to log out
	 * @return true, if the connection is interrupted.
	 */
	public boolean disconnectDBMS(){
		
		boolean ret = false;
		
		try {
			this.connection.close();
			ret = true;
		} 
		catch (SQLException e) {
		//	e.printStackTrace();
		}
		
		return ret;
	}
	
	/**
	 * This method allows to connect to the database with the parameters provided in the constructor of the method.
	 * @return true, if the connection has been established.
	 */
	public boolean connectionDBMS(){
		
		boolean ret = false;
		loadDriver();
		
		try{
			if(this.driver != null){
				this.connection = DriverManager.getConnection(this.getUrl(), this.getUser(), this.getPassword());
				ret = true;
			}
			
		}
		catch(SQLException sqle){
			//sqle.printStackTrace();
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		
		return ret;
	}
	
	/**
	 * This method is used to retrieve this connection.
	 * @return this connection.
	 */
	public Connection getConnection(){
		return this.connection;
	}

	/**
	 * This method is used to retrieve the driver used.
	 * @return the driver used.
	 */
	public String getDriver() {
		return this.driver;
	}

	/**
	 * This method is used to retrieve the url used.
	 * @return the url used.
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * This method is used to retrieve the name of the user.
	 * @return the name of the user.
	 */
	public String getUser() {
		return this.user;
	}

	/**
	 * This method is used to retrieve the password of the user.
	 * @return the password of the user.
	 */
	public String getPassword() {
		return this.password;
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
	    
	    ConnectionDBMS other = (ConnectionDBMS) obj;
	    if (this.driver == null) {
	        if (other.getDriver() != null)
	          return false;
	      } else if (!this.driver.equals(other.getDriver()))
	        return false;
	    if (this.url == null) {
	        if (other.getUrl() != null)
	          return false;
	      } else if (!this.url.equals(other.getUrl()))
	        return false;
	    if (this.user == null) {
	        if (other.getUser() != null)
	          return false;
	      } else if (!this.user.equals(other.getUser()))
	        return false;
	    if (this.password == null) {
	        if (other.getPassword() != null)
	          return false;
	      } else if (!this.password.equals(other.getPassword()))
	        return false;
	     return true;
	}

}