package team.TYGY.GluttonousSnake.UI.MainMenuPanel;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class AdventureMenuBox extends GridPane {

	private AnchorPane subRoot;

	
	public AdventureMenuBox(AnchorPane subRoot) {
		this.subRoot = subRoot;
		initAdventureMenuBox();
	}
	
	private void initAdventureMenuBox() {
		/**
		 * 设置MenuBox的属性
		 */
		this.setPrefSize(303, 413);
		this.setLayoutX(606);
		this.setLayoutY(307);
		this.setStyle("-fx-background-color:#EEE9BF");
	}
	
	public void addAdventureMenuBox() {
		subRoot.getChildren().add(this);
	}
}
