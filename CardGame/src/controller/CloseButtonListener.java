package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class CloseButtonListener implements ActionListener {

	private JDialog dialog;

	public CloseButtonListener(JDialog dialog) {
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dialog.setVisible(false);

	}

}
