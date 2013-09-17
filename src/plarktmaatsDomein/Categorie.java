package plarktmaatsDomein;

public class Categorie {
	private String 	naam;
	
	public Categorie(String nm, PlarktMaats pl) {
		setNaam(nm);
		pl.voegCategorieToe(this);
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
}
