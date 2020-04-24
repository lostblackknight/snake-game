package team.TYGY.GluttonousSnake.UI.MainMenuPanel;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class MenuBox extends GridPane {

	private AnchorPane subRoot;
	
	
	public MenuBox(AnchorPane subRoot) {
		this.subRoot = subRoot;
		initMenuBox();
	}
	
	private void initMenuBox() {
		/**
		 * 设置MenuBox的属性
		 */
		this.setPrefSize(303, 413);
		this.setLayoutX(0);
		this.setLayoutY(307);
		this.setStyle("-fx-background-color:#000000");
	}
	public void addMenuBox() {
		subRoot.getChildren().add(this);
	}
}
