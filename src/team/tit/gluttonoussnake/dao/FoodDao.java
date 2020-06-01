/*
 *
 */
package team.tit.gluttonoussnake.dao;

import team.tit.gluttonoussnake.animation.npc.Food;

/**
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月24日
 */
public interface FoodDao {

	/**
	 * @param fid
	 * @return
	 */
	Food findByFid(int fid);

}
