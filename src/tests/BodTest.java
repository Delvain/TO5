package tests;

import java.util.ArrayList;
import java.util.Calendar;

import plarktmaatsDAO.BodDAOImpl;
import plarktmaatsDomein.Bod;
import plarktmaatsDomein.Gebruiker;

public class BodTest {
	
	public static void main(String[] args) {
		BodDAOImpl impl = new BodDAOImpl();
		Gebruiker jonathan = new Gebruiker("jonathan", "Jonathan", "Karssen", "jonathan@mail.com", Calendar.getInstance(), 5000, "78473923", false, "J");
		Bod bod = new Bod(2, 50, jonathan, "21");
		impl.create(bod);
		bod = impl.read("2");
		if (bod == null) {
			//create of read faalt
		}
		Bod b = new Bod(2, 70, jonathan, "21");
		impl.update("2", b);
		bod = impl.read("2");
		if (bod.getBedrag() != 70) {
			//update faalt
		}
		impl.delete("2");
		bod = impl.read("2");
		if (bod != null) {
			//delete faalt
		}
		ArrayList<Bod> array = impl.getAll();
		System.out.println(array.toString());
	}
}
