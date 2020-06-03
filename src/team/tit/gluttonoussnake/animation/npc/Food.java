package team.tit.gluttonoussnake.animation.npc;

import team.tit.gluttonoussnake.animation.BaseObject;
import team.tit.gluttonoussnake.util.MyUtils;

import static team.tit.gluttonoussnake.constant.Constant.*;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月27日
 */
public class Food extends BaseObject {

	private Color color;

	public Food() {
		init();
	}

	public Food(int id) {
		this.id = id;
	}

	@Override
	public void init() {
		super.init();
		createRandomFood();
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(getColor());
		gc.fillOval(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void update() {
	}

	public void createRandomFood() {
		int x0 = MyUtils.getRandomNumber(0, GRID_W);
		int y0 = MyUtils.getRandomNumber(0, GRID_H);
		setX(x0 * GRID_SIZE);
		setY(y0 * GRID_SIZE);
		setWidth(FOOD_W);
		setHeight(FOOD_H);
		setColor(MyUtils.getRandomColor());
	}

	// 食物不能出现在墙上的生成食物
	public void createRandomFoodNotInWall(ArrayList<Point> wallpoints) {
		createRandomFood();
		if (wallpoints != null) {
			for (int i = 0; i <= wallpoints.size(); i++) {
				if (getX() == wallpoints.get(i).x && getY() == wallpoints.get(i).y) {
					createRandomFoodNotInWall(wallpoints);
				}
			}
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getFoodX() {

		return this.x;
	}

	public int getFoodY() {

		return this.y;
	}
}
