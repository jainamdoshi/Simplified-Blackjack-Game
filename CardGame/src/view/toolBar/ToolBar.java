package view.toolBar;

import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

import controller.toolBar.BetButtonListener;
import controller.toolBar.CanelBetButtonListener;
import controller.toolBar.DealButtonListener;
import controller.toolBar.comboBox.ComboBoxListener;
import model.interfaces.Player;
import model.playerStatus.PlayerStatus;
import view.mainUI.ApplicationFrame;
import view.toolBar.comboBox.ComboBoxRenderer;

public class ToolBar extends JToolBar {

	private ApplicationFrame frame;

	private JComboBox<Player> playerSelection;
	private int playersCount = 0;

	private AbstractButton betButton;
	private AbstractButton dealButton;
	private AbstractButton cancelBetButton;
	private MouseListener dealButtonLisenter;

	public ToolBar(ApplicationFrame frame) {
		this.frame = frame;

		this.playerSelection = new JComboBox<Player>();

		ComboBoxRenderer renderer = new ComboBoxRenderer();
		this.playerSelection.setRenderer(renderer);
		this.playerSelection.addActionListener(new ComboBoxListener(this.frame));
		this.playerSelection.addItem(this.frame.getHouse());

		this.add(this.playerSelection);

		this.betButton = new JButton();
		this.betButton.setText("Bet");
		this.betButton.setEnabled(false);
		this.betButton.addMouseListener(new BetButtonListener(this.frame));
		this.add(this.betButton);

		this.cancelBetButton = new JButton();
		this.cancelBetButton.setText("Cancel Bet");
		this.cancelBetButton.setEnabled(false);
		this.cancelBetButton.addMouseListener(new CanelBetButtonListener(this.frame));
		this.add(this.cancelBetButton);

		this.dealButton = new JButton();
		this.dealButton.setText("Deal");
		this.dealButton.setEnabled(false);
		this.dealButtonLisenter = new DealButtonListener(this.frame);
		this.dealButton.addMouseListener(dealButtonLisenter);
		this.add(this.dealButton);

	}

	public void addPlayer(Player player) {

		this.playerSelection.addItem(player);
		this.playersCount++;
		this.playerSelection.setSelectedIndex(this.playersCount);
	}

	public void removePlayer() {

		this.playerSelection.removeItem(this.playerSelection.getSelectedItem());
		this.playersCount--;

	}

	public ApplicationFrame getFrame() {
		return frame;
	}

	public int getPlayerCount() {
		return this.playersCount;
	}

	public JComboBox<Player> getPlayerSelection() {
		return playerSelection;
	}

	public Player getSelectedItem() {
		return (Player) this.playerSelection.getSelectedItem();
	}

	public void enableButtons(PlayerStatus playerStatus) {
		this.betButton.setEnabled(playerStatus.isBetable());
		this.dealButton.setEnabled(playerStatus.isDealable() && !playerStatus.isDealt() && playerStatus.isBet());
		this.cancelBetButton.setEnabled(playerStatus.isCanelBetable());
	}

	public void removePlayer(Player player) {
		this.playerSelection.removeItem(player);
		this.playersCount--;
	}

	public MouseListener getDealButtonLisenter() {
		return dealButtonLisenter;
	}
}
