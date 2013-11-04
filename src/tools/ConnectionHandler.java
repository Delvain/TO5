package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import plarktmaatsDAO.ConnectionData;

public abstract class ConnectionHandler {
	public static Connection connect() { //Nieuwe connectie maken :)
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			return DriverManager.getConnection(ConnectionData.HOST, ConnectionData.USERNAME, ConnectionData.PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
