package blackjack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GameController {
	private static Scanner scanner = new Scanner(System.in);
	
	private Player player;
	private Dealer dealer;
	private boolean testInput;
	
	public GameController() {
		 player = new Player();
		 dealer = new Dealer();	
	}
	
	//************************
	// MAIN GAME LOGIC METHOD
	//************************
	public String playGame(String[] moves, String inputMode) {
		System.out.println("\nInput array: " + Arrays.toString(moves));
		
		//determines how input will be gathered
		boolean consoleInput;
		if(inputMode.equals("T")) {testInput = true; }
		if(inputMode.equals("C")) { consoleInput = true; }
		else { 						consoleInput = false; }
		
		//variables used, x tracks pos in input array
		String playerMove = "";
		int x = 0;

		Card card0 = new Card(moves[x++]);
		Card card1 = new Card(moves[x++]);
		Card card2 = new Card(moves[x++]);
		Card card3 = new Card(moves[x++]);
		player.hit(card0);
		player.hit(card1);
		dealer.hit(card2);
		dealer.hit(card3);
		
		printGameDataDealerHidden("Cards dealt: ");
		
		//game is over if dealer or player get a blackjack (ie. 21)
		if(dealer.hasBlackjack()) {
			printGameDataDealerVisible("Dealer got a blackjack! Dealer's cards revealed:");
			return "Dealer wins!"; 
		}
		if(player.hasBlackjack()) { 
			printGameDataDealerVisible("Player got a blackjack!");
			return "Player wins!"; 
		}
		
		//split player hand if able to and player decides to
		if(consoleInput && player.canSplit()) { 		playerMove = promptMoveWithSplit(); }
		//else if(consoleInput) { 						playerMove = promptMove(); }
		else { 											playerMove = moves[x]; }
		if(player.canSplit() && playerMove.equals("D")) {
			x++;
			player.split();
			System.out.println(moves[x].toString());
			Card card4 = new Card(moves[x++]);
			player.hit(card4);
			printGameDataDealerHidden("Player splits, and a card is added to their first hand: ");
		}
		
		//player hits or stands
		if(consoleInput && !player.canSplit()) { 		playerMove = promptMove(); }
		else{											playerMove = moves[x++]; }
		while(playerMove.equals("H")) {
			playerMove = "";
			Card card = new Card(moves[x++]);
			if(!player.hit(card)) {
				if(player.hasSplit()) {
					printGameDataDealerHidden("Player hit and went bust on first hand! Total: " + player.getTotal());
					break;
				}
				else {
					printGameDataDealerHidden("Player hit and went bust! Total: " + player.getTotal());
					return "Dealer wins!"; 
				}
			}
			else {
				printGameDataDealerHidden("Player hits:");
			}
			if(consoleInput) { 	playerMove = promptMove(); }
			else { 				playerMove = moves[x++]; }
		}
		
		if(playerMove.equals("S")) {
			printGameDataDealerHidden("Player stands.");
		}
		
		//if player has split, now hits or stands on split
		if(player.hasSplit()) {
			
			//player must hit on second hand
			Card card5 = new Card(moves[x++]);
			if(!player.hitSplit(card5)) {
				printGameDataDealerHidden("Card is dealt to player's second hand. Player went bust on second hand! Total: " + player.getTotal());
			}
			else {
				printGameDataDealerHidden("Card is dealt to player's second hand: ");
			}

			
			//decides whether user hits on second hand
			if(consoleInput) { 	playerMove = promptMove(); }
			else { 				playerMove = moves[x++]; }
			while(playerMove.equals("H")) {
				playerMove = "";
				Card card6 = new Card(moves[x++]);
				if(!player.hitSplit(card6)) {
					printGameDataDealerVisible("Player went bust on second hand! Total: " + player.getTotal());
					break;
				}
				else {
					printGameDataDealerHidden("Player hits on second hand: ");
				}
				
				if(consoleInput) { 	playerMove = promptMove(); }
				else { 				playerMove = moves[x++]; }
			}
			
			if(player.getTotal() > 21 && player.getTotalSplit() > 21) {
				System.out.println("Player went bust on both hands!");
				return "Dealer wins!"; 
			}
			
			printGameDataDealerHidden("Player stands.");

		}

		printGameDataDealerVisible("Dealer's cards revealed:");
		
		//split dealer hand
		if(dealer.canSplit() && dealer.determineHit().equals("H")) {
			dealer.split();
			Card card4 = new Card(moves[x++]);
			dealer.hit(card4);
			printGameDataDealerVisible("Dealer splits, and a card is added to their first hand:");
		}
		
		while(dealer.determineHit().equals("H")) {
			Card card = new Card(moves[x++]);
			if(!dealer.hit(card)) {
				if(dealer.hasSplit()) {
					printGameDataDealerVisible("Dealer hit and went bust on first hand! Total: " + dealer.getTotal());
					break;
				}
				else {
					printGameDataDealerVisible("Dealer hit and went bust! Total: " + dealer.getTotal());
					return "Player wins!"; 
				}
			}
			else {
				printGameDataDealerVisible("Dealer hits:");
			}
		}
		
		if(dealer.getTotal() <= 21) {printGameDataDealerVisible("Dealer stands.");}

		//if dealer has split, now hits or stands on split
		if(dealer.hasSplit()) {
			
			//second hand gets card delt automatically
			Card card6 = new Card(moves[x++]);
			if(!dealer.hitSplit(card6)) {
				printGameDataDealerVisible("Card is added to dealer's second hand. Dealer went bust on second hand! Total: " + dealer.getTotal());
			}
			else {
				printGameDataDealerVisible("Card is added to dealer's second hand:");
			}
			
			while(dealer.determineHitSplit().equals("H")) {
				Card card = new Card(moves[x++]);
				if(!dealer.hitSplit(card)) {
					printGameDataDealerVisible("Dealer hit and went bust on second hand! Total: " + dealer.getTotal());
					break;
				}
				else {
					printGameDataDealerVisible("Dealer hits on second hand:");
				}
			}
			
			if(dealer.getTotal() > 21 && dealer.getTotalSplit() > 21) {
				System.out.println("Dealer went bust on both hands!");
				return "Player wins!"; 
			}
		}
		
		String winner = determineWinner(player, dealer);
		return winner;
	}
	
	//*****************
	// UTILITY METHODS
	//*****************
	//user input validation methods
	public boolean isValidInputType(String inputType) {
		if(inputType.equals("C") || inputType.equals("F")) {
			return true;
		}
		return false;
	}
	
	public boolean isValidMove(String move) {
		if(move.equals("H") || move.equals("S")) {
			return true;
		}
		return false;
	}
	
	public boolean isValidMoveWithSplit(String move) {
		if(move.equals("H") || move.equals("S") || move.equals("D")) {
			return true;
		}
		return false;
	}
	
	public boolean isValidPlayAgain(String input) {
		if(input.equals("Y") || input.equals("N")) {
			return true;
		}
		return false;
	}

	//verifies whether a file exists and is of valid format
	public String isValidFile(String fileName) {
		File file = new File(fileName);
		if(file.exists()) {
			
			Deck deck = new Deck();
			String[] deckArray = deck.toArray();
			String[] inputArray = convertFileToArray(fileName);
			ArrayList<String> deckList = new ArrayList<String>(Arrays.asList(deckArray));
			ArrayList<String> inputList = new ArrayList<String>(Arrays.asList(inputArray));
			
			//ensures values can only get a valid card, S, H, or D
			for(int i = 0; i < inputList.size(); i++) {
				String elem = inputList.get(i);
				if(!elem.equals("S") && !elem.equals("H") && !elem.equals("D") && !deckList.contains(elem)) {
					System.out.println("Invalid file. Contains invalid strings.");
					return "Invalid file. Contains invalid strings.";
				}
			}
			
			//ensures values card values are not duplicated
			for(int i = 0; i < inputList.size(); i++) {
				String elem = inputList.get(i);
				if(!elem.equals("S") && !elem.equals("H") && !elem.equals("D")) {
					if(deckList.contains(elem)) {
						deckList.remove(elem);
					}
					else {
						System.out.println("Invalid file. File contains duplicate strings.");
						return "Invalid file. File contains duplicate strings.";
					}
				}
			}
			
			return "Y";
		}
		
		System.out.println("Invalid file. File does not exist.");
		return "Invalid file. File does not exist.";
	}
	
	//reads file and puts data into array of strings
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
	
	//clears player and dealer cards, used for testing
	public void endGame() {
		player.clearCards();
		dealer.clearCards();
	}
	
	//determines winner
	public String determineWinner(Player player, Dealer dealer) {
		Hand bestPlayerHand = player.getBestHand();
		Hand bestDealerHand = dealer.getBestHand();
		if(bestPlayerHand.getTotal() > bestDealerHand.getTotal()) {
			return "Player wins!";
		}
		else {
			return "Dealer wins!";
		}
	}
	
	//*****************************
	// USER PROMPT/DISPLAY METHODS
	// can't really have test cases for these, only for the validation methods they call
	//*****************************
	public String promptInputType() {
		String input = "";
		while(!isValidInputType(input)) {
			System.out.print("Console (C) or file (F) input: ");
			input = scanner.nextLine();
		}
		return input;
	}
	
	public String promptFileName() {
		String input = "";
		System.out.print("Enter file name: ");
		input = "src/main/resources/" + scanner.nextLine();
		while(!isValidFile(input).equals("Y")) {
			System.out.print("Enter file name: ");
			input = "src/main/resources/" + scanner.nextLine();
		}
		return input;
	}
	
	public String promptMove() {
		String input = "";
		/*
		while(!isValidMove(input)) {
			System.out.print("Hit (H) or Stand (S): ");
			input = scanner.nextLine();
		}*/
		input = GUI.promptMoveButton();
		return input;
	}
	
	public String promptMoveWithSplit() {
		String input = "";
		/*
		while(!isValidMoveWithSplit(input)) {
			System.out.print("Hit (H), Stand (S), or Split (D): ");
			input = scanner.nextLine();
		}*/
		input = GUI.promptMoveButtonWithSplit();
		return input;
	}
	
	public String promptPlayAgain() {
		String input = "";
		/*
		while(!isValidPlayAgain(input)) {
			System.out.print("Play again (Y/N): ");
			input = scanner.nextLine();
		}*/
		input = GUI.promptPlayAgain();
		return input;
	}
	
	//prints all cards
	public void printGameDataDealerHidden(String message) {
		System.out.println("\n" + message);
		if(!message.contains("stands")) {
			System.out.println("Dealer:              " + dealer.getCardStringHidden());
			System.out.println("Player:              " + player.getCardString());
			System.out.println("Dealer total:        ?");
			if(player.hasSplit()) {
				System.out.println("Player first total:  " + player.getTotal());
				System.out.println("Player second total: " + player.getTotalSplit());
			}
			else {
				System.out.println("Player total:        " + player.getTotal());

			}
		}
		
		if(!testInput) { GUI.displayCards(player, dealer, message, true); }
	}
	
	//prints cards but only dealer's first card is visible
	public void printGameDataDealerVisible(String message) {
		System.out.println("\n" +  message);
		if(!message.contains("stands")) {
			System.out.println("Dealer:              " + dealer.getCardStringVisible());
			System.out.println("Player:              " + player.getCardString());
			
			if(dealer.hasSplit()) {
				System.out.println("Dealer first total:  " + dealer.getTotal());
				System.out.println("Dealer second total: " + dealer.getTotalSplit());
			}
			else {
				System.out.println("Dealer total:        " + dealer.getTotal());

			}
			if(player.hasSplit()) {
				System.out.println("Player first total:  " + player.getTotal());
				System.out.println("Player second total: " + player.getTotalSplit());
			}
			else {
				System.out.println("Player total:        " + player.getTotal());

			}
		}

		if(!testInput) { GUI.displayCards(player, dealer, message, false); }

	}
}
