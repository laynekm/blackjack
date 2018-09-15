package blackjack;

import junit.framework.TestCase;
import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class GameControllerTest extends TestCase{
	
	public void startGame() {
		GameController game = new GameController();
		assertTrue(game.startGame().equals("Dealer") || game.startGame().equals("Player"));
	}
	
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
	
	public void testIsValidFile() {
		GameController game = new GameController();
		
		//tests file exists
		String realFileName = "src/main/resources/playerWins.txt";
		String fakeFileName = "src/main/resources/fakeFile.txt";
		assertTrue(game.isValidFile(realFileName));
		assertFalse(game.isValidFile(fakeFileName));
		
		//TODO: test file input is of valid format
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
	
	public void testPlayGame() {
		GameController game = new GameController();
	
		String fileName1 = "src/main/resources/bothHaveBlackjack.txt";
		String fileName2 = "src/main/resources/dealerBusts.txt";
		String fileName3 = "src/main/resources/playerWins.txt";
		String fileName4 = "src/main/resources/playerSplits.txt";
		String fileName5 = "src/main/resources/dealerSplits.txt";

		String[] fileArray1 = game.convertFileToArray(fileName1);
		String[] fileArray2 = game.convertFileToArray(fileName2);
		String[] fileArray3 = game.convertFileToArray(fileName3);
		String[] fileArray4 = game.convertFileToArray(fileName4);
		String[] fileArray5 = game.convertFileToArray(fileName5);
		
		assertTrue(game.playGame(fileArray1, "F").equals("Dealer wins!"));
		game.endGame();
		assertTrue(game.playGame(fileArray2, "F").equals("Player wins!"));
		game.endGame();
		assertTrue(game.playGame(fileArray3, "F").equals("Player wins!"));
		game.endGame();
		assertTrue(game.playGame(fileArray4, "F").equals("Player wins!"));
		game.endGame();
		assertTrue(game.playGame(fileArray5, "F").equals("Player wins!"));
		game.endGame();
	}
	
	public void determineWinner() {
		GameController game = new GameController();
		
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
		
		assertEquals("Dealer", game.determineWinner(player1, dealer1));
	}
}
