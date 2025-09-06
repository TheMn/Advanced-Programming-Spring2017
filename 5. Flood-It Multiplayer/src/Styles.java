import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * A utility class that provides static methods for styling Swing components.
 * This class contains inner classes for different parts of the application,
 * each with methods for styling specific components.
 *
 * @author TheMn
 * @version 1.1
 */
public class Styles {

	/**
	 * An inner class that provides styles for the single-player game.
	 *
	 * @author TheMn
	 */
	public static class Single {

		/**
		 * Applies a standard style to a {@link JFrame}.
		 *
		 * @param gameFrame The frame to style.
		 */
		public static void frame(JFrame gameFrame) {
			gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Dimension dimension = gameFrame.getToolkit().getScreenSize();
			gameFrame.setSize(dimension.width / 3, dimension.height / 2);
			gameFrame.setLocation(dimension.width / 2
					- gameFrame.getSize().width / 2, dimension.height / 2
					- gameFrame.getSize().height / 2);
			gameFrame.setResizable(false);;

		}
	}

	/**
	 * An inner class that provides styles for the client application.
	 *
	 * @author TheMn
	 */
	public static class Client {

		/**
		 * Applies a standard style to a message {@link JLabel}.
		 *
		 * @param messeges The label to style.
		 */
		public static void messages(JLabel messeges) {
			messeges.setText("Waiting for opponent to connect...");
			messeges.setFont(new Font("Consolas", Font.PLAIN, 28));
			messeges.setHorizontalAlignment(SwingConstants.CENTER);
			messeges.setForeground(Color.BLACK);
			messeges.setBorder(null);
			messeges.setOpaque(true);
		}
	}

	/**
	 * An inner class that provides styles for the main class of the application.
	 *
	 * @author TheMn
	 */
	public static class MainClass {

		/**
		 * Applies a standard style to a {@link JFrame}.
		 *
		 * @param firstFrame The frame to style.
		 */
		public static void frame(JFrame firstFrame) {
			firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Dimension dimension = firstFrame.getToolkit().getScreenSize();
			firstFrame.setSize(dimension.width / 2, dimension.height / 2);
			firstFrame.setLocation(dimension.width / 2
					- firstFrame.getSize().width / 2, dimension.height / 2
					- firstFrame.getSize().height / 2);
			firstFrame.getContentPane().setLayout(new GridLayout(1, 2, 3, 3));
			firstFrame.setResizable(false);
		}

		/**
		 * Applies a standard style to a header {@link JLabel}.
		 *
		 * @param headerLabel The label to style.
		 * @param type        The font style (e.g., {@link Font#PLAIN}).
		 * @param size        The font size.
		 */
		public static void header(JLabel headerLabel, int type, int size) {
			headerLabel.setFont(new Font("Times New Roman", type, size));
			headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
			headerLabel.setBackground(Color.BLACK);
			headerLabel.setForeground(Color.WHITE);
		}

		/**
		 * Applies a standard style to a {@link JTextField}.
		 *
		 * @param txtField The text field to style.
		 * @param type     The font style (e.g., {@link Font#PLAIN}).
		 * @param size     The font size.
		 */
		public static void txtField(JTextField txtField, int type, int size) {
			txtField.setFont(new Font("Consolas", type, size));
			txtField.setHorizontalAlignment(SwingConstants.CENTER);
			txtField.setBackground(Color.DARK_GRAY);
			txtField.setForeground(Color.PINK);
			txtField.setBorder(null);
		}

		/**
		 * Applies a standard style to a {@link JButton}.
		 *
		 * @param button The button to style.
		 * @param type   The font style (e.g., {@link Font#PLAIN}).
		 * @param size   The font size.
		 */
		public static void button(JButton button, int type, int size) {
			button.setFont(new Font("Times New Roman", type, size));
			button.setHorizontalAlignment(SwingConstants.CENTER);
			button.setBackground(Color.BLACK);
			button.setForeground(Color.WHITE);
			button.setBorder(null);
			button.setOpaque(true);
		}
	}

	/**
	 * An inner class that provides styles for the welcome screen.
	 *
	 * @author TheMn
	 */
	public static class Welcome {

		/**
		 * Applies a standard style to a welcome {@link JFrame}.
		 *
		 * @param welcomeFrame The frame to style.
		 */
		public static void frame(JFrame welcomeFrame) {
			welcomeFrame.setTitle("Welcome");
			welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			welcomeFrame.setBackground(Color.black);
			Dimension dim = welcomeFrame.getToolkit().getScreenSize();
			welcomeFrame.setSize(dim.width / 2, dim.height / 3);
			welcomeFrame.setLocation(dim.width / 2
					- welcomeFrame.getSize().width / 2, dim.height / 2
					- welcomeFrame.getSize().height / 2);
		}

		/**
		 * Applies a standard style to a {@link JButton}.
		 *
		 * @param button The button to style.
		 */
		public static void button(JButton button) {
			button.setForeground(Color.WHITE);
			button.setBackground(Color.BLACK);
			button.setOpaque(true);
			button.setBorder(null);
			button.setFont(new Font("Times New Roman", Font.PLAIN, 46));
		}

		/**
		 * Applies a standard style to a {@link JTextField}.
		 *
		 * @param textField The text field to style.
		 */
		public static void txtField(JTextField textField) {
			textField.setFont(new Font("Consolas", Font.PLAIN, 28));
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setBackground(Color.DARK_GRAY);
			textField.setForeground(Color.PINK);
			textField.setBorder(null);
		}
	}
}
