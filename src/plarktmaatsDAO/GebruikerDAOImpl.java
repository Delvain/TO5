package plarktmaatsDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import plarktmaatsDomein.Gebruiker;

public class GebruikerDAOImpl implements PlarktmaatsDAOInterface<Gebruiker> {

	@Override
	public void create(Gebruiker c) { 
		
		//TODO kloppend maken met implementatie van database
		
		String voornaam = c.getVoornaam();
		String achternaam = c.getAchternaam();
		String email = c.getEmail();
		String banknr = c.getBankNr();
		
		String query = "INSERT INTO "+ConnectionData.DATABASE+".\"GEBRUIKER\" VALUES ('"+voornaam+"', '"+achternaam+"', '"+email+"', '"+banknr+"')";
		Connection con = connect();
		try {
			con.createStatement().execute(query);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Gebruiker read(String pk) {
		Connection con = connect();
		try {
			PreparedStatement read = con.prepareStatement("SELECT * FROM "+ConnectionData.DATABASE+".\"GEBRUIKER\" WHERE naam = ?");
			read.setString(1, pk);
			ResultSet rs = read.executeQuery();
			while(rs.next()) {
				String voornaam = rs.getString("VOORNAAM");
				String achternaam = rs.getString("ACHTERNAAM");
				String email = rs.getString("EMAIL");
				String banknr = rs.getString("BANKNR");
				return new Gebruiker(voornaam, achternaam, email, banknr);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(String pk, Gebruiker c) {
		String voornaam = c.getVoornaam();
		String achternaam = c.getAchternaam();
		String email = c.getEmail();
		String banknr = c.getBankNr();
		String query = 	"UPDATE \"STUD1626376\".\"GEBRUIKER\" ";
		query +=		"SET voornaam='"+voornaam+"',achternaam='"+achternaam+"',email='"+email+"',banknr='"+banknr+"' ";
		query +=		"WHERE naam = '"+pk+"'";
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
		Connection con = connect();
		try {
			PreparedStatement delete = con.prepareStatement("DELETE FROM "+ConnectionData.DATABASE+".\"GEBRUIKER\" WHERE naam = ?");
			delete.setString(1, pk);
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
//		GebruikerDAOImpl impl = new GebruikerDAOImpl();
//		Gebruiker freak = new Gebruiker("Freak", "Holland", "geen", "Heul Veul");
//		impl.create(freak);
//		freak.setLand("STAATLOOS :O");
//		impl.update(freak.getNaam(), freak);
//		System.out.println(impl.read("Freak").getLand());
//		impl.delete("Freak");
//		System.out.println(impl.genereerWachtwoord(new Gebruiker("Hennk", "Duistland", "geen", "geen")));
	}
}