package poroje4;

import java.util.ArrayList;

/**
 * <h1> Disease </h1>
 * Declaration of Disease class
 */
public class Disease {
	private String name;
	ArrayList<String> symptoms = new ArrayList<String>();
	ArrayList<Medicine> diseaseMedicines = new ArrayList<Medicine>();
	static ArrayList<Disease> diseases = new ArrayList<Disease>();
	
	/**
	 * The Disease contractor
	 * @param name the name of disease
	 */
	public Disease(String name) {
		this.setName(name);
		diseases.add(this);
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method will return an array of Patients infected to this Disease
	 * @return an array of patients
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
	 * This method will add a symptom to Disease symptoms
	 * @param symptom to add
	 */
	public void addSymptoms(String symptom) {
		this.symptoms.add(symptom);
	}
	
	/**
	 * This method will add a number of symptoms to Disease symptoms
	 * @param symptoms to add
	 */
	public void addAllSymptoms(ArrayList<String> symptoms) {
		this.symptoms.addAll(symptoms);
	}
	
	/**
	 * This method will remove a symptom from symptoms
	 * @param symptom to remove
	 * @return true if there was symptom in symptoms
	 */
	public boolean removeSymptom(String symptom){
		return this.symptoms.remove(symptom);
	}
	
	/**
	 * This method will remove a number of symptoms from Disease symptoms
	 * @param symptoms to remove
	 */
	public void removeAllSymptoms(ArrayList<String> symptoms){
		this.symptoms.removeAll(symptoms);
	}
	
	/**
	 * This method will return all symptoms of Disease
	 * @return result String[] 
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
	 * This method will add a medicine to Disease medicines
	 * @param medicine to add
	 */
	public void addMedicine(Medicine medicine){
		this.diseaseMedicines.add(medicine);
	}
	
	/**
	 * This method will add a number of medicines to Disease medicines
	 * @param medicines to add
	 */
	public void addAllMedicines(ArrayList<Medicine> medicines){
		this.diseaseMedicines.addAll(medicines);
	}
	
	/**
	 * This method will remove a medicine from Disease medicines
	 * @param medicine to remove
	 * @return true if there was medicine in Disease medicines
	 */
	public boolean removeMedicine(Medicine medicine){
		return this.diseaseMedicines.remove(medicine);
	}
	
	/**
	 * 
	 * @param medicines to remove
	 */
	public void removeAllMedicines(ArrayList<Medicine> medicines){
		this.diseaseMedicines.removeAll(medicines);
	}
	
	/**
	 * This method will return all medicines of Disease
	 * @return an array of medicines
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
	 * This method will return an array contains all diseases
	 * @return an array of disease
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
