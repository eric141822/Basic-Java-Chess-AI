package chessminimax;

import java.util.*;

import chessminimax.Moves;
import chessminimax.Board;

public class King extends ChessPieces{
	public King(boolean color) {
		super(color);
		//value = 0; 
		value = 100000; //can be any huge value.
	}
	
	public King copy() {
		King tmp = new King(color);
		return tmp;
	}
	public String toString() {
		if (color == ChessPieces.WHITE) {
			return "K";
		}
		else {
			return "k";
		}
	}
	
	public List<Moves> getMovesList(Board b, int x, int y){
		ArrayList<Moves> res = new ArrayList<Moves>();
		
		//King can move to any adjacent space.
		//go up
		if (isValid(x,y+1) && (!b.tiles[x][y+1].isOccupied || (b.tiles[x][y+1].isOccupied && 
				b.tiles[x][y+1].piece.color != color))){
			res.add(new Moves(x,y,x,y+1));
		}
		//go down
		if (isValid(x,y-1) && (!b.tiles[x][y-1].isOccupied || (b.tiles[x][y-1].isOccupied && 
				b.tiles[x][y-1].piece.color != color))){
			res.add(new Moves(x,y,x,y-1));
		}
		//go right
		if (isValid(x+1,y) && (!b.tiles[x+1][y].isOccupied || (b.tiles[x+1][y].isOccupied && 
				b.tiles[x+1][y].piece.color != color))){
			res.add(new Moves(x,y,x+1,y));
		}
		//go left
		if (isValid(x-1,y) && (!b.tiles[x-1][y].isOccupied || (b.tiles[x-1][y].isOccupied && 
				b.tiles[x-1][y].piece.color != color))){
			res.add(new Moves(x,y,x-1,y));
		}
		//go up and right
		if (isValid(x+1,y+1) && (!b.tiles[x+1][y+1].isOccupied || (b.tiles[x+1][y+1].isOccupied && 
				b.tiles[x+1][y+1].piece.color != color))){
			res.add(new Moves(x,y,x+1,y+1));
		}
		//go up and left
		if (isValid(x-1,y+1) && (!b.tiles[x-1][y+1].isOccupied || (b.tiles[x-1][y+1].isOccupied && 
				b.tiles[x-1][y+1].piece.color != color))){
			res.add(new Moves(x,y,x-1,y+1));
		}
		//go down and right
		if (isValid(x+1,y-1) && (!b.tiles[x+1][y-1].isOccupied || (b.tiles[x+1][y-1].isOccupied && 
				b.tiles[x+1][y-1].piece.color != color))){
			res.add(new Moves(x,y,x+1,y-1));
		}
		//go down and left
		if (isValid(x-1,y-1) && (!b.tiles[x-1][y-1].isOccupied || (b.tiles[x-1][y-1].isOccupied && 
				b.tiles[x-1][y-1].piece.color != color))){
			res.add(new Moves(x,y,x-1,y-1));
		}
		
		
		return res;
	}
	
	
	
}
