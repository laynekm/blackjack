package blackjack;

import junit.framework.TestCase;

public class HandTest extends TestCase{

	public void testGetTotalWithHighAce() {
		Hand testerHand1 = new Hand();
		Card card1 = new Card("D", "10");
		Card card2 = new Card("S", "A");
		testerHand1.addCard(card1);
		testerHand1.addCard(card2);
		
		Hand testerHand2 = new Hand();
		Card card3 = new Card("C", "2");
		Card card4 = new Card("D", "10");
		Card card5 = new Card("S", "A");
		testerHand2.addCard(card3);
		testerHand2.addCard(card4);
		testerHand2.addCard(card5);
		
		Hand testerHand3 = new Hand();
		Card card6 = new Card("CQ");
		Card card7 = new Card("SK");
		testerHand3.addCard(card6);
		testerHand3.addCard(card7);
		
		assertEquals(21, testerHand1.getTotal());
		assertEquals(13, testerHand2.getTotal());
		assertEquals(20, testerHand3.getTotal());
	}
	
	public void testGetTotalNoCards() {
		Hand testerHand = new Hand();
		assertEquals(0, testerHand.getTotal());
	}
}
