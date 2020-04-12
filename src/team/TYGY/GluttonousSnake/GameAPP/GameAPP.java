package team.TYGY.GluttonousSnake.GameAPP;

import static team.TYGY.GluttonousSnake.DB.Constant.GAME_STAGE_H;
import static team.TYGY.GluttonousSnake.DB.Constant.GAME_STAGE_TITLE;
import static team.TYGY.GluttonousSnake.DB.Constant.GAME_STAGE_W;
import static team.TYGY.GluttonousSnake.DB.Constant.GAME_STAGE_X;
import static team.TYGY.GluttonousSnake.DB.Constant.GAME_STAGE_Y;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import team.TYGY.GluttonousSnake.UI.UIManager;

/**
 * 
 * @ClassName: GameAPP
 * @description: TODO 管理应用的生命周期
 * @author: 陈思祥
 * @version: 1.3
 * @date: 2020年4月5日-下午12:00:39
 *
 */
public class GameAPP extends Application {

	public static AnchorPane root;											//根节点
	public static Scene scene;												//场景图
	
	
	@Override
	public void init() throws Exception {
		root = new AnchorPane();
		scene = new Scene(root);
		
		UIManager.getUiManager().onLaunch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(GAME_STAGE_TITLE);							//设置窗口的标题
		primaryStage.getIcons().add(new Image("images/GameStage.png"));		//设置窗口的图标
		primaryStage.setWidth(GAME_STAGE_W);								//设置窗口的宽度
		primaryStage.setHeight(GAME_STAGE_H);								//设置窗口的高度
		primaryStage.setX(GAME_STAGE_X);									//设置窗口的坐标X
		primaryStage.setY(GAME_STAGE_Y);									//设置窗口的坐标Y
		primaryStage.setResizable(false);									//设置窗口大小不可变
		primaryStage.show();												//显示窗口
		primaryStage.setScene(scene);										//将场景图加到窗口中
//		primaryStage.setFullScreen(true);									//全屏
	}

	@Override
	public void stop() throws Exception {
		UIManager.getUiManager().onFinish();
		System.out.println("应用退出");
	}
}
