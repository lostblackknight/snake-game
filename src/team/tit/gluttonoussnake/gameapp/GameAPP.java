package team.tit.gluttonoussnake.gameapp;

import static team.tit.gluttonoussnake.constant.Constant.GAME_STAGE_H;
import static team.tit.gluttonoussnake.constant.Constant.GAME_STAGE_TITLE;
import static team.tit.gluttonoussnake.constant.Constant.GAME_STAGE_W;
import static team.tit.gluttonoussnake.constant.Constant.GAME_STAGE_X;
import static team.tit.gluttonoussnake.constant.Constant.GAME_STAGE_Y;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import team.tit.gluttonoussnake.manager.impl.AudioManager;
import team.tit.gluttonoussnake.manager.impl.UIManager;

/**
 * 游戏应用类，控制游戏应用的生命周期
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月21日
 */
public class GameAPP extends Application {

	public static AnchorPane root;		//根节点
	public static Scene scene;			//场景图
	
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

	@Override
	public void stop() throws Exception {
		UIManager.getUiManager().onFinish();
		AudioManager.getAudioManager().onFinish();
	}
	
	/**
	 * 设置游戏窗口的属性，并且显示游戏窗口
	 * @param gameStage 游戏窗口
	 */
	private void showGameStage(Stage gameStage) {
		gameStage.setTitle(GAME_STAGE_TITLE);								//设置窗口的标题
		gameStage.getIcons().add(
				new Image(this.getClass().getClassLoader().getResource("images/GameStage.png").toExternalForm())
				);															//设置窗口的图标
		gameStage.setX(GAME_STAGE_X);										//设置窗口的坐标X
		gameStage.setY(GAME_STAGE_Y);										//设置窗口的坐标Y
		gameStage.setWidth(GAME_STAGE_W);									//设置窗口的宽度
		gameStage.setHeight(GAME_STAGE_H);									//设置窗口的高度
		gameStage.setResizable(false);										//设置窗口大小不可变
		gameStage.setScene(scene);											//将场景图加到窗口中
		gameStage.show();													//显示窗口
	}
}
