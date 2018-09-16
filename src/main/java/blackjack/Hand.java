package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	List<Card> cards;
	
	public Hand() {
		cards = new ArrayList<Card>();
	}
	
	//removes card from end of hand, used for splitting
	public Card popCard() {
		if(cards.size() == 0) {
			return null;
		}
		
		return cards.remove(cards.size() - 1);
	}
	
	//returns size
	public int getSize() {
		return cards.size();
	}
	
	//adds card to hand, returns false if bust (ie. total > 21)
	public boolean hit(Card card) {
		cards.add(card);
		if(getTotal() > 21) {
			return false;
		}
		
		return true;
	}
	
	//checks if player has blackjack (ie. 2 cards and total = 21)
	public boolean hasBlackjack() {
		if(cards.size() == 2 && getTotal() == 21) {
			return true;
		}
		return false;
	}
	
	//checks if player can split (ie. cards have same rank)
	public boolean canSplit() {
		if(cards.size() == 0) {
			return false;
		}
		if(cards.get(0).getRank().equals(cards.get(1).getRank())) {
			return true;
		}
		return false;
	}
	
	//returns total, accounting for whether aces are worth 1 or 11
	public int getTotal() {
		int total = 0;
		int aceCount = 0;
		
		//first determine total without aces
		for(int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i);
			
			if(card.getRank().equals("A")) {
				aceCount++;
			}
			else if(card.getRank().equals("J") || card.getRank().equals("Q") || card.getRank().equals("K")) {
				total += 10;
			}
			else {
				total += Integer.parseInt(card.getRank());
			}
		}
		
		//use pre-total to determine value of aces
		int aceMax = 0;
		for(int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i);
			aceMax = aceCount * 11;
			if(card.getRank().equals("A") && total + aceMax > 21) {
				total += 1;
				aceCount--;
			}
			else if(card.getRank().equals("A") && total + aceMax <= 21) {
				total += 11;
				aceCount--;
			}
		}
		
		return total;
	}
	
	//checks if player has soft 17 (ie. score is 17 and one of the cards is an ace)
	public boolean hasSoft17() {
		if(getTotal() == 17) {
			for(int i = 0; i < cards.size(); i++) {
				if(cards.get(i).getRank().equals("A")){
					return true;
				}
			}
		}

		return false;
	}
	
	//deletes all cards in hand
	public void clear() {
		cards.clear();
	}
	
	//returns list of cards
	public List<Card> getCards(){
		return cards;
	}
}
