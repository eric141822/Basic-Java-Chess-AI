package chessminimax;

public class Moves {
	int x1, y1, x2, y2; //(x1, y1) to (x2, y2)
	
	public Moves(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	
	public boolean equals(Object obj) {
		Moves move = (Moves) obj;
		return move.x1 == x1 && move.x2 == x2 && move.y1 == y1 && move.y2 == y2;
	}
}
