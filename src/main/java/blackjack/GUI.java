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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
	private Label label;
	private Label sublabel;
	private TextField fileTxt;
	private Button fileBtn;
	private static Button hitBtn;
	private static Button standBtn;
	private static Button splitBtn;
	private Label errorLabel;
	private Label winnerLabel;
	private static Label placeholderLabel;
	static HBox hbox;
	static ScrollPane canvas;
	static Pane container;
	static Scene scene;
	static Stage promptStage;
	static Pane promptCanvas;
    static Scene promptScene;
	
	GameController game;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		game = new GameController();
		
		hbox = new HBox();
		container = new Pane();
		canvas = new ScrollPane(hbox);
        scene = new Scene(canvas, 800, 600);
        
        stage.setTitle("Blackjack");
        stage.setScene(scene);
        stage.show();
        importImages();
        initUI(canvas);
        System.out.println(cards.toString());
	}
	
	public void initUI(ScrollPane canvas2) {
		label = new Label("Select console or file input:");
		label.setFont(Font.font("Serif", FontWeight.NORMAL, 20));
		label.relocate(20, 20);
		sublabel = new Label("*Not really console input since there's a UI now, but you know what I mean.");
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
		
		hitBtn = new Button("Hit");
		hitBtn.setMaxWidth(100);
		hitBtn.relocate(10, 10);
		hitBtn.setVisible(false);
		standBtn = new Button("Stand");
		standBtn.setMaxWidth(100);
		standBtn.relocate(10, 40);
		standBtn.setVisible(false);
		splitBtn = new Button("Split");
		splitBtn.setMaxWidth(100);
		splitBtn.relocate(10, 70);
		splitBtn.setVisible(false);
		placeholderLabel = new Label("");
		
		winnerLabel = new Label();
		winnerLabel.setFont(Font.font("Serif", FontWeight.NORMAL, 50));
		
		container.getChildren().addAll(label, sublabel, consoleBtn, fileTxt, fileBtn, errorLabel);
		canvas.setContent(container);
		
		fileBtn.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				
				String validFileResult = game.isValidFile((String.valueOf("src/main/resources/" + fileTxt.getText())));
				if(validFileResult.equals("Y")) {
					String fileName = String.valueOf("src/main/resources/" + fileTxt.getText());
					String[] gameMoves = game.convertFileToArray(fileName);
					clearMenu();
					String winner = game.playGame(gameMoves, "F");
					winnerLabel.setText(winner);
					winnerLabel.relocate(10, playerHandy - 125);
					if(winner.equals("Player wins!")){ winnerLabel.setTextFill(Color.GREEN);}
					else {winnerLabel.setTextFill(Color.RED);}
					container.getChildren().addAll(winnerLabel);
					canvas.setContent(container);
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
				clearMenu();
				setUpButtonEventHandlers();
				String winner = game.playGame(deck.toArray(), "C");
				winnerLabel.setText(winner);
				winnerLabel.relocate(10, playerHandy - 125);
				if(winner.equals("Player wins!")){ winnerLabel.setTextFill(Color.GREEN);}
				else {winnerLabel.setTextFill(Color.RED);}
				container.getChildren().addAll(winnerLabel);
				canvas.setContent(container);
			}
		});
		
		
	}
	
	public void clearMenu() {
		label.setVisible(false);
		sublabel.setVisible(false);
		fileTxt.setVisible(false);
		fileBtn.setVisible(false);
		consoleBtn.setVisible(false);	
		errorLabel.setVisible(false);
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
	
	static int messageLabely = 10;
	static int playerHandy = 110;
	static int playerSplitHandy = 110;
	static int dealerHandy = 30;
	static int dealerSplitHandy = 30;
	public static void displayCards(Player player, Dealer dealer, String message, boolean dealerHidden) {

		//clear existings cards
		
		//add new cards
		int playerHandx = 50;
		int playerSplitHandx = 50;
		int dealerHandx = 50;
		int dealerSplitHandx = 50;
		
		Label messageLabel = new Label(message);
		messageLabel.relocate(10, messageLabely);
		Label playerLabel = new Label("Player");
		playerLabel.relocate(10, playerHandy + 15);
		Label dealerLabel = new Label("Dealer");
		dealerLabel.relocate(10,  dealerHandy + 15);
		
		Label playerTotal = new Label();
		playerTotal.relocate(10, playerHandy + 30);
		Label dealerTotal = new Label();
		dealerTotal.relocate(10,  dealerHandy + 30);
		if(player.hasSplit()) {
			playerTotal.setText("Totals:\n" + player.getTotal() + ", " + player.getTotalSplit());
		}
		else {
			playerTotal.setText("Total:\n" + player.getTotal());
		}
		
		if(dealerHidden) {
			dealerTotal.setText("Total:\n?");
		}
		else if(dealer.hasSplit()) {
			dealerTotal.setText("Totals:\n" + dealer.getTotal() + ", " + dealer.getTotalSplit());
		}
		else {
			dealerTotal.setText("Total:\n" + dealer.getTotal());
		}
		
		
		container.getChildren().addAll(messageLabel, playerLabel, dealerLabel, playerTotal, dealerTotal);
		
		for(int i = 0; i < player.getHand().getSize(); i++) {
			imgView = new ImageView();
			String playerCard = player.getHand().getCards().get(i).toString();
			imgView.setImage(cards.get(playerCard));
			imgView.relocate(playerHandx, playerHandy);
			imgView.setFitWidth(50);
			imgView.setFitHeight(75);
			canvas.setContent(imgView);
			container.getChildren().addAll(imgView);
			playerHandx += 50;
		}
		for(int i = 0; i < player.getSplitHand().getSize(); i++) {
			imgView = new ImageView();
			String playerCard = player.getSplitHand().getCards().get(i).toString();
			imgView.setImage(cards.get(playerCard));
			imgView.relocate((50 * player.getHand().getSize()) + 50 + playerSplitHandx, playerSplitHandy);
			imgView.setFitWidth(50);
			imgView.setFitHeight(75);
			container.getChildren().addAll(imgView);
			playerSplitHandx += 50;
		}
		for(int i = 0; i < dealer.getHand().getSize(); i++) {
			imgView = new ImageView();
			String dealerCard = dealer.getHand().getCards().get(i).toString();
			if(i > 0 && dealerHidden) { imgView.setImage(cards.get("hi")); }
			else { imgView.setImage(cards.get(dealerCard));}
			imgView.relocate(dealerHandx, dealerHandy);
			imgView.setFitWidth(50);
			imgView.setFitHeight(75);
			canvas.setContent(imgView);
			container.getChildren().addAll(imgView);
			dealerHandx += 50;
		}
		for(int i = 0; i < dealer.getSplitHand().getSize(); i++) {
			imgView = new ImageView();
			String dealerCard = dealer.getSplitHand().getCards().get(i).toString();
			imgView.setImage(cards.get(dealerCard));
			imgView.relocate((50 * dealer.getHand().getSize()) + 50 + dealerSplitHandx, dealerSplitHandy);
			imgView.setFitWidth(50);
			imgView.setFitHeight(75);
			container.getChildren().addAll(imgView);
			dealerSplitHandx += 50;
		}
		
		messageLabely += 220;
		playerHandy += 220;
		playerSplitHandy += 220;
		dealerHandy += 220;
		dealerSplitHandy += 220;
		
		canvas.setContent(container);
	}
	
	public void setUpButtonEventHandlers() {
		hitBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				placeholderLabel.setText("H");
				hitBtn.setVisible(false);
				standBtn.setVisible(false);
				splitBtn.setVisible(false);
				promptStage.close();
			}
		});
		standBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				placeholderLabel.setText("S");
				hitBtn.setVisible(false);
				standBtn.setVisible(false);
				splitBtn.setVisible(false);
				promptStage.close();
			}
		});
		standBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				placeholderLabel.setText("D");
				hitBtn.setVisible(false);
				standBtn.setVisible(false);
				splitBtn.setVisible(false);
				promptStage.close();
			}
		});
	}
	
	public static String promptMoveButton() {
		hitBtn.setVisible(true);
		standBtn.setVisible(true);
		placeholderLabel.setText("");
		String returnString = pauseAndWaitForButton();
		placeholderLabel.setText("");
		return returnString;
	}
	
	public static String promptMoveButtonWithSplit() {
		hitBtn.setVisible(true);
		standBtn.setVisible(true);
		splitBtn.setVisible(true);
		placeholderLabel.setText("");
		String returnString = pauseAndWaitForButton();
		placeholderLabel.setText("");
		return returnString;
	}
	
	public static String promptPlayAgain() {
		return "";
	}
	
	private static String pauseAndWaitForButton() {
		promptStage = new Stage();
		promptCanvas = new Pane();
        promptScene = new Scene(promptCanvas, 150, 150);
        promptStage.setTitle("Select Option");
        promptStage.setScene(promptScene);
		promptCanvas.getChildren().addAll(hitBtn, standBtn, splitBtn);
		promptStage.showAndWait();
		return placeholderLabel.getText();
	}
}
