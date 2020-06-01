package team.tit.gluttonoussnake.ui;

import javafx.scene.layout.AnchorPane;

/**
 * 面板抽象类
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月22日
 */
public abstract class BasePanel extends AnchorPane {
	
	/**
	 * 初始化面板，设置组件的属性和布局
	 */
	public abstract void init();
	
	/**
	 * 启动面板，将组件添加到面板上
	 */
	public abstract void start();
	
	/**
	 * 更新面板，对组件添加监听事件
	 */
	public abstract void update();
	
	/**
	 * 当退出面板时，做一些操作
	 */
	public abstract void stop();
}
