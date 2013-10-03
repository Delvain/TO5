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

import plarktmaatsDomein.Categorie;
import plarktmaatsDomein.Gebruiker;
import plarktmaatsDomein.Veiling;

public class CategorieDAOImpl implements PlarktmaatsDAOInterface<Categorie> {

	@Override
	public void create(Categorie c) { 
		String naam = c.getNaam();
				
		String query = "INSERT INTO "+ConnectionData.DATABASE+".\"CATEGORIEEN\" VALUES ('"+naam+"')";
		Connection con = connect();
		try {
			con.createStatement().execute(query);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Categorie read(String pk) {
		Connection con = connect();
		try {
			PreparedStatement read = con.prepareStatement("SELECT * FROM "+ConnectionData.DATABASE+".\"CATEGORIEEN\" WHERE NAAM = ?");
			read.setString(1, pk);
			ResultSet rs = read.executeQuery();
			while(rs.next()) {
				String naam = rs.getString("NAAM");
				return new Categorie(naam);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Categorie> getAll() {
		ArrayList<Categorie> array = new ArrayList<Categorie>();
		Connection con = connect();
		try {
			PreparedStatement read = con.prepareStatement("SELECT * FROM "+ConnectionData.DATABASE+".\"CATEGORIEEN\" ");
			ResultSet rs = read.executeQuery();
			while(rs.next()) {
				String naam = rs.getString("NAAM");
				array.add(new Categorie(naam));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return array;
	}

	@Override
	public void update(String pk, Categorie c) {
		String naam = c.getNaam();
				
		String query = 	"UPDATE \"STUD1630460\".\"CATEGORIEEN\" ";
		query +=		"SET naam='"+naam+"' ";
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
			PreparedStatement delete = con.prepareStatement("DELETE FROM "+ConnectionData.DATABASE+".\"CATEGORIEEN\" WHERE naam = ?");
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