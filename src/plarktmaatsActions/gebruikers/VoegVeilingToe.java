package plarktmaatsActions.gebruikers;

import java.util.Calendar;
import java.util.Date;

import plarktmaatsAware.UserAware;
import plarktmaatsDAO.CategorieDAOImpl;
import plarktmaatsDAO.VeilingDAOImpl;
import plarktmaatsDomein.Beheerder;
import plarktmaatsDomein.Categorie;
import plarktmaatsDomein.Gebruiker;
import plarktmaatsDomein.Persoon;
import plarktmaatsDomein.Veiling;

import com.opensymphony.xwork2.ActionSupport;

public class VoegVeilingToe extends ActionSupport implements UserAware {
	private String categorie;
	private String productnaam;
	private String productomschrijving;
	private String img;
	private String minbedrag;
	private Date eindDatum;
	private Persoon user;

	public String execute() {
		Categorie cat = new Categorie(categorie);
		int id = 0; // goede id wordt opgezocht in de database bij het
					// insert-statement mbv een sequence
		Calendar eindtijd = Calendar.getInstance();
		eindtijd.setTime(eindDatum);
		Gebruiker gebruiker = (Gebruiker) user;
		int min = Integer.parseInt(minbedrag);
		Veiling veiling = new Veiling(id, productnaam, productomschrijving,
				img, min, eindtijd, gebruiker, cat, false, false);
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
		}
		if (productnaam.length() > 35) {
			addFieldError("productnaam",
					"Productnaam mag maximaal 35 tekens zijn!");
		}
		// checken of productomschrijving goed is
		if (productomschrijving.equals("")) {
			addFieldError("productomschrijving","Productomschrijving mag niet leeg zijn!");
		}
		if (productomschrijving.length() > 250) {
			addFieldError("productomschrijving","Productomschrijving mag maximaal 250 tekens zijn!");
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

	public Date getEindDatum() {
		return eindDatum;
	}

	public void setEindDatum(Date eindDatum) {
		this.eindDatum = eindDatum;
	}

	public Persoon getUser() {
		return user;
	}

	@Override
	public void setUser(Persoon user) {
		this.user = user;
	}
}
