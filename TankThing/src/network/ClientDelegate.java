package network;

public interface ClientDelegate
{
	
	/*
	 * this interface provides methods to the players to notify them
	 * of moved tanks, etc
	 */
	
	/* notifies that the tank at the given index moved */
	public void tankMoved(String data, int index);	
	
	public void gameStarted(int tankTotal, int localTank);
}
