package blackjack;

import junit.framework.TestCase;
import static org.junit.Assert.*;

import java.io.File;

public class GameControllerTest extends TestCase{
	public void testRunGame() {
		GameController game = new GameController();
		String result = game.runGame();
		assertTrue(result == "dealer" || result == "player");
	}
	
	//how can this be tested automatically as it's dependent on user input?
	public void testPromptForInputType() {
		GameController game = new GameController();
		String inputType = game.promptForInputType();
		assertTrue(inputType == "C" || inputType == "F");
	}
	
	public void testIsValidFile() {
		GameController game = new GameController();
		
		//tests file exists
		String realFileDir = "src/main/resources/inputFile1.txt";
		String fakeFileDir = "src/main/resources/fakeFile.txt";
		assertTrue(game.isValidFile(realFileDir));
		assertFalse(game.isValidFile(fakeFileDir));
		
		//tests file is correct format
		String validFileDir = "src/main/resources/inputFile1.txt";
		String invalidFileDir = "src/main/resources/invalidFile1.txt";
		assertTrue(game.isValidFile(validFileDir));
		assertFalse(game.isValidFile(invalidFileDir));
	}
	
	public void testConvertFileToArray() {
		GameController game = new GameController();
		File file = new File("src/main/resources/inputFile1.txt");
		String[] expectedArray = {"S10", "D3", "SQ", "CS", "H", "H5", "H", "SA", "S", "CA", "D2"};
		String[] actualArray = game.convertFileToArray(file);
		
		//verify actualArray = expectedArray
		assertArrayEquals(expectedArray, actualArray);
		
	}
	
	public void testPlayWithFileInput() {
		GameController game = new GameController();
		
		//test case where player wins
		File file1 = new File("src/main/resources/inputFile1.txt");
		String[] fileArray1 = game.convertFileToArray(file1);
		assertEquals("dealer", game.playWithFileInput(fileArray1));
		
		//test case where dealer wins
		File file2 = new File("src/main/resources/inputFile2.txt");
		String[] fileArray2 = game.convertFileToArray(file2);
		assertEquals("player", game.playWithFileInput(fileArray2));
	}
	
	//how can this be tested automatically as it's dependent on user input?
	public void testPlayWithConsoleInput() {
		
	}
}
