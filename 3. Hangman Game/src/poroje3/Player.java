package poroje3;

/**
 * Represents a player in the Hangman game. This class stores the player's name
 * and their remaining lives (xp).
 *
 * @author Amirhossein Mahdinejad
 * @version 1.1
 * @since 2023-04-12
 */
public class Player {

	protected String name;
	protected float xp;

	/**
	 * Constructs a new Player object with the given name and initializes their lives (xp) to 6.
	 *
	 * @param name The name of the player.
	 */
	public Player(String name){
		this.setName(name);
		this.setXp();
	}

	/**
	 * Sets the name of the player.
	 *
	 * @param name The name of the player.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the initial number of lives (xp) for the player to 6.
	 */
	public void setXp() {
		this.xp = 6;
	}

	/**
	 * Checks if the player is still alive (i.e., has more than 0 lives).
	 *
	 * @return {@code true} if the player is alive, {@code false} otherwise.
	 */
	public boolean isAlive() {
		return (this.xp > 0);
	}
}
