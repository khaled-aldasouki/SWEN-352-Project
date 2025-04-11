import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import librarysystem.Patron;

@Testable
public class PatronTest {
     private Patron patron;
	
	@BeforeEach
	void setUp() throws Exception {
		patron = new Patron(1,"Patron", 20);


	}

	@AfterEach
	void tearDown() throws Exception {
		patron = null;
	}
    
	//Tests for getters

    @Test
	void testGetId() {
		int expected = 1;
		assertEquals(expected, patron.getId());
	}

	@Test
	void testGetName() {
		String expected = "Patron";
		assertEquals(expected, patron.getName());
	}

    @Test
	void testGetAge() {
		int expected = 20;
		assertEquals(expected, patron.getAge());
	}

    

    //Tests for setters

    //Tests for setName
    @Test 
    public void testSetName(){
        String expected = "New Name";
		patron.setName("New Name");
		assertEquals(expected, patron.getName());
    }

    @Test 
    public void testSetNameNull(){
        String expected = "Patron";
		patron.setName(null);
		assertEquals(expected, patron.getName());
    }

    //Tests for setAge
    @Test 
    public void testSetAge(){
        int expected = 21;
		patron.setAge(21);;
		assertEquals(expected, patron.getAge());
    }

    @Test 
    public void testSetAge0(){
        int expected = 20;
		patron.setAge(0);;
		assertEquals(expected, patron.getAge());
    }

    @Test 
    public void testSetAgeNegative(){
        int expected = 20;
		patron.setAge(-1);
		assertEquals(expected, patron.getAge());
    }

    //Tests for setId
    @Test 
    public void testSetId(){
        int expected = 2;
		patron.setId(2);;
		assertEquals(expected, patron.getId());
    }

    @Test 
    public void testSetId0(){
        int expected = 1;
		patron.setId(0);;
		assertEquals(expected, patron.getId());
    }

    @Test 
    public void testSetIdNegative(){
        int expected = 1;
		patron.setId(-1);
		assertEquals(expected, patron.getId());
    }

    //Tests for hashcode
    @Test
	void testHashcode() {
		int expected = 31 * 1 + patron.getId();
		int actual = patron.hashCode();
		assertEquals(expected, actual );
	}

    //Tests for equals
    @Test
    void testEqaulsSame(){
        assertTrue(patron.equals(patron));
    }

    @Test
    void testEqaulsNull(){
        assertFalse(patron.equals(null));
    }

    @Test
    void testEqualsDiffClass(){
        assertFalse(patron.equals(new Object()));
    }

    @Test
    void testEqualsTrue(){
        assertTrue(patron.equals(new Patron(1,"Patron", 20)));
    }

    @Test
    void testEqualsFalse1(){
        assertFalse(patron.equals(new Patron(2,"Patron", 20)));
    }
    
    @Test
    void testEqualsFalse2(){
        assertFalse(patron.equals(new Patron(1,"Patron 2", 20)));
    }

    @Test
    void testEqualsFalse3(){
        assertFalse(patron.equals(new Patron(1,"Patron", 21)));
    }


    //Tests for toString
    @Test
    void testToString(){
        String expected = "ID: " + patron.getId() + "\nName: Patron\nAge: 20";
        assertEquals(expected, patron.toString());
    }
}
