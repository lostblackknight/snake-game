package team.TYGY.GluttonousSnake.UI.MainMenuPanel;

import java.net.URL;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class OptionsMenuBox extends GridPane {

	private AnchorPane subRoot;
	private GridPane choice;
	private GridPane content;
	private Label display;
	private Label gameplay;
	private Label keyBinding;
	private Label displaySettings;
	private Label gameBackground;
	private Label rules;
	private Label audio;
	private Label language;
	private Label movement;
	private Label speed;
	private Label attack;
	
	public OptionsMenuBox(AnchorPane subRoot) {
		this.subRoot = subRoot;
		initOptionsMenuBox();
	}
	
	@SuppressWarnings("static-access")
	private void initOptionsMenuBox() {
		/**
		 * 创建对象
		 */
		choice = new GridPane();
		content = new GridPane();
		display = new Label("显示");
		gameplay = new Label("游戏玩法");
		keyBinding = new Label("按键绑定");
		displaySettings = new Label("显示设置");
		
		/**
		 * 设置OptionsMenuBox的属性
		 */
		this.setMargin(choice, new Insets(76, 0, 0, 63));
		this.setMargin(content, new Insets(28, 0, 0, 63));
		this.add(choice, 0, 0);
		this.add(content, 0, 1);
		
		choice.setPrefHeight(24);
		choice.setStyle("-fx-background-color:#B2DFEE");
		choice.add(display, 0, 0);
		choice.add(gameplay, 1, 0);
		choice.add(keyBinding, 2, 0);
		choice.setMargin(display, new Insets(0, 42, 0, 0));
		choice.setMargin(gameplay, new Insets(0, 42, 0, 0));
		choice.setMargin(keyBinding, new Insets(0, 0, 0, 0));
		
		display.setId("DISPLAY");
		display.setPadding(new Insets(-5, 0, -3, 0));

		gameplay.setId("GAME_PLAY");
		gameplay.setPadding(new Insets(-5, 0, -3, 0));
		
		keyBinding.setId("KEY_BINDING");
		keyBinding.setPadding(new Insets(-5, 0, -3, 0));
		
		displaySettings.setId("DISPLAY_SETTINGS");
		
		content.setPrefHeight(592);
		content.setStyle("-fx-background-color:#98FB98");
		content.add(displaySettings, 0, 0);
		

		
		
		/**
		 * 设置OptionsMenuBox的CSS样式
		 */
		URL cssUrl = this.getClass().getClassLoader().getResource("css/GluttonousSnake.css");
		this.getStylesheets().add(cssUrl.toExternalForm());
		this.setStyle("-fx-background-color:#000000");
		subRoot.setLeftAnchor(this, 0.0);
		subRoot.setRightAnchor(this, 0.0);
		subRoot.setTopAnchor(this, 0.0);
		subRoot.setBottomAnchor(this, 0.0);
	}
	
	public void addOptionsMenuBox() {
		subRoot.getChildren().add(this);
	}
}
