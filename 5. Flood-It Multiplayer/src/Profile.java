import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Profile {

	private String name;
	private JLabel pic;
	private double score;
	
	/**
	 * Each player has a profile.
	 */
	public Profile() {
		try {

			setName();

			this.pic = new JLabel();
			ImageIcon defaultIcon = new ImageIcon("Images/defaultPic.png");
			this.pic.setIcon(new ImageIcon(defaultIcon.getImage()
					.getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
			setPic();

			setScore(Ranking.lastRecord(this.name));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error!",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * @return profilePanel the panel that shows profile
	 */
	public JPanel profilePanel() {
		JPanel profilePanel = new JPanel();
		JLabel nameLabel = new JLabel(this.name);
		profilePanel.add(nameLabel, BorderLayout.NORTH);
		profilePanel.add(this.pic, BorderLayout.CENTER);
		return profilePanel;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	private void setName() {
		String name = null;
		while (name == null) {
			name = JOptionPane.showInputDialog("Please enter your name:");
		}
		if (name.isEmpty())
			name = "No name!";
		this.name = name;
	}

	/**
	 * @return the pic
	 */
	public JLabel getPic() {
		return pic;
	}

	/**
	 * @param pic
	 *            the pic to set
	 */
	private void setPic() {

		final JFrame chooseFrame = new JFrame("Input");
		chooseFrame.setAlwaysOnTop(true);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		chooseFrame.setSize(220, 220);
		chooseFrame.setAlwaysOnTop(true);
		chooseFrame.setLocation(dimension.width / 2
				- chooseFrame.getSize().width / 2, dimension.height / 2
				- chooseFrame.getSize().height / 2);
		chooseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JButton picButton = new JButton("choose a picture!");
		picButton.addActionListener(new ActionListener() {
			/**
			 * User can select a picture from his files.
			 */
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				int res = fileChooser.showOpenDialog(null);
				if (res == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						ImageIcon icon = new ImageIcon(ImageIO.read(file));
						pic.setIcon(new ImageIcon(
								icon.getImage().getScaledInstance(120, 120,
										Image.SCALE_SMOOTH)));
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"error in reading file");
					}
				}
			}
		});

		JButton sendButton = new JButton("it's Ok");
		sendButton.addActionListener(new ActionListener() {
			/**
			 * Send data to FloodIt frame if everything was OK
			 */
			public void actionPerformed(ActionEvent arg0) {
				chooseFrame.setVisible(false);
				chooseFrame.dispose();
				
			}
		});

		JPanel picturePanel = new JPanel();
		picturePanel.setSize(120, 120);
		picturePanel.add(pic);

		chooseFrame.add(picButton, BorderLayout.NORTH);
		chooseFrame.add(picturePanel, BorderLayout.CENTER);
		chooseFrame.add(sendButton, BorderLayout.SOUTH);
		chooseFrame.pack();
		chooseFrame.setResizable(false);
		chooseFrame.setVisible(true);
	}

	/**
	 * @return the score
	 */
	public Double getScore() {
		return (Double) score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}

}
