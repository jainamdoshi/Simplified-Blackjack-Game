package view.cardPanel.card;

import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.interfaces.PlayingCard.Suit;

public class SuitImageFactory {

	public Map<Suit, Image> suits = new HashMap<Suit, Image>();

	public SuitImageFactory(JPanel cardPanel) {

		for (Suit suit : Suit.values()) {

			Image image = new ImageIcon(String.format("img%s%s.png", File.separator, suit.name().toLowerCase()))
					.getImage();

			suits.put(suit, image);
		}

	}

	public Image getImage(Suit suit) {
		Image image = this.suits.get(suit);
		return image;
	}

}
