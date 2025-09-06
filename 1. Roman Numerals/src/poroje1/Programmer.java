//In The Name of Allah, The Most Beneficent, The Most Merciful
package poroje1;

import java.util.Scanner;

/**
 * This class is designed to process financial data.
 * It reads data about depositors, banks, and annual interest rates,
 * then calculates the final balance for each depositor after a certain number of years.
 * The initial deposit amounts are provided in Roman numerals.
 *
 * @author Amirhossein Mahdinejad
 * @version 1.0
 * @since 2023-04-12
 */
public class Programmer {

	/**
	 * Converts a Roman numeral string to its decimal (base-10) equivalent.
	 * This method supports both lowercase and uppercase Roman numerals.
	 *
	 * @param romanInput The Roman numeral string to be converted.
	 * @return The decimal equivalent of the Roman numeral as a float.
	 */
	public static float romanToTenBase(String romanInput) {
		float res = 0;
		for (int i = 0; i < romanInput.length(); i++) {
			switch(romanInput.charAt(i)){
			case 'i':
			case 'I':
				res += 1;
				break;
			case 'v':
			case 'V':
				res += 5;
				break;
			case 'x':
			case 'X':
				res += 10;
				break;
			case 'l':
			case 'L':
				res += 50;
				break;
			case 'c':
			case 'C':
				res += 100;
				break;
			case 'd':
			case 'D':
				res += 500;
				break;
			case 'm':
			case 'M':
				res += 1000;
				break;
			default:
				break;
			}
		}
		return res;
	}

	/**
	 * Swaps two elements in a string array.
	 *
	 * @param s The string array.
	 * @param x The index of the first element to swap.
	 * @param y The index of the second element to swap.
	 */
	static void swap(String[] s, int x, int y) {
		String tmp = s[x];
		s[x] = s[y];
		s[y] = tmp;
	}

	/**
	 * Swaps two elements in a float array. This is an overloaded method.
	 *
	 * @param a The float array.
	 * @param x The index of the first element to swap.
	 * @param y The index of the second element to swap.
	 */
	static void swap(float a[], int x, int y) {
		float tmp = a[x];
		a[x] = a[y];
		a[y] = tmp;
	}

	/**
	 * Sorts two arrays, one of floats (money) and one of strings (names), in descending order based on the money array.
	 * The names array is sorted in parallel to maintain the correspondence between names and money.
	 *
	 * @param money The array of floats to be sorted.
	 * @param names The array of strings to be sorted in parallel.
	 * @param tah The number of elements to sort.
	 */
	static void sort(float money[], String names[], int tah) {
		for (int i = 0; i < tah; i++) {
			for (int j = i; j < tah; j++) {
				if(money[i] < money[j]){
					swap(money, i, j);
					swap(names, i, j);
				}
			}
		}
	}

	/**
	 * Formats a float to have exactly two decimal places.
	 *
	 * @param f The float to be formatted.
	 * @return The formatted float.
	 */
	static float twoDecimalView(float f){
		int x = (int)(f*100);
		return ((float)x)/(float)100;
	}

	/**
	 * Processes the initial data and deposits to calculate and print the final balances.
	 * This method parses the input strings, calculates interest, sorts the depositors by their final balance,
	 * and prints the results.
	 *
	 * @param initialData A string containing the number of depositors, banks, annual interest rate, and number of years, separated by spaces.
	 * @param deposits A string containing the names of the depositors and their initial deposits in various banks, in Roman numerals.
	 */
	static void process(String initialData,String deposits){

		/*
		 * joda sazie tak tak e dadehaye initialData:
		 */
		int tedadeSepordeGozaran = 0, tedadeBank = 0, sudeSaalane = 0, saalha = 0;
		int avval = 0, shomareshVorudi = 1;
		initialData += " ";

		for (int akhar = 0; akhar < initialData.length(); akhar++) {
			if(initialData.charAt(akhar) == ' '){
				String tmp = initialData.substring(avval, akhar);
				avval = akhar+1;

				switch (shomareshVorudi) {
				case 1:
					tedadeSepordeGozaran = Integer.parseInt(tmp);
					break;
				case 2:
					tedadeBank = Integer.parseInt(tmp);
					break;
				case 3:
					sudeSaalane = Integer.parseInt(tmp);
					break;
				case 4:
					saalha = Integer.parseInt(tmp);
					break;
				default:
					break;
				}
				shomareshVorudi++;
			}
		}

		/*
		 * joda sazie tak tak e dadehaye deposits:
		 */
		String[] esmeSepordeGozaran = new String[tedadeSepordeGozaran];
		float[][] poolDarBank = new float[tedadeSepordeGozaran][tedadeBank];

		deposits += " ";
		int saresh = 0, shomareMoteqayyer = 0, indexEsm = -1, indexBank = 0;

		for (int tahesh = 0; tahesh < deposits.length(); tahesh++) {
			if(deposits.charAt(tahesh) == ' '){
				String tmp = deposits.substring(saresh, tahesh);
				saresh = tahesh+1;
				indexBank %= tedadeBank;

				if(shomareMoteqayyer%(tedadeBank+1) == 0){
					indexEsm++;
					esmeSepordeGozaran[indexEsm] = tmp;
				}
				else{
					poolDarBank[indexEsm][indexBank] = romanToTenBase(tmp);
					indexBank++;
				}

				shomareMoteqayyer++;
			}
		}

		/*
		 * mohasebeye sud va mojudiye kol baraye har fard:
		 */
		float[] pooleNahaei = new float[tedadeSepordeGozaran];
		for (int i = 0; i < tedadeSepordeGozaran; i++) {
			pooleNahaei[i] = 0;
			for (int j = 0; j < tedadeBank; j++) {
				for (int k = 0; k < saalha; k++) {
					poolDarBank[i][j] +=  poolDarBank[i][j]*((float)sudeSaalane/100.00);
				}
				pooleNahaei[i] += poolDarBank[i][j];
			}
		}

		/*
		 * call kardan e function e sort va namayesh dadan e khoruji
		 */
		sort(pooleNahaei, esmeSepordeGozaran, tedadeSepordeGozaran);
		for (int i = 0; i < tedadeSepordeGozaran; i++) {
			System.out.print(esmeSepordeGozaran[i] + ' ');
			System.out.println(twoDecimalView(pooleNahaei[i]));
		}

	}

	/**
	 * The main method, which serves as the entry point for the program.
	 * It reads the initial data and deposits from the standard input and calls the process method.
	 *
	 * @param args Command line arguments (not used).
	 */
	public static void main(String args[]){/// do not edit main method, input should be entered as described before
        String initialData,diposits;
        Scanner scanner= new Scanner(System.in);
        initialData=scanner.nextLine();/// first line of input is the initialData as we described before
        diposits=scanner.nextLine();/// second line of input is diposits as described before
		scanner.close();
        process(initialData,diposits);
	}
}
