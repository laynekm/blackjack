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
	
	//*********************
	// GAME LOGIC METHODS
	//*********************
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
		
		System.out.println("\nCards dealt:");
		printGameDataDealerHidden();
		
		//game is over if dealer or player get a blackjack (ie. 21)
		if(dealer.getTotal() == 21) { 
			System.out.println("Dealer got a blackjack");
			endGame();
			return "Dealer wins!"; 
		}
		if(player.getTotal() == 21) { 
			System.out.println("Player got a blackjack");
			endGame();
			return "Player wins!"; 
		}	
		
		//if no blackjack, player prompted to hit or stand
		int x = 4;
		while(player.getTotal() < 21 && x < moves.length) {
			if(moves[x].equals("H")) {
				System.out.println("Player hits:");
				Card newCard = new Card(moves[x+1]);
				player.addCard(newCard);
				printGameDataDealerHidden();
				x += 2;
			}
			else {
				System.out.println("Player stands.\n");
				x += 1;
				break;
			}
		}
		
		if(player.getTotal() > 21) { 
			System.out.println("Player went bust! Total: " + player.getTotal());
			endGame();
			return "Dealer wins!";
		}
		
		System.out.println("Dealer's card revealed:");
		printGameDataDealerVisible();
		
		//dealer hits if has 16 or soft 17 (ie. one of cards is ace)
		while((dealer.getTotal() <= 16 || dealer.hasSoft17() == true) && x < moves.length){
			System.out.println("Dealer hits:");
			Card newCard = new Card(moves[x]);
			dealer.addCard(newCard);
			printGameDataDealerVisible();
			x += 1;
		}
		
		if(dealer.getTotal() > 21) { 
			System.out.println("Dealer went bust! Total: " + dealer.getTotal());
			endGame();
			return "Player wins!";
		}
		
		System.out.println("Dealer stands.\n");

		if(player.getTotal() > dealer.getTotal()) {
			System.out.println("Player's score is higher than dealer's score");
			endGame();
			return "Player wins!";
		}
		else {
			System.out.println("Dealer's score is higher than player's score");
			endGame();
			return "Dealer wins!";
		}
	}
	
	public String playWithConsoleInput(Deck deck) {
	
		return "";
	}
	
	//******************
	// UTILITY METHODS
	//******************
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
	
	public void printGameDataDealerHidden() {
		System.out.println("Dealer: " + dealer.getCardStringHidden());
		System.out.println("Player: " + player.getCardString());
		System.out.println("Dealer total: ?");
		System.out.println("Player total: " + player.getTotal());
		System.out.println();
	}
	
	public void printGameDataDealerVisible() {
		System.out.println("Dealer: " + dealer.getCardStringVisible());
		System.out.println("Player: " + player.getCardString());
		System.out.println("Dealer total: " + dealer.getTotal());
		System.out.println("Player total: " + player.getTotal());
		System.out.println();
	}
	
	private void endGame() {
		player.clearCards();
		dealer.clearCards();
	}
}
