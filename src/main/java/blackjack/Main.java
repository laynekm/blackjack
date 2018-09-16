package blackjack;

public class Main {
	public static void main(String[] args)
	{
		String playGame = "Y";
		while(playGame.equals("Y")) {
			GameController game = new GameController();
			
			String inputType = game.promptInputType();
			String winner = "";

			if(inputType.equals("C")) {
				Deck deck = new Deck();
				deck.shuffle();
				winner = game.playGame(deck.toArray(), "C");
			}
			
			else{
				String fileName = game.promptFileName();
				String[] gameMoves = game.convertFileToArray(fileName);
				winner = game.playGame(gameMoves, "F");
			}
			
			System.out.println(winner);
			playGame = game.promptPlayAgain();
		}
	}
}
