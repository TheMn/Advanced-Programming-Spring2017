package poroje4;

import java.util.ArrayList;

/**
 * Represents a disease in the hospital management system. This class stores information
 * about a disease, including its name, symptoms, and the medicines used to treat it.
 *
 * @author Amirhossein Mahdinejad
 * @version 1.1
 * @since 2023-04-12
 */
public class Disease {
	private String name;
	ArrayList<String> symptoms = new ArrayList<String>();
	ArrayList<Medicine> diseaseMedicines = new ArrayList<Medicine>();
	static ArrayList<Disease> diseases = new ArrayList<Disease>();

	/**
	 * Constructs a new Disease object with the given name.
	 *
	 * @param name The name of the disease.
	 */
	public Disease(String name) {
		this.setName(name);
		diseases.add(this);
	}

	/**
	 * Sets the name of the disease.
	 *
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the name of the disease.
	 *
	 * @return The name of the disease.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves an array of all patients currently infected with this disease.
	 *
	 * @return An array of {@link Patient} objects.
	 */
	public Patient[] getAllInfected() {
		ArrayList<Patient> infectedPatients = new ArrayList<Patient>();
		for (Patient myPatient : Patient.patients) {
			for (Disease myDisease : myPatient.patientsDiseases) {
				if(this == myDisease)
					infectedPatients.add(myPatient);
			}
		}
		Patient[] result = new Patient[infectedPatients.size()];
		int i = 0;
		for (Patient patient : infectedPatients) {
			result[i++] = patient;
		}
		return result;
	}

	/**
	 * Adds a symptom to the list of symptoms for this disease.
	 *
	 * @param symptom The symptom to add.
	 */
	public void addSymptoms(String symptom) {
		this.symptoms.add(symptom);
	}

	/**
	 * Adds multiple symptoms to the list of symptoms for this disease.
	 *
	 * @param symptoms An ArrayList of symptoms to add.
	 */
	public void addAllSymptoms(ArrayList<String> symptoms) {
		this.symptoms.addAll(symptoms);
	}

	/**
	 * Removes a symptom from the list of symptoms for this disease.
	 *
	 * @param symptom The symptom to remove.
	 * @return {@code true} if the symptom was successfully removed, {@code false} otherwise.
	 */
	public boolean removeSymptom(String symptom){
		return this.symptoms.remove(symptom);
	}

	/**
	 * Removes multiple symptoms from the list of symptoms for this disease.
	 *
	 * @param symptoms An ArrayList of symptoms to remove.
	 */
	public void removeAllSymptoms(ArrayList<String> symptoms){
		this.symptoms.removeAll(symptoms);
	}

	/**
	 * Retrieves all symptoms associated with this disease.
	 *
	 * @return A string array of all symptoms.
	 */
	public String[] getAllSymptoms(){
		String []result = new String[this.symptoms.size()];
		int i = 0;
		for (String mySymptom: this.symptoms) {
			result[i++] = mySymptom;
		}
		return result;
	}

	/**
	 * Adds a medicine to the list of medicines used to treat this disease.
	 *
	 * @param medicine The medicine to add.
	 */
	public void addMedicine(Medicine medicine){
		this.diseaseMedicines.add(medicine);
	}

	/**
	 * Adds multiple medicines to the list of medicines used to treat this disease.
	 *
	 * @param medicines An ArrayList of medicines to add.
	 */
	public void addAllMedicines(ArrayList<Medicine> medicines){
		this.diseaseMedicines.addAll(medicines);
	}

	/**
	 * Removes a medicine from the list of medicines for this disease.
	 *
	 * @param medicine The medicine to remove.
	 * @return {@code true} if the medicine was successfully removed, {@code false} otherwise.
	 */
	public boolean removeMedicine(Medicine medicine){
		return this.diseaseMedicines.remove(medicine);
	}

	/**
	 * Removes multiple medicines from the list of medicines for this disease.
	 *
	 * @param medicines An ArrayList of medicines to remove.
	 */
	public void removeAllMedicines(ArrayList<Medicine> medicines){
		this.diseaseMedicines.removeAll(medicines);
	}

	/**
	 * Retrieves all medicines used to treat this disease.
	 *
	 * @return An array of {@link Medicine} objects.
	 */
	public Medicine[] getAllMedicines(){
		Medicine[] result = new Medicine[this.diseaseMedicines.size()];
		int i = 0;
		for (Medicine myMedicine : this.diseaseMedicines) {
			result[i++] = myMedicine;
		}
		return result;
	}

	/**
	 * Retrieves all diseases that have been created in the system.
	 *
	 * @return An array of all {@link Disease} objects.
	 */
	public static Disease[] getAllDiseases(){
		Disease[] result = new Disease[diseases.size()];
		int i = 0;
		for (Disease myDisease : diseases) {
			result[i++] = myDisease;
		}
		return result;
	}
}
