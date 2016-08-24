package main;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;     

public class Main {
	
	public static void main(String[] args) {
		
		int i = 5;
		System.out.println("Hello World: " + i );
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
		        @Override
		        public void run() {
		        createAndShowGUI();
		    }
		});
		
	}
	
		 private static void createAndShowGUI() {
			 
		        JFrame frame = new JFrame("TankThing");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        JLabel emptyLabel = new JLabel();
		        frame.add(new JLabel(new ImageIcon("/TankThing/tankSpriteGreen.png")));

		        emptyLabel.setPreferredSize(new Dimension(700, 400));
		       
		        frame.getContentPane().add(emptyLabel, BorderLayout.PAGE_START);
		        frame.pack();
		        frame.setVisible(true);
		    }

		
	
	
	
}
