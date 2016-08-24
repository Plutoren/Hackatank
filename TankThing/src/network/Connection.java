package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection implements Runnable 
{

	/* this class describes a network connection between different players.
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
	/* flags that the connection is alive and healthy */
	boolean alive;
	/* the connection's delegate object */
	private ConnectionDelegate connectionDelegate;
	
	/* constructor.  connectionDelegate is the delegate and the socket must
	 * be fully configured before being passed in
	 */
	public Connection(ConnectionDelegate connectionDelegate, Socket socket) throws IOException
	{
		this.socket = socket;
		this.connectionDelegate = connectionDelegate;

		this.writer = new PrintWriter(this.socket.getOutputStream(), true);
		this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

		this.thread = new Thread(this);
	}
	
	/* called by the connectionDelegate, the connection will start listening
	 * for incoming messages after this
	 */
	public void listenForData()
	{
		this.thread.start();
	}
	
	/* called by the connectionDelegate to send data to the other end of the connection */
	public void sendData(String data) throws IOException
	{
		this.writer.println(data);
		System.out.println("Sent Data");
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
				this.connectionDelegate.connectionReceivedData(this, receivedData);
				System.out.println("Received Data");
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
				this.killConnection();
			}
		}
	}
}
