package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	public GamePanel(){
    this.setBackground(Color.GRAY);
    this.setPreferredSize(new Dimension(300, 400));
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.white);
		TxtToMap ttm = null;
		try {
			ttm = new TxtToMap("D:\\testmap.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Obstacle temp = null;
		while(!ttm.obs.isEmpty()){
			temp = ttm.obs.pop();
			if(temp.getType().equals("rect")){
				g.fillRect(temp.getX(), temp.getY(), temp.getW(), temp.getH());
			}
			if(temp.getType().equals("oval")){
				g.fillOval(temp.getX(), temp.getY(), temp.getW(), temp.getH());
			}
		}
	}
}
