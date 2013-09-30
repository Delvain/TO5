package plarktmaatsDAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import plarktmaatsDomein.Gebruiker;

public class GebruikerDAOImpl implements PlarktmaatsDAOInterface<Gebruiker> {
	
	private String readQuery = "SELECT * FROM "+ConnectionData.DATABASE+".\"GEBRUIKER\" WHERE naam = ?";
	private String deleteQuery = "DELETE FROM "+ConnectionData.DATABASE+".\"GEBRUIKER\" WHERE naam = ?";

	@Override
	public void create(Gebruiker c) { //DONE
//		String naam = c.getNaam();
//		String land = c.getLand();
//		String tijdvak = c.getTijdvak();
//		String composities = c.getComposities();
//		String query = "INSERT INTO "+ConnectionData.DATABASE+".\"GEBRUIKER\" VALUES ('"+naam+"', '"+land+"', '"+tijdvak+"', '"+composities+"')";
//		Connection con = connect();
//		try {
//			con.createStatement().execute(query);
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public Gebruiker read(String pk) { //DONE
//		Connection con = connect();
//		try {
//			PreparedStatement read = con.prepareStatement(readQuery);
//			read.setString(1, pk);
//			ResultSet rs = read.executeQuery();
//			while(rs.next()) {
//				String naam = rs.getString("NAAM");
//				String land = rs.getString("LAND");
//				String tijdvak = rs.getString("TIJDVAK");
//				String componist = rs.getString("COMPOSITIES");
//				return new Gebruiker(naam, land, tijdvak, componist);
//			}
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return null;
	}

	@Override
	public void update(String pk, Gebruiker c) { //DONE
//		String nm = c.getNaam();
//		String ln = c.getLand();
//		String tv = c.getTijdvak();
//		String cm = c.getComposities();
//		String query = 	"UPDATE \"STUD1626376\".\"GEBRUIKER\" ";
//		query +=		"SET naam='"+nm+"',land='"+ln+"',tijdvak='"+tv+"',composities='"+cm+"' ";
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
	public void delete(String pk) { //DONE
		Connection con = connect();
		try {
			PreparedStatement delete = con.prepareStatement(deleteQuery);
			delete.setString(1, pk);
			delete.execute();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String genereerWachtwoord(Gebruiker c) {
//		Connection con = connect();
//		try {
//			CallableStatement cs = con.prepareCall("{call GENEREERWW(?, ?, ?)}");
//			cs.setString(1, c.getNaam());
//			cs.setString(2, c.getLand());
//			cs.setString(3, c.getComposities());
//			cs.execute();
//			return cs.getString(0);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return null;
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
