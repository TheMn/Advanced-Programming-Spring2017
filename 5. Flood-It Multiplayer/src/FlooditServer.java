import java.io.IOException;
import java.net.ServerSocket;

/**
 * A server for a networked two-player Flood-It game. The server listens for
 * connections from two clients and then starts a new game. Communication
 * between the server and clients is done via text-based messages.
 *
 * @author TheMn
 * @version 1.1
 */
public class FlooditServer implements Runnable {

	private static ServerSocket listener;
	static int PORT;

	/**
	 * Constructs a new Flood-It server that listens on the specified port.
	 *
	 * @param portNumber The port number for the server to listen on.
	 * @throws IOException if an I/O error occurs when opening the server socket.
	 */
	public FlooditServer(int portNumber) throws IOException {
		PORT = portNumber;
		listener = new ServerSocket(PORT);
	}

	/**
	 * Initializes the server and starts listening for client connections. When two
	 * clients have connected, a new game is started.
	 *
	 * @throws IOException if an I/O error occurs when waiting for a connection.
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

	/**
	 * Runs the server as an application. This method is the entry point for the
	 * server thread.
	 */
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
