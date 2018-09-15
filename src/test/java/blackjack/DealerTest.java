package blackjack;

import junit.framework.TestCase;

public class DealerTest extends TestCase{
	public void testGetCardStringVisible() {
		//test string without splitting
		Dealer dealer1 = new Dealer();
		Card card1 = new Card("D", "2");
		Card card2 = new Card("S", "A");
		Card card3 = new Card("H", "K");
		dealer1.hit(card1);
		dealer1.hit(card2);
		dealer1.hit(card3);

		assertEquals("[D2] [SA] [HK]", dealer1.getCardStringVisible());
		
		//test string with splitting
		Dealer dealer2 = new Dealer();
		Card card4 = new Card("D", "2");
		Card card5 = new Card("S", "A");
		Card card6 = new Card("H", "K");
		Card card7 = new Card("S", "3");
		Card card8 = new Card("D", "Q");
		dealer2.hit(card4);
		dealer2.hit(card5);
		dealer2.hit(card6);
		dealer2.hitSplit(card7);
		dealer2.hitSplit(card8);
		assertEquals("[D2] [SA] [HK] --- [S3] [DQ]", dealer2.getCardStringVisible());
		
		//test empty hand
		Dealer dealer3 = new Dealer();
		assertEquals("", dealer3.getCardStringVisible());
	}
	
	public void testGetCardStringHidden() {
		Dealer dealer1 = new Dealer();
		Card card1 = new Card("D", "2");
		Card card2 = new Card("S", "A");
		dealer1.hit(card1);
		dealer1.hit(card2);
		
		assertEquals("[D2] [||]", dealer1.getCardStringHidden());
	}
	
	public void testSplit() {
		//test valid split
		Dealer dealer1 = new Dealer();
		Card card1 = new Card("D5");
		Card card2 = new Card("S5");
		dealer1.hit(card1);
		dealer1.hit(card2);
		assertEquals(10, dealer1.getTotal());
		assertEquals(0, dealer1.getTotalSplit());
		assertEquals("[D5] [S5]", dealer1.getCardStringVisible());
		assertTrue(dealer1.split());
		assertEquals("[D5] --- [S5]", dealer1.getCardStringVisible());
		assertEquals(5, dealer1.getTotal());
		assertEquals(5, dealer1.getTotalSplit());
		
		//test invalid split
		Dealer dealer2 = new Dealer();
		Card card3 = new Card("D5");
		Card card4 = new Card("S4");
		dealer2.hit(card3);
		dealer2.hit(card4);
		assertFalse(dealer2.split());
		assertEquals(9, dealer2.getTotal());
		assertEquals(0, dealer2.getTotalSplit());
		
		//test empty hand
		Dealer dealer3 = new Dealer();
		assertFalse(dealer3.split());
		
	}
	
	public void testHasSplit() {
		//test case where split
		Player player1 = new Player();
		Card card1 = new Card("D5");
		Card card2 = new Card("S5");
		player1.hit(card1);
		player1.hit(card2);
		player1.split();
		assertTrue(player1.hasSplit());
		
		//test case where no split
		Player player2 = new Player();
		Card card3 = new Card("D5");
		Card card4 = new Card("S5");
		player2.hit(card3);
		player2.hit(card4);
		assertFalse(player2.hasSplit());
		
		//test case where no cards
		Player player3 = new Player();
		assertFalse(player3.hasSplit());
	}
	
	public void testDetermineHit() {
		//case where hand < 16
		Dealer dealer1 = new Dealer();
		Card card1 = new Card("S2");
		Card card2 = new Card("SK");
		dealer1.hit(card1);
		dealer1.hit(card2);
		assertEquals("H", dealer1.determineHit());
		
		//case where hand > 16
		Dealer dealer2 = new Dealer();
		Card card3 = new Card("SQ");
		Card card4 = new Card("SK");
		dealer2.hit(card3);
		dealer2.hit(card4);
		assertEquals("S", dealer2.determineHit());
		
		//where where have soft 17
		Dealer dealer3 = new Dealer();
		Card card5 = new Card("S2");
		Card card6 = new Card("SK");
		dealer3.hit(card5);
		dealer3.hit(card6);
		assertEquals("H", dealer3.determineHit());
	}
	
	public void testDetermineHitSplit() {
		//case where hand < 16
		Dealer dealer1 = new Dealer();
		Card card1 = new Card("S2");
		Card card2 = new Card("SK");
		dealer1.hitSplit(card1);
		dealer1.hitSplit(card2);
		assertEquals("H", dealer1.determineHitSplit());
		
		//case where hand > 16
		Dealer dealer2 = new Dealer();
		Card card3 = new Card("SQ");
		Card card4 = new Card("SK");
		dealer2.hitSplit(card3);
		dealer2.hitSplit(card4);
		assertEquals("S", dealer2.determineHitSplit());
		
		//where where have soft 17
		Dealer dealer3 = new Dealer();
		Card card5 = new Card("S2");
		Card card6 = new Card("SK");
		dealer3.hitSplit(card5);
		dealer3.hitSplit(card6);
		assertEquals("H", dealer3.determineHitSplit());
	}
}
