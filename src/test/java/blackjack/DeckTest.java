package blackjack;

import java.util.Arrays;

import junit.framework.TestCase;

public class DeckTest extends TestCase{
	public void testShuffle() {
		Deck deck = new Deck();
		String[] cards = deck.getCards();
		deck.shuffle();
		String[] shuffledCards = deck.getCards();
		
		//test shuffled deck != original deck
		assertFalse(Arrays.equals(cards, shuffledCards));
	}
	
	public void testDealCard() {
		Deck deck = new Deck();
		String[] cards = deck.getCards();
		String dealtCard = deck.dealCard();
		String[] newCards = deck.getCards();
		
		//test that dealtCard is first in deck
		assertEquals(cards[0], dealtCard);
		
		//test that dealtCard is no longer in deck
		assertFalse(Arrays.asList(newCards).contains(dealtCard));
		
		//test that new deck is one smaller than original
		assertEquals(newCards, cards.length - 1);
	}
}
