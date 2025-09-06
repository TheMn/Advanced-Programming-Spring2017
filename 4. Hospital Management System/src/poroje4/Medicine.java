package poroje4;

import java.util.ArrayList;

/**
 * Represents a medicine in the hospital management system. This class stores
 * information about a medicine, including its name.
 *
 * @author Amirhossein Mahdinejad
 * @version 1.1
 * @since 2023-04-12
 */
public class Medicine {
	private String name;
	static ArrayList<Medicine> medicines = new ArrayList<Medicine>();

	/**
	 * Constructs a new Medicine object with the given name.
	 *
	 * @param name The name of the medicine.
	 */
	public Medicine(String name) {
		this.setName(name);
		medicines.add(this);
	}

	/**
	 * Gets the name of the medicine.
	 *
	 * @return The name of the medicine.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the medicine.
	 *
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves all diseases that this medicine can be used to treat.
	 *
	 * @return An array of {@link Disease} objects.
	 */
	public Disease[] getAllDiseases() {
		Disease[] result = new Disease[Disease.diseases.size()];
		int i = 0;
		for (Disease myDisease : Disease.diseases) {
			result[i++] = myDisease;
		}
		return result;
	}

	/**
	 * Retrieves all patients who are currently prescribed this medicine.
	 *
	 * @return An array of {@link Patient} objects.
	 */
	public Patient[] getAllPatients() {
		int i = 0;
		ArrayList<Patient> patients = new ArrayList<Patient>();
		for (Patient myPatient : Patient.patients) {
			Medicine[] patientMedicines = myPatient.getAllMedicines();
			for (Medicine myMedicine : patientMedicines) {
				if(this == myMedicine)
					patients.add(myPatient);
			}
		}
		Patient[] result = new Patient[patients.size()];
		for (Patient myPatient : patients) {
			result[i++] = myPatient;
		}
		return result;
	}

	/**
	 * Retrieves all medicines that have been created in the system.
	 *
	 * @return An array of all {@link Medicine} objects.
	 */
	public static Medicine[] getAllMedicines() {
		Medicine[] result = new Medicine[medicines.size()];
		int i = 0;
		for (Medicine myMedicine : medicines) {
			result[i++] = myMedicine;
		}
		return result;
	}

}
