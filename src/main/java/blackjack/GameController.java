package blackjack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class GameController {
	private static Scanner scanner = new Scanner(System.in);
	
	private Player player;
	private Dealer dealer;
	
	public GameController() {
		 player = new Player();
		 dealer = new Dealer();
	}
	
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
		System.out.println(Arrays.toString(moves));
		
		Card card0 = new Card(moves[0]);
		Card card1 = new Card(moves[1]);
		player.addCard(card0);
		player.addCard(card1);
		
		Card card2 = new Card(moves[2]);
		Card card3 = new Card(moves[3]);
		dealer.addCard(card2);
		dealer.addCard(card3);
		
		System.out.println("Dealer: " + dealer.getCardStringHidden());
		System.out.println("Dealer: " + dealer.getCardStringVisible());
		System.out.println("Player: " + player.getCardString());
		System.out.println("Player total: " + player.getTotal());
		
		//game is over if dealer or player get a blackjack (ie. 21)
		if(dealer.getTotal() == 21) {
			return "dealer";
		}
		else if(player.getTotal() == 21) {
			return "player";
		}	
		
		//if no blackjack, player prompted to hit or stand
		int x = 4;
		while(player.getTotal() < 21) {
			if(moves[x].equals("H")) {
				Card newCard = new Card(moves[x+1]);
				player.addCard(newCard);
				System.out.println("Player: " + player.getCardString());
				System.out.println("Player total: " + player.getTotal());
			}
			
			x += 2;
		}
		
		if(player.getTotal() > 21) {
			return "dealer";
		}
		else {
			return "player";
		}
	}
}
