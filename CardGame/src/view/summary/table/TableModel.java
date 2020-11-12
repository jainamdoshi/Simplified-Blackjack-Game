package view.summary.table;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import model.interfaces.Player;
import view.mainUI.ApplicationFrame;

public class TableModel extends DefaultTableModel {

	private Collection<Player> players;
	private Map<Player, Integer> playerBets;
	public static int NO_RESULT = -1;

	public TableModel(Object[] columnNames, int rowCount, ApplicationFrame frame) {
		super(columnNames, rowCount);
		this.players = frame.getPlayers();
		this.playerBets = new HashMap<Player, Integer>();
	}

	@Override
	public void removeRow(int id) {
		int row = getRowNumber(id);

		if (row != -1)
			super.removeRow(row);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int id, int column) {
		int row = getRowNumber(id);
		if (row != -1)
			super.setValueAt(aValue, row, column);
	}

	private int getRowNumber(int id) {
		for (int row = 0; row < this.getRowCount(); row++) {
			if (Integer.parseInt((String) this.getValueAt(row, 0)) == id) {
				return row;
			}
		}
		return -1;
	}

	public void updatePlayerData(int result) {

		for (Player player : players) {
			int id = Integer.parseInt(player.getPlayerId());
			int playerResult = player.getResult();
			int playerBet = player.getBet();

			if (result != -1) {
				String resultString = "Draw";

				if (playerResult > result) {
					resultString = "Won " + this.playerBets.get(player) + " Points";
				} else if (playerResult < result) {
					resultString = "Loss " + this.playerBets.get(player) + " Points";
				}

				setValueAt(resultString, id, 5);
			}

			setValueAt(playerResult, id, 4);
			setValueAt(player.getPoints(), id, 3);
			setValueAt(playerBet, id, 2);

		}

	}

	public void setPlayerBet(Player player) {
		this.playerBets.put(player, player.getBet());

	}
}
