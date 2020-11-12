package view.menuBar;

import javax.swing.AbstractButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.menu.AddPlayerListener;
import controller.menu.RemovePlayerButton;
import model.playerStatus.PlayerStatus;
import view.mainUI.ApplicationFrame;
import view.toolBar.ToolBar;

public class MenuBar extends JMenuBar {

	private AbstractButton addPlayerButton;
	private AbstractButton removePlayerButton;

	public MenuBar(ApplicationFrame frame, ToolBar toolBar) {

		JMenu menu = new JMenu("Player");
		this.add(menu);
		this.setVisible(true);

		this.addPlayerButton = new JMenuItem();
		this.addPlayerButton.setText("Add Player");
//		addPlayerButton.setPreferredSize(new Dimension(100, 35));
		this.addPlayerButton.addActionListener(new AddPlayerListener(frame));
		menu.add(this.addPlayerButton);

		this.removePlayerButton = new JMenuItem();
		this.removePlayerButton.setText("Remove Player");
		this.removePlayerButton.setEnabled(false);
//		removePlayerButton.setPreferredSize(new Dimension(100, 35));
		this.removePlayerButton.addActionListener(new RemovePlayerButton(frame));
		menu.add(this.removePlayerButton);
	}

	public void enableButtons(PlayerStatus playerStatus) {
		this.removePlayerButton.setEnabled(playerStatus.isRemoveable() && playerStatus.getRemoveButton());
	}
}
