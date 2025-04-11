package librarysystem;
/**
 * Patrons for libary management system
 */
public class Patron {
    private int id;
    private String name;
    private int age;
    
    /**
     * Constructor for a Patron
     * @param id ID of the patron
     * @param name Full name of the patron
     * @param age Age of the patron in years
     */
    public Patron(int id, String name,int age){
        this.name = name;
        this.age = age;
        this.id = id;
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
        if (age > 0)
            this.age = age;
    }
    /**
    * Sets the id of the Patron
    * @param id The new id of the patron
    */
    public void setId(int id) {
        if (id > 0)
            this.id = id;
    }

    @Override
	public int hashCode() {
        final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
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
        if (!other.name.equals(this.name))
			return false;
        if (other.age != this.age)
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
