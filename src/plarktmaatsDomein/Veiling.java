package plarktmaatsDomein;

import java.util.ArrayList;
import java.util.Calendar;

public class Veiling {
	
	private int			veilingId;
	private String 		veilingNaam;
	private String 		veilingOmschrijving;
	private String 		foto;
	private int 		minBedrag;
	private Calendar 	eindTijd;
	
	private Gebruiker 	aanbieder;
	private Categorie	deCategorie;
	private ArrayList<Bod> alleBiedingen;
	
	private boolean geblokkeerd;
	
	public Veiling() {
		alleBiedingen = new ArrayList<Bod>();
	}
	
	public Veiling(int id, String nm, String omsch, String ft, int min, Calendar eind, Gebruiker aanb, Categorie cat, boolean geblokd) {
		veilingId			= id;
		veilingNaam 		= nm;
		veilingOmschrijving = omsch;
		foto = ft;
		minBedrag = min;
		eindTijd = eind;
		
		aanbieder = aanb;
		deCategorie = cat;
		geblokkeerd = geblokd;
		alleBiedingen = new ArrayList<Bod>();
	}
	
	public Bod getHoogsteBod() {
		Bod hoogsteBod = null;
		if(alleBiedingen == null){
			return null;
		}
		for(Bod b : alleBiedingen) {
			if(hoogsteBod == null) {
				hoogsteBod = b;
			}
			else if(b.getBedrag() > hoogsteBod.getBedrag()) {
				hoogsteBod = b;
			}
		}
		return hoogsteBod;
	}
	
	//alleen voor ophalen bij database
	public void voegBodToe(Bod b) {
		alleBiedingen.add(b);
	}
	
	public boolean doeBod(Bod b) {
		if(getHoogsteBod() != null) {
			if(b.getBedrag() <= getHoogsteBod().getBedrag()) { //nieuwe bod mag niet lager of gelijk zijn aan het huidige bod
				return false;
			}
		}
		if(b.getBedrag() <= minBedrag) {  //checken of bod hoger is dan het minimum bedrag
			return false;
		}
		alleBiedingen.add(b); //alles goed: huidige bod is nieuwe bod
		return true;
	}
	
	public String getVeilingNaam() {
		return veilingNaam;
	}

	public void setVeilingNaam(String veilingNaam) {
		this.veilingNaam = veilingNaam;
	}

	public String getVeilingOmschrijving() {
		return veilingOmschrijving;
	}

	public void setVeilingOmschrijving(String veilingOmschrijving) {
		this.veilingOmschrijving = veilingOmschrijving;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public int getMinBedrag() {
		return minBedrag;
	}

	public void setMinBedrag(int minBedrag) {
		this.minBedrag = minBedrag;
	}

	public Calendar getEindTijd() {
		return eindTijd;
	}

	public void setEindTijd(Calendar eindTijd) {
		this.eindTijd = eindTijd;
	}

	public Gebruiker getAanbieder() {
		return aanbieder;
	}

	public void setAanbieder(Gebruiker aanbieder) {
		this.aanbieder = aanbieder;
	}

	public Categorie getDeCategorie() {
		return deCategorie;
	}

	public void setDeCategorie(Categorie deCategorie) {
		this.deCategorie = deCategorie;
	}

	public int getVeilingId() {
		return veilingId;
	}

	public void setVeilingId(int veilingId) {
		this.veilingId = veilingId;
	}
	
	public ArrayList<Bod> getAlleBiedingen() {
		return alleBiedingen;
	}

	public boolean getGeblokkeerd() {
		return geblokkeerd;
	}

	public void setGeblokkeerd(boolean geblokkeerd) {
		this.geblokkeerd = geblokkeerd;
	}
}
