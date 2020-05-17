package team.TYGY.GluttonousSnake.Animation.NPC;

import static team.TYGY.GluttonousSnake.Animation.ConstantUtil.FOOD_SIZE;
import static team.TYGY.GluttonousSnake.Animation.ConstantUtil.SNAKE_CELL_SIZE;
import static team.TYGY.GluttonousSnake.Animation.ConstantUtil.SNAKE_GRID_H;
import static team.TYGY.GluttonousSnake.Animation.ConstantUtil.SNAKE_GRID_W;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import team.TYGY.GluttonousSnake.Animation.MyUtil;
import team.TYGY.GluttonousSnake.Animation.Player.Snake;

public class Wall
{
	private int x;								//障碍物的位置x
	private int y;								//障碍物的位置y
	//private Color color;						//障碍物的颜色

	
	
	public Wall() {
		init();
	}
	
	
	
private void init()
	{
	x = MyUtil.getRandomNumber(0,SNAKE_GRID_W);
	y = MyUtil.getRandomNumber(0, SNAKE_GRID_H);	
	}



	//画障碍物
	public void paintWall(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(x*FOOD_SIZE, y*FOOD_SIZE, FOOD_SIZE, FOOD_SIZE);
	}
	
	
	
	
//判断是否撞墙
		public boolean isKnockWall(Snake snake) {
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
}