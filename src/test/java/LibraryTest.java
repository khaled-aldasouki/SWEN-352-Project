import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import librarysystem.Book;
import librarysystem.Library;
import librarysystem.LibraryDatabase;
import librarysystem.Patron;
import librarysystem.exceptions.LibraryException;

@ExtendWith (MockitoExtension.class)
public class LibraryTest {

    @Mock
    private LibraryDatabase mockDb;

    @InjectMocks
    private Library library;
    
    // Tests for addBook
    @Test
    public void addBookTest1(){
        // Test with no books previously added
        try{
            boolean result = library.addBook("Title", "Author", "12345", 0);
            verify(mockDb).saveBook(new Book("Title", "Author", "12345", 0));
            verify(mockDb).getAllBooks();
            assertTrue(result);
        }
        catch (LibraryException e){
            assertNotSame("Book already exists in the library system.",e.getMessage());
        }
    }
    
    @Test
    public void addBookTest2(){
        // Test with the same book added already
        try{
            
            // Setup a list with a book for the mocked method to return
            Book testBook = new Book("Title", "Author", "12345", 0);
            List<Book> books = Collections.singletonList(testBook);

            when(mockDb.getAllBooks()).thenReturn(books);

            // Make sure that the library will not add the book if it exists in the database already
            library.addBook("Title", "Author", "12345", 0);
            
        }
        catch (LibraryException e){
            // Verify that saveBook is never called but getAllBooks is
            verify(mockDb,never()).saveBook(new Book("Title", "Author", "12345", 0));
            verify(mockDb).getAllBooks();
            assertEquals("Book already exists in the library system.",e.getMessage());
        }
    }

    @Test
    public void addBookTest3(){
        // Test with different books previously added
        try{
            // Setup a list with a book for the mocked method to return
            Book testBook = new Book("Title1", "Author1", "123456", 0);
            List<Book> books = Collections.singletonList(testBook);

            when(mockDb.getAllBooks()).thenReturn(books);

            // Try adding a different book
            boolean result = library.addBook("Title", "Author", "12345", 0);
            verify(mockDb).saveBook(new Book("Title", "Author", "12345", 0));
            verify(mockDb).getAllBooks();
            assertTrue(result);
        }
        catch (LibraryException e){
            assertNotSame("Book already exists in the library system.",e.getMessage());
        }
    }

    // Tests for removeBook
    @Test
    public void removeBookTest1(){
        // Test with book available to delete
        try{
            when(mockDb.deleteBookByISBN(anyString())).thenReturn(true);

            // Try removing the book
            boolean result = library.removeBook("12345");
            verify(mockDb).deleteBookByISBN("12345");
            assertTrue(result);
        }
        catch (LibraryException e){
            assertNotSame("Book does not exist in the library system.",e.getMessage());
        }
    }
    @Test
    public void removeBookTest2(){
        // Test with book available to delete
        try{
           when(mockDb.deleteBookByISBN(anyString())).thenReturn(false);

            // Try removing the book
            library.removeBook("12345");
            
        }
        catch (LibraryException e){
            verify(mockDb).deleteBookByISBN("12345");
            assertEquals("Book does not exist in the library system.",e.getMessage());
        }
    }
    

    // Tests for addPatron
    @Test
    public void addPatronTest1(){
        try{
            when(mockDb.getPatronById(123)).thenReturn(null);
            boolean result = library.addPatron(123,"Name",20);
            verify(mockDb).getPatronById(123);
            verify(mockDb).savePatron(any());
            assertTrue(result);
        }
        catch (LibraryException e){
            assertNotSame("Patron with this id already exists.", e.getMessage());
        }
            
    }
    @Test
    public void addPatronTest2(){
        try{
            Patron testPatron = new Patron(123, "name", 20);
            when(mockDb.getPatronById(123)).thenReturn(testPatron);
            library.addPatron(123,"Name",20);
            
        }
        catch (LibraryException e){
            verify(mockDb).getPatronById(123);
            verify(mockDb,never()).savePatron(any());
            assertEquals("Patron with this id already exists.", e.getMessage());
        }
            
    }
 
    //Tests for removePatron
    @Test
    public void removePatronTest1(){
        // Test with Patron available to delete
        try{
            when(mockDb.deletePatronById(123)).thenReturn(true);

            // Try removing the patron
            boolean result = library.removePatron(123);
            verify(mockDb).deletePatronById(123);
            assertTrue(result);
        }
        catch (LibraryException e){
            assertNotSame("Patron does not exist in the library system.",e.getMessage());
        }
    }
    @Test
    public void removePatronTest2(){
        // Test with book available to delete
        try{
            when(mockDb.deletePatronById(123)).thenReturn(false);

            // Try removing the patron
            library.removePatron(123);
            
        }
        catch (LibraryException e){
            verify(mockDb).deletePatronById(123);
            assertEquals("Patron does not exist in the library system.",e.getMessage());
        }
    }

