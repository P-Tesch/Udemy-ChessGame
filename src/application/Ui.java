package application;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.enums.Color;

public class Ui {
	
	public static void printMatch(ChessMatch chessMatch) {
		printBoard(chessMatch.getPieces());
		if (!chessMatch.getCapturedPieces().isEmpty()) {
			printCapturedPieces(chessMatch.getCapturedPieces());
		}
		System.out.println("\nTurn: " + chessMatch.getTurn());
		System.out.println("Player: " + chessMatch.getCurrentPlayer());
	}
	
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
	
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print(8 - i);
			for (int j = 0; j < pieces[i].length; j++) {
				if (!possibleMoves[i][j]) {
					printPiece(pieces[i][j]);
				}
				else {
					if (pieces[i][j] == null) {
						System.out.print(" +");
					}
					else {
						System.out.print(" *");
					}
				}
			}
			System.out.println();
		}
		System.out.println("  A B C D E F G H");
	}
	
	public static void printCapturedPieces(List<ChessPiece> capturedPieces) {
		System.out.print("\nPieces captured by White:");
		for (ChessPiece piece : capturedPieces.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList())) {
			printPiece(piece);
		}
		System.out.print("\nPieces captured by Black:");
		for (ChessPiece piece : capturedPieces.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList())) {
			printPiece(piece);
		}
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
	
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String read = sc.nextLine().toLowerCase();
			char column = read.charAt(0);
			int row = (int) read.charAt(1) - '0';
			return new ChessPosition(row, column);
		}
		catch (RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition. Position must be from a1 to h8");
		}
	}
}
