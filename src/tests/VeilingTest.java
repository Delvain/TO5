package tests;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

import plarktmaatsDAO.CategorieDAOImpl;
import plarktmaatsDAO.PersoonDAOImpl;
import plarktmaatsDAO.VeilingDAOImpl;
import plarktmaatsDomein.Categorie;
import plarktmaatsDomein.Gebruiker;
import plarktmaatsDomein.Veiling;

public class VeilingTest {
	
	@Test
	public void testVeilingDAO() {
		VeilingDAOImpl veilingImpl = new VeilingDAOImpl();
		PersoonDAOImpl persoonImpl = new PersoonDAOImpl();
		CategorieDAOImpl categorieImpl = new CategorieDAOImpl();
		Categorie c = (Categorie)categorieImpl.read("Gereedschap");
		Gebruiker p = (Gebruiker)persoonImpl.read("freek");
		Calendar eindtijd = Calendar.getInstance();
		Veiling v1 = new Veiling(0, "Zippo", "Steekt dingen aan", "http://upload.wikimedia.org/wikipedia/commons/7/75/Zippo_burning_with_black_background.jpg", 20, eindtijd, p, c, false);
		veilingImpl.create(v1);
		Veiling v2 = veilingImpl.read("0");
		assertEquals("Create van v1 mislukt", v1, v2);
		Veiling v3 = new Veiling(0, "Zippo", "Steekt dingen aan", "http://upload.wikimedia.org/wikipedia/commons/7/75/Zippo_burning_with_black_background.jpg", 30, eindtijd, p, c, false);
		veilingImpl.update("0", v3);
		assertEquals("Update van v1 mislukt", v1, v3);
		veilingImpl.delete("0");
		v1 = veilingImpl.read("0");
		assertEquals("Delete van v1 mislukt", v1, null);
	}
}
