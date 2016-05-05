package blackjack;
import java.util.List;
import java.util.ArrayList;

/**
 * Black jack dumb code
 * 
 * int player count = sum of player cards
 * int dealer count = sum of dealer cards
 * 
 * deal cards.....
 * if player or dealer count ==21, player or dealer win
 * 
 * if player count<21 player have option to hit or stand                  loop it a few times
 * if hit, player count = new random card + player count
 * 
 * else (player over 21/bust) player loose
 * 
 * 
 * 
 * if player stand 
 * if dealer count< 17, dealer hit. else dealer stand
 * if dealer > 21, dealer bust
 * 
 * 
 * if player>dealer, player win. else dealer win.
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class BlackjackBoard extends Board {
	/**
	 * The size (number of cards) on the board.
	 */
	private static final int BOARD_SIZE = 9;

	/**
	 * The ranks of the cards for this game to be sent to the deck.
	 */
	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS =
		{"spades", "hearts", "diamonds", "clubs"};

	/**
	 * The values of the cards for this game to be sent to the deck.
	 */
	private static final int[] POINT_VALUES =
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};


	/**
	 * The cards on this board.
	 */
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
	 * Creates a new <code>ElevensBoard</code> instance.
	 */
	public BlackjackBoard() {
		super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
	}

	public int score(List<Integer> selectedCards) {
		int player = 0;
		int dealer = 0;
		for (int i : selectedCards)
			player += i;
		return 0;
	}
	/**
	 * Determines if the selected cards form a valid group for removal.
	 * In Elevens, the legal groups are (1) a pair of non-face cards
	 * whose values add to 11, and (2) a group of three cards consisting of
	 * a jack, a queen, and a king in some order.
	 * @param selectedCards the list of the indices of the selected cards.
	 * @return true if the selected cards form a valid group for removal;
	 *         false otherwise.
	 */
	@Override
	public boolean isLegal(List<Integer> selectedCards) {
		return (containsPairSum11(selectedCards) || containsJQK(selectedCards));
	}

	/**
	 * Determine if there are any legal plays left on the board.
	 * In Elevens, there is a legal play if the board contains
	 * (1) a pair of non-face cards whose values add to 11, or (2) a group
	 * of three cards consisting of a jack, a queen, and a king in some order.
	 * @return true if there is a legal play left on the board;
	 *         false otherwise.
	 */
	@Override
	public boolean anotherPlayIsPossible() {
		return(containsPairSum11(cardIndexes()) || containsJQK(cardIndexes()));
	}

	/**
	 * Check for an 11-pair in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find an 11-pair.
	 * @return true if the board entries in selectedCards
	 *              contain an 11-pair; false otherwise.
	 */
	private boolean containsPairSum11(List<Integer> selectedCards) {
		int i = 0;
		int j = 1;
		int sum, tmp1, tmp2;
		while (i < selectedCards.size()) {
			while (j < selectedCards.size()) {
				sum = cardAt(selectedCards.get(i)).pointValue()+cardAt(selectedCards.get(j)).pointValue();
				if (sum == 11) {
					tmp1 = selectedCards.get(i);
					tmp2 = selectedCards.get(j);
					selectedCards.clear();
					selectedCards.add(tmp1);
					selectedCards.add(tmp2);
					return true;
				}
				j++;
			}
			i++;
			j = i+1;
		}
		return false;
	}

	/**
	 * Check for a JQK in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find a JQK group.
	 * @return true if the board entries in selectedCards
	 *              include a jack, a queen, and a king; false otherwise.
	 */
	private boolean containsJQK(List<Integer> selectedCards) {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 9 *** */
		ArrayList<String> rankList = new ArrayList<String>();
		for (int i = 0; i < selectedCards.size(); i++) {
			rankList.add(cardAt(selectedCards.get(i)).rank());
		}
		return (rankList.contains("jack") && rankList.contains("queen") && rankList.contains("king"));
	}
}
