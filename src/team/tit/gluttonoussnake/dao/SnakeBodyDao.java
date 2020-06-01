/*
 *
 */
package team.tit.gluttonoussnake.dao;

import java.util.LinkedList;

import team.tit.gluttonoussnake.animation.player.SnakeNode;

/**
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月24日
 */
public interface SnakeBodyDao {

	/**
	 * @param sid
	 * @return
	 */
	LinkedList<SnakeNode> findBodyBySid(int sid);

}
