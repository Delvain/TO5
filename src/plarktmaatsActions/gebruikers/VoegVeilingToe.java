package plarktmaatsActions.gebruikers;

import java.util.Calendar;
import java.util.Date;

import plarktmaatsAware.UserAware;
import plarktmaatsDAO.CategorieDAOImpl;
import plarktmaatsDAO.VeilingDAOImpl;
import plarktmaatsDomein.Categorie;
import plarktmaatsDomein.Gebruiker;
import plarktmaatsDomein.Persoon;
import plarktmaatsDomein.Veiling;

import com.opensymphony.xwork2.ActionSupport;

public class VoegVeilingToe extends ActionSupport implements UserAware {
	private String categorie;
	private String productnaam;
	private String productomschrijving;
	private Object img;
	private String minbedrag;
	private Date eindDatum;
	private Persoon user;
	
	
	public String execute() {
		System.out.println("voegveilingtoe execute");
		Categorie cat = new Categorie(categorie);
		img = null; //TODO foto toevoegen
		int id = 0; //TODO hoogste id pakken
		Calendar eindtijd = Calendar.getInstance();
		eindtijd.setTime(eindDatum);
		Gebruiker gebruiker = (Gebruiker) user;
		int min = Integer.parseInt(minbedrag);
		Veiling veiling = new Veiling(id, productnaam, productomschrijving, img, min, eindtijd, gebruiker, cat);
		VeilingDAOImpl database = new VeilingDAOImpl();
		database.create(veiling);
		System.out.println("voegveilingtoe execute succes");
		return SUCCESS;
	}
	
	public void validate() {
		System.out.println("voegveilingtoe validate");
		//checken of categorie bestaat
		CategorieDAOImpl categoriecheck = new CategorieDAOImpl();
		Categorie cat = categoriecheck.read(categorie);
		if (cat == null) {
			addFieldError("categorie", "Deze categorie bestaat niet!");
		}
		//checken of minbedrag een getal is
		try {
			int min = Integer.parseInt(minbedrag);
			if (min < 0) {
				addFieldError("minbedrag","Minimumbod mag geen negatief getal zijn!");
			}
		} catch (NumberFormatException nfe) {
			addFieldError("minbedrag","Geen geldig minimumbod!");
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

	public Object getImg() {
		return img;
	}

	public void setImg(Object img) {
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
