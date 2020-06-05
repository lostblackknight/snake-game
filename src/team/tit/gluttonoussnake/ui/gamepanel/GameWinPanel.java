/*
 *
 */
package team.tit.gluttonoussnake.ui.gamepanel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * 游戏胜利面板
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月25日
 */
public class GameWinPanel extends GridPane{
	private Label gamewin;
	private Label l_tip;
	private Label l_tip2;
	
	public GameWinPanel() {
		init();
	}
	
	@SuppressWarnings("static-access")
	public void init() {
		gamewin = new Label("游戏胜利");
		l_tip = new Label("请按ESC键回到主菜单");
		l_tip2 = new Label("或点击继续进入下一关");
		
		gamewin.setId("gamewin");
		l_tip.setId("l_tip");
		l_tip2.setId("l_tip");
		
		this.setMargin(l_tip, new Insets(0, 0, 0, 8));
		this.setMargin(l_tip2, new Insets(0, 0, 0, 7));
		this.getStylesheets().add(this.getClass().getClassLoader().getResource("css/GluttonousSnake.css").toExternalForm());
		this.setStyle("-fx-background-color: #000000;-fx-background-radius: 20 20 20 20;");
		this.setPrefSize(400, 200);
		this.setAlignment(Pos.CENTER);
		this.setVgap(10);
		this.add(gamewin, 0, 0);
		this.add(l_tip, 0, 3);
		this.add(l_tip2, 0, 4);
	}
}
