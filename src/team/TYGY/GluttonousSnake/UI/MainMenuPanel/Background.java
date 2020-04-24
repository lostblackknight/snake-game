package team.TYGY.GluttonousSnake.UI.MainMenuPanel;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Background {
	
	private AnchorPane subRoot;
	private Image im;
	private ImageView iv;
	
	
	public Background(AnchorPane subRoot) {
		this.subRoot = subRoot;
		initBackground();
	}
	
	private void initBackground() {
		im = new Image("images/MainMenuPanel1.png", 1280, 720, true, true);
		iv = new ImageView(im);
	}
	public void addBackground() {
		subRoot.getChildren().add(iv);
	}
}
