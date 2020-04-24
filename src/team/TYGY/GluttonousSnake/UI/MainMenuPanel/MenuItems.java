package team.TYGY.GluttonousSnake.UI.MainMenuPanel;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class MenuItems {

	private MenuBox menuBox;
	private HashMap<String,Label> menuItems;
	

	public MenuItems(MenuBox menuBox) {
		this.menuBox = menuBox;
		initMenuItems();
	}
	
	@SuppressWarnings("static-access")
	private void initMenuItems() {
		/**
		 * 创建对象
		 */
		menuItems = new HashMap<>();
		menuItems.put("LEISURE", new Label("休闲模式"));
		menuItems.put("ADVENTURE", new Label("冒险模式"));
		menuItems.put("OPTIONS", new Label("选项"));
		menuItems.put("EXIT_TO_WINDOWS", new Label("退出至桌面"));
		
		
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
		menuItems.get("LEISURE").setPadding(new Insets(-7, 0, -3, 0));
		menuItems.get("ADVENTURE").setPadding(new Insets(-7, 0, -3, 0));
		menuItems.get("OPTIONS").setPadding(new Insets(-5, 0, -3, 0));
		menuItems.get("EXIT_TO_WINDOWS").setPadding(new Insets(-2, 0, -1, 0));
		menuBox.setMargin(menuItems.get("LEISURE"), new Insets(0, 0, 8, 67));
		menuBox.setMargin(menuItems.get("ADVENTURE"), new Insets(0, 0, 26, 67));
		menuBox.setMargin(menuItems.get("OPTIONS"), new Insets(0, 0, 224, 67));
		menuBox.setMargin(menuItems.get("EXIT_TO_WINDOWS"), new Insets(0, 0, 41, 67));
		
		/**
		 * 设置menuItems的CSS样式
		 */
		URL cssUrl = this.getClass().getClassLoader().getResource("css/GluttonousSnake.css");
		menuBox.getStylesheets().add(cssUrl.toExternalForm());
	}
	
	public void addMenuItems() {
		menuBox.add(menuItems.get("LEISURE"), 0, 1);
		menuBox.add(menuItems.get("ADVENTURE"), 0, 2);
		menuBox.add(menuItems.get("OPTIONS"), 0, 3);
		menuBox.add(menuItems.get("EXIT_TO_WINDOWS"),0,4);
	}
	
	public HashMap<String, Label> getMenuItem() {
		return menuItems;
	}

	public void setMenuItem(HashMap<String, Label> menuItem) {
		this.menuItems = menuItem;
	}	
}
