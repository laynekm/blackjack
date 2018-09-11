package blackjack;

import junit.framework.TestCase;

public class PlayerTest extends TestCase{
	public void testGetCardString() {
		Player player = new Player();
		Card card1 = new Card("D", "2");
		Card card2 = new Card("S", "A");
		Card card3 = new Card("H", "K");
		player.getHand().addCard(card1);
		player.getHand().addCard(card2);
		player.getHand().addCard(card3);
		
		assertEquals("[D2] [SA] [HK]", player.getCardString());
	}
}
