package blackjack;

import junit.framework.TestCase;

public class HandTest extends TestCase{
	
	public void testHit() {
		//test hitting and returning false (ie. > 21)
		Hand testerHand1 = new Hand();
		Card card1 = new Card("CQ");
		Card card2 = new Card("CK");
		Card card3 = new Card("S8");
		testerHand1.hit(card1);
		testerHand1.hit(card2);
		assertEquals(2, testerHand1.getSize());
		assertFalse(testerHand1.hit(card3));
		assertEquals(3, testerHand1.getSize());
		
		//test hitting and returning true (ie. < 21)
		Hand testerHand2 = new Hand();
		Card card4 = new Card("C5");
		Card card5 = new Card("C6");
		Card card6 = new Card("S2");
		testerHand2.hit(card4);
		testerHand2.hit(card5);
		assertEquals(2, testerHand2.getSize());
		assertTrue(testerHand2.hit(card6));
		assertEquals(3, testerHand2.getSize());
	}
	
	public void testPopCard() {
		//test valid case
		Hand testerHand1 = new Hand();
		Card card1 = new Card("CQ");
		Card card2 = new Card("SA");
		testerHand1.hit(card1);
		testerHand1.hit(card2);
		assertEquals(2, testerHand1.getSize());
		assertEquals("SA", testerHand1.popCard().toString());
		assertEquals(1, testerHand1.getSize());
		
		//test case where Hand empty
		Hand testerHand2 = new Hand();
		assertEquals(null, testerHand2.popCard());
		assertEquals(0, testerHand2.getSize());
	}

	public void testGetTotal() {
		Hand testerHand1 = new Hand();
		Card card1 = new Card("D", "10");
		Card card2 = new Card("S", "A");
		testerHand1.hit(card1);
		testerHand1.hit(card2);
		assertEquals(21, testerHand1.getTotal());
		
		Hand testerHand2 = new Hand();
		Card card3 = new Card("C", "2");
		Card card4 = new Card("D", "10");
		Card card5 = new Card("S", "A");
		testerHand2.hit(card3);
		testerHand2.hit(card4);
		testerHand2.hit(card5);
		assertEquals(13, testerHand2.getTotal());
		
		Hand testerHand3 = new Hand();
		Card card6 = new Card("CQ");
		Card card7 = new Card("SK");
		testerHand3.hit(card6);
		testerHand3.hit(card7);
		assertEquals(20, testerHand3.getTotal());
		
		//test case where Hand empty
		Hand testerHand4 = new Hand();
		assertEquals(0, testerHand4.getTotal());
	}
	
	public void testHasSoft17() {
		//test case where have soft 17
		Hand testerHand1 = new Hand();
		Card card1 = new Card("CA");
		Card card2 = new Card("C4");
		Card card3 = new Card("C2");
		testerHand1.hit(card1);
		testerHand1.hit(card2);
		testerHand1.hit(card3);
		assertTrue(testerHand1.hasSoft17());
		
		//test case where have 17 but not soft 17
		Hand testerHand2 = new Hand();
		Card card4 = new Card("CK");
		Card card5 = new Card("C4");
		Card card6 = new Card("C3");
		testerHand2.hit(card4);
		testerHand2.hit(card5);
		testerHand2.hit(card6);
		assertFalse(testerHand2.hasSoft17());

		//test case where Hand empty
		Hand testerHand3 = new Hand();
		assertFalse(testerHand3.hasSoft17());
	}
	
	public void testHasBlackjack() {
		//test case where have two cards and blackjack
		Hand testerHand1 = new Hand();
		Card card1 = new Card("CA");
		Card card2 = new Card("SK");
		testerHand1.hit(card1);
		testerHand1.hit(card2);
		assertTrue(testerHand1.hasBlackjack());
		
		//test case where have two cards and no blackjack
		Hand testerHand2 = new Hand();
		Card card3 = new Card("CQ");
		Card card4 = new Card("SK");
		testerHand2.hit(card3);
		testerHand2.hit(card4);
		assertFalse(testerHand2.hasBlackjack());
		
		//test case where no cards
		Hand testerHand3 = new Hand();
		assertFalse(testerHand3.hasBlackjack());
	}
	
	public void testCanSplit() {
		//test valid split
		Hand testerHand1 = new Hand();
		Card card1 = new Card("C5");
		Card card2 = new Card("S5");
		testerHand1.hit(card1);
		testerHand1.hit(card2);
		assertTrue(testerHand1.canSplit());
		
		//test invalid split
		Hand testerHand2 = new Hand();
		Card card3 = new Card("C5");
		Card card4 = new Card("S4");
		testerHand2.hit(card3);
		testerHand2.hit(card4);
		assertFalse(testerHand2.canSplit());
		
		//test empty hand
		Hand testerHand3 = new Hand();
		assertFalse(testerHand3.canSplit());
	}
}
