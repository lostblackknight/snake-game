package team.tit.gluttonoussnake.ui.mainmenupanel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

/**
 * 冒险模式菜单盒子的选项
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月23日
 */
public class AdventureMenuItems {

	private HashMap<String,Label> menuItems;
	
	public AdventureMenuItems() {
		initLeiSureMenuItems();
	}
	
	private void initLeiSureMenuItems() {
		//1.实例化对象
		menuItems = new HashMap<String, Label>();
		menuItems.put("NEW_GAME", new Label("新游戏"));
		menuItems.put("CONTINUE", new Label("继续"));
		menuItems.put("BACK", new Label("返回"));
		
		//2.设置menuItems的属性
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
