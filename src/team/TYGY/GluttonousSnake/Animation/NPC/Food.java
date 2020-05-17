package team.TYGY.GluttonousSnake.Animation.NPC;




import team.TYGY.GluttonousSnake.Animation.Player.Snake;
import team.TYGY.GluttonousSnake.Animation.MyUtil;
import static team.TYGY.GluttonousSnake.Animation.ConstantUtil.*;

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
	private int size;							//食物的尺寸
	private int x;								//食物的位置x
	private int y;								//食物的位置y
	private Color color;						//食物的颜色
	private int type;							//食物的种类
	
	

	public Food() {
		init();
	}
	//初始化
	private void init() {
		size = FOOD_SIZE;
		x = MyUtil.getRandomNumber(0,SNAKE_GRID_W);
		y = MyUtil.getRandomNumber(0, SNAKE_GRID_H);
		color = MyUtil.getRandomColor();
	}
	
	//画食物
	public void paintFood(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillOval(x*FOOD_SIZE, y*FOOD_SIZE, FOOD_SIZE, FOOD_SIZE);
	}
	//
	

//判断食物是否被吃
	public boolean isFoodEated(Snake snake) {
		int distance =  MyUtil.distance(snake.getHead().getX()*SNAKE_CELL_SIZE,
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
		setX(MyUtil.getRandomNumber(0, SNAKE_GRID_W));
		setY(MyUtil.getRandomNumber(0, SNAKE_GRID_H));
		setColor(MyUtil.getRandomColor());
	}
	
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
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
