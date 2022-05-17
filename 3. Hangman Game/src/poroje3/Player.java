package poroje3;

/**
 * The Player class implementation of HangmanPlayer
 * @author Amirhossein Mahdinejad
 */
public class Player {
	
	protected String name;
	protected float xp;
	
	/**
	 * The Player contractor
	 * @param name String the player name
	 */
	Player(String name){
		this.setName(name);
		this.setXp();
	}
	
	/**
	 * This method will set String name to player name
	 * @param name String
	 */
	private void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This method will set start point equals to 6
	 */
	private void setXp() {
		this.xp = 6;
	}
	
	/**
	 * This method will return if Player is in game or not
	 * @return boolean that shows player is in game or not
	 */
	public boolean isAlive() {
		return (this.xp > 0);
	}
}
