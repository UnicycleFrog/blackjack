package blackjack;

public class Player {
	
	private String myName;
	
	public Player()
	{
		setName("");
	}
	
	public Player(String name)
	{
		setName(name);
	}
	
	public void setName(String name)
	{
		myName = name;
	}
	
	public String getName()
	{
		return myName;
	}
	
	public String toString()
	{
		return getName();
	}
}
