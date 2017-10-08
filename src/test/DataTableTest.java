import org.junit.*;

import model.DataTable;
import model.ConnectionDBMS;

import static org.junit.Assert.*;

public class DataTableTest {
	
	private DataTable datT1, datT2, datT3, datT4;
	private ConnectionDBMS con1, con2;

	@Before()
	public void setUp(){
		con1 = new ConnectionDBMS("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@localhost:1521:xe", "system", "14041998");
		con2 = new ConnectionDBMS(null, null, null, null);

		datT1 = new DataTable(null, null);
		datT2 = new DataTable(null, con2.getConnection());
		datT3 = new DataTable(null, con1.getConnection());
		datT4 = new DataTable("AGENTS", con1.getConnection());
	}

	@Test()
	public void testDataTable(){
		assertTrue(datT1.equals(datT1));
		assertTrue(datT4.equals(datT4));
		assertTrue(datT1.equals(new DataTable(null, null)));
		assertFalse(datT2.equals(null));
	}

	@Test()
	public void testGetName(){
		assertEquals(null, datT1.getName());
		assertEquals("AGENTS", datT4.getName());
	}

	@Test()
	public void testGetConnection(){
		assertEquals(null, datT1.getConnection());
		assertFalse(datT4.equals(datT4.getConnection()));
	}

}