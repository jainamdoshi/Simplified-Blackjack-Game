package view.summary;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.interfaces.Player;
import view.mainUI.ApplicationFrame;
import view.summary.table.TableModel;

public class SummaryPanel extends JPanel {

	private TableModel tableModel;
	private JTable table;
	private JFrame frame;
	private JScrollPane scrollPane;

	public SummaryPanel(ApplicationFrame frame) {

		this.frame = frame;

		String[] columnNames = new String[] { "ID", "Name", "Bet", "Points", "Result", "Previous Round Result Summary" };

		this.tableModel = new TableModel(columnNames, 0, frame);
		this.table = new JTable(tableModel);
		this.scrollPane = new JScrollPane(table);

		this.table.setFillsViewportHeight(true);
		this.add(scrollPane);
	}

	public TableModel getTableModel() {
		return this.tableModel;
	}

	@Override
	public void revalidate() {
		if (this.frame != null) {
			this.scrollPane.setPreferredSize(new Dimension(this.frame.getWidth() - 15, this.frame.getHeight() / 4));
		}
		super.revalidate();
	}

	public void removePlayer(Player player) {
		this.tableModel.removeRow(Integer.parseInt(player.getPlayerId()));

	}

	public void addPlayer(Player player) {
		this.tableModel.addRow(new Object[] { player.getPlayerId(), player.getPlayerName(), player.getBet(), player.getPoints(), player.getResult()} );
	}

}
