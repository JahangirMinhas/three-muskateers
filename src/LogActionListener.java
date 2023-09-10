package assignment1;


import java.awt.Insets;
import java.io.File;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LogActionListener implements EventHandler<MouseEvent>{
	private MainView view;
	
	private Label myLabel;
	
	ListView<String> myListView = new ListView<>();
	
	
	public LogActionListener(MainView view) {
		this.view = view;
		this.myListView = new ListView<>();
		
		
	}
	
	@Override
	public void handle(MouseEvent e) { // Creating and adding all the listeners to the buttons in the moves log window
		Button clicked = (Button) e.getSource();
		if(clicked.getText() == "Open Logs") {
			BorderPane bp = new BorderPane();
			Label label = new Label();
			label.setText("Moves Log");
			label.setFont(new Font(20));
			label.setTextFill(Color.BLACK);
			label.setFont(Font.font ("impact", 30));
			
			Scene sc = new Scene(bp, 300, 300);
			Stage logStage = new Stage();
			Button loadboard = new Button("LoadBoard");
			Button saveLog = new Button("SaveLog");
			Button saveLogs = new Button("SaveLogs");
			
			logStage.setScene(sc);
			VBox box = new VBox(label);
			HBox buttonbox = new HBox(loadboard, saveLog, saveLogs);
			
			box.setAlignment(Pos.CENTER);
			box.setStyle("-fx-background-color: #B6B6B6;");
			loadboard.setAlignment(Pos.CENTER);
			LoadLogListener loadLL = new LoadLogListener(this.view, this.myListView);
			SaveLogs slogs = new SaveLogs(this.view);
			SaveLog slog = new SaveLog(this.view, this.myListView);
		
			saveLog.addEventFilter(MouseEvent.MOUSE_CLICKED, slog);
			
			saveLogs.addEventHandler(MouseEvent.MOUSE_CLICKED, slogs);
			loadboard.addEventHandler(MouseEvent.MOUSE_CLICKED, loadLL);
			buttonbox.setAlignment(Pos.CENTER);
			buttonbox.setSpacing(10);
			this.view.controller.updateBoard();
			bp.setPrefSize(120, 30);
			bp.setStyle("-fx-background-color: white; -fx-text-fill: white;");

			bp.setBottom(buttonbox);
			bp.setTop(box);
			bp.setCenter(myListView);
			logStage.show();
		}
	}
	
	public void getMovesBoard(ListView<String> boardsList) {
		
	
		this.view.controller.model.getBoard().loadBoard("MovesBoard/" + boardsList.getSelectionModel().getSelectedItem());
		
		
		
	}
	

	public void addBoards(Log log) {
		
		myListView.getItems().add(log.getName());
		
	}

	
}
