package application;

import chess.ChessPiece;
import chess.enums.Color;

public class Ui {
	
	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print(8 - i);
			for (int j = 0; j < pieces[i].length; j++) {
				printPiece(pieces[i][j]);
			}
			System.out.println();
		}
		System.out.println("  A B C D E F G H");
	}
	
	public static void printPiece(ChessPiece piece) {
		System.out.print(" ");
		if (piece != null) {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(piece);
			}
			else {
				System.out.print(piece.toString().toLowerCase());
			}
		}
		else {
			System.out.print("-");
		}
	}
}
