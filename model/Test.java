package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
	

			
	static Connection BDDRom;
	static Connection BDDLit;
	static Connection BDDMit;
	
	static void createConnections() throws SQLException {
		String user = "postgres";
		String pass = "1q2w3e";
		BDDRom = DriverManager.getConnection ("jdbc:postgresql://127.0.0.1:5432/BDDRomantic", user, pass);
		BDDLit = DriverManager.getConnection ("jdbc:postgresql://127.0.0.1:5432/BDDLiteratura", user, pass);
		BDDMit = DriverManager.getConnection ("jdbc:postgresql://127.0.0.1:5432/BDDMitologie", user, pass);
	}
	
	static void addBook(Connection connection, String title, String genre) throws SQLException {
		
		String queryInsert = "INSERT INTO carti"+"(titlu) VALUES ('" + title + "');";
	}
	
	
	
	
	
	
}
