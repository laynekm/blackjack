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
		
		//test case where player wins
		/*
		String fileName1 = "src/main/resources/inputFile1.txt";
		String[] fileArray1 = game.convertFileToArray(fileName1);
		assertTrue("dealer".equals(game.playWithFileInput(fileArray1)));
		*/
		
		//test case where dealer wins
		String fileName2 = "src/main/resources/inputFile3.txt";
		String[] fileArray2 = game.convertFileToArray(fileName2);
		System.out.println(game.playWithFileInput(fileArray2) + " wins!");
	}
	
	//how can this be tested automatically as it's dependent on user input?
	public void testPlayWithConsoleInput() {

	}
}
