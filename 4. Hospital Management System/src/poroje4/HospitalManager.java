//In The Name of Allah, The Most Beneficent, The Most Merciful
package poroje4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1> HospitalManager </h1>
 * <h2> (Advanced Programming Fourth Project) </h2>
 * @author Amirhossein Mahdinejad
 * <p> Student Code: 950122680001, Mail: mt.lroc@outlook.com </p>
 */
public class HospitalManager {
	private final Scanner sc = new Scanner(System.in);
	
	/**
	 * The main method
	 * @param args default argument
	 */
	public static void main(String[] args) {
		new HospitalManager();
	}
	
	/**
	 * HospitalManager contractor
	 */
	HospitalManager(){
		String exitAnswer = "no";
		do {
			System.out.println("What do you want to do ?");
			System.out.println("[1]exit   [2]new object   [3]show objects   [4]use methods");
			try{
				switch (Integer.parseInt(sc.next())) {
				
				/**
				 * User wants to exit
				 */
				case 1:
					System.out.println("do you want to exit ? (yes / no)");
					exitAnswer = sc.next();
					break;
					
					/**
					 * User wants to add new object
					 */
				case 2:
					System.out.println("what object do you want to add ?");
					System.out.println("[1]Patient   [2]Doctor   [3]Disease   [4]Medicine");
					
					switch (Integer.parseInt(sc.next())) {
					
					/**
					 * Create new Patient
					 */
					case 1:
						createPatient(-1);
						break;
						
					/**
					 * Create new Doctor
					 */
					case 2:
						createDoctor(-1);
						break;
					
					/**
					 * Create new Disease
					 */
					case 3:
						createDisease(-1);
						break;
					
					/**
					 * Create new Medicine
					 */
					case 4:
						createMedicine(-1);
						break;
						
					default:
						System.err.println("Please just enter default numbers");
						break;
					}
					break;
					
					/**
					 * User wants to see all created objects				
					 */
				case 3:
					showObjects();
					break;
					
				case 4:
					System.out.println("which Object do you want to access ?");
					System.out.println("[1]Patient   [2]Doctor   [3]Disease   [4]Medicine");
					
					switch (Integer.parseInt(sc.next())) {
					
						/**
						 * call Patient classes
						 */
					case 1:
						System.out.println("For which patient ?");
						for (int i = 0; i < Patient.patients.size(); i++) {
							System.out.println("[" + (i+1) + "]" + Patient.patients.get(i).getDescription());
						}
						int patientIndex = Integer.parseInt(sc.next()) - 1;
						
						System.out.println("which method do you want to access ?");
						System.out.println("[1]getDoctor\n[2]setDoctor\n[3]addDisease\n[4]curedDisease\n[5]getAllDiseases\n[6]getAllMedicines\n[7]getAllPatients");
						int methodIndex = Integer.parseInt(sc.next());
						
						callPatientMethods(patientIndex, methodIndex);
						break;
						
						/**
						 * call Doctor classes 
						 */
					case 2:
						System.out.println("For which doctor ?");
						for (int i = 0; i < Doctor.doctors.size(); i++) {
							System.out.println("[" + (i+1) + "]" + Doctor.doctors.get(i).getDescription());
						}
						int doctorIndex = Integer.parseInt(sc.next()) - 1;
						
						System.out.println("which method do you want to access ?");
						System.out.println("[1]addPatient\n[2]addAllPatients\n[3]removePatient\n[4]removeAllPatients\n[5]getAllPatients\n[6]getAllDoctors");
						methodIndex = Integer.parseInt(sc.next());
						
						callDoctorMethods(doctorIndex, methodIndex);
						break;
						
						/**
						 * call Disease classes
						 */
					case 3:
						System.out.println("For which disease ?");
						for (int i = 0; i < Disease.diseases.size(); i++) {
							System.out.println("[" + (i+1) + "]" + Disease.diseases.get(i).getName());
						}
						int diseaseIndex = Integer.parseInt(sc.next()) - 1;
						System.out.println("which method do you want to access ?");
						System.out.println("[1]setName\n[2]getName\n[3]getAllInfected\n[4]addSymptoms\n[5]addAllSymptoms\n[6]removeSymptom\n[7]removeAllSymptoms\n[8]getAllSymptoms\n[9]addMedicine\n[10]addAllMedicines\n[11]removeMedicine\n[12]removeAllMedicines\n[13]getAllMedicines\n[14]getAllDiseases");
						methodIndex = Integer.parseInt(sc.next());
						
						callDiseaseMethods(diseaseIndex, methodIndex);
						break;
						
						/**
						 * call Medicine classes
						 */
					case 4:
						System.out.println("For which Medicine ?");
						for (int i = 0; i < Medicine.medicines.size(); i++) {
							System.out.println("[" + (i+1) + "]" + Medicine.medicines.get(i).getName());
						}
						int medicineIndex = Integer.parseInt(sc.next()) - 1;
						System.out.println("which method do you want to access ?");
						System.out.println("[1]setName\n[2]getName\n[3]getAllDiseases\n[4]getAllPatients\n[5]getAllMedicines");
						methodIndex = Integer.parseInt(sc.next());
						
						callMedicineMethods(medicineIndex, methodIndex);
						break;
						
					default:
						System.err.println("Please just enter default inputs");
						break;
					}
					break;
					
				default:
					System.err.println("Please just enter default inputs");
					break;
				}
			}catch(Exception e){
				System.err.println("*** Enter Standard Test-Cases ***");
			}
		} while (!exitAnswer.equalsIgnoreCase("yes"));
		sc.close();
		
	}
	
