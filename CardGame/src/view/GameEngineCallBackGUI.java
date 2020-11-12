package view;

import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;
import view.mainUI.ApplicationFrame;
import view.statusBar.StatusBar;
import view.summary.table.TableModel;

public class GameEngineCallBackGUI implements GameEngineCallback {

	private ApplicationFrame frame;

	public GameEngineCallBackGUI(GameEngine gameEngine) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				int frameWidth = 1500;
				int frameHeight = 800;

				frame = new ApplicationFrame("Further Programming Assignment 2");
				frame.setGameEngine(gameEngine);
				frame.initializeFrame(frameWidth, frameHeight);
			}
		});

	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {

		this.changeStatusBar(String.format("Card Dealt to %s .. %s", player.getPlayerName(), card));
		this.frame.getCardPanel().addCard(player, card);
		this.frame.getCardPanel().repaint();
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {

		this.frame.addBurstCard(player, card);
		this.changeStatusBar(String.format("Card Dealt to %s .. %s ... YOU BUSTED!", player.getPlayerName(), card));

	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		this.changeStatusBar(String.format("%s has scored .. %d", player.getPlayerName(), result));
		this.frame.updatePlayer(TableModel.NO_RESULT);

	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		this.frame.getCardPanel().addCard(this.frame.getHouse(), card);
		this.frame.getCardPanel().repaint();
		this.changeStatusBar(String.format("Card Dealt to House .. %s", card));

	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		this.frame.getCardPanel().addBurstCard(this.frame.getHouse(), card);
		this.frame.getCardPanel().repaint();
		this.changeStatusBar(String.format("Card Dealt to House .. %s ... HOUSE BUSTED!", card));

	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		this.frame.updatePlayer(result);
		this.changeStatusBar(String.format("House has scored .. %d", result));
		frame.revalidate();

	}

	private void changeStatusBar(String string) {
		((StatusBar) this.frame.getStatusBar()).setText(string);

	}

}
