package team.TYGY.GluttonousSnake.DB;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;


public class MPanel extends JPanel {
	ImageIcon title=new ImageIcon("content/images/title.jpg");
	
	public MPanel()
	{
		
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Color.white);
		title.paintIcon(this, g, 25, 11);
		
		g.fillRect(25, 75, 1230, 600);
		
	}

}
