package blackjack;

import junit.framework.TestCase;
import static org.junit.Assert.*;

import java.io.File;
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
	
	public void testIsValidFile() {
		GameController game = new GameController();
		
		//tests file exists
		String realFileName = "src/main/resources/inputFile1.txt";
		String fakeFileName = "src/main/resources/fakeFile.txt";
		assertTrue(game.isValidFile(realFileName));
		assertFalse(game.isValidFile(fakeFileName));
	}
	
	public void testConvertFileToArray() {
		GameController game = new GameController();
		String fileName = "src/main/resources/inputFile1.txt";
		String[] expectedArray = {"S10", "D3", "SQ", "CS", "H", "H5", "H", "SA", "S", "CA", "D2"};
		String[] actualArray = game.convertFileToArray(fileName);
		
		//verify actualArray = expectedArray
		assertArrayEquals(expectedArray, actualArray);
		
	}
	
	public void testPlayWithFileInput() {
		GameController game = new GameController();
		
		String fileName1 = "src/main/resources/playerWinsBlackjack.txt";
		String fileName2 = "src/main/resources/dealerWinsBlackjack.txt";
		String fileName3 = "src/main/resources/playerGoesBust.txt";
		String fileName4 = "src/main/resources/dealerGoesBust.txt";
		String fileName5 = "src/main/resources/playerScoreHigher.txt";
		String fileName6 = "src/main/resources/dealerScoreHigher.txt";
		String fileName7 = "src/main/resources/scoresEqual.txt";
		
		String[] fileArray1 = game.convertFileToArray(fileName1);
		String[] fileArray2 = game.convertFileToArray(fileName2);
		String[] fileArray3 = game.convertFileToArray(fileName3);
		String[] fileArray4 = game.convertFileToArray(fileName4);
		String[] fileArray5 = game.convertFileToArray(fileName5);
		String[] fileArray6 = game.convertFileToArray(fileName6);
		String[] fileArray7 = game.convertFileToArray(fileName7);
		
		assertTrue(game.playWithFileInput(fileArray1).equals("Player wins!"));
		assertTrue(game.playWithFileInput(fileArray2).equals("Dealer wins!"));
		assertTrue(game.playWithFileInput(fileArray3).equals("Dealer wins!"));
		assertTrue(game.playWithFileInput(fileArray4).equals("Player wins!"));
		assertTrue(game.playWithFileInput(fileArray5).equals("Player wins!"));
		assertTrue(game.playWithFileInput(fileArray6).equals("Dealer wins!"));
		assertTrue(game.playWithFileInput(fileArray7).equals("Dealer wins!"));
	}
	
	//not entirely sure how this is supposed to be tested
	public void testPlayWithConsoleInput() {
		GameController game = new GameController();
		Deck deck = new Deck();
		deck.shuffle();
		assertTrue(game.playWithConsoleInput(deck) == "Player wins!" || game.playWithConsoleInput(deck) == "Dealer wins!");
	}
}
