package team.tit.gluttonoussnake.ui.registerpanel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import team.tit.gluttonoussnake.util.URLUtils;

/**
 * 注册面板类，用户注册
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月23日
 */
public class RegisterPanel extends BasePanel{

	private AnchorPane subRoot;
	private GridPane registerBox;
	private Image im_background;
	private Image im_username;
	private Image im_password;
	private Image im_mobilePhone;
	private ImageView iv_background;
	private ImageView iv_username;
	private ImageView iv_password;
	private ImageView iv_mobilePhone;
	private TextField t_username;
	private TextField t_mobilePhone;
	private TextField t_password;
	private Button b_signUp;
	private Label l_title;
	private Label l_fail_username;
	private Label l_fail_password;
	private Label l_fail_mobilePhone;
	private Label l_tip1;
	private Label l_tip2;
	private Label l_tip3;
	private Label l_msg;
	private Label back;
	private ResultInfo infoRegister = null;
	
	@SuppressWarnings("static-access")
	@Override
	public void init() {
		//1.实例化对象
		subRoot = new AnchorPane();
		registerBox = new GridPane();
		im_background = new Image(URLUtils.getURLString("images/RegisterPanel.png"), 1280, 720, true, true);
		im_username = new Image(URLUtils.getURLString("images/UserName.png"), 20, 20, true, true);
		im_password = new Image(URLUtils.getURLString("images/PassWord.png"), 20, 20, true, true);
		im_mobilePhone = new Image(URLUtils.getURLString("images/MobilePhone.png"), 20, 20, true, true);
		iv_background = new ImageView(im_background);
		iv_username = new ImageView(im_username);
		iv_password = new ImageView(im_password);
		iv_mobilePhone = new ImageView(im_mobilePhone);
		t_username = new TextField();
		t_mobilePhone = new TextField();
		t_password = new TextField();
		l_title = new Label("REGISTER");
		l_fail_username = new Label();
		l_fail_password = new Label();
		l_fail_mobilePhone = new Label();
		l_tip1 = new Label();
		l_tip2 = new Label();
		l_tip3 = new Label();
		l_msg = new Label();
		b_signUp = new Button("Sign Up");
		back = new Label("返回");
		
		//2.设置组件ID，添加CSS样式
		registerBox.setId("registerBox");
		t_username.setId("t_username");
		t_password.setId("t_password");
		t_mobilePhone.setId("t_mobilePhone");
		l_title.setId("l_title");
		l_msg.setId("l_msg");
		l_fail_username.setId("l_fail_username");
		l_fail_password.setId("l_fail_password");
		l_fail_mobilePhone.setId("l_fail_mobilePhone");
		l_tip1.setId("l_tip1");
		l_tip2.setId("l_tip1");
		l_tip3.setId("l_tip1");
		b_signUp.setId("b_signUp");
		back.setId("back");
		this.getStylesheets().add(URLUtils.getURLString("css/GluttonousSnake.css"));
		
		//3.设置布局
		registerBox.setLayoutX(415);
		registerBox.setLayoutY(90);
		registerBox.setPrefWidth(450);
		registerBox.setPrefHeight(550);
		registerBox.setAlignment(Pos.TOP_CENTER);
		registerBox.setHgap(10);
		registerBox.setVgap(15);
		registerBox.setMargin(l_title, new Insets(60, 0, 0, 51));
		registerBox.setMargin(b_signUp, new Insets(20, 0, 10, 35));
		registerBox.setMargin(l_msg, new Insets(0, 0, 0, 79));
		registerBox.setMargin(l_fail_password, new Insets(-15, 0, -15, 10));
		registerBox.setMargin(l_fail_username, new Insets(-15, 0, -15, 10));
		registerBox.setMargin(l_fail_mobilePhone, new Insets(-15, 0, -15, 10));
		registerBox.setMargin(l_tip2, new Insets(-12, 0, 0, 0));
		registerBox.setMargin(l_tip3, new Insets(-12, 0, 0, 0));
		registerBox.setMargin(back, new Insets(0, 0, 0, 96));
		
		//4.设置按钮的宽和高
		b_signUp.setPrefWidth(150);
		b_signUp.setPrefHeight(10);
		
		//5.设置文本框提示文字和宽
		t_username.setPromptText("username");
		t_password.setPromptText("password");
		t_mobilePhone.setPromptText("mobilephone");
		t_username.setPrefWidth(250);
		t_password.setPrefWidth(250);
		t_mobilePhone.setPrefWidth(250);
		
		//6.设置焦点
		t_username.setFocusTraversable(false);
		t_password.setFocusTraversable(false);
		t_mobilePhone.setFocusTraversable(false);
	}

