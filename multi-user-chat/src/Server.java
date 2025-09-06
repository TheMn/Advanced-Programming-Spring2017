import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * A multithreaded chat room server. When a client connects the server requests
 * a screen name, then informs the client of the other clients, and broadcasts
 * the new client's arrival to the other clients.
 *
 */
public class Server {

	/**
	 * The port that the server listens on.
	 */
	private static final int port = 9000;

	/**
	 * The list of all names of clients in the chat room. Maintained so that
	 * there are no duplicates.
	 */
	private static ArrayList<String> names = new ArrayList<>();

	/**
	 * The list of all the print writers for all the clients. This is used to
	 * broadcast messages.
	 */
	private static ArrayList<PrintWriter> writers = new ArrayList<>();

	/**
	 * The appplication main method, which just listens on a port and spawns
	 * handler threads.
     * @param args
     * @throws java.io.IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("the server is running :D");
		ServerSocket listener = new ServerSocket(port);
		try {
			// waiting for clients to connect
			while (true) {
				new Handler(listener.accept()).start();
			}
		} finally {
			listener.close();
		}
	}

	/**
	 * A handler thread class. Handlers are spawned from the listening loop and
	 * are responsible for a dealing with a single client and broadcasting its
	 * messages.
	 */
	private static class Handler extends Thread {
		private String name;
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;

		/**
		 * Constructs a handler thread, squirreling away the socket. All work
		 * is done inside the run method.
		 *
		 * @param socket
		 */
		public Handler(Socket socket) {
			this.socket = socket;
		}

		/**
		 * Services this thread's client by repeatedly requesting a screen name
		 * until a unique one has been submitted, then acknowledges the name and
		 * registers the output stream for the client in a global set, then
		 * repeatedly gets inputs and broadcasts them.
		 */
		public void run() {
			try {
				// Create character streams for the socket.
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);

				// Request a name from this client. Keep requesting until
				// a name is submitted that is not already used. Note that
				// checking for the existence of a name and adding the name
				// must be an atomic operation, otherwise there is a window
				// when two clients might send the same name at the same time.
				while (true) {
					out.println("SUBMIT-NAME");
					name = in.readLine();
					if (!names.contains(name)) {
						names.add(name);
						break;
					}
				}
				writers.add(out);
				sendToAll("JOINED" + name);

				// Accept messages from this client and broadcast them.
				// Stop when the client disconnects.
				while (true) {
					String input = in.readLine();
					if (input == null)
						return;
					sendToAll("MESSAGE" + name + ": " + input);
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			} finally {
				// This client is going down! Remove its name and its print
				// writer from the sets, and close its socket.
				System.out.println(name + " has left");
				sendToAll("LEFT" + name);

				// remove name and writer of this client
				if (name != null)
					names.remove(name);
				if (out != null)
					writers.remove(out);
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println(e.toString());
				}
			}
		}

		/**
		 * @param s
		 */
		// write in all clients pages
		public void sendToAll(String s) {
			for (PrintWriter myWriter : writers)
				myWriter.println(s);
		}
	}
}
