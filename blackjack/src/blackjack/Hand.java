package blackjack;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public abstract class Hand {
	private List<Card> cards = new ArrayList<Card>();
	//private Card[] cards;

	/**
	 * The deck of cards being used to play the current game.
	 */
	//private Deck deck;

	/**
	 * Flag used to control debugging print statements.
	 */
	private static final boolean I_AM_DEBUGGING = false;

	/**
	 * Creates a new <code>Board</code> instance.
	 * @param size the number of cards in the board
	 * @param ranks the names of the card ranks needed to create the deck
	 * @param suits the names of the card suits needed to create the deck
	 * @param pointValues the integer values of the cards needed to create
	 *                    the deck
	 */
	public Hand(int size, Deck deck) {

		//cards = new Card[size];
		dealMyCards(deck);
	}

	/**
	 * Start a new game by shuffling the deck and
	 * dealing some cards to this board.
	 */
	public void newGame(Deck deck) {
		deck.shuffle();
		cards.clear();
		dealMyCards(deck);
	}

	/**
	 * Accesses the size of the board.
	 * Note that this is not the number of cards it contains,
	 * which will be smaller near the end of a winning game.
	 * @return the size of the board
	 */
	public int size() {
		return cards.size();
	}

	/**
	 * Determines if the board is empty (has no cards).
	 * @return true if this board is empty; false otherwise.
	 */
	public boolean isEmpty() {
		for (int k = 0; k < cards.size(); k++) {
			if (cards.get(k) != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Deal a card to the kth position in this board.
	 * If the deck is empty, the kth card is set to null.
	 * @param k the index of the card to be dealt.
	 */
	public void deal(Deck deck) {
		cards.add(deck.deal());
	}

	/**
	 * Accesses the deck's size.
	 * @return the number of undealt cards left in the deck.
	 */
	public int deckSize(Deck deck) {
		return deck.size();
	}

	/**
	 * Accesses a card on the board.
	 * @return the card at position k on the board.
	 * @param k is the board position of the card to return.
	 */
	public Card cardAt(int k) {
		return cards.get(k);
	}

	/**
	 * Replaces selected cards on the board by dealing new cards.
	 * @param selectedCards is a list of the indices of the
	 *        cards to be replaced.
	 */
	/*public void replaceSelectedCards(List<Integer> selectedCards) {
		for (Integer k : selectedCards) {
			deal(k.intValue());
		}
	}*/

	/**
	 * Gets the indexes of the actual (non-null) cards on the board.
	 *
	 * @return a List that contains the locations (indexes)
	 *         of the non-null entries on the board.
	 */
	public List<Integer> cardIndexes() {
		List<Integer> selected = new ArrayList<Integer>();
		for (int k = 0; k < cards.size(); k++) {
			if (cards.get(k) != null) {
				selected.add(new Integer(k));
			}
		}
		return selected;
	}

	/**
	 * Generates and returns a string representation of this board.
	 * @return the string version of this board.
	 */
	public String toString() {
		String s = "";
		for (int k = 0; k < cards.size(); k++) {
			s = s + k + ": " + cards.get(k).toString() + "\n";
		}
		return s;
	}

	/**
	 * Determine whether or not the game has been won,
	 * i.e. neither the board nor the deck has any more cards.
	 * @return true when the current game has been won;
	 *         false otherwise.
	 */
/*	public boolean gameIsWon() {
		if (deck.isEmpty()) {
			for (Card c : cards) {
				if (c != null) {
					return false;
				}
			}
			return true;
		}
		return false;
	}*/
	public abstract boolean won(Hand other);
	public abstract int score();
	public abstract boolean twentyOne();
	public abstract boolean bust();
	public abstract void draw(Graphics window, int x, int y, boolean isDealer);

	/**
	 * Deal cards to this board to start the game.
	 */
	private void dealMyCards(Deck deck) {
		for (int k = 0; k < 2; k++) {
			//cards.set(k, deck.deal());
			cards.add(deck.deal());
		}
	}
}
