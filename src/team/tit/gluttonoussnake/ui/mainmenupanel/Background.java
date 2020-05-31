package team.tit.gluttonoussnake.ui.mainmenupanel;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * 主菜单背景
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月23日
 */
public class Background {
	
	private AnchorPane subRoot;
	private Image im;
	private ImageView iv;
	
	
	public Background(AnchorPane subRoot) {
		this.subRoot = subRoot;
		initBackground();
	}
	
	private void initBackground() {
		im = new Image(this.getClass().getClassLoader().getResource("images/MainMenuPanel.png").toExternalForm(), 1280, 720, true, true);
		iv = new ImageView(im);
	}
	public void addBackground() {
		subRoot.getChildren().add(iv);
	}
}
