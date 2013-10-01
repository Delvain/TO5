package plarktmaatsDomein;

import java.util.ArrayList;

public class Gebruiker extends Persoon {
	
	private int 	credits;
	private String	bankNr;
	private boolean geblokkeerd;
	
	private ArrayList<Veiling> alleVeilingen;

	public Gebruiker(String vNm, String aNm, String mail, String bank) {
		super(vNm, aNm, mail);
		credits 		= 0;
		bankNr 			= bank;
		geblokkeerd		= false;
		alleVeilingen 	= new ArrayList<Veiling>();
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
