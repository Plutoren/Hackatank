package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionListener implements Runnable
{

	/*
	 * This class, on a new thread, listens for incoming connections
	 */
	
	/* this ServerSocket will listen for incoming connections */
	private ServerSocket listener;
	/* this is the port that the listener will listen on */
	private int port;
	/* this is the delegate that will be notified about accepted connections */
	private ConnectionListenerDelegate connectionListenerDelegate;
	/* this is the thread that the ConnectionListener will run on */
	private Thread thread;
	/* indicates that the socketServer is alive and listening */
	private boolean alive;
	
	public ConnectionListener(ConnectionListenerDelegate connectionListenerDelegate, int port) throws IOException
	{
		this.connectionListenerDelegate = connectionListenerDelegate;
		this.port = port;
		this.listener = new ServerSocket(this.port);
		this.thread = new Thread(this);
	}
	
	public void startListening()
	{
		this.alive = true;
		this.thread.start();
	}
	
	public void stopListening()
	{
		try 
		{
			this.alive = false;
			this.listener.close();
			this.thread.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		Socket acceptedSocket;
		while(this.alive)
		{
			try
			{
				acceptedSocket = this.listener.accept();
				this.connectionListenerDelegate.socketAccepted(acceptedSocket);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
			
}
