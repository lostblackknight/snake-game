package team.tit.gluttonoussnake.util;

import javafx.scene.paint.Color;
public class MyUtils
{
	private MyUtils() {
	}
	
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
	
	public static int distance(int width, int height) {
		return (int)Math.sqrt(width * width + height * height);
	}
} 
