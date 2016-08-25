package main;

import java.awt.Rectangle;

public class Obstacle extends Rectangle{
	public int x, y, w, h;
	public String type = "";
	public Obstacle(String type2, int x2, int y2, int w2, int h2){
		type = type2;
		x = x2;
		y = y2;
		w = w2;
		h = h2;
		this.setBounds(x2, y2, w2, h2);
	}
	public String getType(){
		return type;
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public int getW(){
		return w;
	}
	public int getH(){
		return h;
	}
}
