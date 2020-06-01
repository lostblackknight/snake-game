/*
 *
 */
package team.tit.gluttonoussnake.dao.impl;

import team.tit.gluttonoussnake.dao.GameDao;
import team.tit.gluttonoussnake.domain.Game;

/**
 * 操纵数据源中的Game
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月24日
 */
public class GameDaoImpl implements GameDao {

	@Override
	public Game findByUidAndType(int uid, int type) {
		// TODO 查询Game表，返回一个game对象
		
		Game game = new Game(uid,type,1,1,1);
		
		return game;
	}

}
