package team.tit.gluttonoussnake.ui.gamepanel;

import java.util.LinkedList;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import team.tit.gluttonoussnake.animation.npc.Food;
import team.tit.gluttonoussnake.animation.npc.Wall;
import team.tit.gluttonoussnake.animation.player.Snake;
import team.tit.gluttonoussnake.animation.player.SnakeNode;
import team.tit.gluttonoussnake.domain.Game;
import team.tit.gluttonoussnake.domain.ResultInfo;
import team.tit.gluttonoussnake.manager.impl.AudioManager;
import team.tit.gluttonoussnake.manager.impl.UIManager;
import team.tit.gluttonoussnake.service.GameService;
import team.tit.gluttonoussnake.service.impl.GameServiceImpl;
import team.tit.gluttonoussnake.ui.BasePanel;
import team.tit.gluttonoussnake.ui.gamepanel.GScreen.GameState;
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

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}

	public GamePanel() {
	}

	public GamePanel(ResultInfo info) {
		this.infoMain = info;
	}

	public GamePanel(ResultInfo infologin, ResultInfo infomain) {
		this.infoLogin = infologin;
		this.infoMain = infomain;
	}

	@Override
	public void init() {
		// 1.实例化对象
		subRoot = new AnchorPane();
		background = new Background(subRoot);
		subMenu = new SubMenu();
		gameScreen = new GameScreen(subMenu, subRoot, infoMain);

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
					if (gameScreen.info.getScore() >= gameScreen.getTarget() && gameScreen.getType() == 1) {
						// 开始下一关
						if (gameScreen.getMap() > 3) {
							gameScreen.setMap(1);
						}
						UIManager.getUiManager().delPanel("GamePanel");
						Game game = (Game) infoMain.getData();
						game.setWid(gameScreen.getMap());
						System.out.println("map = " + gameScreen.getMap());
						UIManager.getUiManager().regPanel("GamePanel", new GamePanel(infoLogin, infoMain));
						UIManager.getUiManager().gotoPanel("GamePanel");
					}
					gameScreen.setGameState(GameState.GAME_END);
				} else if (gameScreen.getGameState() == GameState.GAME_END) {
					subRoot.getChildren().add(subMenu);
					gameScreen.setGameState(GameState.GAME_EXIT);
				} else if (gameScreen.getGameState() == GameState.GAME_WIN) {
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

			@SuppressWarnings("deprecation")
			@Override
			public void handle(MouseEvent event) {
				loadClickAudio();
				System.out.println(gameScreen.getGameState());
				if (gameScreen.getGameState() == GameState.GAME_PAUSE) {
					gameScreen.setGameState(GameState.GAME_EXIT);
					if (gameScreen.getGameState() == GameState.GAME_EXIT) {
						AudioManager.getAudioManager().getAudio("GameAudio").close();
						LinkedList<SnakeNode> list = gameScreen.snake.getList();

						// 封装数据 保存数据
						if (gameScreen.maInfo != null) {
							Game game2 = (Game) gameScreen.maInfo.getData();

							Snake snake2 = new Snake();
							Food food2 = new Food();
							Wall wall2 = new Wall();
							snake2.setId(game2.getSid());
							LinkedList<SnakeNode> list1 = new LinkedList<SnakeNode>();
							for (int i = 0; i < gameScreen.snake.getList().size(); i++) {
								list1.addLast(new SnakeNode(list.get(i).getX(), list.get(i).getY()));
							}
							snake2.setList(list1);
							snake2.setDir(gameScreen.snake.getDir());
							food2.setId(game2.getFid());
							food2.setX(gameScreen.food.getX());
							food2.setY(gameScreen.food.getY());
							wall2.setId(gameScreen.wall.getId());
							game2.setSanke(snake2);
							game2.setFood(food2);
							game2.setWall(wall2);
							GameService service = new GameServiceImpl();
							service.saveGameAll(game2);
						}
						gameScreen.thread.stop();
						gameScreen.timeline.stop();
					}
					System.out.println("我执行了");
				}
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