package team.tit.gluttonoussnake.ui.gamepanel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * 游戏结束面板
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月23日
 */
public class GameOverPanel extends GridPane {

	private Label gameover;
	private Label l_tip;
	
	public GameOverPanel() {
		init();
	}
	
	@SuppressWarnings("static-access")
	public void init() {
		gameover = new Label("游戏结束");
		l_tip = new Label("请按ESC键回到主菜单");
		
		gameover.setId("gameover");
		l_tip.setId("l_tip");
		
		this.setMargin(l_tip, new Insets(0, 0, 0, 8));
		this.getStylesheets().add(this.getClass().getClassLoader().getResource("css/GluttonousSnake.css").toExternalForm());
		this.setStyle("-fx-background-color: #000000;-fx-background-radius: 20 20 20 20;");
		this.setPrefSize(400, 200);
		this.setAlignment(Pos.CENTER);
		this.setVgap(10);
		this.add(gameover, 0, 0);
		this.add(l_tip, 0, 3);
	}
}
