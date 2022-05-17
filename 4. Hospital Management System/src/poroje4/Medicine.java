package poroje4;

import java.util.ArrayList;

/**
 * <h1> Medicine </h1>
 * Declaration of Medicine class
 */
public class Medicine {
	private String name;
	static ArrayList<Medicine> medicines = new ArrayList<Medicine>();

	/**
	 * The Medicine contractor
	 * @param name to set
	 */
	public Medicine(String name) {
		this.setName(name);
		medicines.add(this);
	}
	/**
	 * @return the name
	 */
	String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	void setName(String name) {
		this.name = name;
	}

	/**
	 * This method will return a list of Diseases
	 * @return Disease[]
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
	 * This method will return a list of Patients
	 * @return Patient[]
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
	 * This method will return a list of Medicines
	 * @return Medicine[]
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
