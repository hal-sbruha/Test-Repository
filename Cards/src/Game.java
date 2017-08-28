import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Card hand[] = new Card[3];
		Card foundation[] = new Card[4];
		ArrayList<ArrayList<Card>> stacks = new ArrayList<ArrayList<Card>>();
		Deck d = new Deck();
		d.shuffle();
		setupStacks(d, stacks);
		setupHand(d,hand);
		printOptions();
		Scanner sc = new Scanner(System.in);
		while (true) {
			display(d, stacks, hand, foundation);
			callOption(sc, d, stacks, foundation);
		}
		
	}
	public static void display(Deck deck, ArrayList<ArrayList<Card>> stacks, Card hand[], Card foundation[]) {
		System.out.printf("[%s %s [%s]]     ", hand[0], hand[1], hand[2]);
		for(int i = 0; i < 4; i++) {
			if (foundation[i] == null) {
				System.out.print("empty ");
			}
			else {
				System.out.print(foundation[i] + " ");
			}
		}
		System.out.println();
		System.out.println();
		for(int i = 0; i < 7; i++) {
			System.out.printf("%9s ", i);
		}
		System.out.println();
		for(int i = 0; i < maxStack(stacks); i++) {
			for(int j = 0; j < 7; j++) {
				if (i < stacks.get(j).size()) {
					System.out.printf("%9s ", stacks.get(j).get(i));
				}
				else {
					System.out.printf("%9s ", "");
				}
			}
			System.out.println();
		}
	}
	public static void setupStacks(Deck deck, ArrayList<ArrayList<Card>> stacks) {
		for(int i = 0; i < 7; i++) {
			stacks.add(new ArrayList<Card>()); 
			for(int j = 0; j < i; j++) {
				Card card = deck.draw();
				card.face_down = true;
				stacks.get(i).add(card);
			}
			stacks.get(i).add(deck.draw());
		}
	}
	public static int maxStack(ArrayList<ArrayList<Card>> stacks) {
		int currentMax = 0; 
		for(int i = 0; i < 7; i++) {
			if(stacks.get(i).size() > currentMax) {
				currentMax = stacks.get(i).size();
			}
		}
		return currentMax;
	}
	public static void setupHand(Deck deck, Card hand[]) {
		hand[0] = deck.draw();
		hand[1] = deck.draw();
		hand[2] = deck.draw();
	}
	public static void move(Deck deck, ArrayList<ArrayList<Card>> stacks, int startStack, int endStack, int numCards) {
		if (stacks.get(endStack).size() != 0 && !valid(stacks.get(endStack).get(stacks.get(endStack).size() -1),stacks.get(startStack).get(stacks.get(startStack).size() - numCards))){
			System.out.println("Not valid move");
			return;
		}
		if (stacks.get(startStack).get(stacks.get(startStack).size() - numCards).face_down) {
			System.out.println("Not valid move");
			return;
		}
		if (!isKing(stacks.get(startStack).get(stacks.get(startStack).size() - numCards), stacks.get(startStack))) {
			System.out.println("Not valid move");
			return;
		}
		for (int i = 0; i < numCards; i++) {
			Card card = stacks.get(startStack).get(stacks.get(startStack).size() + i - numCards); 
			stacks.get(startStack).remove(card);
			stacks.get(endStack).add(card);
		}
		flip(stacks.get(startStack));
	}
	public static void flip(ArrayList<Card> s) {
		if (s.size() == 0) {
			return;
		}
		if (s.get(s.size() - 1).face_down) {
			s.get(s.size() - 1).face_down = false;
		}
	}
	public static void printOptions() {
		System.out.println("Type 'ss' to move a card from one stack to another.");
		System.out.println("Type 'sf' to move a card from a stack to the foundation.");
		System.out.println("Type 'hs' to move a card from your hand to a stack.");
		System.out.println("Type 'hf' to move a card from your hand to the foundation.");
		System.out.println("Type 'fs' to move a card from the foundation to a stack.");
		System.out.println("Type 'd' to draw a new hand.");
		
	}
	public static void callOption(Scanner sc, Deck deck, ArrayList<ArrayList<Card>> stacks, Card[] foudation) {
		System.out.println("What would you like to do?");
		String s = sc.nextLine();
		if (s.equals("ss")) {
			System.out.println("Type the starting stack, then the ending stack, and then the number of cards");
			int startStack = sc.nextInt();
			int endStack = sc.nextInt();
			int numCards = sc.nextInt();
			move(deck, stacks, startStack, endStack, numCards);
			sc.nextLine();
		}		
		else if (s.equals("sf")) {
			System.out.println("Which stack has the card that you want to put into the foundation?");
			int stackNum = sc.nextInt();
			ArrayList<Card> myStack = stacks.get(stackNum);
			if (myStack.size() == 0) {
				System.out.println("Not valid stack.");
			}
			else {
				if (foundation(foudation, myStack.get(myStack.size() - 1))) {
					myStack.remove(myStack.size() - 1);
					flip(myStack);
				}
				
			}
			sc.nextLine();
		}
		else if (s.equals("hs")) {
			
		}
		else if (s.equals("hf")) {
			
		}
		else if (s.equals("fs")) {
			
		}
		else if (s.equals("d")) {
			
		}
		else {
			System.out.println("Oh no!");
		}

	}
	public static boolean valid(Card c1, Card c2) {
		if (c1.isBlack() && c2.isBlack()) {
			return false;
		}
		if (!c1.isBlack() && !c2.isBlack()) {
			return false;
		}
		if(c2.face_down) {
			return false;
		}
		if(c1.val - 1 != c2.val) {
			return false;
		}
		return true;
	}
	public static boolean isKing(Card card, ArrayList<Card> stack) {
		if(stack.isEmpty() && card.val != 13) {
			return false;
		}
		return true;
	}
	public static boolean foundation(Card[] f, Card card) {
		if (card.val == 1) {
			for (int i = 0; i < f.length; i++) {
				if (f[i] == null) {
					f[i] = card; 
					return true;
				}
			}
		}
		for (int i = 0; i < f.length; i++) {
			if (f[i] != null && card.suits == f[i].suits && card.val == f[i].val + 1) {
				f[i] = card;
				return true;
			}
		}
		System.out.println("Not valid move.");
		return false;
	}

}
