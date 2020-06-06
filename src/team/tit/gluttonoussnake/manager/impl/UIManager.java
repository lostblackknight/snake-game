package team.tit.gluttonoussnake.manager.impl;

import java.util.HashMap;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import team.tit.gluttonoussnake.gameapp.GameAPP;
import team.tit.gluttonoussnake.manager.Manager;
import team.tit.gluttonoussnake.ui.BasePanel;
import team.tit.gluttonoussnake.ui.loadingpanel.LoadingPanel;

/**
 * UI管理器类，用来管理所有面板
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月22日
 */
public class UIManager implements Manager {
	
	private static UIManager uiManager = new UIManager();
	private HashMap<String,BasePanel> panelMap;					//存储面板的容器
	private ObjectProperty<BasePanel> currentPanel;				//存储当前面板并且具有监听
	
	private UIManager() {
	}
	
	public static UIManager getUiManager() {
		return uiManager;
	}

	@Override
	public void onLaunch() {
		//1.实例化对象
		panelMap = new HashMap<String,BasePanel>();
		currentPanel = new SimpleObjectProperty<BasePanel>();
		
		//2.设置游戏应用首次要进入的面板
		gotoFirstPanel("LoadingPanel", new LoadingPanel());
		
		//3.添加属性监听，实现面板的切换
		currentPanel.addListener(new ChangeListener<BasePanel>() {

			@Override
			public void changed(ObservableValue<? extends BasePanel> observable, BasePanel oldValue, BasePanel newValue) {
				if(oldValue != null) {
					GameAPP.root.getChildren().remove(oldValue);
					oldValue.stop();
				}
				if(newValue != null) {
					newValue.init();
					newValue.start();
					newValue.update();
					GameAPP.root.getChildren().add(newValue);
				}
			}
		});
	}

	@Override
	public void onFinish() {
	}
	
	/**
	 * 注册面板
	 * @param panelName 面板名字
	 * @param panel 面板
	 */
	public void regPanel(String panelName,BasePanel panel) {
		panelMap.put(panelName, panel);
	}
	
	/**
	 * 删除面板
	 * @param panelName 面板名字
	 */
	public void delPanel(String panelName) {
		panelMap.remove(panelName);
	}
	
	/**
	 * 获取面板
	 * @param panelName 面板名字
	 * @return 根据面板名字返回获取到的面板
	 */
	public BasePanel getPanel(String panelName) {
		return panelMap.get(panelName);
	}
	
	/**
	 * 获取当前面板
	 * @return 返回当前面板
	 */
	public BasePanel getCurrentPanel() {
		return currentPanel.get();
	}
	
	/**
	 * 获取当前面板的只读形式
	 * @return 返回当前面板的只读形式
	 */
	public ReadOnlyObjectProperty<BasePanel> currentPanelProperty() {
		return currentPanel;
	}
	
	/**
	 * 切换面板
	 * @param panelName 面板的名字
	 */
	public void gotoPanel(String panelName) {
		BasePanel panel = getPanel(panelName);
		if(panel != null) {
			currentPanel.set(panel);
		}
	}
	
	/**
	 * 设置游戏应用首次要进入的面板
	 * @param panelNameFirst 首次要进入的面板的名字
	 * @param panelFirst 首次要进入的面板
	 */
	public void gotoFirstPanel(String panelNameFirst,BasePanel panelFirst) {
		//1.注册面板
		regPanel(panelNameFirst, panelFirst);
		panelFirst.init();
		panelFirst.start();
		panelFirst.update();
		//2.切换到首次要的加载面板，并将面板添加到根节点上
		gotoPanel(panelNameFirst);
		GameAPP.root.getChildren().add(getCurrentPanel());
	}
}
