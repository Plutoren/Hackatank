package network;

import java.net.Socket;

public interface ConnectionListenerDelegate {

	/* 
	 * this is an interface for delegates of the ConnectionListener class.
	 * It specifies a series of methods that any delegate must implement
	 */
	
	/*
	 * This function is called whenever an incoming connection is accepted
	 */
	public void socketAccepted(Socket socket);
	
}
