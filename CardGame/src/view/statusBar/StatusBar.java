package view.statusBar;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBar extends JPanel {

	private JLabel text;

	public StatusBar() {
		text = new JLabel();
		this.add(text);
//		this.set
	}

	public void setText(String string) {
		this.text.setText(string);
	}
}
