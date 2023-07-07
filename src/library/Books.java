package library;

public class Books extends CatalogItem {
	private String author;
	private String genre;

	public Books(String ISBN, String title, int publicationDate, int pageTotal, String author, String genre) {
		super(ISBN, title, publicationDate, pageTotal);
		this.author = author;
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public String getGenre() {
		return genre;
	}

	public String toString() {
		return "Book{" + "isbn='" + this.getISBN() + '\'' + ", title='" + this.getTitle() + '\'' + ", publicationDate="
				+ this.getPublicationDate() + ", \", page total=\" + this.getPageTotal() + \", author='"
				+ this.getAuthor() + '\'' + ", genre='" + this.getGenre()
				+ '\'' + '}';
	}
}