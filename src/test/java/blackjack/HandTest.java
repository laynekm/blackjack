package blackjack;

import junit.framework.TestCase;

public class HandTest extends TestCase{

	public void testGetTotalWithHighAce() {
		Hand testerHand = new Hand();
		Card card1 = new Card("D", "10");
		Card card2 = new Card("S", "A");
		testerHand.addCard(card1);
		testerHand.addCard(card2);
		
		assertEquals(21, testerHand.getTotal());
	}
	
	public void testGetTotalWithLowAce() {
		Hand testerHand = new Hand();
		Card card1 = new Card("C", "2");
		Card card2 = new Card("D", "10");
		Card card3 = new Card("S", "A");
		testerHand.addCard(card1);
		testerHand.addCard(card2);
		testerHand.addCard(card3);
		
		assertEquals(13, testerHand.getTotal());
	}
	
	public void testGetTotalNoCards() {
		Hand testerHand = new Hand();
		assertEquals(0, testerHand.getTotal());
	}
}
