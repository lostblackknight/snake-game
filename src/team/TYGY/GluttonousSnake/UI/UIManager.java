package team.TYGY.GluttonousSnake.UI;

import java.util.HashMap;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import team.TYGY.GluttonousSnake.GameAPP.GameAPP;
import team.TYGY.GluttonousSnake.GameAPP.Manager;
import team.TYGY.GluttonousSnake.UI.GamePanel.GamePanel;
import team.TYGY.GluttonousSnake.UI.LoadingPanel.LoadingPanel;
import team.TYGY.GluttonousSnake.UI.LoginPanel.LoginPanel;
import team.TYGY.GluttonousSnake.UI.MainMenuPanel.MainMenuPanel;
import team.TYGY.GluttonousSnake.UI.RegisterPanel.RegisterPanel;

/**
 * 
 * ClassName: UIManager
 * Description: TODO 
 * Date: 2020-05-05 07:17:03
 * 
 * @author 陈思祥
 * @version 1.3
 * @since JDK 1.8
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
		
		gotoPanel("GamePanel");											//首先切换到加载面板
		System.out.println(getCurrentPanel());
		GameAPP.root.getChildren().add(getPanel("GamePanel"));			//将加载面板加到根节点上
		
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
	 * MethodName: initRegister
	 * Description: TODO 
	 * Date: 2020-05-05 07:22:37
	 * 
	 * @author 陈思祥
	 * @param 
	 * @return void
	 */
	private void initRegister() {
		regPanel("loadingPanel", new LoadingPanel());
		regPanel("LoginPanel", new LoginPanel());
		regPanel("RegisterPanel", new RegisterPanel());
		regPanel("MainMenuPanel", new MainMenuPanel());
		regPanel("GamePanel", new GamePanel());
	}
	
	/**
	 * 
	 * MethodName: gotoPanel
	 * Description: TODO 
	 * Date: 2020-05-05 07:24:48
	 * 
	 * @author 陈思祥
	 * @param panelName
	 * @return void
	 */
	public void gotoPanel(String panelName) {
		BasePanel panel = panelMap.get(panelName);
		if(panel != null) {
			currentPanel.set(panel);
		}
	}
	
	/**
	 * 
	 * MethodName: getCurrentPanel
	 * Description: TODO 
	 * Date: 2020-05-05 07:24:08
	 * 
	 * @author 陈思祥
	 * @return BasePanel
	 */
	public BasePanel getCurrentPanel() {
		return currentPanel.get();
	}
	
	public ReadOnlyObjectProperty<BasePanel> currentPanelProperty() {
		return currentPanel;
	}
	
	/**
	 * 
	 * MethodName: regPanel
	 * Description: TODO 
	 * Date: 2020-05-05 07:23:44
	 * 
	 * @author 陈思祥
	 * @param panelName
	 * @param panel
	 * @return void
	 */
	public void regPanel(String panelName,BasePanel panel) {
		panelMap.put(panelName, panel);
	}
	
	/**
	 * 
	 * MethodName: delPanel
	 * Description: TODO 
	 * Date: 2020-05-05 07:23:56
	 * 
	 * @author 陈思祥
	 * @param panelName
	 * @return void
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
