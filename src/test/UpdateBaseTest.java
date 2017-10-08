import org.junit.*;

import model.*;

import static org.junit.Assert.*;

public class UpdateBaseTest {

	private final String QUERY_OK = "UPDATE TEST_CREATION SET TEST ='a' WHERE ID = 1";

	private ConnectionDBMS con1, con2;
	private UpdateBase up1, up2, up3;

	@Before()
	public void setUp(){
		con1 = new ConnectionDBMS("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@localhost:1521:xe", "system", "14041998");
		con2 = new ConnectionDBMS(null, null, null, null);

		up1 = new UpdateBase(con1.getConnection(), QUERY_OK);
		up2 = new UpdateBase(con1.getConnection(), "");
		up3 = new UpdateBase(null, null);
	}

	@Test()
	public void testUpdateBase(){
		assertTrue(up1.equals(up1));
		assertTrue(up2.equals(up2));
		assertTrue(up3.equals(up3));
		assertTrue(up3.equals(new UpdateBase(null, null)));
		assertFalse(up1.equals(null));
	}

}
