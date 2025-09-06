import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	// the port that the server listens on
	private static final int port = 9000;

	// all clients in the chat room
	private static ArrayList<String> names = new ArrayList<>();

	// all printWriters for all clients
	private static ArrayList<PrintWriter> writers = new ArrayList<>();

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

	// a handler thread class
	private static class Handler extends Thread {
		private String name;
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;

		// set socket
		public Handler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				// set reader and writer
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);

				// request name from client
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

				// receive messages
				while (true) {
					String input = in.readLine();
					if (input == null)
						return;
					sendToAll("MESSAGE" + name + ": " + input);
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			} finally {
				// connection lost
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

		// write in all clients pages
		public void sendToAll(String s) {
			for (PrintWriter myWriter : writers)
				myWriter.println(s);
		}
	}
}