	/**
	 * This method will show all created objects to user
	 */
	private void showObjects(){
		System.out.println("Created Objects:");
		if(!Doctor.doctors.isEmpty()){
			System.out.println("---Doctors---");
			for (Doctor myDoctor : Doctor.doctors) {
				System.out.println(myDoctor.getDescription());
			}
		}
		if(!Patient.patients.isEmpty()){
			System.out.println("---Patients---");
			for (Patient myPatient : Patient.patients) {
				System.out.println(myPatient.getDescription());
			}
		}
		if(!Disease.diseases.isEmpty()){
			System.out.println("---Diseases---");
			for (Disease myDisease : Disease.diseases) {
				System.out.println(myDisease.getName());
			}
		}
		if(!Medicine.medicines.isEmpty()){
			System.out.println("---Medicine---");
			for (Medicine myMedicine : Medicine.medicines) {
				System.out.println(myMedicine.getName());
			}
		}
	}
	
	/**
	 * This method will create a patient and set its doctor
	 * @param whichDoctor
	 */
	private void createPatient(int whichDoctor){
		try{
			System.out.println("Please enter patient id (or -1 if it has not id)");
			String answerString = sc.next();
			System.out.println("which type do you want to use ?");
			System.out.println("[1]FirstName, LastName");
			System.out.println("[2]FirstName, LastName, Gender, BirthDate");
			int type = Integer.parseInt(sc.next());
			long id = Long.parseLong(answerString);
			Patient newPatient = null;
			switch (type) {

			case 1:
				System.out.println("Please enter Patients FirstName:");
				String firstName = sc.next();
				
				System.out.println("Please enter Patients LastName:");
				String lastName = sc.next();
				
				if(id == -1)
					newPatient = new Patient(firstName, lastName);
				else	newPatient = new Patient(firstName, lastName, id);
				break;
				
			case 2:
				System.out.println("Please enter Patients FirstName:");
				firstName = sc.next();
				
				System.out.println("Please enter Patients LastName:");
				lastName = sc.next();
				
				System.out.println("Please enter Patients Gender: (Male / Female)");
				String gender = sc.next();
				
				System.out.println("Please enter Patients BirthDate: (YYYY.MM.DD)");
				String birthDate = sc.next();

				if(id == -1){
					newPatient = new Patient(firstName, lastName, gender, birthDate, id);
					newPatient.idGenerator(id);
				}
				else	newPatient = new Patient(firstName, lastName, gender, birthDate, id);
				break;
				
			default:
				break;
			}
			if (whichDoctor != -1) {
				Doctor.doctors.get(whichDoctor).addPatient(newPatient);
			}

		}catch(Exception e){
			System.err.println("Something in creating patient went wrong");
		}
	}
	
