package plarktmaatsDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import plarktmaatsDomein.Beheerder;
import plarktmaatsDomein.Gebruiker;
import plarktmaatsDomein.Persoon;
import tools.ConnectionHandler;

public class PersoonDAOImpl implements PlarktmaatsDAOInterface<Persoon> {

	@Override
	public void create(Persoon p) { 
		String gebruikersnaam = p.getGebruikersnaam();
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
		String wachtwoord = p.getWachtwoord();
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
		String query = "INSERT INTO "+ConnectionData.DATABASE+".\"GEBRUIKERS\" VALUES ('"+gebruikersnaam+"', '"+voornaam+"', '"+achternaam+"', '"+email+"', '"+functie+"', To_Date('"+gebdatum+"','yyyy-mm-dd'), '"+credits+"', '"+banknr+"', '"+geblokkeerd+"', '"+wachtwoord+"')";
		Connection con = ConnectionHandler.connect();
		try {
			con.createStatement().execute(query);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Persoon read(String pk) {
		Connection con = ConnectionHandler.connect();
		try {
			PreparedStatement read = con.prepareStatement("SELECT * FROM "+ConnectionData.DATABASE+".\"GEBRUIKERS\" WHERE gebruikersnaam = ?");
			read.setString(1, pk);
			ResultSet rs = read.executeQuery();
			while(rs.next()) {
				String gebruikersnaam = rs.getString("GEBRUIKERSNAAM");
				String voornaam = rs.getString("VOORNAAM");
				String achternaam = rs.getString("ACHTERNAAM");
				String email = rs.getString("EMAIL");
				String functie = rs.getString("FUNCTIE");
				Date gebdatTemp = rs.getDate("GEBDATUM");
				Calendar gebdat = Calendar.getInstance();
				gebdat.setTime(gebdatTemp);
				int credits = rs.getInt("CREDITS");
				String banknr = rs.getString("BANKNR");
				boolean geblokkeerd = rs.getBoolean("GEBLOKKEERD");
				String wachtwoord = rs.getString("WACHTWOORD");
				
				con.close();
				if (functie.equals("Gebruiker")) {
					return new Gebruiker(gebruikersnaam, voornaam, achternaam, email, gebdat, credits, banknr, geblokkeerd, wachtwoord);
				} else if (functie.equals("Beheerder")) {
					return new Beheerder(gebruikersnaam, voornaam, achternaam, email, gebdat, wachtwoord);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Gebruiker> getAllGebruikers() {
		ArrayList<Gebruiker> array = new ArrayList<Gebruiker>();
		Connection con = ConnectionHandler.connect();
		try {
			PreparedStatement read = con.prepareStatement("SELECT * FROM "
					+ ConnectionData.DATABASE + ".\"GEBRUIKERS\" ");
			ResultSet rs = read.executeQuery();
			while (rs.next()) {
				String functie = rs.getString("FUNCTIE");
				if (functie.equals("Gebruiker")) {
					String gebruikersnaam = rs.getString("GEBRUIKERSNAAM");
					String voornaam = rs.getString("VOORNAAM");
					String achternaam = rs.getString("ACHTERNAAM");
					String email = rs.getString("EMAIL");
					
					Date gebdatTemp = rs.getDate("GEBDATUM");
					Calendar gebdat = Calendar.getInstance();
					gebdat.setTime(gebdatTemp);
					int credits = rs.getInt("CREDITS");
					String banknr = rs.getString("BANKNR");
					boolean geblokkeerd = rs.getBoolean("GEBLOKKEERD");
					String wachtwoord = rs.getString("WACHTWOORD");
					array.add(new Gebruiker(gebruikersnaam, voornaam, achternaam, email, gebdat, credits, banknr, geblokkeerd, wachtwoord));
				}
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return array;
	}

	@Override
	public void update(String pk, Persoon p) {
		String gebruikersnaam = p.getGebruikersnaam();
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
		String wachtwoord = p.getWachtwoord();
		
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
		query +=		"SET gebruikersnaam='"+gebruikersnaam+"',voornaam='"+voornaam+"',achternaam='"+achternaam+"',email='"+email+"',functie='"+functie+"',gebdatum= To_Date('"+gebdatum+"','yyyy-mm-dd'),credits='"+credits+"',banknr='"+banknr+"',geblokkeerd='"+geblokkeerd+"',wachtwoord='"+wachtwoord+"' ";
		query +=		"WHERE gebruikersnaam = '"+pk+"'";
		Connection con = ConnectionHandler.connect();
		try {
			con.createStatement().execute(query);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addCredits(String pk, int credits) {
		String query = 	"UPDATE \"STUD1630460\".\"GEBRUIKERS\" ";
		query +=		"SET credits= credits + " + credits;
		query +=		" WHERE gebruikersnaam = '"+pk+"'";
		Connection con = ConnectionHandler.connect();
		try {
			con.createStatement().execute(query);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String pk) {
		Connection con = ConnectionHandler.connect();
		try {
			PreparedStatement delete = con.prepareStatement("DELETE FROM "+ConnectionData.DATABASE+".\"GEBRUIKERS\" WHERE gebruikersnaam = ?");
			delete.setString(1, pk);
			delete.execute();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}