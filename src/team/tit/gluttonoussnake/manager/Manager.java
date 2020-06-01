package team.tit.gluttonoussnake.manager;

/**
 * 管理器接口，由子类来实现，控制管理器的生命周期
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月21日
 */
public interface Manager {

	/**
	 * 启动管理器
	 * do something in imp class
	 */
	void onLaunch();
	
	/**
	 * 退出管理器
	 * do something in imp class
	 */
	void onFinish();
}
