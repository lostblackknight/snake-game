package team.TYGY.GluttonousSnake.UI.MainMenuPanel;

import java.net.URL;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class MenuBox extends GridPane {

	private MenuItems menuItems;
	
	
	public MenuBox(MenuItems menuItems) {
		this.menuItems = menuItems;
		initMenuBox();
		addMenuBox();
	}
	
	@SuppressWarnings("static-access")
	private void initMenuBox() {
		/**
		 * 设置MenuBox的属性
		 */
		this.setPrefSize(303, 413);
		this.setLayoutX(0);
		this.setLayoutY(307);
//		this.setStyle("-fx-background-color:#FFE4E1");
		this.setMargin(menuItems.getMenuItem().get("LEISURE"), new Insets(0, 0, 8, 67));
		this.setMargin(menuItems.getMenuItem().get("ADVENTURE"), new Insets(0, 0, 26, 67));
		this.setMargin(menuItems.getMenuItem().get("OPTIONS"), new Insets(0, 0, 224, 67));
		this.setMargin(menuItems.getMenuItem().get("EXIT_TO_WINDOWS"), new Insets(0, 0, 41, 67));
		
		/**
		 * 设置menuItems的CSS样式
		 */
		URL cssUrl = this.getClass().getClassLoader().getResource("css/GluttonousSnake.css");
		this.getStylesheets().add(cssUrl.toExternalForm());
	}
	public void addMenuBox() {
		this.add(menuItems.getMenuItem().get("LEISURE"), 0, 1);
		this.add(menuItems.getMenuItem().get("ADVENTURE"), 0, 2);
		this.add(menuItems.getMenuItem().get("OPTIONS"), 0, 3);
		this.add(menuItems.getMenuItem().get("EXIT_TO_WINDOWS"),0,4);
	}
}
