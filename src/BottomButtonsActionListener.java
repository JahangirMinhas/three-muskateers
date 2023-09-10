package assignment1;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BottomButtonsActionListener implements EventHandler<MouseEvent>{
	private MainView view;
	Stage settingStage;

	public BottomButtonsActionListener(MainView view) {
		this.view = view;
	}
	
	@Override
	public void handle(MouseEvent e) {
		Button clicked = (Button) e.getSource();
		if(clicked.getText() == "Game Settings"){	
			SettingsActionListener action = new SettingsActionListener(this.view);

			// Time Buttons
			Label chooseCountdown = new Label("Choose a timer countdown:");
			chooseCountdown.setFont(new Font(20));
			
			Button tenSeconds = new Button("10 Seconds");
			tenSeconds.setStyle("-fx-background-color: black; -fx-text-fill: white;");
			tenSeconds.setPrefSize(150, 70);
			tenSeconds.setFont(new Font(15));
			tenSeconds.addEventHandler(MouseEvent.MOUSE_CLICKED, action);
			
			Button fifteenSeconds = new Button("15 Seconds");
			fifteenSeconds.setStyle("-fx-background-color: black; -fx-text-fill: white;");
			fifteenSeconds.setPrefSize(150, 70);
			fifteenSeconds.setFont(new Font(15));
			fifteenSeconds.addEventHandler(MouseEvent.MOUSE_CLICKED, action);
			
			Button twentySeconds = new Button("20 Seconds");
			twentySeconds.setStyle("-fx-background-color: black; -fx-text-fill: white;");
			twentySeconds.setPrefSize(150, 70);
			twentySeconds.setFont(new Font(15));
			twentySeconds.addEventHandler(MouseEvent.MOUSE_CLICKED, action);
			
			// HBox for Time Buttons
			HBox timeButtons = new HBox(tenSeconds, fifteenSeconds, twentySeconds);
			timeButtons.setSpacing(20);
			timeButtons.setAlignment(Pos.CENTER);
			
			VBox timeButtonsLabel = new VBox(chooseCountdown, timeButtons);
			timeButtonsLabel.setSpacing(20);
			timeButtonsLabel.setAlignment(Pos.CENTER);
			
			// Move Behavior Buttons
			Label chooseMoveBehavior = new Label("Choose a Move Behavior:");
			chooseMoveBehavior.setFont(new Font(20));

			Button nomalButtons = new Button("Normal Behavior");
			nomalButtons.setStyle("-fx-background-color: black; -fx-text-fill: white;");
			nomalButtons.setPrefSize(150, 70);
			nomalButtons.setFont(new Font(15));
			nomalButtons.addEventHandler(MouseEvent.MOUSE_CLICKED, action);
			
			Button diagonalButton = new Button("Diagonal Behavior");
			diagonalButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
			diagonalButton.setPrefSize(150, 70);
			diagonalButton.setFont(new Font(15));
			diagonalButton.addEventHandler(MouseEvent.MOUSE_CLICKED, action);
			
			// Hbox for Move Buttons
			HBox moveButtons = new HBox(nomalButtons, diagonalButton);
			moveButtons.setSpacing(20);
			moveButtons.setAlignment(Pos.CENTER);
			
			VBox moveButtonsLabel = new VBox(chooseMoveBehavior, moveButtons);
			moveButtonsLabel.setSpacing(20);
			moveButtonsLabel.setAlignment(Pos.CENTER);
			
			// VBox for master layout
			VBox master = new VBox(timeButtonsLabel, moveButtonsLabel);
			master.setSpacing(20);
			master.setAlignment(Pos.CENTER);
			master.setPadding(new Insets(20));
			
			// Setting up the stage
			settingStage = new Stage();
			settingStage.setTitle("Game Settings");
			settingStage.setScene(new Scene(master));
			settingStage.show();
		}else if(clicked.getText() == "Save Game"){
			view.controller.saveBoard();
		}else if(clicked.getText() == "New Game"){
			view.controller.stopGame();
			view.controller.start();
		}
	}
}
