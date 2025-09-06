package poroje4;

/**
 * An abstract base class for representing a person in the hospital management system.
 * This class provides common properties for all persons, such as name, gender, and birth date.
 *
 * @author Amirhossein Mahdinejad
 * @version 1.1
 * @since 2023-04-12
 */
public abstract class Person {
	private String firstName;
	private String lastName;
	private String gender;
	private String birthDate;
	public long id;

	/**
	 * Constructs a new Person object with the given first and last names.
	 *
	 * @param firstName The person's first name.
	 * @param lastName The person's last name.
	 */
	public Person(String firstName, String lastName) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}

	/**
	 * Constructs a new Person object with the given personal details.
	 *
	 * @param firstName The person's first name.
	 * @param lastName The person's last name.
	 * @param gender The person's gender.
	 * @param birthDate The person's birth date.
	 */
	public Person(String firstName, String lastName,String gender, String birthDate) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setGender(gender);
		this.setBirthDate(birthDate);
	}

	/**
	 * Sets the person's first name.
	 *
	 * @param firstName The first name to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the person's first name.
	 *
	 * @return The person's first name.
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Sets the person's last name.
	 *
	 * @param lastName The last name to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the person's last name.
	 *
	 * @return The person's last name.
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Gets the person's full name (first name and last name).
	 *
	 * @return The person's full name.
	 */
	public String getFullName() {
		return this.firstName + "-" + this.lastName;
	}

	/**
	 * Abstract method for generating a unique ID.
	 */
	public abstract void idGenerator();

	/**
	 * Abstract method for setting a unique ID.
	 *
	 * @param id The ID to set.
	 */
	public abstract void idGenerator(long id);

	/**
	 * Sets the person's birth date.
	 *
	 * @param birthDate The birth date to set.
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Gets the person's birth date.
	 *
	 * @return The person's birth date.
	 */
	public String getBirthDate() {
		return this.birthDate;
	}

	/**
	 * Sets the person's gender.
	 *
	 * @param gender The gender to set.
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the person's gender.
	 *
	 * @return The person's gender.
	 */
	public String getGender() {
		return this.gender;
	}

	/**
	 * Gets a description of the person, including their full name, gender, birth date, and ID.
	 *
	 * @return A string containing the person's description.
	 */
	public String getDescription() {
		return this.getFullName() + " " + this.gender + " " + this.birthDate + " id: " + this.id;
	}

}
