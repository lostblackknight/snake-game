package team.tit.gluttonoussnake.ui.mainmenupanel;

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
import team.tit.gluttonoussnake.domain.Game;
import team.tit.gluttonoussnake.domain.ResultInfo;
import team.tit.gluttonoussnake.domain.User;
import team.tit.gluttonoussnake.manager.impl.AudioManager;
import team.tit.gluttonoussnake.manager.impl.UIManager;
import team.tit.gluttonoussnake.service.GameService;
import team.tit.gluttonoussnake.service.impl.GameServiceImpl;
import team.tit.gluttonoussnake.ui.BasePanel;
import team.tit.gluttonoussnake.ui.gamepanel.GamePanel;

/**
 * 主菜单面板
 * 
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月23日
 */
public class MainMenuPanel extends BasePanel {

	private AnchorPane subRoot;
	private Background background;
	private Title title;
	private MenuBox menuBox;
	private MenuItems menuItems;
	private LeiSureMenuBox leiSureMenuBox;
	private LeiSureMenuItems leiSureMenuItems;
	private AdventureMenuBox adventureMenuBox;
	private AdventureMenuItems adventureMenuItems;
	private OptionsMenuBox optionsMenuBox;
	private HashMap<String, Pane> boxMap = new HashMap<String, Pane>();
	private ObjectProperty<Pane> currentBox = new SimpleObjectProperty<Pane>();
	private ResultInfo infoLogin;
	private ResultInfo infoMain = null;

	public MainMenuPanel() {
	}

	public MainMenuPanel(ResultInfo info) {
			this.infoLogin = info;		
	}
	@Override
	public void init() {
		// 1.实例化对象
		subRoot = new AnchorPane();
		background = new Background(subRoot);
		title = new Title(subRoot);
		menuItems = new MenuItems();
		menuBox = new MenuBox(menuItems);
		leiSureMenuItems = new LeiSureMenuItems();
		leiSureMenuBox = new LeiSureMenuBox(leiSureMenuItems);
		adventureMenuItems = new AdventureMenuItems();
		adventureMenuBox = new AdventureMenuBox(adventureMenuItems);
		optionsMenuBox = new OptionsMenuBox();

		// 2.注册菜单盒子
		regBox("menuBox", menuBox);
		regBox("leiSureMenuBox", leiSureMenuBox);
		regBox("adventureMenuBox", adventureMenuBox);
		regBox("optionsMenuBox", optionsMenuBox);

		// 3.首先切换到主菜单盒子
		gotoBox("menuBox");

		// 4.音频
		loadMainMenuAudio();
	}

	@Override
	public void start() {
		background.addBackground();
		title.addTitle();
		subRoot.getChildren().add(menuBox);
		this.getChildren().add(subRoot);
	}

	@Override
	public void update() {
		// 1.添加属性监听，实现菜单盒子的切换
		currentBox.addListener(new ChangeListener<Pane>() {

			@Override
			public void changed(ObservableValue<? extends Pane> observable, Pane oldValue, Pane newValue) {
				if (oldValue != null) {
					subRoot.getChildren().remove(oldValue);
				}
				if (newValue != null) {
					subRoot.getChildren().add(newValue);
				}
				// 加载音频
				loadClickAudio();
			}
		});

		// 2.从主菜单盒子切换到休闲模式的菜单盒子
		menuItems.getMenuItem().get("LEISURE").setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gotoBox("leiSureMenuBox");
				// 加载音频
				loadClickAudio();
			}
		});

		// 3.从主菜单盒子切换到冒险模式的菜单盒子
		menuItems.getMenuItem().get("ADVENTURE").setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gotoBox("adventureMenuBox");
				// 加载音频
				loadClickAudio();
			}
		});

		// 4.从主菜单盒子切换到选项的菜单盒子
		menuItems.getMenuItem().get("OPTIONS").setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gotoBox("optionsMenuBox");
				// 加载音频
				loadClickAudio();
			}
		});

		// 5.退出至桌面
		menuItems.getMenuItem().get("EXIT_TO_WINDOWS").setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Platform.exit();
			}
		});

		// 6.从休闲模式菜单盒子切换到主菜单盒子
		leiSureMenuItems.getMenuItems().get("BACK").setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gotoBox("menuBox");
				// 加载音频
				loadClickAudio();
			}
		});

		// 7.从冒险模式菜单盒子切换到主菜单盒子
		adventureMenuItems.getMenuItems().get("BACK").setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gotoBox("menuBox");
				// 加载音频
				loadClickAudio();
			}
		});

		// 8.从选项盒子切换到主菜单盒子
		optionsMenuBox.getBack_options().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gotoBox("menuBox");
				// 加载音频
				loadClickAudio();
			}
		});

		// 9.开始新的游戏(休闲模式)
		leiSureMenuItems.getMenuItems().get("NEW_GAME").setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (infoLogin != null && infoLogin.isFlag()) {
					// 1.获取用户的uid和设置游戏类型为0
					User u = (User) infoLogin.getData();
					int uid = u.getUid();
					int type = 0;

					System.out.println("休闲模式new game");
					// 2.封装数据
					Game game = new Game(uid, type);

					// 3.调用service完成查询游戏数据，返回一个game对象
					GameService service = new GameServiceImpl();
					boolean flag = service.haveOldData(game);
					System.out.println(flag);
					Game g = null;
					if (flag) {
						g = service.findOne(game);
						service.delOldData(g);
						flag = false;
					}else {
					    g=service.setInitialValue(game);
					}

						infoMain = new ResultInfo(flag, g,1);
				}

				// 1.注册游戏面板
				UIManager.getUiManager().regPanel("GamePanel", new GamePanel(infoLogin,infoMain));

				// 2.切换到游戏面板
				UIManager.getUiManager().gotoPanel("GamePanel");
				AudioManager.getAudioManager().getAudio("MainMenuAudio").close();
				// 3.加载音频
				loadClickAudio();
			}
		});

		// 10.继续游戏(休闲模式)
		leiSureMenuItems.getMenuItems().get("CONTINUE").setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO 加载用户的游戏数据
				/*
				 * 1.获取用户的uid和设置游戏类型为0 2.封装成一个Game的实例化对象 3.调用service完成查询游戏数据，返回一个game对象 4.如果sid
				 * && fid == null 5.设置初始数据 6.找到设置数据库中的数据
				 */

				if (infoLogin != null && infoLogin.isFlag()) {
					// 1.获取用户的uid和设置游戏类型为0
					User u = (User) infoLogin.getData();
					int uid = u.getUid();
					int type = 0;

					System.out.println("休闲模式continue game");
					// 2.封装数据
					Game game = new Game(uid, type);

					// 3.调用service完成查询游戏数据，返回一个game对象
					GameService service = new GameServiceImpl();
					boolean flag = service.haveOldData(game);
					System.out.println(flag);
					Game g = new Game(uid, type);
					if (flag) {
						g = service.findOne(game);
					}else {
					    g=service.setInitialValue(game);
					}					
					
						infoMain = new ResultInfo(flag, g,1);
						
				}

