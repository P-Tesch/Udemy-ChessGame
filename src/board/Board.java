package board;

import exceptions.BoardException;

public class Board {
	
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Board must contain at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public Piece piece(int row, int column) {
		if (!this.positionExists(new Position(row, column))) {
			throw new BoardException("Position does not exist");
		}
		return this.pieces[row][column];
	}
	
	public Piece piece(Position position) {
		if (!this.positionExists(position)) {
			throw new BoardException("Position does not exist");
		}
		return this.pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		if (this.thereIsAPiece(position)) {
			throw new BoardException("There already is a piece in this position");
		}
		this.pieces[position.getRow()][position.getColumn()] = piece;
		piece.setPosition(position);
	}
	
	public Piece removePiece(Position position) {
		if (!this.positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		if (this.piece(position) == null) {
			return null;
		}
		Piece piece = piece(position);
		piece.setPosition(null);
		this.pieces[position.getRow()][position.getColumn()] = null;
		return piece;
	}
	
	public boolean positionExists(Position position) {
		return (position.getRow() <= this.getRows() && position.getColumn() <= this.getColumns() && position.getRow() >= 0 && position.getColumn() >= 0);
	}
	
	public boolean thereIsAPiece(Position position) {
		if (!this.positionExists(position)) {
			throw new BoardException("Position does not exist");
		}
		return (piece(position) != null);
	}
}
