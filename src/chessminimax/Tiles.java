package chessminimax;

import chessminimax.ChessPieces;

public class Tiles {
	boolean isOccupied;
	ChessPieces piece;
	
	public Tiles() { //for initial board initialization, empty space.
		this.isOccupied = false;
	}
	
	public Tiles(Tiles t) { //copy constructor
		this.isOccupied = t.isOccupied;
		if (t.isOccupied) {
			this.piece = t.piece.copy();
		}
		else {
			this.piece = null;
		}
	}
	
	
	public Tiles(ChessPieces piece) {
		this.isOccupied = true;
		this.piece = piece;
	}
	
	public String toString() {
		if (isOccupied) {
			return piece.toString();
		}
		else {
			return "*"; //place holder for null space.
		}
	}
}
