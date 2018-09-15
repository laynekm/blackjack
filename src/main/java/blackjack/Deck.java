package blackjack;

import java.util.Arrays;
import java.util.Random;

public class Deck {
	private Card[] cards;
	
	//constructor
	public Deck(){
		
		cards = new Card[52];
		String[] suits = {"C", "D", "H", "S"};
		String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		
		int pos = 0;
		for(String s : suits) {
			for(String r : ranks) {
				Card card = new Card(s, r);
				cards[pos] = card;
				pos++;
			}
		}
	}
	
	//swaps every element with element in random position
	public void shuffle() {
		for(int i = 0; i < cards.length; i++) {
			Random rand = new Random();
			int num = rand.nextInt(52);
			
			Card tempCard = cards[num];
			cards[num] = cards[i];
			cards[i] = tempCard;
		}
	}
	
	//returns card at end of array
	public Card dealCard() {
		Card card = cards[0];
		cards = Arrays.copyOfRange(cards, 1, cards.length);
		return card;
	}
	
	public Card[] getCards() {
		return cards;
	}
	
	public String[] toArray() {
		String[] array = new String[52];
		for(int i = 0; i < cards.length; i++) {
			array[i] = cards[i].toString();
		}
		return array;
	}
}
