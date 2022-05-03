package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.enums.Color;

public class Knight extends ChessPiece {

	public Knight(Board board, Color color) {
		super(board, color);
	}
	
	private boolean canMove(Position position) {
		if (!this.getBoard().positionExists(position)) {
			return false;
		}
		return (!this.getBoard().thereIsAPiece(position) || this.isThereOpponentPiece(position));
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] possibleMoves = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];
		
		//Up - Left
		if (canMove(new Position(this.getPosition().getRow() - 2, this.getPosition().getColumn() - 1))) {
			possibleMoves[this.getPosition().getRow() - 2][this.getPosition().getColumn() - 1] = true;
		}
		
		//Up - Right
		if (canMove(new Position(this.getPosition().getRow() - 2, this.getPosition().getColumn() + 1))) {
			possibleMoves[this.getPosition().getRow() - 2][this.getPosition().getColumn() + 1] = true;
		}
		
		//Right - Up
		if (canMove(new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn() + 2))) {
			possibleMoves[this.getPosition().getRow() - 1][this.getPosition().getColumn() + 2] = true;
		}
		
		//Right - Down
		if (canMove(new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn() + 2))) {
			possibleMoves[this.getPosition().getRow() + 1][this.getPosition().getColumn() + 2] = true;
		}
		
		//Down - Right
		if (canMove(new Position(this.getPosition().getRow() + 2, this.getPosition().getColumn() + 1))) {
			possibleMoves[this.getPosition().getRow() + 2][this.getPosition().getColumn() + 1] = true;
		}
		
		//Down - Left
		if (canMove(new Position(this.getPosition().getRow() + 2, this.getPosition().getColumn() - 1))) {
			possibleMoves[this.getPosition().getRow() + 2][this.getPosition().getColumn() - 1] = true;
		}
		
		//Left - Down
		if (canMove(new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn() - 2))) {
			possibleMoves[this.getPosition().getRow() + 1][this.getPosition().getColumn() - 2] = true;
		}
		
		//Left - Up
		if (canMove(new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn() - 2))) {
			possibleMoves[this.getPosition().getRow() - 1][this.getPosition().getColumn() - 2] = true;
		}
				
		

		return possibleMoves;
	}
	
	@Override
	public String toString() {
		return "N";
	}

}
