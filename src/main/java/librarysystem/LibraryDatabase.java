package librarysystem;

import java.util.List;

public interface LibraryDatabase {
    
    
    /**
     * Saves a new book into the database
     * @param newBook the book being added
     */
    public void saveBook(Book newBook);


    /**
     * Searches for the book based on the passed ISBN number
     * @param ISBN of the book
     * @return Book if found, null otherwise
     */
    public Book getBookByISBN(String ISBN);


    /**
     * Fetches a list of all books
     * @return List<Book>
     */
    public List<Book> getAllBooks();


    /**
     * Deletes the Book with the provided ISBN
     * @param ISBN of the book
     * @return true if deleted successfully and false otherwise
     */
    public boolean deleteBookByISBN(String ISBN);


    /**
     * Saves a new patron into the database
     * @param newPatron the patron being added
     */
    public void savePatron(Patron newPatron);


    /**
     * Searches for the patron with the passed ID number
     * @param id of the patron
     * @return Patron if found, null otherwise
     */
    public Patron getPatronById(int id);


    /**
     * Deletes the Patron with the provided ID number
     * @param id of the patron
     * @return true if deleted successfully and false otherwise
     */
    public boolean deletePatronById(int id); 

    
    
    

   


    
}
