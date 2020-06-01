/*
 *
 */
package team.tit.gluttonoussnake.animation.info;

import static team.tit.gluttonoussnake.constant.Constant.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import team.tit.gluttonoussnake.animation.BaseObject;

/**
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月25日
 */
public class GameInfo extends BaseObject {

	private int score = 0;
	private int length = 2;
	
	
	public GameInfo() {
		init();
	}

	@Override
	public void init() {
		super.init();
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.web("#999", 0.6));
		gc.fillRoundRect(ROOT_PANEL_W-216, 16, 200, 100, 15, 15);
		gc.setFill(Color.web("#191d24"));
		gc.setFont(Font.font(null, FontWeight.findByWeight(800), 28));
		gc.fillText("长度：" + getLength(), 1078, 55);
		gc.fillText("分数：" + getScore(), 1078, 98);
	}
	
	@Override
	public void update() {
		
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}

}
