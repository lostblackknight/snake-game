package team.tit.gluttonoussnake.ui.forgotpanel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import team.tit.gluttonoussnake.domain.ResultInfo;
import team.tit.gluttonoussnake.domain.User;
import team.tit.gluttonoussnake.manager.impl.UIManager;
import team.tit.gluttonoussnake.service.UserService;
import team.tit.gluttonoussnake.service.impl.UserServiceImpl;
import team.tit.gluttonoussnake.ui.BasePanel;
import team.tit.gluttonoussnake.ui.loginpanel.LoginPanel;

/**
 * 忘记密码面板类，找回密码
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月23日
 */
public class ForgotPanel extends BasePanel{

	private AnchorPane subRoot;
	private GridPane forgotBox;
	private Image im_background;
	private Image im_password;
	private Image im_mobilePhone;
	private ImageView iv_background;
	private ImageView iv_password;
	private ImageView iv_mobilePhone;
	private TextField t_mobilePhone;
	private TextField t_password;
	private Button b_lookUp;
	private Label l_title;
	private Label l_msg;
	private Label back;
	private ResultInfo infoForgot = null;
	
	@SuppressWarnings("static-access")
	@Override
	public void init() {
		//1.实例化对象
		subRoot = new AnchorPane();
		forgotBox = new GridPane();
		im_background = new Image(this.getClass().getClassLoader().getResource("images/RegisterPanel.png").toExternalForm(), 1280, 720, true, true);
		im_password = new Image(this.getClass().getClassLoader().getResource("images/PassWord.png").toExternalForm(), 20, 20, true, true);
		im_mobilePhone = new Image(this.getClass().getClassLoader().getResource("images/MobilePhone.png").toExternalForm(), 20, 20, true, true);
		iv_background = new ImageView(im_background);
		iv_password = new ImageView(im_password);
		iv_mobilePhone = new ImageView(im_mobilePhone);
		t_mobilePhone = new TextField();
		t_password = new TextField();
		l_title = new Label("LOOKUP");
		l_msg = new Label();
		b_lookUp = new Button("Look Up");
		back = new Label("返回");
		
		//2.设置组件ID，添加CSS样式
		forgotBox.setId("forgotBox");
		t_password.setId("t_password");
		t_mobilePhone.setId("t_mobilePhone");
		l_title.setId("l_title");
		l_msg.setId("l_msg");
		b_lookUp.setId("b_lookUp");
		back.setId("back");
		this.getStylesheets().add(this.getClass().getClassLoader().getResource("css/GluttonousSnake.css").toExternalForm());
		
		//3.设置布局
		forgotBox.setLayoutX(415);
		forgotBox.setLayoutY(90);
		forgotBox.setPrefWidth(450);
		forgotBox.setPrefHeight(550);
		forgotBox.setAlignment(Pos.TOP_CENTER);
		forgotBox.setHgap(10);
		forgotBox.setVgap(20);
		forgotBox.setMargin(l_title, new Insets(100, 0, 0, 51));
		forgotBox.setMargin(b_lookUp, new Insets(20, 0, 10, 35));
		forgotBox.setMargin(l_msg, new Insets(0, 0, 0, 68));
		forgotBox.setMargin(back, new Insets(68, 0, 0, 96));
		
		//4.设置按钮的宽和高
		b_lookUp.setPrefWidth(150);
		b_lookUp.setPrefHeight(10);
		
		//5.设置文本框提示文字和宽
		t_mobilePhone.setPromptText("mobilephone");
		t_password.setPrefWidth(250);
		t_mobilePhone.setPrefWidth(250);
		
		//6.设置焦点
		t_password.setFocusTraversable(false);
		t_mobilePhone.setFocusTraversable(false);
	}

	@Override
	public void start() {
		forgotBox.add(l_title, 1, 0);
		forgotBox.add(iv_mobilePhone, 0, 2);
		forgotBox.add(t_mobilePhone, 1, 2);
		forgotBox.add(iv_password, 0, 3);
		forgotBox.add(t_password, 1, 3);
		forgotBox.add(b_lookUp, 1, 4);
		forgotBox.add(l_msg, 1, 5);
		forgotBox.add(back, 1, 6);
		subRoot.getChildren().addAll(iv_background,forgotBox);
		this.getChildren().add(subRoot);
	}

	@Override
	public void update() {
		/*
		 * 找回密码
		 * 1.获取手机号
		 * 2.封装对象
		 * 3.调用service查询用户信息
		 * 4.判断用户是否存在
		 * 5.存在返回密码
		 * 6.不存在提示信息
		 */
		
		//1.找回密码
		b_lookUp.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				t_password.setText("");
				//1.获取手机号，封装对象
				String mobilePhone = t_mobilePhone.getText();
				User user = new User(mobilePhone);
				
				//2.调用service完成查询
				UserService service = new UserServiceImpl();
				User u = service.findPassword(user);
				infoForgot = new ResultInfo();
				
				//3.判断
				if (u == null) {
					//找回密码失败，提示信息
					infoForgot.setFlag(false);
					infoForgot.setMsg("找回密码失败");
					l_msg.setText(infoForgot.getMsg());
					l_msg.setStyle("-fx-text-fill: rgb(255, 91, 91)");
				}
				if (u != null) {
					//找回密码成功，提示信息
					infoForgot.setFlag(true);
					infoForgot.setData(u);
					infoForgot.setMsg("找回密码成功");
					l_msg.setText(infoForgot.getMsg());
					l_msg.setStyle("-fx-text-fill: #84C1FF");
					t_password.setText(u.getPassword());
				}				
			}
		});
		
		//2.回到登录面板
		back.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				//1.注册登录面板
				UIManager.getUiManager().regPanel("LoginPanel", new LoginPanel(infoForgot));
				
				//2.切换到登录面板
				UIManager.getUiManager().gotoPanel("LoginPanel");
			}
		});
	}
	
	@Override
	public void stop() {
		//将注册面板从集合中移除
		UIManager.getUiManager().delPanel("ForgotPanel");
	}

}
