package poroje3;

import java.util.Random;

/**
 * The game class implementation of HangmanGame
 * @author Amirhossein Mahdinejad
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
	 * The game contractor
	 * @param player Player
	 */
	Game(Player player) {
		this.setGoal();
		this.setSituation();
		this.setPlayer(player);
		this.setGuessed();
		this.setTime();
	}

	/**
	 * This method will choose a random sentence to guess
	 */
	private void setGoal() {
		Random random = new Random();
		this.goal = sentences[random.nextInt(sentences.length)];
	}

	/**
	 * This method will set the player of current game
	 * @param player Player
	 */
	private void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * This method will replace all characters with underscores and save spaces as they were
	 */
	private void setSituation() {
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
	 * This method will set whole guessed characters false in starting the game
	 */
	private void setGuessed() {
		for (int i = 0; i < this.guessed.length; i++) {
			this.guessed[i] = false;
		}
		this.wrongGuessed = "";
	}
	
	/**
	 * This method will set Start time of the game
	 */
	private void setTime() {
		this.time = System.currentTimeMillis();
	}
	
	/**
	 * This method will return if input String can be a normal guess or not
	 * @param guess String
	 * @return boolean
	 */
	private boolean canGuess(String guess) {
		char c = guess.charAt(0);
		return (guess.length() == 1 && ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')));
	}

	/**
	 * Heart of this code 
	 * This method will process whole game and do guess
	 * @param guess String
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
	 * This method will update situation of game with guessed characters
	 * @param guessChar char
	 */
	private void updateSituation(char guessChar) {
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
	 * This method will set decimal two digits
	 * @param f float
	 * @return float
	 */
	private float twoDecimalView(float f){
		int x = (int)(f*100);
		return ((float)x)/(float)100;
	}
	
	/**
	 * This method will calculate and return score of player after the game ends
	 * @return float
	 */
	public float finalScore() {
		float surat = 1000*this.player.xp;
		float t = (System.currentTimeMillis() - this.time)/1000;
		float makhraj = (float)(Math.sqrt(Math.log(t)/Math.log(2)));
		return twoDecimalView(surat/makhraj);
	}
	
	/**
	 * This method will show if game is over or no
	 * @return boolean
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
	 * This method will show the state of game consist of gussed characters and score
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
