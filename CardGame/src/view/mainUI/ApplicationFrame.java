package view.mainUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.mainUI.FrameResizeListener;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.playerStatus.PlayerStatus;
import view.cardPanel.CardPanel;
import view.menuBar.MenuBar;
import view.statusBar.StatusBar;
import view.summary.SummaryPanel;
import view.summary.table.TableModel;
import view.toolBar.ToolBar;

public class ApplicationFrame extends JFrame {

	private GameEngine gameEngine;
	private MessageDialog messageDialog;
	private StatusBar statusBar;
	private ToolBar toolBar;
	private SummaryPanel summaryPanel;
	private CardPanel cardPanel;
	private MenuBar menuBar;
	private JPanel holderPanel;
	public Player house;

	private Map<Player, PlayerStatus> playerStatuses;

	public ApplicationFrame(String title) {
		super(title);
		this.messageDialog = new MessageDialog(this);
		this.playerStatuses = new HashMap<Player, PlayerStatus>();
		this.house = new SimplePlayer("0", "House", 0);
		PlayerStatus houseStatus = new PlayerStatus(this.house, false);
		houseStatus.setBetable(false);
		this.playerStatuses.put(this.house, houseStatus);
	}

	public void initializeFrame(int width, int height) {

		final int STARTING_X = 200;
		final int STARTING_Y = 100;

		this.setBounds(STARTING_X, STARTING_Y, width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setMinimumSize(new Dimension((int) dimension.getWidth() / 2, (int) dimension.getHeight() / 2));

		this.setLayout(new BorderLayout());
		this.addComponentListener(new FrameResizeListener(this));

		this.holderPanel = new JPanel();

		this.toolBar = new ToolBar(this);
		this.menuBar = new MenuBar(this, this.toolBar);
		this.cardPanel = new CardPanel(this);
		this.summaryPanel = new SummaryPanel(this);
		this.statusBar = new StatusBar();

		this.setJMenuBar(this.menuBar);
		this.add(this.toolBar, BorderLayout.NORTH);
		this.add(this.statusBar, BorderLayout.SOUTH);

		this.holderPanel.setLayout(new BorderLayout());
		this.add(this.holderPanel, BorderLayout.CENTER);
		this.holderPanel.add(this.cardPanel, BorderLayout.CENTER);
		this.holderPanel.add(this.summaryPanel, BorderLayout.SOUTH);

	}

	public void showMessage(String string) {

		this.messageDialog.setText(string);
		this.messageDialog.setVisible(true);

	}

	
	public void addPlayer(Player player) {
		PlayerStatus playerStatus = new PlayerStatus(player, true);
		this.getPlayerStatuses().put(player, playerStatus);
		this.addingPlayer(player);
	}
	
	public void addPlayer(Player newPlayer, Player oldPlayer) {

		this.playerStatuses.put(newPlayer, this.playerStatuses.get(oldPlayer));
		this.playerStatuses.get(newPlayer).setBetable(true);
		this.toolBar.removePlayer(oldPlayer);
		this.summaryPanel.removePlayer(oldPlayer);
		this.addingPlayer(newPlayer);
	}
	
	private void addingPlayer(Player player) {
		this.toolBar.addPlayer(player);
		this.summaryPanel.addPlayer(player);
		this.statusBar.setText(String.format("%s is added to the game", player.getPlayerName()));
	}

	public void removePlayer(Player player) {

		this.playerStatuses.remove(player);
		this.toolBar.removePlayer();
		this.summaryPanel.removePlayer(player);
		this.statusBar.setText(String.format("%s is removed from the game", player.getPlayerName()));
		this.toolBar.getDealButtonLisenter().mouseClicked(null);
	}

	public void playerBet(Player player) {
		PlayerStatus playerStatus = this.playerStatuses.get(player);
		playerStatus.setBet(true);
		playerStatus.setCanelBetable(true);
		playerStatus.setDealable(true);
		this.validateButtons();
		this.updatePlayer(TableModel.NO_RESULT);
		this.summaryPanel.getTableModel().setPlayerBet(player);
		this.statusBar.setText(String.format("%s has bet %d", player.getPlayerName(), player.getBet()));
	}

	public void updatePlayer(int result) {
		this.summaryPanel.getTableModel().updatePlayerData(result);
	}

	

	@Override
	public void revalidate() {
		this.checkPlayerStatus();
		this.summaryPanel.revalidate();
		super.revalidate();
	}

	

	private void allPlayerDealable(boolean status) {
		for (PlayerStatus playerStatus : this.playerStatuses.values()) {
			if (!playerStatus.getPlayer().equals(this.house))
				playerStatus.setDealable(status);
		}

	}

	private void allPlayerBetable(boolean status) {
		for (PlayerStatus playerStatus : this.playerStatuses.values()) {
			if (!playerStatus.getPlayer().equals(this.house))
				playerStatus.setBetable(status);
		}

	}

	public void dealing(Player player) {
		this.cardPanel.getCardsOfPlayer().put(player, null);
		this.cardPanel.getBurstCardOfPlayer().put(player, null);
		this.allPlayerDealable(false);
		
		if (player.equals(this.house)) {
			this.allPlayerBetable(true);
			this.allPlayerRemoveable(true);
			this.allPlayerDealt(false);
		} else {
			this.playerStatuses.get(player).setBetable(false);
			this.playerStatuses.get(player).setCanelBetable(false);
			this.playerStatuses.get(player).setRemoveButton(false);
			this.playerStatuses.get(player).setDealt(true);
		}
		this.validateButtons();
	}

	private void allPlayerDealt(boolean dealt) {
		
		for (PlayerStatus playerStatus : this.playerStatuses.values()) {
			if (!playerStatus.getPlayer().equals(this.house))
				playerStatus.setDealt(dealt);
		}
	}

	private void allPlayerRemoveable(boolean status) {
		for (PlayerStatus playerStatus : this.playerStatuses.values()) {
			if (!playerStatus.getPlayer().equals(this.house))
				playerStatus.setRemoveButton(status);
		}
	}

	public void validateButtons() {
		this.menuBar.enableButtons(this.playerStatuses.get(this.toolBar.getSelectedItem()));
		this.toolBar.enableButtons(this.playerStatuses.get(this.toolBar.getSelectedItem()));

	}

	public void checkPlayerStatus() {

		Collection<Player> playersToRemove = new ArrayList<Player>();

		for (Player player : this.gameEngine.getAllPlayers()) {

			if (player.getPoints() == 0) {
				this.showMessage(String.format("%s has lost all the points and has been removed from the game",
						player.getPlayerName()));
				playersToRemove.add(player);
				this.toolBar.removePlayer(player);
				this.summaryPanel.removePlayer(player);
			}

		}

		for (Player player : playersToRemove) {
			this.gameEngine.removePlayer(player);
		}
	}

	public void betCanceled() {
		PlayerStatus status = this.playerStatuses.get(this.toolBar.getSelectedItem());
		status.setDealable(false);
		status.setBet(false);
		status.setCanelBetable(false);
		this.validateButtons();
		
	}
	
	public void addBurstCard(Player player, PlayingCard card) {
		this.cardPanel.addBurstCard(player, card);
		this.cardPanel.repaint();
		this.allPlayerDealable(true);
		this.validateButtons();
	}
	
	public GameEngine getGameEngine() {
		return gameEngine;
	}

	public JPanel getStatusBar() {
		return statusBar;
	}
	
	public Collection<Player> getPlayers() {
		return this.gameEngine.getAllPlayers();
	}
	
	public Map<Player, PlayerStatus> getPlayerStatuses() {
		return playerStatuses;
	}
	
	public Player getHouse() {
		return this.house;
	}
	
	public CardPanel getCardPanel() {
		return cardPanel;
	}

	public void setGameEngine(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

}
