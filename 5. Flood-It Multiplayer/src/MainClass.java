//In The Name of Allah, The Most Beneficent, The Most Merciful
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * The main class for the Flood-It game. This class provides the entry point
 * for the application and allows the user to choose between single-player and
 * multiplayer modes.
 *
 * @author TheMn
 * @version 1.1
 */
public class MainClass {

	private static JFrame firstFrame;
	private static FlooditServer gameServer;
	private static FlooditClient gameClient;

	// TODO add chat server (or run chat on this server)

	/**
	 * The main method, which serves as the entry point for the application.
	 *
	 * @param args Command line arguments (not used).
	 */
	public static void main(String[] args) {
		try {
			new MainClass();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Akhe error avval e besmellah!?");
		} finally {
			System.out.println("~v~first frame showed");
		}
	}

	/**
	 * Constructs a new MainClass object and displays the game mode selection
	 * dialog.
	 *
	 * @throws IOException if an I/O error occurs.
	 */
	public MainClass() throws IOException {
		String[] options = new String[] { "SinglePlayer", "MultiPlayer" };
		int response = JOptionPane.showOptionDialog(null,
				"Hello, what do you want to do?", "Game Mode",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				options, options[0]);

		switch (response) {
		case 0:
			SinglePlayer newGame = new SinglePlayer();
			newGame.new Welcome();
			// TODO edit singlePlayer GUI
			break;

		case 1:
			firstFrame = new JFrame();
			Styles.MainClass.frame(firstFrame);
			firstFrame.add(new MainClass.ServerMaker());
			firstFrame.add(new MainClass.ClientMaker());
			firstFrame.setTitle(options[response]);
			firstFrame.setVisible(true);
			break;

		default:
			firstFrame.setVisible(false);
			firstFrame.dispose();
			break;
		}

	}

	/**
	 * An inner class that creates a panel for joining a multiplayer game as a
	 * client.
	 *
	 * @author TheMn
	 */
	static class ClientMaker extends JPanel {

		private static final long serialVersionUID = -5206045100238173217L;
		JTextField serverAddress = new JTextField("localhost:1973");
		JButton join = new JButton("Click here to Join server");

		/**
		 * Constructs a new ClientMaker panel.
		 */
		ClientMaker() {
			setLayout(new GridLayout(4, 1, 0, 3));
			setBackground(Color.BLACK);

			JLabel clientH2 = new JLabel("Enter server address here:");
			Styles.MainClass.header(clientH2, Font.PLAIN, 30);
			add(clientH2, SwingConstants.CENTER);

			JLabel clientH1 = new JLabel("Join a server");
			Styles.MainClass.header(clientH1, Font.BOLD, 46);
			add(clientH1, SwingConstants.CENTER);

			Styles.MainClass.txtField(serverAddress, Font.ITALIC, 30);
			add(serverAddress);

			Styles.MainClass.button(join, Font.ITALIC, 34);
			add(join);

			/**
			 * When click on join button, data will send.
			 */
			join.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (!serverAddress.getText().isEmpty()) {
						try {
							gameClient = new FlooditClient(serverAddress
									.getText());
							Thread clientThread = new Thread(gameClient);
							clientThread.start();
							firstFrame.dispose();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null,
									"there is no server");
							e.printStackTrace();
						}
						firstFrame.dispose();
					}
				}
			});
		}

	}

	/**
	 * An inner class that creates a panel for hosting a multiplayer game as a
	 * server.
	 *
	 * @author TheMn
	 */
	class ServerMaker extends JPanel {

		private static final long serialVersionUID = 6836373692949071264L;
		JTextField portNumber = new JTextField("1973");
		JButton host = new JButton("Click here to Host server");

		/**
		 * Constructs a new ServerMaker panel.
		 */
		ServerMaker() {
			setLayout(new GridLayout(4, 1, 0, 3));
			setBackground(Color.BLACK);

			JLabel serverH2 = new JLabel("Enter port number to host on:");
			Styles.MainClass.header(serverH2, Font.PLAIN, 30);
			add(serverH2, SwingConstants.CENTER);

			JLabel serverH1 = new JLabel("Host a server");
			Styles.MainClass.header(serverH1, Font.BOLD, 46);
			add(serverH1, SwingConstants.CENTER);

			Styles.MainClass.txtField(portNumber, Font.ITALIC, 30);
			add(portNumber);

			Styles.MainClass.button(host, Font.ITALIC, 34);
			add(host);

			/**
			 * When click on host button, data will send.
			 */
			host.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						gameServer = new FlooditServer(Integer
								.parseInt(portNumber.getText()));
						Thread serverThread = new Thread(gameServer);
						serverThread.start();
						firstFrame.dispose();

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"A Server is Running on This Port!");
						e.printStackTrace();
					}
					// -TODO- add new server page to kick users

				}
			});
		}
	}

}
