import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameTimer extends JPanel {

	private static final long serialVersionUID = -2941011375311949489L;
	final JLabel label = new JLabel("", SwingConstants.CENTER);

	/**
	 * A GameTimer is a Timer that shows the game time running and its starts
	 * when the game starts.
	 */
	GameTimer() {

		final long startTime = System.currentTimeMillis();
		setSize(200, 100);
		setVisible(true);

		label.setFont(label.getFont().deriveFont(25f));
		label.setSize(200, 100);
		add(label);

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			/**
			 * The thread runs in background.
			 */
			public void run() {
				Long timeCalculated = (System.currentTimeMillis() - startTime) / 1000;
				// System.out.println(timeCalculated);
				label.setText(timeCalculated.toString());
			}
		}, 0, 1000);
	}

	/**
	 * This method will return the time of the game.
	 * @return {@link Integer}
	 */
	public Integer getTime() {
		Integer res = null;
		try {
			res = Integer.parseInt(label.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
