package plarktmaatsDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import plarktmaatsDomein.Bod;
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
			PreparedStatement read = con.prepareStatement("SELECT * FROM "+ConnectionData.DATABASE+".\"VEILINGEN\" WHERE ID = ?");
			read.setString(1, pk);
			ResultSet rs = read.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("ID");
				String naam = rs.getString("NAAM");
				String omschrijving = rs.getString("OMSCHRIJVING");
				int minbedrag = rs.getInt("MINBEDRAG");
				Date eindtijdTemp = rs.getDate("EINDTIJD"); //rs.getDate("GEBDATUM");
				Calendar eindtijd = Calendar.getInstance();
				eindtijd.setTime(eindtijdTemp);
				String gebruikersnaam = rs.getString("GEBRUIKERS_GEBRUIKERSNAAM");
				String categorienaam = rs.getString("CATEGORIEEN_NAAM");
	
//				TODO foto toevoegen
//				InputStream imgStream = resultSet.getBinaryStream(2);
				
				PersoonDAOImpl dao = new PersoonDAOImpl();
				Gebruiker aanbieder = (Gebruiker)dao.read(gebruikersnaam);
				Categorie cat = new Categorie(categorienaam);
				Veiling veil = new Veiling(id, naam, omschrijving, null, minbedrag, eindtijd, aanbieder, cat);
				BodDAOImpl boddao = new BodDAOImpl();
				for (Bod b : boddao.getAllFromVeiling(id)) {
					veil.voegBodToe(b);
				}
				return veil;
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Veiling> getAll() {
		ArrayList<Veiling> array = new ArrayList<Veiling>();
		Connection con = connect();
		try {
			PreparedStatement read = con.prepareStatement("SELECT * FROM "+ConnectionData.DATABASE+".\"VEILINGEN\" ");
			ResultSet rs = read.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("ID");
				String naam = rs.getString("NAAM");
				String omschrijving = rs.getString("OMSCHRIJVING");
				int minbedrag = rs.getInt("MINBEDRAG");
				Date eindtijdTemp = rs.getDate("EINDTIJD"); //rs.getDate("GEBDATUM");
				Calendar eindtijd = Calendar.getInstance();
				eindtijd.setTime(eindtijdTemp);
				String gebruikersnaam = rs.getString("GEBRUIKERS_GEBRUIKERSNAAM");
				String categorienaam = rs.getString("CATEGORIEEN_NAAM");
				
//				TODO foto toevoegen
//				InputStream imgStream = resultSet.getBinaryStream(2);
				
				PersoonDAOImpl dao = new PersoonDAOImpl();
				Gebruiker aanbieder = (Gebruiker)dao.read(gebruikersnaam);
				Categorie cat = new Categorie(categorienaam);
				array.add(new Veiling(id, naam, omschrijving, null, minbedrag, eindtijd, aanbieder, cat));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return array;
	}

	@Override
	public void update(String pk, Veiling v) {
		int id = v.getVeilingId();
		String naam = v.getProductNaam();
		String omschrijving = v.getProductOmschrijving();
		int minbedrag = v.getMinBedrag();
		Calendar eindtijd = v.getEindTijd();
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
		query +=		"SET id='"+id+"',naam='"+naam+"',omschrijving='"+omschrijving+"',minbedrag='"+minbedrag+"',eindtijd= To_Date('"+einddatum+"','yyyy-mm-dd'),gebruikers_gebruikersnaam='"+gebruikersNaam+"',categorieen_naam='"+categorieNaam+"',foto='"+78374+"' ";
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
			PreparedStatement delete = con.prepareStatement("DELETE FROM "+ConnectionData.DATABASE+".\"VEILINGEN\" WHERE id = ?");
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
//		VeilingDAOImpl impl = new VeilingDAOImpl();
//		Calendar gebdat = Calendar.getInstance();
//		Gebruiker freak = new Gebruiker("Freak","Freek", "Nederland", "superloser@superfreak.com", gebdat, "8482929", "super");
//		Categorie cat = new Categorie("Personen");
//		Veiling veil = new Veiling(0, "freak", "superfreak original", null, 5, gebdat, freak, cat);
////		impl.create(veil);
////		veil = impl.read("freak");
////		System.out.println(veil);
////		veil = new Veiling("freak", "superfreak non-original", null, 5, gebdat, freak, cat);
////		impl.update("0", veil);
////		impl.delete("0");
//		ArrayList<Veiling> array = impl.getAll();
//		System.out.println(array.toString());
	}
}