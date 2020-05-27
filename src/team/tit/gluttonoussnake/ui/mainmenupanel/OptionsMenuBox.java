package team.tit.gluttonoussnake.ui.mainmenupanel;

import java.util.HashMap;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import team.tit.gluttonoussnake.manager.impl.AudioManager;
import team.tit.gluttonoussnake.util.URLUtils;

/**
 * 选项盒子
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月23日
 */
public class OptionsMenuBox extends AnchorPane {

	private AnchorPane subRoot;
	private GridPane optionsMenuBox;
	private GridPane choice;
	private GridPane content1;
	private GridPane content2;
	private Label back_options;
	private Label gameplay;
	private Label keyBinding;
	private Label gameBackground;
	private Label audio;
	private GridPane gameBackgroundContent;
	private Label text1;
	private Label text2;
	private Label text3;
	private Label text4;
	private Label text5;
	private Label text6;
	private Label text7;
	private Label text8;
	private GridPane audioContent;
	private Label soundEffects;
	private Label music;
	private Label movement;
	private Label speed;
	private GridPane movementContent;
	private Label up;
	private Label down;
	private Label left;
	private Label right;
	private Label up_key;
	private Label down_key;
	private Label left_key;
	private Label right_key;
	private GridPane speedContent;
	private Label speedUp;
	private Label speedDown;
	private Label speedUp_key;
	private Label speedDown_key;
	public static Slider slider1;//音效
	public static Slider slider2;//音乐
	private HashMap<String,GridPane> nodeMap = new HashMap<String, GridPane>();
	private ObjectProperty<GridPane> currentNode = new SimpleObjectProperty<GridPane>();
	
	public OptionsMenuBox() {
		initOptionsMenuBox();
		addOptionsMenuBox();
	}
	
	public static Slider getSlider1() {
		return slider1;
	}

	public static void setSlider1(Slider slider1) {
		OptionsMenuBox.slider1 = slider1;
	}

	public static Slider getSlider2() {
		return slider2;
	}

	public static void setSlider2(Slider slider2) {
		OptionsMenuBox.slider2 = slider2;
	}

