package blackjack;

import junit.framework.TestCase;

public class PlayerTest extends TestCase{
	public void testGetCardString() {
		//test string without splitting
		Player player1 = new Player();
		Card card1 = new Card("D", "2");
		Card card2 = new Card("S", "A");
		Card card3 = new Card("H", "K");
		player1.hit(card1);
		player1.hit(card2);
		player1.hit(card3);

		assertEquals("[D2] [SA] [HK]", player1.getCardString());
		
		//test string with splitting
		Player player2 = new Player();
		Card card4 = new Card("D", "2");
		Card card5 = new Card("S", "A");
		Card card6 = new Card("H", "K");
		Card card7 = new Card("S", "3");
		Card card8 = new Card("D", "Q");
		player2.hit(card4);
		player2.hit(card5);
		player2.hit(card6);
		player2.hitSplit(card7);
		player2.hitSplit(card8);
		assertEquals("[D2] [SA] [HK] --- [S3] [DQ]", player2.getCardString());
		
		//test empty hand
		Player player3 = new Player();
		assertEquals("", player3.getCardString());
	}
	
	public void testSplit() {
		//test valid split
		Player player1 = new Player();
		Card card1 = new Card("D5");
		Card card2 = new Card("S5");
		player1.hit(card1);
		player1.hit(card2);
		assertEquals(10, player1.getTotal());
		assertEquals(0, player1.getTotalSplit());
		assertEquals("[D5] [S5]", player1.getCardString());
		assertTrue(player1.split());
		assertEquals("[D5] --- [S5]", player1.getCardString());
		assertEquals(5, player1.getTotal());
		assertEquals(5, player1.getTotalSplit());
		
		//test invalid split
		Player player2 = new Player();
		Card card3 = new Card("D5");
		Card card4 = new Card("S4");
		player2.hit(card3);
		player2.hit(card4);
		assertFalse(player2.split());
		assertEquals(9, player2.getTotal());
		assertEquals(0, player2.getTotalSplit());
		
		//test empty hand
		Player player3 = new Player();
		assertFalse(player3.split());
		
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
}
