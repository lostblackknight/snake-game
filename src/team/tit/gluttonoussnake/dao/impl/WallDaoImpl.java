/*
 *
 */
package team.tit.gluttonoussnake.dao.impl;

import java.awt.Point;
import java.util.ArrayList;

import team.tit.gluttonoussnake.animation.npc.Wall;
import team.tit.gluttonoussnake.dao.WallDao;

/**
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月24日
 */
public class WallDaoImpl implements WallDao {

	@Override
	public ArrayList<Wall> findAll() {
		
		ArrayList<Point> points0 = findByWid(0);
		ArrayList<Point> points1 = findByWid(1);
		ArrayList<Point> points2 = findByWid(2);
		ArrayList<Point> points3 = findByWid(3);
		
		Wall wall0 = new Wall(0, points0);
		Wall wall1 = new Wall(1, points1);
		Wall wall2 = new Wall(2, points2);
		Wall wall3 = new Wall(3, points3);
		
		ArrayList<Wall> list = new ArrayList<Wall>();
		list.add(wall0);
		list.add(wall1);
		list.add(wall2);
		list.add(wall3);
		return list;
	}

	@Override
	public ArrayList<Point> findByWid(int wid) {
		// TODO Auto-generated method stub
		return null;
	}

}
