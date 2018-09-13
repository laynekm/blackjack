package blackjack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameController {
	private static Scanner scanner = new Scanner(System.in);
	
	public String runGame() {
		String winner = "";
		String inputType = promptForInputType();

		if(inputType.equals("C")) {
			Deck deck = new Deck();
			deck.shuffle();
			winner = playWithConsoleInput(deck);
		}
		else{
			String fileName = promptForFileName();
			String[] gameMoves = convertFileToArray(fileName);
			winner = playWithFileInput(gameMoves);
		}
		
		return winner;
	}
	
	public String promptForInputType() {
		String inputType = "";
		
		while(!inputType.equals("C") && !inputType.equals("F")) {
			System.out.print("Select console (C) or file (F) input: ");
			inputType = scanner.nextLine();
		}

		return inputType;
	}
	
	public String promptForFileName() {
		String fileName = "";
		
		while(isValidFile(fileName) == false) {
			System.out.print("Enter file name: ");
			fileName = scanner.nextLine();
		}
		return fileName;
	}
	
	public String playWithConsoleInput(Deck deck) {
		
		return "";
	}
	
	public boolean isValidFile(String fileName) {
		File file = new File(fileName);
		if(file.exists()) {
			//TODO: check if formattiing is valid as well
			return true;
		}
		return false;
	}
	
	public String[] convertFileToArray(String fileName){
		Scanner fileScanner;
		String tempString = "";
		try {
			fileScanner = new Scanner(new File(fileName));
			tempString = fileScanner.nextLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String[] returnArray = tempString.split(" ");
		return returnArray;
	}
	
	//where the bulk of code will be
	public String playWithFileInput(String[] moves) {
		
		return "";
	}
}
