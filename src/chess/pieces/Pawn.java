package chess.pieces;

import board.Board;
import board.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.enums.Color;

public class Pawn extends ChessPiece{
	
	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] possibleMoves() {
		
		boolean[][] possibleMoves = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];
		
		// Normal moves
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
		
		// En passant
		if (this.chessMatch.getEnPassantVulnerable() != null) {
			if (this.getColor() == Color.WHITE && this.getPosition().getRow() == 3 && this.getBoard().positionExists(new Position(this.getPosition().getRow(), this.getPosition().getColumn() - 1)) && this.getBoard().piece(this.getPosition().getRow(), this.getPosition().getColumn() - 1) == this.chessMatch.getEnPassantVulnerable()) {
				possibleMoves[this.getPosition().getRow() - 1][this.getPosition().getColumn() - 1] = true;
			}
			
			if (this.getColor() == Color.WHITE && this.getPosition().getRow() == 3 && this.getBoard().positionExists(new Position(this.getPosition().getRow(), this.getPosition().getColumn() + 1)) && this.getBoard().piece(this.getPosition().getRow(), this.getPosition().getColumn() + 1) == this.chessMatch.getEnPassantVulnerable()) {
				possibleMoves[this.getPosition().getRow() - 1][this.getPosition().getColumn() + 1] = true;
			}
			
			if (this.getColor() == Color.BLACK && this.getPosition().getRow() == 4 && this.getBoard().positionExists(new Position(this.getPosition().getRow(), this.getPosition().getColumn() - 1)) && this.getBoard().piece(this.getPosition().getRow(), this.getPosition().getColumn() - 1) == this.chessMatch.getEnPassantVulnerable()) {
				possibleMoves[this.getPosition().getRow() + 1][this.getPosition().getColumn() - 1] = true;
			}
			
			if (this.getColor() == Color.BLACK && this.getPosition().getRow() == 4 && this.getBoard().positionExists(new Position(this.getPosition().getRow(), this.getPosition().getColumn() + 1)) && this.getBoard().piece(this.getPosition().getRow(), this.getPosition().getColumn() + 1) == this.chessMatch.getEnPassantVulnerable()) {
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
