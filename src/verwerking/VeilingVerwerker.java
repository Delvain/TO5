package verwerking;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import plarktmaatsDAO.PersoonDAOImpl;
import plarktmaatsDAO.VeilingDAOImpl;
import plarktmaatsDomein.Gebruiker;
import plarktmaatsDomein.Veiling;

public abstract class VeilingVerwerker {

	public static void verwerk() {
		VeilingDAOImpl dao = new VeilingDAOImpl();
		
		ArrayList<Veiling> alleVeilingen = dao.getAll(); //Alle veilingen ophalen
		
		for(Veiling v : alleVeilingen) { //Alle veilingen doorlopen
			if(!v.getVerwerkt()) { //Veiling is nog niet verwerkt
				Date eindDate 	= v.getEindTijd().getTime();
				Date nuDate 	= Calendar.getInstance().getTime();
				long verschil	= nuDate.getTime() - eindDate.getTime();
				if(verschil > 0) { //Veiling is afgelopen
					if(v.getHoogsteBod() != null) { //er is geboden
						//verwerk credits:
						int hoogsteBod = v.getHoogsteBod().getBedrag();
						
						PersoonDAOImpl persoonDAO = new PersoonDAOImpl();
						
						//credits toevoegen bij aanbieder
						Gebruiker aanbieder = v.getAanbieder();
						aanbieder.voegCreditsToe(hoogsteBod);
						persoonDAO.update(aanbieder.getGebruikersnaam(), aanbieder);
						
						//credits afschrijven bij bieder
						Gebruiker bieder = v.getHoogsteBod().getBieder();
						bieder.verwijderCredits(hoogsteBod);
						persoonDAO.update(bieder.getGebruikersnaam(), bieder);
						
					}
					//opslaan dat veiling is verwerkt
					Veiling nieuweVeiling = v;
					nieuweVeiling.setVerwerkt(true);
					
					dao.update(v.getVeilingId()+"", nieuweVeiling);
					System.out.println(v.getVeilingNaam()+" verwerkt");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		VeilingVerwerker.verwerk();
	}
}
