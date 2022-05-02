package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.enums.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}
	
	private boolean canMove(Position position) {
		if (!this.getBoard().positionExists(position)) {
			return false;
		}
		return this.getBoard().positionExists(position) && (!this.getBoard().thereIsAPiece(position) || this.isThereOpponentPiece(position));
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] possibleMoves = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];
		Position kingPosition = this.getPosition();
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (this.canMove(new Position((kingPosition.getRow() + i), (kingPosition.getColumn() + j)))) {
					possibleMoves[kingPosition.getRow() + i][kingPosition.getColumn() + j] = true;
				}
			}
		}
		
		return possibleMoves;
	}

	@Override
	public String toString() {
		return "K";
	}
}
