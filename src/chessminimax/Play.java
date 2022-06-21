package chessminimax;
import java.util.*;
public class Play {
	public static void main(String[] args) {
		int max_rounds = 1;
		float score_p1 = 0;// p1 == white
		float score_p2 = 0;// p2 == black
		AlphaBetaAiPlayer ai = new AlphaBetaAiPlayer(ChessPieces.WHITE, 3);
		for (int i =0; i < max_rounds; i++) {
			Board b = new Board();			
			int winner = play(b, ai);
			if (winner == 1) {
				score_p1++;
			}
			else if (winner == -1) {
				score_p2++;
			}
			else {
				score_p1+=0.5;
				score_p2+=0.5;
			}
		}
		System.out.println("AI's score: " + score_p1 + "\nHuman's score: " + score_p2);
	}
	
	public static int charToInt(char c) {
		if (c == 'a') {
			return 0;
		}
		else if (c == 'b') {
			return 1;
		}
		else if (c == 'c') {
			return 2;
		}
		else if (c == 'd') {
			return 3;
		}
		else if (c == 'e') {
			return 4;
		}
		else if (c == 'f') {
			return 5;
		}
		else if (c == 'g') {
			return 6;
		}
		else if (c == 'h') {
			return 7;
		}
		else {
			return -1;
		}
	}
	
	public static boolean isPlayerMoveValid(Board b, Moves move) {
		if (b.tiles[move.x1][move.y1].piece.color != ChessPieces.BLACK) {
			System.out.println("Please input a move for black pieces.");
			return false;
		}
		
		List<Moves> legalMoves = b.tiles[move.x1][move.y1].piece.getMovesList(b, move.x1, move.y1);
		
		for (int i = 0; i < legalMoves.size(); i++) {
			if (legalMoves.get(i).equals(move)) {
				return true;
			}
		}
		return false;
	}
	
	public static int play(Board board, AlphaBetaAiPlayer ai) {
		Moves ai_move, human_move;
		int turns = 0;
		int x1, x2, y1, y2;
		Scanner sc = new Scanner(System.in);
		while (true) {
			if (turns > 200) {
				sc.close();
				System.out.println("Over 200 turns played and no winner yet. Draw is forced.");
				return 0;
			}
			
				if (!board.simpleKingCheck(ai.color)) {
					sc.close();
					return -1;
				}
				ai_move = ai.getMove(board);
				System.out.println("Pruned states = " + ai.ai.num_pruned + "\n");
				ai.ai.num_pruned = 0;
				if (ai_move == null) {
					sc.close();
					System.out.println("AI is checked! Great job.");
					return -1;
				}
				board.move(ai_move);
				turns++;
				
				System.out.println(board);
				
				if (!board.simpleKingCheck(!ai.color)) {
					sc.close();
					return 1;
				}
				System.out.println("Please enter your move, format:a7 a6, which means piece from a7 to a6\n");
				while (true) {
					try {
						String input = sc.nextLine();
						x1 = charToInt(input.charAt(0));
						x2 = charToInt(input.charAt(3));
						y1 = Integer.parseInt(String.valueOf(input.charAt(1))) - 1;
						y2 = Integer.parseInt(String.valueOf(input.charAt(4))) - 1;
						human_move = new Moves(x1, y1, x2, y2);
						while (!isPlayerMoveValid(board, human_move) || input.length() != 5) {
							System.out.println("The move is invalid, please try to enter again.");
							input = sc.nextLine();
							x1 = charToInt(input.charAt(0));
							x2 = charToInt(input.charAt(3));
							y1 = Integer.parseInt(String.valueOf(input.charAt(1))) - 1;
							y2 = Integer.parseInt(String.valueOf(input.charAt(4))) - 1;
							human_move = new Moves(x1, y1, x2, y2);
						}
						break;
					} catch (Exception e) {
						System.out.println("Input format is invalid, must be in format: 'a7 a6', no spaces at the beginning or end.");
					}
				}
				board.move(human_move);
				System.out.println(board);
				System.out.println();
				turns++;
			
		}
	}
}
