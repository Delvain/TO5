package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

import plarktmaatsDAO.BodDAOImpl;
import plarktmaatsDomein.Bod;
import plarktmaatsDomein.Gebruiker;

public class BodTest {
	
	@Test
	public void testBodDAO() {
		BodDAOImpl impl = new BodDAOImpl();
		Gebruiker jonathan = new Gebruiker("yolothan", "Jonathan", "Karssen", "jonathan@mail.com", Calendar.getInstance(), 5000, "78473923", false, "J");
		Bod bod = new Bod(2, 50, jonathan, "21");
		impl.create(bod);
		Bod b = impl.read("2");
		assertEquals("Create of read van Bod faalt", bod, b);

		bod = new Bod(2, 70, jonathan, "21");
		impl.update("2", b);
		b = impl.read("2");
		assertEquals("Update van Bod faalt", 70, b.getBedrag());
		
		impl.delete("2");
		b = impl.read("2");
		assertEquals("Delete van Bod faalt", b, null);
		ArrayList<Bod> array = impl.getAll();
		System.out.println(array.toString());
	}
}
