package chess;

import board.Board;
import board.Position;
import chess.enums.Color;
import chess.pieces.Rook;

public class ChessMatch {
	
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;
	private Board board;
	
	public ChessMatch() {
		this.board = new Board(8, 8);
		this.initialSetup();
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Color currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public ChessPiece getEnPassantVulnerable() {
		return enPassantVulnerable;
	}

	public void setEnPassantVulnerable(ChessPiece enPassantVulnerable) {
		this.enPassantVulnerable = enPassantVulnerable;
	}

	public ChessPiece getPromoted() {
		return promoted;
	}

	public Board getBoard() {
		return board;
	}
	
	private void initialSetup() {
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
	}
	
	public ChessPiece[][] getPieces() {
		ChessPiece[][] pieces = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				pieces[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return pieces;
	}
}
