package blackjack;

import junit.framework.TestCase;

public class PlayerTest extends TestCase{
	public void testGetCardString() {
		Player player = new Player();
		Card card1 = new Card("D", "2");
		Card card2 = new Card("S", "A");
		Card card3 = new Card("H", "K");
		player.addCard(card1);
		player.addCard(card2);
		player.addCard(card3);
		
		assertEquals("[D2] [SA] [HK]", player.getCardString());
	}
}
