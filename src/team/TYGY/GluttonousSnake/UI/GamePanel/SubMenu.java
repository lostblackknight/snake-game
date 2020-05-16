package team.TYGY.GluttonousSnake.UI.GamePanel;

import java.net.URL;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import team.TYGY.GluttonousSnake.UI.MainMenuPanel.Title;

public class SubMenu extends AnchorPane{
	
	Title title;
	GridPane subMenuBox;
	Label continueGame;
	Label EXIT_TO_MainMenu;
	Label EXIT_TO_WINDOWS;
	
	public SubMenu() {
		initSubMenu();
		addSubMenu();
	}
	
	public Label getContinueGame() {
		return continueGame;
	}

	public void setContinueGame(Label continueGame) {
		this.continueGame = continueGame;
	}

	public Label getEXIT_TO_MainMenu() {
		return EXIT_TO_MainMenu;
	}

	public void setEXIT_TO_MainMenu(Label eXIT_TO_MainMenu) {
		EXIT_TO_MainMenu = eXIT_TO_MainMenu;
	}

	public Label getEXIT_TO_WINDOWS() {
		return EXIT_TO_WINDOWS;
	}

	public void setEXIT_TO_WINDOWS(Label eXIT_TO_WINDOWS) {
		EXIT_TO_WINDOWS = eXIT_TO_WINDOWS;
	}

	@SuppressWarnings("static-access")
	private void initSubMenu() {
		title = new Title(this);
		subMenuBox = new GridPane();
		continueGame = new Label("继续");
		EXIT_TO_MainMenu = new Label("主菜单");
		EXIT_TO_WINDOWS = new Label("退出至桌面");
		
		
		
		continueGame.setId("continueGame");
		continueGame.setPrefSize(400, 34);
		continueGame.setPadding(new Insets(-7, 0, -4, 0));
		
		EXIT_TO_MainMenu.setId("EXIT_TO_MainMenu");
		EXIT_TO_MainMenu.setPrefSize(400, 34);
		EXIT_TO_MainMenu.setPadding(new Insets(-7, 0, -4, 0));
		
		EXIT_TO_WINDOWS.setId("EXIT_TO_WINDOWS");
		EXIT_TO_WINDOWS.setPrefSize(400, 34);
		EXIT_TO_WINDOWS.setPadding(new Insets(-7, 0, -4, 0));
		
		
		subMenuBox.setMargin(continueGame, new Insets(0, 0, 10, 67));
		subMenuBox.setMargin(EXIT_TO_MainMenu, new Insets(0, 0, 0, 67));
		subMenuBox.setMargin(EXIT_TO_WINDOWS, new Insets(276, 0, 38, 67));
		subMenuBox.setPrefSize(400, 413);
		
		
		/**
		 * 设置SubMenu的属性
		 */
		this.setPrefSize(400, 720);
		this.setStyle("-fx-background-color: #000000");
		this.setTopAnchor(subMenuBox, 307.0);
		/**
		 * 设置CSS样式
		 */
		URL cssUrl = this.getClass().getClassLoader().getResource("css/GluttonousSnake.css");
		this.getStylesheets().add(cssUrl.toExternalForm());
	}
	
	private void addSubMenu() {
		title.addTitle();
		subMenuBox.add(continueGame, 0, 0);
		subMenuBox.add(EXIT_TO_MainMenu, 0, 1);
		subMenuBox.add(EXIT_TO_WINDOWS, 0, 2);
		this.getChildren().add(subMenuBox);
	}
}
