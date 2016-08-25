package network;

import java.io.IOException;

public class GameConnectionManager implements ConnectionDelegate
{

	/* this class handles all network data coming in a correspondingly notifies the players
	 * about moved assets, such as tanks or bullets
	 * 
	 * (non-Javadoc)
	 * @see network.ConnectionDelegate#connectionReceivedData(network.Connection, java.lang.String)
	 */
	
	/* these are the connections to the players of the game */
	private Connection[] connections;
	
	public GameConnectionManager(Connection[] connections) throws IOException {
		this.connections = connections;
	}
	
	public void connectClients() throws IOException
	{
		for (int i = 0; i < connections.length; i++)
		{
			connections[i].listenForData(this);
			connections[i].sendData(NetworkCommands.STARTGAME, new String[] {
				Integer.toString(connections.length), 
				Integer.toString(i)
			});
		}
	}
	
	@Override
	public void connectionReceivedData(Connection connection, String command, String[] arguments) {
		for (int i = 0; i < connections.length; i++)
		{
			if (connections[i] != connection)
			{			
				try
				{
					connections[i].sendData(command, arguments);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void connectionWasKilled(Connection connection) {
		
	}

}
