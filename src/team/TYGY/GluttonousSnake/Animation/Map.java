package team.TYGY.GluttonousSnake.Animation;

import static team.TYGY.GluttonousSnake.Animation.ConstantUtil.GAME_PANEL_H;
import static team.TYGY.GluttonousSnake.Animation.ConstantUtil.GAME_PANEL_W;
import static team.TYGY.GluttonousSnake.Animation.ConstantUtil.SNAKE_CELL_SIZE;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Map
{
	
	
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
