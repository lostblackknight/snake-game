package team.TYGY.GluttonousSnake.UI;

import java.net.URL;
import java.util.function.UnaryOperator;

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
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * 
 * @ClassName: RegisterPanel
 * @Description: TODO 注册面板
 * @author: 陈思祥
 * @version: 1.3
 * @date: 2020年4月12日-下午4:38:16
 *
 */
public class RegisterPanel extends BasePanel{

	private AnchorPane ap;
	
	private Image im;
	private ImageView iv;
	
	private GridPane rectangle;
	
	private Label l_title;
	
	private Image im_username;
	private ImageView iv_username;
	
	private Image im_password;
	private ImageView iv_password;
	
	private Image im_mobilePhone;
	private ImageView iv_mobilePhone;

	private TextField r_t_username;
	private TextField r_t_mobilePhone;
	private PasswordField r_t_password;
	
	private Button b_signUp;
	
	private Label r_l_success;
	private Label r_l_fail;
	private Label r_l_fail_username;
	private Label r_l_fail_password;
	private Label r_l_fail_number;
	private Label r_l_tip1;
	private Label r_l_tip2;
	private Label r_l_tip3;
	
	private Label back;
	
	
	public RegisterPanel() {
		init();
		start();
		stop();
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void init() {
		
		ap = new AnchorPane();
		im = new Image("images/RegisterPanel.png", 1280, 720, true, true);
		iv = new ImageView(im);
		rectangle = new GridPane();
		l_title = new Label("REGISTER");
		im_username = new Image("images/UserName.png",20,20,true,true);
		im_password = new Image("images/PassWord.png",20,20,true,true);
		im_mobilePhone = new Image("images/MobilePhone.png",20,20,true,true);
		iv_username = new ImageView(im_username);
		iv_password = new ImageView(im_password);
		iv_mobilePhone = new ImageView(im_mobilePhone);
		r_t_username = new TextField();
		r_t_mobilePhone = new TextField();
		r_t_password = new PasswordField();
		b_signUp = new Button("Sign Up");
		r_l_success = new Label("注册成功");
		r_l_fail = new Label("账号、密码和手机号不能为空");
		r_l_fail_username = new Label("账号不可以为空");
		r_l_fail_password = new Label("密码不可以为空");
		r_l_fail_number = new Label("手机号不可以为空");
		r_l_tip1 = new Label("· 不能包括空格");
		r_l_tip2 = new Label("· 长度不超过18个字符");
		r_l_tip3 = new Label("· 必须包含字母、数字两种字符");
		back = new Label("返回");
		
		//保持宽高比
		iv.setPreserveRatio(true);
		iv_username.setPreserveRatio(true);
		iv_password.setPreserveRatio(true);
		iv_mobilePhone.setPreserveRatio(true);
		
		//设置ID
		URL cssUrl = this.getClass().getClassLoader().getResource("css/GluttonousSnake.css");
		this.getStylesheets().add(cssUrl.toExternalForm());
		rectangle.setId("rectangle");
		iv_username.setId("iv_username");
		iv_password.setId("iv_password");
		l_title.setId("l_title");
		r_t_username.setId("t_username");
		r_t_password.setId("t_password");
		r_t_mobilePhone.setId("t_mobilePhone");
		b_signUp.setId("b_signUp");
		r_l_success.setId("r_l_success");
		r_l_fail.setId("r_l_fail");
		r_l_fail_username.setId("r_l_fail_username");
		r_l_fail_password.setId("r_l_fail_password");
		r_l_fail_number.setId("r_l_fail_number");
		r_l_tip1.setId("r_l_tip1");
		r_l_tip2.setId("r_l_tip2");
		r_l_tip3.setId("r_l_tip3");
		back.setId("back");
		
		//设置布局
		rectangle.setLayoutX(415);
		rectangle.setLayoutY(90);
		rectangle.setPrefWidth(450);
		rectangle.setPrefHeight(550);
		rectangle.setAlignment(Pos.TOP_CENTER);
		rectangle.setHgap(10);
		rectangle.setVgap(15);
		rectangle.setMargin(l_title, new Insets(100, 0, 0, 40));
		rectangle.setMargin(b_signUp, new Insets(35, 0, 0, 25));
		rectangle.setMargin(r_l_success, new Insets(0, 0, 0, 72));
		rectangle.setMargin(r_l_fail, new Insets(0, 0, 0, 11));
		rectangle.setMargin(r_l_fail_password, new Insets(0, 0, 0, 52));
		rectangle.setMargin(r_l_fail_username, new Insets(0, 0, 0, 52));
		rectangle.setMargin(r_l_fail_number, new Insets(0, 0, 0, 46));
		rectangle.setMargin(r_l_tip2, new Insets(-12, 0, 0, 0));
		rectangle.setMargin(r_l_tip3, new Insets(-12, 0, 0, 0));
		rectangle.setMargin(back, new Insets(0, 0, 0, 86));
		
		//设置按钮宽高
		b_signUp.setPrefWidth(150);
		b_signUp.setPrefHeight(10);
		
		//设置文本框提示文字
		r_t_username.setPromptText("Username");
		r_t_password.setPromptText("Password");
		r_t_mobilePhone.setPromptText("Mobilephone");
		
		//设置焦点
		r_t_username.setFocusTraversable(false);
		r_t_password.setFocusTraversable(false);
		r_t_mobilePhone.setFocusTraversable(false);
		
		//设置透明度
		r_l_success.setStyle("-fx-opacity: 0");
		r_l_fail.setStyle("-fx-opacity: 0");
		r_l_fail_password.setStyle("-fx-opacity: 0");
		r_l_fail_username.setStyle("-fx-opacity: 0");
		r_l_fail_number.setStyle("-fx-opacity: 0");
		r_l_tip1.setStyle("-fx-opacity: 0");
		r_l_tip2.setStyle("-fx-opacity: 0");
		r_l_tip3.setStyle("-fx-opacity: 0");
	}

	@Override
	public void start() {
		
		rectangle.add(l_title, 1, 0);
		rectangle.add(iv_username, 0, 2);
		rectangle.add(r_t_username, 1, 2);
		rectangle.add(iv_password, 0, 3);
		rectangle.add(r_t_password, 1, 3);
		rectangle.add(iv_mobilePhone, 0, 4);
		rectangle.add(r_t_mobilePhone, 1, 4);
		rectangle.add(b_signUp, 1, 5);
		rectangle.add(r_l_success, 1, 7);
		rectangle.add(r_l_fail, 1, 7);
		rectangle.add(r_l_fail_username, 1, 7);
		rectangle.add(r_l_fail_password, 1, 7);
		rectangle.add(r_l_fail_number, 1, 7);
		rectangle.add(r_l_tip1, 1, 8);
		rectangle.add(r_l_tip2, 1, 9);
		rectangle.add(r_l_tip3, 1, 10);
		rectangle.add(back, 1, 11);
		
		ap.getChildren().addAll(iv,rectangle);
		this.getChildren().add(ap);
	}

	@Override
	public void stop() {
		
		//用户名提示
		r_t_username.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				r_l_tip1.setStyle("-fx-opacity: 0");
				r_l_tip2.setStyle("-fx-opacity: 0");
				r_l_tip3.setStyle("-fx-opacity: 0");
				r_l_success.setStyle("-fx-opacity: 0");
				r_l_fail.setStyle("-fx-opacity: 0");
				r_l_fail_password.setStyle("-fx-opacity: 0");
				r_l_fail_username.setStyle("-fx-opacity: 0");
				r_l_fail_number.setStyle("-fx-opacity: 0");
			}
		});
		
		//密码提示
		r_t_password.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				r_l_tip1.setStyle("-fx-opacity: 1");
				r_l_tip2.setStyle("-fx-opacity: 1");
				r_l_tip3.setStyle("-fx-opacity: 1");
				r_l_success.setStyle("-fx-opacity: 0");
				r_l_fail.setStyle("-fx-opacity: 0");
				r_l_fail_password.setStyle("-fx-opacity: 0");
				r_l_fail_username.setStyle("-fx-opacity: 0");
				r_l_fail_number.setStyle("-fx-opacity: 0");
			}
		});
		
		//手机号提示
		r_t_mobilePhone.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				r_l_tip1.setStyle("-fx-opacity: 0");
				r_l_tip2.setStyle("-fx-opacity: 0");
				r_l_tip3.setStyle("-fx-opacity: 0");
				r_l_success.setStyle("-fx-opacity: 0");
				r_l_fail.setStyle("-fx-opacity: 0");
				r_l_fail_password.setStyle("-fx-opacity: 0");
				r_l_fail_username.setStyle("-fx-opacity: 0");
				r_l_fail_number.setStyle("-fx-opacity: 0");
			}
		});
		
		//注册所遇到的各种情况
		b_signUp.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String name = r_t_username.getText(); 
				String password = r_t_password.getText();
				String mobilePhone = r_t_mobilePhone.getText();
				
				if(name.equals("") && (password.equals(""))&& mobilePhone.equals("")) {
					r_l_success.setStyle("-fx-opacity: 0");
					r_l_fail.setStyle("-fx-opacity: 1");
					r_l_fail_password.setStyle("-fx-opacity: 0");
					r_l_fail_username.setStyle("-fx-opacity: 0");
					r_l_fail_number.setStyle("-fx-opacity: 0");
					r_l_tip1.setStyle("-fx-opacity: 0");
					r_l_tip2.setStyle("-fx-opacity: 0");
					r_l_tip3.setStyle("-fx-opacity: 0");
					
				} else if (name.equals("")) {
					r_l_success.setStyle("-fx-opacity: 0");
					r_l_fail.setStyle("-fx-opacity: 0");
					r_l_fail_password.setStyle("-fx-opacity: 0");
					r_l_fail_username.setStyle("-fx-opacity: 1");
					r_l_fail_number.setStyle("-fx-opacity: 0");
					r_l_tip1.setStyle("-fx-opacity: 0");
					r_l_tip2.setStyle("-fx-opacity: 0");
					r_l_tip3.setStyle("-fx-opacity: 0");
					
				} else if (password.equals("")) {
					r_l_success.setStyle("-fx-opacity: 0");
					r_l_fail.setStyle("-fx-opacity: 0");
					r_l_fail_password.setStyle("-fx-opacity: 1");
					r_l_fail_username.setStyle("-fx-opacity: 0");
					r_l_fail_number.setStyle("-fx-opacity: 0");
					r_l_tip1.setStyle("-fx-opacity: 0");
					r_l_tip2.setStyle("-fx-opacity: 0");
					r_l_tip3.setStyle("-fx-opacity: 0");
					
				}else if (mobilePhone.equals("")) {
					r_l_success.setStyle("-fx-opacity: 0");
					r_l_fail.setStyle("-fx-opacity: 0");
					r_l_fail_password.setStyle("-fx-opacity: 0");
					r_l_fail_username.setStyle("-fx-opacity: 0");
					r_l_fail_number.setStyle("-fx-opacity: 1");
					r_l_tip1.setStyle("-fx-opacity: 0");
					r_l_tip2.setStyle("-fx-opacity: 0");
					r_l_tip3.setStyle("-fx-opacity: 0");
					
				}else {
					
					r_l_success.setStyle("-fx-opacity: 1");
					r_l_fail.setStyle("-fx-opacity: 0");
					r_l_fail_password.setStyle("-fx-opacity: 0");
					r_l_fail_username.setStyle("-fx-opacity: 0");
					r_l_fail_number.setStyle("-fx-opacity: 0");
					r_l_tip1.setStyle("-fx-opacity: 0");
					r_l_tip2.setStyle("-fx-opacity: 0");
					r_l_tip3.setStyle("-fx-opacity: 0");
				}
			}
		});
	
		//回到登录界面
		back.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				UIManager.getUiManager().gotoPanel("LoginPanel");
				r_t_username.clear();
				r_t_password.clear();
				r_t_mobilePhone.clear();
			}
		});
		
		//限制密码字符输入格式
		r_t_password.setTextFormatter(new TextFormatter<String>(new UnaryOperator<Change>() {

			@Override
			public Change apply(Change t) {
				String value = t.getText();
				System.out.println("value = " + value);
				if (value.matches("[a-z]*") || value.matches("[0-9]*") ||value.matches("[A-Z]*")) {
					return t;
				}
				return null;
			}
		}));
		
		//限制密码字符输入长度
		r_t_password.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				if (newValue.length() > 18) {
					r_t_password.setText(oldValue);
				}
			}
		});
	}
}
