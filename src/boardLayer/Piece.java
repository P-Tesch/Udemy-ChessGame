package boardLayer;

public class Piece {
	
	private Board board;
	private Position position;
	
	public Piece(Board board) {
		this.board = board;
	}

	protected Board getBoard() {
		return board;
	}
	
	protected void setPosition(Position position) {
		this.position = position;
	}
	
	protected Position getPosition() {
		return this.position;
	}
}
