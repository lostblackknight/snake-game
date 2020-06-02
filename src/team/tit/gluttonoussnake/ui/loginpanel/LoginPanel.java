package team.tit.gluttonoussnake.ui.loginpanel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
import team.tit.gluttonoussnake.ui.forgotpanel.ForgotPanel;
import team.tit.gluttonoussnake.ui.mainmenupanel.MainMenuPanel;
import team.tit.gluttonoussnake.ui.registerpanel.RegisterPanel;

/**
 * 登陆面板类，用户登录
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月22日
 */
public class LoginPanel extends BasePanel {

	private AnchorPane subRoot;
	private GridPane loginBox;
	private Image im_background;
	private Image im_username;
	private Image im_password;
	private ImageView iv_background;
	private ImageView iv_username;
	private ImageView iv_password;
	private TextField t_username;
	private PasswordField t_password;
	private Button b_signIn;
	private Label l_title;
	private Label l_login_fail;
	private Label register;
	private Label forgot_password;
	private ResultInfo infoRegisterOrForgot;
	private ResultInfo infoLogin = null;
	
	public LoginPanel() {
	}
	
	public LoginPanel(ResultInfo info) {
		this.infoRegisterOrForgot = info;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void init() {
		//1.实例化对象
		subRoot = new AnchorPane();
		loginBox = new GridPane();
		im_background = new Image(this.getClass().getClassLoader().getResource("images/LoginPanel.png").toExternalForm(), 1280, 720, true, true);
		im_username = new Image(this.getClass().getClassLoader().getResource("images/UserName.png").toExternalForm(), 20, 20, true, true);
		im_password = new Image(this.getClass().getClassLoader().getResource("images/PassWord.png").toExternalForm(), 20, 20, true, true);
		iv_background = new ImageView(im_background);
		iv_username = new ImageView(im_username);
		iv_password = new ImageView(im_password);
		l_title = new Label("LOGIN");
		l_login_fail = new Label();
		register = new Label("注册账号");
		forgot_password = new Label("忘记密码");
		t_username = new TextField();
		t_password = new PasswordField();
		b_signIn = new Button("Sign in");
		
		//2.设置组件ID，添加CSS样式
		loginBox.setId("loginBox");
		l_title.setId("l_title");
		t_username.setId("t_username");
		t_password.setId("t_password");
		b_signIn.setId("b_signIn");
		l_login_fail.setId("l_login_fail");
		register.setId("register");
		forgot_password.setId("forgot_password");
		this.getStylesheets().add(this.getClass().getClassLoader().getResource("css/GluttonousSnake.css").toExternalForm());
		
		//3.设置布局
		loginBox.setLayoutX(415);
		loginBox.setLayoutY(90);
		loginBox.setPrefWidth(450);
		loginBox.setPrefHeight(550);
		loginBox.setAlignment(Pos.TOP_CENTER);
		loginBox.setHgap(10);
		loginBox.setVgap(20);
		loginBox.setMargin(l_title, new Insets(100, 0, 0, 70));
		loginBox.setMargin(b_signIn, new Insets(20, 0, 0, 35));
		loginBox.setMargin(l_login_fail, new Insets(0, 0, 0, 54));
		loginBox.setMargin(register, new Insets(20, 0, 0, 82));
		loginBox.setMargin(forgot_password, new Insets(0, 0, 0, 82));
		
		//4.设置登录按钮的宽高
		b_signIn.setPrefWidth(150);
		b_signIn.setPrefHeight(10);
		
		//5.设置文本框提示文字和宽
		t_username.setPromptText("username");
		t_password.setPromptText("password");
		t_username.setPrefWidth(250);
		t_password.setPrefWidth(250);
		
		//6.实现数据的回写
		if (infoRegisterOrForgot != null && infoRegisterOrForgot.isFlag()) {
			User userReg = (User)infoRegisterOrForgot.getData();
			t_username.setText(userReg.getUsername());
			t_password.setText(userReg.getPassword());
		}
		
		//7.设置焦点
		t_username.setFocusTraversable(false);
		t_password.setFocusTraversable(false);
	}

	@Override
	public void start() {
		loginBox.add(l_title, 1, 0);
		loginBox.add(iv_username, 0, 2);
		loginBox.add(t_username, 1, 2);
		loginBox.add(iv_password, 0, 3);
		loginBox.add(t_password, 1, 3);
		loginBox.add(b_signIn, 1, 4);
		loginBox.add(l_login_fail, 1, 6);
		loginBox.add(register, 1, 7);
		loginBox.add(forgot_password, 1, 8);
		subRoot.getChildren().addAll(iv_background,loginBox);
		this.getChildren().add(subRoot);
	}

	@Override
	public void update() {
		/*
		 * 用户登录
		 * 1.获取用户输入信息
		 * 2.封装成user对象
		 * 3.调用service查询用户信息
		 * 4.判断用户是否存在
		 * 5.如果user存在登录成功，跳转到主菜单界面
		 * 6.user不存在，提示用户名或密码错误
		 */
		
		//1.用户登录
		b_signIn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//1.获取用户名和密码，封装对象
				String username = t_username.getText();
				String password = t_password.getText();
				User user = new User(username,password);
				
				//2.调用service完成查询
				UserService service = new UserServiceImpl();
				User u = service.login(user);
				
				System.out.println(u.getUid());
				System.out.println(u.getUsername());
				System.out.println(u.getPassword());
				infoLogin = new ResultInfo();
				
				//3.判断
				if (u == null) {
					//用户名或密码错误
					infoLogin.setFlag(false);
					infoLogin.setMsg("用户名或密码错误");
					l_login_fail.setText(infoLogin.getMsg());
				}
				if (u != null) {
					//登录成功，跳转到主菜单
					infoLogin.setFlag(true);
					infoLogin.setData(u);
					l_login_fail.setText("");
					
					//1.注册主菜单面板
					UIManager.getUiManager().regPanel("MainMenuPanel", new MainMenuPanel(infoLogin));
					
					//2.切换到主菜单面板
					UIManager.getUiManager().gotoPanel("MainMenuPanel");
				}
			}
		});
		
		//2.点击注册标签进入到注册界面
		register.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				//1.注册注册面板
				UIManager.getUiManager().regPanel("RegisterPanel", new RegisterPanel());
				
				//2.切换到注册面板
				UIManager.getUiManager().gotoPanel("RegisterPanel");
			}
		});
	
		//3.点击找回密码标签进入到找回密码界面
		forgot_password.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				//1.注册忘记密码面板
				UIManager.getUiManager().regPanel("ForgotPanel", new ForgotPanel());
				
				//2.切换到忘记密码面板
				UIManager.getUiManager().gotoPanel("ForgotPanel");
			}
		});
		
		//4.设置用户名焦点事件
		t_username.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue = true) {
					l_login_fail.setText("");
				}
			}
		});
		
		//5.设置密码焦点事件
		t_password.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue == true) {
					l_login_fail.setText("");
				}
			}
		});
	}
	
	@Override
	public void stop() {
		//将登录面板从集合中移除
		UIManager.getUiManager().delPanel("LoginPanel");
	}
}
