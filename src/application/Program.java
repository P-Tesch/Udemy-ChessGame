package application;

import board.Position;
import chess.ChessMatch;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		ChessMatch chessMatch = new ChessMatch();
		Ui.printBoard(chessMatch.getPieces());
	}

}
