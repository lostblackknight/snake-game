package team.tit.gluttonoussnake.service.impl;

import team.tit.gluttonoussnake.dao.UserDao;
import team.tit.gluttonoussnake.dao.impl.UserDaoImpl;
import team.tit.gluttonoussnake.domain.User;
import team.tit.gluttonoussnake.service.UserService;

/**
 * 处理用户业务的实现类
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月22日
 */
public class UserServiceImpl implements UserService{

	private UserDao userDao = new UserDaoImpl();
	
	/**
	 * 注册用户
	 * @param user user对象
	 * @return 返回true表示注册成功，返回false表示注册失败
	 */
	@Override
	public boolean regist(User user) {
		//1.根据用户名查询用户对象
		User u = userDao.findByUsername(user.getUsername());
		if (u != null) {
			//用户名存在，注册失败
			return false;
		}
		//2.保存用户信息
		userDao.save(user);
		return true;
	}

	/**
	 * 用户登录
	 * @param user user对象
	 * @return 返回一个在数据源中找到的user对象
	 */
	@Override
	public User login(User user) {
		User u = userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
		return u;
	}

	/**
	 * 找回密码
	 * @param user user对象
	 * @return 返回一个在数据源中找到的user对象
	 */ 
	@Override
	public User findPassword(User user) {
		User u = userDao.findByMobilePhone(user.getMobliePhone());
		return u;
	}
}
