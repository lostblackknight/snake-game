package team.tit.gluttonoussnake.animation.npc;

import team.tit.gluttonoussnake.animation.BaseObject;
import team.tit.gluttonoussnake.util.MyUtils;

import static team.tit.gluttonoussnake.constant.Constant.*;

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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
