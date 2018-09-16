package blackjack;

import java.util.List;

public class Dealer {
	private Hand hand;
	private Hand splitHand;

	public Dealer() {
		hand = new Hand();
		splitHand = new Hand();
	}
	
	//wrappers for methods in hand, don't require their own test cases
	public boolean hit(Card card) { 		return hand.hit(card); }
	public boolean hasBlackjack() { 		return hand.hasBlackjack(); }
	public boolean canSplit() { 			return hand.canSplit(); }
	public int getTotal() {					return hand.getTotal(); }
	public boolean hasSoft17() {			return hand.hasSoft17(); }
	public boolean hitSplit(Card card) { 	return splitHand.hit(card); }
	public int getTotalSplit() {			return splitHand.getTotal(); }
	public boolean hasSoft17Split() {		return splitHand.hasSoft17(); }
	public void clearCards() {				hand.clear(); splitHand.clear(); }
	
	//splits user's hand (ie. pops from hand and adds to splitHand)
	public boolean split() {
		if(hand.canSplit()) {
			splitHand.hit(hand.popCard());
			return true;
		}
		
		return false;
	}
	
	//checks if player has split (ie. size of splitHand > 0)
	public boolean hasSplit() {
		if(splitHand.getSize() > 0) { return true; }
		return false;
	}
	
	//determines whether AI dealer should hit or not
	public String determineHit() {
		if(hand.getTotal() <= 16 || hand.hasSoft17() == true) { return "H"; }
		return "S";
	}
	
	public String determineHitSplit() {
		if(splitHand.getTotal() <= 16 || splitHand.hasSoft17() == true) { return "H"; }
		return "S";
	}
	
	public Hand getBestHand() {
		if(hand.getTotal() <= 21 && (hand.getTotal() >= splitHand.getTotal() || splitHand.getTotal() > 21)) {
			return hand;
		}
		if(splitHand.getTotal() <= 21 && (splitHand.getTotal() >= hand.getTotal() || hand.getTotal() > 21)) {
			return splitHand;
		}
		return null;
	}
	
	//returns string representation of dealer's hand with all cards visible
	public String getCardStringVisible() {
		String returnString = "";
		List<Card> cards = hand.getCards();
		List<Card> splitCards = splitHand.getCards();
		
		for(int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i);
			returnString += "[";
			returnString += card.getSuit();
			returnString += card.getRank();
			returnString += "] ";
		}
		
		if(splitHand.getSize() > 0) {
			returnString += "--- ";
			for(int i = 0; i < splitCards.size(); i++) {
				Card splitCard = splitCards.get(i);
				returnString += "[";
				returnString += splitCard.getSuit();
				returnString += splitCard.getRank();
				returnString += "] ";
			}
		}
		
		//remove extra space at the end and return
		if(returnString != "") {
			returnString = returnString.substring(0,  returnString.length() - 1);
		}
		
		return returnString;
	}
	
	//returns string representation of dealer's hand with subsequent cards hidden
	public String getCardStringHidden() {
		String returnString = "";
		List<Card> cards = hand.getCards();
		
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
}
