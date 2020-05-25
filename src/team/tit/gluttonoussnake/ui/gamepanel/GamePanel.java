package team.tit.gluttonoussnake.ui.gamepanel;

import static team.tit.gluttonoussnake.animation.ConstantUtil.DOWN;
import static team.tit.gluttonoussnake.animation.ConstantUtil.GAME_PANEL_W;
import static team.tit.gluttonoussnake.animation.ConstantUtil.LEFT;
import static team.tit.gluttonoussnake.animation.ConstantUtil.RIGHT;
import static team.tit.gluttonoussnake.animation.ConstantUtil.UP;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import team.tit.gluttonoussnake.animation.Map;
import team.tit.gluttonoussnake.animation.npc.Food;
import team.tit.gluttonoussnake.animation.npc.Wall;
import team.tit.gluttonoussnake.animation.player.Snake;
import team.tit.gluttonoussnake.animation.player.SnakeNode;
import team.tit.gluttonoussnake.domain.Game;
import team.tit.gluttonoussnake.domain.ResultInfo;
import team.tit.gluttonoussnake.gameapp.GameAPP;
import team.tit.gluttonoussnake.manager.impl.AudioManager;
import team.tit.gluttonoussnake.manager.impl.UIManager;
import team.tit.gluttonoussnake.ui.BasePanel;
import team.tit.gluttonoussnake.ui.mainmenupanel.MainMenuPanel;
import team.tit.gluttonoussnake.ui.mainmenupanel.OptionsMenuBox;

/**
 * 游戏面板
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月23日
 */
public class GamePanel extends BasePanel{

	private AnchorPane subRoot;
	private Background background;
	private ScorePanel scorePanel;
	private SubMenu subMenu;
	private GameOverPanel gameOverPanel;
	private boolean flag = false;
	private ResultInfo mainInfo;
	
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
	}

	public GamePanel(ResultInfo mainInfo) {
		this.mainInfo = mainInfo;
	}
	public GamePanel(int id) {
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
		
//		boolean flag = mainInfo.isFlag();
//		Game game = (Game)mainInfo.getData();
//		if (mainInfo != null && flag == true) {
//			int headX = game.getSanke().getSnakeHeadX();
//			int headY = game.getSanke().getSnakeHeadY();
//			LinkedList<SnakeNode> body = game.getSanke().getBody();
//			int foodY = game.getFood().getFoodX();
//			int foodX = game.getFood().getFoodY();
//			ArrayList<Point> wall = game.getMap().getPoints();
//			//TODO 将获取出来的值设到snake对象中去
//		}
		
		loadGameAudio();
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
						drawupdate();	
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

	@Override
	public void update() {
		gamePause();
		subMenu.getContinueGame().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				subRoot.getChildren().remove(subMenu);
				flag = false;
			}
		});
		
		//退出至主菜单
		subMenu.getEXIT_TO_MainMenu().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				subRoot.getChildren().remove(subMenu);
				//1.注册主菜单面板
				UIManager.getUiManager().regPanel("MainMenuPanel", new MainMenuPanel());
				
				//2.切换到主菜单面板
				AudioManager.getAudioManager().getAudio("GameAudio").close();
				UIManager.getUiManager().gotoPanel("MainMenuPanel");
			}
		});
		
		//退出至桌面
		subMenu.getEXIT_TO_WINDOWS().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Platform.exit();
			}
		});
	}

	@Override
	public void stop() {
		//将游戏面板从集合中移除
		UIManager.getUiManager().delPanel("GamePanel");
	}
	
	private void loadGameAudio() {
		AudioManager.getAudioManager().getAudio("GameAudio").init();
		AudioManager.getAudioManager().getAudio("GameAudio").getMp().volumeProperty().bind(OptionsMenuBox.slider2.valueProperty());
		AudioManager.getAudioManager().getAudio("GameAudio").play();
	}
	
	/**
	 * 
	 * MethodName: gamePause
	 * Description: TODO 暂停游戏
	 * Date: 2020-05-10 06:03:50
	 * 
	 * @author 陈思祥
	 * @return void
	 */
	public void gamePause() {
		subRoot.setFocusTraversable(true);
		subRoot.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ESCAPE && flag == false) {
					subRoot.getChildren().add(subMenu);
					flag =true;
				}else if (event.getCode() == KeyCode.ESCAPE && flag == true) {
					subRoot.getChildren().remove(subMenu);
					flag =false;
				}
			}
		});
	}
	
	/**
	 * 
	 * MethodName: gameContinue
	 * Description: TODO 继续游戏
	 * Date: 2020-05-10 06:06:42
	 * 
	 * @author 陈思祥
	 * @return void
	 */
	public void gameContinue() {
		subMenu.getContinueGame().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				subRoot.getChildren().remove(subMenu);
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
	
		public void drawupdate() {			  
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