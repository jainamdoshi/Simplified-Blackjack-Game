package view.cardPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;
import view.cardPanel.card.SuitImageFactory;
import view.mainUI.ApplicationFrame;

public class CardPanel extends JPanel {

	private Map<Player, List<PlayingCard>> cardsOfPlayer;
	private Map<Player, PlayingCard> burstCardOfPlayer;
	private ApplicationFrame frame;
	private SuitImageFactory suitsFactory;

	public CardPanel(ApplicationFrame frame) {
		this.frame = frame;

		this.cardsOfPlayer = new HashMap<Player, List<PlayingCard>>();
		this.burstCardOfPlayer = new HashMap<Player, PlayingCard>();

		this.cardsOfPlayer.put(this.frame.getHouse(), null);
		this.burstCardOfPlayer.put(this.frame.getHouse(), null);
		this.suitsFactory = new SuitImageFactory(this);
	}

	public void addCard(Player player, PlayingCard card) {

		List<PlayingCard> playerCards = this.cardsOfPlayer.get(player);

		if (playerCards == null) {
			playerCards = new ArrayList<PlayingCard>();
			playerCards.add(card);
			this.cardsOfPlayer.put(player, playerCards);
		} else if (playerCards.size() >= 1) {
			playerCards.add(card);
		}
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		int width = this.getWidth();
		int height = this.getHeight();

		List<PlayingCard> cards = this.cardsOfPlayer.get(this.frame.getToolBar().getSelectedItem());
		PlayingCard burstCard = this.burstCardOfPlayer.get(this.frame.getToolBar().getSelectedItem());

		if (cards != null) {

			for (int i = 0; i < cards.size(); i++) {

				PlayingCard card = cards.get(i);
				
				int cardBorderX = (width * i / 6) + (width / 93);
				int cardBorderY = (int) (height / 5.5);
				int cardBorderWidth = (int) ((width / 5.5) - (width / 30));
				int cardBorderHeight = (int) (cardBorderWidth * 1.5);

				if (burstCard != null && card.equals(burstCard)) {
					graphics.setColor(Color.LIGHT_GRAY);
				} else {
					graphics.setColor(Color.WHITE);
				}

				graphics.fillRoundRect(cardBorderX, cardBorderY, cardBorderWidth, cardBorderHeight, 25, 25);

				int value1X = cardBorderX + (cardBorderWidth / 15);
				int value1Y = cardBorderY + (cardBorderHeight / 6) - (cardBorderWidth / 46);
				int value2X = cardBorderX + (cardBorderWidth) - (cardBorderWidth / 6);
				int value2Y = cardBorderY + (cardBorderHeight) - (cardBorderWidth / 21);

				String cardString = this.getSuitString(card.getValue());

				if (card.getSuit() == Suit.DIAMONDS || card.getSuit() == Suit.HEARTS) {
					graphics.setColor(Color.RED);
				} else {
					graphics.setColor(Color.BLACK);
				}

				graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, (int) (Math.sqrt((Math.pow(cardBorderWidth, 2)) + Math.pow(cardBorderHeight, 2)) / 9)));
				graphics.drawString(cardString, value1X, value1Y);
				graphics.drawString(cardString, value2X, value2Y);

				int suitX = (int) (cardBorderX + (cardBorderWidth / 2.7));
				int suitY = (int) (cardBorderY + (cardBorderHeight / 2.35));
				int suitWidth = (int) (cardBorderWidth / 3.5);
				int suitHeight = cardBorderHeight / 5;

				Image image = this.suitsFactory.getImage(card.getSuit());
				graphics.drawImage(image, suitX, suitY, suitWidth, suitHeight, this);

			}
		}

	}

	private String getSuitString(Value value) {
		
		String cardString = null;
		
		switch (value) {

		case ACE:
			cardString = "A";
			break;

		case KING:
			cardString = "K";
			break;

		case QUEEN:
			cardString = "Q";
			break;

		case JACK:
			cardString = "J";
			break;

		case TEN:
			cardString = "T";
			break;

		case NINE:
			cardString = "9";
			break;

		case EIGHT:
			cardString = "8";
			break;
		}
		
		return cardString;
	}

	@Override
	public void revalidate() {
		if (this.frame != null) {
			this.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		}
		super.revalidate();

	}

	public Map<Player, List<PlayingCard>> getCardsOfPlayer() {
		return cardsOfPlayer;
	}

	public void addBurstCard(Player player, PlayingCard card) {
		this.burstCardOfPlayer.put(player, card);

	}

	public Map<Player, PlayingCard> getBurstCardOfPlayer() {
		return burstCardOfPlayer;
	}

}