//				// 用于测试从表里获取数据（之后删掉）
//				Game game = new Game(1, 0);
//				GameService service = new GameServiceImpl();
//				Game g = service.findOne(game);
//				boolean flag = service.haveOldData(game);
//				infoMain = new ResultInfo(flag, g);

				// 1.注册游戏面板
				UIManager.getUiManager().regPanel("GamePanel", new GamePanel(infoLogin,infoMain));

				// 2.切换到游戏面板
				UIManager.getUiManager().gotoPanel("GamePanel");
				AudioManager.getAudioManager().getAudio("MainMenuAudio").close();
				// 3.加载音频
				loadClickAudio();
			}
		});

		// 11.开始新的游戏(冒险模式)
		adventureMenuItems.getMenuItems().get("NEW_GAME").setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (infoLogin != null && infoLogin.isFlag()) {
					// 1.获取用户的uid和设置游戏类型为1
					User u = (User) infoLogin.getData();
					int uid = u.getUid();
					int type = 1;

					System.out.println("冒险模式new game");
					// 2.封装数据
					Game game = new Game(uid, type);

					// 3.调用service完成查询游戏数据，返回一个game对象
					GameService service = new GameServiceImpl();
					boolean flag = service.haveOldData(game);

					Game g = service.findOne(game);

					if (flag) {
						service.delOldData(g);
						flag = false;
					}else {						
					    g=service.setInitialValue(game);						    
					}					
					g.setWid(1);
					
						infoMain = new ResultInfo(flag, g,1);		
				}
				// 1.注册游戏面板
				UIManager.getUiManager().regPanel("GamePanel", new GamePanel(infoLogin,infoMain));

				// 2.切换到游戏面板
				UIManager.getUiManager().gotoPanel("GamePanel");
				AudioManager.getAudioManager().getAudio("MainMenuAudio").close();
				// 3.加载音频
				loadClickAudio();
			}
		});

		// 12.继续游戏(冒险模式)
		adventureMenuItems.getMenuItems().get("CONTINUE").setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO 加载用户的游戏数据
				if (infoLogin != null && infoLogin.isFlag()) {
					// 1.获取用户的uid和设置游戏类型为1
					User u = (User) infoLogin.getData();
					int uid = u.getUid();
					int type = 1;

					System.out.println("冒险模式continue game");
					// 2.封装数据
					Game game = new Game(uid, type);

					// 3.调用service完成查询游戏数据，返回一个game对象
					GameService service = new GameServiceImpl();
					boolean flag = service.haveOldData(game);
					System.out.println(flag);
					Game g = null;
					if (flag) {
						g = service.findOne(game);
					}else {
					    g=service.setInitialValue(game);
					}
					
						infoMain = new ResultInfo(flag, g,1);
				}
				// 1.注册游戏面板
				UIManager.getUiManager().regPanel("GamePanel", new GamePanel(infoLogin,infoMain));

				// 2.切换到游戏面板
				UIManager.getUiManager().gotoPanel("GamePanel");
				AudioManager.getAudioManager().getAudio("MainMenuAudio").close();
				// 3.加载音频
				loadClickAudio();
			}
		});
	}

	@Override
	public void stop() {
		// 将主菜单面板从集合中移除
		UIManager.getUiManager().delPanel("MainMenuPanel");
	}

	public void gotoBox(String boxName) {
		Pane box = boxMap.get(boxName);
		if (box != null) {
			currentBox.set(box);
		}
	}

	public void regBox(String boxName, Pane box) {
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

	private void loadClickAudio() {
		AudioManager.getAudioManager().getAudio("ClickAudio").init();
		AudioManager.getAudioManager().getAudio("ClickAudio").getAudio().volumeProperty()
				.bind(OptionsMenuBox.slider1.valueProperty());
		AudioManager.getAudioManager().getAudio("ClickAudio").play();
	}

	private void loadMainMenuAudio() {
		AudioManager.getAudioManager().getAudio("MainMenuAudio").init();
		AudioManager.getAudioManager().getAudio("MainMenuAudio").getAudio().volumeProperty()
				.bind(OptionsMenuBox.slider2.valueProperty());
		AudioManager.getAudioManager().getAudio("MainMenuAudio").play();
	}
}
