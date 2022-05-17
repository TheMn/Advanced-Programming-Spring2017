import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Game is entirely text based that strings will send. Client to Server: MOVE,
 * CHAT, QUIT
 * 
 * @author TheMn
 * 
 */
public class FlooditClient implements Runnable {
	private boolean checked[][];

	private JFrame frame = new JFrame();
	private JPanel topPanel;
	private Profile profile;
	private JLabel messageLabel;
	private JButton[][] coloredButtons;
	private JPanel gameBoard;
	private int[] selected = new int[2];
	int n, m, c;

	char mark;

	private static int PORT;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	/**
	 * Constructs the client by connecting to a server, laying out the GUI and
	 * registering GUI listeners.
	 * 
	 * @param serverAddress the server address
	 * @throws Exception {@link Throwable}
	 */
	public FlooditClient(String serverAddress) throws Exception {

		this.profile = new Profile();

		// TODO lay the GUI out
		PORT = Integer.parseInt(serverAddress.split(":")[1]);
		socket = new Socket(serverAddress.split(":")[0], PORT);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);

		topPanel = new JPanel();
		topPanel.setBorder(null);
		topPanel.setOpaque(true);
		messageLabel = new JLabel();
		Styles.Client.messages(messageLabel);
		topPanel.add(messageLabel, BorderLayout.SOUTH);
		frame.add(topPanel, BorderLayout.NORTH);

	}

	/**
	 * Returns a String includes all locations that are connected to our pair x,
	 * y and they are in Color of selected one.
	 * 
	 * @param x
	 *            Integer
	 * @param y
	 *            Integer
	 * @param col
	 *            Color
	 * @return {@code String}
	 */
	private String connected(int x, int y, Color col) {
		// System.out.println("here for col" + col.toString());
		String res = "";
		if (!checked[x][y] && col.equals(coloredButtons[x][y].getBackground())) {
			checked[x][y] = true;
			res = ((Integer) x).toString() + "*" + ((Integer) y).toString()
					+ " ";
			if (x == n - 1 && y == m - 1) {
				res += connected(x - 1, y, col) + connected(x, y - 1, col);
			} else if (x == 0 && y == 0) {
				res += connected(x + 1, y, col) + connected(x, y + 1, col);
			} else if (x == 0 && y == m - 1) {
				res += connected(x + 1, y, col) + connected(x, y - 1, col);
			} else if (x == n - 1 && y == 0) {
				res += connected(x, y + 1, col) + connected(x - 1, y, col);
			} else if (x == n - 1) {
				res += connected(x, y + 1, col) + connected(x, y - 1, col)
						+ connected(x - 1, y, col);
			} else if (y == m - 1) {
				res += connected(x + 1, y, col) + connected(x - 1, y, col)
						+ connected(x, y - 1, col);
			} else if (y == 0) {
				res += connected(x + 1, y, col) + connected(x - 1, y, col)
						+ connected(x, y + 1, col);
			} else if (x == 0) {
				res += connected(x, y - 1, col) + connected(x, y + 1, col)
						+ connected(x + 1, y, col);
			} else {
				res += connected(x + 1, y, col) + connected(x, y + 1, col)
						+ connected(x - 1, y, col) + connected(x, y - 1, col);
			}
		}
		return res;
	}

	/**
	 * This method calls when player select a button. Then the players base will
	 * be updated.
	 * 
	 * @param x
	 * @param y
	 * @param type
	 */
	private void colorSelect(int x, int y, char type) {
		Color clicked = coloredButtons[x][y].getBackground();
		String[] homeBase = connected(
				(type == '1') ? 0 : n - 1,
				(type == '1') ? 0 : m - 1,
				(type == '1') ? coloredButtons[0][0].getBackground()
						: coloredButtons[n - 1][m - 1].getBackground()).split(
				" ");

		for (int i = 0; i < homeBase.length; i++) {
			if (!homeBase[i].isEmpty())
				coloredButtons[Integer.parseInt(homeBase[i].substring(0,
						homeBase[i].indexOf("*")))][Integer
						.parseInt(homeBase[i].substring(homeBase[i]
								.indexOf("*") + 1))].setBackground(clicked);
		}
	}

	/** 
	 * This method will listen for messages from the server. The first message
	 * will be a "WELCOME" message.
	 * @throws Exception {@link Throwable}
	 */
	public void play() throws Exception {
		String response;
		try {
			response = in.readLine();

			if (response.startsWith("HELLO")) {
				String[] nmc = response.substring(6, response.indexOf("^"))
						.split(",");
				n = Integer.parseInt(nmc[0]);
				m = Integer.parseInt(nmc[1]);
				c = Integer.parseInt(nmc[2]);

				/**
				 * set table
				 */
				System.out.println("ta is ble");
				gameBoard = new JPanel();
				gameBoard.setLayout(new GridLayout(n, m, 0, 0));
				coloredButtons = new JButton[n][m];
				checked = new boolean[n][m];
				String[] squars = response.substring(response.indexOf("^") + 1)
						.split("\\*");

				int k = 0;
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < m; j++) {
						coloredButtons[i][j] = new JButton(" ");

						String[] rgb = squars[k++].split(",");
						final Color thisColor = new Color(
								Integer.parseInt(rgb[0]),
								Integer.parseInt(rgb[1]),
								Integer.parseInt(rgb[2]));
						coloredButtons[i][j].setBackground(thisColor);

						coloredButtons[i][j].setOpaque(true);
						coloredButtons[i][j].setBorderPainted(false);

						final int row = i, column = j;
						/**
						 * The selected button will be checked if is a valid
						 * move or not for select color
						 */
						coloredButtons[row][column]
								.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent arg0) {
										out.println("MOVE " + row + "*"
												+ column);
										System.out.println(row + " :row");
										System.out.println(column + " :col");
										selected[0] = row;
										selected[1] = column;
									}
								});
						gameBoard.add(coloredButtons[i][j]);
					}
				}

				this.frame.add(gameBoard, BorderLayout.CENTER);
				this.frame.setVisible(true);

				/**
				 * set welcome
				 */
				System.out.println("well is come");
				mark = response.charAt(response.lastIndexOf("^") + 1);
				// TODO to delete:
				frame.setTitle("Final Project - Player " + mark);
			}

			while (true) {
				response = in.readLine();
				if (response.startsWith("VALID_MOVE")) {
					messageLabel.setText("Valid move, please wait");

					for (int k = 0; k < n; k++) {
						for (int l = 0; l < m; l++) {
							checked[k][l] = false;
						}
					}
					colorSelect(selected[0], selected[1], mark);

				} else if (response.startsWith("OPPONENT_MOVED")) {

					String location = response.substring(15);

					for (int k = 0; k < n; k++) {
						for (int l = 0; l < m; l++) {
							checked[k][l] = false;
						}
					}
					colorSelect(Integer.parseInt(response.substring(15,
							response.indexOf("*"))), Integer.parseInt(location
							.substring(location.indexOf("*") + 1)),
							(mark == '1') ? '2' : '1');

					messageLabel.setText("Opponent moved, your turn");
				} else if (response.startsWith("VICTORY")) {
					messageLabel.setText("You win");
					break;
				} else if (response.startsWith("DEFEAT")) {
					messageLabel.setText("You lose");
					break;
				} else if (response.startsWith("TIE")) {
					messageLabel.setText("You tied");
					break;
				} else if (response.startsWith("MESSAGE")) {
					String toShow = response.substring(8);
					if (toShow.equals("All players connected")) {
						// TODO playground
						topPanel.add(this.profile.profilePanel());
						topPanel.add(new GameTimer());

					}
					messageLabel.setText(toShow);
				}
			}
			out.println("QUIT");
		} finally {
			socket.close();
		}
	}

	/**
	 * This method will show a showConfirmDialog to user that he wants to keep
	 * playing or no. if both of two clients accept this will be create new
	 * game.
	 * 
	 * @return {@link Boolean}
	 */
	private boolean wantsToPlayAgain() {
		int response = JOptionPane.showConfirmDialog(frame,
				"Want to play again?", "Floodit is fun!",
				JOptionPane.YES_NO_OPTION);
		frame.dispose();
		return response == JOptionPane.YES_OPTION;
	}

	/**
	 * The server is running and clients must be joined.
	 * @throws Exception
	 */
	private void initClient() throws Exception {
		while (true) {
			String serverAddress = "localhost";
			System.out.println(serverAddress);
			this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			Dimension dimension = this.frame.getToolkit().getScreenSize();
			this.frame.setSize(dimension.width / 2, (dimension.height*2) / 3);
			this.frame.setLocation(dimension.width / 2
					- this.frame.getSize().width / 2, dimension.height / 2
					- this.frame.getSize().height / 2);
			this.play();
			if (!this.wantsToPlayAgain()) {
				break;
			}
		}
	}

	/**
	 * Runs the client as an application.
	 */
	@Override
	public void run() {
		try {
			initClient();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}