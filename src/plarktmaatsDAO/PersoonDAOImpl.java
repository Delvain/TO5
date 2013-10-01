package plarktmaatsDAO;

import java.sql.Connection;
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
		if (p instanceof Gebruiker) {
			Gebruiker g = (Gebruiker)p;
			int id = g.getId();
			String voornaam = g.getVoornaam();
			String achternaam = g.getAchternaam();
			String email = g.getEmail();
			String functie = "Gebruiker";
			Calendar gebdat = g.getGeboortedatum();
			int credits = g.getCredits();
			String banknr = g.getBankNr();
			boolean geblokd = g.getGeblokkeerd();
			String geblokkeerd = "0";
			if (geblokd = true) {
				geblokkeerd = "1";
			}
						
			String query = "INSERT INTO "+ConnectionData.DATABASE+".\"GEBRUIKERS\" VALUES ('"+id+"', '"+voornaam+"', '"+achternaam+"', '"+email+"', '"+functie+"', '"+gebdat+"', '"+credits+"', '"+banknr+"', '"+geblokkeerd+"')";
			Connection con = connect();
			try {
				con.createStatement().execute(query);
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (p instanceof Beheerder) {
			Beheerder b = (Beheerder)p;
			int id = b.getId();
			String voornaam = b.getVoornaam();
			String achternaam = b.getAchternaam();
			String email = b.getEmail();
			String functie = "Beheerder";
			Calendar gebdat = b.getGeboortedatum();
						
			String query = "INSERT INTO "+ConnectionData.DATABASE+".\"GEBRUIKERS\" VALUES ('"+id+"', '"+voornaam+"', '"+achternaam+"', '"+email+"', '"+functie+"', '"+gebdat+"', '"+null+"', '"+null+"', '"+null+"')";
			Connection con = connect();
			try {
				con.createStatement().execute(query);
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
		if (p instanceof Gebruiker) {
			Gebruiker g = (Gebruiker)p;
			int id = g.getId();
			String voornaam = g.getVoornaam();
			String achternaam = g.getAchternaam();
			String email = g.getEmail();
			String functie = "Gebruiker";
			Calendar gebdat = g.getGeboortedatum();
			int credits = g.getCredits();
			String banknr = g.getBankNr();
			boolean geblokkeerd = g.getGeblokkeerd();
			String query = 	"UPDATE \"STUD1626376\".\"GEBRUIKERS\" ";
			query +=		"SET id='"+id+"',voornaam='"+voornaam+"',achternaam='"+achternaam+"',email='"+email+"',functie='"+functie+"',gebdatum='"+gebdat+"',credits='"+credits+"',banknr='"+banknr+"',geblokkeerd='"+geblokkeerd+"' ";
			query +=		"WHERE id = '"+primaryk+"'";
			Connection con = connect();
			try {
				con.createStatement().execute(query);
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (p instanceof Beheerder) {
			Beheerder b = (Beheerder)p;
			int id = b.getId();
			String voornaam = b.getVoornaam();
			String achternaam = b.getAchternaam();
			String email = b.getEmail();
			String functie = "Gebruiker";
			Calendar gebdat = b.getGeboortedatum();
			String query = 	"UPDATE \"STUD1626376\".\"GEBRUIKERS\" ";
			query +=		"SET id='"+id+"',voornaam='"+voornaam+"',achternaam='"+achternaam+"',email='"+email+"',functie='"+functie+"',gebdatum='"+gebdat+"',credits='"+null+"',banknr='"+null+"',geblokkeerd='"+null+"' ";
			query +=		"WHERE id = '"+primaryk+"'";
			Connection con = connect();
			try {
				con.createStatement().execute(query);
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
		PersoonDAOImpl impl = new PersoonDAOImpl();
		Gebruiker freak = new Gebruiker("Freak", "Holland", "geen", null, "Heul Veul");
		impl.create(freak);
//		freak.setLand("STAATLOOS :O");
//		impl.update(freak.getNaam(), freak);
//		System.out.println(impl.read("Freak").getLand());
//		impl.delete("Freak");
//		System.out.println(impl.genereerWachtwoord(new Gebruiker("Hennk", "Duistland", "geen", "geen")));
	}
}
