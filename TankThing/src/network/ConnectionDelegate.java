package network;

public interface ConnectionDelegate 
{

	/*
	 * Interface for connections
	 * Various methods with required delegate implementation
	 */

	/* called whenever the connection receives data from the other end */
	void connectionReceivedData(Connection connection, String data);

	/* called whenever the connection is closed */
	void connectionWasKilled(Connection connection);
}
