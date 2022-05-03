package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.enums.Color;

public class Bishop extends ChessPiece {

	public Bishop(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
boolean[][] possibleMoves = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];
		
		
		//Up - Right
		for (int i = this.getPosition().getRow() - 1, j = this.getPosition().getColumn() + 1; i >= 0 && j < this.getBoard().getColumns(); i--, j++) {
			Position position = new Position(i, j);
			ChessPiece piece = (ChessPiece) this.getBoard().piece(position);
			if (piece == null) {
				possibleMoves[i][j] = true;
			}
			else if (this.isThereOpponentPiece(position)) {
				possibleMoves[i][j] = true;
				break;
			}
			else {
				break;
			}
		}
		
		//Down - Right
		for (int i = this.getPosition().getRow() + 1, j = this.getPosition().getColumn() + 1; i < this.getBoard().getRows() && j < this.getBoard().getColumns(); i++, j++) {
			Position position = new Position(i, j);
			ChessPiece piece = (ChessPiece) this.getBoard().piece(position);
			if (piece == null) {
				possibleMoves[i][j] = true;
			}
			else if (this.isThereOpponentPiece(position)) {
				possibleMoves[i][j] = true;
				break;
			}
			else {
				break;
			}
		}
		
		//Down - Left
		for (int i = this.getPosition().getRow() + 1, j = this.getPosition().getColumn() - 1; i < this.getBoard().getRows() && j >= 0; i++, j--) {
			Position position = new Position(i, j);
			ChessPiece piece = (ChessPiece) this.getBoard().piece(position);
			if (piece == null) {
				possibleMoves[i][j] = true;
			}
			else if (this.isThereOpponentPiece(position)) {
				possibleMoves[i][j] = true;
				break;
			}
			else {
				break;
			}
		}
		
		//Up - Left
		for (int i = this.getPosition().getRow() - 1, j = this.getPosition().getColumn() - 1; i >= 0 && j >= 0; i--, j--) {
			Position position = new Position(i, j);
			ChessPiece piece = (ChessPiece) this.getBoard().piece(position);
			if (piece == null) {
				possibleMoves[i][j] = true;
			}
			else if (this.isThereOpponentPiece(position)) {
				possibleMoves[i][j] = true;
				break;
			}
			else {
				break;
			}
		}
		
		return possibleMoves;
	}
	
	@Override
	public String toString() {
		return "B";
	}

}
