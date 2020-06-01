package team.tit.gluttonoussnake.ui.mainmenupanel;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

/**
 * 主菜单盒子
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月23日
 */
public class MenuBox extends GridPane {

	private MenuItems menuItems;
	
	public MenuBox(MenuItems menuItems) {
		this.menuItems = menuItems;
		initMenuBox();
		addMenuBox();
	}
	
	@SuppressWarnings("static-access")
	private void initMenuBox() {
		//1.设置MenuBox的属性
		this.setPrefSize(303, 413);
		this.setLayoutX(0);
		this.setLayoutY(307);
		this.setMargin(menuItems.getMenuItem().get("LEISURE"), new Insets(0, 0, 8, 67));
		this.setMargin(menuItems.getMenuItem().get("ADVENTURE"), new Insets(0, 0, 26, 67));
		this.setMargin(menuItems.getMenuItem().get("OPTIONS"), new Insets(0, 0, 224, 67));
		this.setMargin(menuItems.getMenuItem().get("EXIT_TO_WINDOWS"), new Insets(0, 0, 41, 67));
		
		//2.设置menuItems的CSS样式
		this.getStylesheets().add(this.getClass().getClassLoader().getResource("css/GluttonousSnake.css").toExternalForm());
	}
	
	public void addMenuBox() {
		this.add(menuItems.getMenuItem().get("LEISURE"), 0, 1);
		this.add(menuItems.getMenuItem().get("ADVENTURE"), 0, 2);
		this.add(menuItems.getMenuItem().get("OPTIONS"), 0, 3);
		this.add(menuItems.getMenuItem().get("EXIT_TO_WINDOWS"),0,4);
	}
}
