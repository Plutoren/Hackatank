package network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import main.Movement;

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
	
	public void sendTank(Movement tank, int index) throws IOException
	{
		this.connection.sendData(NetworkCommands.UPDATEGAME, new String[] {Integer.toString(index), tank.toString()});
	}
	
	/* ConnectionDelegate methods */
	@Override
	public void connectionReceivedData(Connection connection, String command, String[] arguments)
	{
		switch (command)
		{
		case NetworkCommands.UPDATEGAME:
			this.clientDelegate.tankMoved(arguments[1], Integer.parseInt(arguments[0]));
			break;
		case NetworkCommands.STARTGAME:
			this.clientDelegate.gameStarted(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1]));
			break;
		default:
			break;
		}

		/* commands are formatted like this: command$argument#argument#argument */
	}

	@Override
	public void connectionWasKilled(Connection connection)
	{
		
	}
	
}
