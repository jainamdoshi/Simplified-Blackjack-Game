package view.toolBar.comboBox;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.interfaces.Player;

// https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/CustomComboBoxDemoProject/src/components/CustomComboBoxDemo.java
public class ComboBoxRenderer extends JLabel implements ListCellRenderer<Player> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Player> list, Player player, int index,
			boolean isSelected, boolean cellHasFocus3) {

		if (player != null)
			this.setText(player.getPlayerName());
		else
			this.setText(null);

		return this;
	}

}
