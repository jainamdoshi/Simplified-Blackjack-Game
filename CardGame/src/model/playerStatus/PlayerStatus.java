package model.playerStatus;

import model.interfaces.Player;

public class PlayerStatus {

	private Player player;
	private boolean removeable;
	private boolean removeButton = true;
	private boolean betable = true;
	private boolean dealable = false;
	private boolean canelBetable = false;
	private boolean dealt = false;
	private boolean bet = false;

	public PlayerStatus(Player player, boolean removeable) {
		this.player = player;
		this.removeable = removeable;
	}

	public boolean isRemoveable() {
		return this.removeable;
	}

	public void setRemoveable(boolean removeable) {
		this.removeable = removeable;
	}

	public boolean isBetable() {
		return this.betable;
	}

	public void setBetable(boolean betable) {
		this.betable = betable;
	}

	public boolean isDealable() {
		return this.dealable;
	}

	public void setDealable(boolean dealable) {
		this.dealable = dealable;
	}

	public boolean isCanelBetable() {
		return this.canelBetable;
	}

	public void setCanelBetable(boolean canelBetable) {
		this.canelBetable = canelBetable;
	}

	public boolean isDealt() {
		return dealt;
	}

	public void setDealt(boolean dealt) {
		this.dealt = dealt;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setRemoveButton(boolean status) {
		this.removeButton = status;
		
	}

	public boolean getRemoveButton() {
		return this.removeButton;
	}

	public boolean isBet() {
		return bet;
	}

	public void setBet(boolean bet) {
		this.bet = bet;
	}

}
