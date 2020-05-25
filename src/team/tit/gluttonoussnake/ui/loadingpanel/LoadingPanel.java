package team.tit.gluttonoussnake.ui.loadingpanel;

import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import team.tit.gluttonoussnake.manager.impl.UIManager;
import team.tit.gluttonoussnake.ui.BasePanel;
import team.tit.gluttonoussnake.ui.loginpanel.LoginPanel;
import team.tit.gluttonoussnake.util.URLUtils;

/**
 * 加载面板类，加载动画
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月22日
 */
public class LoadingPanel extends BasePanel {

	private AnchorPane subRoot;
	private Media media;
	private MediaPlayer mp;
	private MediaView mv;

	public LoadingPanel() {
	}
	
	@Override
	public void init() {
		//1.实例化对象
		subRoot = new AnchorPane();
		media = new Media(URLUtils.getURLString("movies/FirstLoad.mp4"));
		mp = new MediaPlayer(media);
		mv = new MediaView(mp);
		
		//2.设置自动播放
		mp.setAutoPlay(true);
	}
	
	@Override
	public void start() {
		subRoot.getChildren().add(mv);
		this.getChildren().add(subRoot);
	}

	@Override
	public void update() {
		//媒体播放完时切换到登录面板
		mp.setOnEndOfMedia(new Runnable() {
			
			@Override
			public void run() {
				//1.注册登录面板
				UIManager.getUiManager().regPanel("LoginPanel", new LoginPanel());
				
				//2.切换到登陆面板
				UIManager.getUiManager().gotoPanel("LoginPanel");
			}
		});
	}
	
	@Override
	public void stop() {
		//释放资源
		mp.dispose();
		
		//将加载面板从集合中移除
		UIManager.getUiManager().delPanel("LoadingPanel");
	}
}
