package team.TYGY.GluttonousSnake.UI.MainMenuPanel;

import java.net.URL;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class AdventureMenuBox extends GridPane {

	private AdventureMenuItems adventureMenuItems;
	
	public AdventureMenuBox(AdventureMenuItems adventureMenuItems) {
		this.adventureMenuItems = adventureMenuItems;
		initAdventureMenuBox();
		addAdventureMenuBox();
	}
	
	@SuppressWarnings("static-access")
	private void initAdventureMenuBox() {
		/**
		 * 设置MenuBox的属性
		 */
		this.setPrefSize(303, 413);
		this.setLayoutX(0);
		this.setLayoutY(307);
//		this.setStyle("-fx-background-color:#EEE9BF");
		this.setMargin(adventureMenuItems.getMenuItems().get("NEW_GAME"), new Insets(0, 0, 8, 60));
		this.setMargin(adventureMenuItems.getMenuItems().get("CONTINUE"), new Insets(0, 0, 276, 60));
		this.setMargin(adventureMenuItems.getMenuItems().get("BACK"), new Insets(0, 0, 43, 68));
		
		/**
		 * 设置menuItems的CSS样式
		 */
		URL cssUrl = this.getClass().getClassLoader().getResource("css/GluttonousSnake.css");
		this.getStylesheets().add(cssUrl.toExternalForm());
	}
	
	private void addAdventureMenuBox() {
		this.add(adventureMenuItems.getMenuItems().get("NEW_GAME"), 0, 1);
		this.add(adventureMenuItems.getMenuItems().get("CONTINUE"), 0, 2);
		this.add(adventureMenuItems.getMenuItems().get("BACK"), 0, 3);
	}
}
