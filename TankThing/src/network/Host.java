package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import player.Player;

public class Host extends Player implements ConnectionListenerDelegate
{

	/* 
	 * this class describes a host, a particular type of player that
	 * can create matches, and that other machines can connect to
	 */
	
	/* 
	 * this is an arraylist that holds incoming connections until the maximum amount
	 * of players is known (after everyone has joined).
	 * After this, they are stored in a static-sized array (faster)
	 */
	private ArrayList<Connection> connectionBuffer;
	/* this connectionListener object will accept sockets */
	ConnectionListener connectionListener;
	
	public Host(int port)
	{
		try
		{
			this.connectionBuffer = new ArrayList<Connection>();
			this.connectionListener = new ConnectionListener(this, port);
			this.connectionListener.startListening();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	/* ConnectionListenerDelegate methods */
	@Override
	public void socketAccepted(Socket socket) {
		try 
		{
			Connection newConnection = new Connection(this, socket);
			this.connectionBuffer.add(newConnection);
			System.out.println("Accepted some connection");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
}
