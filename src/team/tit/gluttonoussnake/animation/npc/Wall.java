package team.tit.gluttonoussnake.animation.npc;

import static team.tit.gluttonoussnake.constant.Constant.*;
import static team.tit.gluttonoussnake.constant.Constant.GRID_W;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import team.tit.gluttonoussnake.animation.BaseObject;
import team.tit.gluttonoussnake.util.MyUtils;

public class Wall extends BaseObject {
	
	public Color color;
	ArrayList<Point> points = new ArrayList<Point>();
	
	
	public Wall() {
		init();
	}
	

	public Wall(int id ,ArrayList<Point> points) {
		this.id = id;
		this.points = points;
	}

	@Override
	public void init() {
		super.init();
		setX(MyUtils.getRandomNumber(0, GRID_W) * GRID_SIZE);
		setY(MyUtils.getRandomNumber(0, GRID_H) * GRID_SIZE);
		setWidth(WALL_W);
		setHeight(WALL_H);
		setColor(Color.RED);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillRect(getX(), getY(), getWidth(), getHeight());
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

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}
}
