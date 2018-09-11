package blackjack;

import java.util.Arrays;

import junit.framework.TestCase;

public class DeckTest extends TestCase{
	public void testConstructor() {
		Deck deck = new Deck();
		
		//test that deck is correct length
		assertTrue(deck.getCards().length == 52);
		
		//test that deck contains all correct suits/ranks
		//- just look at output for now
	}
	
	public void testShuffle() {
		Deck deck = new Deck();
		Card[] cards = deck.getCards();
		
		deck.shuffle();
		Card[] shuffledCards = deck.getCards();
		
		//test that shuffled deck is still the correct length
		assertTrue(deck.getCards().length == 52);
		
		//test shuffled deck != original deck (ie. at least one card is different)
		int equalChecker = 1; 
		for(int i = 0; i < cards.length; i++) {
			if(cards[i].toString() != shuffledCards[i].toString()) {
				equalChecker = 0;
			}
		}
		assertTrue(equalChecker == 0);
	}
	
	public void testDealCard() {
		Deck deck = new Deck();
		Card[] cards = deck.getCards();
		Card dealtCard = deck.dealCard();
		Card[] newCards = deck.getCards();
		
		//test that dealtCard is first in deck
		assertEquals(cards[0].toString(), dealtCard.toString());
		
		//test that dealtCard is no longer in deck
		assertFalse(Arrays.asList(newCards.toString()).contains(dealtCard.toString()));
		
		//test that new deck is one smaller than original
		assertEquals(newCards.length, cards.length - 1);
	}
}
