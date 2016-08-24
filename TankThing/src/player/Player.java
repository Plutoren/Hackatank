package player;

import network.Connection;
import network.ConnectionDelegate;

public abstract class Player implements ConnectionDelegate
{
	
	/* 
	 * this class is an abstract class describing a single player
	 * which can be extended into more specific roles (host and client)
	 */

	/* an array of connections from this player to every other player */
	private Connection[] connections;
	/* some list of game objects that will be updated */

	/* set the connections */
	public void setConnections(Connection[] connections) 
	{
		this.connections = connections;
	}

	/* connectionDelegate methods */
	@Override
	public void connectionReceivedData(Connection connection, String data) 
	{
		// overriden
	}

	@Override
	public void connectionWasKilled(Connection connection) 
	{
		// remove from game objects or something
	}
}
