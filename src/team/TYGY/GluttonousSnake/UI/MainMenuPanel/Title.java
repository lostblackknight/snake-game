package team.TYGY.GluttonousSnake.UI.MainMenuPanel;

import static team.TYGY.GluttonousSnake.DB.Constant.GAME_STAGE_TITLE;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Title {

	private AnchorPane subRoot;
	private Text text;
	
	
	public Title(AnchorPane subRoot) {
		this.subRoot = subRoot;
	}
	
	public void addTitle() {
		text = new Text(GAME_STAGE_TITLE);
		text.setFill(Color.WHITE);
        text.setEffect(new DropShadow(30, Color.BLACK));
		text.setY(100);
		
		subRoot.getChildren().add(text);
	}
}
