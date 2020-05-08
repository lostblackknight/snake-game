package team.TYGY.GluttonousSnake.UI.GameOverPanel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import team.TYGY.GluttonousSnake.UI.BasePanel;
import team.TYGY.GluttonousSnake.UI.UIManager;

/**
 * 
 * @ClassName: GameOverPanel
 * @Description: TODO 游戏结束面板
 * @author: 陈思祥
 * @version: 1.3
 * @date: 2020年4月10日-下午9:27:33
 *
 */
public class GameOverPanel extends BasePanel{

	private Button b1;
	private Button b2;
	private HBox hb;
	public GameOverPanel() {
		init();
		start();
		stop();
	}
	@Override
	public void init() {
		hb = new HBox();
		b1 = new Button("回到主菜单");
		b2 = new Button("退出");
	}

	@Override
	public void start() {
		hb.getChildren().addAll(b1,b2);
		this.getChildren().add(hb);
	}

	@Override
	public void stop() {
		b1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				UIManager.getUiManager().gotoPanel("mainMenuPanel");
			}
		});
		b2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
