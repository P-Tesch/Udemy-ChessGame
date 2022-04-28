package application;

import board.Position;
import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {
		
		ChessMatch chessMatch = new ChessMatch();
		Ui.printBoard(chessMatch.getPieces());
		
		System.out.println(chessMatch.getBoard().positionExists(new Position(5, 5)));
		System.out.println(chessMatch.getBoard().positionExists(new Position(9, 5)));
		System.out.println(chessMatch.getBoard().thereIsAPiece(new Position(2, 1)));
		System.out.println(chessMatch.getBoard().thereIsAPiece(new Position(3, 1)));
	}

}
