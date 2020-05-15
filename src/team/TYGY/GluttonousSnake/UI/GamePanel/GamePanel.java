package team.TYGY.GluttonousSnake.UI.GamePanel;

import static team.TYGY.GluttonousSnake.Animation.ConstantUtil.DOWN;

import static team.TYGY.GluttonousSnake.Animation.ConstantUtil.GAME_PANEL_W;
import static team.TYGY.GluttonousSnake.Animation.ConstantUtil.LEFT;
import static team.TYGY.GluttonousSnake.Animation.ConstantUtil.RIGHT;
import static team.TYGY.GluttonousSnake.Animation.ConstantUtil.UP;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import team.TYGY.GluttonousSnake.Animation.Map;
import team.TYGY.GluttonousSnake.Animation.NPC.Food;
import team.TYGY.GluttonousSnake.Animation.NPC.Wall;
import team.TYGY.GluttonousSnake.Animation.Player.Snake;
import team.TYGY.GluttonousSnake.GameAPP.GameAPP;
import team.TYGY.GluttonousSnake.UI.BasePanel;


public class GamePanel extends BasePanel{
	private AnchorPane subRoot;
	private Background background;
	private ScorePanel scorePanel;
	private SubMenu subMenu;
	private GameOverPanel gameOverPanel;
	
//游戏需要的对象
	AnimationTimer at1;
	GraphicsContext gc;
	public Snake snake=new Snake();
	public Food food=new Food();
	Map map=new Map();
	int anjian;
	Wall wall=new Wall();
	protected GameState mGameState;
	
	public GamePanel() {

		init();
		start();
		//update();
		stop();
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void init() {
		subRoot = new AnchorPane();
		background = new Background(subRoot);
		scorePanel = new ScorePanel(subRoot);
		subMenu = new SubMenu();
		gameOverPanel = new GameOverPanel();
		
		
		GameAPP.root.setLeftAnchor(this, 0.0);
		GameAPP.root.setRightAnchor(this, 0.0);
		GameAPP.root.setTopAnchor(this, 0.0);
		GameAPP.root.setBottomAnchor(this, 0.0);
		this.setLeftAnchor(subRoot, 0.0);
		this.setRightAnchor(subRoot, 0.0);
		this.setTopAnchor(subRoot, 0.0);
		this.setBottomAnchor(subRoot, 0.0);
		
	}

	@Override
	public void start() {
//背景
		background.addBackground();
//计分板
		scorePanel.addScorePanel();		
//加入画布
        Canvas canvas=new Canvas(1280,720);
		gc=canvas.getGraphicsContext2D();		
		subRoot.getChildren().add(canvas);
		this.getChildren().add(subRoot);
		
//animationtimer1
		 at1=new AnimationTimer() {		
			@Override
			public void handle(long now)
			{				
				draw(gc);
				update();	
			}
		};
	
//animationtimer1开始	
		at1.start();
		mGameState= GameState.GAME_START;
		
//加入监听
	GameAPP.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event)
					{                        
						onKeyPressed(event);	
					}		
				});
	GameAPP.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event)
					{
                           
						onKeyReleased(event);
					}			
				});
	}

	
//画
	 public void draw(GraphicsContext gc) {
		gc.setEffect(null);
		gc.clearRect(0, 0,GAME_PANEL_W,GAME_PANEL_W);
		//画网格
		map.paintGrid(gc);	
		//画食物
		food.paintFood(gc);
		//画蛇
		snake.paintSnake(gc);	
		//随机位置画一个障碍物（测试）
		wall.paintWall(gc);
	}
	 
//更新
	@Override
	public void update() {			  
		if(snake.isLife()) {
			if(snake.isPause() == false) {
				if(food.isFoodEated(snake)) {
					snake.eatFood();
					food.newFood(food);
				}
				snake.move(snake);
				draw(gc);
				if(snake.isEatBody()||wall.isKnockWall(snake)) {				
					at1.stop();
					snake.die();											
					mGameState= GameState.GAME_END;
					//跳转到游戏结束界面	
					System.out.println("gameover");
				}				
				
			}
		}
		try
		{
			Thread.sleep(snake.getSleepTime());
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	@Override
	public void stop() {
      
		//at1.stop();
	}

	
	//监听
	public void onKeyPressed(KeyEvent event) {
		switch(event.getCode()){
		case A:		
			// 方向变化时蛇不能回头，以下类同		 
			if(snake.getHead().getDir() != RIGHT&&snake.ismove==0){								
				snake.getHead().setDir(LEFT);								
				}		
			snake.ismove=1;
			break;
		case W:
			if(snake.getHead().getDir() != DOWN&&snake.ismove==0){								
				snake.getHead().setDir(UP);											
				}		
			snake.ismove=1;
			break;
		case D:
			if(snake.getHead().getDir() != LEFT&&snake.ismove==0){									
				snake.getHead().setDir(RIGHT);											
				}		
			snake.ismove=1;
			break;
		case S :
			if(snake.getHead().getDir() != UP&&snake.ismove==0){			
				snake.getHead().setDir(DOWN);									
				}		
			snake.ismove=1;
			break;
		case SPACE :
			if(snake.isPause()==true) {
				snake.setPause(false);
				mGameState= GameState.GAME_CONTINUE;
			}else {
				snake.setPause(true);
				mGameState= GameState.GAME_PAUSE;
			}
				
			break;
			//按下U加速
		case U :
			if(snake.getSleepTime()!=50)
				snake.setSleepTime(50);
			break;
			//按下I减速
		case I :
			if(snake.getSleepTime()!=550)
				snake.setSleepTime(550); 
			break;
		default:
			break;
		}		
	}
		
	public void onKeyReleased( KeyEvent event) {	
		
		switch(event.getCode()){
		//释放U变回原来的速度
		case U :			
				snake.setSleepTime(300);
			break;
		//释放I变回原来的速度
		case I :			
				snake.setSleepTime(300);
			break;
		default:
			break;			
		}
	}	
	
	
	
	//枚举类型
	protected enum GameState {
		 GAME_START, GAME_PAUSE,GAME_CONTINUE, GAME_END
	};


}





