package team.TYGY.GluttonousSnake.UI.MainMenuPanel;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Title {

	private AnchorPane subRoot;
	private Image im;
	private ImageView iv;
	
	
	public Title(AnchorPane subRoot) {
		this.subRoot = subRoot;
		initTitle();
	}
	
	private void initTitle() {
		im = new Image("images/GluttonousSnake.png", 400, 400, true, true);
		iv = new ImageView(im);
		iv.setX(60);
		iv.setY(68);
	}

	public void addTitle() {
		subRoot.getChildren().add(iv);
	}
}
