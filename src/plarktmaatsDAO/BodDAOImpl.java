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
import plarktmaatsDomein.Gebruiker;

public class BodDAOImpl implements PlarktmaatsDAOInterface<Bod> {

	@Override
	public void create(Bod b) { 
		int bedrag = b.getBedrag();
		Calendar tijdstip = b.getDatum();
		if (tijdstip == null) {
			tijdstip = Calendar.getInstance();
		}
		Date bodtijdstip = new java.sql.Date(tijdstip.getTimeInMillis());
		String gebruikersNaam = b.getBieder().getGebruikersnaam();
		String veilingId = b.getVeilingId();
		
		String query = "INSERT INTO "+ConnectionData.DATABASE+".\"BIEDINGEN\" VALUES (seq_bod.nextval, '"+bedrag+"', To_Date('"+bodtijdstip+"','yyyy-mm-dd'), '"+gebruikersNaam+"', '"+veilingId+"')";
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
				Date tijdstipTemp = rs.getDate("TIJDSTIP");
				Calendar tijdstip = Calendar.getInstance();
				tijdstip.setTime(tijdstipTemp);
				String gebruikersnaam = rs.getString("GEBRUIKERS_GEBRUIKERSNAAM");
				String veilingId = rs.getString("VEILINGEN_ID");

				PersoonDAOImpl persoonDAO = new PersoonDAOImpl();
				Gebruiker bieder = (Gebruiker)persoonDAO.read(gebruikersnaam);
				
				return new Bod(id, bedrag, tijdstip, bieder, veilingId);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Bod getHoogsteBodTussen(Calendar begin, Calendar eind) {
		Connection con = connect();
		try {
			Date begintijdstip = new java.sql.Date(begin.getTimeInMillis());
			Date eindtijdstip = new java.sql.Date(eind.getTimeInMillis());
			PreparedStatement read = con.prepareStatement("SELECT * FROM (SELECT * FROM "+ConnectionData.DATABASE+".\"BIEDINGEN\" WHERE tijdstip >= To_Date('"+ begintijdstip+"','yyyy-mm-dd') AND tijdstip <= To_Date('"+eindtijdstip+"','yyyy-mm-dd') ORDER BY bedrag DESC) WHERE ROWNUM = 1");
			ResultSet rs = read.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("ID");
				int bedrag = rs.getInt("BEDRAG");;
				Date tijdstipTemp = rs.getDate("TIJDSTIP"); //rs.getDate("GEBDATUM");
				Calendar tijdstip = Calendar.getInstance();
				tijdstip.setTime(tijdstipTemp);
				String gebruikersnaam = rs.getString("GEBRUIKERS_GEBRUIKERSNAAM");
				String veilingId = rs.getString("VEILINGEN_ID");

				PersoonDAOImpl persoonDAO = new PersoonDAOImpl();
				Gebruiker bieder = (Gebruiker)persoonDAO.read(gebruikersnaam);
				
				return new Bod(id, bedrag, tijdstip, bieder, veilingId);
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
				Date tijdstipTemp = rs.getDate("TIJDSTIP");
				Calendar tijdstip = Calendar.getInstance();
				tijdstip.setTime(tijdstipTemp);
				String gebruikersnaam = rs.getString("GEBRUIKERS_GEBRUIKERSNAAM");
				String veilingId = rs.getString("VEILINGEN_ID");
				
				PersoonDAOImpl dao = new PersoonDAOImpl();
				Gebruiker bieder = (Gebruiker)dao.read(gebruikersnaam);
				
				array.add(new Bod(id, bedrag, tijdstip, bieder, veilingId));
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
				Date tijdstipTemp = rs.getDate("TIJDSTIP");
				Calendar tijdstip = Calendar.getInstance();
				tijdstip.setTime(tijdstipTemp);
				String gebruikersnaam = rs.getString("GEBRUIKERS_GEBRUIKERSNAAM");
				String idVeiling = rs.getString("VEILINGEN_ID");
				
				PersoonDAOImpl dao = new PersoonDAOImpl();
				Gebruiker bieder = (Gebruiker)dao.read(gebruikersnaam);
				
				array.add(new Bod(id, bedrag, tijdstip, bieder, idVeiling));
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
		if (tijdstip == null) {
			tijdstip = Calendar.getInstance();
		}
		Date bodtijdstip = new java.sql.Date(tijdstip.getTimeInMillis());
		String gebruikersNaam = b.getBieder().getGebruikersnaam();
		
		String query = 	"UPDATE \"STUD1630460\".\"BIEDINGEN\" ";
		query +=		"SET id='"+id+"',bedrag='"+bedrag+"',tijdstip= To_Date('"+bodtijdstip+"','yyyy-mm-dd'),gebruikers_gebruikersnaam='"+gebruikersNaam+"' ";
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
}