package team.TYGY.GluttonousSnake.UI.MainMenuPanel;


import team.TYGY.GluttonousSnake.GameAPP.GameAPP;
import team.TYGY.GluttonousSnake.UI.BasePanel;

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

	private Background background;
	private Title title;
	private MenuBox menuBox;
	private MenuItems menuItems;
	
	
	public MainMenuPanel() {
		init();
		start();
		stop();
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void init() {
		background = new Background(this);
		title = new Title(this);
		menuBox = new MenuBox(this);
		menuItems = new MenuItems(menuBox);
		
		GameAPP.root.setLeftAnchor(this, 0.0);
		GameAPP.root.setRightAnchor(this, 0.0);
		GameAPP.root.setTopAnchor(this, 0.0);
		GameAPP.root.setBottomAnchor(this, 0.0);
		
//		this.setStyle("-fx-background-color:#FF79BC");	
		
	}

	@Override
	public void start() {
		background.addBackground();
		title.addTitle();
		menuBox.addMenuBox();
		menuItems.addMenuItems();
	}

	@Override
	public void stop() {
		
	}

}
