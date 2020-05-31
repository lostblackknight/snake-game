/*
 *
 */
package team.tit.gluttonoussnake.ui.gamepanel;

import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import team.tit.gluttonoussnake.animation.info.GameInfo;
import team.tit.gluttonoussnake.animation.npc.Food;
import team.tit.gluttonoussnake.animation.npc.Grid;
import team.tit.gluttonoussnake.animation.npc.Wall;
import team.tit.gluttonoussnake.animation.player.Snake;
import team.tit.gluttonoussnake.animation.player.Snake.DIR;
import team.tit.gluttonoussnake.manager.impl.AudioManager;
import team.tit.gluttonoussnake.ui.mainmenupanel.OptionsMenuBox;
import team.tit.gluttonoussnake.animation.player.SnakeNode;

/**
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月26日
 */
public class GameScreen extends GScreen {
	
	Grid grid = new Grid();
	GameInfo info = new GameInfo();
	Snake snake = new Snake();
	Food food = new Food();
	Wall wall = new Wall();
	SubMenu subMenu;
	AnchorPane subRoot;
	private GameOverPanel gameOverPanel;
	private GameWinPanel gameWinPanel = new GameWinPanel();
	
	public GameScreen(SubMenu subMenu, AnchorPane subRoot) {
		super();
		this.subMenu = subMenu;
		this.subRoot = subRoot;
		addObject(grid);
		addObject(info);
		addObject(food);
		addObject(wall);
		addObject(snake);
	}
	
	@SuppressWarnings({ "deprecation", "static-access" })
	@Override
	public void draw(GraphicsContext gc) {
		super.draw(gc);
		if (gameState == GameState.GAME_PAUSE) {
			return;
		}
		if (gameState == GameState.GAME_END) {
			AudioManager.getAudioManager().getAudio("GameAudio").close();
			loadGameOverAudio();
//			loadGameWinAudio();
			
			/*
			 * 拿到蛇的位置，食物的位置，身体的位置
			 */
			int sid = snake.getId();
			int snakeHeadX = snake.getX();
			int snakeHeadY = snake.getY();
			DIR dir = snake.getDir();
			int length = info.getLength();
			int score = info.getScore();
			
			gameOverPanel = new GameOverPanel();
			
			subRoot.setTopAnchor(gameOverPanel, 260.0);
			subRoot.setLeftAnchor(gameOverPanel, 440.0);
			subRoot.getChildren().add(gameOverPanel);
			
			System.out.println("--------------------");
			System.out.println("snakeHeadX = " + snakeHeadX);
			System.out.println("snakeHeadY = " + snakeHeadY);
			System.out.println("dir = " + dir);
			System.out.println("length = " + length);
			System.out.println("score = " + score);
			LinkedList<SnakeNode> list = snake.getList();
			
			for (int i = 1; i < list.size(); i++) {
				System.out.println("第" + i + "个蛇身的X" + list.get(i).getX());
				System.out.println("第" + i + "个蛇身的Y" + list.get(i).getY());
			}
			
			
			System.out.println("food = " + food.getX());
			System.out.println("food = " + food.getY());
			
			thread.stop();
			timeline.stop();
		}
		if (gameState == GameState.GAME_EXIT) {
			AudioManager.getAudioManager().getAudio("GameAudio").close();
			int sid = snake.getId();
			int snakeHeadX = snake.getX();
			int snakeHeadY = snake.getY();
			DIR dir = snake.getDir();
			int length = info.getLength();
			int score = info.getScore();
			
			System.out.println("--------------------");
			System.out.println("snakeHeadX = " + snakeHeadX);
			System.out.println("snakeHeadY = " + snakeHeadY);
			System.out.println("dir = " + dir);
			System.out.println("length = " + length);
			System.out.println("score = " + score);
			LinkedList<SnakeNode> list = snake.getList();
			
			for (int i = 1; i < list.size(); i++) {
				System.out.println("第" + i + "个蛇身的X" + list.get(i).getX());
				System.out.println("第" + i + "个蛇身的Y" + list.get(i).getY());
			}
			
			System.out.println("food = " + food.getX());
			System.out.println("food = " + food.getY());
			thread.stop();
			timeline.stop();
		}
	}
	
