package plarktmaatsDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import plarktmaatsDomein.Veiling;

public class VeilingDAOImpl implements PlarktmaatsDAOInterface<Veiling> {

	@Override
	public void create(Veiling v) { 
		
		//TODO kloppend maken met implementatie van database
		
//		String voornaam = v.getVoornaam();
//		String achternaam = v.getAchternaam();
//		String email = v.getEmail();
//		String banknr = v.getBankNr();
//		
//		String query = "INSERT INTO "+ConnectionData.DATABASE+".\"VEILING\" VALUES ('"+voornaam+"', '"+achternaam+"', '"+email+"', '"+banknr+"')";
//		Connection con = connect();
//		try {
//			con.createStatement().execute(query);
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public Veiling read(String pk) {
		Connection con = connect();
		try {
			PreparedStatement read = con.prepareStatement("SELECT * FROM "+ConnectionData.DATABASE+".\"VEILING\" WHERE naam = ?");
			read.setString(1, pk);
			ResultSet rs = read.executeQuery();
			while(rs.next()) {
				String voornaam = rs.getString("VOORNAAM");
				String achternaam = rs.getString("ACHTERNAAM");
				String email = rs.getString("EMAIL");
				String banknr = rs.getString("BANKNR");
			//	return new Veiling(voornaam, achternaam, email, banknr);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(String pk, Veiling c) {
//		String voornaam = c.getVoornaam();
//		String achternaam = c.getAchternaam();
//		String email = c.getEmail();
//		String banknr = c.getBankNr();
//		String query = 	"UPDATE \"STUD1626376\".\"VEILING\" ";
//		query +=		"SET voornaam='"+voornaam+"',achternaam='"+achternaam+"',email='"+email+"',banknr='"+banknr+"' ";
//		query +=		"WHERE naam = '"+pk+"'";
//		Connection con = connect();
//		try {
//			con.createStatement().execute(query);
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public void delete(String pk) {
		Connection con = connect();
		try {
			PreparedStatement delete = con.prepareStatement("DELETE FROM "+ConnectionData.DATABASE+".\"VEILING\" WHERE naam = ?");
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
