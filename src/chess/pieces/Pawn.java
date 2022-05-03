package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessPiece;
import chess.enums.Color;

public class Pawn extends ChessPiece{

	public Pawn(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		
		boolean[][] possibleMoves = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];
		
		if (this.getColor() == Color.WHITE) {
			if (this.getBoard().positionExists(new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn())) && !this.getBoard().thereIsAPiece(new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn()))) {
				possibleMoves[this.getPosition().getRow() - 1][this.getPosition().getColumn()] = true;
			}
			
			if (this.getMoveCount() == 0 && this.getBoard().positionExists(new Position(this.getPosition().getRow() - 2, this.getPosition().getColumn())) && !this.getBoard().thereIsAPiece(new Position(this.getPosition().getRow() - 2, this.getPosition().getColumn()))) {
				possibleMoves[this.getPosition().getRow() - 2][this.getPosition().getColumn()] = true;
			}
			
			if (this.getBoard().positionExists(new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn() - 1)) && this.isThereOpponentPiece(new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn() - 1))) {
				possibleMoves[this.getPosition().getRow() - 1][this.getPosition().getColumn() - 1] = true;
			}
			
			if (this.getBoard().positionExists(new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn() + 1)) && this.isThereOpponentPiece(new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn() + 1))) {
				possibleMoves[this.getPosition().getRow() - 1][this.getPosition().getColumn() + 1] = true;
			}
		}
		else {
			if (this.getBoard().positionExists(new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn())) && !this.getBoard().thereIsAPiece(new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn()))) {
				possibleMoves[this.getPosition().getRow() + 1][this.getPosition().getColumn()] = true;
			}
			
			if (this.getMoveCount() == 0 && this.getBoard().positionExists(new Position(this.getPosition().getRow() + 2, this.getPosition().getColumn())) && !this.getBoard().thereIsAPiece(new Position(this.getPosition().getRow() + 2, this.getPosition().getColumn()))) {
				possibleMoves[this.getPosition().getRow() + 2][this.getPosition().getColumn()] = true;
			}
			
			if (this.getBoard().positionExists(new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn() - 1)) && this.isThereOpponentPiece(new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn() - 1))) {
				possibleMoves[this.getPosition().getRow() + 1][this.getPosition().getColumn() - 1] = true;
			}
			
			if (this.getBoard().positionExists(new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn() + 1)) && this.isThereOpponentPiece(new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn() + 1))) {
				possibleMoves[this.getPosition().getRow() + 1][this.getPosition().getColumn() + 1] = true;
			}
		}
		
		return possibleMoves;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