	/**
	 * This method will create a doctor and set its patient
	 * @param whichPatient
	 */
	private void createDoctor(int whichPatient){
		try{
			System.out.println("Please enter doctor id (or -1 if it has not id)");
			String answerString = sc.next();
			System.out.println("which type do you want to use ?");
			System.out.println("[1]FirstName, LastName");
			System.out.println("[2]FirstName, LastName, Gender, BirthDate");
			int type = Integer.parseInt(sc.next());
			long id = Long.parseLong(answerString);
			
			Doctor newDoctor = null;
			switch (type) {

			case 1:
				System.out.println("Please enter Doctors FirstName:");
				String firstName = sc.next();
				
				System.out.println("Please enter Doctors LastName:");
				String lastName = sc.next();
				
				if(id == -1)
					newDoctor = new Doctor(firstName, lastName);
				else	newDoctor = new Doctor(firstName, lastName, id);
				break;
				
			case 2:
				System.out.println("Please enter Doctors FirstName:");
				firstName = sc.next();
				
				System.out.println("Please enter Doctors LastName:");
				lastName = sc.next();
				
				System.out.println("Please enter Doctors Gender: (Male / Female)");
				String gender = sc.next();
				
				System.out.println("Please enter Doctors BirthDate: (YYYY.MM.DD)");
				String birthDate = sc.next();

				if(id == -1){
					newDoctor = new Doctor(firstName, lastName, gender, birthDate, id);
					newDoctor.idGenerator(id);
				}
				else	newDoctor = new Doctor(firstName, lastName, gender, birthDate, id);
				break;
				
			default:
				break;
			}
			if (whichPatient != -1) {
				Patient.patients.get(whichPatient).setDoctor(newDoctor);
			}

		}catch(Exception e){
			System.err.println("Something in creating Doctor went wrong");
		}
	}
	
	/**
	 * This method will create a disease and set its patient
	 * @param whichPatient
	 */
	private void createDisease(int whichPatient){
		try{
			System.out.println("Please enter Disease name:");
			Disease newDisease = new Disease(sc.next());
			if(whichPatient != -1){
				Patient.patients.get(whichPatient).addDisease(newDisease);
			}
		}catch(Exception e){
			System.err.println("Something in creating Disease went wrong");
		}
	}
	
	/**
	 * This method will create a medicine and set its disease
	 * @param whichDisease
	 */
	private void createMedicine(int whichDisease){
		try{
			System.out.println("Please enter Medicine name:");
			Medicine newMedicine = new Medicine(sc.next());
			if(whichDisease != -1){
				Disease.diseases.get(whichDisease).addMedicine(newMedicine);
			}
		}catch(Exception e){
			System.err.println("Something in creating Medicine went wrong");
		}
	}
	
