package team.tit.gluttonoussnake.main;

import javafx.application.Application;
import team.tit.gluttonoussnake.gameapp.GameAPP;

/**
 * 包含启动游戏应用的main方法
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月21日
 */
public class Main {
	
	/**
	 * 应用程序的主入口点
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(GameAPP.class, args);
	}
}
