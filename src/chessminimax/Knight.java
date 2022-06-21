package chessminimax;

import java.util.*;

import chessminimax.Moves;
import chessminimax.Board;

public class Knight extends ChessPieces{
	public Knight(boolean color) {
		super(color);
		//value = 3;
		value = 300;
	}
	
	public Knight copy() {
		Knight tmp = new Knight(color);
		return tmp;
	}
	
	public String toString() {
		if (color == ChessPieces.WHITE) {
			return "N";
		}
		else {
			return "n";
		}
	}
	
	public List<Moves> getMovesList(Board b, int x, int y){
		ArrayList<Moves> res = new ArrayList<Moves>();
		
		//knight moves in L shape, and can skip over pieces. i.e. no need for blocking pieces, just check color.
		//go up 2, right 1
		if (isValid(x+1,y+2) && (!b.tiles[x+1][y+2].isOccupied || (b.tiles[x+1][y+2].isOccupied && 
				b.tiles[x+1][y+2].piece.color != color))) {
			res.add(new Moves(x,y,x+1,y+2));
		}
		//go up 1, right 2
		if (isValid(x+2,y+1) && (!b.tiles[x+2][y+1].isOccupied || (b.tiles[x+2][y+1].isOccupied && 
				b.tiles[x+2][y+1].piece.color != color))) {
			res.add(new Moves(x,y,x+2,y+1));
		}
		//go up 2, left 1
		if (isValid(x-1,y+2) && (!b.tiles[x-1][y+2].isOccupied || (b.tiles[x-1][y+2].isOccupied && 
				b.tiles[x-1][y+2].piece.color != color))) {
			res.add(new Moves(x,y,x-1,y+2));
		}
		//go up 1, left 2
		if (isValid(x-2,y+1) && (!b.tiles[x-2][y+1].isOccupied || (b.tiles[x-2][y+1].isOccupied && 
				b.tiles[x-2][y+1].piece.color != color))) {
			res.add(new Moves(x,y,x-2,y+1));
		}
		
		//go down 2, right 1
		if (isValid(x+1,y-2) && (!b.tiles[x+1][y-2].isOccupied || (b.tiles[x+1][y-2].isOccupied && 
				b.tiles[x+1][y-2].piece.color != color))) {
			res.add(new Moves(x,y,x+1,y-2));
		}
		//go down 1, right 2
		if (isValid(x+2,y-1) && (!b.tiles[x+2][y-1].isOccupied || (b.tiles[x+2][y-1].isOccupied && 
				b.tiles[x+2][y-1].piece.color != color))) {
			res.add(new Moves(x,y,x+2,y-1));
		}
		
		//go down 2, left 1
		if (isValid(x-1,y-2) && (!b.tiles[x-1][y-2].isOccupied || (b.tiles[x-1][y-2].isOccupied && 
				b.tiles[x-1][y-2].piece.color != color))) {
			res.add(new Moves(x,y,x-1,y-2));
		}
		
		//go down 1, left 2
		if (isValid(x-2,y-1) && (!b.tiles[x-2][y-1].isOccupied || (b.tiles[x-2][y-1].isOccupied && 
				b.tiles[x-2][y-1].piece.color != color))) {
			res.add(new Moves(x,y,x-2,y-1));
		}
		return res;
	}
	
}
