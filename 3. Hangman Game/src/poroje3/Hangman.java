package poroje3;

import java.util.Scanner;

/**
 * <h1> HangmanGame </h1>
 * <h2> (Advanced Programming Third Project) </h2>
 * @author Amirhossein Mahdinejad
 * <p> Student Code: 950122680001, Mail: mt.lroc@outlook.com </p>
 */
public class Hangman {
	
	/**
	 * Main method will set input and call other functions and Classes
	 * @param args default
	 */
	public static void main(String args[]){
		Scanner sc;
		boolean keepGoing = true;
		System.out.println("Welcome to hangman game!");
		do{
			sc = new Scanner(System.in);
			System.out.print("Please enter your name:\n");
			String playerName = sc.nextLine();
			Player player = new Player(playerName);
			Game hangman = new Game(player);
			
			System.out.println("The game is started:");
			hangman.showState();
			while(!hangman.gameIsOver()){
				String playerGuess = sc.next();
				hangman.guessing(playerGuess);
				hangman.showState();
			}
			float finalScore = hangman.finalScore();
			System.out.println("Final Score is: "+ finalScore);
			if(player.isAlive())
				Ranking.addRanking(player, finalScore);
			Ranking.showRanking();
			
			System.out.println("Do you want to play again? (Yes\\No)");
			if(!sc.next().equalsIgnoreCase("yes"))
				keepGoing = false;
		}while(keepGoing);
		System.out.println("THE END...");
		sc.close();
	}
	
}