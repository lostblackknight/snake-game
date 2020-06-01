package team.tit.gluttonoussnake.animation.player;

import static team.tit.gluttonoussnake.constant.Constant.DEFAULT_LENGTH;
import static team.tit.gluttonoussnake.constant.Constant.GRID_H;
import static team.tit.gluttonoussnake.constant.Constant.GRID_SIZE;
import static team.tit.gluttonoussnake.constant.Constant.GRID_W;
import static team.tit.gluttonoussnake.constant.Constant.ROOT_PANEL_H;
import static team.tit.gluttonoussnake.constant.Constant.ROOT_PANEL_W;
import static team.tit.gluttonoussnake.constant.Constant.SNAKE_H;
import static team.tit.gluttonoussnake.constant.Constant.SNAKE_W;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import team.tit.gluttonoussnake.animation.BaseObject;
import team.tit.gluttonoussnake.animation.npc.Food;
import team.tit.gluttonoussnake.animation.npc.Wall;
import team.tit.gluttonoussnake.util.MyUtils;


public class Snake extends BaseObject {
	
	public enum DIR {
		UP, DOWN, LEFT, RIGHT
	};
	
	private LinkedList<SnakeNode> list = new LinkedList<SnakeNode>();		//蛇的节点
	private DIR dir;														//蛇的方向
	private int length;														//蛇的长度
	private Color color;													//蛇的颜色
	private int sleepTime;													//控制蛇的速度
	private SnakeNode tail;
	private boolean isEnough;
	
	public Snake() {
		init();
	}
	public Snake(int id) {
		this.id = id;
	}
	
	
	public Snake(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public Snake(int id, LinkedList<SnakeNode> list) {
		this.id = id;
		this.list = list;
	}

	@Override
	public void init() {
		super.init();
		setX((GRID_W >> 1) * GRID_SIZE);
		setY((GRID_H >> 1) * GRID_SIZE);
		setWidth(SNAKE_W);
		setHeight(SNAKE_H);
		setDir(DIR.RIGHT);
		setLength(DEFAULT_LENGTH);
		for (int i = 0; i < getLength(); i++) {
			list.addLast(new SnakeNode(getX(), getY()));
			x = x - getWidth();
		}
		if (sleepTime == 0) {
			setSleepTime(300);
		}
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		for (int i = 1; i < list.size(); i++) {
			gc.setFill(Color.WHITE);
			gc.fillOval(list.get(i).getX(), list.get(i).getY(), SNAKE_W, SNAKE_H);
		}
		gc.setFill(Color.YELLOW);
		gc.fillOval(getHead().getX(), getHead().getY(), SNAKE_W, SNAKE_H);
		setX(getHead().getX());
		setY(getHead().getY());
	}

	@Override
	public void update() {
		int x = getHead().getX();
		int y = getHead().getY();
		switch (dir) {
		case UP:
			y = y - GRID_SIZE;
			if (y < 0) {
				y = ROOT_PANEL_H - GRID_SIZE;
			}
			break;
		case DOWN:
			y = y + GRID_SIZE;
			if (y > ROOT_PANEL_H - GRID_SIZE) {
				y = 0;
			}
			break;
		case LEFT:
			x = x - GRID_SIZE;
			if (x < 0) {
				x = ROOT_PANEL_W - GRID_SIZE;
			}
			break;
		case RIGHT:
			x = x + GRID_SIZE;
			if (x > ROOT_PANEL_W - GRID_SIZE) {
				x = 0;
			}
			break;
		default:
			break;
		}
		isEnough = true;
		tail = list.removeLast();
		SnakeNode newHead = new SnakeNode(x, y);
		list.addFirst(newHead);
	}	
	
	/**
	 * 键盘监听，改变蛇的方向
	 * @param event
	 */
	public void onKeyPressed(KeyEvent event) {
		KeyCode tmpCode = event.getCode();
		// 如果反方向那么不处理，蛇不会掉头
		if ((tmpCode == KeyCode.W && dir == DIR.DOWN)
				|| (tmpCode == KeyCode.S && dir == DIR.UP)
				|| (tmpCode == KeyCode.D && dir == DIR.LEFT)
				|| (tmpCode == KeyCode.A && dir == DIR.RIGHT)) {
			return;
		}
		updateDir(tmpCode);
	}
	
	public void updateDir(KeyCode code) {
		if (isEnough()) {
			if (code == KeyCode.W) {
				dir = DIR.UP;
			} else if (code == KeyCode.S) {
				dir = DIR.DOWN;
			} else if (code == KeyCode.A) {
				dir = DIR.LEFT;
			} else if (code == KeyCode.D) {
				dir = DIR.RIGHT;
			} else if (code == KeyCode.U) {
				if (getSleepTime() != 50) {
					setSleepTime(50);
				}
			} else if (code == KeyCode.I) {
				if (getSleepTime() != 550) {
					setSleepTime(550);
				}
			}
			setEnough(false);
		}
	}
	
	public void grow() {
		list.add(tail);
		length = length + 1;
	}
	
	public boolean isEatFood(Food food) {
		int x0 = getHead().getX() * getWidth() + (getWidth() >> 1);
		int y0 = getHead().getY() * getHeight() + (getHeight() >> 1);
		int x1 = food.getX() * food.getWidth() + (food.getWidth() >> 1);
		int y1 = food.getY() * food.getHeight() + (food.getHeight() >> 1);
		int w = (getWidth() + food.getWidth()) >> 1;
		int h = (getHeight() + food.getHeight()) >> 1;
		int disCurrent = MyUtils.distance(x0, y0, x1, y1);
		int disCrashed = MyUtils.distance(w, h);
		if (disCurrent < disCrashed) {
			return true;
		}
		return false;
	}
	
	public boolean isEatBody() {
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i).getX() == getHead().getX() && list.get(i).getY() == getHead().getY())
				return true;
		}
		return false;
	}
	
public boolean isEatWall(ArrayList<Point> points) {
		
		for(int i=0;i<points.size();i++) {
		int x0 = getHead().getX() * getWidth() + (getWidth() >> 1);
		int y0 = getHead().getY() * getHeight() + (getHeight() >> 1);
		int x1 = (int) (points.get(i).getX() * getWidth() + 10);
		int y1 =(int) (points.get(i).getY() * getHeight() + 10);
		int w = (getWidth() + 20) >> 1;
		int h = (getHeight() + 20) >> 1;
		int disCurrent = MyUtils.distance(x0, y0, x1, y1);
		int disCrashed = MyUtils.distance(w, h);		
		if (disCurrent < disCrashed) {
			return true;
		}
		}
		return false;
		
	}
	
	public SnakeNode getHead() {
		return list.getFirst();
	}
	
	public LinkedList<SnakeNode> getList() {
		return list;
	}

	public void setList(LinkedList<SnakeNode> list) {
		this.list = list;
	}

	public DIR getDir() {
		return dir;
	}

	public void setDir(DIR dir) {
		this.dir = dir;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public SnakeNode getTail() {
		return tail;
	}

	public void setTail(SnakeNode tail) {
		this.tail = tail;
	}
	
	public boolean isEnough() {
		return isEnough;
	}

	public void setEnough(boolean isEnough) {
		this.isEnough = isEnough;
	}
}
