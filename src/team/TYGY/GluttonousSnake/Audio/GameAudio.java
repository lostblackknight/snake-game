package team.TYGY.GluttonousSnake.Audio;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 * 
 * ClassName: GameAudio
 * Description: TODO 
 * Date: 2020-05-17 10:08:07
 * 
 * @author 陈思祥
 * @version 1.3
 * @since JDK 1.8
 *
 */
public class GameAudio {
	public static void main(String[] args)
	{
		launch(args);
	}
	    
	private static void launch(String[] args) {
		// TODO Auto-generated method stub
		
	}
		public void start() throws UnsupportedEncodingException
		{
			URL url1 = this.getClass().getClassLoader().getResource("content/music/snakebgm.wav");
			System.out.println(url1.toExternalForm());
			String path1 = URLDecoder.decode(url1.toExternalForm(), "utf-8");
			System.out.println(path1);
			
			URL url2 = this.getClass().getClassLoader().getResource("content/music/eatfood.wav");
			System.out.println(url2.toExternalForm());
			String path2 = URLDecoder.decode(url2.toExternalForm(), "utf-8");
			System.out.println(path2);
			
			Media media1 = new Media(url1.toExternalForm());
			Media media2 = new Media(url2.toExternalForm());
			
			MediaPlayer mp1=new MediaPlayer(media1);
			mp1.setCycleCount(Integer.MAX_VALUE);
			MediaPlayer mp2=new MediaPlayer(media2);
			mp2.setCycleCount(1);
			
			mp1.setAutoPlay(true);
			
		}	
}
