package assignment1;

public class Log {
	private String time;
	private String name;
	private Board board;
	private Move move;
	
	public void setTime(String time) {
		this.time = time;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public void setMove(Move move) {
		this.move = move;
	}
	
	public String getName() {
		return this.name;
	}
	public String getTime() {
		return this.time;
	}
	public Board getBoard() {
		return this.board;
	}
	public void saveBoard() {
		System.out.println(this.name);
	}
}
