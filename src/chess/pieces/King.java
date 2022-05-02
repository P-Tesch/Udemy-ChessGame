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
		ChessPiece piece = (ChessPiece) this.getBoard().piece(position);
		return this.getBoard().positionExists(position) && (piece == null || piece.getColor() != this.getColor());
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] possibleMoves = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];
		Position kingPosition = this.getPosition();
		
		//Clockwise
		possibleMoves[kingPosition.getRow()][kingPosition.getColumn() - 1] = canMove(new Position(kingPosition.getRow(), kingPosition.getColumn() - 1));
		possibleMoves[kingPosition.getRow() + 1][kingPosition.getColumn() - 1] = canMove(new Position(kingPosition.getRow() + 1, kingPosition.getColumn() - 1));
		possibleMoves[kingPosition.getRow() + 1][kingPosition.getColumn()] = canMove(new Position(kingPosition.getRow() + 1, kingPosition.getColumn()));
		possibleMoves[kingPosition.getRow() + 1][kingPosition.getColumn() + 1] = canMove(new Position(kingPosition.getRow() + 1, kingPosition.getColumn() + 1));
		possibleMoves[kingPosition.getRow()][kingPosition.getColumn() + 1] = canMove(new Position(kingPosition.getRow(), kingPosition.getColumn() + 1));
		possibleMoves[kingPosition.getRow() - 1][kingPosition.getColumn() + 1] = canMove(new Position(kingPosition.getRow() - 1, kingPosition.getColumn() + 1));
		possibleMoves[kingPosition.getRow() - 1][kingPosition.getColumn()] = canMove(new Position(kingPosition.getRow() - 1, kingPosition.getColumn()));
		possibleMoves[kingPosition.getRow() - 1][kingPosition.getColumn() - 1] = canMove(new Position(kingPosition.getRow() - 1, kingPosition.getColumn() - 1));
		
		return possibleMoves;
	}

	@Override
	public String toString() {
		return "K";
	}
}
