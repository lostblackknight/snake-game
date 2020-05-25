/*
 *
 */
package team.tit.gluttonoussnake.dao.impl;

import team.tit.gluttonoussnake.animation.player.Snake;
import team.tit.gluttonoussnake.dao.SnakeDao;

/**
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月24日
 */
public class SnakeDaoImpl implements SnakeDao {

	@Override
	public Snake findBySid(int sid) {
		// TODO 通过sid查询Snake表返回一个snake对象
		
		Snake snake = new Snake(sid,10,10);
		
		return snake;
	}

}
