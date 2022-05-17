package poroje3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Ranking class implementation of HangmanRanking
 * @author Amirhossein Mahdinejad
 */
public class Ranking {
	
	private static int numberOfPlayers = 0;
	
	/**
	 * This method will count number of players and set it to its Field
	 * @param numberOfPlayers the numberOfPlayers to set
	 */
	private static void setNumberOfPlayers() {
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
	 * This method will add a player to rankings file
	 * @param player Player
	 * @param score float
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
	 * This method will read the rankings file and show the result of games
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
	 * This method will change place of two index String array
	 * @param s String[]
	 * @param x integer
	 * @param y integer
	 */
	private static void swap(String[] s, int x, int y) {
		String tmp = s[x];
		s[x] = s[y];
		s[y] = tmp;
	}
	
	/**
	 * This method will change place of two index float array
	 * @param a float[]
	 * @param x integer
	 * @param y integer
	 */
	private static void swap(float a[], int x, int y) {
		float tmp = a[x];
		a[x] = a[y];
		a[y] = tmp;
	}
	
	/**
	 * This method will sort two arrays
	 * @param scores float[]
	 * @param names String[]
	 * @param tah integer
	 */
	private static void sort(float scores[], String names[], int tah) {
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
