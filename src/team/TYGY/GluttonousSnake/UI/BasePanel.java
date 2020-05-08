package team.TYGY.GluttonousSnake.UI;

import javafx.scene.layout.AnchorPane;

/**
 * 
 * @ClassName: BasePanel
 * @description: TODO 所有面板的公共父类
 * @author: 陈思祥
 * @version: 1.3
 * @date: 2020年4月10日-下午8:07:51
 *
 */
public abstract class BasePanel extends AnchorPane {
	
	public abstract void init();						//面板初始化
	public abstract void start();						//面板启动
	public abstract void update();
	public abstract void stop();						//面板退出
}
