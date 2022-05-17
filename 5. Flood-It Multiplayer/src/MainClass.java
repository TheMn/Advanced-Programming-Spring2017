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
 * <h1>Floodit</h1> 
 * <h2>(Advanced Programming Final Project)</h2>
 * Player will enter their name and select the game mode.
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
	 * Floodit main method.
	 * 
	 * @param args default argument
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
	 * Player has two options of singlePlayer or multiPlayer to choose.
	 * 
	 * @throws IOException {@link Throwable}
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
	 * Inner class ClientMaker is a panel that shows what is need to join game
	 * as a client.
	 * 
	 * @author TheMn
	 */
	static class ClientMaker extends JPanel {

		private static final long serialVersionUID = -5206045100238173217L;
		JTextField serverAddress = new JTextField("localhost:1973");
		JButton join = new JButton("Click here to Join server");

		/**
		 * Client will enter the serverAddress and port.
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
	 * Inner class ServerMaker is a panel that shows what is need to start a
	 * server.
	 * 
	 * @author TheMn
	 */
	class ServerMaker extends JPanel {

		private static final long serialVersionUID = 6836373692949071264L;
		JTextField portNumber = new JTextField("1973");
		JButton host = new JButton("Click here to Host server");

		/**
		 * Host will enter the port number.
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
