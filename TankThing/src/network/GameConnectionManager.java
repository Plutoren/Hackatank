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
	
	/* this is the delegate */
	private GameConnectionManagerDelegate gameConnectionManagerDelegate;
	/* these are the connections to the players of the game */
	private Connection[] connections;
	
	public GameConnectionManager(GameConnectionManagerDelegate gameConnectionManagerDelegate, Connection[] connections) throws IOException {
		this.gameConnectionManagerDelegate = gameConnectionManagerDelegate;
		this.connections = connections;
	}
	
	public void connectClients() throws IOException
	{
		for (Connection connection : connections)
		{
			connection.listenForData(this);
			connection.sendData(NetworkCommands.STARTGAME, null);
		}
	}
	
	@Override
	public void connectionReceivedData(Connection connection, String command, String[] arguments) {
		
	}

	@Override
	public void connectionWasKilled(Connection connection) {
		
	}

}
