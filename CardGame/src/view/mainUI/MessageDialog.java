package view.mainUI;

import java.awt.BorderLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import controller.CloseButtonListener;

public class MessageDialog extends JDialog {

	private JLabel text;
	private AbstractButton okButton;

	public static final String NONE_PLAYER_SELECTED_ERROR = "Please select a player";

	public MessageDialog(ApplicationFrame frame) {
		this.text = new JLabel();
		this.okButton = new JButton("OK");

		this.setBounds(frame.getX() + 750, frame.getY() + 350, 450, 150);
		this.setResizable(false);
		this.setModal(true);
		this.setLayout(new BorderLayout());

		this.add(this.text, BorderLayout.CENTER);
		this.add(this.okButton, BorderLayout.SOUTH);

		this.okButton.addActionListener(new CloseButtonListener(this));
	}

	public void setText(String string) {
		this.text.setText(string);
	}
}
