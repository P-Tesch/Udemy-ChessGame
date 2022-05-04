package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPosition;
import exceptions.ChessException;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ChessMatch chessMatch = new ChessMatch();
		
		while (!chessMatch.isCheckmate()) {
			try {
				Ui.printMatch(chessMatch);
				System.out.print("\nSource position: ");
				ChessPosition source = Ui.readChessPosition(sc);
				
				Ui.printBoard(chessMatch.getPieces(), chessMatch.possibleMoves(source));
				
				System.out.print("\nTarget Position: ");
				ChessPosition target = Ui.readChessPosition(sc);
				chessMatch.performChessMove(source, target);
				System.out.println();
				
				if (chessMatch.getPromoted() != null) {
					String type = "F";
					while(!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")) {
						System.out.println("Enter piece for promotion (B/N/R/Q): ");
						type = sc.nextLine().toUpperCase();
					}
					chessMatch.replacePromotedPiece(type);
				}
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
		Ui.printMatch(chessMatch);
	}
}
