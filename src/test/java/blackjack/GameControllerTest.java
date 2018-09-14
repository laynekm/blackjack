package blackjack;

import junit.framework.TestCase;
import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

public class GameControllerTest extends TestCase{
	/*
	public void testRunGame() {
		GameController game = new GameController();
		String result = game.runGame();
		assertTrue(result == "dealer" || result == "player");
	}
	
	public void testPromptForInputType() {
		GameController game = new GameController();
		String inputType = game.promptForInputType();
		assertTrue(inputType.equals("C") || inputType.equals("F"));
	}
	
	public void testPromptForFileName() {
		GameController game = new GameController();
		String fileName = game.promptForFileName();
		assertTrue(game.isValidFile(fileName));
	}
	
	public void testIsValidFile() {
		GameController game = new GameController();
		
		//tests file exists
		String realFileName = "src/main/resources/inputFile1.txt";
		String fakeFileName = "src/main/resources/fakeFile.txt";
		assertTrue(game.isValidFile(realFileName));
		assertFalse(game.isValidFile(fakeFileName));
		
		//tests file is correct format
		String validFileName = "src/main/resources/inputFile1.txt";
		String invalidFileName = "src/main/resources/invalidFile1.txt";
		assertTrue(game.isValidFile(validFileName));
		assertFalse(game.isValidFile(invalidFileName));
	}
	
	public void testConvertFileToArray() {
		GameController game = new GameController();
		String fileName = "src/main/resources/inputFile1.txt";
		String[] expectedArray = {"S10", "D3", "SQ", "CS", "H", "H5", "H", "SA", "S", "CA", "D2"};
		String[] actualArray = game.convertFileToArray(fileName);
		
		//verify actualArray = expectedArray
		assertArrayEquals(expectedArray, actualArray);
		
	}
	*/
	
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
	
	//how can this be tested automatically as it's dependent on user input?
	public void testPlayWithConsoleInput() {

	}
}
