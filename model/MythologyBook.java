package model;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MythologyBook{
	String title;
	static Connection connection;
	
	MythologyBook(String title) throws SQLException {
		String queryInsert = "INSERT INTO carti(titlu) VALUES ('" + title + "');";
		Statement statement = connection.createStatement();
		statement.executeUpdate(queryInsert);
		
		System.out.println("### Mythology Book added ###");
	}

	static void createConnection() throws SQLException {
		connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/BDMitologie", "postgres", "1q2w3e");
	}
	
	static void selectBooks() throws SQLException {

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
