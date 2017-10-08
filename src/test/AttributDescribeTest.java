import org.junit.*;

import model.AttributDescribe;

import static org.junit.Assert.*;

public class AttributDescribeTest {
	
	private AttributDescribe att1, att2;

	@Before()
	public void setUp(){
		att1 = new AttributDescribe(null, null, null, null, null, null);
		att2 = new AttributDescribe(false, "NOM", "NUMBER", "4", false, false);
	}

	@Test()
	public void testAttributDescribe(){
		assertTrue(att1.equals(att1));
		assertTrue(att1.equals(new AttributDescribe(null, null, null, null, null, null)));
		assertFalse(att1.equals(null));
	}

	@Test()
	public void testGetPk(){
		assertEquals("La clé primaire est incorrecte", null, att1.getPk());
		assertEquals("La clé primaire est incorrecte", false, att2.getPk());		
	}

	@Test()
	public void testGetNul(){
		assertEquals("La contrainte de nulité est incorrecte", null, att1.getNul());
		assertEquals("La contrainte de nulité  est incorrecte", false, att2.getNul());		
	}

	@Test()
	public void testGetUk(){
		assertEquals("La contrainte d'unicité est incorrecte", null, att1.getUk());
		assertEquals("La contrainte d'unicité est incorrecte", false, att2.getUk());		
	}

	@Test()
	public void testGetName(){
		assertEquals("Le nom est incorrect", null, att1.getName());
		assertEquals("Le nom est incorrect", "NOM", att2.getName());		
	}

	@Test()
	public void testGetType(){
		assertEquals("Le type est incorrect", null, att1.getType());
		assertEquals("Le type est incorrect", "NUMBER", att2.getType());		
	}

	@Test()
	public void testGetTaille(){
		assertEquals("La taille est incorrecte", null, att1.getTaille());
		assertEquals("La taille est incorrecte", "4", att2.getTaille());		
	}
	
}