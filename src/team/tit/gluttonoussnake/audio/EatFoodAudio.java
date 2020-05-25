package team.tit.gluttonoussnake.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import team.tit.gluttonoussnake.util.URLUtils;

/**
 * 蛇吃食物的音乐
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月23日
 */
public class EatFoodAudio extends BaseAudio{
	
	public EatFoodAudio() {
		media = new Media(URLUtils.getURLString("music/eatfood.wav"));
	}
	
	@Override
	public void init() {
		mp = new MediaPlayer(media);
	}

	@Override
	public void play() {
		mp.play();
	}

	@Override
	public void close() {
		mp.dispose();
	}
}
