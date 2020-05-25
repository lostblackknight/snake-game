package team.tit.gluttonoussnake.animation;

import static team.tit.gluttonoussnake.animation.ConstantUtil.GAME_PANEL_H;
import static team.tit.gluttonoussnake.animation.ConstantUtil.GAME_PANEL_W;
import static team.tit.gluttonoussnake.animation.ConstantUtil.SNAKE_CELL_SIZE;

import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Map
{
	private int mid;
	private ArrayList<Point> points;
	
	
	public Map() {
	}


	public Map(int mid, ArrayList<Point> points) {
		this.mid = mid;
		this.points = points;
	}


	public int getMid() {
		return mid;
	}


	public void setMid(int mid) {
		this.mid = mid;
	}


	public ArrayList<Point> getPoints() {
		return points;
	}


	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}


		//画地图网格的方法
		public void paintGrid(GraphicsContext gc) {
			gc.setStroke(Color.web("#999", 0.5));
			for (int i = 0; i < GAME_PANEL_W/20; i++) {
				gc.strokeLine(i*SNAKE_CELL_SIZE, 0, i*SNAKE_CELL_SIZE, GAME_PANEL_H);
			}
			for (int i = 0; i < GAME_PANEL_H/20; i++) {
				gc.strokeLine(0, i*SNAKE_CELL_SIZE, GAME_PANEL_W, i*SNAKE_CELL_SIZE);
			}}
		
		
		
}
