package blackjack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GameController {
	private static Scanner scanner = new Scanner(System.in);
	
	private Player player;
	private Dealer dealer;
	
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
		
		System.out.println("\nCards dealt:");
		printGameDataDealerHidden();
		
		//game is over if dealer or player get a blackjack (ie. 21)
		if(dealer.hasBlackjack()) {
			System.out.println("Dealer's cards revealed:");
			printGameDataDealerVisible();
			System.out.println("Dealer got blackjack!");
			return "Dealer wins!"; 
		}
		if(player.hasBlackjack()) { 
			System.out.println("Dealer's cards revealed:");
			printGameDataDealerVisible();
			System.out.println("Player got blackjack!");
			return "Player wins!"; 
		}
		
		//split player hand if able to and player decides to
		if(consoleInput && player.canSplit()) { 		playerMove = promptMoveWithSplit(); }
		else if(consoleInput) { 						playerMove = promptMove(); }
		else { 											playerMove = moves[x]; }
		if(player.canSplit() && playerMove.equals("D")) {
			x++;
			System.out.println("Player splits:");
			player.split();
			System.out.println(moves[x].toString());
			Card card4 = new Card(moves[x++]);
			player.hit(card4);
			printGameDataDealerHidden();
		}
		
		//player hits or stands
		if(!consoleInput) { playerMove = moves[x++]; }
		while(playerMove.equals("H")) {
			playerMove = "";
			System.out.println("Player hits:");
			Card card = new Card(moves[x++]);
			if(!player.hit(card)) {
				printGameDataDealerHidden();
				if(player.hasSplit()) {
					System.out.println("Player went bust on first hand! Total: " + player.getTotal());
				}
				else {
					System.out.println("Player went bust! Total: " + player.getTotal());
					return "Dealer wins!"; 
				}
			}
			printGameDataDealerHidden();
			if(consoleInput) { 	playerMove = promptMove(); }
			else { 				playerMove = moves[x++]; }
		}
		
		if(playerMove.equals("S")) {
			System.out.println("\nPlayer stands.\n");
		}
		
		//if player has split, now hits or stands on split
		if(player.hasSplit()) {
			//second card automatically gets dealt to second hand
			System.out.println("Player hits on second hand:");
			Card card5 = new Card(moves[x++]);
			if(!player.hitSplit(card5)) {
				printGameDataDealerHidden();
				System.out.println("Player went bust on second hand! Total: " + player.getTotal());
			}
			printGameDataDealerHidden();
			
			//decides whether user hits on second hand
			if(consoleInput) { 	playerMove = promptMove(); }
			else { 				playerMove = moves[x++]; }
			while(playerMove.equals("H")) {
				playerMove = "";
				System.out.println("Player hits on second hand:");
				Card card6 = new Card(moves[x++]);
				if(!player.hitSplit(card6)) {
					printGameDataDealerVisible();
					System.out.println("Player went bust on second hand! Total: " + player.getTotal());
				}
				printGameDataDealerHidden();
			}
			
			if(player.getTotal() > 21 && player.getTotalSplit() > 21) {
				System.out.println("Player went bust on both hands!");
				return "Dealer wins!"; 
			}
			
			System.out.println("Player stands.\n");
		}
		
		System.out.println("Dealer's card revealed:");
		printGameDataDealerVisible();
		
		//split dealer hand
		if(dealer.canSplit() && dealer.determineHit().equals("H")) {
			System.out.println("Dealer splits:");
			dealer.split();
			Card card4 = new Card(moves[x++]);
			dealer.hit(card4);
			printGameDataDealerVisible();
		}
		
		while(dealer.determineHit().equals("H")) {
			System.out.println("Dealer hits:");
			Card card = new Card(moves[x++]);
			if(!dealer.hit(card)) {
				printGameDataDealerVisible();
				if(dealer.hasSplit()) {
					System.out.println("Dealer went bust on first hand! Total: " + dealer.getTotal());
				}
				else {
					System.out.println("Dealer went bust! Total: " + dealer.getTotal());
					return "Player wins!"; 
				}
			}
			printGameDataDealerVisible();
		}
		
		System.out.println("Dealer stands.\n");

		//if dealer has split, now hits or stands on split
		if(dealer.hasSplit()) {
			
			//second hand gets card delt automatically
			System.out.println("Dealer hits on second hand:");
			Card card6 = new Card(moves[x++]);
			if(!dealer.hitSplit(card6)) {
				printGameDataDealerVisible();
				System.out.println("Dealer went bust on second hand! Total: " + dealer.getTotal());
			}
			printGameDataDealerVisible();
			
			while(dealer.determineHitSplit().equals("H")) {
				System.out.println("Dealer hits on second hand:");
				Card card = new Card(moves[x++]);
				if(!dealer.hitSplit(card)) {
					printGameDataDealerVisible();
					System.out.println("Dealer went bust on second hand! Total: " + dealer.getTotal());
				}
				printGameDataDealerVisible();
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

	//verifies whether a file exists and is of valid format
	public boolean isValidFile(String fileName) {
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
					return false;
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
						return false;
					}
				}
			}
			
			return true;
		}
		
		System.out.println("Invalid file. File does not exist.");
		return false;
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
	
	//determines
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
		input = scanner.nextLine();
		while(!isValidFile(input)) {
			System.out.print("Enter file name: ");
			input = scanner.nextLine();
		}
		return input;
	}
	
	public String promptMove() {
		String input = "";
		while(!isValidMove(input)) {
			System.out.print("Hit (H) or Stand (S): ");
			input = scanner.nextLine();
		}
		return input;
	}
	
	public String promptMoveWithSplit() {
		String input = "";
		while(!isValidMoveWithSplit(input)) {
			System.out.print("Hit (H), Stand (S), or Split (D): ");
			input = scanner.nextLine();
		}
		return input;
	}
	
	//prints all cards
	public void printGameDataDealerHidden() {
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
		System.out.println();
	}
	
	//prints cards but only dealer's first card is visible
	public void printGameDataDealerVisible() {
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
		System.out.println();
	}
}
