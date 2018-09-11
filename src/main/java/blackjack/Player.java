package blackjack;

import java.util.List;

public class Player {
	private Hand playerHand;

	public Player() {
		playerHand = new Hand();
	}
	
	public void addCard(Card card) {
		playerHand.addCard(card);
	}
	
	public String getCardString() {
		String returnString = "";
		List<Card> cards = playerHand.getCards();
		
		for(int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i);
			returnString += "[";
			returnString += card.getSuit();
			returnString += card.getRank();
			returnString += "] ";
		}
		
		//remove extra space at the end and return
		if(returnString.endsWith(" ")) {
			returnString = returnString.substring(0,  returnString.length() - 1);
		}
		
		return returnString;
	}
}
