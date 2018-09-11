package blackjack;

import junit.framework.TestCase;

public class DealerTest extends TestCase{
	public void testGetCardStringVisible() {
		Dealer player = new Dealer();
		Card card1 = new Card("D", "2");
		Card card2 = new Card("S", "A");
		player.addCard(card1);
		player.addCard(card2);
		
		assertEquals("[D2] [SA]", player.getCardStringVisible());
	}
	
	public void testGetCardStringHidden() {
		Dealer player = new Dealer();
		Card card1 = new Card("D", "2");
		Card card2 = new Card("S", "A");
		player.addCard(card1);
		player.addCard(card2);
		
		assertEquals("[D2] [||]", player.getCardStringVisible());
	}
}
