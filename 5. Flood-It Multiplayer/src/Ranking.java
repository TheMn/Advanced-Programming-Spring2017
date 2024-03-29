import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The Ranking class implementation a copy of my HangmanRanking for project 3
 * 
 * @author TheMn
 */
public class Ranking {

	/**
	 * This method will check players and set its score to its Field
	 * 
	 * @param name the name
	 * @return double
	 */
	public static double lastRecord(String name) {
		double record = 0;
		try {
			FileReader reader = new FileReader("rankings.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				String inList = line.substring(0, line.indexOf(":"));
				if (inList.equals(name)) {
					record = Double
							.parseDouble(line.substring(line.indexOf(":") + 1));
				}
			}
			reader.close();
			return record;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return record;
	}

//	/**
//	 * This method will add a player to rankings file
//	 * 
//	 * @param player
//	 *            Player
//	 * @param score
//	 *            float
//	 */
//	public static void addRanking(String name, double score) {
//		try {
//			FileWriter writer = new FileWriter("rankings.txt", true);
//			writer.write(name + ":" + score + "\r\n");
//			writer.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * This method will change place of two index String array
//	 * 
//	 * @param s
//	 *            String[]
//	 * @param x
//	 *            integer
//	 * @param y
//	 *            integer
//	 */
//	private static void swap(String[] s, int x, int y) {
//		String tmp = s[x];
//		s[x] = s[y];
//		s[y] = tmp;
//	}
//
//	/**
//	 * This method will change place of two index float array
//	 * 
//	 * @param a
//	 *            float[]
//	 * @param x
//	 *            integer
//	 * @param y
//	 *            integer
//	 */
//	private static void swap(float a[], int x, int y) {
//		float tmp = a[x];
//		a[x] = a[y];
//		a[y] = tmp;
//	}
//
//	/**
//	 * This method will sort two arrays
//	 * 
//	 * @param scores
//	 *            float[]
//	 * @param names
//	 *            String[]
//	 * @param tah
//	 *            integer
//	 */
//	private static void sort(float scores[], String names[], int tah) {
//		for (int i = 0; i < tah; i++) {
//			for (int j = i; j < tah; j++) {
//				if (scores[i] < scores[j]) {
//					swap(scores, i, j);
//					swap(names, i, j);
//				}
//			}
//		}
//	}
}
