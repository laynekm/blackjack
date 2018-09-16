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
		//ace counts as 11
		Hand testerHand1 = new Hand();
		Card card1 = new Card("D", "10");
		Card card2 = new Card("S", "A");
		testerHand1.hit(card1);
		testerHand1.hit(card2);
		assertEquals(21, testerHand1.getTotal());
		
		//ace counts as 1
		Hand testerHand2 = new Hand();
		Card card3 = new Card("C", "2");
		Card card4 = new Card("D", "10");
		Card card5 = new Card("S", "A");
		testerHand2.hit(card3);
		testerHand2.hit(card4);
		testerHand2.hit(card5);
		assertEquals(13, testerHand2.getTotal());
		
		//ace counts as 11 then 1
		Hand testerHand3 = new Hand();
		Card card6 = new Card("C8");
		Card card7 = new Card("SA");
		testerHand3.hit(card6);
		testerHand3.hit(card7);
		assertEquals(19, testerHand3.getTotal());
		Card card8 = new Card("S5");
		testerHand3.hit(card8);
		assertEquals(14, testerHand3.getTotal());
		
		//aces count as 11 and 1
		Hand testerHand4 = new Hand();
		Card card9 = new Card("SA");
		Card card10 = new Card("CA");
		testerHand4.hit(card9);
		testerHand4.hit(card10);
		assertEquals(12, testerHand4.getTotal());
		
		//aces both count as 1
		Hand testerHand5 = new Hand();
		testerHand5.hit(card9);
		testerHand5.hit(card10);
		testerHand5.hit(card4);
		assertEquals(12, testerHand5.getTotal());
		
		//test J, Q, K all count as 10
		Hand testerHand6 = new Hand();
		Card card11 = new Card("SJ");
		Card card12 = new Card("SQ");
		Card card13 = new Card("SK");
		testerHand6.hit(card11);
		assertEquals(10, testerHand5.getTotal());
		testerHand6.hit(card12);
		assertEquals(20, testerHand5.getTotal());
		testerHand6.hit(card13);
		assertEquals(30, testerHand5.getTotal());

		//test case where Hand empty
		Hand testerHand7 = new Hand();
		assertEquals(0, testerHand7.getTotal());
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
