package poroje3;

import java.util.Random;

/**
 * Represents a single game of Hangman. This class manages the game state,
 * including the secret sentence, the player's progress, and the remaining guesses.
 *
 * @author Amirhossein Mahdinejad
 * @version 1.1
 * @since 2023-04-12
 */
public class Game {

	private final String[] sentences = {
			"Only those who will risk going too far can possibly find out how far one can go",
			"My best friend is the one who brings out the best in me",
			"The man who moves a mountain begins by carrying away small stones",
			"Be the change that you wish to see in the world",
			"Friends are the siblings God never gave us",
			"In the end its not going to matter how many breaths you took",
			"Health is not valued till sickness comes",
			"Love is the only force capable of transforming an enemy into a friend",
			"A friend is someone who gives you total freedom to be yourself",
			"life runs on code",
			"Leaving sex to the feminists is like letting your dog vacation at the taxidermist",
			"Sometimes by losing a battle you find a new way to win the war",
			"The two most powerful warriors are patience and time",
			"War is peace Freedom is slavery Ignorance is strength",
			"There is not a long track record of people leaving professional sports to become a software developer",
			"You dont have to be a nerd or a programmer or a network engineer to make a difference",
			"Low level programming is good for the programmers soul",
			"The three chief virtues of a programmer are Laziness Impatience and Hubris",
			"I dont think anyone is going to say great things about being a native developer on Android",
			"talk is cheap show me the code",
			"Things are never quite as scary when you have got a best friend",
			"The language of friendship is not words but meanings",
			"There are no strangers here Only friends you have not yet met",
			"One loyal friend is worth ten thousand relatives",
			"but how many moments took your breath away",
			"When life gives you a hundred reasons to cry",
			"show life that you have a thousand reasons to smile",
			"Be who you are and say what you feel",
			"because those who mind dont matter",
			"those who matter dont mind",
			"Try not to become a man of success but rather try to become a man of value",
			"The true sign of intelligence is not knowledge but imagination",
			"A person who never made a mistake never tried anything new",
			"A girl should be classy and fabulous",
			"All our dreams can come true if we have the courage to pursue them",
			"Success usually comes to those who are too busy to be looking for it",
			"Things work out best for those who make the best of how things work out",
			"Our deepest fear is not that we are inadequate" };
	private Player player;
	private String goal;
	private String situation = "";
	private String wrongGuessed;
	private boolean[] guessed = new boolean[26];
	private long toGuess = 0;
	private long time;

	/**
	 * Constructs a new Game object for the given player.
	 * Initializes the game by selecting a random sentence, setting up the initial state,
	 * and recording the start time.
	 *
	 * @param player The player of the game.
	 */
	public Game(Player player) {
		this.setGoal();
		this.setSituation();
		this.setPlayer(player);
		this.setGuessed();
		this.setTime();
	}

	/**
	 * Selects a random sentence from the predefined list to be the goal of the game.
	 */
	public void setGoal() {
		Random random = new Random();
		this.goal = sentences[random.nextInt(sentences.length)];
	}

	/**
	 * Sets the player for the current game.
	 *
	 * @param player The player of the game.
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Initializes the 'situation' string, which represents the player's progress.
	 * All letters are initially replaced with underscores, while spaces are preserved.
	 */
	public void setSituation() {
		for (int i = 0; i < this.goal.length(); i++) {
			if (this.goal.charAt(i) == ' ')
				this.situation += " ";
			else
			{
				this.situation += "_";
				toGuess++;
			}
		}
	}

	/**
	 * Resets the record of guessed characters at the beginning of a new game.
	 */
	public void setGuessed() {
		for (int i = 0; i < this.guessed.length; i++) {
			this.guessed[i] = false;
		}
		this.wrongGuessed = "";
	}

	/**
	 * Records the start time of the game.
	 */
	public void setTime() {
		this.time = System.currentTimeMillis();
	}

