import java.util.*;

public class Problem2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int numCardsInHand = 5;
		while (scan.hasNextLine()) {
			String input = scan.nextLine();
			Scanner inputScan = new Scanner(input);
			for (int i = 0; i < numCardsInHand; i++) {
				Card card = new Card(inputScan.next());
			}
		}
	}
}


private class Card {
	private CardSuit cardSuit;
	private CardValue cardValue;

	public Card(String input) {
		char v = input.charAt(0);
		switch (c) {
			case '2':
				this.cardValue = CardValue.2;
				break;
			case '3':
				this.cardValue = CardValue.3;
				break;
			case '4':
				this.cardValue = CardValue.4;
				break;
			case '5':
				this.cardValue = CardValue.5;
				break;
			case '6':
				this.cardValue = CardValue.6;
				break;
			case '7':
				this.cardValue = CardValue.8;
				break;
			case '8':
				this.cardValue = CardValue.8;
				break;
			case '9':
				this.cardValue = CardValue.9;
				break;
			case 'T':
				this.cardValue = CardValue.T;
				break;
			case 'J':
				this.cardValue = CardValue.J;
				break;
			case 'Q':
				this.cardValue = CardValue.Q;
				break;
			case 'K':
				this.cardValue = CardValue.K;
				break;
			case 'A':
				this.cardValue = CardValue.A;
				break;
		}
		char s = input.charAt(1);
		switch (s) {
			case 'C':
				this.cardSuit = CardSuit.C;
				break;
			case 'D':
				this.cardSuit = CardSuit.D;
				break;
			case 'H':
				this.cardSuit = CardSuit.H;
				break;
			case 'S':
				this.cardSuit = CardSuit.S;
				break; 	
		}
	}
	public String toString() {
		return String.format("%s%s", this.cardValue.toString(), this.cardSuit.toString());
	}
}

private enum CardSuit {
	C,
	D,
	H,
	S;
}

private enum CardValue {
	2,
	3,
	4,
	5,
	6,
	7,
	8,
	9,
	T,
	J,
	Q,
	K,
	A;
}
