package tests;

import java.util.ArrayList;
import java.util.Calendar;

import plarktmaatsDAO.VeilingDAOImpl;
import plarktmaatsDomein.Categorie;
import plarktmaatsDomein.Gebruiker;
import plarktmaatsDomein.Veiling;

public class VeilingTest {
	
	public static void main(String[] args) {
		 VeilingDAOImpl impl = new VeilingDAOImpl();
		 Calendar gebdat = Calendar.getInstance();
		 Gebruiker freak = new Gebruiker("Freak","Freek", "Nederland",
		 "superloser@superfreak.com", gebdat, "8482929", "super");
		 Categorie cat = new Categorie("Personen");
		 Veiling veil = new Veiling(0, "freak", "superfreak original", null, 5, gebdat, freak, cat, false);
		  impl.create(veil);
		  veil = impl.read("freak");
		  System.out.println(veil);
		  veil = new Veiling(0, "freak", "superfreak non-original", null, 5, gebdat, freak, cat, true);
		  impl.update("0", veil);
		  impl.delete("0");
		 ArrayList<Veiling> array = impl.getAll();
		 System.out.println(array.toString());
	}
}
