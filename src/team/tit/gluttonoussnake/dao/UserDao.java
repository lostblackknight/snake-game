package team.tit.gluttonoussnake.dao;

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
public interface UserDao {
	
	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名
	 * @return 返回一个user对象
	 */
	User findByUsername(String username);
	
	/**
	 * 保存用户信息
	 * @param user user对象
	 */
	void save(User user);

	/**
	 * 根据用户名和密码查询用户信息
	 * @param username 用户名
	 * @param password 密码
	 * @return 返回一个user对象
	 */
	User findByUsernameAndPassword(String username, String password);

	/**
	 * 根据手机号查询用户信息
	 * @param mobliePhone 手机号
	 * @return 返回一个user对象
	 */
	User findByMobilePhone(String mobliePhone);
}
