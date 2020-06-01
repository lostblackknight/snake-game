package team.tit.gluttonoussnake.audio;

import javafx.scene.media.AudioClip;

/**
 * 鼠标单击的音乐
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月23日
 */
public class ClickAudio extends BaseAudio{
	
	public ClickAudio() {
	}
	
	@Override
	public void init() {
		audio = new AudioClip(this.getClass().getClassLoader().getResource("music/click.wav").toExternalForm());
	}

	@Override
	public void play() {
		audio.play();
	}

	@Override
	public void close() {
		audio.stop();
	}

	@Override
	public AudioClip getAudio() {
		return super.getAudio();
	}

	@Override
	public void setAudio(AudioClip audio) {
		super.setAudio(audio);
	}
}
