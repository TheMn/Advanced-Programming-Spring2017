//In The Name of Allah, The Most Beneficent, The Most Merciful
package poroje1;

import java.util.Scanner;

/*
 * Amirhossein Mahdinejad
 * 950122680001
 * mt.lroc@outlook.com
 */
public class Programmer {
	
	/*
	 * tabdile adad romi be adad sahih:
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
	
	/*
	 * avaz kardan e jaye 2 khane dar array (ke baraye String[] va float[] overload shode)
	 */
	static void swap(String[] s, int x, int y) {
		String tmp = s[x];
		s[x] = s[y];
		s[y] = tmp;
	}
	static void swap(float a[], int x, int y) {
		float tmp = a[x];
		a[x] = a[y];
		a[y] = tmp;
	}
	
	/*
	 * moratab sazi e array float[]
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
	
	/*
	 * bargardandan e adad float ba 2 raqam ashaar
	 */
	static float twoDecimalView(float f){
		int x = (int)(f*100);
		return ((float)x)/(float)100;
	}
	
	/*
	 * gereftan e 2 reshte va anjam e mohasebat:
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
	
	/*
	 * function main baraye vorudi gereftan va call kardan e process
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
