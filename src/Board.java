package assignment1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import assignment1.Piece.Type;


import java.util.ArrayList;

public class Board {
    public int size = 5;

    // 2D Array of Cells for representation of the game board
    public final Cell[][] board = new Cell[size][size];
    
    private File boardFile;
    private Piece.Type turn;
    private Piece.Type winner;
    private MoveBehavior moveBehavior = new MoveNormally();

    /**
     * Create a Board with the current player turn set.
     */
    public Board() {
        this.boardFile = new File("boards/Starter.txt");
        this.loadBoard(this.boardFile.getPath());
    }

    /**
     * Create a Board with the current player turn set and a specified board.
     * @param boardFilePath The path to the board file to import (e.g. "Boards/Starter.txt")
     */
    public Board(String boardFilePath) {
        this.loadBoard(boardFilePath);
    }
    

    /**
     * Creates a Board copy of the given board.
     * @param board Board to copy
     */
    public Board(Board board) {
        this.size = board.size;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                this.board[row][col] = new Cell(board.board[row][col]);
            }
        }
        this.turn = board.turn;
        this.winner = board.winner;
    }
    /**
     * Setter for moveBehavior
     * * @param moveBehavior to set this.moveBehavior
     */
    public void setMoveBehavior(MoveBehavior moveBehavior) {
    	this.moveBehavior = moveBehavior;
    }
    /**
     * Getter for moveBehavior
     * @return the this.moveBehavior
     */
    public MoveBehavior getMoveBehavior() {
    	return this.moveBehavior;
    }

    /**
     * @return the Piece.Type (Muskeeteer or Guard) of the current turn
     */
    public Piece.Type getTurn() {
        return turn;
    }
    
    /**
     * 
     * Helper function to set the current turn for the board.
     */
    public void setTurn(Piece.Type type) {
    	turn = type;
    }

    /**
     * Get the cell located on the board at the given coordinate.
     * @param coordinate Coordinate to find the cell
     * @return Cell that is located at the given coordinate
     */
    public Cell getCell(Coordinate coordinate) { // TODO
        return this.board[coordinate.row][coordinate.col];
    }

    /**
     * @return the game winner Piece.Type (Muskeeteer or Guard) if there is one otherwise null
     */
    public Piece.Type getWinner() {
        return winner;
    }

    /**
     * Gets all the Musketeer cells on the board.
     * @return List of cells
     */
    public List<Cell> getMusketeerCells() { // TODO
    	List<Cell> lst = new ArrayList<Cell>();
    	for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
            	Coordinate cor = new Coordinate(row, col);
            	//if (this.getCell(cor).getPiece().getType() == Type.MUSKETEER) {
            	if (this.getCell(cor).hasPiece() && this.getCell(cor).getPiece().getType() == Type.MUSKETEER) {
            		lst.add(this.getCell(cor));
            	} 
            }
        }
    	return lst;
    }

    /**
     * Gets all the Guard cells on the board.
     * @return List of cells
     */
    public List<Cell> getGuardCells() { // TODO
    	List<Cell> lst = new ArrayList<Cell>();
    	for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
            	Coordinate cor = new Coordinate(row, col);
            	//if (this.getCell(cor).getPiece().getType() == Type.GUARD) {
            	if (this.getCell(cor).hasPiece() && this.getCell(cor).getPiece().getType() == Type.GUARD) {
            		lst.add(this.getCell(cor));
            	} 
            }
        }
    	return lst;
    }

    /**
     * Executes the given move on the board and changes turns at the end of the method.
     * @param move a valid move
     */
    public void move(Move move) { // TODO
    	if (move.fromCell.hasPiece() && move.fromCell.getPiece().getType() == this.getTurn() && this.isValidMove(move)) {
    	//if (this.isValidMove(move)) {
    		Piece a = move.fromCell.getPiece();
    		int row2 = move.toCell.getCoordinate().row;
            int row1 = move.fromCell.getCoordinate().row;
            int col2 = move.toCell.getCoordinate().col;
            int col1 = move.fromCell.getCoordinate().col;
            //this.board[row1][col1].setPiece(new NullPiece());
            this.board[row1][col1].removePiece();
            this.board[row2][col2].setPiece(a);
            if(this.getTurn() == Type.MUSKETEER) {
            	this.setTurn(Type.GUARD);
            }else {
            	this.setTurn(Type.MUSKETEER);
            }
    	}
    }

    /**
     * Undo the move given.
     * @param move Copy of a move that was done and needs to be undone. The move copy has the correct piece info in the
     *             from and to cell fields. Changes turns at the end of the method.
     */
    public void undoMove(Move move) { // TODO
    	
    	Piece originalPiece = move.fromCell.getPiece();
    	Piece restorePiece = move.toCell.getPiece();
    	int fromRow = move.fromCell.getCoordinate().row;
    	int fromCol = move.fromCell.getCoordinate().col;
    	int toRow = move.toCell.getCoordinate().row;
    	int toCol = move.toCell.getCoordinate().col;
    	board[fromRow][fromCol].setPiece(originalPiece);
    	board[toRow][toCol].setPiece(restorePiece);
    	if(this.getTurn() == Type.MUSKETEER) {
    		this.setTurn(Type.GUARD);
    	} else {
    		this.setTurn(Type.MUSKETEER);
    	}
    }

    /**
     * Checks if the given move is valid. Things to check:
     * (1) the toCell is next to the fromCell
     * (2) the fromCell piece can move onto the toCell piece.
     * @param move a move
     * @return     True, if the move is valid, false otherwise
     */
    public Boolean isValidMove(Move move) { // TODO
        return this.getMoveBehavior().valid(this, move);
    }

    /**
     * Get all the possible cells that have pieces that can be moved this turn.
     * @return      Cells that can be moved from the given cells
     */
    public List<Cell> getPossibleCells() { // TODO
    	List<Cell> pt;
    	List<Cell> lst = new ArrayList<Cell>();
    	if(this.getTurn() == Type.MUSKETEER) {
    		pt = this.getMusketeerCells();
    		for(Cell possibleCell: pt) {
    			if (this.getPossibleDestinations(possibleCell).size() > 0) {
    				lst.add(possibleCell);
    			}
    		}
    	} else if(this.getTurn() == Type.GUARD) {
    		pt = this.getGuardCells();
    		for(Cell possibleCell: pt) {
    			if (this.getPossibleDestinations(possibleCell).size() > 0) {
    				lst.add(possibleCell);
    			}
    		}
    	}
        return lst;
    }

    /**
     * Get all the possible cell destinations that is possible to move to from the fromCell.
     * @param fromCell The cell that has the piece that is going to be moved
     * @return List of cells that are possible to get to
     */
    public List<Cell> getPossibleDestinations(Cell fromCell) { // TODO
    	return this.getMoveBehavior().possibleDestintions(this, fromCell);
    }

    /**
     * Get all the possible moves that can be made this turn.
     * @return List of moves that can be made this turn
     */
    public List<Move> getPossibleMoves() { // TODO
    	List<Cell> pt;
    	List<Move> moves =  new ArrayList<Move>();
    	
    	pt = this.getPossibleCells();
    	for(Cell from: pt) {
			for(Cell destination: this.getPossibleDestinations(from)) {
				moves.add(new Move(from, destination));
			}
		}
        return moves;
    }

    /**
     * Checks if the game is over and sets the winner if there is one.
     * @return True, if the game is over, false otherwise.
     */
    public boolean isGameOver() { // TODO
    	List<Cell> musketeers= this.getMusketeerCells();
    	Boolean flag = true;
    	int r = musketeers.get(0).getCoordinate().row;
    	int c = musketeers.get(0).getCoordinate().col;
    	for (int i = 1; i < musketeers.size(); i++) {
    		if(r != musketeers.get(i).getCoordinate().row) {
    			flag = false;
    		}
    	}
    	if (flag) {
    		this.winner = Type.GUARD;
        	return true;
    	}
    	
    	flag = true;
    	for (int k = 1; k < musketeers.size(); k++) {
    		if(c != musketeers.get(k).getCoordinate().col) {
    			flag = false;
    		}
    	}
    	if (flag) {
    		this.winner = Type.GUARD;
        	return true;
    	}
    	if(this.getTurn() == Type.MUSKETEER) {
	    	for (Cell mus: musketeers) {
	    		if (this.getPossibleDestinations(mus).size() > 0) {
	    			return false;
	    		}
	    	}
	    	this.winner = Type.MUSKETEER;
	    	return true;
    	}
    	return false;
    }

    /**
     * Saves the current board state to the boards directory
     */
    public void saveBoard() {
        String filePath = String.format("boards/%s.txt",
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        File file = new File(filePath);

        try {
            file.createNewFile();
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write(turn.getType() + "\n");
            for (Cell[] row: board) {
                StringBuilder line = new StringBuilder();
                for (Cell cell: row) {
                    if (cell.getPiece() != null) {
                        line.append(cell.getPiece().getSymbol());
                    } else {
                        line.append("_");
                    }
                    line.append(" ");
                }
                writer.write(line.toString().strip() + "\n");
            }
            writer.close();
            System.out.printf("Saved board to %s.\n", filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("Failed to save board to %s.\n", filePath);
        }
    }
    public void saveLogs(ArrayList<Log> logs) {
        String filePath = String.format("Logs/%s.txt",
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        File file = new File(filePath);

        try {
            file.createNewFile();
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write(turn.getType() + "\n");
            writer.write("Behavior: " + this.getMoveBehavior().toString() + "\n");
            for (Cell[] row: board) {
                StringBuilder line = new StringBuilder();
                for (Cell cell: row) {
                    if (cell.getPiece() != null) {
                        line.append(cell.getPiece().getSymbol());
                    } else {
                        line.append("_");
                    }
                    line.append(" ");
                }
                writer.write(line.toString().strip() + "\n");
            }
            
            for(Log log: logs) {
            	
            	writer.write(log.getName() + "\n");
            	
            }
            writer.close();
            System.out.printf("Saved board to %s.\n", filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("Failed to save board to %s.\n", filePath);
        }
    }
    
    public void saveFile(Move m) {
        String filePath = String.format("MovesBoard/%s.txt",
                new SimpleDateFormat("{}").format(m.toString()));
        File file = new File(filePath);

        try {
            file.createNewFile();
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write(turn.getType() + "\n");
            for (Cell[] row: board) {
                StringBuilder line = new StringBuilder();
                for (Cell cell: row) {
                    if (cell.getPiece() != null) {
                        line.append(cell.getPiece().getSymbol());
                    } else {
                        line.append("_");
                    }
                    line.append(" ");
                }
                writer.write(line.toString().strip() + "\n");
            }
            writer.close();
            System.out.printf("Saved board to %s.\n", filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("Failed to save board to %s.\n", filePath);
        }
    }

    @Override
    public String toString() {
        StringBuilder boardStr = new StringBuilder("   | A   B  C   D  E\n");
        boardStr.append("--+--------------\n");
        for (int i = 0; i < size; i++) {
            boardStr.append(i + 1).append(" | ");
            for (int j = 0; j < size; j++) {
                Cell cell = board[i][j];
                if (!cell.hasPiece()) {
                	boardStr.append(cell).append(" ");
                }
                boardStr.append(cell).append("  ");
            }
            boardStr.append("\n");
        }
        return boardStr.toString();
    }
    
    public Boolean onBoard(Coordinate coordinate) {
        return 0 <= coordinate.col && coordinate.col < this.size &&
                0 <= coordinate.row && coordinate.row < this.size;
    }
    
    /**
     * Returns the board file.
     */
    public File getBoardFile() {
    	return this.boardFile;
    }

    /**
     * Loads a board file from a file path.
     * @param filePath The path to the board file to load (e.g. "Boards/Starter.txt")
     */
    public void loadBoard(String filePath) {
        File file = new File(filePath);
        this.boardFile = file;
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.printf("File at %s not found.", filePath);
            System.exit(1);
        }

        turn = Piece.Type.valueOf(scanner.nextLine().toUpperCase());

        int row = 0, col = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] pieces = line.trim().split(" ");
            for (String piece: pieces) {
                Cell cell = new Cell(new Coordinate(row, col));
                switch (piece) {
                    case "O" -> cell.setPiece(new Guard());
                    case "X" -> cell.setPiece(new Musketeer());
                    default -> cell.setPiece(null);
                }
                this.board[row][col] = cell;
                col += 1;
            }
            col = 0;
            row += 1;
        }
        scanner.close();
        System.out.printf("Loaded board from %s.\n", filePath);
    }
}
