package chessminimax;

import java.util.*;

import chessminimax.Moves;
import chessminimax.Board;

public class Bishop extends ChessPieces{
	public Bishop(boolean color) {
		super(color);
		//value = 3;
		value = 300;
	}
	
	public Bishop copy() {
		Bishop tmp = new Bishop(color);
		return tmp;
	}
	
	public String toString() {
		if (color == ChessPieces.WHITE) {
			return "B";
		}
		else {
			return "b";
		}
	}
	
	public List<Moves> getMovesList(Board b, int x, int y){
		ArrayList<Moves> res = new ArrayList<Moves>();
		
		//bishops may only move diagonally.
		//going top right.
		for (int i = 1; i < 8; i++) {
			if (isValid(x+i, y+i)) {
				if (b.tiles[x+i][y+i].isOccupied) {
					//kill
					if (b.tiles[x+i][y+i].piece.color != color) {
						res.add(new Moves(x, y, x+i, y+i));
					}
					break; //blocked by a same colored piece.
				}
				else {
					res.add(new Moves(x, y, x+i, y+i));
				}
			}
		}
		//going top left
		for (int i = 1; i < 8; i++) {
			if (isValid(x-i, y+i)) {
				if (b.tiles[x-i][y+i].isOccupied) {
					//kill
					if (b.tiles[x-i][y+i].piece.color != color) {
						res.add(new Moves(x, y, x-i, y+i));
						
					}
					break; //blocked by a same colored piece.
				}
				else {
					res.add(new Moves(x, y, x-i, y+i));
				}
			}
		}
		
		//going bottom right.
		for (int i = 1; i < 8; i++) {
			if (isValid(x+i, y-i)) {
				if (b.tiles[x+i][y-i].isOccupied) {
					//kill
					if (b.tiles[x+i][y-i].piece.color != color) {
						res.add(new Moves(x, y, x+i, y-i));
						
					}
					break; //blocked by a same colored piece.
				}
				else {
					res.add(new Moves(x, y, x+i, y-i));
				}
			}
		}
		//going bottom left.
		for (int i = 1; i < 8; i++) {
			if (isValid(x-i, y-i)) {
				if (b.tiles[x-i][y-i].isOccupied) {
					//kill
					if (b.tiles[x-i][y-i].piece.color != color) {
						res.add(new Moves(x, y, x-i, y-i));
						
					}
					break; //blocked by a same colored piece.
				}
				else {
					res.add(new Moves(x, y, x-i, y-i));
				}
			}
		}
		
		
		return res;
	}
	
}
