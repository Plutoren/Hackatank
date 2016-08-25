package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Connection implements Runnable 
{
	
	/* 
	 * this class describes a network connection between different players.
	 * How this connection is handled is up to whichever player owns it
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	
	/* the connection runs on a seperate thread */
	private Thread thread;
	/* the socket on which the connection communicates */
	private Socket socket;
	/* objects for reading and writing from/to the socket.*/
	private PrintWriter writer;
	private BufferedReader reader;
	/* the connection's delegate object */
	private ConnectionDelegate connectionDelegate;
	/* flags that the connection is alive and healthy */
	boolean alive;

	
	/*
	 *  constructors.  connectionDelegate is the delegate and the socket must
	 * be fully configured before being passed in
	 */
	public Connection(Socket socket) throws IOException
	{
		this.socket = socket;

		this.writer = new PrintWriter(this.socket.getOutputStream(), true);
		this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

		this.thread = new Thread(this);
	}
	/*
	 * this constructor parses a string to create the socket from scratch
	 */
	public Connection(String address) throws IOException
	{
		String hostname = "localhost";
		int port = 80;
		
		/* make sure there is a colon (address:port) */
		if (address.contains(":"))
		{
			/* basic parsing of the address */
			String[] components = address.split(":");
			hostname = components[0];
			port = Integer.parseInt(components[1]);
		} else {
			System.out.println("Error parsing address.  Connecting to default location");
		}
		
		this.socket = new Socket(hostname, port);
		
		/* open up readers and writers */
		this.writer = new PrintWriter(this.socket.getOutputStream(), true);
		this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

		/* allocate the thread */
		this.thread = new Thread(this);
	}
	
	/* 
	 * called by the connectionDelegate, the connection will start listening
	 * for incoming messages after this
	 */
	public void listenForData(ConnectionDelegate connectionDelegate)
	{
		this.alive = true;
		this.connectionDelegate = connectionDelegate;
		this.thread.start();
	}
	
	/* called by the connectionDelegate to send data to the other end of the connection */
	public void sendData(String data) throws IOException
	{
		this.writer.println(data);
		System.out.println("Sent Data");
	}
	
	/* this function sends data in the proper format */
	public void sendData(String command, String[] arguments) throws IOException
	{
		String data = command;
		if (arguments != null && arguments.length > 0)
		{
			data += "#";
			for (String argument : arguments)
			{
				data += argument + ",";
			}
		}

		this.sendData(data);
	}

	/* called whenever the connection is killed, 
	 * or by the connectionDelegate to kill the connection 
	 */
	public void killConnection()
	{
		try
		{
			this.socket.close();	
			this.connectionDelegate.connectionWasKilled(this);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/* 
	 * these next two methods convert the socket's address
	 * and port into a string in the following format
	 * (address:port)
	 */
	public static String SocketToString(Socket socket)
	{
		InetAddress clientAddress = socket.getInetAddress();
		
		String connectionAddress = clientAddress.getHostName();
		int connectionPort = socket.getPort();
		return connectionAddress + ":" + connectionPort;		
	}
	
	public String toString()
	{
		return Connection.SocketToString(this.socket);
	}
	
	/* Runnable methods */
	@Override
	public void run() 
	{
		String receivedData;
		while (this.alive)
		{
			try 
			{
				receivedData = this.reader.readLine();
				System.out.println("received data: " + receivedData);
				String[] components = new String[] {receivedData};
				String command;
				String[] arguments = components;
				
				if (receivedData.contains("#"))
				{
					components = receivedData.split("#");

					arguments = new String[] {components[1]};
					if (components[1].contains(","))
					{
						arguments = components[1].split(",");					
					}
				}
				
				command = components[0];
				
				System.out.println("command:"+command+" arguments:"+arguments[0]);

				this.connectionDelegate.connectionReceivedData(this, command, arguments);				
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
				this.killConnection();
			}
		}
	}
}
