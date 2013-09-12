package classes;

import java.util.Calendar;

public class Bod {

	private int 		bedrag;
	private Calendar 	datum;
	
	private Gebruiker	bieder;
	
	public Bod(int bdrg, Gebruiker bdr) {
		bedrag 	= bdrg;
		datum 	= Calendar.getInstance();
		bieder	= bdr;
	}
	
	public int getBedrag() {
		return bedrag;
	}
	
	public Gebruiker getBieder() {
		return bieder;
	}	
}
