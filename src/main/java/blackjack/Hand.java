package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	List<Card> cards;
	
	public Hand() {
		cards = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public int getTotal() {
		int total = 0;
		
		//first determine total without aces
		for(int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i);
			
			if(card.getRank().equals("A")) {
				continue;
			}
			else if(card.getRank().equals("J") || card.getRank().equals("Q") || card.getRank().equals("K")) {
				total += 10;
			}
			else {
				total += Integer.parseInt(card.getRank());
			}
		}
		
		//use this pre-total to determine whether ace should be 1 or 11
		for(int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i);
			if(card.getRank().equals("A") && total <= 10) {
				total += 11;
			}
			else if(card.getRank().equals("A") && total > 10) {
				total += 1;
			}
		}
		
		return total;
	}
	
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
	
	public List<Card> getCards(){
		return cards;
	}
}
