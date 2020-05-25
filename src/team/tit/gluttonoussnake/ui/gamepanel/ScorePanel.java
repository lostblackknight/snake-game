package team.tit.gluttonoussnake.ui.gamepanel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import team.tit.gluttonoussnake.util.URLUtils;

/**
 * 得分面板
 * @author 陈思祥
 * @author 何宇昌
 * @author 郭佳强
 * @author 郭晨
 * @version 1.4
 * @since JDK1.8 2020年5月23日
 */
public class ScorePanel extends GridPane{
	
	AnchorPane subRoot;
	Label length;
	Label score;
	Label length_value;
	Label score_value;
	String length_relValue = "3";
	String score_relValue = "0";
	
	public ScorePanel(AnchorPane subRoot) {
		this.subRoot = subRoot;
		initScorePanel();
	}
	
	@SuppressWarnings("static-access")
	private void initScorePanel() {
		length = new Label("长度:");
		score = new Label("分数:");
		length_value = new Label();
		score_value = new Label();
		length.setId("length");
		score.setId("score");
		length_value.setId("length_value");
		score_value.setId("score_value");
		length_value.setText(getLength_relValue());
		score_value.setText(getScore_relValue());
		this.setVgap(4);
		this.setHgap(10);
		this.setAlignment(Pos.CENTER_LEFT);
		this.setMargin(length, new Insets(0, 0, 0, 14));
		this.setMargin(score, new Insets(0, 0, 0, 14));
		this.setId("ScorePanel");
		this.setPrefSize(200, 100);
		subRoot.setTopAnchor(this, 16.0);
		subRoot.setRightAnchor(this, 16.0);
		this.getStylesheets().add(URLUtils.getURLString("css/GluttonousSnake.css"));
	}
	
	public void addScorePanel() {
		this.add(length, 0, 0);
		this.add(score, 0, 1);
		this.add(length_value, 1, 0);
		this.add(score_value, 1, 1);
		subRoot.getChildren().add(this);
	}
	
	public String getLength_relValue() {
		return length_relValue;
	}

	public void setLength_relValue(String length_relValue) {
		this.length_relValue = length_relValue;
	}

	public String getScore_relValue() {
		return score_relValue;
	}

	public void setScore_relValue(String score_relValue) {
		this.score_relValue = score_relValue;
	}
}
