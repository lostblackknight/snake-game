package team.tit.gluttonoussnake.animation.player;

import static team.tit.gluttonoussnake.animation.ConstantUtil.DOWN;
import static team.tit.gluttonoussnake.animation.ConstantUtil.LEFT;
import static team.tit.gluttonoussnake.animation.ConstantUtil.RIGHT;
import static team.tit.gluttonoussnake.animation.ConstantUtil.SNAKE_CELL_SIZE;
import static team.tit.gluttonoussnake.animation.ConstantUtil.SNAKE_GRID_H;
import static team.tit.gluttonoussnake.animation.ConstantUtil.SNAKE_GRID_W;
import static team.tit.gluttonoussnake.animation.ConstantUtil.UP;

import java.util.LinkedList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import team.tit.gluttonoussnake.animation.player.SnakeNode;


public class Snake {
	
	
	private int sid;
	private LinkedList<SnakeNode> body;		                        //存储蛇的链表结构
	private int snakeHeadX;
	private int snakeHeadY;
	private boolean life;											//是否活着
	private boolean pause;											//是否暂停游戏
	private int foodCount;											//吃掉食物的数量
	private SnakeNode oldTail;										//旧的尾巴（在吃掉食物的时候使用）
	private Color headColor;										//头的颜色
	private Color bodyColor;										//身体的颜色
	private int sleepTime;											//控制蛇的速度
	public int ismove=0;                                            //用来判断蛇是否移动（处理连续快速转向BUG）

	public Snake() {
	  body = new LinkedList<SnakeNode>();
		init();
		
	}
	
	public LinkedList<SnakeNode> getBody() {
		return body;
	}

	public void setBody(LinkedList<SnakeNode> body) {
		this.body = body;
	}


	public Snake(int sid) {
		this.sid = sid;
	}
	
	public Snake(int sid, int snakeHeadX, int snakeHeadY) {
		super();
		this.sid = sid;
		this.snakeHeadX = snakeHeadX;
		this.snakeHeadY = snakeHeadY;
	}

	public int getSnakeHeadX() {
		return snakeHeadX;
	}

	public void setSnakeHeadX(int snakeHeadX) {
		this.snakeHeadX = snakeHeadX;
	}

	public int getSnakeHeadY() {
		return snakeHeadY;
	}

	public void setSnakeHeadY(int snakeHeadY) {
		this.snakeHeadY = snakeHeadY;
	}

	public int getSid() {
		return sid;
	}


	public void setSid(int sid) {
		this.sid = sid;
	}


	//初始化
	private void init() {
		int x = SNAKE_GRID_W>>1;												//相当于SNAKE_GRID_W/2
		int y = SNAKE_GRID_H>>1;												//相当于SNAKE_GRID_H/2
		for (int i = 0; i < 3; i++) {
			body.addLast(new SnakeNode(x, y, RIGHT));
			x--;
		}
		foodCount = 0;
		life = true;
		pause = false;
		if(sleepTime==0) {
			sleepTime = 300;
		}
	}
	
	
//清空蛇的节点的方法（用来开始一次新的游戏）
	public void clear() {
		body.clear();
	}
	
	
//蛇死亡
	public void die() {
		life = false;
	}
	
	
//获取蛇的头节点
	public SnakeNode getHead() {
		return body.getFirst();
	}
	
	
	//蛇移动的方法，通过去尾加头的方法实现蛇的移动
	public void move(Snake snake) {
		oldTail = body.removeLast();	
		//去掉尾节点
		int x = body.getFirst().getX();
		int y = body.getFirst().getY();		
		switch (getHead().getDir()) {
		case UP:
			y=y-1;
			//System.out.println("move1");
			/**
			 * 解决越界问题，以下类同
			 */
			if(y<0) {
				y = SNAKE_GRID_H -1;
			}
			break;
		case DOWN:
			y=y+1;
			//System.out.println("move1");
			if(y >= SNAKE_GRID_H) {
				y = 0;
			}
			break;
		case LEFT:
			x=x-1;
			//System.out.println("move1");
			if(x < 0) {
				x = SNAKE_GRID_W -1;
			}
			break;
		case RIGHT:
			x=x+1;
			if(x >= SNAKE_GRID_W) {
				x = 0;
			}
			break;
		}
		SnakeNode newhead = new SnakeNode(x, y, getHead().getDir());
		body.addFirst(newhead);				//增加头结点
        snake.ismove=0;
	}
	
	
//蛇吃到食物后身体变长
	public void eatFood() {		
		body.addLast(oldTail);
		foodCount++;
	}
	
	
//判断蛇是否吃到了自己的身体
	public boolean isEatBody() {
		for(int i=1;i<body.size();i++) {
			if(body.get(i).getX() == getHead().getX() && body.get(i).getY() == getHead().getY()) 
				return true;
		}	
			return false;	
	}
	
	
	//画蛇头
	public void paintHead(GraphicsContext gc) {
		
//		Color color = Color.web("#38a3fd", 1.0);
//		gc.setFill(color);
		gc.setFill(Color.YELLOW);
		gc.fillOval(body.getFirst().getX()*SNAKE_CELL_SIZE, body.getFirst().getY()*SNAKE_CELL_SIZE,
				SNAKE_CELL_SIZE, SNAKE_CELL_SIZE);
	}
	
	
//画蛇
	public void paintSnake(GraphicsContext gc) {
		for (int i = 1; i < body.size(); i++) {
			gc.setFill(Color.WHITE);

					//注释掉的时原来画的蛇
			gc.fillRect(body.get(i).getX()*SNAKE_CELL_SIZE, body.get(i).getY()*SNAKE_CELL_SIZE, 
					SNAKE_CELL_SIZE, SNAKE_CELL_SIZE);
		}
		paintHead(gc);
	}		
	
	
	
	
	public boolean isLife() {
		return life;
	}
	public void setLife(boolean life) {
		this.life = life;
	}
	public boolean isPause() {
		return pause;
	}
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	public Color getHeadColor() {
		return headColor;
	}
	public void setHeadColor(Color headColor) {
		this.headColor = headColor;
	}
	public Color getBodyColor() {
		return bodyColor;
	}
	public void setBodyColor(Color bodyColor) {
		this.bodyColor = bodyColor;
	}
	public int getFoodCount() {
		return foodCount;
	}
	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}
	public SnakeNode getOldTail() {
		return oldTail;
	}
	public void setOldTail(SnakeNode oldTail) {
		this.oldTail = oldTail;
	}
	public int getSleepTime() {
		return sleepTime;
	}
	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
}
