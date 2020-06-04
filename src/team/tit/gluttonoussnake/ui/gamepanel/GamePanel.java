package team.tit.gluttonoussnake.ui.gamepanel;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import team.tit.gluttonoussnake.animation.player.SnakeNode;
import team.tit.gluttonoussnake.domain.Game;
import team.tit.gluttonoussnake.domain.ResultInfo;
import team.tit.gluttonoussnake.manager.impl.AudioManager;
import team.tit.gluttonoussnake.manager.impl.UIManager;
import team.tit.gluttonoussnake.ui.BasePanel;
import team.tit.gluttonoussnake.ui.gamepanel.GScreen.GameState;
import team.tit.gluttonoussnake.ui.loadingpanel.LoadingPanel;
import team.tit.gluttonoussnake.ui.mainmenupanel.MainMenuPanel;
import team.tit.gluttonoussnake.ui.mainmenupanel.OptionsMenuBox;

/**
 * 游戏面板
 * 
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月23日
 */
public class GamePanel extends BasePanel {

	private AnchorPane subRoot;
	private Background background;
	private SubMenu subMenu;
	private ResultInfo infoMain;
	private ResultInfo infoLogin;
	private GameScreen gameScreen;

	public static int map = 0;

	public GamePanel() {
	}

	public GamePanel(ResultInfo info) {
		this.infoMain = info;
	}
	public GamePanel(ResultInfo infologin,ResultInfo infomain) {
		this.infoLogin = infologin;
		this.infoMain = infomain;
	}

	@Override
	public void init() {
		// 1.实例化对象
		subRoot = new AnchorPane();
		background = new Background(subRoot);
		subMenu = new SubMenu();
		gameScreen = new GameScreen(subMenu, subRoot,infoMain);

		// 2.获取从主菜单传来的数据对象

		if (infoMain != null && infoMain.isFlag()) {
			// 加判断
			Game game = (Game) infoMain.getData();
			if (game.getType() == 0) {
				// int headX = game.getSanke().getSnakeHeadX();
				// int headY = game.getSanke().getSnakeHeadY();
				LinkedList<SnakeNode> body = game.getSanke().getList();
				int foodX = game.getFood().getFoodX();
				int foodY = game.getFood().getFoodY();
				// TODO 将获取出来的值设到snake对象中去
				// gameScreen.snake.setX(headX);
				// gameScreen.snake.setY(headY);				
				gameScreen.snake.setList(body);
				gameScreen.food.setX(foodX);
				gameScreen.food.setY(foodY);
				gameScreen.info.setLength(body.size()-1);
				gameScreen.info.setScore((body.size()-3)*10);
			}

			if (game.getType() == 1) {
				// int headX = game.getSanke().getSnakeHeadX();
				// int headY = game.getSanke().getSnakeHeadY();
				LinkedList<SnakeNode> body = game.getSanke().getList();
				int foodX = game.getFood().getFoodX();
				int foodY = game.getFood().getFoodY();
//				ArrayList<Point> wall = game.getWall().getPoints();
				int wid = game.getWid();
				int sid = game.getSid();
				int fid = game.getFid();
				ArrayList<Point> points = LoadingPanel.getList().get(wid).getPoints();
				// TODO 将获取出来的值设到snake对象中去
				// gameScreen.snake.setX(headX);
				// gameScreen.snake.setY(headY);
				gameScreen.snake.setId(sid);
				gameScreen.snake.setList(body);
				gameScreen.food.setId(fid);
				gameScreen.food.setX(foodX);
				gameScreen.food.setY(foodY);
				gameScreen.wall.setId(wid);
				gameScreen.wall.setPoints(points);
			}

		}

		// 3.音频
		loadGameAudio();
		// 4.设置游戏状态
		gameScreen.setGameState(GameState.GAME_START);
	}

	@Override
	public void start() {
		background.addBackground();
		subRoot.getChildren().add(gameScreen);
		this.getChildren().add(subRoot);
		gameScreen.start();
	}

	@Override
	public void update() {

		subRoot.setFocusTraversable(true);
		// 键盘按下监听
		subRoot.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				gameScreen.onKeyPressed(event);
			}
		});
		// 键盘释放监听
		subRoot.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				gameScreen.onKeyReleased(event);
			}
		});

		// 点击继续按钮
		subMenu.getContinueGame().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				loadClickAudio();
				if (gameScreen.getGameState() == GameState.GAME_PAUSE) {
					subRoot.getChildren().remove(subMenu);
					gameScreen.setGameState(GameState.GAME_CONTINUE);
				} else if (gameScreen.getGameState() == GameState.GAME_CONTINUE
						|| gameScreen.getGameState() == GameState.GAME_START) {
					subRoot.getChildren().add(subMenu);
					gameScreen.setGameState(GameState.GAME_PAUSE);
				} else if (gameScreen.getGameState() == GameState.GAME_EXIT) {
					subRoot.getChildren().remove(subMenu);
					gameScreen.setGameState(GameState.GAME_END);
				} else if (gameScreen.getGameState() == GameState.GAME_END) {
					subRoot.getChildren().add(subMenu);
					gameScreen.setGameState(GameState.GAME_EXIT);
				}
			}
		});

		// 退出至主菜单
		subMenu.getEXIT_TO_MainMenu().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gameScreen.setGameState(GameState.GAME_EXIT);

				loadClickAudio();
				subRoot.getChildren().remove(subMenu);
				// 1.注册主菜单面板
				UIManager.getUiManager().regPanel("MainMenuPanel", new MainMenuPanel(infoLogin));

				// 2.切换到主菜单面板
				UIManager.getUiManager().gotoPanel("MainMenuPanel");
			}
		});

		// 退出至桌面
		subMenu.getEXIT_TO_WINDOWS().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				loadClickAudio();
				Platform.exit();
			}
		});
	}

	@Override
	public void stop() {
		// 将游戏面板从集合中移除
		UIManager.getUiManager().delPanel("GamePanel");
	}

	/**
	 * 加载音频
	 */
	private void loadGameAudio() {
		AudioManager.getAudioManager().getAudio("GameAudio").init();
		AudioManager.getAudioManager().getAudio("GameAudio").getAudio().volumeProperty()
				.bind(OptionsMenuBox.slider2.valueProperty());
		AudioManager.getAudioManager().getAudio("GameAudio").play();
	}

	private void loadClickAudio() {
		AudioManager.getAudioManager().getAudio("ClickAudio").init();
		AudioManager.getAudioManager().getAudio("ClickAudio").getAudio().volumeProperty()
				.bind(OptionsMenuBox.slider1.valueProperty());
		AudioManager.getAudioManager().getAudio("ClickAudio").play();
	}
}