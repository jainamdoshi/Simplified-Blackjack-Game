package controller.toolBar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.interfaces.Player;
import view.mainUI.ApplicationFrame;

public class BetButtonListener extends MouseAdapter {

	private ApplicationFrame frame;

	public BetButtonListener(ApplicationFrame frame) {
		this.frame = frame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (((AbstractButton) e.getSource()).isEnabled()) {
			Player player = this.frame.getToolBar().getSelectedItem();
			JTextField points = new JTextField();

			Object[] message = { String.format("How much do you want to be for %s", player.getPlayerName()), points };

			int returnValue = JOptionPane.showConfirmDialog(frame, message, "Bet", JOptionPane.OK_CANCEL_OPTION);

			if (returnValue == JOptionPane.OK_OPTION) {
				
				if (points.getText() != null && points.getText().matches("[0-9]+")) {
					int bet = Integer.parseInt(points.getText());
	
					if (bet > 0 && bet <= player.getPoints()) {
	
						this.frame.getGameEngine().placeBet(player, bet);
						this.frame.playerBet(player);
					} else
						this.frame.showMessage(String.format("Please set a valid bet for %s", player.getPlayerName()));
				} else
					this.frame.showMessage(String.format("Please set a valid bet for %s", player.getPlayerName()));
			}
		}
	}

}
