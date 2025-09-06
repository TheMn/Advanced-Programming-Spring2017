package poroje4;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a patient in the hospital management system. This class extends the
 * {@link Person} class and includes information specific to patients, such as their
 * diseases and prescribed medicines.
 *
 * @author Amirhossein Mahdinejad
 * @version 1.1
 * @since 2023-04-12
 */
public class Patient extends Person{
	ArrayList<Disease> patientsDiseases = new ArrayList<Disease>();
	static ArrayList<Patient> patients = new ArrayList<Patient>();

	/**
	 * Constructs a new Patient object with the given first and last names.
	 * A unique ID is automatically generated.
	 *
	 * @param firstName The patient's first name.
	 * @param lastName The patient's last name.
	 */
	public Patient(String firstName, String lastName) {
		super(firstName, lastName);
		this.idGenerator();
		patients.add(this);
	}

	/**
	 * Constructs a new Patient object with the given first name, last name, and ID.
	 *
	 * @param firstName The patient's first name.
	 * @param lastName The patient's last name.
	 * @param id The patient's ID.
	 */
	public Patient(String firstName, String lastName, long id) {
		super(firstName, lastName);
		this.idGenerator(id);
		patients.add(this);
	}

	/**
	 * Constructs a new Patient object with the given personal details and ID.
	 *
	 * @param firstName The patient's first name.
	 * @param lastName The patient's last name.
	 * @param gender The patient's gender.
	 * @param birthDate The patient's birth date.
	 * @param id The patient's ID.
	 */
	public Patient(String firstName, String lastName, String gender,
			String birthDate, long id) {
		super(firstName, lastName, gender, birthDate);
		this.idGenerator(id);
		patients.add(this);
	}

	/**
	 * Retrieves the doctor assigned to this patient.
	 *
	 * @return The {@link Doctor} object assigned to this patient, or {@code null} if no doctor is assigned.
	 */
	public Doctor getDoctor() {
		for (Doctor myDoctor : Doctor.doctors) {
			for (Patient myPatient : myDoctor.doctorsPatients) {
				if(myPatient == this)
					return myDoctor;
			}
		}
		return null;
	}

	/**
	 * Assigns a doctor to this patient.
	 *
	 * @param doctor The doctor to assign.
	 */
	public void setDoctor(Doctor doctor){
		boolean doctorWasInList = false;
		for (Doctor myDoctor : Doctor.doctors) {
			if(myDoctor == doctor){
				doctorWasInList = true;
				myDoctor.addPatient(this);
			}
		}
		if(!doctorWasInList){
			Doctor.doctors.add(doctor);
			doctor.addPatient(this);
		}
	}

	/**
	 * Adds a disease to this patient's list of diseases.
	 *
	 * @param disease The disease to add.
	 */
	public void addDisease(Disease disease) {
		this.patientsDiseases.add(disease);
	}

	/**
	 * Removes a disease from this patient's list of diseases, signifying that it has been cured.
	 *
	 * @param disease The disease to remove.
	 * @return {@code true} if the disease was successfully removed, {@code false} otherwise.
	 */
	public boolean curedDisease(Disease disease) {
		return this.patientsDiseases.remove(disease);
	}

	/**
	 * Retrieves all diseases that this patient has.
	 *
	 * @return An array of {@link Disease} objects.
	 */
	public Disease[] getAllDiseases() {
		Disease[] result = new Disease[this.patientsDiseases.size()];
		int i = 0;
		for(Disease myDisease: this.patientsDiseases){
			result[i++] = myDisease;
		}
		return result;
	}

	/**
	 * Retrieves all medicines prescribed to this patient for their diseases.
	 *
	 * @return An array of {@link Medicine} objects.
	 */
	public Medicine[] getAllMedicines() {
		ArrayList<Medicine> medicines = new ArrayList<Medicine>();
		for (Disease myDisease : this.patientsDiseases) {
			for (Medicine myMedicine : myDisease.diseaseMedicines) {
				medicines.add(myMedicine);
			}
		}
		Medicine[] result = new Medicine[medicines.size()];
		int i = 0;
		for(Medicine myMedicine: medicines){
			result[i++] = myMedicine;
		}
		return result;
	}

	/**
	 * Retrieves all patients that have been created in the system.
	 *
	 * @return An array of all {@link Patient} objects.
	 */
	public static Patient[] getAllPatients() {
		Patient[] result = new Patient[patients.size()];
		int i = 0;
		for (Patient myPatient: patients) {
			result[i++] = myPatient;
		}
		return result;
	}

	/**
	 * Generates a unique ID for the patient.
	 */
	public void idGenerator() {
		long identifier;
		Random rand = new Random();
		identifier = (long)952.e6 + (long)rand.nextInt((int)1.e6);
		this.idGenerator(identifier);
	}

	/**
	 * Sets the patient's ID, ensuring it is unique. If the provided ID already exists,
	 * a new unique ID is generated.
	 *
	 * @param id The desired ID for the patient.
	 */
	public void idGenerator(long id) {
		boolean wasCreated = false;
		for (Patient myPatient : patients) {
			if(myPatient.id == id){
				wasCreated = true;
				this.idGenerator();
			}
		}
		if(!wasCreated)
			this.id = id;
	}

}
