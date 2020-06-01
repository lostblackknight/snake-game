/*
 *
 */
package team.tit.gluttonoussnake.dao;

import team.tit.gluttonoussnake.domain.Game;

/**
 * 操纵数据源中的Game的接口
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月24日
 */
public interface GameDao {

	/**
	 * 通过uid和type查询游戏数据
	 * @param uid
	 * @param type
	 * @return
	 */
	Game findByUidAndType(int uid, int type);

}
