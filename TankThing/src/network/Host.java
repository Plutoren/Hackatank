package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Host implements Runnable
{

	/* 
	 * this class describes a host that other machines can connect to.
	 * it listens on a seperate thread for incoming connections
	 */
	
	/* this ServerSocket will listen for incoming connections */
	private ServerSocket listener;
	/* this is the port that the listener will listen on */
	private int port;
	/* this is the thread that the ConnectionListener will run on */
	private Thread thread;
	/* 
	 * this is an arraylist that holds incoming connections until the maximum amount
	 * of players is known (after everyone has joined).
	 * After this, they are stored in a static-sized array (faster)
	 */
	private ArrayList<Connection> connectionBuffer;
	/* this player will later be used in the game.  Now, it is allocated and used as the connection delegate */
	private GameConnectionManager gameConnectionManager;
	/* flags that the host is currently listening for connections */
	private boolean alive;
	/* if this array contains values, only those addresses will be allowed to connect */
	
	public Host(int port) throws IOException
	{
		this.connectionBuffer = new ArrayList<Connection>();
		
		this.port = port;
		this.listener = new ServerSocket(this.port);
		this.thread = new Thread(this);
	}
	
	/*
	 * starts the thread and initializes related data
	 */
	public void startListening()
	{
		this.alive = true;
		this.thread.start();
	}
	
	/*
	 * closes the connection listener and halts the running thread
	 */
	public void stopListening()
	{
		try 
		{
			this.alive = false;
			this.listener.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void startGame() throws IOException
	{
		this.stopListening();

		int connectionCount = this.connectionBuffer.size();
		Connection[] connections = new Connection[connectionCount];

		System.out.println("there are " + connectionCount + " connections");
		
		for (int i = 0; i < connectionCount; i++)
		{
			System.out.println("Setting connection " + i);
			connections[i] = this.connectionBuffer.get(i);
		}
		
		/* incomplete.  Start game */
		this.gameConnectionManager = new GameConnectionManager(connections);
		this.gameConnectionManager.connectClients();
	}
	
	/*
	 * accepts one connnection
	 */
	public Connection acceptConnection() throws IOException
	{
		Socket acceptedSocket = this.listener.accept();
		System.out.println("Accepted new connection");
		return new Connection(acceptedSocket);
	}
	
	/* continuously accepts incoming connections while running  */
	@Override
	public void run()
	{
		System.out.println("Listening for incoming connections");
		Connection acceptedConnection;
		while(this.alive)
		{
			try
			{
				acceptedConnection = this.acceptConnection();
				this.connectionBuffer.add(acceptedConnection);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("Stopped Listening");
	}

}
