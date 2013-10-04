package plarktmaatsDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import plarktmaatsDomein.Bod;
import plarktmaatsDomein.Categorie;
import plarktmaatsDomein.Gebruiker;
import plarktmaatsDomein.Veiling;

public class BodDAOImpl implements PlarktmaatsDAOInterface<Bod> {

	@Override
	public void create(Bod b) { 
		
		int id = b.getId();
		int bedrag = b.getBedrag();
		Calendar tijdstip = b.getDatum();
		Date bodtijdstip = null;
		if (tijdstip != null) {
			bodtijdstip = new java.sql.Date(tijdstip.getTimeInMillis());
		} else {
			Calendar morgen = Calendar.getInstance();
			morgen.add(Calendar.DAY_OF_MONTH, 1);
			bodtijdstip = new java.sql.Date(morgen.getTimeInMillis());
		}
		String gebruikersNaam = b.getBieder().getGebruikersnaam();
		int veilingId = 1;
		//TODO veilingid opzoeken:
		for (Veiling v: new VeilingDAOImpl().getAll()) {
			for (Bod bied : v.getAlleBiedingen()) {
				
			}
		}
		
		
		String query = "INSERT INTO "+ConnectionData.DATABASE+".\"BIEDINGEN\" VALUES ('"+id+"', '"+bedrag+"', To_Date('"+bodtijdstip+"','yyyy-mm-dd'), '"+gebruikersNaam+"', '"+veilingId+"')";
		Connection con = connect();
		try {
			con.createStatement().execute(query);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Bod read(String pk) {
		int primaryK = Integer.parseInt(pk);
		Connection con = connect();
		try {
			PreparedStatement read = con.prepareStatement("SELECT * FROM "+ConnectionData.DATABASE+".\"BIEDINGEN\" WHERE ID = ?");
			read.setInt(1, primaryK);
			ResultSet rs = read.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("ID");
				int bedrag = rs.getInt("BEDRAG");;
				Date tijdstipTemp = rs.getDate("TIJDSTIP"); //rs.getDate("GEBDATUM");
				Calendar tijdstip = Calendar.getInstance();
				tijdstip.setTime(tijdstipTemp);
				String gebruikersnaam = rs.getString("GEBRUIKERS_GEBRUIKERSNAAM");
				
				//TODO iets doen met veiling?
				//int veilingId = rs.getInt("VEILINGEN_ID");
				
				PersoonDAOImpl dao = new PersoonDAOImpl();
				Gebruiker bieder = (Gebruiker)dao.read(gebruikersnaam);
				return new Bod(id, bedrag, tijdstip, bieder);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Bod> getAll() {
		ArrayList<Bod> array = new ArrayList<Bod>();
		Connection con = connect();
		try {
			PreparedStatement read = con.prepareStatement("SELECT * FROM "+ConnectionData.DATABASE+".\"BIEDINGEN\"");
			ResultSet rs = read.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("ID");
				int bedrag = rs.getInt("BEDRAG");;
				Date tijdstipTemp = rs.getDate("TIJDSTIP"); //rs.getDate("GEBDATUM");
				Calendar tijdstip = Calendar.getInstance();
				tijdstip.setTime(tijdstipTemp);
				String gebruikersnaam = rs.getString("GEBRUIKERS_GEBRUIKERSNAAM");
				
				//TODO iets doen met veilingid?
				//int veilingId = rs.getInt("VEILINGEN_ID");
				PersoonDAOImpl dao = new PersoonDAOImpl();
				Gebruiker bieder = (Gebruiker)dao.read(gebruikersnaam);
				array.add(new Bod(id, bedrag, tijdstip, bieder));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array;
	}
	
	public ArrayList<Bod> getAllFromVeiling(int veilingId) {
		ArrayList<Bod> array = new ArrayList<Bod>();
		Connection con = connect();
		try {
			PreparedStatement read = con.prepareStatement("SELECT * FROM "+ConnectionData.DATABASE+".\"BIEDINGEN\" WHERE VEILINGEN_ID = ?");
			read.setInt(1, veilingId);
			ResultSet rs = read.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("ID");
				int bedrag = rs.getInt("BEDRAG");;
				Date tijdstipTemp = rs.getDate("TIJDSTIP"); //rs.getDate("GEBDATUM");
				Calendar tijdstip = Calendar.getInstance();
				tijdstip.setTime(tijdstipTemp);
				String gebruikersnaam = rs.getString("GEBRUIKERS_GEBRUIKERSNAAM");
				
				PersoonDAOImpl dao = new PersoonDAOImpl();
				Gebruiker bieder = (Gebruiker)dao.read(gebruikersnaam);
				array.add(new Bod(id, bedrag, tijdstip, bieder));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array;
	}

	@Override
	public void update(String pk, Bod b) {
		int primaryKey = Integer.parseInt(pk);
		int id = b.getId();
		int bedrag = b.getBedrag();
		Calendar tijdstip = b.getDatum();
		Date bodtijdstip = null;
		if (tijdstip != null) {
			bodtijdstip = new java.sql.Date(tijdstip.getTimeInMillis());
		} else {
			Calendar morgen = Calendar.getInstance();
			morgen.add(Calendar.DAY_OF_MONTH, 1);
			bodtijdstip = new java.sql.Date(morgen.getTimeInMillis());
		}
		String gebruikersNaam = b.getBieder().getGebruikersnaam();
		int veilingId = 1;
		//TODO veilingid opzoeken:
		for (Veiling v: new VeilingDAOImpl().getAll()) {
			for (Bod bied : v.getAlleBiedingen()) {
				
			}
		}
		
		String query = 	"UPDATE \"STUD1630460\".\"BIEDINGEN\" ";
		query +=		"SET id='"+id+"',bedrag='"+bedrag+"',tijdstip= To_Date('"+tijdstip+"','yyyy-mm-dd'),gebruikers_gebruikersnaam='"+gebruikersNaam+"',veilingen_id='"+veilingId+"' ";
		query +=		"WHERE id = '"+primaryKey+"'";
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
			PreparedStatement delete = con.prepareStatement("DELETE FROM "+ConnectionData.DATABASE+".\"BIEDINGEN\" WHERE id = ?");
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
		BodDAOImpl impl = new BodDAOImpl();
		Calendar gebdat = Calendar.getInstance();
		Gebruiker freak = new Gebruiker("Freak","Freek", "Nederland", "superloser@superfreak.com", gebdat, "8482929", "super");
		Bod bod = new Bod(2, 50, Calendar.getInstance(), freak);
//		impl.create(bod);
//		bod = impl.read("2");
//		System.out.println(bod);
//		veil = new Bod("freak", "superfreak non-original", null, 5, gebdat, freak, cat);
//		impl.update("0", veil);
//		impl.delete("2");
//		ArrayList<Bod> array = impl.getAll();
//		System.out.println(array.toString());
	}
}