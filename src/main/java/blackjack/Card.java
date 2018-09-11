package blackjack;

public class Card {
	private String rank;
	private String suit;
	
	public Card(String s, String r) {
		suit = s;
		rank = r;
	}
	
	public String getRank() { return rank; }
	public String getSuit() { return suit; }
	public String toString() {
		return rank + suit;
	}
}
