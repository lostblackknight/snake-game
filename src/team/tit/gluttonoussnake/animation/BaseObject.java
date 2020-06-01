/*
 *
 */
package team.tit.gluttonoussnake.animation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

/**
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月25日
 */
public abstract class BaseObject {

	protected int id;
	protected int x;
	protected int y;
	protected int width;
	protected int height;

	public BaseObject() {
	}

	/**
	 * 初始化
	 */
	public void init() {
	}

	/**
	 * 画对象
	 * 
	 * @param gc 画笔
	 */
	public abstract void draw(GraphicsContext gc);

	/**
	 * 更新对象
	 */
	public abstract void update();

	/**
	 * 默认按键按下事件
	 * 
	 * @param event
	 */
	public void onKeyPressed(KeyEvent event) {
	}

	/**
	 * 默认按键释放事件
	 * 
	 * @param event
	 */
	public void onKeyReleased(KeyEvent event) {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
