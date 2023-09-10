package assignment1;

public class LogBuilder implements Builder{
	
	private Log log;
	
	
	@Override
	public void BuildLog(String name, Board board, Move move) {
		this.log = new Log();
		this.log.setName(name);
		this.log.setBoard(board);
		this.log.setTime("");
		this.log.setMove(move);
		
	}

	@Override
	public void BuildLogTime(String name, Board board, String time, Move move) {
		this.log = new Log();
		this.log.setName(name);
		this.log.setBoard(board);
		this.log.setTime(time);
		this.log.setMove(move);
		
	}
	
	public Log getLog() {
		return this.log;
	}


	
	
}
