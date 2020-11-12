package controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.mainUI.ApplicationFrame;

public class AddPlayerListener implements ActionListener {

	private ApplicationFrame frame;

	public AddPlayerListener(ApplicationFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// https://stackoverflow.com/questions/6555040/multiple-input-in-joptionpane-showinputdialog/6555051

		JTextField id = new JTextField();
		JTextField name = new JTextField();
		JTextField initialPoints = new JTextField();

		Object[] message = { "ID:", id, "Name:", name, "Points:", initialPoints };

		int returnValue = JOptionPane.showConfirmDialog(frame, message, "Add Player", JOptionPane.OK_CANCEL_OPTION);

		if (returnValue == JOptionPane.OK_OPTION) {

			if (id != null && name != null && initialPoints != null && initialPoints.getText().matches("[0-9]+")) {
				GameEngine gameEngine = this.frame.getGameEngine();
				
				int points = Integer.parseInt(initialPoints.getText());
				if (points > 0) {
					Player newPlayer = new SimplePlayer(id.getText(), name.getText(), points);
					Player oldPlayer = gameEngine.getPlayer(id.getText());
					if (oldPlayer == null) {
	
						this.frame.addPlayer(newPlayer);
	
					} else {
						this.frame.addPlayer(newPlayer, oldPlayer);
					}
	
					gameEngine.addPlayer(newPlayer);
				} else 
					this.frame.showMessage("Please enter valid details to add a player");
			} else
				this.frame.showMessage("Please enter valid details to add a player");
		}
	}

}
