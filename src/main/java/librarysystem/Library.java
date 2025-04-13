package librarysystem;

import librarysystem.exceptions.LibraryException;

public class Library {

    private final LibraryDatabase database;

    public Library(LibraryDatabase database){
        this.database = database;
    }


    /**
     * Adds a book into the library system assuming it does not already exist.
     * @param title Title of the book
     * @param author Author of the book
     * @param ISBN ISBN of the book
     * @param copies Initial number of available copies
     * @throws LibraryException Thrown if the book already exists
     * @return boolean 
     */
    public boolean addBook(String title, String author, String ISBN, int copies) throws LibraryException{

        // Create the book object and check if an equal one currently exists in the database
        Book newBook = new Book(title, author, ISBN, copies);
        for (Book book:database.getAllBooks()){
            // If the book does exist, then throw an exception
            if (book.equals(newBook)){
                throw new LibraryException("Book already exists in the library system.");
            }
        }
        // If the book does not exist, then save it
        database.saveBook(newBook);
        return true;
    }

    /**
     * Remove a book from the library system
     * @param ISBN The ISBN of the book to be removed
     * @throws LibraryException Thrown if the book does not exist in the system
     * @return boolean
     */
    public boolean removeBook(String ISBN) throws LibraryException{
        // attempt to delete the book and throw an error if it does not exist
        if (!database.deleteBookByISBN(ISBN)){
            throw new LibraryException("Book does not exist in the library system.");
        }
        return true;
    }

    /**
     * Adds a patron to the system
     * @param id 
     * @param name 
     * @param age 
     * @throws LibraryException 
     */
    public boolean addPatron(int id,String name,int age) throws LibraryException{
        if (database.getPatronById(id) == null){
            database.savePatron(new Patron(id,name, age));
            return true;
        }
        else{
            throw new LibraryException("Patron with this id already exists.");
        }
        
    }


    /**
     * Remove a patron from the library system
     * @param patronId
     * @throws LibraryException thrown if the patron does not exist
     */
    public boolean removePatron(int patronId) throws LibraryException{
        // attempt to delete the patron and throw an error if it does not exist
        if (!database.deletePatronById(patronId)){
            throw new LibraryException("Patron does not exist in the library system.");
        }
        return true;
    }


    /**
     * Register the book checkout for a patron. The book and patron must both exist, the book must have a copy available, and the patron must 
     * not be borrowing a book already.
     * @param patronId
     * @param ISBN
     * @throws LibraryException thrown if any of the conditions have not been met
     */
    public void checkOutBook(int patronId, String ISBN) throws LibraryException{

        // Find patron and Book objects
        Patron patron = database.getPatronById(patronId);
        Book book = database.getBookByISBN(ISBN);

        // Check that both the book and patron exist
        if (patron != null && book != null){
            // Check that the patron is not currenlty borrowing a book
            if (patron.getBorrowedBook() == null){
                // Check that the book has available copies for borrowing
                if (book.getCopies() > 0){
                    // Confirm that the patron is now borrowing the book and remove a copy from the available copies of the book
                    patron.setBorrowedBook(book);
                    book.setCopies(book.getCopies() - 1);
                }
                else throw new LibraryException("The book currently doesn't have any available copies to borrow.");
            }
            else{
                throw new LibraryException("The patron must return their current book before borrowing another one.");
            }
        }
        else{
            throw new LibraryException("The patron ID or book ISBN is incorrect.");
        }
    }

    /**
     * Return the book borrowed by the patron
     * @param patronId
     * @throws LibraryException thrown if the patron is not borrowing a book
     */
    public void returnBook (int patronId) throws LibraryException{
        // Check that the patron is borrowing a book
        Patron patron = database.getPatronById(patronId);
        Book book = patron.getBorrowedBook();
        if (book != null){
            // return the book and add a copy 
            patron.setBorrowedBook(null);
            book.setCopies(book.getCopies() + 1);
        }
        else{
            throw new LibraryException("The patron is not currently borrowing a book.");
        }
    }

}
