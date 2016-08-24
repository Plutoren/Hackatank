package main;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Movement {

    private int dx;
    private int dy;
    private int x;
    private int y;
    private int rightMax = 555;
    private int leftMax = -2;
    private int topMax = -2;
    private int bottomMax = 427;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        
        if (key == KeyEvent.VK_SPACE) {
            
        }
        
        if (key == KeyEvent.VK_LEFT) {
        	
        	   dx = -1;
        	
        	
        	 
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
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
