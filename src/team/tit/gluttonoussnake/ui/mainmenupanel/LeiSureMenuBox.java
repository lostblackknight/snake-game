package team.tit.gluttonoussnake.ui.mainmenupanel;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import team.tit.gluttonoussnake.util.URLUtils;

/**
 * 休闲模式的菜单盒子
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月23日
 */
public class LeiSureMenuBox extends GridPane {

	private LeiSureMenuItems leiSureMenuItems;
	
	public LeiSureMenuBox(LeiSureMenuItems leiSureMenuItems) {
		this.leiSureMenuItems = leiSureMenuItems;
		initLeiSureMenuBox();
		addLeiSureMenuBox();
	}
	
	@SuppressWarnings("static-access")
	private void initLeiSureMenuBox() {
		//1.设置MenuBox的属性
		this.setPrefSize(303, 413);
		this.setLayoutX(0);
		this.setLayoutY(307);
		this.setMargin(leiSureMenuItems.getMenuItems().get("NEW_GAME"), new Insets(0, 0, 8, 60));
		this.setMargin(leiSureMenuItems.getMenuItems().get("CONTINUE"), new Insets(0, 0, 276, 60));
		this.setMargin(leiSureMenuItems.getMenuItems().get("BACK"), new Insets(0, 0, 43, 68));
		
		//2.设置menuItems的CSS样式
		this.getStylesheets().add(URLUtils.getURLString("css/GluttonousSnake.css"));
	}
	
	private void addLeiSureMenuBox() {
		this.add(leiSureMenuItems.getMenuItems().get("NEW_GAME"), 0, 1);
		this.add(leiSureMenuItems.getMenuItems().get("CONTINUE"), 0, 2);
		this.add(leiSureMenuItems.getMenuItems().get("BACK"), 0, 3);
	}
}
