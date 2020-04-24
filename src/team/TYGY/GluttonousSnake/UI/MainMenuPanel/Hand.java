package team.TYGY.GluttonousSnake.UI.MainMenuPanel;

import java.net.URL;

import javafx.scene.Cursor;
import javafx.scene.layout.AnchorPane;

public class Hand {
	
	private AnchorPane subRoot;
	
	public Hand(AnchorPane subroot) {
		this.subRoot = subroot;
	}
	
	public void setHand() {
		URL url = this.getClass().getClassLoader().getResource("images/Hand.png");
		String path = url.toExternalForm();
		subRoot.setCursor(Cursor.cursor(path));
	}
}