	@Override
	public void update() {
		while (gameState != GameState.GAME_END) {
			if (gameState != GameState.GAME_PAUSE) {
				super.update();
				eatFood(snake);
				eatBody(snake);
				eatWall(snake);
			}
			try {
				Thread.sleep(snake.getSleepTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void eatFood(Snake snake) {
		if (snake.isEatFood(food)) {
			loadEatFoodAudio();
			snake.grow();
			food.createRandomFood();
			info.setLength(snake.getLength() - 1);
			info.setScore((snake.getLength() - 3) * 10);
		}
	}
	
	public void eatBody(Snake snake) {
		if (snake.isEatBody()) {
			gameState = GameState.GAME_END;
			loadSnakeDeathAudio();
		}
	}
	
	public void eatWall(Snake snake) {
		if (snake.isEatWall(wall)) {
			gameState = GameState.GAME_END;
			loadSnakeDeathAudio();
		}
	}
	
	@Override
	public void onKeyReleased(KeyEvent event) {
		super.onKeyReleased(event);
		if (event.getCode() == KeyCode.ESCAPE) {
			if (gameState == GameState.GAME_PAUSE) {
				subRoot.getChildren().remove(subMenu);
				gameState = GameState.GAME_CONTINUE;
			} else if (gameState == GameState.GAME_CONTINUE || gameState == GameState.GAME_START) {
				subRoot.getChildren().add(subMenu);
				gameState = GameState.GAME_PAUSE;
			} else if (gameState == GameState.GAME_END) {
				subRoot.getChildren().add(subMenu);
				gameState = GameState.GAME_EXIT;
			} else if (gameState == GameState.GAME_EXIT) {
				subRoot.getChildren().remove(subMenu);
				gameState = GameState.GAME_END;
			} 
		} else if (event.getCode() == KeyCode.U) {
			snake.setSleepTime(300);
		} else if (event.getCode() == KeyCode.I) {
			snake.setSleepTime(300);
		}
	}
	
	@Override
	public void onKeyPressed(KeyEvent event) {
		super.onKeyPressed(event);
	}
	
	private void loadSnakeDeathAudio() {
		AudioManager.getAudioManager().getAudio("SnakeDeathAudio").init();
		AudioManager.getAudioManager().getAudio("SnakeDeathAudio").getAudio().volumeProperty().bind(OptionsMenuBox.slider2.valueProperty());
		AudioManager.getAudioManager().getAudio("SnakeDeathAudio").play();
	}
	
	private void loadEatFoodAudio() {
		AudioManager.getAudioManager().getAudio("EatFoodAudio").init();
		AudioManager.getAudioManager().getAudio("EatFoodAudio").getAudio().volumeProperty().bind(OptionsMenuBox.slider2.valueProperty());
		AudioManager.getAudioManager().getAudio("EatFoodAudio").play();
	}
	
	private void loadGameOverAudio() {
		AudioManager.getAudioManager().getAudio("GameOverAudio").init();
		AudioManager.getAudioManager().getAudio("GameOverAudio").getAudio().volumeProperty().bind(OptionsMenuBox.slider2.valueProperty());
		AudioManager.getAudioManager().getAudio("GameOverAudio").play();
	}
	
	private void loadGameWinAudio() {
		AudioManager.getAudioManager().getAudio("GameWinAudio").init();
		AudioManager.getAudioManager().getAudio("GameWinAudio").getAudio().volumeProperty().bind(OptionsMenuBox.slider2.valueProperty());
		AudioManager.getAudioManager().getAudio("GameWinAudio").play();
	}
}
