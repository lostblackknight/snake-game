package team.TYGY.GluttonousSnake.UI.MainMenuPanel;

import java.net.URL;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class LeiSureMenuBox extends GridPane {

	private LeiSureMenuItems leiSureMenuItems;

	
	public LeiSureMenuBox(LeiSureMenuItems leiSureMenuItems) {
		this.leiSureMenuItems = leiSureMenuItems;
		initLeiSureMenuBox();
		addLeiSureMenuBox();
	}
	
	@SuppressWarnings("static-access")
	private void initLeiSureMenuBox() {
		/**
		 * 设置MenuBox的属性
		 */
		this.setPrefSize(303, 413);
		this.setLayoutX(0);
		this.setLayoutY(307);
//		this.setStyle("-fx-background-color:#FFEC8B");
		this.setMargin(leiSureMenuItems.getMenuItems().get("NEW_GAME"), new Insets(0, 0, 8, 60));
		this.setMargin(leiSureMenuItems.getMenuItems().get("CONTINUE"), new Insets(0, 0, 276, 60));
		this.setMargin(leiSureMenuItems.getMenuItems().get("BACK"), new Insets(0, 0, 43, 68));
		/**
		 * 设置menuItems的CSS样式
		 */
		URL cssUrl = this.getClass().getClassLoader().getResource("css/GluttonousSnake.css");
		this.getStylesheets().add(cssUrl.toExternalForm());
	}
	
	private void addLeiSureMenuBox() {
		this.add(leiSureMenuItems.getMenuItems().get("NEW_GAME"), 0, 1);
		this.add(leiSureMenuItems.getMenuItems().get("CONTINUE"), 0, 2);
		this.add(leiSureMenuItems.getMenuItems().get("BACK"), 0, 3);
	}
}
