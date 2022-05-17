package poroje4;

import java.util.ArrayList;
import java.util.Random;

/**
 * <h1> Patient </h1>
 * Declaration of Patient class
 */
public class Patient extends Person{
	ArrayList<Disease> patientsDiseases = new ArrayList<Disease>();
	static ArrayList<Patient> patients = new ArrayList<Patient>();
	
	/**
	 * The Patient contractor
	 * @param firstName the first name
	 * @param lastName the last name
	 */
	public Patient(String firstName, String lastName) {
		super(firstName, lastName);
		this.idGenerator();
		patients.add(this);
	}
	
	/**
	 * The Patient contractor
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param id the identifier
	 */
	public Patient(String firstName, String lastName, long id) {
		super(firstName, lastName);
		this.idGenerator(id);
		patients.add(this);
	}
	
	/**
	 * The Patient contractor
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param gender the gender
	 * @param birthDate the birth date
	 * @param id the identifier
	 */
	public Patient(String firstName, String lastName, String gender,
			String birthDate, long id) {
		super(firstName, lastName, gender, birthDate);
		this.idGenerator(id);
		patients.add(this);
	}
	
	/**
	 * This method will return doctor
	 * @return doctor the patients Doctor
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
	 * This method will set doctor
	 * @param doctor to set
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
	 * This method will add a disease to patients diseases
	 * @param disease to add
	 */
	public void addDisease(Disease disease) {
		this.patientsDiseases.add(disease);
	}
	
	/**
	 * This method will remove a cured disease from patients diseases
	 * @param disease to remove
	 * @return boolean true if it was in array
	 */
	public boolean curedDisease(Disease disease) {
		return this.patientsDiseases.remove(disease);
	}

	/**
	 * This method will return an array contains all patients diseases
	 * @return an array of diseases
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
	 * This method will return an array contains all patients medicines
	 * @return an array of medicines
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
	 * This method will return an array contains all Patients
	 * @return an array of patients
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
	 * This method will set an unique id to a Patient
	 */
	public void idGenerator() {
		long identifier;
		Random rand = new Random();
		identifier = (long)952.e6 + (long)rand.nextInt((int)1.e6);
		this.idGenerator(identifier);
	}
	
	/**
	 * This method will set id to Patient if it was not created before
	 * @param id the identifier of patient
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
