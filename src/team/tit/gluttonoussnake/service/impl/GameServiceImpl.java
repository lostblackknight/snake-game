/*
 *
 */
package team.tit.gluttonoussnake.service.impl;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

import team.tit.gluttonoussnake.animation.npc.Food;
import team.tit.gluttonoussnake.animation.npc.Wall;
import team.tit.gluttonoussnake.animation.player.Snake;
import team.tit.gluttonoussnake.animation.player.SnakeNode;
import team.tit.gluttonoussnake.dao.FoodDao;
import team.tit.gluttonoussnake.dao.GameDao;
import team.tit.gluttonoussnake.dao.WallDao;
import team.tit.gluttonoussnake.dao.SnakeBodyDao;
import team.tit.gluttonoussnake.dao.SnakeDao;
import team.tit.gluttonoussnake.dao.impl.FoodDaoImpl;
import team.tit.gluttonoussnake.dao.impl.GameDaoImpl;
import team.tit.gluttonoussnake.dao.impl.WallDaoImpl;
import team.tit.gluttonoussnake.dao.impl.SnakeBodyDaoImpl;
import team.tit.gluttonoussnake.dao.impl.SnakeDaoImpl;
import team.tit.gluttonoussnake.domain.Game;
import team.tit.gluttonoussnake.service.GameService;

/**
 * 处理游戏数据的实现类
 * 
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月24日
 */
public class GameServiceImpl implements GameService {

	private GameDao gameDao = new GameDaoImpl();
	private SnakeDao snakeDao = new SnakeDaoImpl();
	private SnakeBodyDao snakeBodyDao = new SnakeBodyDaoImpl();
	private FoodDao foodDao = new FoodDaoImpl();
	private WallDao mapDao = new WallDaoImpl();

	/**
	 * 查询用户的 游戏数据
	 * 
	 * @param game 游戏数据对象
	 * @return 返回一个游戏数据的对象
	 */
	@Override
	public Game findOne(Game game) {

		// 1.查询Game表
		Game g = gameDao.findByUidAndType(game.getUid(), game.getType());

		// 2.获取snake对象
		// 2.1获取蛇头
		Snake snake = snakeDao.findBySid(g.getSid());
		// 2.2获取蛇身体
		LinkedList<SnakeNode> list = snakeBodyDao.findBodyBySid(g.getSid());
		snake.setList(list);
				
		g.setSanke(snake);

		// 3.获取食物对象
		Food food = foodDao.findByFid(g.getFid());
		g.setFood(food);

		// 4.获取地图对象
		ArrayList<Point> wall = mapDao.findByWid(g.getMid());
		Wall w = new Wall();
		w.setId(g.getWid());
		w.setPoints(wall);
		g.setWall(w);
		return g;
	}

	@Override
	public boolean haveOldData(Game game) {
		// TODO 查找game表
		Game g = gameDao.findByUidAndType(game.getUid(), game.getType());
		if (game.getType() == 0) {
			if (g.getSid() > 0 && game.getType() == 0) {
				return true;
			} else {

				return false;
			}
		} else {
			if (g.getSid() > 0 && game.getType() == 1) {
				return true;
			} else {
				return false;
			}
		}
	}



	@Override
	public void delOldData(Game g) {
		gameDao.delOldData(g);
	}
	@Override
	public void saveGameAll(Game game)
	{
		gameDao.saveGameData(game);
		foodDao.saveFoodData(game.getFood());
		snakeDao.saveSnake(game.getSanke());
		snakeBodyDao.saveSnakeBodyData(game.getSanke());		
	}

	@Override
	public Game setInitialValue(Game game)
	{
		Game g=gameDao.setInitialValue(game);
		return g;
	}
}
