package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import board.Board;
import chess.enums.Color;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;
import exceptions.ChessException;

public class ChessMatch {
	
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkmate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;
	private Board board;
	private List<ChessPiece> capturedPieces;
	private List<ChessPiece> piecesOnTheBoard;
	
	public ChessMatch() {
		this.board = new Board(8, 8);
		this.turn = 1;
		this.currentPlayer = Color.WHITE;
		this.capturedPieces = new ArrayList<>();
		this.piecesOnTheBoard = new ArrayList<>();
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
	
	public boolean isCheckmate() {
		return checkmate;
	}

	public ChessPiece getEnPassantVulnerable() {
		return enPassantVulnerable;
	}

	public ChessPiece getPromoted() {
		return promoted;
	}

	public Board getBoard() {
		return board;
	}
	
	public List<ChessPiece> getCapturedPieces() {
		return capturedPieces;
	}
	
	public List<ChessPiece> getPiecesOnTheBoard() {
		return piecesOnTheBoard;
	}
	
	private void initialSetup() {
		for (int i = 0; i < this.getBoard().getColumns(); i++) {
			this.placePiece((char)('a' + i), 2, new Pawn(this.board, Color.WHITE));
		}
		this.placePiece('a', 1, new Rook(this.board, Color.WHITE));
		this.placePiece('h', 1, new Rook(this.board, Color.WHITE));
		this.placePiece('e', 1, new King(this.board, Color.WHITE));
		this.placePiece('c', 1, new Bishop(this.board, Color.WHITE));
		this.placePiece('f', 1, new Bishop(this.board, Color.WHITE));
		this.placePiece('d', 1, new Queen(this.board, Color.WHITE));
		this.placePiece('b', 1, new Knight(this.board, Color.WHITE));
		this.placePiece('g', 1, new Knight(this.board, Color.WHITE));
		
		for (int i = 0; i < this.getBoard().getColumns(); i++) {
			this.placePiece((char)('a' + i), 7, new Pawn(this.board, Color.BLACK));
		}
		this.placePiece('a', 8, new Rook(this.board, Color.BLACK));
		this.placePiece('h', 8, new Rook(this.board, Color.BLACK));
		this.placePiece('e', 8, new King(this.board, Color.BLACK));
		this.placePiece('c', 8, new Bishop(this.board, Color.BLACK));
		this.placePiece('f', 8, new Bishop(this.board, Color.BLACK));
		this.placePiece('d', 8, new Queen(this.board, Color.BLACK));
		this.placePiece('b', 8, new Knight(this.board, Color.BLACK));
		this.placePiece('g', 8, new Knight(this.board, Color.BLACK));
	}
	
	private void nextTurn() {
		this.turn++;
		this.currentPlayer = (this.currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private void placePiece(char column, int row, ChessPiece piece) {
		this.board.placePiece(piece, new ChessPosition(row, column).toPosition());
		this.piecesOnTheBoard.add(piece);
	}
	
	
	private boolean testCheck(Color color) {
		for (ChessPiece piece : this.piecesOnTheBoard.stream().filter(x -> x.getColor() != color).collect(Collectors.toList())) {
			ChessPiece king = this.king(color);
			if (piece.possibleMoves()[king.getChessPosition().toPosition().getRow()][king.getChessPosition().toPosition().getColumn()]) {
				return true;
			}
			
		}
		return false;
	}
	
	private boolean testCheckmate(Color color) {
		if (!this.testCheck(color)) {
			return false;
		}
		for (ChessPiece piece : this.piecesOnTheBoard.stream().filter(x -> x.getColor() == color).collect(Collectors.toList())) {
			boolean[][] moves = piece.possibleMoves();
			for (int i = 0; i < moves.length; i++) {
				for (int j = 0; j < moves[i].length; j++) {
					if (moves[i][j]) {
						ChessPosition sourcePosition = piece.getChessPosition();
						ChessPosition targetPosition = new ChessPosition(8 - i, (char) ('a' + j));
						ChessPiece capturedPiece = this.makeMove(sourcePosition, targetPosition);
						boolean isCheck = this.testCheck(color);
						this.undoMove(sourcePosition, targetPosition, capturedPiece);
						if (!isCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private ChessPiece king(Color color) {
		for (ChessPiece piece : this.piecesOnTheBoard.stream().filter(x -> x.getColor() == color).collect(Collectors.toList())) {
			if (piece instanceof King) {
				return piece;
			}
		}
		throw new IllegalStateException("There is no " + color + "king on the board");
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
	
	public void performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		this.validateSourcePosition(sourcePosition);
		this.validadeTargetPosition(sourcePosition, targetPosition);
		ChessPiece capturedPiece = this.makeMove(sourcePosition, targetPosition);
		
		if (this.testCheck(currentPlayer)) {
			this.undoMove(sourcePosition, targetPosition, capturedPiece);
			throw new ChessException("Illegal move, can't put your own king in check ");
		}
		
		if (this.testCheck((currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE)) {
			this.check = true;
		}
		else {
			this.check = false;
		}
		
		if (this.testCheckmate((currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE)) {
			this.checkmate = true;
		}
		else {
			this.nextTurn();
		}
	}
	
	private ChessPiece makeMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		ChessPiece capturedPiece = (ChessPiece) this.board.removePiece(targetPosition.toPosition());
		this.board.placePiece(this.board.removePiece(sourcePosition.toPosition()), targetPosition.toPosition());
		((ChessPiece)this.board.piece(targetPosition.toPosition())).increaseMovecount();
		if (capturedPiece != null) {
			this.piecesOnTheBoard.remove(capturedPiece);
			this.capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}
	
	private void undoMove(ChessPosition sourcePosition, ChessPosition targetPosition, ChessPiece capturedPiece) {
		this.board.placePiece(this.board.removePiece(targetPosition.toPosition()), sourcePosition.toPosition());
		((ChessPiece)this.board.piece(sourcePosition.toPosition())).decreaseMovecount();
		if (capturedPiece != null) {
			this.board.placePiece(capturedPiece, targetPosition.toPosition());
			this.capturedPieces.remove(capturedPiece);
			this.piecesOnTheBoard.add(capturedPiece);
		}
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
