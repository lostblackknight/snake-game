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
	private Hand mouseHand;
	private LeiSureMenuBox leiSureMenuBox;
	private LeiSureMenuItems leiSureMenuItems;
	private AdventureMenuBox adventureMenuBox;
	private AdventureMenuItems adventureMenuItems;
	private OptionsMenuBox optionsMenuBox;
	
	
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
		leiSureMenuBox = new LeiSureMenuBox(this);
		leiSureMenuItems = new LeiSureMenuItems(leiSureMenuBox);
		adventureMenuBox = new AdventureMenuBox(this);
		adventureMenuItems = new AdventureMenuItems(adventureMenuBox);
		optionsMenuBox = new OptionsMenuBox(this);
		mouseHand = new Hand(this);

		
		GameAPP.root.setLeftAnchor(this, 0.0);
		GameAPP.root.setRightAnchor(this, 0.0);
		GameAPP.root.setTopAnchor(this, 0.0);
		GameAPP.root.setBottomAnchor(this, 0.0);
	}

	@Override
	public void start() {
		mouseHand.setHand();
		background.addBackground();
		title.addTitle();
		menuBox.addMenuBox();
		menuItems.addMenuItems();
		leiSureMenuBox.addLeiSureMenuBox();
		leiSureMenuItems.addLeiSureMenuItems();
		adventureMenuBox.addAdventureMenuBox();
		adventureMenuItems.addLeiSureMenuItems();
		optionsMenuBox.addOptionsMenuBox();
	}

	@Override
	public void stop() {
		
	}

}
