import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * A simple timer component that displays the elapsed time since it was started.
 * The timer updates every second.
 *
 * @author TheMn
 * @version 1.1
 */
public class GameTimer extends JPanel {

	private static final long serialVersionUID = -2941011375311949489L;
	final JLabel label = new JLabel("", SwingConstants.CENTER);

	/**
	 * Constructs a new GameTimer. The timer starts immediately upon creation.
	 */
	public GameTimer() {

		final long startTime = System.currentTimeMillis();
		setSize(200, 100);
		setVisible(true);

		label.setFont(label.getFont().deriveFont(25f));
		label.setSize(200, 100);
		add(label);

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			/**
			 * The task that is executed by the timer every second. It updates the
			 * label with the elapsed time.
			 */
			public void run() {
				Long timeCalculated = (System.currentTimeMillis() - startTime) / 1000;
				// System.out.println(timeCalculated);
				label.setText(timeCalculated.toString());
			}
		}, 0, 1000);
	}

	/**
	 * Gets the current elapsed time in seconds.
	 *
	 * @return The elapsed time in seconds, or {@code null} if the time cannot be parsed.
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
