
public class Card {
	int val;
	Suits suits; 
	boolean face_down = false;
	public Card(int val, Suits suits) {
		this.suits = suits;
		this.val = val;
	}
	
	public void print() {
		switch (val) {
			case 1: System.out.print("Ace of ");
				break;
			case 11: System.out.print("Jack of ");
				break;
			case 12: System.out.print("Queen of ");
				break;
			case 13: System.out.print("King of ");
				break;
			default: System.out.print(val + " of ");
				break;
		}
		switch (suits) {
		case HEARTS: System.out.print("Hearts");
			break;
		case SPADES: System.out.print("Spades");
			break;
		case CLUBS: System.out.print("Clubs");
			break;
		case DIAMONDS: System.out.print("Diamonds");
			break;
		}
	}
	public String toString() {
		String cardString;
		switch (val) {
			case 1: cardString = "ACE_";
				break;
			case 11: cardString = "JCK_";
				break;
			case 12: cardString = "QNN_";
				break;
			case 13: cardString = "KNG_";
				break;
			default: cardString = val + "_";
				break;
		}
		switch (suits) {
		case HEARTS: cardString += "HEART";
			break;
		case SPADES: cardString += "SPADE";
			break;
		case CLUBS: cardString += "CLUB";
			break;
		case DIAMONDS: cardString += "DIMND";
			break;
		}
		if (face_down) {
			cardString = "facedown";
		}
		return cardString;
	}
	public boolean isBlack() {
		if (suits == Suits.CLUBS || suits == Suits.SPADES) {
			return true;
		}
		return false;
	}
}
	
