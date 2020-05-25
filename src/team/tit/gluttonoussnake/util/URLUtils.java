package team.tit.gluttonoussnake.util;

/**
 * 获取资源的URL并转换为字符串的工具类
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月21日
 */
public class URLUtils {
	
	/**
	 * 通过类加载器加载资源
	 * @param name 资源路径
	 * @return 返回一个URL的字符串形式
	 */
	public static String getURLString(String name) {
		String url = Thread.currentThread().getContextClassLoader().getResource(name).toExternalForm();
		return url;
	}
}
