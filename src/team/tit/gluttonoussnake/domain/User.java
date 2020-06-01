package team.tit.gluttonoussnake.domain;

/**
 * 用户类
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月22日
 */
public class User {

	private int uid;
	private String username;
	private String password;
	private String mobliePhone;
	
	public User() {
	}
	
	public User(String mobliePhone) {
		this.mobliePhone = mobliePhone;
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, String mobliePhone) {
		this.username = username;
		this.password = password;
		this.mobliePhone = mobliePhone;
	}

	public User(int uid, String username, String password, String mobliePhone) {
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.mobliePhone = mobliePhone;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMobliePhone() {
		return mobliePhone;
	}
	
	public void setMobliePhone(String mobliePhone) {
		this.mobliePhone = mobliePhone;
	}
}
