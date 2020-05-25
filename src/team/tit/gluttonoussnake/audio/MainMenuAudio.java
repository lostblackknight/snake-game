package team.tit.gluttonoussnake.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import team.tit.gluttonoussnake.util.URLUtils;

/**
 * 主菜单的音乐
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月23日
 */
public class MainMenuAudio extends BaseAudio{
	
	public MainMenuAudio() {
		media = new Media(URLUtils.getURLString("music/mainmenubgm.wav"));
	}
	
	@Override
	public void init() {
		mp = new MediaPlayer(media);
	}

	@Override
	public void play() {
		//设置循环次数
		mp.setCycleCount(MediaPlayer.INDEFINITE);
		mp.play();
	}

	@Override
	public void close() {
		mp.dispose();
	}

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
