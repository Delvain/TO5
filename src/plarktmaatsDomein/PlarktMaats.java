package plarktmaatsDomein;

import java.util.ArrayList;

public class PlarktMaats {

	private ArrayList<Persoon> allePersonen;
	private ArrayList<Veiling> alleVeilingen;
	private ArrayList<Categorie> alleCategorieen;

	public PlarktMaats() {
		allePersonen	= new ArrayList<Persoon>();
		alleVeilingen	= new ArrayList<Veiling>();
		alleCategorieen = new ArrayList<Categorie>();
	}

	// allePersonen:
	public ArrayList<Persoon> getAllePersonen() {
		return allePersonen;
	}

	public boolean heeftPersoon(Persoon p) {
		for (Persoon per : allePersonen) {
			if (p.getId() == per.getId())
				return true;
		}
		return false;
	}

	public boolean voegPersoonToe(Persoon p) {
		if (!heeftPersoon(p))
			allePersonen.add(p);
		else
			return false;
		return true;
	}

	// alleVeilingen:
	public ArrayList<Veiling> getAlleVeilingen() {
		return alleVeilingen;
	}

	public void voegVeilingToe(Veiling v) {
		alleVeilingen.add(v);
	}
	
	// alleCategorieen:
	public ArrayList<Categorie> getAlleCategorieen() {
		return alleCategorieen;
	}

	public void voegCategorieToe(Categorie v) {
		alleCategorieen.add(v);
	}
}
