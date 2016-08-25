package main;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Movement extends Rectangle
{

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
	private Image image;

	public Movement()
	{
		initCraft();
	}

	private void initCraft()
	{
		ImageIcon ii = new ImageIcon("tankSpriteGreen.png");
		image = ii.getImage();
		x = 480;
		y = 50;
	}

	/* synchronized to avoid clusterfucks */

	public void move()
	{
		synchronized (this)
		{
			this.setBounds((int) x, (int) y, imageWidth, imageHeight);
			if (x < rightMax && x > leftMax)
			{
				x += dx;
			} else if (x >= rightMax)
			{
				x = rightMax - 1;
			} else
			{
				x = leftMax + 1;
			}
			if (y > topMax && y < bottomMax)
			{
				y += dy;
			} else if (y >= bottomMax)
			{
				y = bottomMax - 1;
			} else
			{
				y = topMax + 1;
			}
		}
	}

	public void setX(double x)
	{
		synchronized (this)
		{
			this.x = x;
		}
	}

	public void setY(double y)
	{
		synchronized (this)
		{
			this.y = y;
		}
	}

	public double getX()
	{
		double x;
		synchronized (this)
		{
			x = this.x;
		}
		return x;
	}

	public double getY()
	{
		double y;
		synchronized (this)
		{
			y = this.y;
		}
		return y;
	}

	public Image getImage()
	{
		return image;
	}

	public void keyPressed(KeyEvent e)
	{

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_SPACE)
		{
			// fire!
		}

		if (key == KeyEvent.VK_LEFT)
		{
			for (Obstacle obs : Main.obs)
			{
				if (this.intersects(obs))
				{
					dx = 0.4;
				} else
				{
					dx = -0.2;
				}
			}
		}

		if (key == KeyEvent.VK_RIGHT)
		{
			for (Obstacle obs : Main.obs)
			{
				if (this.intersects(obs))
				{
					dx = -0.4;
				} else
				{
					dx = 0.2;
				}
			}
		}

		if (key == KeyEvent.VK_UP)
		{
			for (Obstacle obs : Main.obs)
			{
				if (this.intersects(obs))
				{
					dy = 0.4;
				} else
				{
					dy = -0.2;
				}
			}
		}
		if (key == KeyEvent.VK_DOWN)
		{
			for (Obstacle obs : Main.obs)
			{
				if (this.intersects(obs))
				{
					dy = -0.4;
				} else
				{
					dy = 0.2;
				}
			}
		}
	}

	public void keyReleased(KeyEvent e)
	{

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT)
		{
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT)
		{
			dx = 0;
		}

		if (key == KeyEvent.VK_UP)
		{
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN)
		{
			dy = 0;
		}
	}

	public String toString()
	{
		return this.x + "&" + this.y;
	}

	public void readString(String values)
	{
		String[] attributes = values.split("&");
		int x = Integer.parseInt(attributes[0].replace(".0", ""));
		int y = Integer.parseInt(attributes[1].replace(".0", ""));
		this.setX(x);
		this.setY(y);
	}

}
