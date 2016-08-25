package main;

import java.io.IOException;

import network.Client;
import network.Host;

public class Main {
	
	public static final int PORT = 7567;
	
	public static void main(String[] args) {
		
		int mode = 1;
		
		try
		{
			if (mode == 0)
			{
				Host host = new Host(PORT);
				host.startListening(); 				
			} 
			else 
			{
				Client client = new Client(PORT, "127.0.0.1", null);
				client.connect();				
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}	
}
