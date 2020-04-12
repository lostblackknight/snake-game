package team.TYGY.GluttonousSnake.DB;

/**
 * 
 * @ClassName: DBManager
 * @Description: TODO DataBase管理器
 * @author: 陈思祥
 * @version: 1.3
 * @date: 2020年4月10日-下午9:24:43
 *
 */
public class DBManager {

	private static DBManager dbManager;
	
	
	private DBManager() {
		
	}

	public static DBManager getDbManager() {
		return dbManager;
	}
}
