package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import exceptions.ChessException;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ChessMatch chessMatch = new ChessMatch();
		
		while (true) {
			try {
				Ui.printBoard(chessMatch.getPieces());
				System.out.print("\nSource position: ");
				ChessPosition source = Ui.readChessPosition(sc);
				
				System.out.print("\nTarget Position: ");
				ChessPosition target = Ui.readChessPosition(sc);
				ChessPiece captured = chessMatch.performChessMove(source, target);
				Ui.printPiece(captured);
				System.out.println();
			}
			catch (ChessException exception) {
				System.out.println(exception.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException exception) {
				System.out.println(exception.getMessage());
				sc.nextLine();
			}
		}
	}
}
