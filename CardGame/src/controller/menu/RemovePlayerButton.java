package controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import model.interfaces.Player;
import view.mainUI.ApplicationFrame;

public class RemovePlayerButton implements ActionListener {

	private ApplicationFrame frame;

	public RemovePlayerButton(ApplicationFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (((AbstractButton) e.getSource()).isEnabled()) {

			Player player = this.frame.getToolBar().getSelectedItem();

			if (this.frame.getGameEngine().removePlayer(player)) {
				this.frame.removePlayer(player);
			}

		}

	}

}
