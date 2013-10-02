package plarktmaatsDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import plarktmaatsDomein.Categorie;
import plarktmaatsDomein.Gebruiker;
import plarktmaatsDomein.Veiling;

public class VeilingDAOImpl implements PlarktmaatsDAOInterface<Veiling> {

	@Override
	public void create(Veiling v) { 
		
		int id = v.getVeilingId();
		String naam = v.getProductNaam();
		String omschrijving = v.getProductOmschrijving();
		int minbedrag = v.getMinBedrag();
		Calendar eindtijd = v.getEindTijd();
		//TODO Date goed omzetten voor sql
		Date einddatum = null;
		if (eindtijd != null) {
			einddatum = new java.sql.Date(eindtijd.getTimeInMillis());
		} else {
			Calendar morgen = Calendar.getInstance();
			morgen.add(Calendar.DAY_OF_MONTH, 1);
			einddatum = new java.sql.Date(morgen.getTimeInMillis());
		}
		
		int gebruikersId = v.getAanbieder().getId();
		String categorieNaam = v.getDeCategorie().getNaam();
		
		//TODO foto erin zetten als blob
		//Object foto = v.getFoto();
		
		String query = "INSERT INTO "+ConnectionData.DATABASE+".\"VEILINGEN\" VALUES ('"+id+"', '"+naam+"', '"+omschrijving+"', '"+minbedrag+"', To_Date('"+einddatum+"','yyyy-mm-dd'), '"+gebruikersId+"', '"+categorieNaam+"', '"+4371938+"')";
		Connection con = connect();
		try {
			con.createStatement().execute(query);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
				
//				InputStream imgStream = resultSet.getBinaryStream(2);
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
		VeilingDAOImpl impl = new VeilingDAOImpl();
		Calendar gebdat = Calendar.getInstance();
		Gebruiker freak = new Gebruiker("Freek", "Holland", "geen", gebdat, "8482929");
		Categorie cat = new Categorie("superlosers");
		Veiling veil = new Veiling("freak", "superfreak original", null, 5, gebdat, freak, cat);
		impl.create(veil);
		
//		impl.create(freak);
//		freak.setLand("STAATLOOS :O");
//		impl.update(freak.getNaam(), freak);
//		System.out.println(impl.read("Freak").getLand());
//		impl.delete("Freak");
//		System.out.println(impl.genereerWachtwoord(new Gebruiker("Hennk", "Duistland", "geen", "geen")));
	}
}