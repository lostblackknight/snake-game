/*
 *
 */
package team.tit.gluttonoussnake.animation.npc;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import team.tit.gluttonoussnake.animation.BaseObject;
import static team.tit.gluttonoussnake.constant.Constant.*;

/**
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月27日
 */
public class Grid extends BaseObject {

	@Override
	public void draw(GraphicsContext gc) {
		gc.setStroke(Color.web("#999", 0.5));
		for (int i = 0; i < GRID_W; i++) {
			gc.strokeLine(i * GRID_SIZE, 0, i * GRID_SIZE, ROOT_PANEL_H);
		}
		for (int i = 0; i < GRID_H; i++) {
			gc.strokeLine(0, i * GRID_SIZE, ROOT_PANEL_W, i * GRID_SIZE);
		}
	}

	@Override
	public void update() {
	}

}
