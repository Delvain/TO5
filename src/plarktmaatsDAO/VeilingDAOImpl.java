package plarktmaatsDAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import plarktmaatsDomein.Bod;
import plarktmaatsDomein.Categorie;
import plarktmaatsDomein.Gebruiker;
import plarktmaatsDomein.Veiling;
import tools.ProjectTools;

public class VeilingDAOImpl implements PlarktmaatsDAOInterface<Veiling> {

	@Override
	public void create(Veiling v) {
		String naam = v.getVeilingNaam();
		String omschrijving = v.getVeilingOmschrijving();
		int minbedrag = v.getMinBedrag();
		Calendar eindtijd = v.getEindTijd();
		String einddatum = ProjectTools.CalendarToString(eindtijd);

		String gebruikersNaam = v.getAanbieder().getGebruikersnaam();
		String categorieNaam = v.getDeCategorie().getNaam();
		String foto = v.getFoto();
		int geblokkeerd = 0;
		if(v.getGeblokkeerd())
			geblokkeerd = 1;
		String query = "INSERT INTO " + ConnectionData.DATABASE	+ ".\"VEILINGEN\" VALUES (seq_veiling.nextval, '" + naam + "', '" + omschrijving + "', '" + minbedrag + "', To_Date('" + einddatum + "','yyyy-mm-dd HH24:MI:SS'), '" + gebruikersNaam + "', '" + categorieNaam + "', '" + foto + "', "+geblokkeerd+")";
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
			PreparedStatement read = con.prepareStatement("SELECT * FROM "
					+ ConnectionData.DATABASE + ".\"VEILINGEN\" WHERE ID = ?");
			read.setString(1, pk);
			ResultSet rs = read.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String naam = rs.getString("NAAM");
				String omschrijving = rs.getString("OMSCHRIJVING");
				int minbedrag = rs.getInt("MINBEDRAG");
				Date eindtijdTemp = rs.getDate("EINDTIJD");
				Calendar eindtijd = Calendar.getInstance();
				eindtijd.setTime(eindtijdTemp);
				String gebruikersnaam = rs
						.getString("GEBRUIKERS_GEBRUIKERSNAAM");
				String categorienaam = rs.getString("CATEGORIEEN_NAAM");
				boolean geblokkeerd = false;
				if(rs.getInt("GEBLOKKEERD") == 1)
					geblokkeerd = true;
				String foto = rs.getString("FOTO");

				PersoonDAOImpl dao = new PersoonDAOImpl();
				Gebruiker aanbieder = (Gebruiker) dao.read(gebruikersnaam);
				Categorie cat = new Categorie(categorienaam);
				Veiling veil = new Veiling(id, naam, omschrijving, foto,
						minbedrag, eindtijd, aanbieder, cat, geblokkeerd);
				BodDAOImpl boddao = new BodDAOImpl();
				ArrayList<Bod> biedingen = boddao.getAllFromVeiling(id);
				if (!biedingen.isEmpty()) {
					for (Bod b : biedingen) {
						veil.voegBodToe(b);
					}
				}
				return veil;
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Ophalen alle veilingen van een specifieke gebruiker
	public List<Veiling> mijnVeilingen(String gebruikersNaam) {
		Connection con = connect();
		ArrayList<Veiling> mijnVeilingen = new ArrayList<Veiling>();
		try {
			PreparedStatement read = con.prepareStatement("SELECT * FROM " + ConnectionData.DATABASE + ".\"VEILINGEN\" WHERE GEBRUIKERS_GEBRUIKERSNAAM = ?");
			read.setString(1, gebruikersNaam);
			ResultSet rs = read.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String naam = rs.getString("NAAM");
				String omschrijving = rs.getString("OMSCHRIJVING");
				int minbedrag = rs.getInt("MINBEDRAG");
				Date eindtijdTemp = rs.getDate("EINDTIJD"); // rs.getDate("GEBDATUM");
				Calendar eindtijd = Calendar.getInstance();
				eindtijd.setTime(eindtijdTemp);
				String foto = rs.getString("FOTO");
				String categorienaam = rs.getString("CATEGORIEEN_NAAM");
				PersoonDAOImpl dao = new PersoonDAOImpl();
				Gebruiker aanbieder = (Gebruiker) dao.read(gebruikersNaam);
				Categorie cat = new Categorie(categorienaam);
				boolean geblokkeerd = false;
				if(rs.getInt("GEBLOKKEERD") == 1)
					geblokkeerd = true;
				Veiling v = new Veiling(id, naam, omschrijving, foto, minbedrag, eindtijd, aanbieder, cat, geblokkeerd);
				mijnVeilingen.add(v);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mijnVeilingen;
	}

	//Ophalen alle biedingen van een specifieke gebruiker
	public List<Bod> mijnBiedingen(String gebruikersNaam) {
		Connection con = connect();
		ArrayList<Bod> mijnBiedingen = new ArrayList<Bod>();
		try {
			PreparedStatement read = con.prepareStatement("SELECT * FROM " + ConnectionData.DATABASE + ".\"BIEDINGEN\" WHERE GEBRUIKERS_GEBRUIKERSNAAM = ?");
			read.setString(1, gebruikersNaam);
			ResultSet rs = read.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				int bedrag = rs.getInt("BEDRAG");
				Date tijdstipTemp = rs.getDate("TIJDSTIP");
				Calendar tijdstip = Calendar.getInstance();
				tijdstip.setTime(tijdstipTemp);
				String veilingId = rs.getString("VEILINGEN_ID");
				PersoonDAOImpl dao = new PersoonDAOImpl();
				Gebruiker bieder = (Gebruiker) dao.read(gebruikersNaam);
				Bod b = new Bod(id, bedrag, tijdstip, bieder, veilingId);
				mijnBiedingen.add(b);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mijnBiedingen;
	}
	
	//Ophalen alle veilingen van een specifieke gebruiker
	public List<Veiling> zoekVeiling(String zoekterm) {
		Connection con = connect();
		ArrayList<Veiling> zoekResultaten = new ArrayList<Veiling>();
		try {
			//Zoek op naam
			PreparedStatement readNaam = con.prepareStatement("SELECT * FROM " + ConnectionData.DATABASE + ".\"VEILINGEN\" WHERE naam COLLATE UTF8_GENERAL_CI LIKE %?%");
			readNaam.setString(1, zoekterm);			
			ResultSet rsNaam = readNaam.executeQuery();	
			
			while (rsNaam.next()) {
				int id = rsNaam.getInt("ID");
				String naam = rsNaam.getString("NAAM");
				String omschrijving = rsNaam.getString("OMSCHRIJVING");
				int minbedrag = rsNaam.getInt("MINBEDRAG");
				Date eindtijdTemp = rsNaam.getDate("EINDTIJD"); // rs.getDate("GEBDATUM");
				Calendar eindtijd = Calendar.getInstance();
				eindtijd.setTime(eindtijdTemp);
				String foto = rsNaam.getString("FOTO");
				String categorienaam = rsNaam.getString("CATEGORIEEN_NAAM");
				PersoonDAOImpl dao = new PersoonDAOImpl();
				Gebruiker aanbieder = (Gebruiker) dao.read(zoekterm);
				Categorie cat = new Categorie(categorienaam);
				boolean geblokkeerd = false;
				if(rsNaam.getInt("GEBLOKKEERD") == 1)
					geblokkeerd = true;
				Veiling v = new Veiling(id, naam, omschrijving, foto, minbedrag, eindtijd, aanbieder, cat, geblokkeerd);
				zoekResultaten.add(v);
			}
			
			//Zoek op omschrijving
			PreparedStatement readOmschrijving = con.prepareStatement("SELECT * FROM " + ConnectionData.DATABASE + ".\"VEILINGEN\" WHERE omschrijving COLLATE UTF8_GENERAL_CI LIKE %?%");
			readOmschrijving.setString(1, zoekterm);
			ResultSet rsOmschrijving = readOmschrijving.executeQuery();
			
			while (rsOmschrijving.next()) {
				int id = rsOmschrijving.getInt("ID");
				String naam = rsOmschrijving.getString("NAAM");
				String omschrijving = rsOmschrijving.getString("OMSCHRIJVING");
				int minbedrag = rsOmschrijving.getInt("MINBEDRAG");
				Date eindtijdTemp = rsOmschrijving.getDate("EINDTIJD"); // rs.getDate("GEBDATUM");
				Calendar eindtijd = Calendar.getInstance();
				eindtijd.setTime(eindtijdTemp);
				String foto = rsOmschrijving.getString("FOTO");
				String categorienaam = rsOmschrijving.getString("CATEGORIEEN_NAAM");
				PersoonDAOImpl dao = new PersoonDAOImpl();
				Gebruiker aanbieder = (Gebruiker) dao.read(zoekterm);
				Categorie cat = new Categorie(categorienaam);
				boolean geblokkeerd = false;
				if(rsOmschrijving.getInt("GEBLOKKEERD") == 1)
					geblokkeerd = true;
				Veiling v = new Veiling(id, naam, omschrijving, foto, minbedrag, eindtijd, aanbieder, cat, geblokkeerd);
				if (!zoekResultaten.contains(v)) {
					zoekResultaten.add(v);
				}
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return zoekResultaten;
	}
	
	//Ophalen van de veilingNaam (voor de associate tussen bod en veiling; in bod bestaat alleen een verwijzing dmv veilingId)
	public String getVeilingNaam(String veilingId) {
		String veilingNaam = null;
		Connection con = connect();
		String query = "SELECT naam FROM " + ConnectionData.DATABASE + ".\"VEILINGEN\" WHERE id = " + veilingId;
		
		try {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			while (rs.next()) {
				veilingNaam = rs.getString("NAAM");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return veilingNaam;
	}
	
	public ArrayList<String> readBedragTijd(int pk) {
		ArrayList<String> returnVal = new ArrayList<String>();
		
		Connection con = connect();
		try {
			String read = "SELECT minbedrag, eindtijd FROM " + ConnectionData.DATABASE + ".\"VEILINGEN\" WHERE ID = "+pk;
			ResultSet rs = con.createStatement().executeQuery(read);
			while (rs.next()) {
				int bedrag = rs.getInt("MINBEDRAG");				

				BodDAOImpl boddao = new BodDAOImpl();
				ArrayList<Bod> biedingen = boddao.getAllFromVeiling(pk);
				for(Bod b : biedingen) {
					if(b.getBedrag() > bedrag)
						bedrag = b.getBedrag();
				}
				returnVal.add(0, bedrag+"");
				
				Date eindtijd = rs.getDate("EINDTIJD");
				Date nu = (Date)Calendar.getInstance().getTime();
				
				long diff = (eindtijd.getTime() - nu.getTime())/1000;
				
				returnVal.add(1, diff+"");
				}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnVal;
	}
	
	public ArrayList<Veiling> getAll() {
		ArrayList<Veiling> array = new ArrayList<Veiling>();
		Connection con = connect();
		try {
			PreparedStatement read = con.prepareStatement("SELECT * FROM "
					+ ConnectionData.DATABASE + ".\"VEILINGEN\" ORDER BY eindtijd ASC");
			ResultSet rs = read.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String naam = rs.getString("NAAM");
				String omschrijving = rs.getString("OMSCHRIJVING");
				int minbedrag = rs.getInt("MINBEDRAG");
				Date eindtijdTemp = rs.getDate("EINDTIJD");
				Calendar eindtijd = Calendar.getInstance();
				eindtijd.setTime(eindtijdTemp);
				String gebruikersnaam = rs
						.getString("GEBRUIKERS_GEBRUIKERSNAAM");
				String categorienaam = rs.getString("CATEGORIEEN_NAAM");
				String foto = rs.getString("FOTO");

				PersoonDAOImpl persoonDAO = new PersoonDAOImpl();
				Gebruiker aanbieder = (Gebruiker) persoonDAO.read(gebruikersnaam);
				Categorie cat = new Categorie(categorienaam);
				boolean geblokkeerd = false;
				if(rs.getInt("GEBLOKKEERD") == 1)
					geblokkeerd = true;
				Veiling v = new Veiling(id, naam, omschrijving, foto, minbedrag,
						eindtijd, aanbieder, cat, geblokkeerd);
				BodDAOImpl bodDAO = new BodDAOImpl();
				ArrayList<Bod> alleBiedingen = bodDAO.getAll();
				for(Bod b : alleBiedingen) {
					if(b.getVeilingId().equals(id+""))
						v.voegBodToe(b);
				}
				array.add(v);
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
		String naam = v.getVeilingNaam();
		String omschrijving = v.getVeilingOmschrijving();
		int minbedrag = v.getMinBedrag();
		Calendar eindtijd = v.getEindTijd();
		String einddatum = ProjectTools.CalendarToString(eindtijd);

		String gebruikersNaam = v.getAanbieder().getGebruikersnaam();
		String categorieNaam = v.getDeCategorie().getNaam();
		String foto = v.getFoto();
		int geblokkeerd = 0;
		if(v.getGeblokkeerd())
			geblokkeerd = 1;

		String query = "UPDATE \"STUD1630460\".\"VEILINGEN\" ";
		query += "SET id='" + id + "',naam='" + naam + "',omschrijving='"
				+ omschrijving + "',minbedrag='" + minbedrag
				+ "',eindtijd= To_Date('" + einddatum
				+ "','yyyy-mm-dd HH24:MI:SS'),gebruikers_gebruikersnaam='"
				+ gebruikersNaam + "',categorieen_naam='" + categorieNaam
				+ "',foto='" + foto + "', geblokkeerd='"+geblokkeerd+"' ";
		query += "WHERE id = '" + pk + "'";
		Connection con = connect();
		try {
			con.createStatement().execute(query);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void blokkeer(String pk) {
		String query = "UPDATE \"STUD1630460\".\"VEILINGEN\" ";
		query += "SET geblokkeerd='1'";
		query += "WHERE id = '" + pk + "'";
		Connection con = connect();
		try {
			con.createStatement().execute(query);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deblokkeer(String pk) {
		String query = "UPDATE \"STUD1630460\".\"VEILINGEN\" ";
		query += "SET geblokkeerd='0'";
		query += "WHERE id = '" + pk + "'";
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
			PreparedStatement delete = con.prepareStatement("DELETE FROM "
					+ ConnectionData.DATABASE + ".\"VEILINGEN\" WHERE id = ?");
			delete.setString(1, pk);
			delete.execute();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String verwerkVeilingen() {
		Connection con = connect();
		
		CallableStatement callableStatement = null;
		try {
			callableStatement = con.prepareCall("{call verwerk_veilingen}");
			callableStatement.executeUpdate();
		} catch (SQLException e1) {
			
		}
		
		try {
			PreparedStatement read = con.prepareStatement("SELECT * FROM "
					+ ConnectionData.DATABASE + ".\"VEILINGVERWERKTDATUM\" ");
			ResultSet rs = read.executeQuery();
			while (rs.next()) {
				Date verwerktTotTemp = rs.getDate("VEILINGEN_VERWERKT_TOT");
				Calendar verwerktTot = Calendar.getInstance();
				verwerktTot.setTime(verwerktTotTemp);
				return ProjectTools.CalendarToNiceString(verwerktTot);
			}
		}catch (SQLException e) {
			
		}
		return "";
	}
	
	public List<String[]> getInkomstenOverzicht(Calendar begin, Calendar eind) {
		Date begintijdstip = new java.sql.Date(begin.getTimeInMillis());
		Date eindtijdstip = new java.sql.Date(eind.getTimeInMillis());
		List<String[]> array = new ArrayList<String[]>();
		Connection con = connect();
		
		CallableStatement callableStatement = null;
		try {
			callableStatement = con.prepareCall("{call inkomstenoverzicht_procedure}");
			callableStatement.executeUpdate();
		} catch (SQLException e1) {
			
		}
		
		
		try {
			PreparedStatement read = con.prepareStatement("SELECT * FROM "
					+ ConnectionData.DATABASE + ".\"INKOMSTENOVERZICHT\" WHERE eindtijd >= To_Date('"+ begintijdstip+"','yyyy-mm-dd') AND eindtijd < To_Date('"+eindtijdstip+"','yyyy-mm-dd') ORDER BY bedrag DESC");
			ResultSet rs = read.executeQuery();
			while (rs.next()) {
				String[] inkomsten;
				int id = rs.getInt("VEILING_ID");
				String naam = rs.getString("VEILING_NAAM");
				int bedrag = rs.getInt("BEDRAG");
				Date eindtijdTemp = rs.getDate("EINDTIJD");
				Calendar eindtijd = Calendar.getInstance();
				eindtijd.setTime(eindtijdTemp);
				
				inkomsten = new String[] {"" +id, naam, "" + bedrag, ProjectTools.CalendarToNiceString(eindtijd) };
				array.add(inkomsten);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return array;
	}

	private Connection connect() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			return DriverManager.getConnection(ConnectionData.HOST,
					ConnectionData.USERNAME, ConnectionData.PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		System.out.println(ProjectTools.CalendarToString(c));
	}
}