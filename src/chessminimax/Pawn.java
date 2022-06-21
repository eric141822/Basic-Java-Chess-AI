package chessminimax;

import java.util.*;

import chessminimax.Moves;
import chessminimax.Board;

public class Pawn extends ChessPieces{
	boolean isFirstMove = true;
	public Pawn(boolean color) {
		super(color);
		//value = 1;
		value = 100;
	}
	
	public Pawn copy() {
		Pawn tmp = new Pawn(color);
		return tmp;
	}
	
	public String toString() {
		if (color == ChessPieces.WHITE) {
			return "P";
		}
		else {
			return "p";
		}
	}
	
	public List<Moves> getMovesList(Board b, int x, int y){
		ArrayList<Moves> res = new ArrayList<Moves>();
		
		
		if (isFirstMove) {
			if (color == ChessPieces.WHITE) {//bottom to top for white.
					if (isValid(x, y+2) && !b.tiles[x][y+2].isOccupied) {
						res.add(new Moves(x,y,x,y+2));
					}
				
				if (isValid(x,y+1) && !b.tiles[x][y+1].isOccupied) {
					res.add(new Moves(x, y, x, y+1));
				}
				//kill
				if (isValid(x+1,y+1) && b.tiles[x+1][y+1].isOccupied && b.tiles[x+1][y+1].piece.color != color) {
					res.add(new Moves(x, y, x+1, y+1));
				}
				if (isValid(x-1, y+1) && b.tiles[x-1][y+1].isOccupied && b.tiles[x-1][y+1].piece.color != color) {
					res.add(new Moves(x, y, x-1, y+1));
				}
			}
			else {
				
					if (isValid(x, y-2) && !b.tiles[x][y-2].isOccupied) {
						res.add(new Moves(x,y,x,y-2));
					}
					
				if (isValid(x,y-1) && !b.tiles[x][y-1].isOccupied) {
					res.add(new Moves(x, y, x, y-1));
				}
				if (isValid(x+1,y-1) && b.tiles[x+1][y-1].isOccupied && b.tiles[x+1][y-1].piece.color != color) {
					res.add(new Moves(x, y, x+1, y-1));
				}
				if (isValid(x-1, y-1) && b.tiles[x-1][y-1].isOccupied && b.tiles[x-1][y-1].piece.color != color) {
					res.add(new Moves(x, y, x-1, y-1));
				}
			}
			isFirstMove = false;
		}
		else {
			if (color == ChessPieces.WHITE) {//bottom to top for white.
				if (isValid(x,y+1) && !b.tiles[x][y+1].isOccupied) {
					res.add(new Moves(x, y, x, y+1));
				}
				//kill
				if (isValid(x+1,y+1) && b.tiles[x+1][y+1].isOccupied && b.tiles[x+1][y+1].piece.color != color) {
					res.add(new Moves(x, y, x+1, y+1));
				}
				if (isValid(x-1, y+1) && b.tiles[x-1][y+1].isOccupied && b.tiles[x-1][y+1].piece.color != color) {
					res.add(new Moves(x, y, x-1, y+1));
				}
			}
			else {
				if (isValid(x,y-1) && !b.tiles[x][y-1].isOccupied) {
					res.add(new Moves(x, y, x, y-1));
				}
				if (isValid(x+1,y-1) && b.tiles[x+1][y-1].isOccupied && b.tiles[x+1][y-1].piece.color != color) {
					res.add(new Moves(x, y, x+1, y-1));
				}
				if (isValid(x-1, y-1) && b.tiles[x-1][y-1].isOccupied && b.tiles[x-1][y-1].piece.color != color) {
					res.add(new Moves(x, y, x-1, y-1));
				}
			}
		}
		return res;
	}
	
	
	
}
