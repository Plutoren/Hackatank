package main;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import network.Client;
import network.ClientDelegate;
import network.Host;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener, ClientDelegate {

	public final int PORT = 4276;

	Board board;
	Host host;
	Client client;
    JTextField ipAddressField;

    public Main() {
        initUI();
    }
    public static ArrayList<Obstacle> obs = new ArrayList<Obstacle>();
    private void initUI() {
    	
    	this.board = new Board();
    	add(board);
		
		setSize(700, 500);
		setResizable(false);
		setFocusable(false);
		
		setTitle("TankThing");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		       
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(Color.BLACK);
		
		JPanel ipPanel = new JPanel();
		ipPanel.setBackground(Color.CYAN);
		ipPanel.setPreferredSize(new Dimension(100, 50));
		
		ipAddressField=new JTextField(8);
		// textField.setBounds(0, 0, 580, 100);
		ipAddressField.enableInputMethods(false);
		
		String IP = ipAddressField.getText();
		IP.length();//place holder
		
		ipAddressField.setText("Enter IP"); 
		JButton connectButton = new JButton("Connect");
		connectButton.addActionListener(this);
		connectButton.setActionCommand("Connect");
		connectButton.setEnabled(true);
		connectButton.setFocusable(false);
		
		
		ipAddressField.setEditable(true);
		ipAddressField.setSize(200, 100);
		ipPanel.add(ipAddressField);
		ipPanel.add(connectButton);
		JPanel pointsPanel = new JPanel();
		pointsPanel.setBackground(Color.BLUE);
		
		JButton hostButton = new JButton("Host");
		hostButton.addActionListener(this);
		hostButton.setActionCommand("Host");
		hostButton.setEnabled(true);
		hostButton.setFocusable(false);
		
		JButton startGameButton = new JButton("Start");
		startGameButton.addActionListener(this);
		startGameButton.setActionCommand("Start");
		startGameButton.setEnabled(true);
		startGameButton.setFocusable(false);
		
		pointsPanel.setPreferredSize(new Dimension(100, 95));
		pointsPanel.add(hostButton);        
		pointsPanel.add(startGameButton);
		
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.setPreferredSize(new Dimension(100, 400));
		infoPanel.add(ipPanel);
		infoPanel.add(pointsPanel);
		
		// add(gamePanel, BorderLayout.WEST);
		add(infoPanel, BorderLayout.EAST);
    }

    public void joinGame(int port, String ip) throws UnknownHostException, IOException
    {
    	this.client = new Client(port, ip, this);
    	this.client.connect();
    }
    
	@Override
	public void actionPerformed(ActionEvent e)
	{		
		switch (e.getActionCommand())
		{
		case "Host":
			try
			{
				System.out.println("Hosting");
				this.host = new Host(PORT);
				this.host.startListening();
				this.joinGame(PORT, "localhost");
			}
			catch (IOException x)
			{
				x.printStackTrace();
			}
			break;
		case "Connect":
			try
			{
				this.joinGame(PORT, this.ipAddressField.getText());
			}
			catch (IOException x)
			{
				x.printStackTrace();
			}
			break;
		case "Start":
			try
			{
				System.out.println("Starting Game");
				this.host.startGame();
			} 
			catch (IOException x)
			{
				x.printStackTrace();
			}
			System.out.println("Starting");
			break;
		default:
			break;
		}
	}
	
    /* game client starting up */
    
	@Override
	public void tankMoved(String data, int index)
	{
		this.board.tankMoved(data, index);
	}
	
	@Override
	public void gameStarted(int tankTotal, int localTank)
	{
		this.board.initBoard(tankTotal, localTank, this.client);
	}
	
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