	@Override
	public void start() {
		registerBox.add(l_title, 1, 0);
		registerBox.add(iv_username, 0, 2);
		registerBox.add(t_username, 1, 2);
		registerBox.add(l_fail_username, 1, 3);
		registerBox.add(iv_password, 0, 4);
		registerBox.add(t_password, 1, 4);
		registerBox.add(l_fail_password, 1, 5);
		registerBox.add(iv_mobilePhone, 0, 6);
		registerBox.add(t_mobilePhone, 1, 6);
		registerBox.add(l_fail_mobilePhone, 1, 7);
		registerBox.add(b_signUp, 1, 8);
		registerBox.add(l_msg, 1, 9);
		registerBox.add(l_tip1, 1, 10);
		registerBox.add(l_tip2, 1, 11);
		registerBox.add(l_tip3, 1, 12);
		registerBox.add(back, 1, 13);
		subRoot.getChildren().addAll(iv_background,registerBox);
		this.getChildren().add(subRoot);
	}

	@Override
	public void update() {
		/*
		 * 用户信息校验
		 * 1.用户名：不能包括空格，长度为3-15个字符，必须包含字母、数字、下划线至少1种
		 * 2.密码：不能含有特殊字符，长度为8-16个字符，必须包含字母和数字
		 * 3.手机号：符合手机格式
		 */
		
		//1.当点击提交按钮时，进行校验，校验成功后注册
		b_signUp.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				if (checkUsername() && checkPassword() && checkMobliePhone()) {
					//1.获取数据并封装对象
					String username = t_username.getText();
					String password = t_password.getText();
					String mobilePhone = t_mobilePhone.getText();
					User user = new User(username, password, mobilePhone);
					
					//2.调用service完成注册
					UserService service = new UserServiceImpl();
					boolean flag = service.regist(user);
					infoRegister = new ResultInfo();
					
					//3.响应结果
					if (flag) {
						//注册成功，提示注册成功
						infoRegister.setFlag(true);
						infoRegister.setData(user);
						infoRegister.setMsg("注册成功！");
						l_msg.setText(infoRegister.getMsg());
					} else {
						//注册失败，提示注册失败
						infoRegister.setFlag(false);
						infoRegister.setMsg("注册失败！");
						l_msg.setText(infoRegister.getMsg());
						l_fail_username.setText("用户名已存在");
					}
				}
			}
		});
		
		//2.当用户名失去焦点时进行校验，获取焦点时提示用户名的规则
		t_username.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue == false) {
					//失去焦点
					//1.校验用户名是否合法
					checkUsername();
					
					//2.隐藏用户名的规则
					l_tip1.setText("");
					l_tip2.setText("");
					l_tip3.setText("");
				}
				if (newValue == true) {
					//获取焦点
					//1.设置边框颜色为默认颜色，不显示用户名错误的提示信息，不显示注册的信息
					t_username.setStyle("-fx-border-color: rgb(128,128,128)");
					l_fail_username.setText("");
					l_msg.setText("");
					
					//2.提示用户名的规则
					l_tip1.setText("· 不能包括空格");
					l_tip2.setText("· 长度为3-15个字符");
					l_tip3.setText("· 必须包含字母、数字、下划线至少1种");
				}
			}
		});
		
		//3.当密码失去焦点时进行校验，获取焦点时提示密码的规则
		t_password.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue == false) {
					//失去焦点
					//1.校验密码是否合法
					checkPassword();
					
					//2.隐藏密码的规则
					l_tip1.setText("");
					l_tip2.setText("");
					l_tip3.setText("");
				}
				if (newValue == true) {
					//获取焦点
					//1.设置边框颜色为默认颜色，不显示密码错误的提示信息，不显示注册的信息
					t_password.setStyle("-fx-border-color: rgb(128,128,128)");
					l_fail_password.setText("");
					l_msg.setText("");
					
					//2.提示密码的规则
					l_tip1.setText("· 不能含有特殊字符");
					l_tip2.setText("· 长度为8-16个字符");
					l_tip3.setText("· 必须包含字母和数字");
				}
			}
		});
		
		//4.当手机号失去焦点时进行校验，获取焦点时提示手机号的规则
		t_mobilePhone.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue == false) {
					//失去焦点
					//1.校验手机号是否合法
					checkMobliePhone();
					
					//2.隐藏手机号的规则
					l_tip1.setText("");
				}
				if (newValue == true) {
					//获取焦点
					//1.设置边框颜色为默认颜色，不显示手机号错误的提示信息，不显示注册的信息
					t_mobilePhone.setStyle("-fx-border-color: rgb(128,128,128)");
					l_fail_mobilePhone.setText("");
					l_msg.setText("");
					
					//2.提示手机号的规则
					l_tip1.setText("· 请输入正确的手机号");
				}
			}
		});
		
		//5.回到登录面板
		back.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				//1.注册登录面板
				UIManager.getUiManager().regPanel("LoginPanel", new LoginPanel(infoRegister));
				
				//2.切换到登录面板
				UIManager.getUiManager().gotoPanel("LoginPanel");
			}
		});
	}
	
	@Override
	public void stop() {
		//将注册面板从集合中移除
		UIManager.getUiManager().delPanel("RegisterPanel");
	}
	
	/**
	 * 校验用户名是否合法
	 * @return 结果为true表示用户名合法，false表示用户名不合法
	 */
	boolean checkUsername() {
		//1.获取用户名
		String username = t_username.getText();
		//2.写正则
		String regex = "^\\w{3,15}+$";
		//2.判断
		if (username.matches(regex)) {
			//用户名合法，设置边框颜色为默认颜色，不提示信息
			t_username.setStyle("-fx-border-color: rgb(128,128,128)");
			l_fail_username.setText("");
			return true;
		} else {
			//用户名非法，设置边框颜色为红色，提示用户名格式不正确
			t_username.setStyle("-fx-border-color: rgb(255, 91, 91)");
			l_fail_username.setText("用户名格式不正确");
			return false;
		}
	}
	
	/**
	 * 校验密码是否合法
	 * @return 结果为true表示密码合法，false表示密码不合法
	 */
	boolean checkPassword() {
		//1.获取密码
		String password = t_password.getText();
		//2.写正则
		String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
		//3.判断
		if (password.matches(regex)) {
			//密码合法，设置边框颜色为默认颜色，不提示信息
			t_password.setStyle("-fx-border-color: rgb(128,128,128)");
			l_fail_password.setText("");
			return true;
		} else {
			//密码非法，设置边框颜色为红色，提示密码格式不正确
			t_password.setStyle("-fx-border-color: rgb(255, 91, 91)");
			l_fail_password.setText("密码格式不正确");
			return false;
		}
	}
	
	/**
	 * 校验手机号是否合法
	 * @return 结果为true表示手机号合法，false表示手机号不合法
	 */
	boolean checkMobliePhone() {
		//1.获取手机号
		String mobilePhone = t_mobilePhone.getText();
		//2.写正则
		String regex = "^((13[0-9])|(14[5-9])|(15([0-3]|[5-9]))|(16([5,6])|(17[0-8])|(18[0-9]))|(19[1,8,9]))\\d{8}$";
		//3.判断
		if (mobilePhone.matches(regex)) {
			//手机号合法，设置边框颜色为默认颜色，不提示信息
			t_mobilePhone.setStyle("-fx-border-color: rgb(128,128,128)");
			l_fail_mobilePhone.setText("");
			return true;
		} else {
			//手机号非法，设置边框颜色为红色，提示手机号格式不正确
			t_mobilePhone.setStyle("-fx-border-color: rgb(255, 91, 91)");
			l_fail_mobilePhone.setText("手机号格式不正确");
			return false;
		}
	}
}
