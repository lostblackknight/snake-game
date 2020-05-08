package team.TYGY.GluttonousSnake.UI.MainMenuPanel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class LeiSureMenuItems {

	private HashMap<String,Label> menuItems;
	
	
	public LeiSureMenuItems() {
		initLeiSureMenuItems();
	}
	
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
	}
	
	public HashMap<String, Label> getMenuItems() {
		return menuItems;
	}
	public void setMenuItems(HashMap<String, Label> menuItems) {
		this.menuItems = menuItems;
	}
}