    // Tests for Check out Book
    @Test
    public void checkOutBookTest1(){
        // Test with patron that doesn't exist
        try{
            when(mockDb.getPatronById(123)).thenReturn(null);
            Book testBook = new Book("Title", "Author", "123456", 1);
            when(mockDb.getBookByISBN("12345")).thenReturn(testBook);

            library.checkOutBook(123, "12345");
            
        }
        catch (LibraryException e){
            verify(mockDb).getPatronById(123);
            verify(mockDb).getBookByISBN("12345");
            assertEquals("The patron ID or book ISBN is incorrect.",e.getMessage());
        }
    }
    @Test
    public void checkOutBookTest2(){
        // Test with book that doesn't exist
        try{
            Patron testPatron =new Patron(123, "name", 20);
            when(mockDb.getPatronById(123)).thenReturn(testPatron);
            when(mockDb.getBookByISBN("12345")).thenReturn(null);
            
            library.checkOutBook(123, "12345");
            
        }
        catch (LibraryException e){
            verify(mockDb).getPatronById(123);
            verify(mockDb).getBookByISBN("12345");
            assertEquals("The patron ID or book ISBN is incorrect.",e.getMessage());
        }
    }

    @Test
    public void checkOutBookTest3(){
        // Test with patron and book that don't exist
        try{
            when(mockDb.getPatronById(123)).thenReturn(null);
            when(mockDb.getBookByISBN("12345")).thenReturn(null);
            
            library.checkOutBook(123, "12345");
            
        }
        catch (LibraryException e){
            verify(mockDb).getPatronById(123);
            verify(mockDb).getBookByISBN("12345");
            assertEquals("The patron ID or book ISBN is incorrect.",e.getMessage());
        }
    }


    @Test
    public void checkOutBookTest4(){
        // Test while patron still has a book borrowed
        try{
            Patron testPatron =new Patron(123, "name", 20);
            Book testBook = new Book("Title", "Author", "123456", 1);
            testPatron.setBorrowedBook(testBook);
            when(mockDb.getPatronById(123)).thenReturn(testPatron);
            when(mockDb.getBookByISBN("12345")).thenReturn(testBook);
            
            library.checkOutBook(123, "12345");
            
        }
        catch (LibraryException e){
            verify(mockDb).getPatronById(123);
            verify(mockDb).getBookByISBN("12345");
            assertEquals("The patron must return their current book before borrowing another one.",e.getMessage());
        }
    }

    @Test
    public void checkOutBookTest5(){
        // Test when no copies are available
        try{
            Patron testPatron =new Patron(123, "name", 20);
            Book testBook = new Book("Title", "Author", "123456", 0);
            when(mockDb.getPatronById(123)).thenReturn(testPatron);
            when(mockDb.getBookByISBN("12345")).thenReturn(testBook);
            
            library.checkOutBook(123, "12345");
            
        }
        catch (LibraryException e){
            verify(mockDb).getPatronById(123);
            verify(mockDb).getBookByISBN("12345");
            assertEquals("The book currently doesn't have any available copies to borrow.",e.getMessage());
        }
    }
    @Test
    public void checkOutBookTest6(){
        // Test when all conditions are met
        try{
            Patron testPatron =new Patron(123, "name", 20);
            Book testBook = new Book("Title", "Author", "123456", 1);
            when(mockDb.getPatronById(123)).thenReturn(testPatron);
            when(mockDb.getBookByISBN("12345")).thenReturn(testBook);
            
            library.checkOutBook(123, "12345");
            verify(mockDb).getPatronById(123);
            verify(mockDb).getBookByISBN("12345");
            assertEquals(testBook, testPatron.getBorrowedBook());
            //assertEquals(0, testBook.getCopies());
        }
        catch (LibraryException e){
            assertNotSame("The patron ID or book ISBN is incorrect.",e.getMessage());
            assertNotSame("The patron must return their current book before borrowing another one.",e.getMessage());
            assertNotSame("The book currently doesn't have any available copies to borrow.",e.getMessage());
        }
    } 

    // Tests for returnBook
    @Test
    public void returnBookTest1(){
        // Test when no book is borrowed
        try{
            Patron testPatron =new Patron(123, "name", 20);
            when(mockDb.getPatronById(123)).thenReturn(testPatron);
            
            library.returnBook(123);
            
        }
        catch (LibraryException e){
            verify(mockDb).getPatronById(123);
            assertEquals("The patron is not currently borrowing a book.",e.getMessage());
        }
    }
    @Test
    public void returnBookTest2(){
        // Test when there is a book to return
        try{
            Patron testPatron =new Patron(123, "name", 20);
            Book testBook = new Book("Title", "Author", "123456", 0);
            testPatron.setBorrowedBook(testBook);
            when(mockDb.getPatronById(123)).thenReturn(testPatron);
            
            library.returnBook(123);
            verify(mockDb).getPatronById(123);
            assertEquals(null, testPatron.getBorrowedBook());
            //assertEquals(1, testBook.getCopies());
        }
        catch (LibraryException e){
            assertNotSame("The patron is not currently borrowing a book.",e.getMessage());
        }
    }
}
