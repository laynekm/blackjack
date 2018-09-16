package blackjack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

//Application.launch(GUI.class, args);

public class GUI extends Application {
	
	//note: the key for the hidden card is 'hi'
	private static Map<String, Image> cards;
	private static ImageView imgView;
	private Button consoleBtn;
	private TextField fileTxt;
	private Button fileBtn;
	private Button hitBtn;
	private Button standBtn;
	private Button splitBtn;
	private Label errorLabel;
	
	GameController game;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		game = new GameController();
		
		Pane canvas = new Pane();
        Scene scene = new Scene(canvas, 800, 600);
        stage.setTitle("Blackjack");
        stage.setScene(scene);
        stage.show();
        importImages();
        initUI(canvas);
        System.out.println(cards.toString());
	}
	
	public void initUI(Pane canvas) {
		Label label = new Label("Select console or file input:");
		label.setFont(Font.font("Serif", FontWeight.NORMAL, 20));
		label.relocate(20, 20);
		Label sublabel = new Label("*Not really console input since there's a UI now, but you know what I mean.");
		sublabel.setFont(Font.font("Serif", FontWeight.NORMAL, 14));
		sublabel.relocate(20, 50);
		
		consoleBtn = new Button("Console");
		consoleBtn.setMinWidth(100);
		consoleBtn.relocate(20, 80);
		
		fileBtn = new Button("File");
		fileBtn.setMinWidth(100);
		fileBtn.relocate(20,  110);
		fileTxt = new TextField();
		fileTxt.relocate(140,  110);
		errorLabel = new Label();
		errorLabel.relocate(20, 140);
		errorLabel.setTextFill(Color.RED);
		
		canvas.getChildren().addAll(label, sublabel, consoleBtn, fileTxt, fileBtn, errorLabel);
		
		//String winner = "";
		
		fileBtn.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				
				String validFileResult = game.isValidFile((String.valueOf("src/main/resources/" + fileTxt.getText())));
				if(validFileResult.equals("Y")) {
					String fileName = String.valueOf("src/main/resources/" + fileTxt.getText());
					String[] gameMoves = game.convertFileToArray(fileName);
					game.playGame(gameMoves, "F");
				}
				else {
					errorLabel.setText(validFileResult);
				}
			}
		});
		
		consoleBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Deck deck = new Deck();
				deck.shuffle();
				game.playGame(deck.toArray(), "C");
			}
		});
		
		
	}
	
	public void importImages() {
		File cardsDir = new File("src/main/resources/cards");
		FilenameFilter imgFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith("png");
			}
		};
		
		File[] cardsFile = cardsDir.listFiles(imgFilter);
		cards = new HashMap<String, Image>();
		for(File cardFile : cardsFile) {
			String cardString = cardFile.toString();
			cardString = cardString.substring(25, cardString.length());
			if(cardString.charAt(2) == '0') { cardString = cardString.substring(0, 3); }
			else { cardString = cardString.substring(0, 2); }

			try {
				cards.put(cardString, new Image(new FileInputStream(cardFile.getPath())));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
	}
	
	public static void displayCards(Player player, Dealer dealer) {
		//clear existings cards
		
		//add new cards
		int playerHandx = 10;
		int playerHandy = 10;
		int playerSplitHandx = 10;
		int playerSplitHandy = 150;
		int dealerHandx = 10;
		int dealerHandy = 300;
		int dealerSplitHandx = 10;
		int dealerSplitHandy = 450;
		for(int i = 0; i < player.getHand().getSize(); i++) {
			imgView = new ImageView();
			String playerCard = player.getHand().getCards().get(i).toString();
			imgView.setImage(cards.get(playerCard));
			imgView.relocate(playerHandx, playerHandy);
		}
		for(int i = 0; i < player.getSplitHand().getSize(); i++) {
			
		}
		for(int i = 0; i < dealer.getHand().getSize(); i++) {
			
		}
		for(int i = 0; i < dealer.getSplitHand().getSize(); i++) {
			
		}
	}
}
