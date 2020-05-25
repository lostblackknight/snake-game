/*
 *
 */
package team.tit.gluttonoussnake.service;

import team.tit.gluttonoussnake.domain.Game;

/**
 * 处理游戏数据的接口
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月24日
 */
public interface GameService {

	/**
	 * 查询用户的 游戏数据
	 * @param game 游戏数据对象
	 * @return 返回一个游戏数据的对象
	 */
	Game findOne(Game game);

	/**
	 * @param game
	 * @return
	 */
	boolean haveOldData(Game game);

}
