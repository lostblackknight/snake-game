package team.tit.gluttonoussnake.util;

import static team.tit.gluttonoussnake.animation.ConstantUtil.GAME_PANEL_H;
import static team.tit.gluttonoussnake.animation.ConstantUtil.GAME_PANEL_W;
import static team.tit.gluttonoussnake.animation.ConstantUtil.SNAKE_CELL_SIZE;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class MyUtils
{
	//获取一定范围内的随机值
	public static int getRandomNumber(int min,int max) {
		return (int)(Math.random()*(max - min) + min);
	}
	//颜色组
	private static Color[] colors = {
			Color.WHITE,
			Color.GREEN,
			Color.PINK,
			Color.BLUE,
			Color.PINK,
			Color.ORANGE,
			Color.RED,
			Color.YELLOW,
			Color.CYAN,
			Color.GRAY
	};
	//获取随机颜色
	public static Color getRandomColor() {
		return colors[getRandomNumber(0, colors.length)];
	}
	//计算俩点之间的距离
	public static int distance(int x0,int y0, int x1,int y1) {
		int disX = x0 - x1;
		int disY = y0 - y1;
		return (int)Math.sqrt(disX * disX + disY * disY);
	}
	//画网格
	public void paintGrid(GraphicsContext gc) {
		gc.setStroke(Color.WHITE);
		for (int i = 0; i < GAME_PANEL_W/20; i++) {
			gc.strokeLine(i*SNAKE_CELL_SIZE, 0, i*SNAKE_CELL_SIZE, GAME_PANEL_H);
		}
		for (int i = 0; i < GAME_PANEL_H/20; i++) {
			gc.strokeLine(0, i*SNAKE_CELL_SIZE, GAME_PANEL_W, i*SNAKE_CELL_SIZE);
		}
	}
} 
