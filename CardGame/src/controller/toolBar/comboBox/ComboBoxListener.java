package controller.toolBar.comboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import view.mainUI.ApplicationFrame;

public class ComboBoxListener implements ActionListener {

	private ApplicationFrame frame;

	public ComboBoxListener(ApplicationFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JPanel cardPanel = this.frame.getCardPanel();

		if (cardPanel != null) {
			cardPanel.repaint();
			this.frame.validateButtons();

		}

	}

}
