package library;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

public class Catalog {
	private List<CatalogItem> itemList;

	public Catalog() {
		itemList = new ArrayList<>();
	}

	public void add(CatalogItem item) {
		if (item instanceof Books) {
			Books book = (Books) item;
			boolean isbnAvailable = itemList.stream().filter(e -> e instanceof Books).map(e -> (Books) e)
					.anyMatch(e -> e.getISBN().equals(book.getISBN()));
			if (!isbnAvailable) {
				itemList.add(book);
				System.out.println("Book successfully added!");
			} else {
				System.out.println("Error: the ISBN entered is already in the list!");
			}
		} else if (item instanceof Magazines) {
			Magazines magazine = (Magazines) item;
			boolean isbnAvailable = itemList.stream().filter(e -> e instanceof Magazines).map(e -> (Magazines) e)
					.anyMatch(e -> e.getISBN().equals(magazine.getISBN()));
			if (!isbnAvailable) {
				itemList.add(magazine);
				System.out.println("Magazine successfully added!");
			} else {
				System.out.println("Error: the ISBN entered is already in the list!");
			}
		}
	}

	public void remove(String ISBN) {
		itemList.removeIf(item -> item.getISBN().equals(ISBN));
	}

	public Optional<CatalogItem> searchByISBN(String ISBN) {
		return itemList.stream().filter(item -> item.getISBN().equals(ISBN)).findFirst();
	}

	public List<CatalogItem> searchByPublicationDate(int date) {
		return itemList.stream().filter(item -> item.getPublicationDate() == date).collect(Collectors.toList());
	}

	public List<Books> searchByAuthor(String author) {
		return itemList.stream().filter(item -> item instanceof Books).map(item -> (Books) item)
				.filter(book -> book.getAuthor().equals(author)).collect(Collectors.toList());
	}

	public void saveToDisk(String fileName) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();

		for (CatalogItem e : itemList) {
			if (e instanceof Books) {
				Books book = (Books) e;
				stringBuilder.append(book.getISBN()).append("@").append(book.getTitle()).append("@")
						.append(book.getPublicationDate()).append("@").append(book.getPageTotal()).append("@")
						.append(book.getAuthor()).append("@")
						.append(book.getGenre()).append("#");
			} else if (e instanceof Magazines) {
				Magazines magazine = (Magazines) e;
				stringBuilder.append(magazine.getISBN()).append("@").append(magazine.getTitle()).append("@")
						.append(magazine.getPublicationDate()).append("@").append(magazine.getPageTotal())
						.append(magazine.getPeriodicity())
						.append("#");
			}
		}

		File file = new File(fileName);
		FileUtils.writeStringToFile(file, stringBuilder.toString(), "UTF-8");
	}

	public void uploadFromDisk(String fileName) throws IOException {
		File file = new File(fileName);

		if (!file.exists()) {
			return;
		}
		String fileContent = FileUtils.readFileToString(file, "UTF-8");

		itemList.clear();

		String[] items = fileContent.split("#");
		for (String item : items) {
			String[] data = item.split("@");
			if (data.length == 6) {
				Books l = new Books(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), data[4],
						data[5]);
				itemList.add(l);
			} else if (data.length == 5) {
				Magazines magazine = new Magazines(data[0], data[1], Integer.parseInt(data[2]),
						Integer.parseInt(data[3]),
						data[4]);
				itemList.add(magazine);
			}
		}
	}

}
