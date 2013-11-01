package plarktmaatsDomein;

import java.util.Calendar;

public class Bod {

	private int			id;
	private int 		bedrag;
	private Calendar 	datum;
	
	private Gebruiker	bieder;
	private String		veilingId;
	
	public Bod(int id, int bdrg, Gebruiker bdr, String veilId) {
		this.id = id;
		bedrag 	= bdrg;
		datum 	= Calendar.getInstance();
		bieder	= bdr;
		veilingId = veilId;
	}
	
	public Bod(int id, int bdrg, Calendar tijd, Gebruiker bdr, String veilId) {
		this.id = id;
		bedrag 	= bdrg;
		datum 	= tijd;
		bieder	= bdr;
		veilingId = veilId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBedrag() {
		return bedrag;
	}
	
	public Gebruiker getBieder() {
		return bieder;
	}
	
	public Calendar getDatum() {
		return datum;
	}

	public void setDatum(Calendar datum) {
		this.datum = datum;
	}

	public void setBedrag(int bedrag) {
		this.bedrag = bedrag;
	}

	public void setBieder(Gebruiker bieder) {
		this.bieder = bieder;
	}
	
	public void setVeilingId(String veilId) {
		veilingId = veilId;
	}
	
	public String getVeilingId() {
		return veilingId;
	}
	
	public String toString() {
		return "" + id + ", " + bedrag + ", " + bieder.getGebruikersnaam() + ", " + veilingId;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean equals = false;
		try {
			Bod b = (Bod) o;
			if (this.id == b.id && this.bedrag == b.bedrag && this.bieder.gebruikersnaam.equals(b.bieder.gebruikersnaam) && this.veilingId.equals(b.veilingId)) {
				equals = true;
			}
		} catch (Exception e) {
			
		}
		return equals;
	}
}
