import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame implements Runnable {

	private static final long serialVersionUID = -3611092341471039022L;
	private static BufferedReader in;
	private static PrintWriter out;
	private static Socket socket;
	final JTextField txtField = new JTextField(40);
	final JTextArea messageArea = new JTextArea(8, 40);

	public Client() {
		// set frame style
		txtField.setFont(new Font("Consolas", Font.PLAIN, 22));
		messageArea.setFont(new Font("Consolas", Font.BOLD, 26));
		txtField.setEditable(false);
		messageArea.setEditable(false);
		getContentPane().add(txtField, "North");
		getContentPane().add(new JScrollPane(messageArea), "Center");
		pack();

		txtField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// when client press ENTER key
				out.println(txtField.getText());
				txtField.setText("");
			}
		});
	}

	// ask for server IP address
	private String serverGetter() {
		return JOptionPane.showInputDialog(this, "Enter server address:",
				"Wellcome", JOptionPane.QUESTION_MESSAGE);
	}

	// ask for client name
	private String nameGetter() {
		return JOptionPane.showInputDialog(this, "Choose a name:",
				"Name Selection", JOptionPane.PLAIN_MESSAGE);
	}

	// create new client page
	public static void main(String[] args) throws IOException {
		Client client = new Client();
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.setVisible(true);
		client.run();
	}

	@Override
	public void run() {
		// set fields
		String serverAddress = serverGetter();
		try {
			socket = new Socket(serverAddress, 9000);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		while (true) {
			// receive clients input
			String line = null;
			try {
				line = in.readLine();
			} catch (IOException e) {
				System.out.println(e.toString());
			}

			// example for text protocol
			if (line.startsWith("SUBMIT-NAME")) {
				String clientName = nameGetter();
				out.println(clientName);
				setTitle(clientName);
			} else if (line.startsWith("JOINED")) {
				messageArea.append(line.substring(6) + " joined the room\n");
				txtField.setEditable(true);
			} else if (line.startsWith("LEFT")) {
				messageArea.append(line.substring(4) + " has left the room\n");
			} else if (line.startsWith("MESSAGE")) {
				messageArea.append(line.substring(7) + "\n");
			}
		}
	}

}
