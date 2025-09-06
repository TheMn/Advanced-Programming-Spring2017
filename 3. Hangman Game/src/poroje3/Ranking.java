package poroje3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Manages the game's ranking system. This class handles reading from and writing to
 * a rankings file, as well as sorting and displaying the rankings.
 *
 * @author Amirhossein Mahdinejad
 * @version 1.1
 * @since 2023-04-12
 */
public class Ranking {

	private static int numberOfPlayers = 0;

	/**
	 * Counts the number of players in the rankings file and updates the numberOfPlayers field.
	 */
	public static void setNumberOfPlayers() {
		try {
            FileReader reader = new FileReader("rankings.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            int c = 0;
            while ((bufferedReader.readLine()) != null) {
		c++;
            }
            reader.close();
            numberOfPlayers = c;
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	/**
	 * Adds a player's score to the rankings file.
	 *
	 * @param player The player to add to the rankings.
	 * @param score The player's score.
	 */
	public static void addRanking(Player player, float score) {
		try {
            FileWriter writer = new FileWriter("rankings.txt", true);
            writer.write(player.name + " " + score + "\r\n");
            writer.close();
            setNumberOfPlayers();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	/**
	 * Reads the rankings from the file, sorts them in descending order of score,
	 * and prints them to the console.
	 */
	public static void showRanking() {
		System.out.println("---- RANKING LIST ----");
		if(numberOfPlayers != 0){
			try {
				String []playerNames = new String[numberOfPlayers];
				float []playerScores = new float[numberOfPlayers];

	            FileReader reader = new FileReader("rankings.txt");
	            BufferedReader bufferedReader = new BufferedReader(reader);
	            String line;
	            int k=0;
	            while ((line = bufferedReader.readLine()) != null) {
			for (int i = line.length()-1; i >= 0; i--) {
						if(line.charAt(i) == ' '){
							playerNames[k] = line.substring(0, i);
							playerScores[k++] = Float.parseFloat(line.substring(i+1));
							break;
						}
					}
	            }
	            sort(playerScores, playerNames, numberOfPlayers);
	            for (int i = 0; i < numberOfPlayers; i++) {
					System.out.println(i+1 + ": " + playerNames[i] + " " + playerScores[i]);
				}
	            reader.close();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}else
			System.out.println("ranking is empty");
		System.out.println();
	}

	/**
	 * Swaps two elements in a string array.
	 *
	 * @param s The string array.
	 * @param x The index of the first element to swap.
	 * @param y The index of the second element to swap.
	 */
	public static void swap(String[] s, int x, int y) {
		String tmp = s[x];
		s[x] = s[y];
		s[y] = tmp;
	}

	/**
	 * Swaps two elements in a float array.
	 *
	 * @param a The float array.
	 * @param x The index of the first element to swap.
	 * @param y The index of the second element to swap.
	 */
	public static void swap(float a[], int x, int y) {
		float tmp = a[x];
		a[x] = a[y];
		a[y] = tmp;
	}

	/**
	 * Sorts two arrays (one of scores, one of names) in descending order based on the scores.
	 * The names array is sorted in parallel to maintain the correspondence between names and scores.
	 *
	 * @param scores The array of scores to be sorted.
	 * @param names The array of names to be sorted in parallel.
	 * @param tah The number of elements to sort.
	 */
	public static void sort(float scores[], String names[], int tah) {
		for (int i = 0; i < tah; i++) {
			for (int j = i; j < tah; j++) {
				if(scores[i] < scores[j]){
					swap(scores, i, j);
					swap(names, i, j);
				}
			}
		}
	}
}
