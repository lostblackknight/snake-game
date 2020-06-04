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
public class GameScreen extends GScreen
{

	Grid grid = new Grid();
	GameInfo info = new GameInfo();
	Snake snake = new Snake();
	Food food = new Food();
	Wall wall = new Wall();
	SubMenu subMenu;
	AnchorPane subRoot;
	ResultInfo maInfo;
	private GameOverPanel gameOverPanel;
	private GameWinPanel gameWinPanel = new GameWinPanel();

	public GameScreen(SubMenu subMenu, AnchorPane subRoot, ResultInfo maInfo)
	{
		super();
		this.subMenu = subMenu;
		this.subRoot = subRoot;
		this.maInfo = maInfo;
		addObject(grid);
		addObject(info);

		if (maInfo != null)
		{
			Game game = (Game) maInfo.getData();
			snake.setId(game.getSid());
			food.setId(game.getFid());
			wall.setId(game.getWid());
			
			ArrayList<Point> points = null;
			if (wall.getId() == 0)
			{
				points = null;
			}
			else
			{
				points = LoadingPanel.getList().get(wall.getId()).getPoints();
			}
			wall.setPoints(points);
			info.setLength(snake.getList().size() - 1);
			info.setScore((snake.getList().size() - 3) * 10);
		}

		addObject(food);
		addObject(wall);
		addObject(snake);
	}

