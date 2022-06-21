package chessminimax;

import java.util.*;

import chessminimax.Board;
import chessminimax.Moves;

public abstract class ChessPieces {
	static final boolean WHITE = true;
	static final boolean BLACK = false;
	

	boolean color;
	int value; //value of piece, pawn = 1, knight/bishop = 3, rook = 5, queen = 9, king = Huge value.
	
	public ChessPieces(boolean color) {
		this.color = color;
		this.value = 0;
	}
		
	public abstract ChessPieces copy();
	
	public static boolean isValid(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			return false;
		}
		else {
			return true;
		}
	}
	
	
	public abstract List<Moves> getMovesList(Board b, int x, int y);
}