# blackjack
java blackjack based on elevens

## Screenshot

![Screenshot](https://raw.githubusercontent.com/UnicycleFrog/blackjack/screenshot/screenshot.png "A screenshot of the game")

## Synopsis

This game uses the card and deck classes from the Elevens Lab. It is a simple blackjack game that keeps track of the score using a txt file.

## Interface

The DeckInterface interface is used to implement the Deck.
```
package blackjack;

public interface DeckInterface {
	public int size();
	public void shuffle();
	public Card deal();
	public String toString();
}
```
## Abstract

The hand is an abstract class which the BlackjackHand extends.
```
package blackjack;

...

public abstract class Hand {

...

	public abstract boolean won(Hand other);
	public abstract int score();
	public abstract boolean twentyOne();
	public abstract boolean bust();
	public abstract void draw(Graphics window, int x, int y, boolean isDealer);
}
```
## Text File I/O

The GUIRunner checks for log.txt in the package directory, and if it does not exist, creates one. For each win, tie, or loss, a "W", "T", or "L" and a newline is appended.
```
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
				...
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
```

## Contributors

* [UnicycleFrog](https://github.com/UnicycleFrog)
* [Cable74](https://github.com/cable74)
* [changtman](https://github.com/changtman)
