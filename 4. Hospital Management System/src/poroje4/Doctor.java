package poroje4;

import java.util.ArrayList;
import java.util.Random;

/**
 * <h1> Doctor </h1>
 * Declaration of Doctor class
 */
public class Doctor extends Person {
	ArrayList<Patient> doctorsPatients = new ArrayList<Patient>();
	static ArrayList<Doctor> doctors = new ArrayList<Doctor>();
	
	/**
	 * The Doctor contractor
	 * @param firstName the first name
	 * @param lastName the last name
	 */
	public Doctor(String firstName, String lastName) {
		super(firstName, lastName);
		this.idGenerator();
		doctors.add(this);
	}

	/**
	 * The Doctor contractor
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param id the identifier
	 */
	public Doctor(String firstName, String lastName, long id) {
		super(firstName, lastName);
		this.idGenerator(id);
		doctors.add(this);
	}

	/**
	 * The Doctor contractor
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param gender the gender
	 * @param birthDate the birth date
	 * @param id the identifier
	 */
	public Doctor(String firstName, String lastName, String gender,
			String birthDate, long id) {
		super(firstName, lastName, gender, birthDate);
		this.idGenerator(id);
		doctors.add(this);
	}
	
	/**
	 * This method will add a Patient to Doctor patients
	 * @param patient to add
	 */
	public void addPatient(Patient patient) {
		this.doctorsPatients.add(patient);
	}
	
	/**
	 * This method will add a number of patients to Doctor patients
	 * @param patients to add
	 */
	public void addAllPatients(ArrayList<Patient> patients) {
		this.doctorsPatients.addAll(patients);
	}
	
	/**
	 * This method will remove a patient from Doctor patients
	 * @param patient we want to remove
	 * @return boolean true if it was in array
	 */
	public boolean removePatient(Patient patient) {
		return this.doctorsPatients.remove(patient);
	}
	
	/**
	 * This method will remove a number of patients from Doctor patients
	 * @param patients to remove
	 */
	public void removeAllPatients(ArrayList<Patient> patients) {
		this.doctorsPatients.removeAll(patients);
	}
	
	/**
	 * This method will return an array contains all Doctor patients
	 * @return a Patient array
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
	 * This method will return an array contains all Doctors
	 * @return a Doctor array
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
	 * This method will set an unique id to a Doctor
	 */
	public void idGenerator() {
		long identifier;
		Random rand = new Random();
		identifier = (long)951.e6 + (long)rand.nextInt((int)1.e6);
		this.idGenerator(identifier);
	}
	
	/**
	 * This method will set id to Doctor if it was not created before
	 * @param id the identifier of doctor
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
