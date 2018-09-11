package blackjack;

import junit.framework.TestCase;

public class DealerTest extends TestCase{
	public void testGetCardStringVisible() {
		Dealer dealer = new Dealer();
		Card card1 = new Card("D", "2");
		Card card2 = new Card("S", "A");
		dealer.addCard(card1);
		dealer.addCard(card2);
		
		assertEquals("[D2] [SA]", dealer.getCardStringVisible());
	}
	
	public void testGetCardStringVisibleNoCards() {
		Dealer dealer = new Dealer();
		assertEquals("", dealer.getCardStringVisible());
	}
	
	public void testGetCardStringHidden() {
		Dealer dealer = new Dealer();
		Card card1 = new Card("D", "2");
		Card card2 = new Card("S", "A");
		dealer.addCard(card1);
		dealer.addCard(card2);
		
		assertEquals("[D2] [||]", dealer.getCardStringHidden());
	}
	
	public void testGetCardStringHiddenNoCards() {
		Dealer dealer = new Dealer();
		assertEquals("", dealer.getCardStringHidden());
	}
}
