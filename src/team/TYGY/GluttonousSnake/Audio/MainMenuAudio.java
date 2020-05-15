package team.TYGY.GluttonousSnake.Audio;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Stage;


/**
 * 
 * @ClassName: MainMenuAudio
 * @Description: TODO 
 * @author: 陈思祥
 * @version: 1.3
 * @date: 2020年4月10日-下午9:20:45
 *
 */
public class MainMenuAudio {
	public static void main(String[] args)
	{
		launch(args);
	}
	
	private static void launch(String[] args) {
		// TODO Auto-generated method stub
		
	}

	public void start(Stage primaryStage) throws Exception
	{
		URL url1 = this.getClass().getClassLoader().getResource("content/music/clip.wav");
		System.out.println(url1.toExternalForm());
		String path1 = URLDecoder.decode(url1.toExternalForm(), "utf-8");
		System.out.println(path1);
		Media media1 = new Media(url1.toExternalForm());
		MediaPlayer mp1 = new MediaPlayer(media1);
		mp1.setCycleCount(1);
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
		Button bu1 = new Button("播放");
		Button bu2 = new Button("暂停");
		AnchorPane root = new AnchorPane();
		root.getChildren().addAll(bu1,bu2);
		AnchorPane.setLeftAnchor(bu2, 200.0);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setWidth(1500);
		primaryStage.setHeight(900);
		primaryStage.centerOnScreen();
		primaryStage.setTitle("JavaFX = MediaPlayer");
		primaryStage.show();
		
		bu1.setOnAction(new EventHandler<ActionEvent>()
				{
			public void handle(ActionEvent event)
			{
				mp1.setVolume(0.1);
				mp1.play();
			}
				});
		bu1.setOnAction(new EventHandler<ActionEvent>()
				{
			public void handle(ActionEvent event)
			{
				mp1.dispose();
				mp1.stop();
			}
				});
	}
}
