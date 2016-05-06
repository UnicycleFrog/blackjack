package blackjack;

public interface DeckInterface {
	public int size();
	public void shuffle();
	public Card deal();
	public String toString();
}
