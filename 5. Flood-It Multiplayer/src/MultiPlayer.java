
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A two-player game. The main change is instead of passing data between the
 * client and server depends on my protocol.
 * 
 * @author TheMn
 */
class MultiPlayer {

	private JButton[][] coloredButtons;
	private boolean checked[][];
	private Color[] colors;
	private Random rand;
	private int n, m, c;

	/**
	 * Inner class Welcome is a JFrame that sets value of n, m and c for new
	 * game.
	 * 
	 * @author TheMn
	 */
	public class Welcome extends JFrame {

		private static final long serialVersionUID = 5257594021915572438L;
		private HintTextField[] txts;
		private JButton button;

		/**
		 * Create the frame and set values.
		 */
		Welcome() {
			System.out.println("NEW WELCOME (FIRST PLAYER WILL SET VALUES)");
			Styles.Welcome.frame(this);

			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(3, 1, 0, 0));
			panel.setBorder(null);

			txts = new HintTextField[3];

			final String[] hints = { "~~> Enter Number of Columns Here <~~",
					"~~> Enter Number of Rows Here <~~",
					"~~> Enter Number of Colors Here <~~" };
			for (int i = 0; i < 3; i++) {
				txts[i] = new HintTextField(hints[i]);
				Styles.Welcome.txtField(txts[i]);
				panel.add(txts[i]);
			}

			button = new JButton("Set the Values then Click Here!");
			Styles.Welcome.button(button);
			getContentPane().add(button, BorderLayout.NORTH);

			button.addActionListener(new ActionListener() {
				/**
				 * Send data to FloodIt game frame if everything was OK
				 */
				public void actionPerformed(ActionEvent arg0) {

					if (checkTexts()) {

						setVisible(false);
						dispose(); // Close this frame...
						n = Integer.parseInt(txts[0].getText());
						m = Integer.parseInt(txts[1].getText());
						c = Integer.parseInt(txts[2].getText());

						System.out.println("OK! NOW n=" + txts[0].getText()
								+ " m=" + txts[1].getText() + " c="
								+ txts[2].getText());

						createGameField();

					} else
						for (int i = 0; i < 3; i++)
							txts[i].setText("");

				}
			});

			setVisible(true);
		}

