package team.tit.gluttonoussnake.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * 音频抽象类
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月23日
 */
public abstract class BaseAudio {

	public Media media;
	public MediaPlayer mp;
	
	/**
	 * 初始化音频
	 */
	public abstract void init();
	
	/**
	 * 播放音频
	 */
	public abstract void play();
	
	/**
	 * 释放音频
	 */
	public abstract void close();

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public MediaPlayer getMp() {
		return mp;
	}

	public void setMp(MediaPlayer mp) {
		this.mp = mp;
	}
}
