package team.TYGY.GluttonousSnake.DB;

/**
 * 
 * @ClassName: Constant
 * @Description: TODO 存储所有的字面常量
 * @author: 陈思祥
 * @version: 1.3
 * @date: 2020年4月10日-下午9:21:01
 *  
 */
public class Constant {
	
	public static final String GAME_STAGE_TITLE = "GluttonousSnake";		//窗口的标题
	
	public static final int GAME_STAGE_X = 318;								//窗口的坐标X
	public static final int GAME_STAGE_Y = 163;								//窗口的坐标Y
	
	public static final int GAME_STAGE_BORDER_TOP = 32;						//窗口上边框的宽度
	public static final int GAME_STAGE_BORDER_BOTTOM = 3;					//窗口下边框的宽度
	public static final int GAME_STAGE_BORDER_LEFT = 3;						//窗口左边框的宽度
	public static final int GAME_STAGE_BORDER_RIGHT = 3;					//窗口右边框的宽度
	
	public static final int ROOT_PANEL_W = 1280;							//根节点的宽
	public static final int ROOT_PANEL_H = 720;								//根节点的高
	
	public static final int GAME_STAGE_W = ROOT_PANEL_W + 					//窗口的宽
			GAME_STAGE_BORDER_LEFT + GAME_STAGE_BORDER_RIGHT;			
	public static final int GAME_STAGE_H = ROOT_PANEL_H + 					//窗口的高
			GAME_STAGE_BORDER_TOP + GAME_STAGE_BORDER_BOTTOM;
	
	public static final int SNAKE_CELL_SIZE = 20;							
	public static final int SNAKE_GRID_W = ROOT_PANEL_W/20;				
	public static final int SNAKE_GRID_H = ROOT_PANEL_W/20;	
	
}
