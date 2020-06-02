package team.tit.gluttonoussnake.domain;


import team.tit.gluttonoussnake.animation.npc.Food;
import team.tit.gluttonoussnake.animation.npc.Wall;
import team.tit.gluttonoussnake.animation.player.Snake;

/**
 * 游戏数据类
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月24日
 */
public class Game {

	private int uid;		//用户的id
	private int type;		//游戏模式的游戏类型(0:表示休闲模式，1：表示冒险模式)
	private int sid;
	private int fid;
	private int wid;		//1：地图1 2：地图2 3：地图3 4：地图4
	
	private Snake sanke;
	private Food food;
	private Wall wall;

	
	public Game() {
	}
	
	public Game(int uid, int type) {
		this.uid = uid;
		this.type = type;
	}
	
	public Game(int uid, int type, int sid, int fid) {
		this.uid = uid;
		this.type = type;
		this.sid = sid;
		this.fid = fid;
	}

	public Game(int uid, int type, int sid, int fid, int mid) {
		this.uid = uid;
		this.type = type;
		this.sid = sid;
		this.fid = fid;
		this.wid = mid;
	}
	
	public Game(int uid, int type, int sid, int fid, int mid, Snake sanke, Food food) {
		this.uid = uid;
		this.type = type;
		this.sid = sid;
		this.fid = fid;
		this.wid = mid;
		this.sanke = sanke;
		this.food = food;

	}

	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getSid() {
		return sid;
	}
	
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	public int getFid() {
		return fid;
	}
	
	public void setFid(int fid) {
		this.fid = fid;
	}
	
	public int getMid() {
		return wid;
	}
	
	public void setMid(int mid) {
		this.wid = mid;
	}
	
	public Snake getSanke() {
		return sanke;
	}
	
	public void setSanke(Snake sanke) {
		this.sanke = sanke;
	}
	
	public Food getFood() {
		return food;
	}
	
	public void setFood(Food food) {
		this.food = food;
	}

	public Wall getWall() {
		return wall;
	}

	public void setWall(Wall wall) {
		this.wall = wall;
	}

	public int getWid() {
		return wid;
	}

	public void setWid(int wid) {
		this.wid = wid;
	}
}
