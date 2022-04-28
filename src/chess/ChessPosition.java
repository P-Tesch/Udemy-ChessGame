package chess;

import board.Position;
import exceptions.ChessException;

public class ChessPosition {
	
	private int row;
	private char column;
	
	public ChessPosition(int row, char column) {
		if (row < 1 || row > 8 || column < 'a' || column > 'h') {
			throw new ChessException("Error instantiating ChessPosition. Position must be from a1 to h8");
		}
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public char getColumn() {
		return column;
	}
	
	@Override
	public String toString() {
		// return "" + this.row + this.column;
		return String.valueOf(this.row) + String.valueOf(this.column);
	}
	
	protected Position toPosition() {
		// return new Position((8 - this.row), ((int) this.column - 97));
		return new Position((8 - this.row), ((int) this.column - 'a'));
	}
	
	protected ChessPosition fromPosition(Position position) {
		return new ChessPosition((8 - position.getRow()), (char) ('a' + position.getColumn()));
	}
}
