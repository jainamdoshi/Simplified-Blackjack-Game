package controller.toolBar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;

import view.mainUI.ApplicationFrame;
import view.summary.table.TableModel;

public class CanelBetButtonListener extends MouseAdapter {

	private ApplicationFrame frame;

	public CanelBetButtonListener(ApplicationFrame frame) {
		this.frame = frame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (((AbstractButton) e.getSource()).isEnabled()) {
			this.frame.getToolBar().getSelectedItem().resetBet();
			this.frame.updatePlayer(TableModel.NO_RESULT);
			this.frame.betCanceled();
		}
	}

}
