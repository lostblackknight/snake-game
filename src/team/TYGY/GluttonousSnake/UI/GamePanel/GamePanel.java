package team.TYGY.GluttonousSnake.UI.GamePanel;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import team.TYGY.GluttonousSnake.GameAPP.GameAPP;
import team.TYGY.GluttonousSnake.UI.BasePanel;
import team.TYGY.GluttonousSnake.UI.UIManager;
import team.TYGY.GluttonousSnake.UI.MainMenuPanel.MainMenuPanel;

/**
 * 
 * @ClassName: GamePanel
 * @Description: TODO 游戏面板
 * @author: 陈思祥
 * @version: 1.3
 * @date: 2020年4月10日-下午9:27:42
 *
 */
public class GamePanel extends BasePanel{

	private AnchorPane subRoot;
	private Background background;
	private ScorePanel scorePanel;
	private SubMenu subMenu;
	private GameOverPanel gameOverPanel;
	private boolean flag = false;
	
	public GamePanel() {
		init();
		start();
		update();
		stop();
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void init() {
		subRoot = new AnchorPane();
		background = new Background(subRoot);
		scorePanel = new ScorePanel(subRoot);
		subMenu = new SubMenu();
		gameOverPanel = new GameOverPanel();
		
		
		GameAPP.root.setLeftAnchor(this, 0.0);
		GameAPP.root.setRightAnchor(this, 0.0);
		GameAPP.root.setTopAnchor(this, 0.0);
		GameAPP.root.setBottomAnchor(this, 0.0);
		this.setLeftAnchor(subRoot, 0.0);
		this.setRightAnchor(subRoot, 0.0);
		this.setTopAnchor(subRoot, 0.0);
		this.setBottomAnchor(subRoot, 0.0);
	}

	@Override
	public void start() {
		background.addBackground();
		
		scorePanel.addScorePanel();
		
		this.getChildren().add(subRoot);
		
	}

	@Override
	public void update() {
		gamePause();
		subMenu.getContinueGame().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				subRoot.getChildren().remove(subMenu);
				flag = false;
			}
		});
	}

	@Override
	public void stop() {
		subMenu.getEXIT_TO_MainMenu().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				subRoot.getChildren().remove(subMenu);
				UIManager.getUiManager().delPanel("GamePanel");
				UIManager.getUiManager().regPanel("MainMenuPanel", new MainMenuPanel());
				UIManager.getUiManager().gotoPanel("GamePanel");
				UIManager.getUiManager().gotoPanel("MainMenuPanel");
			}
		});
		
		subMenu.getEXIT_TO_WINDOWS().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Platform.exit();
			}
		});
	}
	
	/**
	 * 
	 * MethodName: gamePause
	 * Description: TODO 暂停游戏
	 * Date: 2020-05-10 06:03:50
	 * 
	 * @author 陈思祥
	 * @return void
	 */
	public void gamePause() {
		subRoot.setFocusTraversable(true);
		subRoot.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ESCAPE && flag == false) {
					subRoot.getChildren().add(subMenu);
					flag =true;
				}else if (event.getCode() == KeyCode.ESCAPE && flag == true) {
					subRoot.getChildren().remove(subMenu);
					flag =false;
				}
			}
		});
	}
	
	/**
	 * 
	 * MethodName: gameContinue
	 * Description: TODO 继续游戏
	 * Date: 2020-05-10 06:06:42
	 * 
	 * @author 陈思祥
	 * @return void
	 */
	public void gameContinue() {
		subMenu.getContinueGame().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				subRoot.getChildren().remove(subMenu);
			}
		});
	}
}