	/**
	 * This method will call Medicine methods
	 * @param medicineIndex which medicine
	 * @param methodIndex which method
	 */
	void callMedicineMethods(int medicineIndex, int methodIndex){
		//[1]setName\n[2]getName\n[3]getAllDiseases\n[4]getAllPatients\n[5]getAllMedicines
		switch (methodIndex) {
	
		/**
		 * Set name to Medicine
		 */
		case 1:
			System.out.println("Please enter medicine name:");
			Medicine.medicines.get(medicineIndex).setName(sc.next());
			break;

		/**
		 * Get the name of Medicine
		 */
		case 2:
			System.out.println("Medicine name: " + Medicine.medicines.get(medicineIndex).getName());
			break;
			
		/**
		 * Get all diseases of this medicine
		 */
		case 3:
			System.out.println("All diseases of this medicine:");
			Disease[] diseasesToShow = Medicine.medicines.get(medicineIndex).getAllDiseases();
			for (int i = 0; i < diseasesToShow.length; i++) {
				System.out.println((i+1) + ": " + diseasesToShow[i].getName());
			}
			break;
			
		/**
		 * Get all patients of this medicine
		 */
		case 4:
			System.out.println("All patients of this medicine:");
			Patient[] patientsToShow = Medicine.medicines.get(medicineIndex).getAllPatients();
			for (int i = 0; i < patientsToShow.length; i++) {
				System.out.println((i+1) + ": " + patientsToShow[i].getDescription());
			}
			break;
			
		/**
		 * Get all medicines
		 */
		case 5:
			System.out.println("All medicines:");
			Medicine[] allMedicines = Medicine.getAllMedicines();
			for (int i = 0; i < allMedicines.length; i++) {
				System.out.println((i+1) + ": " + allMedicines[i].getName());
			}
			break;
		
		default:
			break;
		}
	}
	
