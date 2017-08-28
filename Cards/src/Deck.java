import java.util.Random;

public class Deck {
	int counter = -1;
	Card deck[] = new Card[52];
	public Deck() {
		Suits suits[] = {Suits.HEARTS, Suits.CLUBS, Suits.DIAMONDS, Suits.SPADES};
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 13; j++) {
				Card c = new Card(j + 1, suits[i]);
				deck[i * 13 + j] = c;
			}
		}
	}
	public Card draw() {
		counter++;
		return deck[counter];	
	}
	public void shuffle() {
		Random r = new Random();
		Card temp;
		for(int i = 0; i < 51; i++) {
			int rnum = r.nextInt(52-i) + i;
			temp = deck[i];
			deck[i] = deck[rnum];
			deck[rnum] = temp;
		}
		counter = -1;
	}

}
