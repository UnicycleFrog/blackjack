package blackjack;
/**
 * This is a class that tests the Card class.
 */
public class CardTester {

	/**
	 * The main method in this class checks the Card operations for consistency.
	 *	@param args is not used.
	 */
	public static void main(String[] args) {
		Card test = new Card("Ace", "Spades", 1);
		System.out.println(test);
		Card test1 = new Card("Seven", "Clubs", 7);
		System.out.println(test1);
		Card test2 = new Card("Queen", "Hearts", 10);
		System.out.println(test2);
		Card test3 = new Card("Ace", "Spades", 1);
		System.out.println(test3);
		
		System.out.println(test.matches(test3));
	}
}
