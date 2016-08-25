package network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client implements ConnectionDelegate
{

	/* 
	 * this is a client class that connects to a remote host 
	 */
	
	/* this is the connection that it will attempt to create */
	private Connection connection;
	/* 
	 * this is the game delegate that will received networked
	 * game updates
	 */ 
	private ClientDelegate clientDelegate;
	
	public Client(int port, String ip, ClientDelegate clientDelegate) throws UnknownHostException, IOException
	{
		this.clientDelegate = clientDelegate;
		Socket socket = new Socket(ip, port);
		this.connection = new Connection(socket);
	}
	
	public void connect()
	{
		this.connection.listenForData(this);
	}
	
	public void notifyGameStateChange()
	{
		/* this isn't really defined right now but it will be */
	}
	
	/* ConnectionDelegate methods */
	@Override
	public void connectionReceivedData(Connection connection, String command, String[] arguments)
	{
		System.out.println("received command" + command);
		/* commands are formatted like this: command$argument#argument#argument */
	}

	@Override
	public void connectionWasKilled(Connection connection)
	{
		
	}
	
}