		/**
		 * This method will check all textFields to be as we want
		 * 
		 * @return boolean if everything was OK
		 */
		private boolean checkTexts() {
			boolean isOk = true;

			/**
			 * Check if any textField is empty
			 */
			boolean empty = false;
			for (int i = 0; i < 3; i++) {
				if (txts[i].getText().equals(new JTextField().getText()))
					empty = true;
			}
			if (empty) {
				isOk = false;
				JOptionPane.showMessageDialog(null,
						"You should fill all items!");
			}

			/**
			 * Check the value of number fields
			 */
			try {
				for (int i = 0; i < 3; i++) {
					if (Integer.parseInt(txts[i].getText()) < 2) {
						isOk = false;
						JOptionPane.showMessageDialog(null,
								"The numbers should be greater than two!");
						break;
					}
				}
				if (Integer.parseInt(txts[2].getText()) <= 2) {
					isOk = false;
					JOptionPane.showMessageDialog(null,
							"We can't have 2 colors when we have 2 players!");
				}
				if (Integer.parseInt(txts[2].getText()) > Integer
						.parseInt(txts[1].getText())
						* Integer.parseInt(txts[0].getText())) {
					isOk = false;
					JOptionPane.showMessageDialog(null,
							"Number of colors can not be more than buttons!");
				}
			} catch (Exception e) {
				isOk = false;
				JOptionPane.showMessageDialog(null,
						"Columns and Rows and Colors should be numbers!");
			}

			return isOk;
		}

	}

	/**
	 * This method will creates the buttons of gameField in the server. It can
	 * be shown in a JFrame.
	 */
	private void createGameField() {

		colors = new Color[c];
		rand = new Random();
		for (int i = 0; i < colors.length; i++) {
			colors[i] = new Color(rand.nextInt(0xFFFFFF));
		}

		// Set buttons colors from our random colors.
		coloredButtons = new JButton[n][m];
		checked = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				checked[i][j] = false;
				coloredButtons[i][j] = new JButton(" ");
				coloredButtons[i][j].setBackground(getRandomColor());
				coloredButtons[i][j].setOpaque(true);
				coloredButtons[i][j].setBorderPainted(false);

				final int row = i, column = j;
				/**
				 * Update gameField when clicked on the button.
				 */
				coloredButtons[row][column]
						.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								colorSelect(row, column, '1');
								for (int k = 0; k < n; k++) {
									for (int l = 0; l < m; l++) {
										checked[k][l] = false;
									}
								}
							}
						});
			}
		}

		// Checks if the players colors should not be like each other.
		while (true)
			if (coloredButtons[0][0].getBackground().equals(
					coloredButtons[n - 1][m - 1].getBackground())) {
				coloredButtons[0][0].setBackground(getRandomColor());
			} else
				break;
	}

	/**
	 * Returns a String includes all locations that are connected to our pair x,
	 * y and they are in Color of selected one.
	 * 
	 * @param x
	 *            {@link Integer}
	 * @param y
	 *            {@link Integer}
	 * @param col
	 *            {@link Color}
	 * @return {@link String}
	 */
	private String connected(int x, int y, Color col) {
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
	 *            {@link Integer}
	 * @param y
	 *            {@link Integer}
	 * @param type
	 *            {@link Character}
	 */
	private void colorSelect(int x, int y, char type) {
		Color clicked = coloredButtons[x][y].getBackground();
		String[] homeBase = connected(
				(type == '1') ? 0 : n - 1,
				(type == '1') ? 0 : m - 1,
				(type == '1') ? coloredButtons[0][0].getBackground()
						: coloredButtons[n - 1][m - 1].getBackground()).split(
				" ");

		// Update homeBase color to selected buttons background.
		for (int i = 0; i < homeBase.length; i++) {
			if (!homeBase[i].isEmpty())
				coloredButtons[Integer.parseInt(homeBase[i].substring(0,
						homeBase[i].indexOf("*")))][Integer
						.parseInt(homeBase[i].substring(homeBase[i]
								.indexOf("*") + 1))].setBackground(clicked);
		}
	}

	/**
	 * Get a random color to set buttons background witch is one of random
	 * colors in array with length of c
	 * 
	 * @return {@link Color}
	 */
	private Color getRandomColor() {
		return colors[rand.nextInt(colors.length)];
	}

	// The current player.
	Player currentPlayer;

	/**
	 * Returns whether the current state of the board is such that one of the
	 * players is a winner.
	 * @return {@link Boolean} hasWinner
	 */
	public boolean hasWinner() {
		boolean hasWinner = true;
		Color friend = coloredButtons[0][0].getBackground();
		Color enemy = coloredButtons[n - 1][m - 1].getBackground();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!coloredButtons[i][j].getBackground().equals(friend)
						&& !coloredButtons[i][j].getBackground().equals(enemy)) {
					hasWinner = false;
				}
			}
		}
		return hasWinner;
	}

	/**
	 * This method will check if the game is tied or not.
	 * 
	 * @return {@link Boolean}
	 */
	public boolean areLevel() {
		// TODO number of player1 colors and his opponent like each other and
		// their score should be zero
		return false;
	}


	/** 
	 * This method checks to see if the move is legal, the game state is updated
	 * and the other player is notified of the move.
	 * @param i loc x
	 * @param j loc y
	 * @param player user
	 * @return {@link Boolean}
	 */
	public synchronized boolean legalMove(int i, int j, Player player) {
		Color selectedColor = coloredButtons[i][j].getBackground();
		if ((player == currentPlayer)
				&& (!selectedColor.equals(coloredButtons[0][0].getBackground()))
				&& (!selectedColor.equals(coloredButtons[n - 1][m - 1]
						.getBackground()))) {

			colorSelect(i, j, player.mark);
			for (int k = 0; k < n; k++) {
				for (int l = 0; l < m; l++) {
					checked[k][l] = false;
				}
			}

			currentPlayer = currentPlayer.opponent;
			currentPlayer.otherPlayerMoved(i, j);
			return true;
		}
		return false;
	}

	/**
	 * A Player is identified by a character mark which is either '1' or '2'.
	 */
	class Player extends Thread {
		char mark;
		Player opponent;
		Socket socket;
		BufferedReader input;
		PrintWriter output;

		/**
		 * 
		 * Constructs a handler thread for a given socket.
		 * @param socket serverSocket
		 * @param mark the identifier
		 */
		public Player(Socket socket, char mark) {
			this.socket = socket;
			this.mark = mark;
			try {
				input = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				output = new PrintWriter(socket.getOutputStream(), true);

				// Send the values of table.
				output.print("HELLO " + n + "," + m + "," + c + "^");

				for (int i = 0; i < n; i++) {
					for (int j = 0; j < m; j++) {
						String[] rgb = new String[3];
						String s = coloredButtons[i][j]
								.getBackground()
								.toString()
								.substring(
										coloredButtons[i][j].getBackground()
												.toString().indexOf("["));
						String[] res = s.split("=|\\,|\\]");
						rgb[0] = res[1];
						rgb[1] = res[3];
						rgb[2] = res[5];
						for (int k = 0; k < rgb.length; k++) {
							// Send each button color.
							output.print(rgb[k] + ",");
						}
						output.print("*");
					}
				}

				// Send player mark.
				output.print("^");
				output.print(mark);

				output.println("MESSAGE Waiting for opponent to connect");
			} catch (IOException e) {
				System.out.println("PLAYER DIED: " + e.getMessage());
			}
		}

		/**
		 * Accepts notification of who the opponent is.
		 * @param opponent Player
		 */
		public void setOpponent(Player opponent) {
			this.opponent = opponent;
		}

		/**
		 * 
		 * Handles the otherPlayerMoved message.
		 * @param i location x
		 * @param j location y
		 */
		public void otherPlayerMoved(int i, int j) {
			output.println("OPPONENT_MOVED " + i + "*" + j);
			output.println(hasWinner() ? "DEFEAT" : areLevel() ? "TIE" : "");
		}

		/**
		 * The run method of this thread.
		 */
		public void run() {
			try {

				// The thread is only started after everyone connects.
				output.println("MESSAGE All players connected");

				// Tell the first player that it is his turn.
				if (mark == '1') {
					output.println("MESSAGE Your move");
				}

				// Repeatedly get commands from the client and process them.
				while (true) {
					String command = input.readLine();
					if (command.startsWith("MOVE")) {
						try {
							int[] xy = {
									Integer.parseInt(command.substring(5,
											command.indexOf("*"))),
									Integer.parseInt(command.substring(command
											.indexOf("*") + 1)) };

							if (legalMove(xy[0], xy[1], this)) {
								output.println("VALID_MOVE");
								output.println(hasWinner() ? "VICTORY"
										: areLevel() ? "TIE" : "");
							} else {
								output.println("MESSAGE ?");
							}

						} catch (Exception e) {
							JOptionPane.showMessageDialog(null,
									"the hell when select one");
						}
					} else if (command.startsWith("QUIT")) {
						return;
					}
				}
			} catch (IOException e) {
				System.out.println("PLAYER DIED: " + e.getMessage());
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
	}
}