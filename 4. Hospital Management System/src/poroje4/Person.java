package poroje4;

/**
 * <h1> Person </h1>
 * Declaration of Person class
 */
public abstract class Person {
	private String firstName;
	private String lastName;
	private String gender;
	private String birthDate;
	public long id;
	
	/**
	 * The Person contractor
	 * @param firstName the first name
	 * @param lastName the last name
	 */
	public Person(String firstName, String lastName) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}
	
	/**
	 * The Person contractor
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param gender the gender
	 * @param birthDate the birth date
	 */
	public Person(String firstName, String lastName,String gender, String birthDate) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setGender(gender);
		this.setBirthDate(birthDate);
	}
	
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * This method will return FullName of Person
	 * @return String the FullName
	 */
	public String getFullName() {
		return this.firstName + "-" + this.lastName;
	}
	
	/**
	 * idGenerator abstract classes 
	 */
	public abstract void idGenerator();
	public abstract void idGenerator(long id);
	
	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	/**
	 * @return the birthDate
	 */
	public String getBirthDate() {
		return this.birthDate;
	}
	
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/**
	 * @return the gender
	 */
	public String getGender() {
		return this.gender;
	}
	
	/**
	 * This method will return some description about Person
	 * @return String description
	 */
	public String getDescription() {
		return this.getFullName() + " " + this.gender + " " + this.birthDate + " id: " + this.id;
	}
	
}
