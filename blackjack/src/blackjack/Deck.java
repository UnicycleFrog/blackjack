package blackjack;
import java.util.List;
import java.util.ArrayList;

/**
 * The Deck class represents a shuffled deck of cards.
 * It provides several operations including
 *      initialize, shuffle, deal, and check if empty.
 */
public class Deck implements DeckInterface {

	/**
	 * cards contains all the cards in the deck.
	 */
	private List<Card> cards = new ArrayList<Card>();
	
	//Unit 9 - Array version of the Deck
	//private Card[] cards;

	/**
	 * size is the number of not-yet-dealt cards.
	 * Cards are dealt from the top (highest index) down.
	 * The next card to be dealt is at size - 1.
	 */
	private int size;


	/**
	 * Creates a new <code>Deck</code> instance.<BR>
	 * It pairs each element of ranks with each element of suits,
	 * and produces one of the corresponding card.
	 * @param ranks is an array containing all of the card ranks.
	 * @param suits is an array containing all of the card suits.
	 * @param values is an array containing all of the card point values.
	 */
	public Deck(String[] ranks, String[] suits, int[] values) {
		for (int i = 0; i < suits.length; i++)
		{
			for (int j = 0; j < ranks.length; j++)
			{
				cards.add(new Card(ranks[j], suits[i], values[j]));
				size++;
			}
		}
		/*Array Version
		size = suits.length*ranks.length;
		cards = new Card[size];
		int s = 0;
			for (int i = 0; i < suits.length; i++)
			{
				for (int j = 0; j < ranks.length; j++)
				{
					cards[s] = new Card(ranks[j], suits[i], values[j]);
					s++;
				}
			}*/
		shuffle();
	}

	/**
	 * Accesses the number of undealt cards in this deck.
	 * @return the number of undealt cards in this deck.
	 */
	public int size() {
		return size;
	}

	/**
	 * Randomly permute the given collection of cards
	 * and reset the size to represent the entire deck.
	 */
	public void shuffle() {
		int rand;
		Card tmp;
		/* Array version		
 		for (int i = cards.length -1; i >=0; i--)
		{
			rand = (int) (Math.floor(Math.random()*(cards.length-1)));
			tmp = cards[i];
			cards[i] = cards[rand];
			cards[rand] = tmp;
		}*/
		//System.out.println("kek");
		for (int i = size-1; i >= 0; i--)
		{
			rand = (int) (Math.floor(Math.random()*(size-1)));
			tmp = cards.get(i);
			cards.set(i, cards.get(rand));
			cards.set(rand, tmp);
		}
	}

	/**
	 * Deals a card from this deck.
	 * @return the card just dealt, or null if all the cards have been
	 *         previously dealt.
	 */
	public Card deal() {
		if (size == 0)
			return null;
		else
			size--;
			return cards.get(size);
			//return cards[size];
	}

	/**
	 * Generates and returns a string representation of this deck.
	 * @return a string representation of this deck.
	 */
	@Override
	public String toString() {
		String rtn = "size = " + size+"\n";
		for (int i = 0; i < size; i++)
		{
			rtn += cards.get(i)+"\n";
			//rtn += cards[i].toString()+"\n";
		}
		//String rtn = "size = " + size + "\nUndealt cards: \n";
		
		//Unit 9 - modify to work with Arrays
		/*
		for (int k = size - 1; k >= 0; k--) {
			rtn = rtn + cards.get(k);
			if (k != 0) {
				rtn = rtn + ", ";
			}
			if ((size - k) % 2 == 0) {
				// Insert carriage returns so entire deck is visible on console.
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\nDealt cards: \n";
		for (int k = cards.size() - 1; k >= size; k--) {
			rtn = rtn + cards.get(k);
			if (k != size) {
				rtn = rtn + ", ";
			}
			if ((k - cards.size()) % 2 == 0) {
				// Insert carriage returns so entire deck is visible on console.
				rtn = rtn + "\n";
			}
		}
		*/

		rtn = rtn + "\n";
		return rtn;
	}
}
