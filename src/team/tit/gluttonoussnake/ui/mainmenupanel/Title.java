package team.tit.gluttonoussnake.ui.mainmenupanel;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * 菜单面板的标题
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月23日
 */
public class Title {

	private AnchorPane subRoot;
	private Image im;
	private ImageView iv;
	
	public Title(AnchorPane subRoot) {
		this.subRoot = subRoot;
		initTitle();
	}
	
	private void initTitle() {
		im = new Image(this.getClass().getClassLoader().getResource("images/GluttonousSnake.png").toExternalForm(), 400, 400, true, true);
		iv = new ImageView(im);
		iv.setX(60);
		iv.setY(68);
	}

	public void addTitle() {
		subRoot.getChildren().add(iv);
	}

	public Image getIm() {
		return im;
	}

	public void setIm(Image im) {
		this.im = im;
	}

	public ImageView getIv() {
		return iv;
	}

	public void setIv(ImageView iv) {
		this.iv = iv;
	}
}
