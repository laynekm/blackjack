package blackjack;

import java.util.List;

public class Player {
	private Hand hand;
	private Hand splitHand;

	public Player() {
		hand = new Hand();
		splitHand = new Hand();
	}
	
	//wrappers for methods in hand, don't require their own test cases
	public boolean hit(Card card) { 		return hand.hit(card); }
	public boolean hasBlackjack() { 		return hand.hasBlackjack(); }
	public boolean canSplit() { 			return hand.canSplit(); }
	public int getTotal() {					return hand.getTotal(); }
	public boolean hitSplit(Card card) { 	return splitHand.hit(card); }
	public int getTotalSplit() {			return splitHand.getTotal(); }
	public void clearCards() {				hand.clear(); splitHand.clear(); }
	
	//splits user's hand (ie. pops from hand and adds to splitHand), retruns false if invalid split
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
	
	public Hand getBestHand() {
		if(hand.getTotal() <= 21 && (hand.getTotal() >= splitHand.getTotal() || splitHand.getTotal() > 21)) {
			return hand;
		}
		if(splitHand.getTotal() <= 21 && (splitHand.getTotal() >= hand.getTotal() || hand.getTotal() > 21)) {
			return splitHand;
		}
		return null;
	}
	
	//returns string representation of player's hand
	public String getCardString() {
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
		if(returnString.endsWith(" ")) {
			returnString = returnString.substring(0,  returnString.length() - 1);
		}
		
		return returnString;
	}
}
