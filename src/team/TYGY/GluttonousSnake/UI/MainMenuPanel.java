package team.TYGY.GluttonousSnake.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import team.TYGY.GluttonousSnake.GameAPP.GameAPP;

/**
 * 
 * @ClassName: MainMenuPanel
 * @Description: TODO 主菜单面板
 * @author: 陈思祥
 * @version: 1.3
 * @date: 2020年4月10日-下午9:27:52
 *
 */
public class MainMenuPanel extends BasePanel {

	private HBox hb;
	private Button b1;
	
	public MainMenuPanel() {
		init();
		start();
		stop();
	}
	
	@SuppressWarnings("static-access")
	public void init() {
		hb = new HBox();
		b1 = new Button();
		GameAPP.root.setLeftAnchor(this, 0.0);
		GameAPP.root.setRightAnchor(this, 0.0);
		GameAPP.root.setTopAnchor(this, 0.0);
		GameAPP.root.setBottomAnchor(this, 0.0);
		this.setLeftAnchor(hb, 0.0);
		this.setRightAnchor(hb, 0.0);
		this.setTopAnchor(hb, 0.0);
		this.setBottomAnchor(hb, 0.0);
		
		hb.setStyle("-fx-background-color:#ffaad5");
		b1.setId("b1");
		
	}

	public void start() {
		b1.setText("菜单");
		this.getChildren().add(hb);
		hb.getChildren().add(b1);
	}

	public void stop() {
		b1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				UIManager.getUiManager().gotoPanel("gameOverPanel");
			}
		});
	}

}
