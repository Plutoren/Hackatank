package main;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Main extends JFrame {

    public Main() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
        
        setSize(700, 500);
        setResizable(false);
        setFocusable(false);
        
        setTitle("TankThing");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JTextField textField;
      //  JPanel gamePanel = new JPanel();
      //  gamePanel.setBackground(Color.GRAY);
      //  gamePanel.setPreferredSize(new Dimension(300, 400));

     
       
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.BLACK);

        JPanel ipPanel = new JPanel();
        ipPanel.setBackground(Color.CYAN);
        ipPanel.setPreferredSize(new Dimension(100, 50));
        
        textField=new JTextField(8);
       // textField.setBounds(0, 0, 580, 100);
        textField.enableInputMethods(false);
        
        String IP = textField.getText();
        IP.length();//place holder
        
        textField.setText("Enter IP"); 
        JButton connectButton = new JButton("Connect");
       
      
        textField.setEditable(true);
        textField.setSize(200, 100);
        ipPanel.add(textField);
         ipPanel.add(connectButton);
        JPanel pointsPanel = new JPanel();
        pointsPanel.setBackground(Color.BLUE);
        JButton hostButton = new JButton("Host");
        pointsPanel.setPreferredSize(new Dimension(100, 95));
        pointsPanel.add(hostButton);
        
        
        hostButton.setActionCommand(getName());
        
        
        
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setPreferredSize(new Dimension(100, 400));
        infoPanel.add(ipPanel);
        infoPanel.add(pointsPanel);

       // add(gamePanel, BorderLayout.WEST);
        add(infoPanel, BorderLayout.EAST);
        
        
        
        
        
        
    }
    
    
//    private void startGame(){
//        add(new Board());
//        
//        setSize(700, 500);
//        setResizable(false);
//        setFocusable(false);
//        
//        setTitle("TankThing");
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                Main ex = new Main();
                ex.setVisible(true);
            }
        });
    }
}
