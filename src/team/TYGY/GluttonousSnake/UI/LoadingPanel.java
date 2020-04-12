package team.TYGY.GluttonousSnake.UI;

import java.net.URL;

import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import team.TYGY.GluttonousSnake.GameAPP.GameAPP;

/**
 * 
 * @ClassName: LoadingPanel
 * @Description: TODO 加载面板
 * @author: 陈思祥
 * @version: 1.3
 * @date: 2020年4月10日-下午8:56:39
 *
 */
public class LoadingPanel extends BasePanel {

	private AnchorPane ap;
	private Media media;
	private MediaPlayer mp;
	private MediaView mv;
	
	public LoadingPanel() {
		init();
		start();
		stop();
	}

	public void init() {
		
		URL url = this.getClass().getClassLoader().getResource("movies/FirstLoad.mp4");
		String path = url.toExternalForm();
		
		ap = new AnchorPane();
		media = new Media(path);
		mp = new MediaPlayer(media);
		mv = new MediaView(mp);

		//设置自动播放
		mp.setAutoPlay(true);
		
		//绑定
		ap.prefWidthProperty().bind(GameAPP.root.widthProperty());
		ap.prefHeightProperty().bind(GameAPP.root.heightProperty());
		mv.fitWidthProperty().bind(ap.widthProperty());
		mv.fitHeightProperty().bind(ap.heightProperty());
	}

	public void start() {
		ap.getChildren().add(mv);
		this.getChildren().add(ap);
	}

	public void stop() {
		mp.setOnEndOfMedia(new Runnable() {
			
			@Override
			public void run() {
				UIManager.getUiManager().gotoPanel("LoginPanel");
			}
		});
	}
}
