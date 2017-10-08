import org.junit.*;

import model.ConnectionDBMS;

import static org.junit.Assert.*;


public class ConnectionDBMSTest {
	
	private ConnectionDBMS connection1, connection2, connection3;
	
	
	@Before()
	public void setUp(){
		connection1 = new ConnectionDBMS(null, null, null, null);
		connection2 = new ConnectionDBMS("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@localhost:1521:xe", "toto", "titi");
		connection3 = new ConnectionDBMS("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@localhost:1521:xe", "system", "14041998");
	}
	 
	@Test()
	public void testConnectionDBMS(){
		assertTrue(connection1.equals(connection1));
		assertTrue(connection1.equals(new ConnectionDBMS(null, null, null, null)));
		assertFalse(connection1.equals(null));
	}
	
	
	@Test()
	public void testLoadDriver(){
		assertFalse(connection1.loadDriver());
		assertTrue(connection2.loadDriver());
	}
	
	@Test()
	public void testConnection(){
		assertFalse(connection1.connectionDBMS());
		assertFalse(connection2.connectionDBMS());
		assertTrue(connection3.connectionDBMS());
	}
	
	@Test()
	public void testIsValidConnection(){
		connection3.connectionDBMS();
		assertTrue(connection3.isValidConnection());
	}
	
	@Test()
	public void testDisconnectDBMS(){
		connection3.connectionDBMS();
		assertTrue(connection3.disconnectDBMS());
	}
	
	@Test()
	public void testIsValidDisconnection(){
		connection3.connectionDBMS();
		assertTrue(connection3.disconnectDBMS());
		assertTrue(connection3.isValidDisconnection());
	}
	
}