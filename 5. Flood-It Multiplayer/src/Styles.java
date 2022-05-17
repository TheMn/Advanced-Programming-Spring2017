import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Styles {

	/**
	 * All styles of the client page will set here as an inner class.
	 * @author TheMn
	 */
	public static class Single {

		/**
		 * This method will set frame style
		 * @param gameFrame {@link JFrame}
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
	 * All styles of the client page will set here as an inner class.
	 * 
	 * @author TheMn
	 */
	public static class Client {

		/**
		 * This method will set messages style
		 * @param messeges {@link JLabel}
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
	 * All styles of the main page will set here as an inner class.
	 * 
	 * @author TheMn
	 */
	public static class MainClass {

		/**
		 * This method will set frame style
		 * @param firstFrame {@link JFrame}
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
		 * This method will set headers styles
		 * @param headerLabel {@link JLabel}
		 * @param type {@link Integer}
		 * @param size {@link Integer}
		 */
		public static void header(JLabel headerLabel, int type, int size) {
			headerLabel.setFont(new Font("Times New Roman", type, size));
			headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
			headerLabel.setBackground(Color.BLACK);
			headerLabel.setForeground(Color.WHITE);
		}

		/**
		 * This method will set {@link JTextField} styles
		 * @param txtField {@link JTextField}
		 * @param type {@link Integer}
		 * @param size {@link Integer}
		 */
		public static void txtField(JTextField txtField, int type, int size) {
			txtField.setFont(new Font("Consolas", type, size));
			txtField.setHorizontalAlignment(SwingConstants.CENTER);
			txtField.setBackground(Color.DARK_GRAY);
			txtField.setForeground(Color.PINK);
			txtField.setBorder(null);
		}

		/**
		 * This method will set {@link JButton} styles
		 * @param button {@link JButton}
		 * @param type {@link Integer}
		 * @param size {@link Integer}
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
	 * All styles of the welcome page will set here as an inner class.
	 * 
	 * @author TheMn
	 */
	public static class Welcome {

		/**
		 * This method will set Frame styles
		 * 
		 * @param welcomeFrame {@link JFrame}
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
		 * This method will set button styles
		 * 
		 * @param button
		 *            the button to be designed
		 */
		public static void button(JButton button) {
			button.setForeground(Color.WHITE);
			button.setBackground(Color.BLACK);
			button.setOpaque(true);
			button.setBorder(null);
			button.setFont(new Font("Times New Roman", Font.PLAIN, 46));
		}

		/**
		 * This method will set textField styles
		 * 
		 * @param textField
		 *            the textField to be designed
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
