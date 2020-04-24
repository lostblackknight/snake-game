package team.TYGY.GluttonousSnake.UI;

import java.util.HashMap;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import team.TYGY.GluttonousSnake.GameAPP.GameAPP;
import team.TYGY.GluttonousSnake.GameAPP.Manager;
import team.TYGY.GluttonousSnake.UI.LoadingPanel.LoadingPanel;
import team.TYGY.GluttonousSnake.UI.LoginPanel.LoginPanel;
import team.TYGY.GluttonousSnake.UI.MainMenuPanel.MainMenuPanel;
import team.TYGY.GluttonousSnake.UI.RegisterPanel.RegisterPanel;

/**
 * 
 * @ClassName: UIManager
 * @Description: TODO UI管理器
 * @author: 陈思祥
 * @version: 1.3
 * @date: 2020年4月10日-下午8:37:06
 *
 */
public class UIManager implements Manager {
	
	private static UIManager uiManager = new UIManager();
	
	private HashMap<String,BasePanel> panelMap;					//存储面板的容器
	private ObjectProperty<BasePanel> currentPanel;				//存储但前面板并添加监听
	
	
	private UIManager() {
		
	}
	
	public static UIManager getUiManager() {
		return uiManager;
	}

	@Override
	public void onLaunch() {
		panelMap = new HashMap<String,BasePanel>();
		currentPanel = new SimpleObjectProperty<BasePanel>();
		
		initRegister();
		
		gotoPanel("mainMenuPanel");											//首先切换到加载面板
		System.out.println(getCurrentPanel());
		GameAPP.root.getChildren().add(getPanel("mainMenuPanel"));			//将加载面板加到根节点上
		
		//面板切换
		currentPanel.addListener(new ChangeListener<BasePanel>() {

			@Override
			public void changed(ObservableValue<? extends BasePanel> observable, BasePanel oldValue, BasePanel newValue) {
				if(oldValue != null) {
					GameAPP.root.getChildren().remove(oldValue);
					System.out.println("oldValue = " + oldValue);
				}
				if(newValue != null) {
					GameAPP.root.getChildren().add(newValue);
					System.out.println("newValue = " + newValue);
				}
			}
		});
	}

	@Override
	public void onFinish() {
		System.out.println("UI管理器退出");
	}
	
	/**
	 * 
	 * @Title: initRegister
	 * @Description: TODO 注册所有面板
	 * @param: 
	 * @return: void
	 * @throws
	 */
	private void initRegister() {
		regPanel("loadingPanel", new LoadingPanel());
		regPanel("LoginPanel", new LoginPanel());
		regPanel("RegisterPanel", new RegisterPanel());
		regPanel("mainMenuPanel", new MainMenuPanel());
		regPanel("gameOverPanel", new GameOverPanel());
		
	}
	
	/**
	 * 
	 * @Title: gotoPanel
	 * @Description: TODO 跳转到指定面板
	 * @param: @param panelName
	 * @return: void
	 * @throws
	 */
	public void gotoPanel(String panelName) {
		BasePanel panel = panelMap.get(panelName);
		if(panel != null) {
			currentPanel.set(panel);
		}
	}
	
	/**
	 * 
	 * @Title: getCurrentPanel
	 * @Description: TODO 获取当前面板
	 * @param: @return
	 * @return: BasePanel
	 * @throws
	 */
	public BasePanel getCurrentPanel() {
		return currentPanel.get();
	}
	
	public ReadOnlyObjectProperty<BasePanel> currentPanelProperty() {
		return currentPanel;
	}
	
	/**
	 * 
	 * @Title: regPanel
	 * @Description: TODO 注册面板
	 * @param: @param panelName
	 * @param: @param panel
	 * @return: void
	 * @throws
	 */
	public void regPanel(String panelName,BasePanel panel) {
		panelMap.put(panelName, panel);
	}
	
	/**
	 * 
	 * @Title: delPanel
	 * @Description: TODO 删除面板
	 * @param: @param panelName
	 * @return: void
	 * @throws
	 */
	public void delPanel(String panelName) {
		panelMap.remove(panelName);
	}
	
	/**
	 * 
	 * @Title: getPanel
	 * @Description: TODO 获取面板
	 * @param: @param panelName
	 * @param: @return
	 * @return: BasePanel
	 * @throws
	 */
	public BasePanel getPanel(String panelName) {
		return panelMap.get(panelName);
	}
}
