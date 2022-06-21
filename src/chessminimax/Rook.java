package chessminimax;

import java.util.*;

import chessminimax.Moves;
import chessminimax.Board;

public class Rook extends ChessPieces{
	public Rook(boolean color) {
		super(color);
		//value = 5;
		value = 500;
	}
	
	public Rook copy() {
		Rook tmp = new Rook(color);
		return tmp;
	}
	
	
	//Rook can only move straight.
	public List<Moves> getMovesList(Board b, int x, int y){
		ArrayList<Moves> res = new ArrayList<Moves>();
		
		//going up
		for (int i = 1; i < 8; i++) {
			if (isValid(x, y+i)) {
				if (b.tiles[x][y+i].isOccupied) {
					//kill
					if (b.tiles[x][y+i].piece.color != color) {
						res.add(new Moves(x, y, x, y+i));
						
					}
					break; //blocked by a same colored piece.
				}
				res.add(new Moves(x,y,x,y+i));
			}
		}
		
		//going down.
		for (int i = 1; i < 8; i++) {
			if (isValid(x, y-i)) {
				if (b.tiles[x][y-i].isOccupied) {
					//kill
					if (b.tiles[x][y-i].piece.color != color) {
						res.add(new Moves(x, y, x, y-i));
						
					}
					break; //blocked by a same colored piece.
				}
				res.add(new Moves(x,y,x,y-i));
			}
		}
		
		//going right.
		for (int i = 1; i < 8; i++) {
			if (isValid(x+i, y)) {
				if (b.tiles[x+i][y].isOccupied) {
					//kill
					if (b.tiles[x+i][y].piece.color != color) {
						res.add(new Moves(x, y, x+i, y));
						
					}
					break; //blocked by a same colored piece.
				}
				res.add(new Moves(x,y,x+i,y));
			}
		}
		
		//going left.
		for (int i = 1; i < 8; i++) {
			if (isValid(x-i, y)) {
				if (b.tiles[x-i][y].isOccupied) {
					//kill
					if (b.tiles[x-i][y].piece.color != color) {
						res.add(new Moves(x, y, x-i, y));
						
					}
					break; //blocked by a same colored piece.
				}
				res.add(new Moves(x,y,x-i,y));
			}
		}
		
		
		return res;
	}
	
	public String toString() {
		if (color == ChessPieces.WHITE) {
			return "R";
		}
		else {
			return "r";
		}
	}
	
}
