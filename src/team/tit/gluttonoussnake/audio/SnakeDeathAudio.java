package team.tit.gluttonoussnake.audio;

import javafx.scene.media.AudioClip;

/**
 * 蛇死亡的音乐
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月23日
 */
public class SnakeDeathAudio extends BaseAudio{
	
	public SnakeDeathAudio() {
	}
	
	@Override
	public void init() {
		audio = new AudioClip(this.getClass().getClassLoader().getResource("music/snakedeath.wav").toExternalForm());
	}

	@Override
	public void play() {
		audio.play();
	}

	@Override
	public void close() {
		audio.stop();
	}
}
