package chess;

import board.Board;
import chess.enums.Color;
import chess.pieces.King;
import chess.pieces.Rook;
import exceptions.ChessException;

public class ChessMatch {
	
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;
	private Board board;
	
	public ChessMatch() {
		this.board = new Board(8, 8);
		this.turn = 1;
		this.currentPlayer = Color.WHITE;
		this.initialSetup();
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
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
	
	private void nextTurn() {
		this.turn++;
		this.currentPlayer = (this.currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private void placePiece(char column, int row, ChessPiece piece) {
		this.board.placePiece(piece, new ChessPosition(row, column).toPosition());
	}
	
	private void initialSetup() {
		this.placePiece('d', 4, new Rook(this.board, Color.WHITE));;
		this.placePiece('g', 4, new King(this.board, Color.WHITE));;
		this.placePiece('d', 7, new Rook(this.board, Color.BLACK));;
		//this.placePiece('c', 7, new King(this.board, Color.BLACK));;
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
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		this.validateSourcePosition(sourcePosition);
		this.validadeTargetPosition(sourcePosition, targetPosition);
		ChessPiece capturedPiece = (ChessPiece) this.board.removePiece(targetPosition.toPosition());
		this.board.placePiece(this.board.removePiece(sourcePosition.toPosition()), targetPosition.toPosition());;
		this.nextTurn();
		return capturedPiece;
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		this.validateSourcePosition(sourcePosition);
		return this.getBoard().piece(sourcePosition.toPosition()).possibleMoves();
	}
	
	private void validateSourcePosition(ChessPosition sourcePosition) {
		if (!this.board.thereIsAPiece(sourcePosition.toPosition())) {
			throw new ChessException("There is no piece in source position");
		}
		if (((ChessPiece)this.board.piece(sourcePosition.toPosition())).getColor() != this.currentPlayer) {
			throw new ChessException("This piece is not owned by the current player");
		}
		if (!this.board.piece(sourcePosition.toPosition()).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible move");
		}
	}
	
	private void validadeTargetPosition(ChessPosition sourcePosition, ChessPosition targetPosition) {
		if (!this.board.piece(sourcePosition.toPosition()).possibleMoves()[targetPosition.toPosition().getRow()][targetPosition.toPosition().getColumn()]) {
			throw new ChessException("Target Position is not valid");
		}
	}
}
