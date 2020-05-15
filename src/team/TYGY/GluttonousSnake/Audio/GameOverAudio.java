package team.TYGY.GluttonousSnake.Audio;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

/**
 * 
 * @ClassName: GameOverAudio
 * @Description: TODO 
 * @author: 陈思祥
 * @version: 1.3
 * @date: 2020年4月10日-下午9:20:38
 *
 */
public class GameOverAudio {
	public static void main(String[] args)
	{
		launch(args);
	}
	
	private static void launch(String[] args) {
		// TODO Auto-generated method stub
		
	}
	public void start() throws UnsupportedEncodingException
	{
		URL url1 = this.getClass().getClassLoader().getResource("content/music/sankedeath.wav");
		System.out.println(url1.toExternalForm());
		String path1 = URLDecoder.decode(url1.toExternalForm(), "utf-8");
		System.out.println(path1);
		
		URL url2 = this.getClass().getClassLoader().getResource("content/music/gameover.wav");
		System.out.println(url2.toExternalForm());
		String path2 = URLDecoder.decode(url2.toExternalForm(), "utf-8");
		System.out.println(path2);
		
		URL url3 = this.getClass().getClassLoader().getResource("content/music/gamewin.wav");
		System.out.println(url3.toExternalForm());
		String path3 = URLDecoder.decode(url1.toExternalForm(), "utf-8");
		System.out.println(path3);
		
		Media media1 = new Media(url1.toExternalForm());
		Media media2 = new Media(url2.toExternalForm());
		Media media3 = new Media(url3.toExternalForm());
		
		MediaPlayer mp1 = new MediaPlayer(media1);
		mp1.setCycleCount(1);
		MediaPlayer mp2 = new MediaPlayer(media2);
		mp2.setCycleCount(1);
		MediaPlayer mp3 = new MediaPlayer(media3);
		mp3.setCycleCount(1);
		
		mp1.setOnError(new Runnable()
		{
			public void run()
			{
				System.out.println("错误");
			}
		});
		mp1.setOnHalted(new Runnable()
		{
			public void run()
			{
				System.out.println("无法修复的错误，无法再使用MediaPlayer");
			}
		});
		mp1.setOnStalled(new Runnable()
		{
			public void run()
			{
				System.out.println("进入缓冲阶段");
			}
		});
		mp1.setOnRepeat(new Runnable()
		{
			public void run()
			{
				System.out.println("重复播放");
			}
		});
		mp1.setOnPaused(new Runnable()
		{
			public void run()
			{
				System.out.println("暂停");
			}
		});
		mp1.setOnStopped(new Runnable()
		{
			public void run()
			{
				System.out.println("停止重置");
			}
		});
		mp1.setOnPlaying(new Runnable()
		{
			public void run()
			{
				System.out.println("正在播放");
			}
		});
		mp1.setOnReady(new Runnable()
		{
			public void run()
			{
				System.out.println("准备阶段");
				mp1.setVolume(0.2);
				mp1.play();
			}
		});
		mp1.setOnEndOfMedia(new Runnable()
		{
			public void run()
			{
				System.out.println("结束");
			}
		});
		mp1.statusProperty().addListener(new ChangeListener<Status>()
		{
	        @Override
			public void changed(ObservableValue<? extends Status> observable, Status oldValue, Status newValue) 
	        {
				System.out.println("status = " + newValue.name());
				}
		});
		mp1.errorProperty().addListener(new ChangeListener<MediaException>()
		{

			@Override
			public void changed(ObservableValue<? extends MediaException> observable, MediaException oldValue,
					MediaException newValue) {
				System.out.println("MediaException = " + newValue.getMessage());
			}
		});
	}	
}
