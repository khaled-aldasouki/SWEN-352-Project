package librarysystem;

public interface LibraryDatabase {
    
    /**
     * Searches for the book based on the passed ISBN number
     * @param ISBN of the book
     * @return Book if found, null otherwise
     */
    public Book getBookByISBN(String ISBN);

    /**
     * Searches for the patron with the passed ID number
     * @param id of the patron
     * @return Patron if found, null otherwise
     */
    public Patron getPatronById(int id);

    /**
     * Deletes the Book with the provided ISBN
     * @param ISBN of the book
     * @return true if deleted successfully and false otherwise
     */
    public boolean deleteBookByISBN(String ISBN);
    
    /**
     * Deletes the Patron with the provided ID number
     * @param id of the patron
     * @return true if deleted successfully and false otherwise
     */
    public boolean deletePatronById(int id); 
}
