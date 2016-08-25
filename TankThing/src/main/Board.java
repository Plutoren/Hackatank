package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

import network.Client;


@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {

	private final int DELAY = 1;

    private Timer timer;
    private Movement[] tanks;
    private Movement localTank;
    private Client gameClient;
    private int localIndex;
    
    public Board()
    {
    	this(1, 0, null);
    }
    
    public Board(int tankCount, int localIndex, Client gameClient) {
    	this.localIndex = localIndex;
    	this.gameClient = gameClient;
    	this.tanks = new Movement[tankCount];
    	for (int i = 0; i < tanks.length; i++)
		{
			this.tanks[i] = new Movement();
		}
    	this.localTank = this.tanks[localIndex];
    	initBoard();
    }
    
    public void initBoard(int tankCount, int localIndex, Client gameClient)
    {
    	System.out.println("tankCount = " + tankCount + " : localIndex = " + localIndex);
    	this.localIndex = localIndex;
    	this.gameClient = gameClient;
    	this.tanks = new Movement[tankCount];
    	for (int i = 0; i < tanks.length; i++)
		{
			this.tanks[i] = new Movement();
		}
    	this.localTank = this.tanks[localIndex];    	
    }
    
    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);

        timer = new Timer(DELAY, this);
        timer.start();      
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
        //super.paintComponent(g);
		g.setColor(Color.gray);
		TxtToMap ttm = null;
		try {
			ttm = new TxtToMap("testmap.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Obstacle temp = null;
		while(!ttm.obs.isEmpty()){
			temp = ttm.obs.pop();
			Main.obs.add(temp);
			if(temp.getType().equals("rect")){
				g.fillRect((int)temp.getX(), (int)temp.getY(), temp.getW(), temp.getH());
			}
			if(temp.getType().equals("oval")){
				g.fillOval((int)temp.getX(), (int)temp.getY(), temp.getW(), temp.getH());
			}
		}
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (Movement tank : tanks)
		{
            g2d.drawImage(tank.getImage(), (int)tank.getX(), (int)tank.getY(), this);        			
		}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.localTank.move();
        if (this.gameClient != null)
        {
        	try
			{
				this.gameClient.sendTank(this.localTank, this.localIndex);
			} catch (IOException x)
			{
				x.printStackTrace();
			}
        }
        repaint();  
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            localTank.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            localTank.keyPressed(e);
        }
    }
    
    public void tankMoved(String data, int index)
    {
    	System.out.println("Tank Moved: " + data);
		this.tanks[index].readString(data);	
    }
	
}
