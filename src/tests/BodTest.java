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
		String bodId = "20"; //verander dit elke keer naar nieuwe waarde van bod-sequence aangezien bod id automatisch wordt aangewezen
		int id = Integer.parseInt(bodId);
		BodDAOImpl impl = new BodDAOImpl();
		Gebruiker jonathan = new Gebruiker("jopie", "Jonathan", "Karssen", "jonathan@mail.com", Calendar.getInstance(), 5000, "78473923", false, "J");
		Bod bod = new Bod(id, 50, jonathan, "21");
		impl.create(bod);
		Bod b = impl.read(bodId);
		assertEquals("Create of read van Bod faalt", bod, b);

		bod = new Bod(id, 70, jonathan, "21");
		impl.update(bodId, bod);
		b = impl.read(bodId);
		assertEquals("Update van Bod faalt", 70, b.getBedrag());
		
		impl.delete(bodId);
		b = impl.read(bodId);
		assertEquals("Delete van Bod faalt", b, null);
		ArrayList<Bod> array = impl.getAll();
		System.out.println(array.toString());
	}
}
