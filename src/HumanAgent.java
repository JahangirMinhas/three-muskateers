package assignment1;

import java.util.Scanner;


public class HumanAgent extends Agent implements Observer{

    public HumanAgent(Board board) {
        super(board);
    }

    /**
     * Asks the human for a move with from and to coordinates and makes sure its valid.
     * Create a Move object with the chosen fromCell and toCell
     * @return the valid human inputted Move
     */
    @Override
    public Move getMove() { // TODO
        return null;
    }
    
    public void update(String message, Timer timer) {
		timer.timerLog.setText(timer.timerLog.getText() + "\n" + message);
	}	
}
