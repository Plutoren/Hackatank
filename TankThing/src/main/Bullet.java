package main;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bullet extends Movement{
	private int dx, dy;
	private int x, y;
	Image i = null;
	public Bullet(int direction, int originX, int originY){
		ImageIcon ii = new ImageIcon("bullet.jpg");
		i = ii.getImage();
		x = originX;
		y = originY;
		if(direction == 0){
			dx = 0;
			dy = -2;
		}
		if(direction == 1){
			dx = 2;
			dy = -2;
		}
		if(direction == 2){
			dx = 2;
			dy = 0;
		}
		if(direction == 3){
			dx = 2;
			dy = 2;
		}
		if(direction == 4){
			dx = 0;
			dy = 2;
		}
		if(direction == 5){
			dx = -2;
			dy = 2;
		}
		if(direction == 6){
			dx = -2;
			dy = 0;
		}
		if(direction == 7){
			dx = -2;
			dy = -2;
		}
	}
	public void move(){
		x += dx;
		y += dy;
		for(Obstacle obs : Main.obs){
    		if(this.intersects(obs)){
    			Board.bullets.remove(this);
    		}
		}
	}
	public Image getImage(){
		return i;
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	
	
}
