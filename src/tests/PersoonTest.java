package tests;

import java.util.Calendar;

import plarktmaatsDAO.PersoonDAOImpl;
import plarktmaatsDomein.Gebruiker;
import plarktmaatsDomein.Persoon;

public class PersoonTest {
	public static void main(String[] args) {
		PersoonDAOImpl impl = new PersoonDAOImpl();
		Calendar gebdat = Calendar.getInstance();
		Gebruiker jonathan = new Gebruiker("jonathan", "Jonathan", "Karssen", "jonathan@mail.com", Calendar.getInstance(), 5000, "78473923", false, "J");
		impl.create(jonathan);
		Persoon p = impl.read("jonathan");
		if (p == null) {
			//create of read faalt
		}
		jonathan = new Gebruiker("jonathan", "Jonathan", "Karssen", "jonathan@anderemail.com", Calendar.getInstance(), 5000, "78473923", false, "J");
		impl.update("jonathan", jonathan);
		p = impl.read("jonathan");
		if (!p.getEmail().equals("jonathan@anderemail.com")) {
			//update faalt
		}
		impl.delete("jonathan");
		p = impl.read("jonathan");
		if (p != null) {
			//delete faalt
		}
	}
}
