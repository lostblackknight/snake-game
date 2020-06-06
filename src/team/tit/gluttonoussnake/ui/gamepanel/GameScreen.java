/*
 *
 */
package team.tit.gluttonoussnake.ui.gamepanel;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import static team.tit.gluttonoussnake.constant.Constant.*;
import javafx.scene.layout.AnchorPane;
import team.tit.gluttonoussnake.animation.info.GameInfo;
import team.tit.gluttonoussnake.animation.npc.Food;
import team.tit.gluttonoussnake.animation.npc.Grid;
import team.tit.gluttonoussnake.animation.npc.Wall;
import team.tit.gluttonoussnake.animation.player.Snake;
import team.tit.gluttonoussnake.animation.player.Snake.DIR;
import team.tit.gluttonoussnake.manager.impl.AudioManager;
import team.tit.gluttonoussnake.service.GameService;
import team.tit.gluttonoussnake.service.impl.GameServiceImpl;
import team.tit.gluttonoussnake.ui.loadingpanel.LoadingPanel;
import team.tit.gluttonoussnake.ui.mainmenupanel.OptionsMenuBox;
import team.tit.gluttonoussnake.animation.player.SnakeNode;
import team.tit.gluttonoussnake.domain.Game;
import team.tit.gluttonoussnake.domain.ResultInfo;

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
	public Snake snake = new Snake();
	public Wall wall = new Wall();
	public Food food = new Food();
	public int map;
	private int type;
	private int target = TARGET;
	SubMenu subMenu;
	AnchorPane subRoot;
	public ResultInfo maInfo;
	private GameOverPanel gameOverPanel;
	private GameWinPanel gameWinPanel = new GameWinPanel();

	public GameScreen(SubMenu subMenu, AnchorPane subRoot, ResultInfo maInfo) {
		super();
		this.subMenu = subMenu;
		this.subRoot = subRoot;
		this.maInfo = maInfo;
		addObject(grid);
		addObject(info);

		if (maInfo != null && maInfo.isFlag() == true) {
			Game game = (Game) maInfo.getData();
			if (game.getType() == 0) {
				// int headX = game.getSanke().getSnakeHeadX();
				// int headY = game.getSanke().getSnakeHeadY();
				LinkedList<SnakeNode> body = game.getSanke().getList();
				int foodX = game.getFood().getFoodX();
				int foodY = game.getFood().getFoodY();
				// gameScreen.snake.setX(headX);
				// gameScreen.snake.setY(headY);
				snake.setId(game.getSid());
				snake.setDir(game.getSanke().getDir());
				snake.setList(body);
				food.setId(game.getFid());
				food.setX(foodX);
				food.setY(foodY);
				wall.setId(game.getWid());
				info.setLength(snake.getList().size() - 1);
				info.setScore((snake.getList().size() - 3) * 10);
				Game game1 = (Game) maInfo.getData();
				ArrayList<Point> points = null;
				if (game1.getWid() == 0) {
					System.out.println("***休闲***" + game1.getWid());
					points = null;
				} else {
					points = LoadingPanel.getList().get(game1.getWid()).getPoints();
				}
				wall.setPoints(points);
				type = 0;
			}

			if (game.getType() == 1) {
				// int headX = game.getSanke().getSnakeHeadX();
				// int headY = game.getSanke().getSnakeHeadY();
				LinkedList<SnakeNode> body = game.getSanke().getList();
				int foodX = game.getFood().getFoodX();
				int foodY = game.getFood().getFoodY();
				// gameScreen.snake.setX(headX);
				// gameScreen.snake.setY(headY);
				snake.setId(game.getSid());
				snake.setDir(game.getSanke().getDir());
				snake.setList(body);
				food.setId(game.getFid());
				food.setX(foodX);
				food.setY(foodY);
				wall.setId(game.getWid());
				info.setLength(snake.getList().size() - 1);
				info.setScore((snake.getList().size() - 3) * 10);
				Game game1 = (Game) maInfo.getData();
				ArrayList<Point> points = null;
				if (game1.getWid() == 0) {
					points = null;
				} else {
					System.out.println("***冒险***" + game1.getWid());
					points = LoadingPanel.getList().get(game1.getWid()).getPoints();
				}
				wall.setPoints(points);
				map = game.getWid();
				type = 1;
			}
		}

		if (maInfo != null && maInfo.isFlag() == false) {
			Game game1 = (Game) maInfo.getData();
			ArrayList<Point> points = null;
			if (game1.getWid() == 0) {
				System.out.println("***休闲 new game***" + game1.getWid());
				points = null;
				type = 0;
			} else {
				System.out.println("***冒险 new game***" + game1.getWid());
				points = LoadingPanel.getList().get(game1.getWid()).getPoints();
				map = game1.getWid();
				type = 1;
			}
			wall.setPoints(points);
		}

		addObject(food);
		addObject(wall);
		addObject(snake);
	}

	public int getMap() {
		return map;
	}

	public void setMap(int map) {
		this.map = map;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
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
			// loadGameWinAudio();

			/*
			 * 拿到蛇的位置，食物的位置，身体的位置
			 */

			gameOverPanel = new GameOverPanel();

			subRoot.setTopAnchor(gameOverPanel, 260.0);
			subRoot.setLeftAnchor(gameOverPanel, 440.0);
			subRoot.getChildren().add(gameOverPanel);

			// 封装数据 保存数据
			if (maInfo != null) {
				Game game1 = (Game) maInfo.getData();
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

			thread.stop();
			timeline.stop();
		}
		if (gameState == GameState.GAME_EXIT) {
			AudioManager.getAudioManager().getAudio("GameAudio").close();
			LinkedList<SnakeNode> list = snake.getList();

			// 封装数据 保存数据
			if (maInfo != null) {
				Game game2 = (Game) maInfo.getData();

				Snake snake2 = new Snake();
				Food food2 = new Food();
				Wall wall2 = new Wall();
				snake2.setId(game2.getSid());
				LinkedList<SnakeNode> list1 = new LinkedList<SnakeNode>();
				for (int i = 0; i < snake.getList().size(); i++) {
					list1.addLast(new SnakeNode(list.get(i).getX(), list.get(i).getY()));
				}
				snake2.setList(list1);
				snake2.setDir(snake.getDir());
				food2.setId(game2.getFid());
				food2.setX(food.getX());
				food2.setY(food.getY());
				wall2.setId(wall.getId());
				game2.setSanke(snake2);
				game2.setFood(food2);
				game2.setWall(wall2);
				GameService service = new GameServiceImpl();
				service.saveGameAll(game2);
			}
			thread.stop();
			timeline.stop();
		}
		if (gameState == GameState.GAME_WIN) {
			AudioManager.getAudioManager().getAudio("GameAudio").close();
			loadGameWinAudio();

			gameWinPanel = new GameWinPanel();

			subRoot.setTopAnchor(gameWinPanel, 260.0);
			subRoot.setLeftAnchor(gameWinPanel, 440.0);
			subRoot.getChildren().add(gameWinPanel);

			// 封装数据 保存数据
			if (maInfo != null) {
				Game game1 = (Game) maInfo.getData();
				game1.setWid(map++);
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
				gameNext(info);
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

			food.createRandomFoodNotInWall(wall.getPoints());
			info.setLength(snake.getList().size() - 1);
			info.setScore((snake.getList().size() - 3) * DEGREE);
		}
	}

	public void eatBody(Snake snake) {
		if (snake.isEatBody()) {
			gameState = GameState.GAME_END;
			loadSnakeDeathAudio();
		}
	}

	public void eatWall(Snake snake) {
		if (snake.isEatWall(wall.getPoints())) {
			gameState = GameState.GAME_END;
			loadSnakeDeathAudio();
		}
	}

	public void gameNext(GameInfo info) {
		if (info.getScore() >= target) {
			if (type == 1) {
				gameState = GameState.GAME_WIN;
			}
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
			} else if (gameState == GameState.GAME_END || gameState == GameState.GAME_WIN) {
				subRoot.getChildren().add(subMenu);
				gameState = GameState.GAME_EXIT;
			} else if (gameState == GameState.GAME_EXIT) {
				subRoot.getChildren().remove(subMenu);
				if (info.getScore() >= target && type == 1) {
					gameState = GameState.GAME_WIN;
				}
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
		AudioManager.getAudioManager().getAudio("SnakeDeathAudio").getAudio().volumeProperty()
				.bind(OptionsMenuBox.slider2.valueProperty());
		AudioManager.getAudioManager().getAudio("SnakeDeathAudio").play();
	}

	private void loadEatFoodAudio() {
		AudioManager.getAudioManager().getAudio("EatFoodAudio").init();
		AudioManager.getAudioManager().getAudio("EatFoodAudio").getAudio().volumeProperty()
				.bind(OptionsMenuBox.slider2.valueProperty());
		AudioManager.getAudioManager().getAudio("EatFoodAudio").play();
	}

	private void loadGameOverAudio() {
		AudioManager.getAudioManager().getAudio("GameOverAudio").init();
		AudioManager.getAudioManager().getAudio("GameOverAudio").getAudio().volumeProperty()
				.bind(OptionsMenuBox.slider2.valueProperty());
		AudioManager.getAudioManager().getAudio("GameOverAudio").play();
	}

	private void loadGameWinAudio() {
		AudioManager.getAudioManager().getAudio("GameWinAudio").init();
		AudioManager.getAudioManager().getAudio("GameWinAudio").getAudio().volumeProperty()
				.bind(OptionsMenuBox.slider2.valueProperty());
		AudioManager.getAudioManager().getAudio("GameWinAudio").play();
	}
}
