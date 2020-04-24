package team.TYGY.GluttonousSnake.UI.LoginPanel;

import java.net.URL;

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
import team.TYGY.GluttonousSnake.UI.BasePanel;
import team.TYGY.GluttonousSnake.UI.UIManager;

/**
 * 
 * @ClassName: LoginPanel
 * @Description: TODO 登录面板
 * @author: 陈思祥
 * @version: 1.3
 * @date: 2020年4月12日-下午4:40:08
 *
 */
public class LoginPanel extends BasePanel {

	private AnchorPane ap;
	
	private Image im;
	private ImageView iv;
	
	private GridPane rectangle;
	
	private Label l_title;
	
	private Image im_username;
	private ImageView iv_username;
	
	private Image im_password;
	private ImageView iv_password;

	private TextField t_username;
	private PasswordField t_password;
	
	private Button b_signIn;
	
	private Label l_fail;
	private Label l_fail_username;
	private Label l_fail_password;
	private Label register;
	
	
	public LoginPanel() {
		init();
		start();
		stop();
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void init() {
		ap = new AnchorPane();
		im = new Image("images/LoginPanel.png", 1280, 720, true, true);
		iv = new ImageView(im);
		rectangle = new GridPane();
		l_title = new Label("LOGIN");
		im_username = new Image("images/UserName.png",20,20,true,true);
		im_password = new Image("images/PassWord.png",20,20,true,true);
		iv_username = new ImageView(im_username);
		iv_password = new ImageView(im_password);
		t_username = new TextField();
		t_password = new PasswordField();
		b_signIn = new Button("Sign in");
		l_fail = new Label("您输入的账户名或密码不正确");
		l_fail_username = new Label("请你输入账号后再登陆");
		l_fail_password = new Label("请你输入密码后再登陆");
		register = new Label("注册账号");
		
		//保持宽高比
		iv.setPreserveRatio(true);
		iv_username.setPreserveRatio(true);
		iv_password.setPreserveRatio(true);
		
		//设置ID
		URL cssUrl = this.getClass().getClassLoader().getResource("css/GluttonousSnake.css");
		this.getStylesheets().add(cssUrl.toExternalForm());
		rectangle.setId("rectangle");
		iv_username.setId("iv_username");
		iv_password.setId("iv_password");
		l_title.setId("l_title");
		t_username.setId("t_username");
		t_password.setId("t_password");
		b_signIn.setId("b_signIn");
		l_fail.setId("l_fail");
		l_fail_username.setId("l_fail_username");
		l_fail_password.setId("l_fail_password");
		register.setId("register");
		
		//设置布局
		rectangle.setLayoutX(415);
		rectangle.setLayoutY(90);
		rectangle.setPrefWidth(450);
		rectangle.setPrefHeight(550);
		rectangle.setAlignment(Pos.TOP_CENTER);
		rectangle.setHgap(10);
		rectangle.setVgap(20);
		rectangle.setMargin(l_title, new Insets(100, 0, 0, 55));
		rectangle.setMargin(b_signIn, new Insets(35, 0, 0, 25));
		rectangle.setMargin(l_fail, new Insets(0, 0, 0, 10));
		rectangle.setMargin(l_fail_username, new Insets(0, 0, 0, 30));
		rectangle.setMargin(l_fail_password, new Insets(0, 0, 0, 30));
		rectangle.setMargin(register, new Insets(0, 0, 0, 74));
		
		//设置按钮宽高
		b_signIn.setPrefWidth(150);
		b_signIn.setPrefHeight(10);
		
		//设置文本框提示文字
		t_username.setPromptText("Username");
		t_password.setPromptText("Password");
		
		//设置焦点
		t_username.setFocusTraversable(false);
		t_password.setFocusTraversable(false);
		
		//设置透明度
		l_fail.setStyle("-fx-opacity: 0");
		l_fail_password.setStyle("-fx-opacity: 0");
		l_fail_username.setStyle("-fx-opacity: 0");
		
		//测试值
		t_username.setUserData("123");
		t_password.setUserData("123");
	}

	@Override
	public void start() {
		
		rectangle.add(l_title, 1, 0);
		rectangle.add(iv_username, 0, 2);
		rectangle.add(t_username, 1, 2);
		rectangle.add(iv_password, 0, 3);
		rectangle.add(t_password, 1, 3);
		rectangle.add(b_signIn, 1, 4);
		rectangle.add(l_fail, 1, 6);
		rectangle.add(l_fail_username, 1, 6);
		rectangle.add(l_fail_password, 1, 6);
		rectangle.add(register, 1, 8);
		
		ap.getChildren().addAll(iv,rectangle);
		this.getChildren().add(ap);
	}

	@Override
	public void stop() {
		
		//登录所遇到的各种情况
		b_signIn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				String name = t_username.getText(); 
				String password = t_password.getText(); 
				
				if (t_username.getUserData().equals(name) && t_password.getUserData().equals(password)) {
					UIManager.getUiManager().gotoPanel("mainMenuPanel");
					
				}else if (name.equals("")) {
					l_fail.setStyle("-fx-opacity: 0");
					l_fail_password.setStyle("-fx-opacity: 0");
					l_fail_username.setStyle("-fx-opacity: 1");
					
				}else if (password.equals("")) {
					l_fail_username.setStyle("-fx-opacity: 0");
					l_fail.setStyle("-fx-opacity: 0");
					l_fail_password.setStyle("-fx-opacity: 1");
					
				}else{
					l_fail_username.setStyle("-fx-opacity: 0");
					l_fail_password.setStyle("-fx-opacity: 0");
					l_fail.setStyle("-fx-opacity: 1");
				}
			}
		});
		
		//进入注册界面
		register.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				UIManager.getUiManager().gotoPanel("RegisterPanel");
			}
		});
	}
}
