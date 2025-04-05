package librarysystem;

import java.util.HashMap;

import librarysystem.exceptions.LibraryException;

public class Library {

    // Maps the ISBN to the Book
    private HashMap<String, Book> books;

    // Maps the ISBN to the Book
    private HashMap<Integer, Patron> patrons;

    // Maps the ISBN to the number of available copies of each book
    private HashMap<String, Integer> availableCopies;

    // Maps the patron id to a book's ISBN which they currently have checked out (borrowed) 
    private HashMap<Integer, String> borrowedBooks;


    public Library(){
        this.books = new HashMap<String,Book>();
        this.patrons = new HashMap<Integer,Patron>();
        this.availableCopies = new HashMap<String, Integer>();
        this.borrowedBooks = new HashMap<Integer, String>();
    }


    /**
     * Returns the books in the system
     * @return HashMap<String, Book>
     */
    public HashMap<String, Book> getBooks() {
        return books;
    }

    /**
     * Returns the patrons in the system
     * @return HashMap<Integer, Patron>
     */
    public HashMap<Integer, Patron> getPatrons() {
        return patrons;
    }


    /**
     * Returns the available copies of the books in the system
     * @return HashMap<String, Integer>
     */
    public HashMap<String, Integer> getAvailableCopies() {
        return availableCopies;
    }

    /**
     * Returns the patrons with a borrowed book and it's ISBN
     * @return HashMap<Integer, String>
     */
    public HashMap<Integer, String> getBorrowedBooks() {
        return borrowedBooks;
    }


    /**
     * Adds a book into the library system assuming it does not already exist.
     * @param title Title of the book
     * @param author Author of the book
     * @param ISBN ISBN of the book
     * @param copies Initial number of available copies
     * @throws LibraryException Thrown if the book already exists
     */
    public void addBook(String title, String author, String ISBN, int copies) throws LibraryException{
        if (!books.containsKey(ISBN)){
            books.put(ISBN, new Book(title,author,ISBN));
            availableCopies.put(ISBN, copies);
        }
        else{
            throw new LibraryException("Book already exists in the library system.");
        }
    }

    /**
     * Remove a book from the library system
     * @param ISBN The ISBN of the book to be removed
     * @throws LibraryException Thrown if the book does not exist in the system
     */
    public void removeBook(String ISBN) throws LibraryException{
        if (books.containsKey(ISBN)){
            books.remove(ISBN);
            availableCopies.remove(ISBN);
        }
        else{
            throw new LibraryException("Book does not exist in the library system.");
        }
    }

    /**
     * Adds a patron to the system
     * @param name Name of the patron
     * @param age Age of the patron
     */
    public void addPatron(String name,int age){
        Patron newPatron = new Patron(name, age);
        patrons.put(newPatron.getId(), newPatron);
    }

    /**
     * Let's a patron check out (borrow) a book using it's ISBN
     * @param patronId Id of the patron which wants to borrow the book
     * @param ISBN ISBN of the book the patron is trying to borrow
     * @throws LibraryException
     */
    public void checkOutBook(int patronId, String ISBN) throws LibraryException{
        //Check if the patron exists
        if (!patrons.containsKey(patronId)){
            throw new LibraryException("Patron does not exist in the sytem.");
        }
        //Check if the book exists
        else if (!books.containsKey(ISBN)){
            throw new LibraryException("Book with ISBN " + ISBN + " does not exist in the sytem.");
        }
        //Check if the book still has enough copies
        else if (availableCopies.get(ISBN) == 0){
            throw new LibraryException("Book with ISBN " + ISBN + " does not have available copies.");
        }
        // Make sure the patron does not have a book already borrowed
        else if (borrowedBooks.containsKey(patronId)){
            throw new LibraryException("Patron already has a book checked out. It must be returned before another book can be borrowed.");
        }
        else{
            // Check out the book and decrement available copies
            borrowedBooks.put(patronId, ISBN);
            availableCopies.put(ISBN, availableCopies.get(ISBN) - 1 );
        }
    }
}
