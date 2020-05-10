package team.TYGY.GluttonousSnake.DB;

import java.awt.Component;

import javax.swing.JFrame;

public class Mapsnake {

	public static void main(String[] args) {
		JFrame frame =new JFrame();
		frame.setBounds(10, 10, 1280, 720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new MPanel());
	
		frame.setVisible(true);
		

	}

}
