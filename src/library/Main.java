package library;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
	public static <Item> void main(String[] args) {

		Catalog catalog = new Catalog();
		try (Scanner input = new Scanner(System.in)) {
			while (true) {
				System.out.println("Choose an option:");
				System.out.println("1. Add book");
				System.out.println("2. Add magazine");
				System.out.println("3. Remove item by ISBN code");
				System.out.println("4. Search by ISBN");
				System.out.println("5. Search by publication date");
				System.out.println("6. Search by author");
				System.out.println("7. Upload item list from disk");
				System.out.println("8. Save item list to disk");

				int choice = input.nextInt();
				input.nextLine();

				switch (choice) {
				case 1:
					System.out.println("Enter book details:");
					System.out.print("ISBN: ");
					String isbn = input.nextLine();
					System.out.print("Title: ");
					String title = input.nextLine();
					System.out.print("Publication Date: ");
					int date = input.nextInt();
					input.nextLine();
					System.out.print("Page total: ");
					int pages = input.nextInt();
					input.nextLine();
					System.out.print("Author: ");
					String author = input.nextLine();
					System.out.print("Genre: ");
					String genre = input.nextLine();

					Books book = new Books(isbn, title, date, pages, author, genre);
					catalog.add(book);
					break;

				case 2:
					System.out.println("Enter magazine details:");
					System.out.print("ISBN: ");
					String isbnMagazine = input.nextLine();
					System.out.print("Title: ");
					String titleMagazine = input.nextLine();
					System.out.print("Publication Date: ");
					int dateMagazine = input.nextInt();
					input.nextLine();
					System.out.print("PageTotal: ");
					int pagesMagazine = input.nextInt();
					System.out.print("Periodicity (Weekly, Monthly or Semiannual): ");
					String periodicity = input.nextLine().toUpperCase();

					Magazines magazine = new Magazines(isbnMagazine, titleMagazine, dateMagazine, pagesMagazine,
							periodicity);
					catalog.add(magazine);
					break;

				case 3:
					System.out.print("Enter the ISBN code of the item to be deleted: ");
					String isbnToRemove = input.nextLine();
					catalog.remove(isbnToRemove);
					break;

				case 4:
					System.out.print("Enter the ISBN code of the item to be searched: ");
					String isbnToFind = input.nextLine();
					Optional<CatalogItem> resultISBN = catalog.searchByISBN(isbnToFind);
					if (resultISBN.isEmpty()) {
						System.out.println("Item not found!");
					} else {
						System.out.println(resultISBN);
					}
					break;

				case 5:
					System.out.print("Enter the publication date: ");
					int dateAlfa = input.nextInt();
					input.nextLine();

					List<CatalogItem> itemsByDate = catalog.searchByPublicationDate(dateAlfa);
					if (itemsByDate.size() > 0) {
						System.out.println("Items published in this year " + dateAlfa + ":");
						for (CatalogItem e : itemsByDate) {
							System.out.println(e.toString());
						}
					} else {
						System.out.println("No item found published in this year " + dateAlfa);
					}
					break;

				case 6:
					System.out.print("Enter author: ");
					String authorAlfa = input.nextLine();

					List<Books> itemsByAuthor = catalog.searchByAuthor(authorAlfa);
					if (itemsByAuthor.size() > 0) {
						System.out.println("Author's items " + authorAlfa + ":");
						for (CatalogItem e : itemsByAuthor) {
							System.out.println(e.toString());
						}
					} else {
						System.out.println("No item found by the author " + authorAlfa);
					}
					break;

				case 7:
					try {
						System.out.print("Enter the file path: ");
						String filePath = input.nextLine();

						catalog.uploadFromDisk(filePath);
						System.out.println("Upload complete!");
					} catch (IOException e) {
						System.out.println("Error while uploading the file: " + e.getMessage());
					}
					break;
				case 8:
					try {
						System.out.print("Enter the file path: ");
						String filePath = input.nextLine();

						catalog.saveToDisk(filePath);
						System.out.println("Saving complet!");
					} catch (IOException e) {
						System.out.println("Error while saving the file: " + e.getMessage());
					}
					break;

				default:
					System.out.println("Invalid choice!");
					break;
				}
			}
		}
	}
}
