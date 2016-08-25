package main;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Movement extends Rectangle{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
    private boolean left, right, up, down;
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
 

    /* synchronized to avoid clusterfucks */

    public void move() {
    	synchronized (this)
		{
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
    }
    
    public void setX(int x){
    	synchronized (this)
		{
        	this.x = x;			
		}
    }
    
    public void setY(int y){
    	synchronized (this)
		{
    		this.y = y;
		}
    }

    public double getX() {
    	double x;
    	synchronized (this)
		{
			x = this.x;
		}    	
    	return x;
    }

    public double getY() {
    	double y;
    	synchronized (this)
		{
    		y = this.y;
		}
    	return y;
    }

    public Image getImage() {
        return image;
    }

    public void keyPressed(KeyEvent e) {
    	
        int key = e.getKeyCode();

        
        if (key == KeyEvent.VK_SPACE) {
        	if(left&&up){
            	Board.bullets.add(new Bullet(7, (int)x, (int)y)); 
        	}
        	else if(up&&right){
             	Board.bullets.add(new Bullet(1, (int)x, (int)y)); 
            }
        	else if(right&&down){
             	Board.bullets.add(new Bullet(3, (int)x, (int)y));
            }
        	else if(down&&left){
              	Board.bullets.add(new Bullet(5, (int)x, (int)y)); 
            }
        	else if(up){
            	Board.bullets.add(new Bullet(0, (int)x, (int)y)); 
        	}
            else if(right){
            	Board.bullets.add(new Bullet(2, (int)x, (int)y)); 
            }
            else if(down){
            	Board.bullets.add(new Bullet(4, (int)x, (int)y)); 
            }           
            else if(left){
            	Board.bullets.add(new Bullet(6, (int)x, (int)y));
        	}        	
        }
        
        if (key == KeyEvent.VK_LEFT) {
        	left = true;
        	for(Obstacle obs : Main.obs){
        		if(this.intersects(obs)){
        			dx = 1;
        		}
          	else{
        		 	dx = -1;
        		}
        	}
        }

        if (key == KeyEvent.VK_RIGHT) {
        	right = true;
        	for(Obstacle obs : Main.obs){
        		if(this.intersects(obs)){
        			dx = -1;
        		}
        		else{
        		 	dx = 1;
        		}
        	}
        }

        if (key == KeyEvent.VK_UP) {
        	up = true;
        	for(Obstacle obs : Main.obs){
        		if(this.intersects(obs)){
        			dy = 1;
        		}
        		else{
        		 	dy = -1;
        		}
        	}
        }
        if (key == KeyEvent.VK_DOWN) {
        	down = true;
        	for(Obstacle obs : Main.obs){
        		if(this.intersects(obs)){
        			dy = -1;
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
        	left = false;
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
        	right = false;
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            up = false;
        	dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            down = false;
        	dy = 0;
        }
    }
    
    public String toString()
    {
    	return this.x + "," + this.y;
    }

    public void readString(String values)
    {
    	String[] attributes = values.split(",");
    	this.setX(Integer.parseInt(attributes[1]));
    	this.setY(Integer.parseInt(attributes[2]));
    }
    
}
