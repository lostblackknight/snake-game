package team.tit.gluttonoussnake.audio;

import javafx.scene.media.AudioClip;

/**
 * 游戏面板的音乐
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月23日
 */
public class GameAudio extends BaseAudio{
	
	public GameAudio() {
	}
	
	@Override
	public void init() {
		audio = new AudioClip(this.getClass().getClassLoader().getResource("music/snakebgm.wav").toExternalForm());
	}

	@Override
	public void play() {
		audio.setCycleCount(AudioClip.INDEFINITE);
		audio.play();
	}

	@Override
	public void close() {
		audio.stop();
	}
}
