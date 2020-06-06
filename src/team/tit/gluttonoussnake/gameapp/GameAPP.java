package team.tit.gluttonoussnake.gameapp;

import static team.tit.gluttonoussnake.constant.Constant.GAME_STAGE_H;
import static team.tit.gluttonoussnake.constant.Constant.GAME_STAGE_TITLE;
import static team.tit.gluttonoussnake.constant.Constant.GAME_STAGE_W;
import static team.tit.gluttonoussnake.constant.Constant.GAME_STAGE_X;
import static team.tit.gluttonoussnake.constant.Constant.GAME_STAGE_Y;

import java.util.LinkedList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import team.tit.gluttonoussnake.animation.npc.Food;
import team.tit.gluttonoussnake.animation.npc.Wall;
import team.tit.gluttonoussnake.animation.player.Snake;
import team.tit.gluttonoussnake.animation.player.SnakeNode;
import team.tit.gluttonoussnake.animation.player.Snake.DIR;
import team.tit.gluttonoussnake.domain.Game;
import team.tit.gluttonoussnake.manager.impl.AudioManager;
import team.tit.gluttonoussnake.manager.impl.UIManager;
import team.tit.gluttonoussnake.service.GameService;
import team.tit.gluttonoussnake.service.impl.GameServiceImpl;
import team.tit.gluttonoussnake.ui.gamepanel.GScreen.GameState;
import team.tit.gluttonoussnake.ui.gamepanel.GamePanel;

/**
 * 游戏应用类，控制游戏应用的生命周期
 * 
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月21日
 */
public class GameAPP extends Application {

	public static AnchorPane root; // 根节点
	public static Scene scene; // 场景图

	@Override
	public void init() throws Exception {
		root = new AnchorPane();
		scene = new Scene(root);
		AudioManager.getAudioManager().onLaunch();
		UIManager.getUiManager().onLaunch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		showGameStage(primaryStage);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void stop() throws Exception {
		GamePanel gamePanel = (GamePanel) UIManager.getUiManager().getPanel("GamePanel");
		if (gamePanel != null) {
			System.out.println(gamePanel.getGameScreen().getGameState());
			if (gamePanel.getGameScreen().getGameState() == GameState.GAME_PAUSE
					|| gamePanel.getGameScreen().getGameState() == GameState.GAME_START
					|| gamePanel.getGameScreen().getGameState() == GameState.GAME_CONTINUE) {
				gamePanel.getGameScreen().setGameState(GameState.GAME_EXIT);
				if (gamePanel.getGameScreen().getGameState() == GameState.GAME_EXIT) {
					AudioManager.getAudioManager().getAudio("GameAudio").close();
					LinkedList<SnakeNode> list = gamePanel.getGameScreen().snake.getList();

					// 封装数据 保存数据
					if (gamePanel.getGameScreen().maInfo != null) {
						Game game2 = (Game) gamePanel.getGameScreen().maInfo.getData();

						Snake snake2 = new Snake();
						Food food2 = new Food();
						Wall wall2 = new Wall();
						snake2.setId(game2.getSid());
						LinkedList<SnakeNode> list1 = new LinkedList<SnakeNode>();
						for (int i = 0; i < gamePanel.getGameScreen().snake.getList().size(); i++) {
							list1.addLast(new SnakeNode(list.get(i).getX(), list.get(i).getY()));
						}
						snake2.setList(list1);
						snake2.setDir(gamePanel.getGameScreen().snake.getDir());
						food2.setId(game2.getFid());
						food2.setX(gamePanel.getGameScreen().food.getX());
						food2.setY(gamePanel.getGameScreen().food.getY());
						wall2.setId(gamePanel.getGameScreen().wall.getId());
						game2.setSanke(snake2);
						game2.setFood(food2);
						game2.setWall(wall2);
						GameService service = new GameServiceImpl();
						service.saveGameAll(game2);
					}
					gamePanel.getGameScreen().thread.stop();
					gamePanel.getGameScreen().timeline.stop();
				}
			}

			if (gamePanel.getGameScreen().getGameState() == GameState.GAME_WIN) {
				if (gamePanel.getGameScreen().maInfo != null) {
					Game game1 = (Game) gamePanel.getGameScreen().maInfo.getData();
					game1.setWid(gamePanel.getGameScreen().map++);
					Snake snake1 = new Snake();
					snake1.setId(game1.getSid());
					snake1.setDir(DIR.RIGHT);
					Food food1 = new Food();
					food1.setId(game1.getFid());
					Wall wall1 = new Wall();
					wall1.setId(game1.getWid());
					game1.setSanke(snake1);
					game1.setFood(food1);
					game1.setWall(wall1);
					GameService service = new GameServiceImpl();
					service.saveGameAll(game1);
				}

				gamePanel.getGameScreen().thread.stop();
				gamePanel.getGameScreen().timeline.stop();
			}
		}

		UIManager.getUiManager().onFinish();
		AudioManager.getAudioManager().onFinish();
	}

	/**
	 * 设置游戏窗口的属性，并且显示游戏窗口
	 * 
	 * @param gameStage 游戏窗口
	 */
	private void showGameStage(Stage gameStage) {
		gameStage.setTitle(GAME_STAGE_TITLE); // 设置窗口的标题
		gameStage.getIcons()
				.add(new Image(this.getClass().getClassLoader().getResource("images/GameStage.png").toExternalForm())); // 设置窗口的图标
		gameStage.setX(GAME_STAGE_X); // 设置窗口的坐标X
		gameStage.setY(GAME_STAGE_Y); // 设置窗口的坐标Y
		gameStage.setWidth(GAME_STAGE_W); // 设置窗口的宽度
		gameStage.setHeight(GAME_STAGE_H); // 设置窗口的高度
		gameStage.setResizable(false); // 设置窗口大小不可变
		gameStage.setScene(scene); // 将场景图加到窗口中
		gameStage.show(); // 显示窗口
	}
}
