package assignment1;

import java.io.File;
import java.io.FilenameFilter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class GameModePanel {

	private MainView view;
	private Button hH;
	private Button hR;
	private Button hG;

	public GameModePanel(MainView view) {
		this.view = view;
	}
	
	public void start() {
		displayUI();
	}

	private void displayUI() {
		ModePanelActionListener action = new ModePanelActionListener(view);
		Label chooseMode = new Label("Choose a Game Mode");
		chooseMode.setFont(new Font(30));
		chooseMode.setPadding(new Insets(20, 0, 20, 0));
		
		hH = new Button("Human vs Human");
		hH.setFont(new Font(15));
		hH.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		hH.setPrefSize(250, 70);
		hH.addEventHandler(MouseEvent.MOUSE_CLICKED, action);
		
		hR = new Button("Human vs Computer (Random)");
		hR.setFont(new Font(15));
		hR.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		hR.setPrefSize(250, 70);
		hR.addEventHandler(MouseEvent.MOUSE_CLICKED, action);
		
		hG = new Button("Human vs Computer (Greedy)");
		hG.setFont(new Font(15));
		hG.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		hG.setPrefSize(250, 70);
		hG.addEventHandler(MouseEvent.MOUSE_CLICKED, action);
		
		VBox buttons = new VBox(chooseMode, hH, hR, hG);
		buttons.setSpacing(20);
		buttons.setAlignment(Pos.TOP_CENTER);
		buttons.setStyle("-fx-background-color: white;");
		view.modeBorder.setCenter(buttons);
		
		showListView();
	}
	
	protected void setButttons(boolean enable) {
		if(enable) {
			hH.setDisable(false);
			hR.setDisable(false);
			hG.setDisable(false);
		}else {
			hH.setDisable(true);
			hR.setDisable(true);
			hG.setDisable(true);
		}
	}

	private void showListView() {
		Label currentBoardLabel = new Label("Selected Board: " + view.controller.model.getBoard().getBoardFile().getName());
		
		ComboBox<String> boardsList = new ComboBox<String>();
		
		int selectedIndex = getFiles(boardsList);
        boardsList.getSelectionModel().select(selectedIndex);
        
		Button loadBoard = new Button("Load Board");
		loadBoard.setStyle("-fx-background-color: black; -fx-text-fill: white;");
		loadBoard.setOnAction(e -> selectBoard(currentBoardLabel, boardsList));

		VBox labelList = new VBox(currentBoardLabel, boardsList);
		labelList.setPadding(new Insets(20, 0, 30, 0));
		labelList.setAlignment(Pos.CENTER);
		
		HBox bottom = new HBox(labelList, loadBoard);
		bottom.setAlignment(Pos.CENTER);
		bottom.setSpacing(10);
		
		view.modeBorder.setBottom(bottom);
	}
	
	private int getFiles(ComboBox<String> boardsList) { // TODO
    	File directoryPath = new File("boards");
    	File[] files = directoryPath.listFiles(new FilenameFilter() 
		{
			public boolean accept(File dir, String name) 
			{
				return name.endsWith(".txt");
			}
		});
    	for(File file: files) {
    		boardsList.getItems().add(file.getName());
    	}
        return boardsList.getItems().indexOf("Starter.txt");
    }
	
	private void selectBoard(Label currentBoardLabel, ComboBox<String> boardsList) { // TODO
    	File selected = null;
    	File directoryPath = new File("boards");
    	File[] files = directoryPath.listFiles(new FilenameFilter() 
		{
			public boolean accept(File dir, String name) 
			{
				return name.endsWith(".txt");
			}
		});
    	for(File file: files) {
    		if(file.getName().equals(boardsList.getSelectionModel().getSelectedItem())) {
    			selected = file;
    		}
    	}
    	view.controller.model.getBoard().loadBoard(selected.getPath());
    	currentBoardLabel.setText("Selected Board: " + boardsList.getSelectionModel().getSelectedItem());
    }
}
