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


@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Movement Craft;
    private final int DELAY = 1;

    public Board() {

        initBoard();
    }
    
    private void initBoard() {
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);
//
        Craft = new Movement();

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
			// TODO Auto-generated catch block
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
        g2d.drawImage(Craft.getImage(), (int)Craft.getX(), (int)Craft.getY(), this);        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        Craft.move();
        repaint();  
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            Craft.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            Craft.keyPressed(e);
        }
    }
}
