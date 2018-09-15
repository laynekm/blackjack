package blackjack;

public class Card {
	private String rank;
	private String suit;
	
	public Card(String s, String r) {
		suit = s;
		rank = r;
	}
	
	public Card(String sr) {
		suit = sr.substring(0, 1);
		rank = sr.substring(1, sr.length());
	}
	
	public String getRank() { return rank; }
	public String getSuit() { return suit; }
	public String toString() {
		return suit + rank;
	}
}
