package main;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Movement extends Rectangle{

    private double dx;
    private double dy;
    private double x;
    private double y;
    private double rightMax = 555;
    private double leftMax = -2;
    private double topMax = -2;
    private double bottomMax = 427;
    private int imageWidth = 41;
    private int imageHeight = 47;
    private Image image;

    public Movement() {
        
        initCraft();
    }
    
    private void initCraft() {
        ImageIcon ii = new ImageIcon("tankSpriteGreen.png");
        image = ii.getImage();
        x = 480;
        y = 50;    
    }
 
    public void move() {
    	this.setBounds((int)x, (int)y, imageWidth, imageHeight);
    	if (x < rightMax && x > leftMax) {
    		  x += dx;
    		}
    	else if (x >= rightMax) {
    		x = rightMax - 1;
    		}
    	else {
    	x = leftMax + 1;
    		}
    	if (y > topMax && y < bottomMax) {
    			y += dy;
    		}
    	else if (y >= bottomMax) {
    		y = bottomMax - 1;
    		}
    	else {
  		y = topMax + 1;
    	}
    	
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void keyPressed(KeyEvent e) {
    	
        int key = e.getKeyCode();

        
        if (key == KeyEvent.VK_SPACE) {
            //fire!
        }
        
        if (key == KeyEvent.VK_LEFT) {
        	for(Obstacle obs : Main.obs){
        		if(this.intersects(obs)){
        			x += 2;
        		}
        		else{
        		 	dx = -1;
        		}
        	}
        }

        if (key == KeyEvent.VK_RIGHT) {
        	for(Obstacle obs : Main.obs){
        		if(this.intersects(obs)){
        			dx = -2;
        		}
        		else{
        		 	dx = 1;
        		}
        	}
        }

        if (key == KeyEvent.VK_UP) {
        	for(Obstacle obs : Main.obs){
        		if(this.intersects(obs)){
        			dy = 2;
        		}
        		else{
        		 	dy = -1;
        		}
        	}
        }
        if (key == KeyEvent.VK_DOWN) {
        	for(Obstacle obs : Main.obs){
        		if(this.intersects(obs)){
        			dy = -2;
        		}
        		else{
        		 	dy = 1;
        		}
        	}
        }
    }

    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
