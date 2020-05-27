package team.tit.gluttonoussnake.animation.player;


public class SnakeNode
{
	private int x;								//节点的x坐标
	private int y;								//节点的y坐标
	private int dir;								//节点的方向
	
	public SnakeNode() {
	}
	
	public SnakeNode(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public SnakeNode(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}
}
