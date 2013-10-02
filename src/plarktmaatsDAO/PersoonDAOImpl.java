package plarktmaatsDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import plarktmaatsDomein.Beheerder;
import plarktmaatsDomein.Gebruiker;
import plarktmaatsDomein.Persoon;

public class PersoonDAOImpl implements PlarktmaatsDAOInterface<Persoon> {

	@Override
	public void create(Persoon p) { 
		int id = p.getId();
		String voornaam = p.getVoornaam();
		String achternaam = p.getAchternaam();
		String email = p.getEmail();
		String functie = "Beheerder";
		Calendar gebdat = p.getGeboortedatum();
		Date gebdatum = null;
		if (gebdat != null) {
			gebdatum = new java.sql.Date(gebdat.getTimeInMillis());
		} else {
			gebdatum = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		}
		int credits = 0;
		String banknr = null;
		String geblokkeerd = "0";
		if (p instanceof Gebruiker) {
			Gebruiker g = (Gebruiker)p;
			functie = "Gebruiker";
			credits = g.getCredits();
			banknr = g.getBankNr();
			boolean geblokd = g.getGeblokkeerd();
			if (geblokd == true) {
				geblokkeerd = "1";
			}
		} 
		String query = "INSERT INTO "+ConnectionData.DATABASE+".\"GEBRUIKERS\" VALUES ('"+id+"', '"+voornaam+"', '"+achternaam+"', '"+email+"', '"+functie+"', To_Date('"+gebdatum+"','yyyy-mm-dd'), '"+credits+"', '"+banknr+"', '"+geblokkeerd+"')";
		Connection con = connect();
		try {
			con.createStatement().execute(query);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Persoon read(String pk) {
		Connection con = connect();
		int primaryk = Integer.parseInt(pk);
		try {
			PreparedStatement read = con.prepareStatement("SELECT * FROM "+ConnectionData.DATABASE+".\"GEBRUIKERS\" WHERE id = ?");
			read.setInt(1, primaryk);
			ResultSet rs = read.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("ID");
				String voornaam = rs.getString("VOORNAAM");
				String achternaam = rs.getString("ACHTERNAAM");
				String email = rs.getString("EMAIL");
				String functie = rs.getString("FUNCTIE");
				Calendar gebdat = null; //rs.getDate("GEBDATUM");
				int credits = rs.getInt("CREDITS");
				String banknr = rs.getString("BANKNR");
				boolean geblokkeerd = rs.getBoolean("GEBLOKKEERD");
				
				con.close();
				if (functie.equals("Gebruiker")) {
					return new Gebruiker(id, voornaam, achternaam, email, gebdat, credits, banknr, geblokkeerd);
				} else if (functie.equals("Beheerder")) {
					return new Beheerder(id, voornaam, achternaam, email, gebdat);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(String pk, Persoon p) {
		int primaryk = Integer.parseInt(pk);
		int id = p.getId();
		String voornaam = p.getVoornaam();
		String achternaam = p.getAchternaam();
		String email = p.getEmail();
		String functie = "Beheerder";
		Calendar gebdat = p.getGeboortedatum();
		Date gebdatum = null;
		if (gebdat != null) {
			gebdatum = new java.sql.Date(gebdat.getTimeInMillis());
		} else {
			gebdatum = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		}
		int credits = 0;
		String banknr = null;
		String geblokkeerd = "0";
		
		if (p instanceof Gebruiker) {
			Gebruiker g = (Gebruiker)p;
			functie = "Gebruiker";
			credits = g.getCredits();
			banknr = g.getBankNr();
			boolean geblokd = g.getGeblokkeerd();
			if (geblokd == true) {
				geblokkeerd = "1";
			}
		}
		String query = 	"UPDATE \"STUD1630460\".\"GEBRUIKERS\" ";
		query +=		"SET id='"+id+"',voornaam='"+voornaam+"',achternaam='"+achternaam+"',email='"+email+"',functie='"+functie+"',gebdatum= To_Date('"+gebdatum+"','yyyy-mm-dd'),credits='"+credits+"',banknr='"+banknr+"',geblokkeerd='"+geblokkeerd+"' ";
		query +=		"WHERE id = '"+primaryk+"'";
		Connection con = connect();
		try {
			con.createStatement().execute(query);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String pk) {
		int primaryk = Integer.parseInt(pk);
		Connection con = connect();
		try {
			PreparedStatement delete = con.prepareStatement("DELETE FROM "+ConnectionData.DATABASE+".\"GEBRUIKERS\" WHERE id = ?");
			delete.setInt(1, primaryk);
			delete.execute();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Connection connect() { //DONE
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			return DriverManager.getConnection(ConnectionData.HOST, ConnectionData.USERNAME, ConnectionData.PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		// TESTS
		
//		PersoonDAOImpl impl = new PersoonDAOImpl();
//		Calendar gebdat = Calendar.getInstance();
//		Gebruiker freak = new Gebruiker("Freek", "Holland", "geen", gebdat, "8482929");
//		impl.create(freak);
//		impl.delete("0");
//		Persoon p = impl.read("2");
//		System.out.println(p);
//		p.setAchternaam("yolo");
//		impl.update("2", p);
//		freak.setLand("STAATLOOS :O");
//		impl.update(freak.getNaam(), freak);
//		System.out.println(impl.read("Freak").getLand());
//		impl.delete("Freak");
//		System.out.println(impl.genereerWachtwoord(new Gebruiker("Hennk", "Duistland", "geen", "geen")));
	}
}