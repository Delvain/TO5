package plarktmaatsDomein;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Veiling {
	
	private String 		productNaam;
	private String 		productOmschrijving;
	private Object 		foto;
	private int 		minBedrag;
	private Calendar 	eindTijd;
	
	private Gebruiker 	aanbieder;
	private Categorie	deCategorie;
	private PlarktMaats plarktMaats;
	private ArrayList<Bod> alleBiedingen;
	
	
	public Veiling(String nm, String omsch, Object ft, int min, Calendar eind, Gebruiker aanb, Categorie cat, PlarktMaats pl) {
		productNaam 		= nm;
		productOmschrijving = omsch;
		foto = ft;
		minBedrag = min;
		eindTijd = eind;
		plarktMaats = pl;
		
		aanbieder = aanb;
		deCategorie = cat;
		alleBiedingen = new ArrayList<Bod>();
		plarktMaats.voegVeilingToe(this);
		
		new Timer().schedule(new veilingTimer(), eindTijd.getTime()); //timer starten
	}
	
	private Bod getHoogsteBod() {
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
			System.out.println("De veiling van "+productNaam+" is afgelopen. ");
			if(getHoogsteBod() == null)	
				System.out.println("Niet verkocht");
			else
				System.out.println("Verkocht aan "+getHoogsteBod().getBieder().getVoornaam()+" voor "+getHoogsteBod().getBedrag());
		}
	}	
}
