import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import librarysystem.Book;

@Testable
public class BookTest {
    private Book book;
	
	@BeforeEach
	void setUp() throws Exception {
		book = new Book("Title","Author","12345",1);
	}

	@AfterEach
	void tearDown() throws Exception {
		book = null;
	}

	//Tests for getters
	@Test
	void testGetTitle() {
		String expected = "Title";
		assertEquals(expected, book.getTitle());
	}

    @Test
	void testGetAuthor() {
		String expected = "Author";
		assertEquals(expected, book.getAuthor());
	}

    @Test
	void testGetISBN() {
		String expected = "12345";
		assertEquals(expected, book.getISBN());
	}

    @Test
	void testGetCopies() {
		int expected = 1;
		assertEquals(expected, book.getCopies());
	}
    //Tests for setters

    //Tests for setTitle
    @Test 
    public void testSetTitle(){
        String expected = "New Title";
		book.setTitle("New Title");
		assertEquals(expected, book.getTitle());
    }

    @Test 
    public void testSetTitleNull(){
        String expected = "Title";
		book.setTitle(null);
		assertEquals(expected, book.getTitle());
    }

    
    //Tests for setAuthor
    @Test 
    public void testSetAuthor(){
        String expected = "New Author";
		book.setAuthor("New Author");
		assertEquals(expected, book.getAuthor());
    }

    @Test 
    public void testSetAuthorNull(){
        String expected = "Author";
		book.setAuthor(null);
		assertEquals(expected, book.getAuthor());
    }


    //Tests for setISBN
    @Test 
    public void testSetISBN(){
        String expected = "54321";
		book.setISBN("54321");
		assertEquals(expected, book.getISBN());
    }

    @Test 
    public void testSetISBNNull(){
        String expected = "12345";
		book.setISBN(null);
		assertEquals(expected, book.getISBN());
    }

    //Tests for setCopies
    @Test 
    public void testSetCopies(){
        int expected = 2;
		book.setCopies(2);
		assertEquals(expected, book.getCopies());
    }

    @Test 
    public void testSetCopiesNegative(){
        int expected = 1;
		book.setCopies(-1);
		assertEquals(expected, book.getCopies());
    }

    //Tests for hashcode
    @Test
	void testHashcode() {
		int expected = 31 * 1 + "12345".hashCode();
		int actual = book.hashCode();
			
		assertEquals(expected, actual );
	}

    //Tests for equals
    @Test
    void testEqaulsSame(){
        assertTrue(book.equals(book));
    }

    @Test
    void testEqaulsNull(){
        assertFalse(book.equals(null));
    }

    @Test
    void testEqualsDiffClass(){
        assertFalse(book.equals(new Object()));
    }

    @Test
    void testEqualsTrue(){
        assertTrue(book.equals(new Book("Title","Author","12345",1)));
    }

    @Test
    void testEqualsFalse1(){
        assertFalse(book.equals(new Book("Title 2","Author","12345",1)));
    }
    
    @Test
    void testEqualsFalse2(){
        assertFalse(book.equals(new Book("Title","Author 2","12345",1)));
    }

    @Test
    void testEqualsFalse3(){
        assertFalse(book.equals(new Book("Title","Author","54321",1)));
    }

    //Tests for toString
    @Test
    void testToString(){
        String expected = "Title: Title\nAuthor: Author\nISBN: 12345\nCopies: 1";
        assertEquals(expected, book.toString());
    }
}
