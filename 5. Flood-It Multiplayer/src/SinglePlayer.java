import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A Single player version of Floodit.
 * 
 * @author TheMn
 */
class SinglePlayer {

	private JButton[][] coloredButtons;
	private boolean checked[][];
	private Color[] colors;
	private Random rand;
	private int n, m, c;

	private GameTimer timer;
	public JFrame gameFrame = new JFrame();
	private JPanel gameBoard = new JPanel();

	/**
	 * Inner class Welcome is a JFrame that sets value of n, m and c for new
	 * game.
	 * 
	 * @author TheMn
	 */
	public class Welcome extends JFrame {

		private static final long serialVersionUID = 2337797882545738959L;
		private HintTextField[] txts;
		private JButton button;

		/**
		 * Create the frame and set values.
		 */
		Welcome() {
			timer = new GameTimer();
			gameFrame.add(timer, BorderLayout.NORTH);

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
				 * Send data to FloodIt game frame if everything was OK.
				 */
				public void actionPerformed(ActionEvent arg0) {

					if (checkTexts()) {

						setVisible(false);
						dispose();
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
			System.out.println("yes");
		}

		/**
		 * This method will check all textFields to be as we want.
		 * 
		 * @return boolean if everything was OK
		 */
		private boolean checkTexts() {
			boolean isOk = true;

			// Check if any textField is empty.
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

			// Check the value of number fields.
			try {
				for (int i = 0; i < 3; i++) {
					if (Integer.parseInt(txts[i].getText()) < 2) {
						isOk = false;
						JOptionPane.showMessageDialog(null,
								"The numbers should be greater than two!");
						break;
					}
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
	 * This method will create the buttons of gameField.
	 */
	private void createGameField() {
		gameBoard.setLayout(new GridLayout(n, m, 0, 0));

		colors = new Color[c];
		rand = new Random();
		for (int i = 0; i < colors.length; i++) {
			colors[i] = new Color(rand.nextInt(0xFFFFFF));
		}

		coloredButtons = new JButton[n][m];
		checked = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				checked[i][j] = false;
				coloredButtons[i][j] = new JButton(" ");
				coloredButtons[i][j].setBackground(getRandomColor());
				coloredButtons[i][j].setOpaque(true);
				coloredButtons[i][j].setBorderPainted(false);
				gameBoard.add(coloredButtons[i][j]);
				final int row = i, column = j;
				/**
				 * Update gameField when clicked on the button.
				 */
				coloredButtons[row][column]
						.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								if (coloredButtons[row][column].getBackground()
										.equals(coloredButtons[0][0]
												.getBackground())) {
									JOptionPane
											.showMessageDialog(null,
													"You can't select your current color");
								}
								updateGround(row, column);
								for (int k = 0; k < n; k++) {
									for (int l = 0; l < m; l++) {
										checked[k][l] = false;
									}
								}
								if (allForOne()) {
									double score = finalScore();
									JOptionPane.showMessageDialog(null,
											"your score: " + score);
									if (wantsToPlayAgain()) {
										SinglePlayer newGame = new SinglePlayer();
										newGame.new Welcome();
									}
								}
							}
						});
			}
		}

		Styles.Single.frame(gameFrame);
		gameFrame.add(gameBoard, BorderLayout.CENTER);
		gameFrame.setVisible(true);
	}

	public double finalScore() {
		return n * m * 100.0 / Math.pow(timer.getTime(), 0.33);
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
	 */
	private void updateGround(int x, int y) {
		Color clicked = coloredButtons[x][y].getBackground();
		String[] homeBase = connected(0, 0,
				coloredButtons[0][0].getBackground()).split(" ");

		for (int i = 0; i < homeBase.length; i++) {
			if (!homeBase[i].isEmpty())
				coloredButtons[Integer.parseInt(homeBase[i].substring(0,
						homeBase[i].indexOf("*")))][Integer
						.parseInt(homeBase[i].substring(homeBase[i]
								.indexOf("*") + 1))].setBackground(clicked);
		}
	}

	/**
	 * Get a random color to set buttons background.
	 * 
	 * @return {@link Color}
	 */
	private Color getRandomColor() {
		return colors[rand.nextInt(colors.length)];
	}

	/**
	 * 
	 * Returns whether the current state of the board is such that one of the
	 * players is a winner.
	 * @return {@link Boolean}
	 */
	public boolean allForOne() {
		Color tmp = coloredButtons[0][0].getBackground();
		boolean allOfOneColor = true;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!tmp.equals(coloredButtons[i][j].getBackground()))
					allOfOneColor = false;
			}
		}
		return allOfOneColor;
	}

	/**
	 * This method will check if the player wants to play again or not.
	 * 
	 * @return {@link Boolean}
	 */
	private boolean wantsToPlayAgain() {
		int response = JOptionPane.showConfirmDialog(gameFrame,
				"Want to play again?", "Floodit is fun!",
				JOptionPane.YES_NO_OPTION);
		gameFrame.setVisible(false);
		gameFrame.dispose();
		return response == JOptionPane.YES_OPTION;
	}
}