package team.tit.gluttonoussnake.animation.npc;

import static team.tit.gluttonoussnake.constant.Constant.GRID_H;
import static team.tit.gluttonoussnake.constant.Constant.GRID_W;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import team.tit.gluttonoussnake.animation.BaseObject;
import team.tit.gluttonoussnake.util.MyUtils;

public class Wall extends BaseObject {
	
	public Color color;
	
	public Wall() {
		init();
	}

	@Override
	public void init() {
		super.init();
		setX(MyUtils.getRandomNumber(0,GRID_W));
		setY(MyUtils.getRandomNumber(0,GRID_H));
		setColor(Color.RED);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillOval(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void update() {
		
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
