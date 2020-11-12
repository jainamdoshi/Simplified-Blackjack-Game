package model;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard {

	private final Suit SUIT;
	private final Value VALUE;
	private int score;

	public PlayingCardImpl(Suit suit, Value value) {
		this.SUIT = suit;
		this.VALUE = value;
		
	}

	@Override
	public Suit getSuit() {
		return this.SUIT;
	}

	@Override
	public Value getValue() {
		return this.VALUE;
	}

	@Override
	public int getScore() {
		// A switch case statement to associate value with the card values
		switch (this.VALUE) {

		case ACE:
			this.score = 11;
			break;

		case KING:
			this.score = 10;
			break;

		case QUEEN:
			this.score = 10;
			break;

		case JACK:
			this.score = 10;
			break;

		case TEN:
			this.score = 10;
			break;

		case NINE:
			this.score = 9;
			break;

		case EIGHT:
			this.score = 8;
			break;

		default:
			this.score = 0;
		}
		return this.score;
	}

	@Override
	public boolean equals(PlayingCard card) {
		return this.SUIT.equals(card.getSuit()) && this.VALUE.equals(card.getValue());
	}

	@Override
	public String toString() {
		return String.format("Suit: %s, Value: %s, Score: %d", capitalize(this.SUIT.toString()),
				capitalize(this.VALUE.toString()), this.score);
	}

	// This is a helper method to apply proper letter casing
	private String capitalize(String name) {
		return name.substring(0, 1) + name.substring(1).toLowerCase();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + score;
		result = prime * result + ((SUIT == null) ? 0 : SUIT.hashCode());
		result = prime * result + ((VALUE == null) ? 0 : VALUE.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object card) {
		if (card != null) {
			if (card instanceof PlayingCard)
				return this.equals((PlayingCard) card);
		}
		return false;
	}
}
