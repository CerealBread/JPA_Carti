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

	public static void main(String[] args) throws SQLException {
		createConnections();
		addBook(BDDRom, "Pride and Prejudicess", "Gen");
		addBook(BDDRom, "Bared to You", "Gen");
		addBook(BDDRom, "The Rosie Project", "Gen");
		addBook(BDDMit, "Odyssey", "Gen");
		addBook(BDDMit, "Circe", "Gen");
		addBook(BDDMit, "The Greek Myths", "Gen");
		addBook(BDDLit, "The Great Gatsby", "Gen");
		addBook(BDDLit, "To Kill a Mochingbird", "Gen");
		addBook(BDDLit, "Brave New World", "Gen");
		
		System.out.println('\n' + "Romantic books");
		selectBooks(BDDRom);

		System.out.println('\n' + "Literature books");
		selectBooks(BDDLit);

		System.out.println('\n' + "Mythology books");
		selectBooks(BDDMit);

		
	}
	
	static void createConnections() throws SQLException {
		String user = "postgres";
		String pass = "1q2w3e";
		BDDRom = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/BDRomantic", user, pass);
		BDDLit = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/BDLiteratura", user, pass);
		BDDMit = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/BDMitologie", user, pass);
	}

	static void addBook(Connection connection, String title, String genre) throws SQLException {

		String queryInsert = "INSERT INTO carti(titlu) VALUES ('" + title + "');";
		Statement statement = connection.createStatement();
		statement.executeUpdate(queryInsert);
	}

	static void selectBooks(Connection connection) throws SQLException {

		String querySelect = "SELECT * FROM CARTI;";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(querySelect);
		
		showResult(resultSet);

	}

	private static void showResult(ResultSet resultSet) throws SQLException {
		int i;
		ResultSetMetaData rsMetaData = resultSet.getMetaData();
		// preia numarul de coloane
		int numCols = rsMetaData.getColumnCount();
		// afiseaza antetele coloanelor
		String header = "";
		for (i = 1; i <= numCols; i++) {
			if (i > 1)
				header = header + ",";
			header = header + rsMetaData.getColumnLabel(i);
		}
		System.out.println(header);
		// afiseaza toate datele din tabel
		boolean more = resultSet.next();
		while (more) {
			// trece de la un rand la altul afisand datele
			String rowData = "";
			for (i = 1; i <= numCols; i++) {
				if (i > 1)
					rowData = rowData + ",";
				rowData = rowData + resultSet.getString(i);
			}
			System.out.println(rowData);
			// incarca urmatoarul rand
			more = resultSet.next();
		}
	}
	

}