	/**
	 * This method will call Disease methods
	 * @param diseaseIndex which disease
	 * @param methodIndex which method
	 */
	void callDiseaseMethods(int diseaseIndex, int methodIndex){
		switch (methodIndex) {
		
		/**
		 * Set name to disease
		 */
		case 1:
			System.out.println("Please enter disease name:");
			Disease.diseases.get(diseaseIndex).setName(sc.next());
			break;
		
		/**
		 * Get the name of disease
		 */
		case 2:
			System.out.println("Disease name: " + Disease.diseases.get(diseaseIndex).getName());
			break;

		/**
		 * Get all infected patients to this disease
		 */
		case 3:
			System.out.println("All infected:");
			Patient[] allInfected = Disease.diseases.get(diseaseIndex).getAllInfected();
			for (int i = 0; i < allInfected.length; i++) {
				System.out.println((i+1) + ": " + allInfected[i].getDescription());
			}
			break;
			
		/**
		 * Add a symptom to this disease
		 */
		case 4:
			System.out.println("Please enter symptom of this Disease:");
			String symptom = sc.next();
			Disease.diseases.get(diseaseIndex).symptoms.add(symptom);
			break;
			
		/**
		 * Add a list of symptoms to this disease
		 */
		case 5:
			System.out.println("Please enter symptoms names...\nEnter a * character at the end of names");
			ArrayList<String> symptomNames = new ArrayList<String>();
			while(true){
				symptom = sc.next();
				if(symptom.equals("*"))
					break;
				symptomNames.add(symptom);
			}
			Disease.diseases.get(diseaseIndex).addAllSymptoms(symptomNames);
			break;
			
		/**
		 * Remove a symptom from disease symptoms
		 */
		case 6:
			System.out.println("List of symptoms:");
			ArrayList<String> allSymptoms = new ArrayList<String>();
			for (int i = 0, k = 0; i < Disease.diseases.size(); i++) {
				for (int j = 0; j < Disease.diseases.get(i).symptoms.size(); j++) {
					System.out.println("[" + (++k) + "]" + Disease.diseases.get(i).symptoms.get(j));
					allSymptoms.add(Disease.diseases.get(i).symptoms.get(j));
				}
			}
			System.out.println("which symptom do you want to remove from disease ?");
			symptom = sc.next();
			Disease.diseases.get(diseaseIndex).removeSymptom(allSymptoms.get(Integer.parseInt(symptom) - 1));
			break;
			
		/**
		 * Remove a list of symptoms from disease symptoms
		 */
		case 7:
			System.out.println("List of symptoms:");
			allSymptoms = new ArrayList<String>();
			for (int i = 0, k = 0; i < Disease.diseases.size(); i++) {
				for (int j = 0; j < Disease.diseases.get(i).symptoms.size(); j++) {
					System.out.println("[" + (++k) + "]" + Disease.diseases.get(i).symptoms.get(j));
					allSymptoms.add(Disease.diseases.get(i).symptoms.get(j));
				}
			}
			System.out.println("Please enter your selected symptoms as a number digits...\nEnter a * character at the end of names");
			symptomNames = new ArrayList<String>();
			while(true){
				symptom = sc.next();
				if(symptom.equals("*"))
					break;
				symptomNames.add(symptom);
			}
			Disease.diseases.get(diseaseIndex).removeAllSymptoms(symptomNames);
			break;
			
		/**
		 * Get all symptoms of this disease
		 */
		case 8:
			System.out.println("All symptoms of this disease are:");
			String[] thisDiseaseSymptoms = Disease.diseases.get(diseaseIndex).getAllSymptoms();
			for (int i = 0; i < thisDiseaseSymptoms.length; i++) {
				System.out.println((i+1) + ": " + thisDiseaseSymptoms[i]);
			}
			break;
		
		/**
		 * Add a Medicine to disease medicines
		 */
		case 9:
			System.out.println("List of Medicines:");
			for (int i = 0; i < Medicine.medicines.size(); i++) {
				System.out.println("[" + (i+1) + "]" + Medicine.medicines.get(i).getName());
			}
			System.out.println("[" + (Medicine.medicines.size() + 1) + "]" + "create new medicine");
			String userAnswer = sc.next();
			if(Medicine.medicines.size() + 1 == Integer.parseInt(userAnswer))
				createMedicine(diseaseIndex);
			else
				Disease.diseases.get(diseaseIndex).addMedicine(Medicine.medicines.get(Integer.parseInt(userAnswer) - 1));
			break;
		/**
		 * Add a list of Medicines to disease medicines
		 */
		case 10:
			System.out.println("List of Medicines:");
			for (int i = 0; i < Medicine.medicines.size(); i++) {
				System.out.println("[" + (i+1) + "]" + Medicine.medicines.get(i).getName());
			}
			System.out.println("Please enter Medicines Numbers...\nEnter a * character at the end of names");
			ArrayList<Medicine> medicinesToAdd = new ArrayList<Medicine>();
			while(true){
				String medicineNumber = sc.next();
				if(medicineNumber.equals("*"))
					break;
				medicinesToAdd.add(Medicine.medicines.get(Integer.parseInt(medicineNumber) - 1));
			}
			Disease.diseases.get(diseaseIndex).addAllMedicines(medicinesToAdd);
			break;
			
		/**
		 * Remove a medicine from disease medicines
		 */
		case 11:
			System.out.println("List of Medicines:");
			for (int i = 0; i < Medicine.medicines.size(); i++) {
				System.out.println("[" + (i+1) + "]" + Medicine.medicines.get(i).getName());
			}
			String medicineToRemove = sc.next();
			Disease.diseases.get(diseaseIndex).removeMedicine(Medicine.medicines.get(Integer.parseInt(medicineToRemove) - 1));
			break;
			
		/**
		 * Remove a list of medicines from disease medicines
		 */
		case 12:
			System.out.println("List of Medicines:");
			for (int i = 0; i < Medicine.medicines.size(); i++) {
				System.out.println("[" + (i+1) + "]" + Medicine.medicines.get(i).getName());
			}
			System.out.println("Please enter Medicines Numbers...\nEnter a * character at the end of names");
			ArrayList<Medicine> medicinesToRomove = new ArrayList<Medicine>();
			while(true){
				String medicineNumber = sc.next();
				if(medicineNumber.equals("*"))
					break;
				medicinesToRomove.add(Medicine.medicines.get(Integer.parseInt(medicineNumber) - 1));
			}
			Disease.diseases.get(diseaseIndex).removeAllMedicines(medicinesToRomove);
			break;

		/**
		 * Get all medicines of this disease
		 */
		case 13:
			System.out.println("All Medicines of this disease:");
			Medicine[] medicinesToShow = Disease.diseases.get(diseaseIndex).getAllMedicines();
			for (int i = 0; i < medicinesToShow.length; i++) {
				System.out.println((i+1) + ": " + medicinesToShow[i].getName());
			}
			break;
		
		/**
		 * Get all diseases
		 */
		case 14:
			System.out.println("All diseases:");
			Disease[] allDiseases = Disease.getAllDiseases();
			for (int i = 0; i < allDiseases.length; i++) {
				System.out.println((i+1) + ": " + allDiseases[i].getName());
			}
			break;
			
		default:
			break;
		}
	}
	
