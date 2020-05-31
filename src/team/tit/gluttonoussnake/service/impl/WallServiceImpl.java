/*
 *
 */
package team.tit.gluttonoussnake.service.impl;

import java.util.ArrayList;

import team.tit.gluttonoussnake.animation.npc.Wall;
import team.tit.gluttonoussnake.dao.WallDao;
import team.tit.gluttonoussnake.dao.impl.WallDaoImpl;
import team.tit.gluttonoussnake.service.WallService;

/**
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月31日
 */
public class WallServiceImpl implements WallService {
	
	WallDao wallDao = new WallDaoImpl();

	@Override
	public ArrayList<Wall> findAll() {
		ArrayList<Wall> list = wallDao.findAll();
		return list;
	}

}
