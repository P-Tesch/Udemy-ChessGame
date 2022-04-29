package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.enums.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] possibleMoves = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];
		
		
		//Up
		for (int i = this.getPosition().getRow() - 1; i >= 0; i--) {
			Position position = new Position(i, this.getPosition().getColumn());
			ChessPiece piece = (ChessPiece) this.getBoard().piece(position);
			if (piece == null) {
				possibleMoves[i][this.getPosition().getColumn()] = true;
			}
			else if (this.isThereOpponentPiece(position)) {
				possibleMoves[i][this.getPosition().getColumn()] = true;
				break;
			}
			else {
				break;
			}
		}
		
		//Right
		for (int i = this.getPosition().getColumn() + 1; i < this.getBoard().getColumns(); i++) {
			Position position = new Position(this.getPosition().getRow(), i);
			ChessPiece piece = (ChessPiece) this.getBoard().piece(position);
			if (piece == null) {
				possibleMoves[this.getPosition().getRow()][i] = true;
			}
			else if (this.isThereOpponentPiece(position)) {
				possibleMoves[this.getPosition().getRow()][i] = true;
				break;
			}
			else {
				break;
			}
		}
		
		//Down
		for (int i = this.getPosition().getRow() + 1; i < this.getBoard().getRows(); i++) {
			Position position = new Position(i, this.getPosition().getColumn());
			ChessPiece piece = (ChessPiece) this.getBoard().piece(position);
			if (piece == null) {
				possibleMoves[i][this.getPosition().getColumn()] = true;
			}
			else if (this.isThereOpponentPiece(position)) {
				possibleMoves[i][this.getPosition().getColumn()] = true;
				break;
			}
			else {
				break;
			}
		}
		
		//Left
		for (int i = this.getPosition().getColumn() - 1; i >= 0; i--) {
			Position position = new Position(this.getPosition().getRow(), i);
			ChessPiece piece = (ChessPiece) this.getBoard().piece(position);
			if (piece == null) {
				possibleMoves[this.getPosition().getRow()][i] = true;
			}
			else if (this.isThereOpponentPiece(position)) {
				possibleMoves[this.getPosition().getRow()][i] = true;
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
		return "R";
	}
}
