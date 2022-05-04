package chess.pieces;

import java.util.stream.Collectors;

import board.Board;
import board.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.enums.Color;

public class King extends ChessPiece {
	
	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	private boolean canMove(Position position) {
		if (!this.getBoard().positionExists(position)) {
			return false;
		}
		return (!this.getBoard().thereIsAPiece(position) || this.isThereOpponentPiece(position));
	}
	
	private boolean testCheck(Position position) {
		for (ChessPiece piece : this.chessMatch.getPiecesOnTheBoard().stream().filter(x -> x.getColor() != this.getColor() && !(x instanceof King)).collect(Collectors.toList())) {
			if (piece.possibleMoves()[position.getRow()][position.getColumn()]) {
				return true;
			}
			
		}
		return false;
	}
	
	private boolean testCastlingSmall(Position position) {
		ChessPiece piece = (ChessPiece) this.chessMatch.getBoard().piece(position);
		return piece != null &&
				piece instanceof Rook &&
				piece.getColor() == this.getColor() &&
				piece.getMoveCount() == 0 &&
				!this.chessMatch.isCheck() &&
				this.getBoard().piece(this.getPosition().getRow(), this.getPosition().getColumn() + 1) == null &&
				this.getBoard().piece(this.getPosition().getRow(), this.getPosition().getColumn() + 2) == null &&
				!this.testCheck(new Position(this.getPosition().getRow(), this.getPosition().getColumn() + 1));
	}
	
	private boolean testCastlingLarge(Position position) {
		ChessPiece piece = (ChessPiece) this.chessMatch.getBoard().piece(position);
		return piece != null &&
				piece instanceof Rook &&
				piece.getColor() == this.getColor() &&
				piece.getMoveCount() == 0 &&
				!this.chessMatch.isCheck() &&
				this.getBoard().piece(this.getPosition().getRow(), this.getPosition().getColumn() - 1) == null &&
				this.getBoard().piece(this.getPosition().getRow(), this.getPosition().getColumn() - 2) == null &&
				this.getBoard().piece(this.getPosition().getRow(), this.getPosition().getColumn() - 3) == null &&
				!this.testCheck(new Position(this.getPosition().getRow(), this.getPosition().getColumn() - 1));
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] possibleMoves = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];
		Position kingPosition = this.getPosition();
		
		// Normal moves
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (this.canMove(new Position((kingPosition.getRow() + i), (kingPosition.getColumn() + j)))) {
					possibleMoves[kingPosition.getRow() + i][kingPosition.getColumn() + j] = true;
				}
			}
		}
		
		//Castling
		if (this.getMoveCount() == 0) {
			
		// Kingside
		possibleMoves[this.getPosition().getRow()][this.getPosition().getColumn() + 2] = this.testCastlingSmall(new Position(this.getPosition().getRow(), this.getPosition().getColumn() + 3));
		
		// Queenside
		possibleMoves[this.getPosition().getRow()][this.getPosition().getColumn() - 2] = this.testCastlingLarge(new Position(this.getPosition().getRow(), this.getPosition().getColumn() - 4));
		}
		
		return possibleMoves;
	}

	@Override
	public String toString() {
		return "K";
	}
}
