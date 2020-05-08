package team.TYGY.GluttonousSnake.UI.MainMenuPanel;


import java.util.HashMap;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import team.TYGY.GluttonousSnake.GameAPP.GameAPP;
import team.TYGY.GluttonousSnake.UI.BasePanel;

/**
 * 
 * @ClassName: MainMenuPanel
 * @Description: TODO 主菜单面板
 * @author: 陈思祥
 * @version: 1.3
 * @date: 2020年4月10日-下午9:27:52
 *
 */
public class MainMenuPanel extends BasePanel {

	private AnchorPane ap;
	private Background background;
	private Title title;
	private MenuBox menuBox;
	private MenuItems menuItems;
//	private Hand mouseHand;
	private LeiSureMenuBox leiSureMenuBox;
	private LeiSureMenuItems leiSureMenuItems;
	private AdventureMenuBox adventureMenuBox;
	private AdventureMenuItems adventureMenuItems;
	private OptionsMenuBox optionsMenuBox;
	
	private HashMap<String,Pane> boxMap = new HashMap<String, Pane>();
	private ObjectProperty<Pane> currentBox = new SimpleObjectProperty<Pane>();
	
	public MainMenuPanel() {
		init();
		start();
		update();
		stop();
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void init() {
		ap = new AnchorPane();
		background = new Background(ap);
		title = new Title(ap);
		
		menuItems = new MenuItems();
		menuBox = new MenuBox(menuItems);
		
		leiSureMenuItems = new LeiSureMenuItems();
		leiSureMenuBox = new LeiSureMenuBox(leiSureMenuItems);
		
		adventureMenuItems = new AdventureMenuItems();
		adventureMenuBox = new AdventureMenuBox(adventureMenuItems);
		
		optionsMenuBox = new OptionsMenuBox();
//		mouseHand = new Hand(ap);

		regBox("menuBox", menuBox);
		regBox("leiSureMenuBox", leiSureMenuBox);
		regBox("adventureMenuBox", adventureMenuBox);
		regBox("optionsMenuBox", optionsMenuBox);
		
		GameAPP.root.setLeftAnchor(this, 0.0);
		GameAPP.root.setRightAnchor(this, 0.0);
		GameAPP.root.setTopAnchor(this, 0.0);
		GameAPP.root.setBottomAnchor(this, 0.0);
		this.setLeftAnchor(ap, 0.0);
		this.setRightAnchor(ap, 0.0);
		this.setTopAnchor(ap, 0.0);
		this.setBottomAnchor(ap, 0.0);
		
		
	}

	@Override
	public void start() {
//		mouseHand.setHand();
		background.addBackground();
		title.addTitle();
//		leiSureMenuBox.addLeiSureMenuBox();
//		adventureMenuBox.addAdventureMenuBox();
//		optionsMenuBox.addOptionsMenuBox();
		ap.getChildren().add(menuBox);
		this.getChildren().add(ap);
		
		gotoBox("menuBox");
		currentBox.addListener(new ChangeListener<Pane>() {

			@Override
			public void changed(ObservableValue<? extends Pane> observable, Pane oldValue, Pane newValue) {
				if(oldValue != null) {
					ap.getChildren().remove(oldValue);
				}
				if(newValue != null) {
					ap.getChildren().add(newValue);
				}
			}
		});
	}

	@Override
	public void update() {
		
		menuItems.getMenuItem().get("LEISURE").setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gotoBox("leiSureMenuBox");
			}
		});
		
		menuItems.getMenuItem().get("ADVENTURE").setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gotoBox("adventureMenuBox");
			}
		});
		
		menuItems.getMenuItem().get("OPTIONS").setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gotoBox("optionsMenuBox");
			}
		});
		
		menuItems.getMenuItem().get("EXIT_TO_WINDOWS").setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Platform.exit();
			}
		});
		
		leiSureMenuItems.getMenuItems().get("BACK").setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gotoBox("menuBox");
			}
		});
		
		adventureMenuItems.getMenuItems().get("BACK").setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gotoBox("menuBox");
			}
		});
		optionsMenuBox.getBack_options().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gotoBox("menuBox");
			}
		});
	}
	
	@Override
	public void stop() {
		
	}
	
	public void gotoBox(String boxName) {
		Pane box = boxMap.get(boxName);
		if(box != null) {
			currentBox.set(box);
		}
	}
	
	public void regBox(String boxName,Pane box) {
		boxMap.put(boxName, box);
	}
	
	public void delBox(String boxName) {
		boxMap.remove(boxName);
	}
	
	public Pane getBox(String boxName) {
		return boxMap.get(boxName);
	}
	
	public Pane getCurrentBox() {
		return currentBox.get();
	}
}
