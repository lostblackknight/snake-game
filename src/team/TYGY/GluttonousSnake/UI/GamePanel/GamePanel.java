package team.TYGY.GluttonousSnake.UI.GamePanel;

import javafx.scene.layout.AnchorPane;
import team.TYGY.GluttonousSnake.GameAPP.GameAPP;
import team.TYGY.GluttonousSnake.UI.BasePanel;

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
		
	}

	@Override
	public void stop() {
		
	}
}