	/**
	 * This method will call doctors methods
	 * @param doctorIndex which patient
	 * @param methodIndex which method
	 */
	void callDoctorMethods(int doctorIndex, int methodIndex){
		switch (methodIndex) {
		
		/**
		 * Add a patient to list of doctors patients
		 */
		case 1:
			System.out.println("List of patients:");
			for (int i = 0; i < Patient.patients.size(); i++) {
				System.out.println("[" + (i+1) + "]" + Patient.patients.get(i).getDescription());
			}
			System.out.println("[" + (Patient.patients.size() + 1) + "]" + "create new patient");
			String userAnswer = sc.next();
			if(Patient.patients.size() + 1 == Integer.parseInt(userAnswer))
				createPatient(doctorIndex);
			else
				Doctor.doctors.get(doctorIndex).addPatient(Patient.patients.get(Integer.parseInt(userAnswer)-1));
			break;

		/**
		 * Add a list of patients to doctors patients
		 */
		case 2:
			ArrayList<Patient> patientsToAdd = new ArrayList<Patient>();
			System.out.println("List of patients:");
			for (int i = 0; i < Patient.patients.size(); i++) {
				System.out.println("[" + (i+1) + "]" + Patient.patients.get(i).getDescription());
			}
			System.out.println("Please enter your selected patients...\nEnter a * character at the end");
			while(true){
				String whichPatient = sc.next();
				if(whichPatient.equals("*"))
					break;
				patientsToAdd.add(Patient.patients.get(Integer.parseInt(whichPatient) - 1));
			}
			Doctor.doctors.get(doctorIndex).addAllPatients(patientsToAdd);
			break;
		
		/**
		 * Remove a patient from doctors patients
		 */
		case 3:
			System.out.println("The doctors patients:");
			for (int i = 0; i < Doctor.doctors.get(doctorIndex).doctorsPatients.size(); i++) {
				System.out.println("[" + (i+1) + "]" + Doctor.doctors.get(doctorIndex).doctorsPatients.get(i).getDescription());
			}
			System.out.println("which patient do you want to remove from doctors list ?");
			userAnswer = sc.next();
			Doctor.doctors.get(doctorIndex).removePatient(Patient.patients.get(Integer.parseInt(userAnswer)-1));
			break;
		
		/**
		 * Remove a list of patients from doctors patients
		 */
		case 4:
			ArrayList<Patient> patientsToRemove = new ArrayList<Patient>();
			System.out.println("List of patients:");
			for (int i = 0; i < Patient.patients.size(); i++) {
				System.out.println("[" + (i+1) + "]" + Patient.patients.get(i).getDescription());
			}
			System.out.println("Please enter your selected patients...\nEnter a * character at the end");
			while(true){
				String whichPatient = sc.next();
				if(whichPatient.equals("*"))
					break;
				patientsToRemove.add(Patient.patients.get(Integer.parseInt(whichPatient) - 1));
			}
			Doctor.doctors.get(doctorIndex).removeAllPatients(patientsToRemove);
			break;
		
		/**
		 * Get all patients of this doctor
		 */
		case 5:
			System.out.println("All patients of this doctor:");
			Patient[] doctorsPatients = Doctor.doctors.get(doctorIndex).getAllPatients();
			for (int i = 0; i < doctorsPatients.length; i++) {
				System.out.println((i+1) + ": " + doctorsPatients[i].getDescription());
			}
			break;

		/**
		 * Get all doctors
		 */
		case 6:
			System.out.println("All doctors:");
			Doctor[] allDoctors = Doctor.getAllDoctors();
			for (int i = 0; i < allDoctors.length; i++) {
				System.out.println((i+1) + ": " + allDoctors[i].getDescription());
			}
			break;
			
		default:
			break;
		}
	}
	
