package team.tit.gluttonoussnake.ui.loadingpanel;

import java.util.ArrayList;

import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import team.tit.gluttonoussnake.animation.npc.Wall;
import team.tit.gluttonoussnake.manager.impl.UIManager;
import team.tit.gluttonoussnake.service.WallService;
import team.tit.gluttonoussnake.service.impl.WallServiceImpl;
import team.tit.gluttonoussnake.ui.BasePanel;
import team.tit.gluttonoussnake.ui.loginpanel.LoginPanel;

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
	public static ArrayList<Wall> list = new ArrayList<Wall>();

	public LoadingPanel() {
	}
	
	@Override
	public void init() {
		//1.实例化对象
		subRoot = new AnchorPane();
		media = new Media(this.getClass().getClassLoader().getResource("movies/FirstLoad.mp4").toExternalForm());
		mp = new MediaPlayer(media);
		mv = new MediaView(mp);
		
		mp.setStartTime(Duration.seconds(0));
		mp.setStopTime(Duration.seconds(5));
		//2.设置自动播放
		mp.play();
		
		//3.获取地图数据
		/*
		 * 1.查询数据库返回一个Wall对象的集合
		 * 2.
		 */	
		WallService service = new WallServiceImpl();
		ArrayList<Wall> list = service.findAll();
		
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
				mp.dispose();
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
