package assignment1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameScenePanel {
	private MainView view;
	private Label boardText;
	LogActionListener logAction;
	Timer timer;
	Label enterMove;

	public GameScenePanel(MainView view) {
		this.view = view;
		this.boardText = null;
	}
	
	protected void start() {
		BorderPane mainLayout = new BorderPane();
		
		// Top Labels
		Label teamNull = new Label("Team Null");
		teamNull.setTextFill(Color.BLACK);
		teamNull.setFont(Font.font ("impact", 50));
		Label musketeers = new Label("Three Musketeers");
		musketeers.setTextFill(Color.BLACK);
		musketeers.setFont(new Font(20));
		VBox top = new VBox(teamNull, musketeers);
		top.setAlignment(Pos.TOP_CENTER);
		top.setPadding(new Insets(20, 10, 20, 10));
		top.setStyle("-fx-background-color: #B6B6B6;");
		mainLayout.setTop(top);
		
		// Add the Timer here. Set it to the left of mainLayout
		timer = new Timer(view);
        mainLayout.setRight(timer.getGuiVbox());
		// Timer Ends
		
		// Log Buttons
        logAction = new LogActionListener(view);
		Button createLog = new Button("Open Logs");
		createLog.addEventHandler(MouseEvent.MOUSE_CLICKED, logAction);
		createLog.setFont(new Font(15));
		createLog.setPrefSize(100, 30);
		createLog.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		HBox logButtons = new HBox(createLog);
		logButtons.setSpacing(10);
		logButtons.setAlignment(Pos.CENTER);
		
		// Main Board TextArea
		boardText = new Label(view.controller.getBoardString());
		boardText.setFont(new Font(30));
		VBox boardTextContainer = new VBox(boardText);
		boardTextContainer.setAlignment(Pos.TOP_CENTER);
		
		enterMove = new Label("[" + view.controller.getTurn() + "] Enter a Move:");
		Label fromLabel = new Label("From: ");
		fromLabel.setFont(new Font(20));
		Label toLabel = new Label("To: ");
		toLabel.setFont(new Font(20));
		enterMove.setFont(new Font(20));
		TextField from = new TextField();
		TextField to = new TextField();
		from.setMaxWidth(80);
		to.setMaxWidth(80);
		
		SubmitMoveActionListener action = new SubmitMoveActionListener(view, from, to);
		Button submit = new Button("Submit Move");
		submit.addEventHandler(MouseEvent.MOUSE_CLICKED, action);
		submit.setFont(new Font(15));
		submit.setPrefSize(120, 30);
		submit.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		HBox textFields = new HBox(fromLabel, from, toLabel, to);
		textFields.setSpacing(10);
		textFields.setAlignment(Pos.CENTER_RIGHT);
		textFields.setPadding(new Insets(0, 140, 0, 0));
		VBox submissionMove = new VBox(enterMove, textFields, submit);
		submissionMove.setSpacing(10);
		submissionMove.setAlignment(Pos.CENTER);
		
		VBox textAreaLogButtons = new VBox(logButtons, boardTextContainer, submissionMove);
		textAreaLogButtons.setSpacing(20);
		textAreaLogButtons.setAlignment(Pos.CENTER_LEFT);
		textAreaLogButtons.setPadding(new Insets(20));
		textAreaLogButtons.setStyle("-fx-background-color: white;");
		mainLayout.setCenter(textAreaLogButtons);

		// Other bottom buttons
		BottomButtonsActionListener bottomButtonAction = new BottomButtonsActionListener(this.view);
		
		Button newGame = new Button("New Game");
		newGame.setFont(new Font(15));
		newGame.setPrefSize(100, 30);
		newGame.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		newGame.addEventHandler(MouseEvent.MOUSE_CLICKED, bottomButtonAction);
		// Added game scene buttons
		Button saveGame = new Button("Save Game");
		saveGame.setFont(new Font(15));
		saveGame.setPrefSize(100, 30);
		saveGame.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		saveGame.addEventHandler(MouseEvent.MOUSE_CLICKED, bottomButtonAction);
		
		
		Button gameSettings = new Button("Game Settings");
		gameSettings.setFont(new Font(15));
		gameSettings.setPrefSize(150, 30);
		gameSettings.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		gameSettings.addEventHandler(MouseEvent.MOUSE_CLICKED, bottomButtonAction);
		
		HBox bottomButtons = new HBox(newGame, saveGame, gameSettings);
		bottomButtons.setSpacing(20);
		bottomButtons.setAlignment(Pos.CENTER);
		bottomButtons.setPadding(new Insets(20));
		mainLayout.setBottom(bottomButtons);
		
		Scene gameScene = new Scene(mainLayout, 850, 750);
		view.mainStage.setScene(gameScene);
	}
	
	protected void setBoard(String board) {
		boardText.setText(board);
	}
	
	protected void setTurnLabel() {
		enterMove.setText("[" + view.controller.getTurn() + "] Enter a Move:");
	}
	
	protected void setGameEndedLabel() {
		enterMove.setText("Winner: " + view.controller.getWinner() + "!");
	}
}
