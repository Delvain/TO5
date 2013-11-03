package tests;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

import plarktmaatsDAO.PersoonDAOImpl;
import plarktmaatsDomein.Gebruiker;

public class PersoonTest {
	
	@Test
	public void testPersoonDAO() {
		PersoonDAOImpl impl = new PersoonDAOImpl();
		Gebruiker jonathan = new Gebruiker("jonathan", "Jonathan", "Karssen", "jonathan@mail.com", Calendar.getInstance(), 5000, "78473923", false, "J");
		impl.create(jonathan);
		Gebruiker p = (Gebruiker)impl.read("jonathan");
		assertEquals("Create of read van Persoon faalt", jonathan, p);
		jonathan = new Gebruiker("jonathan", "Jonathan", "Karssen", "jonathan@anderemail.com", Calendar.getInstance(), 5000, "78473923", false, "J");
		impl.update("jonathan", jonathan);
		p = (Gebruiker)impl.read("jonathan");
		assertEquals("Update van Persoon faalt", jonathan, p);
		impl.delete("jonathan");
		p = (Gebruiker)impl.read("jonathan");
		assertEquals("Delete van Persoon faalt", p, null);
	}
}