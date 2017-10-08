import org.junit.*;

import model.*;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class TableDescribeTest {
	
	private TableDescribe tb1, tb2, tb3, tb4;
	private AttributDescribe att1, att2, att3;


	@Before()
	public void setUp(){

		tb1 = new TableDescribe("TEST1");

		att1 = new AttributDescribe(false, "NOM", "NUMBER", "4", false, false);
		att2 = new AttributDescribe(true, "PRENOM", "NUMBER", "4", false, false);
		att3 = new AttributDescribe(false, "AGE", "VARCHAR2", "15", true, false);

		ArrayList<AttributDescribe> attributs = new ArrayList<AttributDescribe>();
		attributs.add(att1);
		attributs.add(att2);
		attributs.add(att3);

		tb2 = new TableDescribe("TEST2", attributs);
		tb3 = new TableDescribe("TEST3", null);
		tb4 = new TableDescribe(null, null);
	}

	@Test()
	public void testTableDescribe(){
		assertTrue(tb1.equals(tb1));
		assertTrue(tb2.equals(tb2));
		assertTrue(tb3.equals(tb3));
		assertTrue(tb4.equals(new TableDescribe(null, null)));
		assertFalse(tb1.equals(null));

		assertTrue(tb1.getNameTable().equals("TEST1"));
		assertTrue(tb2.getNameTable().equals("TEST2"));
		assertEquals(tb4.getNameTable(),null);	
	}

	@Test()
	public void testAddAttributDescribe(){
		tb1.addAtributDescribe(att1);
		assertEquals(tb1.getAttributs().size(), 1);
		assertEquals(tb1.getAttributs().get(0), att1);
	}

	@Test()
	public void testSetNameTable(){
		tb1.setNameTable("IUT");
		assertEquals(tb1.getNameTable(), "IUT");
	}
}