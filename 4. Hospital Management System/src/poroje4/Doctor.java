package poroje4;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a doctor in the hospital management system. This class extends the
 * {@link Person} class and includes information specific to doctors, such as their patients.
 *
 * @author Amirhossein Mahdinejad
 * @version 1.1
 * @since 2023-04-12
 */
public class Doctor extends Person {
	ArrayList<Patient> doctorsPatients = new ArrayList<Patient>();
	static ArrayList<Doctor> doctors = new ArrayList<Doctor>();

	/**
	 * Constructs a new Doctor object with the given first and last names.
	 * A unique ID is automatically generated.
	 *
	 * @param firstName The doctor's first name.
	 * @param lastName The doctor's last name.
	 */
	public Doctor(String firstName, String lastName) {
		super(firstName, lastName);
		this.idGenerator();
		doctors.add(this);
	}

	/**
	 * Constructs a new Doctor object with the given first name, last name, and ID.
	 *
	 * @param firstName The doctor's first name.
	 * @param lastName The doctor's last name.
	 * @param id The doctor's ID.
	 */
	public Doctor(String firstName, String lastName, long id) {
		super(firstName, lastName);
		this.idGenerator(id);
		doctors.add(this);
	}

	/**
	 * Constructs a new Doctor object with the given personal details and ID.
	 *
	 * @param firstName The doctor's first name.
	 * @param lastName The doctor's last name.
	 * @param gender The doctor's gender.
	 * @param birthDate The doctor's birth date.
	 * @param id The doctor's ID.
	 */
	public Doctor(String firstName, String lastName, String gender,
			String birthDate, long id) {
		super(firstName, lastName, gender, birthDate);
		this.idGenerator(id);
		doctors.add(this);
	}

	/**
	 * Adds a patient to this doctor's list of patients.
	 *
	 * @param patient The patient to add.
	 */
	public void addPatient(Patient patient) {
		this.doctorsPatients.add(patient);
	}

	/**
	 * Adds multiple patients to this doctor's list of patients.
	 *
	 * @param patients An ArrayList of patients to add.
	 */
	public void addAllPatients(ArrayList<Patient> patients) {
		this.doctorsPatients.addAll(patients);
	}

	/**
	 * Removes a patient from this doctor's list of patients.
	 *
	 * @param patient The patient to remove.
	 * @return {@code true} if the patient was successfully removed, {@code false} otherwise.
	 */
	public boolean removePatient(Patient patient) {
		return this.doctorsPatients.remove(patient);
	}

	/**
	 * Removes multiple patients from this doctor's list of patients.
	 *
	 * @param patients An ArrayList of patients to remove.
	 */
	public void removeAllPatients(ArrayList<Patient> patients) {
		this.doctorsPatients.removeAll(patients);
	}

	/**
	 * Retrieves all patients assigned to this doctor.
	 *
	 * @return An array of {@link Patient} objects.
	 */
	public Patient[] getAllPatients() {
		Patient[] result = new Patient[this.doctorsPatients.size()];
		int i = 0;
		for (Patient myPatient : this.doctorsPatients) {
			result[i++] = myPatient;
		}
		return result;
	}

	/**
	 * Retrieves all doctors that have been created in the system.
	 *
	 * @return An array of all {@link Doctor} objects.
	 */
	public static Doctor[] getAllDoctors() {
		Doctor[] result = new Doctor[doctors.size()];
		int i = 0;
		for (Doctor myDoctor : doctors) {
			result[i++] = myDoctor;
		}
		return result;
	}

	/**
	 * Generates a unique ID for the doctor.
	 */
	public void idGenerator() {
		long identifier;
		Random rand = new Random();
		identifier = (long)951.e6 + (long)rand.nextInt((int)1.e6);
		this.idGenerator(identifier);
	}

	/**
	 * Sets the doctor's ID, ensuring it is unique. If the provided ID already exists,
	 * a new unique ID is generated.
	 *
	 * @param id The desired ID for the doctor.
	 */
	public void idGenerator(long id) {
		boolean wasCreated = false;
		for (Doctor myDoctor : doctors) {
			if(myDoctor.id == id){
				wasCreated = true;
				this.idGenerator();
			}
		}
		if(!wasCreated)
			this.id = id;
	}

}
