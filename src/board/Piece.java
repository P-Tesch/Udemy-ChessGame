package board;

public abstract class Piece {
	
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
	
	protected boolean possibleMove(Position position) {
		return this.possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] possibleMoves = this.possibleMoves();
		for (int i = 0; i < possibleMoves.length; i++) {
			for (int j = 0; j < possibleMoves[i].length; j++) {
				if (possibleMoves[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	public abstract boolean[][] possibleMoves();
}
