package blackjack;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Canvas;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackjackGUI extends Canvas implements ActionListener{
	private BufferedImage back;
	//private Board board;
	private Hand dealer;
	private Hand player;
	private int totalWins;
	private int gamesPlayed;
	private Deck deck;
	private static final String[] ranks =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
	private static final String[] suits =
		{"spades", "hearts", "diamonds", "clubs"};
	private static final int[] pointValues =
		{11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
	private String message;
	private File file;
	
	public BlackjackGUI() {
		deck = new Deck(ranks, suits, pointValues);
		setBackground(Color.decode("#007F00"));
		dealer = new BlackjackHand(deck);
		player = new BlackjackHand(deck);
		fileReader();
		//totalWins = 0;
		//gamesPlayed = 0;
		message = "";
		setVisible(true);
	}
	public void update(Graphics window)
    {
            paint(window);
    }
	public void paint( Graphics window )
	{
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

			
			
		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();

		graphToBack.setColor(Color.decode("#007F00"));
		graphToBack.fillRect(0,0,800,600);
        graphToBack.setFont(new Font("SansSerif", Font.PLAIN, 16));
		graphToBack.setColor(Color.black);
		graphToBack.drawString("Games Played: " + gamesPlayed, 25, 50);
		graphToBack.drawString("Wins: " + totalWins, 25, 75);
		if(player.size() > 0)
			graphToBack.drawString("Player score: " + player.score(), 400, 375);
        if (player.score() == 21 || dealer.score() == 21)
        	endCheck();
		if (message != "") {
			graphToBack.drawString("Dealer score: " + dealer.score(), 400, 75);
			dealer.draw(graphToBack, 100, 100, false);
		}
		else
			dealer.draw(graphToBack, 100, 100, true);
        player.draw(graphToBack, 100, 400, false);
        graphToBack.setFont(new Font("SansSerif", Font.PLAIN, 50));
		//graphToBack.drawString("Blackjack ", 400, 50 );
		graphToBack.drawString(message, 300, 300);
            //ship.draw(graphToBack);
            twoDGraph.drawImage(back, null, 0, 0);

	}
	 public void run()
     {
         try
         {
             while(true)
             {
                 Thread.currentThread().sleep(5);
                 repaint();
             }
         }
         catch(Exception e)
         {

         }
     }
	 public void fileReader() {
		 file = new File("src/blackjack/log.txt");
		 totalWins = 0;
		 gamesPlayed = 0;
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Scanner in = new Scanner(file);
			while (in.hasNextLine()) {
				String input = in.nextLine();
				if (input.indexOf("W") == 0) {
					totalWins++;
					gamesPlayed++;
				}
				else if (input.indexOf("D") == 0)
					gamesPlayed++;
				else if (input.indexOf("L") == 0)
					gamesPlayed++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 public void fileWriter() {
		 FileWriter write;
		try {
			System.out.println("Writing to file");
			write = new FileWriter(file, true);
			BufferedWriter buf = new BufferedWriter(write);
			if (message.indexOf("w") > -1)
				buf.write("W");
			else if (message.indexOf("T")> -1)
				buf.write("T");
			else
				buf.write("L");
			buf.newLine();
			buf.flush();
			buf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 public void dealPlayer() {
			player.deal(deck);
			if (player.bust())
				endCheck();
				
	 }
	 public void bot() {
		 while (dealer.score()<= 17)
			 dealer.deal(deck);
	 }
	 public void endCheck() {
		 if(player.won(dealer)) {
			 	message = "";
				totalWins++;
				if (player.score() == 21 && player.size() == 2)
					message = "Blackjack! ";
				message += "You won!";
		}
		 else if (player.score() == dealer.score())
			 message = "Tie!";
		 else {
				message = "You lost!";
		}
		fileWriter();
		gamesPlayed++;
			 
	 }
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(BlackjackGUIRunner.hit)) {
			System.out.println("You pressed hit");
			if (message == "")
				dealPlayer();
			else
				message = "Click \"new game\"";
			repaint();
		} else if (e.getSource().equals(BlackjackGUIRunner.stand)) {
			System.out.println("You pressed stand");
			if (message == "") {
				bot();
				endCheck();
			}
			else
				message = "Click \"new game\"";
			repaint();
		} else if (e.getSource().equals(BlackjackGUIRunner.newGame)) {
			System.out.println("You pressed new game");
			player.newGame(deck);
			dealer.newGame(deck);
			message = "";
			repaint();
		} else {
			System.out.println("ERROR, could not identify actionevent!");
			return;
		}
		
	}
}
