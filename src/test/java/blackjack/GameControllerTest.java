package blackjack;

import junit.framework.TestCase;
import static org.junit.Assert.*;

import java.io.File;

public class GameControllerTest extends TestCase{
	public void runGameTest() {
		GameController game = new GameController();
		String result = game.runGame();
		assertTrue(result == "dealer" || result == "player");
	}
	
	//how can this be tested automatically as it's dependent on user input?
	public void promptForInputTypeTest() {
		GameController game = new GameController();
		String inputType = game.promptForInputType();
		assertTrue(inputType == "C" || inputType == "F");
	}
	
	public void isValidFileTest() {
		GameController game = new GameController();
		
		//tests file exists
		File realFile = new File("src/main/resources/inputFile1.txt");
		File fakeFile = new File("src/main/resources/fakeFile.txt");
		assertTrue(game.isValidFile(realFile));
		assertFalse(game.isValidFile(fakeFile));
		
		//tests file is correct format
		File validFile = new File("src/main/resources/inputFile1.txt");
		File invalidFile = new File("src/main/resources/invalidFile1.txt");
		assertTrue(game.isValidFile(validFile));
		assertFalse(game.isValidFile(invalidFile));
	}
	
	public void readFileToArrayTest() {
		GameController game = new GameController();
		File file = new File("src/main/resources/inputFile1.txt");
		String[] expectedArray = {"S10", "D3", "SQ", "CS", "H", "H5", "H", "SA", "S", "CA", "D2"};
		String[] actualArray = game.readFileToArray(file);
		
		//verify actualArray = expectedArray
		assertArrayEquals(expectedArray, actualArray);
		
	}
	
	public void playWithFileInputTest() {
		GameController game = new GameController();
		
		//test case where dealer wins
		File file1 = new File("src/main/resources/inputFile1.txt");
		String[] fileArray1 = game.readFileToArray(file1);
		assertEquals("dealer", game.playWithFileInput(fileArray1));
		
		//test case where player wins
		File file2 = new File("src/main/resources/inputFile2.txt");
		String[] fileArray2 = game.readFileToArray(file2);
		assertEquals("player", game.playWithFileInput(fileArray2));
	}
	
	//how can this be tested automatically as it's dependent on user input?
	public void playWithConsoleInputTest() {
		
	}
}
