package blackjack;

public class BlackjackGUIRunner {

	public static void main(String[] args) {
		Board board = new BlackjackBoard();
		CardGameGUI gui = new CardGameGUI(board);
		gui.displayGame();
	}

}
