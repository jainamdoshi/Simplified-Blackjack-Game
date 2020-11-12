package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {

	private Map<String, Player> players;
	private List<GameEngineCallback> gameEngineCallbacks;
	private Deque<PlayingCard> deck;

	public GameEngineImpl() {
		this.players = new TreeMap<String, Player>();
		this.gameEngineCallbacks = new ArrayList<GameEngineCallback>();
		this.deck = this.getShuffledHalfDeck();
	}

	@Override
	public void dealPlayer(Player player, int delay) throws IllegalArgumentException {
		
		player.setPoints(player.getPoints() - player.getBet());
		deal(player, this.gameEngineCallbacks, delay);
	}

	@Override
	public void dealHouse(int delay) throws IllegalArgumentException {

		deal(null, this.gameEngineCallbacks, delay);

		// Resets bets of all the players
		for (Player player : this.players.values()) {
			player.resetBet();
		}
	}

	// This is a helper method for both dealPlayer and dealHouse
	private void deal(Player player, List<GameEngineCallback> gameEngineCallbacks, int delay) {

		// Checks if the delay time is valid
		this.checkDelay(delay);

		int score = 0;
		int currScore = 0;
		PlayingCard card = null;

		// A do while loop that will run until the player/house busts
		do {
			score += currScore;

			// Generate a new deck every time the deck runs out of cards
			if (deck.size() == 0) {
				deck = this.getShuffledHalfDeck();
			}

			card = deck.removeLast();
			currScore = card.getScore();

			// Calls methods of the gameEngineCallbacks
			for (GameEngineCallback callback : gameEngineCallbacks) {
				if (player != null) {
					callback.nextCard(player, card, this);
				} else {
					callback.nextHouseCard(card, this);
				}
			}

			// Delay for the next card
			this.delay(delay);

		} while ((score + currScore) <= GameEngine.BUST_LEVEL);
		
		if (player == null) {
			for (Player player2 : this.players.values()) {
				this.applyWinLoss(player2, score);
			}
		}

		// Calls methods of the gameEngineCallbacks
		for (GameEngineCallback callback : gameEngineCallbacks) {
			if (player != null) {
				callback.bustCard(player, card, this);

				callback.result(player, score, this);
				player.setResult(score);

			} else {

				callback.houseBustCard(card, this);
				callback.houseResult(score, this);
				
				for (Player player2 : this.players.values()) {
					player2.setBet(0);
				}
			}
		}
	}

	// This is a helper method for validating the argument
	private void checkDelay(int delay) throws IllegalArgumentException {
		if (delay < 0 || delay > 1000)
			throw new IllegalArgumentException();
	}

	// This is a helper method to delay between cards dealt
	private void delay(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void applyWinLoss(Player player, int houseResult) {
		int playerResult = player.getResult();

		// Increases points of the player if they won against the house
		if (playerResult > houseResult) {
			player.setPoints(player.getPoints() + (2 * player.getBet()));

			// else decrease points of the player if they lost against the house
		} else if (playerResult == houseResult) {
			player.setPoints(player.getPoints() + player.getBet());
		}
	}

	@Override
	public void addPlayer(Player player) {
		this.players.put(player.getPlayerId(), player);
	}

	@Override
	public Player getPlayer(String id) {
		return this.players.get(id);
	}

	@Override
	public boolean removePlayer(Player player) {
		if (player != null)
			return this.players.remove(player.getPlayerId(), player);
		return false;
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		// Check for a valid bet
		if (bet == 0) {
			player.resetBet();
	
		} else if (bet <= player.getPoints() && bet > 0) {
			return player.setBet(bet);
		}
		return false;
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.gameEngineCallbacks.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		return this.gameEngineCallbacks.remove(gameEngineCallback);
	}

	@Override
	public Collection<Player> getAllPlayers() {
		
		return Collections.unmodifiableCollection(this.players.values());
		
	}

	@Override
	public Deque<PlayingCard> getShuffledHalfDeck() {
		LinkedList<PlayingCard> deck = new LinkedList<PlayingCard>();

		// Access all the suits and values as a array
		PlayingCard.Suit[] suits = PlayingCard.Suit.values();
		PlayingCard.Value[] values = PlayingCard.Value.values();

		// Nested for loop to create every combination of playing card with given suits
		// and values
		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < values.length; j++) {
				PlayingCard card = new PlayingCardImpl(suits[i], values[j]);
				deck.add(card);
			}
		}

		Collections.shuffle(deck);
		return deck;
	}

}