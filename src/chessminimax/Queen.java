package chessminimax;

import java.util.*;

import chessminimax.Moves;
import chessminimax.Board;

public class Queen extends ChessPieces{
	public Queen(boolean color) {
		super(color);
		//value = 9;
		value = 900;
	}
	
	public Queen copy() {
		Queen tmp = new Queen(color);
		return tmp;
	}
	
	public String toString() {
		if (color == ChessPieces.WHITE) {
			return "Q";
		}
		else {
			return "q";
		}
	}
	
	public List<Moves> getMovesList(Board b, int x, int y){
		ArrayList<Moves> res = new ArrayList<Moves>();
		//queen is a combination of rook and bishop.
		
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
	
}
