package plarktmaatsActions.gebruikers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import plarktmaatsAware.UserAware;
import plarktmaatsDAO.CategorieDAOImpl;
import plarktmaatsDAO.VeilingDAOImpl;
import plarktmaatsDomein.Beheerder;
import plarktmaatsDomein.Categorie;
import plarktmaatsDomein.Gebruiker;
import plarktmaatsDomein.Persoon;
import plarktmaatsDomein.Veiling;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class VoegVeilingToe extends ActionSupport implements UserAware {
	private String categorie;
	private String productnaam;
	private String productomschrijving;
	private String img;
	private String minbedrag;
	private String eindDatum;
	private String eindTijd;
	private Calendar eindCalendar = Calendar.getInstance();
	private Persoon user;
	DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

	public String execute() {
		Categorie cat = new Categorie(categorie);
		int id = 0; // goede id wordt opgezocht in de database bij het
					// insert-statement mbv een sequence
		Gebruiker gebruiker = (Gebruiker) user;
		int min = Integer.parseInt(minbedrag);
		Veiling veiling = new Veiling(id, productnaam, productomschrijving,
				img, min, eindCalendar, gebruiker, cat, false);
		VeilingDAOImpl database = new VeilingDAOImpl();
		database.create(veiling);
		return SUCCESS;
	}

	public void validate() {
		// checken of gebruiker geblokkeerd is
		if (user instanceof Gebruiker) {
			Gebruiker g = (Gebruiker) user;
			if (g.getGeblokkeerd()) {
				addFieldError("categorie", "Uw account is geblokkeerd");
			}
		}
		
		// checken of user geen beheerder is
		if (user instanceof Beheerder) {
			addFieldError("categorie","Als beheerder mag u geen veilingen toevoegen!");
		}
		// checken of categorie bestaat
		CategorieDAOImpl categoriecheck = new CategorieDAOImpl();
		Categorie cat = categoriecheck.read(categorie);
		if (cat == null) {
			addFieldError("categorie", "Deze categorie bestaat niet!");
		}
		// checken of minbedrag een getal is
		try {
			int min = Integer.parseInt(minbedrag);
			if (min < 0) {
				addFieldError("minbedrag","Minimumbod mag geen negatief getal zijn!");
			}
		} catch (NumberFormatException nfe) {
			addFieldError("minbedrag", "Geen geldig minimumbod!");
		}
		// checken of productnaam goed is
		if (productnaam.equals("")) {
			addFieldError("productnaam", "Productnaam mag niet leeg zijn!");
		} else if (productnaam.length() > 35) {
			addFieldError("productnaam",
					"Productnaam mag maximaal 35 tekens zijn!");
		}
		// checken of productomschrijving goed is
		if (productomschrijving.equals("")) {
			addFieldError("productomschrijving","Productomschrijving mag niet leeg zijn!");
		} else if (productomschrijving.length() > 250) {
			addFieldError("productomschrijving","Productomschrijving mag maximaal 250 tekens zijn!");
		}
		
		//checken of de datum klopt
		if (!eindDatum.equals("")) {
			try {
				eindCalendar.setTime(df.parse(eindDatum));
				Calendar vandaag = Calendar.getInstance();
				if (eindCalendar.before(vandaag)) {
					addFieldError("eindDatum","De datum mag niet in het verleden zijn!");
				}
			} catch (ParseException e) {
				addFieldError("eindDatum","Geen geldige datum!");
			}
		} else {
			addFieldError("eindDatum","Datum mag niet leeg zijn!");
		}
		
		//checken of de tijd klopt
		int uur = 12;
		int minuut = 0;
		if (eindTijd != "") {
			if (eindTijd.contains(":")) {
				String[] parts = eindTijd.split(":");
				String strUur = parts[0];
				String strMinuut = parts[1];
				try {
					uur = Integer.parseInt(strUur);
					minuut = Integer.parseInt(strMinuut);
					if (uur < 0 || uur > 23 || minuut < 0 || minuut > 59) {
						addFieldError("eindTijd","Geen geldige tijd!");
					} else {
						eindCalendar.set(Calendar.HOUR_OF_DAY, uur);
						eindCalendar.set(Calendar.MINUTE, minuut);
					}
				} catch (NumberFormatException nfe) {
					addFieldError("eindTijd","Geen geldige tijd!");
				}
			} else {
				addFieldError("eindTijd","Geen geldige tijd!");
			}
		} else {
			int seconde = (int)Math.random()*59;
			eindCalendar.set(Calendar.HOUR_OF_DAY, uur);
			eindCalendar.set(Calendar.MINUTE, minuut);
			eindCalendar.set(Calendar.SECOND, seconde);
		}
		
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getProductnaam() {
		return productnaam;
	}

	public void setProductnaam(String productnaam) {
		this.productnaam = productnaam;
	}

	public String getProductomschrijving() {
		return productomschrijving;
	}

	public void setProductomschrijving(String productomschrijving) {
		this.productomschrijving = productomschrijving;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getMinbedrag() {
		return minbedrag;
	}

	public void setMinbedrag(String minbedrag) {
		this.minbedrag = minbedrag;
	}

	public String getEindDatum() {
		return eindDatum;
	}

	public void setEindDatum(String eindDatum) {
		this.eindDatum = eindDatum;
	}

	public String getEindTijd() {
		return eindTijd;
	}

	public void setEindTijd(String eindTijd) {
		this.eindTijd = eindTijd;
	}

	public Persoon getUser() {
		return user;
	}

	@Override
	public void setUser(Persoon user) {
		this.user = user;
	}
}
