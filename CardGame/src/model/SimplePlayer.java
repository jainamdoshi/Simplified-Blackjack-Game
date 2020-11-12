package model;

import model.interfaces.Player;

public class SimplePlayer implements Player {

	private String id;
	private String playerName;
	private int points;
	private int bet;
	private int result;

	public SimplePlayer(String id, String playerName, int initialPoints) {
		this.points = initialPoints;
		this.id = id;
		this.playerName = playerName;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public int getPoints() {
		return this.points;
	}

	@Override
	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String getPlayerId() {
		return this.id;
	}

	@Override
	public boolean setBet(int bet) {
		this.bet = bet;
		return true;
	}

	@Override
	public int getBet() {
		return this.bet;
	}

	@Override
	public void resetBet() {
		this.bet = 0;
	}

	@Override
	public int getResult() {
		return this.result;
	}

	@Override
	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public boolean equals(Player player) {
		return this.id.equals(player.getPlayerId());
	}

	@Override
	public boolean equals(Object player) {
		if (player != null) {
			if (player instanceof Player)
				return this.equals((Player) player);
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
		return result;
	}

	@Override
	public int compareTo(Player player) {
		return this.id.compareTo(player.getPlayerId());
	}

	@Override
	public String toString() {
		return String.format("Player: id=%s, name=%s, bet=%d, points=%d, RESULT .. %d ", this.id, this.playerName,
				this.bet, this.points, this.result);
	}
}
