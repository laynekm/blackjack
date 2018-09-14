package blackjack;

import java.util.List;

public class Dealer {
	private Hand dealerHand;

	public Dealer() {
		dealerHand = new Hand();
	}
	
	public void addCard(Card card) {
		dealerHand.addCard(card);
	}
	
	public int getTotal() {
		return dealerHand.getTotal();
	}
	
	public String getCardStringVisible() {
		String returnString = "";
		List<Card> cards = dealerHand.getCards();
		
		for(int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i);
			returnString += "[";
			returnString += card.getSuit();
			returnString += card.getRank();
			returnString += "] ";
		}
		
		//remove extra space at the end and return
		if(returnString != "") {
			returnString = returnString.substring(0,  returnString.length() - 1);
		}
		
		return returnString;
	}
	
	public String getCardStringHidden() {
		String returnString = "";
		List<Card> cards = dealerHand.getCards();
		
		for(int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i);
			
			if(i == 0) {
				returnString += "[";
				returnString += card.getSuit();
				returnString += card.getRank();
				returnString += "] ";
			}
			else {
				returnString += "[||]";
			}
		}
		
		//remove extra space at the end and return
		if(returnString.endsWith(" ")) {
			returnString = returnString.substring(0,  returnString.length() - 1);
		}
		
		return returnString;
	}
	
	public boolean hasSoft17() {
		return dealerHand.hasSoft17();
	}
	
	public void clearCards() {
		dealerHand.clear();
	}
}
