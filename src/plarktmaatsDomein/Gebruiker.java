package plarktmaatsDomein;

import java.util.ArrayList;
import java.util.Calendar;

public class Gebruiker extends Persoon {
	
	private int 	credits;
	private String	bankNr;
	private boolean geblokkeerd;
	
	private ArrayList<Veiling> alleVeilingen;

	public Gebruiker(String gNm, String vNm, String aNm, String mail, Calendar gebdat, String bank) {
		super(gNm, vNm, aNm, mail, gebdat);
		credits 		= 0;
		bankNr 			= bank;
		geblokkeerd		= false;
		alleVeilingen 	= new ArrayList<Veiling>();
	}
	
	public Gebruiker(String gNm, String vNm, String aNm, String mail,
			Calendar gebdat, int cred, String bank,
			boolean geblok) {
		super(gNm, vNm, aNm, mail, gebdat);
		
	}

	//getters&setters Geblokkeerd:
	public boolean getGeblokkeerd() {
		return geblokkeerd;
	}
	public void setGeblokkeerd(boolean blok) {
		geblokkeerd = blok;
	}
	
	//getters&setters Credits:
	public synchronized boolean voegCreditsToe(int cred) {
		if(cred < 1) //Mag niks negatiefs optellen
			return false;
		credits += cred;
		return true;
	}
	public synchronized boolean verwijderCredits(int cred) {
		if(credits - cred < 0 || cred < 1) //Mag niks negatiefs aftrekken en saldo mag niet onder 0 gaan
			return false;
		credits -= cred;
		return true;
	}
	public int getCredits() {
		return credits;
	}
	
	//getters&setters BankNr:
	public String getBankNr() {
		return bankNr;
	}
	public void setBankNr(String bank) {
		bankNr = bank; 
	}
	
	//getter alleVeilingen
	public ArrayList<Veiling> getAlleVeilingen() {
		return alleVeilingen;
	}
}
