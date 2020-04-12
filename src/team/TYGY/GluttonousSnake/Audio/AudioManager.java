package team.TYGY.GluttonousSnake.Audio;

/**
 * 
 * @ClassName: AudioManager
 * @Description: TODO Audio管理器
 * @author: 陈思祥
 * @version: 1.3
 * @date: 2020年4月10日-下午9:20:23
 *
 */
public class AudioManager {

	private static AudioManager audioManager = new AudioManager();
	
	
	private AudioManager() {
		
	}

	public static AudioManager getAudioManager() {
		return audioManager;
	}
}
