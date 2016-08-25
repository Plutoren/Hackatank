package network;

public interface NetworkCommands
{
	
	/* a bunch of static final strings that correspond to strings sent over the network */
	
	/* signifies that the game has started arguments are [total amount of tanks] and [local tank] */
	public static final String STARTGAME = "STRT";
	
	/* sort of not super clear about what this will be but whatever */
	public static final String UPDATEGAME = "UPDT";
	
}
