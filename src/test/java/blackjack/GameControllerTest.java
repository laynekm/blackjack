package blackjack;

import junit.framework.TestCase;
import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class GameControllerTest extends TestCase{
	
	public void testIsValidInputType() {
		GameController game = new GameController();
		assertTrue(game.isValidInputType("C"));
		assertTrue(game.isValidInputType("F"));
		assertFalse(game.isValidInputType("X"));
		assertFalse(game.isValidInputType(""));
	}
	
	public void testIsValidMove() {
		GameController game = new GameController();
		assertTrue(game.isValidMove("H"));
		assertTrue(game.isValidMove("S"));
		assertFalse(game.isValidMove("X"));
		assertFalse(game.isValidMove(""));
	}
	
	public void testIsValidMoveWithSplit() {
		GameController game = new GameController();
		assertTrue(game.isValidMoveWithSplit("H"));
		assertTrue(game.isValidMoveWithSplit("S"));
		assertTrue(game.isValidMoveWithSplit("D"));
		assertFalse(game.isValidMoveWithSplit("X"));
		assertFalse(game.isValidMoveWithSplit(""));
	}
	
	public void testIsValidPlayAgain() {
		GameController game = new GameController();
		assertTrue(game.isValidPlayAgain("Y"));
		assertTrue(game.isValidPlayAgain("N"));
		assertFalse(game.isValidPlayAgain("X"));
		assertFalse(game.isValidPlayAgain(""));
	}
	
	public void testIsValidFile() {
		GameController game = new GameController();
		
		//tests file exists
		String realFileName = "src/main/resources/playerWins.txt";
		String fakeFileName = "src/main/resources/fakeFile.txt";
		assertTrue(game.isValidFile(realFileName).equals("Y"));
		assertFalse(game.isValidFile(fakeFileName).equals("Y"));
		
		//test case where input has incorrect suit/rank
		String invalidCardsFileName = "src/main/resources/invalidInput1.txt";
		assertFalse(game.isValidFile(invalidCardsFileName).equals("Y"));
		
		//test case where duplicate cards
		String duplicateCardsFileName = "src/main/resources/invalidInput2.txt";
		assertFalse(game.isValidFile(duplicateCardsFileName).equals("Y"));
	}
	
	public void testConvertFileToArray() {
		GameController game = new GameController();
		String fileName = "src/main/resources/dealerSplits.txt";
		String[] expectedArray = {"SK", "H9", "C5", "D5", "S", "H7", "CQ", "SA", "SQ", "D2"};
		String[] actualArray = game.convertFileToArray(fileName);
		
		System.out.println(Arrays.toString(actualArray));
		
		//verify actualArray = expectedArray
		assertArrayEquals(expectedArray, actualArray);
		
	}
	
	public void testPlayGameFileInput() {
		GameController game = new GameController();
	
		//test with file input
		String fileName1 = "src/main/resources/bothHaveBlackjack.txt";
		String fileName2 = "src/main/resources/dealerBusts.txt";
		String fileName3 = "src/main/resources/playerWins.txt";
		String fileName4 = "src/main/resources/playerSplits.txt";
		String fileName5 = "src/main/resources/dealerSplits.txt";
		String fileName6 = "src/main/resources/playerBusts.txt";
		String fileName7 = "src/main/resources/playerHasBlackjack.txt";
		String fileName8 = "src/main/resources/playerHitsRepeatedly.txt";
		String fileName9 = "src/main/resources/dealerHitsRepeatedly.txt";
		String fileName10 = "src/main/resources/playerStands.txt";

		String[] fileArray1 = game.convertFileToArray(fileName1);
		String[] fileArray2 = game.convertFileToArray(fileName2);
		String[] fileArray3 = game.convertFileToArray(fileName3);
		String[] fileArray4 = game.convertFileToArray(fileName4);
		String[] fileArray5 = game.convertFileToArray(fileName5);
		String[] fileArray6 = game.convertFileToArray(fileName6);
		String[] fileArray7 = game.convertFileToArray(fileName7);
		String[] fileArray8 = game.convertFileToArray(fileName8);
		String[] fileArray9 = game.convertFileToArray(fileName9);
		String[] fileArray10 = game.convertFileToArray(fileName10);
		
		assertTrue(game.playGame(fileArray1, "T").equals("Dealer wins!"));
		game.endGame();
		assertTrue(game.playGame(fileArray2, "T").equals("Player wins!"));
		game.endGame();
		assertTrue(game.playGame(fileArray3, "T").equals("Player wins!"));
		game.endGame();
		assertTrue(game.playGame(fileArray4, "T").equals("Player wins!"));
		game.endGame();
		assertTrue(game.playGame(fileArray5, "T").equals("Player wins!"));
		game.endGame();
		assertTrue(game.playGame(fileArray6, "T").equals("Dealer wins!"));
		game.endGame();
		assertTrue(game.playGame(fileArray7, "T").equals("Player wins!"));
		game.endGame();
		assertTrue(game.playGame(fileArray8, "T").equals("Dealer wins!"));
		game.endGame();
		assertTrue(game.playGame(fileArray9, "T").equals("Dealer wins!"));
		game.endGame();
		assertTrue(game.playGame(fileArray10, "T").equals("Player wins!"));
		game.endGame();
		
	}
	
	public void testDetermineWinner() {
		GameController game = new GameController();
		
		//test case where dealer wins
		Player player1 = new Player();
		Dealer dealer1 = new Dealer();
		Card card1 = new Card("SK");
		Card card2 = new Card("DK");
		Card card3 = new Card("S5");
		Card card4 = new Card("D2");
		Card card5 = new Card("S3");
		Card card6 = new Card("H10");
		player1.hit(card1);
		player1.hitSplit(card2);
		player1.hitSplit(card3);
		dealer1.hit(card4);
		dealer1.hit(card5);
		dealer1.hit(card6);
		assertEquals("Dealer wins!", game.determineWinner(player1, dealer1));
		game.endGame();
		
		//test case where player wins
		player1.hit(card1);
		player1.hit(card6);
		dealer1.hit(card2);
		dealer1.hit(card3);
		dealer1.hit(card4);
		assertEquals("Player wins!", game.determineWinner(player1, dealer1));
	}
}
