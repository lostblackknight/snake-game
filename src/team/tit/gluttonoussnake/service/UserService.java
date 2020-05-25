package team.tit.gluttonoussnake.service;

import team.tit.gluttonoussnake.domain.User;

/**
 * 处理用户业务的接口
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月22日
 */
public interface UserService {

	/**
	 * 注册用户
	 * @param user user对象
	 * @return 返回true表示注册成功，返回false表示注册失败
	 */
	boolean regist(User user);

	/**
	 * 用户登录
	 * @param user user对象
	 * @return 返回一个在数据源中找到的user对象
	 */
	User login(User user);

	/**
	 * 找回密码
	 * @param user user对象
	 * @return 返回一个在数据源中找到的user对象
	 */
	User findPassword(User user);
}
