/*
 *
 */
package team.tit.gluttonoussnake.ui.gamepanel;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import team.tit.gluttonoussnake.animation.BaseObject;

/**
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月26日
 */
public class GScreen extends Canvas {

	// 游戏状态
	protected enum GameState {
		GAME_START, GAME_PAUSE, GAME_CONTINUE, GAME_END ,GAME_EXIT
	};

	public List<BaseObject> objects = new ArrayList<BaseObject>();
	protected GameState gameState;

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public Timeline timeline;
	public KeyFrame keyFrame;
	public int duration = 10;
	public Thread thread;

	public GScreen() {
		this.setWidth(1280);
		this.setHeight(720);
		initAllThread();
	}

	public void onKeyPressed(KeyEvent event) {
		for (BaseObject object : objects) {
			object.onKeyPressed(event);
		}
	}

	public void onKeyReleased(KeyEvent event) {
		for (BaseObject object : objects) {
			object.onKeyReleased(event);
		}
	}

	public void addObject(BaseObject baseObject) {
		this.objects.add(baseObject);
	}

	public void removeObject(BaseObject baseObject) {
		this.objects.remove(baseObject);
	}

	public void removeObjectAtIndex(int index) {
		this.objects.remove(index);
	}

	public void draw(GraphicsContext gc) {
		gc.setEffect(null);
		gc.clearRect(0, 0, 1280, 720);
		for (int i = 0; i < objects.size(); i++) {
			BaseObject object = objects.get(i);
			object.draw(gc);
		}
	}

	public void update() {
		for (int i = 0; i < objects.size(); i++) {
			BaseObject object = objects.get(i);
			object.update();
		}
	}

	private void initAllThread() {
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		keyFrame = new KeyFrame(Duration.millis(duration), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// 重复画
				draw(getGraphicsContext2D());
			}
		});
		timeline.getKeyFrames().add(keyFrame);
		thread = new Thread() {
			@Override
			public void run() {
				// 更新
				update();
			}
		};
		thread.setDaemon(true);// 设为守护线程
	}

	public void start() {
		timeline.play();// 画游戏对象
		thread.start();// 更新游戏对象
	}
}
