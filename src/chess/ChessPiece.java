package chess;

import board.Board;
import board.Piece;
import board.Position;
import chess.enums.Color;

public abstract class ChessPiece extends Piece{
	
	private Color color;
	private int moveCount;
	
	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(this.getPosition());
	}

	public Color getColor() {
		return color;
	}

	public int getMoveCount() {
		return moveCount;
	}
	
	protected void increaseMovecount() {
		this.moveCount++;
	}
	
	protected void decreaseMovecount() {
		this.moveCount--;
	}
	
	public boolean isThereOpponentPiece(Position position) {
		if (!this.getBoard().positionExists(position)) {
			return false;
		}
		ChessPiece piece = (ChessPiece) this.getBoard().piece(position);
		return piece != null && piece.getColor() != this.color;
	}
}
