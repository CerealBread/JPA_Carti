package model;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		LiteratureBook.createConnection();
		MythologyBook.createConnection();
		RomanticBook.createConnection();
		menu();

		scanner.close();
	}

	static void menu() throws SQLException {
		int menuItem;

		do {
			System.out.println("Press 1 for adding a book");
			System.out.println("Press 2 for displaying the books of a genre");
			System.out.println("Press 3 for displaying all books");
			System.out.println("Press anything else for exiting");
			menuItem = scanner.nextInt();
			
			switch (menuItem) {
			case 1:
				bookCreationMenu();
				break;
			case 2:
				displaySpecificBooks();
				break;
			case 3:
				displayAllBooks();
				break;
			default:
				menuItem = 10;
			}
		} while (menuItem != 10);
	}

	static void displaySpecificBooks() throws SQLException {
		int genre;
		System.out.println("Choose the genre of the book");
		System.out.println("1 for Romantic");
		System.out.println("2 for Mythology");
		System.out.println("3 for Literature");
		
		genre = scanner.nextInt();
		switch (genre) {
		case 1:
			RomanticBook.selectBooks();
			break;
		case 2:
			MythologyBook.selectBooks();
			break;
		case 3:
			LiteratureBook.selectBooks();
			break;
		}
	}

	static void displayAllBooks() throws SQLException {
		System.out.println('\n' + "------ The romantic Books: ------");
		RomanticBook.selectBooks();
		System.out.println('\n' + "------ The Mythology Books: ------");
		MythologyBook.selectBooks();
		System.out.println('\n' + "------ The Literature Books: ------");
		LiteratureBook.selectBooks();
	}

	static void bookCreationMenu() throws SQLException {
		// in this method, we promt the choosing of the genre and creation of the book
		System.out.println("Enter the title of the Book");
		String title = scanner.next();
		System.out.println("Choose the genre of the book");
		System.out.println("1 for Romantic");
		System.out.println("2 for Mythology");
		System.out.println("3 for Literature");
		int genre = scanner.nextInt();

		switch (genre) {
		case 1:
			RomanticBook romanticBook = new RomanticBook(title);
			break;
		case 2:
			MythologyBook mythologyBook = new MythologyBook(title);
			break;
		case 3:
			LiteratureBook literatureBook = new LiteratureBook(title);
			break;
		}
	}
}
