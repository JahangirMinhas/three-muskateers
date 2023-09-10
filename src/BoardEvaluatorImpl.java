package assignment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardEvaluatorImpl implements BoardEvaluator {

    /**
     * Calculates a score for the given Board
     * A higher score means the Musketeer is winning
     * A lower score means the Guard is winning
     * Add heuristics to generate a score given a Board
     * @param board Board to calculate the score of
     * @return int Score of the given Board
     */
    @Override
    public int evaluateBoard(Board board) { // TODO
        /*Add up these for the final return value:
         * +Difference of row placement for musketeers multiplied by 5(to bring the weighting up)
         * +Difference of column placement for musketeers multiplied by 5(to bring the weighting up)
         * +Possible destinations for each musketeer
         * I've decided to remove the fourth part of the algorithm, shown below.
         * -Number of guards divided by 5(to reduce the weighting/significance since its not as meaningful as the ones above)
         */
    	int deltaRow;
    	int deltaCol;
    	
    	//Removed: double numGuards;
    	int total;
    	
    	//calculate the difference avg for rows
    	List<Cell> musks = board.getMusketeerCells();
    	List<Integer> rows = new ArrayList();
    	for(Cell m1: musks) {
    		rows.add(m1.getCoordinate().row + 1);
    	}
    	//No sorting is needed since Board.getCoordinate()'s return value is already sorted (for rows).
    	
    	int deltaRow1 = (rows.get(2)  - rows.get(1));
    	int deltaRow2 = (rows.get(1) - rows.get(0));
    	/*Weighted each delta differently relative to their values
    	The weightings ensure that the more balanced board is picked, for example:
    	OOXOO should be picked over XOOOO
    	XOOOX                       XOOOX
    	since the minimum delta is higher in the first board example, it will give a higher value.
    	 */
    	
    	deltaRow = Math.min(deltaRow1, deltaRow2)*4 + Math.max(deltaRow1, deltaRow2) + (rows.get(2) - rows.get(0));
    	
    	//calculate the difference avg for cols
    	List<Integer> cols = new ArrayList();
    	for(Cell m2: musks) {
    		cols.add(m2.getCoordinate().col + 1);
    	}
    	//This time we need to sort.
    	Collections.sort(cols);
    	//Same logic as before applies
    	int deltaCol1 = (cols.get(2)  - cols.get(1));
    	int deltaCol2 = (cols.get(1) - cols.get(0));
    	deltaCol = Math.min(deltaCol1, deltaCol2)*4 + Math.max(deltaCol1, deltaCol2) + (cols.get(2) - cols.get(0));
    	
    	//calculate the avg possible destinations per musketeer
    	List<Cell> destinations = new ArrayList();
    	List<Move> moves =  new ArrayList<Move>();
    	int availableMoves = 0;
    	for(Cell m3: musks) {
    		availableMoves = availableMoves + board.getPossibleDestinations(m3).size();
		}
    	
    	/*At the start of the game, having more possible move is an advantage to musketeers
    	 * But as the game goes on, that changes. Afterall, the musketeers win when there is no moves available for them.
    	 * So while # guard cells are over 5, musketeers should look for a position with more available moves
    	 * But otherwise, they are better off chosing the option with less available moves
    	 */
    	if(deltaRow == 0 || deltaCol == 0) {
    		return 0;
    	}
    	if(board.getGuardCells().size() > 5) {
    		//The delta row and col are more important and should have a higher weighing
    		total = deltaRow*2 + deltaCol*2 + availableMoves;
    	} else {
    		total = deltaRow*2 + deltaCol*2 - availableMoves;
    	}
    	
    	return total;
    }
}
