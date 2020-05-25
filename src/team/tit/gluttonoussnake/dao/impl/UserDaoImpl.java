package team.tit.gluttonoussnake.dao.impl;

import team.tit.gluttonoussnake.dao.UserDao;
import team.tit.gluttonoussnake.domain.User;

/**
 * 操纵数据源中的User
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月22日
 */
public class UserDaoImpl implements UserDao{

	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名
	 * @return 返回数据源中的user对象
	 */
	@Override
	public User findByUsername(String username) {
		//TO DO 根据用户名查询数据源中的user，返回user对象
		User user = null;
		
		User user1 = new User("182056219","csx123456","17735746553");
		if (username.equals(user1.getUsername())) {
			return user1;
		}
		return user;
	}
 
	/**
	 * 保存用户信息
	 * @param user user对象
	 */
	@Override
	public void save(User user) {
		// TODO 将user对象保存到数据源中
	}

	/**
	 * 根据用户名和密码查询用户信息
	 * @param username 用户名
	 * @param password 密码
	 * @return 返回一个user对象
	 */
	@Override
	public User findByUsernameAndPassword(String username, String password) {
		// TODO 根据用户名和密码查询数据源中的user，返回user对象
		User user = null;
		
		User user1 = new User("182056219","csx123456","17735746553");
		if (username.equals(user1.getUsername()) && password.equals(user1.getPassword())) {
			return user1;
		}
		return user;
	}

	/**
	 * 根据手机号查询用户信息
	 * @param mobliePhone 手机号
	 * @return 返回一个user对象
	 */
	@Override
	public User findByMobilePhone(String mobliePhone) {
		// TODO 根据手机号查询数据源中的user，返回user对象
		User user = null;
		
		User user1 = new User("182056219","csx123456","17735746553");
		if (mobliePhone.equals(user1.getMobliePhone())) {
			return user1;
		}
		return user;
	}
}
