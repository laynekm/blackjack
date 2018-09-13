package blackjack;

import junit.framework.TestCase;

public class HandTest extends TestCase{

	public void testGetTotal() {
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
		
		Hand testerHand4 = new Hand();
		
		assertEquals(21, testerHand1.getTotal());
		assertEquals(13, testerHand2.getTotal());
		assertEquals(20, testerHand3.getTotal());
		assertEquals(0, testerHand4.getTotal());
	}
	
	public void testHasSoft17() {
		Hand testerHand1 = new Hand();
		Card card1 = new Card("CA");
		Card card2 = new Card("C4");
		Card card3 = new Card("C2");
		testerHand1.addCard(card1);
		testerHand1.addCard(card2);
		testerHand1.addCard(card3);
		
		Hand testerHand2 = new Hand();
		Card card4 = new Card("CK");
		Card card5 = new Card("C4");
		Card card6 = new Card("C3");
		testerHand2.addCard(card4);
		testerHand2.addCard(card5);
		testerHand2.addCard(card6);
		
		Hand testerHand3 = new Hand();
		
		assertTrue(testerHand1.hasSoft17());
		assertFalse(testerHand2.hasSoft17());
		assertFalse(testerHand3.hasSoft17());
	}
}
