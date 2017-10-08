import org.junit.*;

import model.*;

import static org.junit.Assert.*;

public class ListDataTest {

	private final Data TABLE = Data.TABLE;
	private final Data VIEW = Data.VIEW;
	private final Data TRIGGER = Data.TRIGGER;

	private ConnectionDBMS con1, con2;
	private ListData lDat1, lDat2, lDat3, lDat4, lDat5, lDat6;

	@Before()
	public void setUp(){
		con1 = new ConnectionDBMS("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@localhost:1521:xe", "system", "14041998");
		con2 = new ConnectionDBMS(null, null, null, null);

		lDat1 = new ListData(con1.getConnection(), TABLE);
		lDat2 = new ListData(con1.getConnection(), VIEW);
		lDat3 = new ListData(con1.getConnection(), TRIGGER);

		lDat4 = new ListData(null, null);
		lDat5 = new ListData(con2.getConnection(), null);
		lDat6 = new ListData(con1.getConnection(), null);
	}

	@Test()
	public void testListData(){
		assertTrue(lDat1.equals(lDat1));
		assertTrue(lDat4.equals(lDat4));
		assertTrue(lDat4.equals(new ListData(null, null)));
		assertFalse(lDat1.equals(null));
		assertTrue(lDat1.getQuery().equals("SELECT table_name FROM user_tables"));
		assertTrue(lDat2.getQuery().equals("SELECT view_name FROM user_views"));
		assertTrue(lDat3.getQuery().equals("SELECT trigger_name FROM user_triggers"));

	}
}