package plarktmaatsDomein;

public class Categorie {
	private String 	naam;
	
	public Categorie(String nm, PlarktMaats pl) {
		naam = nm;
		pl.voegCategorieToe(this);
	}
}
