package library;

class Magazines extends CatalogItem {
	private Periodicity periodicity;

	enum Periodicity {
		WEEKLY, MONTHLY, SEMIANNUAL
	};

	public Magazines(String ISBN, String title, int publicationDate, int pageTotal, String periodicity) {
		super(ISBN, title, publicationDate, pageTotal);
		this.periodicity = Periodicity.valueOf(periodicity);
	}

	public Periodicity getPeriodicity() {
		return periodicity;
	}

	public String toString() {
		return "Magazine{" + " isbn='" + this.getISBN() + '\'' + ", title='" + this.getTitle() + '\''
				+ ", publicationDate=" + this.getPublicationDate() + 
				", pageTotal=" + this.getPageTotal() +
				", periodicity=" + this.getPeriodicity() +
				'}';
	}
}