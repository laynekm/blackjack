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
	
	public void testGetBestHand() {
		//test case where one hand > other hand
		Player player1 = new Player();
		Card card1 = new Card("D5");
		Card card2 = new Card("S2");
		Card card3 = new Card("SK");
		Card card4 = new Card("DK");
		player1.hit(card1);
		player1.hit(card2);
		player1.hitSplit(card3);
		player1.hitSplit(card4);
		assertEquals(20, player1.getBestHand().getTotal());
				
		//test case where one hand is bust
		Player player2 = new Player();
		Card card5 = new Card("D5");
		Card card6 = new Card("S2");
		Card card7 = new Card("SK");
		Card card8 = new Card("DK");
		Card card9 = new Card("DJ");
		player2.hit(card5);
		player2.hit(card6);
		player2.hitSplit(card7);
		player2.hitSplit(card8);
		player2.hitSplit(card9);
		assertEquals(7, player2.getBestHand().getTotal());
		
		//test case where both hands bust
		Player player3 = new Player();
		player3.hit(card1);
		player3.hit(card3);
		player3.hit(card4);
		player3.hitSplit(card7);
		player3.hitSplit(card8);
		player3.hitSplit(card9);
		assertEquals(null, player3.getBestHand());
		
		//test with no splitting
		Player player4 = new Player();
		player4.hit(card1);
		assertEquals(5, player4.getBestHand().getTotal());
	}
}
