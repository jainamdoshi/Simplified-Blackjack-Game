package controller.toolBar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.mainUI.ApplicationFrame;
import view.summary.table.TableModel;

public class DealButtonListener extends MouseAdapter {

	private ApplicationFrame frame;
	private int dealCount = 0;

	public DealButtonListener(ApplicationFrame frame) {
		this.frame = frame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (e == null || ((AbstractButton) e.getSource()).isEnabled()) {
			
			Player player = this.frame.getToolBar().getSelectedItem();

			if (e == null || player.getBet() > 0) {
				GameEngine gameEngine = this.frame.getGameEngine();
				
				new Thread() {
					@Override
					public void run() {
						
						if (e != null) {
							frame.dealing(player);
							frame.updatePlayer(TableModel.NO_RESULT);
							dealCount++;
							gameEngine.dealPlayer(player, 100);
						}
						if (dealCount != 0 && dealCount >= frame.getToolBar().getPlayerCount()) {
							frame.showMessage("The House is ready to deal!");
							frame.dealing(frame.getHouse());
							frame.getToolBar().getPlayerSelection().setSelectedIndex(0);
							dealCount = 0;
							gameEngine.dealHouse(100);

						}
					}
				}.start();

			} else
				this.frame.showMessage(String.format("Please set a bet before dealing for %s", player.getPlayerName()));
		}
	}

}