	@SuppressWarnings(
	{ "deprecation", "static-access" })
	@Override
	public void draw(GraphicsContext gc)
	{
		super.draw(gc);
		if (gameState == GameState.GAME_PAUSE)
		{
			return;
		}
		if (gameState == GameState.GAME_END)
		{
			AudioManager.getAudioManager().getAudio("GameAudio").close();
			loadGameOverAudio();
			// loadGameWinAudio();

			/*
			 * 拿到蛇的位置，食物的位置，身体的位置
			 */
			int sid = snake.getId();
			int snakeHeadX = snake.getHead().getX();
			int snakeHeadY = snake.getHead().getY();
			DIR dir = snake.getDir();
			int length = info.getLength();
			int score = info.getScore();
			int fid = food.getId();
			int wid = wall.getId();

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

			for (int i = 0; i < list.size(); i++)
			{
				System.out.println("第" + i + "个蛇身的X" + list.get(i).getX());
				System.out.println("第" + i + "个蛇身的Y" + list.get(i).getY());
			}

			System.out.println("food = " + food.getX());
			System.out.println("food = " + food.getY());

			// 封装数据 保存数据
			if (maInfo != null)
			{
				Game game1 = (Game) maInfo.getData();
				Snake snake1 = new Snake();
				snake1.setId(sid);
				snake1.setDir(snake.getDir());
				Food food1 = new Food();
				food1.setId(fid);
				Wall wall1 = new Wall();
				wall1.setId(wid);
				System.out.println("game1dir:" + snake.getDir());
				game1.setSanke(snake1);
				game1.setFood(food1);
				game1.setWall(wall1);
				GameService service = new GameServiceImpl();
				service.saveGameAll(game1);
			}

			thread.stop();
			timeline.stop();
		}
		if (gameState == GameState.GAME_EXIT)
		{
			AudioManager.getAudioManager().getAudio("GameAudio").close();
			int sid = snake.getId();
			int snakeHeadX = snake.getHead().getX();
			int snakeHeadY = snake.getHead().getY();
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

			for (int i = 0; i < list.size(); i++)
			{
				System.out.println("第" + i + "个蛇身的X" + list.get(i).getX());
				System.out.println("第" + i + "个蛇身的Y" + list.get(i).getY());
			}

			System.out.println("food = " + food.getX());
			System.out.println("food = " + food.getY());

			// 封装数据 保存数据
			if (maInfo != null)
			{
				Game game2 = (Game) maInfo.getData();

				Snake snake2 = new Snake();
				Food food2 = new Food();
				Wall wall2 = new Wall();
				snake2.setId(snake.getId());
				LinkedList<SnakeNode> list1 = new LinkedList<SnakeNode>();
				for (int i = 0; i < snake.getList().size(); i++)
				{
					list1.addLast(new SnakeNode(list.get(i).getX(), list.get(i).getY()));
				}
				snake2.setList(list1);
				snake2.setDir(snake.getDir());
				System.out.println("game2dir:" + snake.getDir());
				food2.setId(food.getId());
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
	}

	@Override
	public void update()
	{
		while (gameState != GameState.GAME_END)
		{
			if (gameState != GameState.GAME_PAUSE)
			{
				super.update();
				eatFood(snake);
				eatBody(snake);
				eatWall(snake);
			}
			try
			{
				Thread.sleep(snake.getSleepTime());
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void eatFood(Snake snake)
	{
		if (snake.isEatFood(food))
		{
			loadEatFoodAudio();
			snake.grow();

			// 食物不在墙上的生成食物（好像不对）
			food.createRandomFoodNotInWall(wall.getPoints());

			info.setLength(snake.getLength() - 1);
			info.setScore((snake.getLength() - 3) * 10);
		}
	}

	public void eatBody(Snake snake)
	{
		if (snake.isEatBody())
		{
			gameState = GameState.GAME_END;
			loadSnakeDeathAudio();
		}
	}

	public void eatWall(Snake snake)
	{
		if (snake.isEatWall(wall.getPoints()))
		{
			gameState = GameState.GAME_END;
			loadSnakeDeathAudio();
		}
	}

	@Override
	public void onKeyReleased(KeyEvent event)
	{
		super.onKeyReleased(event);
		if (event.getCode() == KeyCode.ESCAPE)
		{
			if (gameState == GameState.GAME_PAUSE)
			{
				subRoot.getChildren().remove(subMenu);
				gameState = GameState.GAME_CONTINUE;
			}
			else if (gameState == GameState.GAME_CONTINUE || gameState == GameState.GAME_START)
			{
				subRoot.getChildren().add(subMenu);
				gameState = GameState.GAME_PAUSE;
			}
			else if (gameState == GameState.GAME_END)
			{
				subRoot.getChildren().add(subMenu);
				gameState = GameState.GAME_EXIT;
			}
			else if (gameState == GameState.GAME_EXIT)
			{
				subRoot.getChildren().remove(subMenu);
				gameState = GameState.GAME_END;
			}
		}
		else if (event.getCode() == KeyCode.U)
		{
			snake.setSleepTime(300);
		}
		else if (event.getCode() == KeyCode.I)
		{
			snake.setSleepTime(300);
		}
	}

	@Override
	public void onKeyPressed(KeyEvent event)
	{
		super.onKeyPressed(event);
	}

	private void loadSnakeDeathAudio()
	{
		AudioManager.getAudioManager().getAudio("SnakeDeathAudio").init();
		AudioManager.getAudioManager().getAudio("SnakeDeathAudio").getAudio().volumeProperty()
				.bind(OptionsMenuBox.slider2.valueProperty());
		AudioManager.getAudioManager().getAudio("SnakeDeathAudio").play();
	}

	private void loadEatFoodAudio()
	{
		AudioManager.getAudioManager().getAudio("EatFoodAudio").init();
		AudioManager.getAudioManager().getAudio("EatFoodAudio").getAudio().volumeProperty()
				.bind(OptionsMenuBox.slider2.valueProperty());
		AudioManager.getAudioManager().getAudio("EatFoodAudio").play();
	}

	private void loadGameOverAudio()
	{
		AudioManager.getAudioManager().getAudio("GameOverAudio").init();
		AudioManager.getAudioManager().getAudio("GameOverAudio").getAudio().volumeProperty()
				.bind(OptionsMenuBox.slider2.valueProperty());
		AudioManager.getAudioManager().getAudio("GameOverAudio").play();
	}

	private void loadGameWinAudio()
	{
		AudioManager.getAudioManager().getAudio("GameWinAudio").init();
		AudioManager.getAudioManager().getAudio("GameWinAudio").getAudio().volumeProperty()
				.bind(OptionsMenuBox.slider2.valueProperty());
		AudioManager.getAudioManager().getAudio("GameWinAudio").play();
	}
}
