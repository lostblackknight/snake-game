package team.tit.gluttonoussnake.animation.npc;




import team.tit.gluttonoussnake.animation.player.Snake;
import team.tit.gluttonoussnake.util.MyUtils;

import static team.tit.gluttonoussnake.animation.ConstantUtil.*;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 
 * @ClassName: Food
 * @Description: TODO 
 * @author: 陈思祥
 * @version: 1.3
 * @date: 2020年4月10日-下午9:19:08
 *
 */
public class Food {
	private int fid;
	private int size;							//食物的尺寸
	private int foodX;							//食物的位置x
	private int foodY;							//食物的位置y
	private Color color;						//食物的颜色
	private int type;							//食物的种类
	
	

	public Food() {
		init();
	}
	
	
	public Food(int fid) {
		this.fid = fid;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public int getFoodX() {
		return foodX;
	}

	public void setFoodX(int foodX) {
		this.foodX = foodX;
	}

	public int getFoodY() {
		return foodY;
	}


	public void setFoodY(int foodY) {
		this.foodY = foodY;
	}


	//初始化
	private void init() {
		size = FOOD_SIZE;
		foodX = MyUtils.getRandomNumber(0,SNAKE_GRID_W);
		foodY = MyUtils.getRandomNumber(0, SNAKE_GRID_H);
		color = MyUtils.getRandomColor();
	}
	
	//画食物
	public void paintFood(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillOval(foodX*FOOD_SIZE, foodY*FOOD_SIZE, FOOD_SIZE, FOOD_SIZE);
	}
	//
	

//判断食物是否被吃
	public boolean isFoodEated(Snake snake) {
		int distance =  MyUtils.distance(snake.getHead().getX()*SNAKE_CELL_SIZE,
				snake.getHead().getY()*SNAKE_CELL_SIZE,
				getX()*FOOD_SIZE,
				getY()*FOOD_SIZE);
		int dis = (SNAKE_CELL_SIZE + FOOD_SIZE)>>1;
		if(distance < dis) {
			return true;
		} else {
			return false;
		}
	}
	
//生成新的食物
	public void newFood(Food food) {
		setX(MyUtils.getRandomNumber(0, SNAKE_GRID_W));
		setY(MyUtils.getRandomNumber(0, SNAKE_GRID_H));
		setColor(MyUtils.getRandomColor());
	}
	
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getX() {
		return foodX;
	}
	public void setX(int x) {
		this.foodX = x;
	}
	public int getY() {
		return foodY;
	}
	public void setY(int y) {
		this.foodY = y;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
