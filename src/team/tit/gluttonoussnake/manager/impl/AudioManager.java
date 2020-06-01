package team.tit.gluttonoussnake.manager.impl;

import java.util.HashMap;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import team.tit.gluttonoussnake.audio.BaseAudio;
import team.tit.gluttonoussnake.audio.ClickAudio;
import team.tit.gluttonoussnake.audio.EatFoodAudio;
import team.tit.gluttonoussnake.audio.GameAudio;
import team.tit.gluttonoussnake.audio.GameOverAudio;
import team.tit.gluttonoussnake.audio.GameWinAudio;
import team.tit.gluttonoussnake.audio.MainMenuAudio;
import team.tit.gluttonoussnake.audio.SnakeDeathAudio;
import team.tit.gluttonoussnake.manager.Manager;

/**
 * Audio管理器类，用来管理所有音频
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月23日
 */
public class AudioManager implements Manager {

	private static AudioManager audioManager = new AudioManager();
	private HashMap<String,BaseAudio> audioMap;					//存储音频的容器
	private ObjectProperty<BaseAudio> currentAudio;				//存储当前音频并且具有监听
	
	private AudioManager() {
	}
	
	@Override
	public void onLaunch() {
		//1.实例化对象
		audioMap = new HashMap<String,BaseAudio>();
		currentAudio = new SimpleObjectProperty<BaseAudio>();
		
		//2.注册音频
		regAudio("ClickAudio", new ClickAudio());
		regAudio("EatFoodAudio", new EatFoodAudio());
		regAudio("GameAudio", new GameAudio());
		regAudio("GameOverAudio", new GameOverAudio());
		regAudio("GameWinAudio", new GameWinAudio());
		regAudio("MainMenuAudio", new MainMenuAudio());
		regAudio("SnakeDeathAudio", new SnakeDeathAudio());

		//3.添加属性监听，实现音频的切换
		currentAudio.addListener(new ChangeListener<BaseAudio>() {

			@Override
			public void changed(ObservableValue<? extends BaseAudio> observable, BaseAudio oldValue, BaseAudio newValue) {
				if(oldValue != null) {
					oldValue.close();
				}
				if(newValue != null) {
					newValue.init();
					newValue.play();
				}
			}
		});
	}

	@Override
	public void onFinish() {
		
	}

	public static AudioManager getAudioManager() {
		return audioManager;
	}
	
	/**
	 * 注册音频
	 * @param audioName 音频名字
	 * @param audio 音频
	 */
	public void regAudio(String audioName,BaseAudio audio) {
		audioMap.put(audioName, audio);
	}
	
	/**
	 * 删除音频
	 * @param audioName 音频名字
	 */
	public void delAudio(String audioName) {
		audioMap.remove(audioName);
	}
	
	/**
	 * 获取音频
	 * @param audioName 音频名字
	 * @return 根据音频名字获取音频
	 */
	public BaseAudio getAudio(String audioName) {
		return audioMap.get(audioName);
	}
	
	/**
	 * 获取当前播放的音频
	 * @return 返回前播放的音频
	 */
	public BaseAudio getCurrentAudio() {
		return currentAudio.get();
	}
	
	/**
	 * 获取当前音频的只读形式
	 * @return 返回当前音频的只读形式
	 */
	public ReadOnlyObjectProperty<BaseAudio> currentAudioProperty() {
		return currentAudio;
	}
	
	/**
	 * 切换音频
	 * @param audioName 音频名字
	 */
	public void gotoAudio(String audioName) {
		BaseAudio audio = getAudio(audioName);
		if(audio != null) {
			currentAudio.set(audio);
		}
	}
}
