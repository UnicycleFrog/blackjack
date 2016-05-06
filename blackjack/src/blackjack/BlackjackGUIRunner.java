package blackjack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Component;

public class BlackjackGUIRunner extends JFrame {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	public static JButton hit;
	public static JButton stand;
	public static JButton newGame;
	
	public BlackjackGUIRunner() {
		super("BLACKJACK");
		JPanel panel= new JPanel();
		setSize(WIDTH,HEIGHT);
		//Board board = new BlackjackBoard();
		BlackjackGUI gui = new BlackjackGUI();
		((Component)gui).setFocusable(true);
		hit = new JButton("Hit");
		stand = new JButton("Stand");
		newGame = new JButton("New Game");
		
		getContentPane().add(gui);
		
		panel.add(hit);
		panel.add(stand);
		panel.add(newGame);
		hit.addActionListener(gui);
		stand.addActionListener(gui);
		newGame.addActionListener(gui);
		getContentPane().add(panel, BorderLayout.SOUTH);

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static JButton getHit() {
		return hit;
	}
	public static JButton getStand() {
		return stand;
	}
	public static void main(String[] args) {
		//Board board = new BlackjackBoard();
		//BlackjackGUI gui = new BlackjackGUI(board);
		BlackjackGUIRunner test = new BlackjackGUIRunner();

	}

}
