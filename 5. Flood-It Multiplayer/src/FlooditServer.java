import java.io.IOException;
import java.net.ServerSocket;

/**
 * A server for a network multi-player Floodit game that strings will send.
 * Server to Client: WELCOME, VALID_MOVE, OTHER_PLAYER_MOVED, VICTORY, DEFEAT,
 * TIE, MESSAGE
 * 
 * @author TheMn
 */
public class FlooditServer implements Runnable {

	private static ServerSocket listener;
	static int PORT;

	/**
	 * implementation of Floodit server.
	 * 
	 * @param portNumber the port number
	 * @throws IOException {@link Throwable}
	 */
	public FlooditServer(int portNumber) throws IOException {
		PORT = portNumber;
		listener = new ServerSocket(PORT);
	}

	/**
	 * A new game will start and the values in welcome page will set up. Then
	 * listener waits for the players.
	 * 
	 * @throws IOException
	 */
	private void initServer() throws IOException {
		System.out.println("Tic Tac Toe Server is Running");
		try {

			while (true) {
				MultiPlayer game = new MultiPlayer();
				game.new Welcome();
				MultiPlayer.Player player1 = game.new Player(listener.accept(),
						'1');
				System.out.println(player1.mark + " joined :)");

				MultiPlayer.Player player2 = game.new Player(listener.accept(),
						'2');
				System.out.println(player2.mark + " joined :)");

				player1.setOpponent(player2);
				player2.setOpponent(player1);
				game.currentPlayer = player1;
				player1.start();
				player2.start();

				// TODO what the hell
				while (true)
					listener.accept();

			}
		} finally {
			listener.close();
		}
	}

	@Override
	public void run() {
		try {
			initServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
