package plarktmaatsActions.gebruikers;

import plarktmaatsDAO.PersoonDAOImpl;

import com.opensymphony.xwork2.ActionSupport;

public class CreditsAJAX extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String credits = "";
	private String gebruikersnaam = "";

	public String execute(){
		gebruikersnaam = gebruikersnaam.trim();
		
		if(gebruikersnaam.length() < 0)
			return SUCCESS;
		
		PersoonDAOImpl pDI = new PersoonDAOImpl();
		
		String creditsTemp = pDI.getCredits(gebruikersnaam);
		if(creditsTemp != null)
			credits = "U heeft " + creditsTemp + " credits.";
		
		return SUCCESS;
	}
	
	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}
	public String getCredits() {
		return credits;
	}
}
