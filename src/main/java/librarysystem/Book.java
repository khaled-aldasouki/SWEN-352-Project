package librarysystem;

/**
 * Books in the library management system
 */
public class Book {

    private String title;
    private String author;
    private String ISBN;

    /**
	 * Constructor for a book
	 * @param title Title of the book
	 * @param author Author of the book
	 * @param ISBN ISBN of the book
	 */
	public Book(String title, String author, String ISBN){
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
    }


    /**
    * Returns the title of the book
    * @return String
    */
    public String getTitle() {
        return title;
    }


	/**
    * Sets the title of the book
    * @param title The new title of the Book
    */
	public void setTitle(String title){
		if (title != null)
			this.title = title;
	}


	/**
    * Returns the author of the book
    * @return String
    */
    public String getAuthor() {
        return author;
    }


	/**
    * Sets the author of the book
    * @param author The new author of the Book
    */
	public void setAuthor(String author){
		if (author != null)
			this.author = author;
	}


	/**
    * Returns the ISBN of the book
    * @return String
    */
    public String getISBN() {
        return ISBN;
    }


	/**
    * Sets the ISBN of the book
    * @param ISBN The new ISBN of the Book
    */
	public void setISBN(String ISBN){
		if (ISBN != null)
			this.ISBN = ISBN;
	}


    @Override
	public int hashCode() {
		return ISBN.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Book other = (Book) obj;
		if (other.ISBN != this.ISBN)
			return false;
		return true;
	}

    /**
	* Returns a String describing the book details
	* @return String
	*/
	public String toString() {
		return "Title: " + title + "\nAuthor: " + author +"\nISBN: " + ISBN ;
	}
}