	/**
	 * Validates if the player's guess is a single alphabetic character.
	 *
	 * @param guess The player's guess.
	 * @return {@code true} if the guess is a single letter, {@code false} otherwise.
	 */
	public boolean canGuess(String guess) {
		char c = guess.charAt(0);
		return (guess.length() == 1 && ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')));
	}

	/**
	 * Processes the player's guess. If the guess is valid and has not been made before,
	 * it updates the game state. Otherwise, it informs the player of the invalid input.
	 *
	 * @param guess The player's guess.
	 */
	public void guessing(String guess) {
		char guessChar = guess.charAt(0);

		int alfabet = 0;
		if (guessChar >= 'a' && guessChar <= 'z') {
			alfabet = guessChar - 'a';
		}
		else if (guessChar >= 'A' && guessChar <= 'Z') {
			alfabet = guessChar - 'A';
		}
		if (this.canGuess(guess)) {
			if (!this.guessed[alfabet]) {
				this.updateSituation(guessChar);
				this.guessed[alfabet] = true;
			}
			else
				System.out.println("!!!GUESSED BEFORE!!!");
		}
		else
			System.out.println("!!!WRONG INPUT!!!");

	}

	/**
	 * Updates the 'situation' string based on the player's guess.
	 * If the guess is correct, the corresponding underscores are replaced with the letter.
	 * If the guess is incorrect, the player loses a life (xp).
	 *
	 * @param guessChar The character guessed by the player.
	 */
	public void updateSituation(char guessChar) {
		boolean rigthGuess = false;
		int size = this.situation.length(), differ = 'a' - 'A';
		String res = "";
		for (int i = 0; i < size; i++) {
			char c = this.goal.charAt(i);
			char goalChar = (c >= 'a' && c <= 'z') ? (char) ((int) c - differ): c;
			c = guessChar;
			guessChar = (c >= 'a' && c <= 'z') ? (char) ((int) c - differ): c;

			if (goalChar == guessChar) {
				rigthGuess = true;
				toGuess--;
				res += this.goal.charAt(i);
			}
			else
				res += this.situation.charAt(i);
		}
		if (!rigthGuess && player.isAlive()){
			System.out.println("WRONG GUESS ._.");
			player.xp--;
			wrongGuessed += guessChar + " ";
		}
		else
			System.out.println("RIGHT GUESS ^_^");
		this.situation = res;
	}

	/**
	 * Formats a float to two decimal places.
	 *
	 * @param f The float to format.
	 * @return The formatted float.
	 */
	public float twoDecimalView(float f){
		int x = (int)(f*100);
		return ((float)x)/(float)100;
	}

	/**
	 * Calculates the final score of the player based on remaining lives (xp) and time taken.
	 *
	 * @return The player's final score.
	 */
	public float finalScore() {
		float surat = 1000*this.player.xp;
		float t = (System.currentTimeMillis() - this.time)/1000;
		float makhraj = (float)(Math.sqrt(Math.log(t)/Math.log(2)));
		return twoDecimalView(surat/makhraj);
	}

	/**
	 * Checks if the game is over, either by the player winning or losing.
	 * Prints a corresponding message if the game has ended.
	 *
	 * @return {@code true} if the game is over, {@code false} otherwise.
	 */
	public boolean gameIsOver() {
		if(toGuess == 0){
			System.out.println("Sentence: " + this.goal);
			System.out.println("YAAAAY!!! U WIN!");
			System.out.println("Time: " + (int)(System.currentTimeMillis() - this.time)/1000 + "s");
		}
		else if(!this.player.isAlive()){
			System.out.println("Sentence: " + this.goal);
			System.out.println("SHIIIIT!!! U LOSE!");
			System.out.println("Time: " + (int)(System.currentTimeMillis() - this.time)/1000 + "s");
		}
		return (!this.player.isAlive() || toGuess == 0);
	}

	/**
	 * Displays the current state of the game, including the player's score,
	 * the partially guessed sentence, wrong guesses, and a graphical representation of the hangman.
	 */
	public void showState() {
		System.out.println("Your score: " + (int)this.player.xp);
		System.out.print("Sentence: ");
		for (int i = 0; i < situation.length(); i++) {
			System.out.print(this.situation.charAt(i) + " ");
		}
		if(this.wrongGuessed.equals(""))
			System.out.println("\nwrong guessed: none");
		else
			System.out.println("\nwrong guessed: "+ this.wrongGuessed);
		String[] human = { "", " |", " O", " |", "/|\\", " |", "/ \\" };
		String[] gallows = { " _________", " |", " |", " |", " |", " |", "/_\\" };
		for (int i = 0; i < gallows.length; i++) {
			System.out.print(gallows[i]);
			if (6 - player.xp >= i)
				System.out.print("\t" + human[i]);
			System.out.println();
		}
	}
}
