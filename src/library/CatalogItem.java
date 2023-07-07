package library;

public abstract class CatalogItem {
	private String ISBN;
	private String title;
	private int publicationDate;
	private int pageTotal;

	public CatalogItem(String ISBN, String title, int publicationDate, int pageTotal) {
		this.ISBN = ISBN;
		this.title = title;
		this.publicationDate = publicationDate;
		this.pageTotal = pageTotal;
	}

	public String getISBN() {
		return ISBN;
	}

	public String getTitle() {
		return title;
	}

	public int getPublicationDate() {
		return publicationDate;
	}

	public int getPageTotal() {
		return pageTotal;
	}
}