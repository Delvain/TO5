package plarktmaatsDomein;

import java.util.Calendar;

public class Beheerder extends Persoon {
	public Beheerder(String vNm, String aNm, String mail, Calendar gebdat) {
		super(vNm, aNm, mail, gebdat);
	}
	public Beheerder(int id, String vNm, String aNm, String mail, Calendar gebdat) {
		super(id, vNm, aNm, mail, gebdat);
	}
}
