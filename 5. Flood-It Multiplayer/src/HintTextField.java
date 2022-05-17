import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

/**
 * HintTextField is the child of JTextField that will show a hint
 * message when focus lost and it is ready to accept texts when focus gained.
 * 
 * @author TheMn
 */
class HintTextField extends JTextField implements FocusListener {

	private static final long serialVersionUID = 7107332898216289633L;
	private final String hint;
	private boolean showingHint;

	/**
	 * Creates the new HintTextField
	 * 
	 * @param hint
	 *            the String will show when the focus is lost
	 */
	public HintTextField(final String hint) {
		super(hint);
		this.hint = hint;
		this.showingHint = true;
		super.addFocusListener(this);
	}

	public void focusGained(FocusEvent e) {
		if (this.getText().isEmpty()) {
			super.setText("");
			showingHint = false;
		}
	}

	public void focusLost(FocusEvent e) {
		if (this.getText().isEmpty()) {
			super.setText(hint);
			showingHint = true;
		}
	}

	@Override
	public String getText() {
		return showingHint ? new JTextField().getText() : super.getText();
	}

	@Override
	public void setText(String str) {
		if (str.equals("")) {
			super.setText(hint);
			this.showingHint = true;
			super.addFocusListener(this);
		} else
			super.setText(str);
	}
}