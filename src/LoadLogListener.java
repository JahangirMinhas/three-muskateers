package assignment1;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class LoadLogListener implements EventHandler<MouseEvent>{
	private MainView view;
	private ListView<String> moves;
	
	public LoadLogListener(MainView view, ListView<String> moves) {
		this.view = view;
		this.moves = moves;
	}
	
	@Override
	public void handle(MouseEvent e) {
	   this.view.controller.setBoard(this.view.controller.getBoards().get(this.moves.getSelectionModel().getSelectedIndex()));
	   this.view.controller.updateBoard();

		
		
	}
}