	/**
	 * This method will call patients methods
	 * @param patientIndex which patient
	 * @param methodIndex which method
	 */
	void callPatientMethods(int patientIndex, int methodIndex){
		switch (methodIndex) {
		/**
		 * Get patients doctor
		 */
		case 1:
			System.out.println(Patient.patients.get(patientIndex).getDoctor().getDescription());
			break;
			
		/**
		 * Set patients doctor
		 */
		case 2:
			System.out.println("List of doctors:");
			for (int i = 0; i < Doctor.doctors.size(); i++) {
				System.out.println("[" + (i+1) + "]" + Doctor.doctors.get(i).getDescription());
			}
			System.out.println("[" + (Doctor.doctors.size() + 1) + "]" + "create new doctor");
			String userAnswer = sc.next();
			if(Doctor.doctors.size() + 1 == Integer.parseInt(userAnswer))
				createDoctor(patientIndex);
			else
				Patient.patients.get(patientIndex).setDoctor(Doctor.doctors.get(Integer.parseInt(userAnswer)-1));
			break;
			
		/**
		 * Add disease to patients diseases
		 */
		case 3:
			System.out.println("List of diseases:");
			for (int i = 0; i < Disease.diseases.size(); i++) {
				System.out.println("[" + (i+1) + "]" + Disease.diseases.get(i).getName());
			}
			System.out.println("[" + (Disease.diseases.size() + 1) + "]" + "create new disease");
			userAnswer = sc.next();
			if(Disease.diseases.size() + 1 == Integer.parseInt(userAnswer))
				createDisease(patientIndex);
			else
				Patient.patients.get(patientIndex).addDisease(Disease.diseases.get(Integer.parseInt(userAnswer)-1));
			break;
			
		/**
		 * Cured disease of patient
		 */
		case 4:
			System.out.println("List of all diseases:");
			for (int i = 0; i < Disease.diseases.size(); i++) {
				System.out.println("[" + (i+1) + "]" + Disease.diseases.get(i).getName());
			}
			userAnswer = sc.next();
			Patient.patients.get(patientIndex).curedDisease(Disease.diseases.get(Integer.parseInt(userAnswer)-1));
			break;
		
		/**
		 * Get all diseases of patient
		 */
		case 5:
			System.out.println("All diseases of this patient:");
			Disease[] diseasesToShow = Patient.patients.get(patientIndex).getAllDiseases();
			for (int i = 0; i < diseasesToShow.length; i++) {
				System.out.println((i+1) + ": " + diseasesToShow[i].getName());
			}
			break;
			
		/**
		 * Get all medicines of patient
		 */
		case 6:
			System.out.println("All medicines of this patient:");
			Medicine[] medicinesToShow = Patient.patients.get(patientIndex).getAllMedicines();
			for (int i = 0; i < medicinesToShow.length; i++) {
				System.out.println((i+1) + ": " + medicinesToShow[i].getName());
			}
			break;
		
		/**
		 * Get all patients
		 */
		case 7:
			System.out.println("All patients:");
			Patient[] patientsToShow = Patient.getAllPatients();
			for (int i = 0; i < patientsToShow.length; i++) {
				System.out.println((i+1) + ": " + patientsToShow[i].getDescription());
			}
			break;
			
		default:
			break;
		}
	}

}
