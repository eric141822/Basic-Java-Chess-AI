package chessminimax;
import chessminimax.Moves;
import chessminimax.Board;

public class AlphaBetaAiPlayer {
	AlphaBeta ai;
	boolean color;
	public AlphaBetaAiPlayer(boolean color, int depth) {
		this.color = color;
		this.ai = new AlphaBeta(color, depth);
	}
	public Moves getMove(Board b) {
		Moves move = ai.decision(b);
		return move;
	}
}
