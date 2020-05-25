/*
 *
 */
package team.tit.gluttonoussnake.dao;

import java.awt.Point;
import java.util.ArrayList;

import team.tit.gluttonoussnake.animation.Map;

/**
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月24日
 */
public interface MapDao {

	/**
	 * @param mid
	 * @return
	 */
	ArrayList<Point> findByMid(int mid);

}
