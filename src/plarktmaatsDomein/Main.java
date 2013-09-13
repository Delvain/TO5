package plarktmaatsDomein;

import java.util.Calendar;


public class Main {
	public static void main(String[] args) {
		
		PlarktMaats plarktMaats = new PlarktMaats();
		
		Gebruiker g1 = new Gebruiker("Henk", "de Vries", "henk.de.vries@test.nl", "NLABN0978687", plarktMaats);
		
		Categorie cat1 = new Categorie("Zooi", plarktMaats);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.SECOND, 5);
		Veiling v1 = new Veiling("Fiets", "lelijk", "foto", 100, c, g1, cat1, plarktMaats);
		c.add(Calendar.SECOND, 2);
		Veiling v2 = new Veiling("Auto", "beetje mooi maar te duur", "foto", 0, c, g1, cat1, plarktMaats);
		
		Bod b1 = new Bod(101, g1);
		Bod b2 = new Bod(90, g1);
		Bod b3 = new Bod(120, g1);
		v1.doeBod(b1); v1.doeBod(b2);
		v1.doeBod(b3);
		
		Bod b4 = new Bod(9, g1);
		v2.doeBod(b4);
		
	}
}
