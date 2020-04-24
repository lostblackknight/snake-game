package team.TYGY.GluttonousSnake.UI.MainMenuPanel;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class LeiSureMenuBox extends GridPane {

	private AnchorPane subRoot;

	
	public LeiSureMenuBox(AnchorPane subRoot) {
		this.subRoot = subRoot;
		initLeiSureMenuBox();
	}
	
	private void initLeiSureMenuBox() {
		/**
		 * 设置MenuBox的属性
		 */
		this.setPrefSize(303, 413);
		this.setLayoutX(303);
		this.setLayoutY(307);
		this.setStyle("-fx-background-color:#FFEC8B");
	}
	
	public void addLeiSureMenuBox() {
		subRoot.getChildren().add(this);
	}
}
