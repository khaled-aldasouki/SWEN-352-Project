package librarysystem;
/**
 * Patrons for libary management system
 */
public class Patron {
    private static int idCounter = 1;
    private int id;
    private String name;
    private int age;
    
    /**
     * Constructor for a Patron
     * @param name Full name of the patron
     * @param age Age of the patron in years
     */
    public Patron(String name,int age){
        this.name = name;
        this.age = age;
        this.id = idCounter;
        idCounter += 1;
    }

    /**
    * Returns the ID of the Patron
    * @return int
    */
    public int getId() {
        return id;
    }

    /**
    * Returns the name of the Patron
    * @return String
    */
    public String getName() {
        return name;
    }

    /**
    * Sets the ID of the Patron
    * @param name The new name of the patron
    */
    public void setName(String name) {
        if (name != null)
            this.name = name;
    }

    /**
    * Returns the Age of the Patron
    * @return int
    */
    public int getAge() {
        return age;
    }

    /**
    * Sets the Age of the Patron
    * @param age The new age of the patron
    */
    public void setAge(int age) {
        if (age != 0)
            this.age = age;
    }

    @Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Patron other = (Patron) obj;
		if (other.id != this.id)
			return false;
		return true;
	}

    /**
	* Returns a String describing the details of the Patron
	* @return String
	*/
	public String toString() {
		return "ID: " + id + "\nName: " + name + "\nAge: " + age;
	}
}
