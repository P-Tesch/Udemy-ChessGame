package chess.pieces;

import board.Board;
import chess.ChessPiece;
import chess.enums.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] possibleMoves = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];
		return possibleMoves;
	}

	@Override
	public String toString() {
		return "R";
	}
}
