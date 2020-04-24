package team.TYGY.GluttonousSnake.UI.MainMenuPanel;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class LeiSureMenuItems {

	private LeiSureMenuBox leiSureMenuBox;
	private HashMap<String,Label> menuItems;
	
	
	public LeiSureMenuItems(LeiSureMenuBox leiSureMenuBox) {
		this.leiSureMenuBox = leiSureMenuBox;
		initLeiSureMenuItems();
	}
	
	@SuppressWarnings("static-access")
	private void initLeiSureMenuItems() {
		/**
		 * 创建对象
		 */
		menuItems = new HashMap<String, Label>();
		menuItems.put("NEW_GAME", new Label("新游戏"));
		menuItems.put("CONTINUE", new Label("继续"));
		menuItems.put("BACK", new Label("返回"));
		
		/**
		 * 设置menuItems的属性
		 */
		Set<Map.Entry<String,Label>> entrySet = menuItems.entrySet();
		Iterator<Map.Entry<String, Label>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String,Label> node = iterator.next();
			String id = node.getKey();
			Label label = node.getValue();
			label.setId(id);
			label.setPrefSize(236, 35);
		}
		menuItems.get("NEW_GAME").setPadding(new Insets(-6, 0, -4, 0));
		menuItems.get("CONTINUE").setPadding(new Insets(-6, 0, -4, 0));
		menuItems.get("BACK").setPadding(new Insets(-1, 0, 0, 0));
		leiSureMenuBox.setMargin(menuItems.get("NEW_GAME"), new Insets(0, 0, 8, 60));
		leiSureMenuBox.setMargin(menuItems.get("CONTINUE"), new Insets(0, 0, 276, 60));
		leiSureMenuBox.setMargin(menuItems.get("BACK"), new Insets(0, 0, 43, 68));
		
		/**
		 * 设置menuItems的CSS样式
		 */
		URL cssUrl = this.getClass().getClassLoader().getResource("css/GluttonousSnake.css");
		leiSureMenuBox.getStylesheets().add(cssUrl.toExternalForm());
	}
	
	public void addLeiSureMenuItems() {
		leiSureMenuBox.add(menuItems.get("NEW_GAME"), 0, 1);
		leiSureMenuBox.add(menuItems.get("CONTINUE"), 0, 2);
		leiSureMenuBox.add(menuItems.get("BACK"), 0, 3);
	}
	
	public HashMap<String, Label> getMenuItems() {
		return menuItems;
	}
	public void setMenuItems(HashMap<String, Label> menuItems) {
		this.menuItems = menuItems;
	}
}
