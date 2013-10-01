package plarktmaatsDomein;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Veiling {
	
	private String 		veilingNaam;
	private String 		veilingOmschrijving;
	private Object 		foto;
	private int 		minBedrag;
	private Calendar 	eindTijd;
	
	private Gebruiker 	aanbieder;
	private Categorie	deCategorie;
	private ArrayList<Bod> alleBiedingen;
	
	
	public Veiling(String nm, String omsch, Object ft, int min, Calendar eind, Gebruiker aanb, Categorie cat) {
		veilingNaam 		= nm;
		veilingOmschrijving = omsch;
		foto = ft;
		minBedrag = min;
		eindTijd = eind;
		
		aanbieder = aanb;
		deCategorie = cat;
		alleBiedingen = new ArrayList<Bod>();
		
		new Timer().schedule(new veilingTimer(), eindTijd.getTime()); //timer starten
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
	
	private class veilingTimer extends TimerTask {
		public void run() {
			//TODO
			System.out.println("De veiling van "+veilingNaam+" is afgelopen. ");
			if(getHoogsteBod() == null)	
				System.out.println("Niet verkocht");
			else
				System.out.println("Verkocht aan "+getHoogsteBod().getBieder().getVoornaam()+" voor "+getHoogsteBod().getBedrag());
		}
	}

	public String getProductNaam() {
		return veilingNaam;
	}

	public void setProductNaam(String productNaam) {
		this.veilingNaam = productNaam;
	}

	public String getProductOmschrijving() {
		return veilingOmschrijving;
	}

	public void setProductOmschrijving(String productOmschrijving) {
		this.veilingOmschrijving = productOmschrijving;
	}

	public Object getFoto() {
		return foto;
	}

	public void setFoto(Object foto) {
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
}
