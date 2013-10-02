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
		
		String gebruikersNaam = v.getAanbieder().getGebruikersnaam();
		String categorieNaam = v.getDeCategorie().getNaam();
		
		//TODO foto erin zetten als blob
		//File image = new File(path);
//		FileInputStream fis = new FileInputStream ( image );
//
//		String sql="insert into imgtst (username,image) values (?, ?)";
//		pst=con.prepareStatement(sql);
//
//		pst.setString(1, user);
//		pst.setBinaryStream (2, fis, (int) file.length() );
		
		String query = "INSERT INTO "+ConnectionData.DATABASE+".\"VEILINGEN\" VALUES ('"+id+"', '"+naam+"', '"+omschrijving+"', '"+minbedrag+"', To_Date('"+einddatum+"','yyyy-mm-dd'), '"+gebruikersNaam+"', '"+categorieNaam+"', '"+4371938+"')";
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
			PreparedStatement read = con.prepareStatement("SELECT * FROM "+ConnectionData.DATABASE+".\"VEILINGEN\" WHERE naam = ?");
			read.setString(1, pk);
			ResultSet rs = read.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("ID");
				String naam = rs.getString("NAAM");
				String omschrijving = rs.getString("OMSCHRIJVING");
				int minbedrag = rs.getInt("MINBEDRAG");
				Calendar eindtijd = null; //rs.getDate("GEBDATUM");
				String gebruikersnaam = rs.getString("GEBRUIKERS_GEBRUIKERSNAAM");
				String categorienaam = rs.getString("CATEGORIEEN_NAAM");
				
//				InputStream imgStream = resultSet.getBinaryStream(2);
				
				PersoonDAOImpl dao = new PersoonDAOImpl();
				Gebruiker aanbieder = (Gebruiker)dao.read(gebruikersnaam);
				Categorie cat = new Categorie(categorienaam);
				return new Veiling(naam, omschrijving, null, minbedrag, eindtijd, aanbieder, cat);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(String pk, Veiling v) {
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
		
		String gebruikersNaam = v.getAanbieder().getGebruikersnaam();
		String categorieNaam = v.getDeCategorie().getNaam();
		
		//TODO foto erin zetten als blob
		//File image = new File(path);
//		FileInputStream fis = new FileInputStream ( image );
//
//		String sql="insert into imgtst (username,image) values (?, ?)";
//		pst=con.prepareStatement(sql);
//
//		pst.setString(1, user);
//		pst.setBinaryStream (2, fis, (int) file.length() );
		
		
		String query = 	"UPDATE \"STUD1630460\".\"VEILINGEN\" ";
		query +=		"SET id='"+id+"',naam='"+naam+"',omschrijving='"+omschrijving+"',minbedrag='"+minbedrag+"',eindtijd= To_Date('"+eindtijd+"','yyyy-mm-dd'),gebruikers_gebruikersnaam='"+gebruikersNaam+"',categorieen_naam='"+categorieNaam+"',foto='"+null+"' ";
		query +=		"WHERE id = '"+pk+"'";
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
		Gebruiker freak = new Gebruiker("Freak", "Freek", "Holland", "geen", gebdat, "8482929");
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