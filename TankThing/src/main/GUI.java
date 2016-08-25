package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GUI extends JPanel {
	    public GUI() {	
	    	
	    	JTextField textField;
	        GamePanel gamePanel = new GamePanel();

	        JPanel infoPanel = new JPanel();
	        infoPanel.setBackground(Color.BLACK);

	        JPanel ipPanel = new JPanel();
	        ipPanel.setBackground(Color.CYAN);
	        ipPanel.setPreferredSize(new Dimension(100, 50));
	        
            textField=new JTextField(8);
	       // textField.setBounds(0, 0, 580, 100);
            textField.setText("Enter IP");
	        textField.setEditable(true);
	        textField.setSize(200, 100);
	        ipPanel.add(textField);
	        
	        JPanel pointsPanel = new JPanel();
	        pointsPanel.setBackground(Color.BLUE);
	        pointsPanel.setPreferredSize(new Dimension(100, 95));

	        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
	        infoPanel.setPreferredSize(new Dimension(100, 400));
	        infoPanel.add(ipPanel);
	        infoPanel.add(pointsPanel);

	        add(gamePanel, BorderLayout.CENTER);
	        add(infoPanel, BorderLayout.EAST);
	    }
	    public static void main(String s[]) {
	    
	        JFrame frame = new JFrame("TankThing");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setContentPane(new GUI());
	        frame.pack();
	      
	        frame.setLocationRelativeTo(null);
	        
	       

	        
	        frame.setVisible(true);
	    }
	}