	@SuppressWarnings("static-access")
	private void initOptionsMenuBox() {
		//1.实例化对象
		optionsMenuBox = new GridPane();
		choice = new GridPane();
		content1 = new GridPane();
		content2 = new GridPane();
		back_options = new Label("返回");
		regNode("content1", content1);
		regNode("content2", content2);
		gotoNode("content1");
		gameplay = new Label("游戏玩法");
		keyBinding = new Label("按键绑定");
		gameBackground = new Label("游戏背景");
		audio = new Label("音频");
		movement = new Label("移动");
		speed = new Label("速度");
		gameBackgroundContent = new GridPane();
		text1 = new Label("在遥远的未来世界，人类科技发达，人类");
		text2 = new Label("逐渐体会到了科技带来的便捷，一些心怀");
		text3 = new Label("不轨的人类想要利用科技控制地球。为了");
		text4 = new Label("对抗他们，科学家研究了蛇，在无穷尽的");
		text5 = new Label("对抗中，蛇的能量逐渐耗尽，体态逐渐残");
		text6 = new Label("缺。为了补充能源，恢复体态，人类需要");
		text7 = new Label("控制蛇不断进食，增强蛇的体态，来对抗");
		text8 = new Label("邪恶。");
		audioContent = new GridPane();
		soundEffects = new Label("音效"); 
		music = new Label("音乐"); 
		movementContent = new GridPane();
		up    = new Label("上");
		down  = new Label("下");
		left  = new Label("左");
		right = new Label("右");
		up_key = new Label("W");
		down_key = new Label("S");
		left_key = new Label("A");
		right_key = new Label("D");
		speedContent = new GridPane();
		speedUp = new Label("加速");
		speedDown = new Label("减速");
		speedUp_key =new Label("U");
		speedDown_key =new Label("I");
		slider1 = new Slider(0, 1, 0.5);
		slider2 = new Slider(0, 1, 0.5);
		
		//2.设置OptionsMenuBox的属性
		optionsMenuBox.setMargin(choice, new Insets(76, 0, 0, 63));
		optionsMenuBox.setMargin(content1, new Insets(28, 0, 0, 63));
		optionsMenuBox.setMargin(content2, new Insets(28, 0, 0, 63));
		optionsMenuBox.setMargin(back_options, new Insets(0, 0, 41, 63));
		optionsMenuBox.add(choice, 0, 0);
		optionsMenuBox.add(content1, 0, 1);
		optionsMenuBox.add(back_options, 0, 2);
		choice.setPrefHeight(24);
		choice.add(gameplay, 0, 0);
		choice.add(keyBinding, 1, 0);
		choice.setMargin(gameplay, new Insets(0, 42, 0, 0));
		choice.setMargin(keyBinding, new Insets(0, 0, 0, 0));
		gameplay.setId("GAME_PLAY");
		gameplay.setPadding(new Insets(-5, 0, -3, 0));
		keyBinding.setId("KEY_BINDING");
		keyBinding.setPadding(new Insets(-5, 0, -3, 0));
		gameBackground.setId("GAME_BACKGROUND");
		gameBackground.setPadding(new Insets(-3, 0, -3, 0));
		audio.setId("AUDIO");
		audio.setPadding(new Insets(-3, 0, -3, 0));
		text1.setId("text1");
		text2.setId("text2");
		text3.setId("text3");
		text4.setId("text4");
		text5.setId("text5");
		text6.setId("text6");
		text7.setId("text7");
		text8.setId("text8");
		gameBackgroundContent.add(text1, 0, 0);
		gameBackgroundContent.add(text2, 0, 1);
		gameBackgroundContent.add(text3, 0, 2);
		gameBackgroundContent.add(text4, 0, 3);
		gameBackgroundContent.add(text5, 0, 4);
		gameBackgroundContent.add(text6, 0, 5);
		gameBackgroundContent.add(text7, 0, 6);
		gameBackgroundContent.add(text8, 0, 7);
		gameBackgroundContent.setMargin(text1, new Insets(16,0,0,0));
		soundEffects.setId("soundEffects");
		music.setId("music");
		
		slider1.setPrefWidth(100);
		slider2.setPrefWidth(100);

		audioContent.add(soundEffects, 0, 0);
		audioContent.add(slider1, 1, 0);
		audioContent.add(music, 0, 1);
		audioContent.add(slider2, 1, 1);

		audioContent.setMargin(soundEffects, new Insets(16, 0, 24, 0));
		audioContent.setMargin(slider1, new Insets(-7, 0, 0, 106));
		audioContent.setMargin(slider2, new Insets(0, 0, 0, 106));
		content1.setPrefHeight(532);
		content1.setMargin(gameBackgroundContent, new Insets(0,0,60,0));
		content1.add(gameBackground, 0, 0);
		content1.add(gameBackgroundContent, 0, 1);
		content1.add(audio, 0, 2);
		content1.add(audioContent, 0, 3);
		movement.setId("MOVEMENT");
		movement.setPadding(new Insets(-3, 0, -3, 0));
		speed.setId("SPEED");
		speed.setPadding(new Insets(-3, 0, -3, 0));
		up.setId("up");
		down.setId("down");
		left.setId("left");
		right.setId("right");
		up_key.setId("up_key");
		down_key.setId("down_key");
		left_key.setId("left_key");
		right_key.setId("right_key");
		movementContent.add(up, 0, 0);
		movementContent.add(down, 0, 1);
		movementContent.add(left, 0, 2);
		movementContent.add(right, 0, 3);
		movementContent.add(up_key, 1, 0);
		movementContent.add(down_key, 1, 1);
		movementContent.add(left_key, 1, 2);
		movementContent.add(right_key, 1, 3);
		movementContent.setMargin(up, new Insets(16, 0, 26, 0));
		movementContent.setMargin(down, new Insets(0, 0, 26, 0));
		movementContent.setMargin(left, new Insets(0, 0, 26, 0));
		movementContent.setMargin(up_key, new Insets(16, 0, 26, 166));
		movementContent.setMargin(down_key, new Insets(0, 0, 26, 168));
		movementContent.setMargin(left_key, new Insets(0, 0, 26, 166));
		movementContent.setMargin(right_key, new Insets(0, 0, 0, 166));
		speedUp.setId("speedUp");
		speedDown.setId("speedDown");
		speedUp_key.setId("speedUp_key");
		speedDown_key.setId("speedDown_key");
		speedContent.add(speedUp, 0, 0);
		speedContent.add(speedDown, 0, 1);
		speedContent.add(speedUp_key, 1, 0);
		speedContent.add(speedDown_key, 1, 1);
		movementContent.setMargin(speedUp, new Insets(16, 0, 26, 0));
		movementContent.setMargin(speedUp_key, new Insets(20, 0, 26, 154));
		movementContent.setMargin(speedDown_key, new Insets(26, 0, 26, 154));
		content2.setPrefHeight(532);
		content2.setMargin(movementContent, new Insets(0,0,60,0));
		content2.add(movement, 0, 0);
		content2.add(movementContent, 0, 1);
		content2.add(speed, 0, 2);
		content2.add(speedContent, 0, 3);
		back_options.setPrefWidth(236);
		back_options.setId("back_options");
		
		//3.设置OptionsMenuBox的CSS样式
		this.getStylesheets().add(URLUtils.getURLString("css/GluttonousSnake.css"));
		this.setStyle("-fx-background-color:#000000");
		subRoot.setLeftAnchor(this, 0.0);
		subRoot.setRightAnchor(this, 0.0);
		subRoot.setTopAnchor(this, 0.0);
		subRoot.setBottomAnchor(this, 0.0);
		this.setLeftAnchor(optionsMenuBox, 0.0);
		this.setRightAnchor(optionsMenuBox, 0.0);
		this.setTopAnchor(optionsMenuBox, 0.0);
		this.setBottomAnchor(optionsMenuBox, 0.0);
		
		//4.添加属性监听，实现节点的切换
		currentNode.addListener(new ChangeListener<GridPane>() {

			@Override
			public void changed(ObservableValue<? extends GridPane> observable, GridPane oldValue, GridPane newValue) {
				if(oldValue != null) {
					optionsMenuBox.getChildren().remove(oldValue);
				}
				if(newValue != null) {
					optionsMenuBox.add(newValue, 0, 1);
					newValue.setStyle("-fx-text-fill: #d5b700");
				}
				
			}
		});
		
		//5.点击游戏玩法，切换到游戏玩法界面
		gameplay.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gotoNode("content1");
				gameplay.setStyle("-fx-text-fill:#d5b700");
				keyBinding.setStyle("-fx-text-fill:#ffffff");
				//加载音频
				loadClickAudio();
			}
		});
		
		//6.点击按键绑定，切换到按键绑定界面
		keyBinding.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				gotoNode("content2");
				keyBinding.setStyle("-fx-text-fill:#d5b700");
				gameplay.setStyle("-fx-text-fill:#ffffff");
				//加载音频
				loadClickAudio();
			}
		});
	}
	
	private void loadClickAudio() {
		AudioManager.getAudioManager().getAudio("ClickAudio").init();
		AudioManager.getAudioManager().getAudio("ClickAudio").getMp().volumeProperty().bind(OptionsMenuBox.slider1.valueProperty());
		AudioManager.getAudioManager().getAudio("ClickAudio").play();
	}
	
	public void gotoNode(String nodeName) {
		GridPane node = nodeMap.get(nodeName);
		if(node != null) {
			currentNode.set(node);
		}
	}
	
	public GridPane getCurrentNode() {
		return currentNode.get();
	}
	
	public void regNode(String nodeName,GridPane node) {
		nodeMap.put(nodeName, node);
	}
	
	public void delPanel(String nodeName) {
		nodeMap.remove(nodeName);
	}
	
	public GridPane getNode(String nodeName) {
		return nodeMap.get(nodeName);
	}
	
	public Label getBack_options() {
		return back_options;
	}

	public void setBack_options(Label back_options) {
		this.back_options = back_options;
	}
	
	private void addOptionsMenuBox() {
		this.getChildren().add(optionsMenuBox);
	}
}
