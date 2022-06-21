package chessminimax;

import java.util.*;

import chessminimax.Board;
import chessminimax.ChessPieces;
import chessminimax.Moves;
import chessminimax.Tiles;

public class AlphaBeta {
	boolean color;
	int maxDepth;
	static int num_pruned;
	//Position value heuristics, values are taken from the Internet and edited to my likings.
	//You may freely change these values to see how the AI performs differently.
	//Uncomment the below tables and the switch table in the utility() method to utilize these positional heuristics.
	/**
	final static int[][] knightTable = {
		   	{-50, -40, -30, -30, -30, -30, -40, -50},
			{-40, -20, 0, 0, 0, 0, -20, -40},
			{-30, 0, 10, 15, 15, 10, 0, -30},
			{-30, 5, 15, 20, 20, 15, 5, -30},
			{-30, 0, 15, 20, 20, 15, 0, -30},
			{-30, 5, 10, 15, 15, 10, 5, -30},
			{-40, -20, 0, 0, 0, 0, -20, -40},
			{-50, -40, -30, -30, -30, -30, -40, -50}};
	
	final static int[][] bishopTable = {
			{-20,-10,-10,-10,-10,-10,-10,-20},
	          {-10,  0,  0,  0,  0,  0,  0,-10},
	          {-10,  0,  5, 10, 10,  5,  0,-10},
	          {-10,  5,  5, 10, 10,  5,  5,-10},
	          {-10,  0, 10, 10, 10, 10,  0,-10},
	          {-10, 10, 10, 10, 10, 10, 10,-10},
	          {-10,  5,  0,  0,  0,  0,  5,-10},
	          {-20,-10,-10,-10,-10,-10,-10,-20}
	};
	
	final static int[][] pawnTable = {
			{0,  0,  0,  0,  0,  0,  0,  0},
	          {0, 0, 0, 0, 0, 0, 0, 0},
	          {10, 10, 20, 30, 30, 20, 10, 10},
	          {5,  5, 10, 25, 25, 10,  5,  5},
	          {0,  0,  0, 25, 25,  0,  0,  0},
	          {5, -5,-20,  5,  5,-20, -5,  5},
	          {5, 10, 10,-20,-20, 10, 10,  5},
	          {0,  0,  0,  0,  0,  0,  0,  0} 
	};
	
	final static int[][] rookTable = {
			{0,  0,  0,  0,  0,  0,  0,  0},
            {5,  0, 0, 0, 0, 0, 0,  5},
           {-5,  0,  0,  0,  0,  0,  0, -5},
           {-5,  0,  0,  0,  0,  0,  0, -5},
           {-5,  0,  0,  0,  0,  0,  0, -5},
           {-5,  0,  0,  0,  0,  0,  0, -5},
           {-5,  10,  10,  10,  10,  10,  10, -5},
            {0,  0,  0,  5,  5,  0,  0,  0}
	};
	
	final static int[][] queenTable = {
			{-20,-10,-10, -5, -5,-10,-10,-20},
	          {-10,  0,  0,  0,  0,  0,  0,-10},
	          {-10,  0,  5,  5,  5,  5,  0,-10},
	           {-10,  0,  5,  5,  5,  5,  0, -10},
	            {-10,  0,  5,  5,  5,  5,  0, -10},
	          {-10,  5,  5,  5,  5,  5,  0,-10},
	          {-10,  0,  5,  0,  0,  0,  0,-10},
	          {-20,-10,-10, -5, -5,-10,-10,-20} 
	};
	
	final static int[][] kingTable = {
			{-30,-40,-40,-50,-50,-40,-40,-30},
	          {-30,-40,-40,-50,-50,-40,-40,-30},
	          {-30,-40,-40,-50,-50,-40,-40,-30},
	          {-30,-40,-40,-50,-50,-40,-40,-30},
	          {-20,-30,-30,-40,-40,-30,-30,-20},
	          {-10,-20,-20,-20,-20,-20,-20,-10},
	           {20, 20,  0,  0,  0,  0, 20, 20},
	           {20, 30, 10,  0,  0, 10, 30, 20}
	};
	**/
	public AlphaBeta (boolean color, int depth){
		this.color = color;
		this.maxDepth = depth;
	}
	
	public float maxVal(Board b, float alpha, float beta, int depth, ArrayList<Moves> state) {
		if (depth > maxDepth) {
			return utility(b, state, color);
		}
		float util = Float.NEGATIVE_INFINITY;
		float curr = 0;
		int counter = 0;
		ArrayList<Moves> moves_after = b.MovesAfterGetMoves(color, true, state);
		if (moves_after.size() == 0) {
			return util;
		}
		
		
		for (int i = 0; i < moves_after.size(); i++){
			Moves m = moves_after.get(i);
			counter++;
			state.add(m);
			curr = minVal(b, alpha, beta, depth+1, state);
			state.remove(state.lastIndexOf(m));
			
			if (curr > util) {
				util = curr;
			}
			if (util > beta) {
				num_pruned += ((moves_after.size()) - counter);
				return util;
			}
			if (util > alpha) {
				alpha = util;
			}
			
		}
		
		return util;
		
	}
	
	public float minVal(Board b, float alpha, float beta, int depth, ArrayList<Moves> state) {
		if (depth > maxDepth) {
			return utility(b, state, color);
		}
		float util = Float.POSITIVE_INFINITY;
		float curr = 0;
		int counter = 0;
		ArrayList<Moves> moves_after = b.MovesAfterGetMoves(color, true, state);
		if (moves_after.size() == 0) {
			return util;
		}
		
		
		for (int i = 0; i < moves_after.size(); i++) {
			Moves m = moves_after.get(i);
			state.add(m);
			counter++;
			curr = maxVal(b, alpha, beta, depth+1, state);
			state.remove(state.lastIndexOf(m));
			
			if (curr < util) {
				util = curr;
			}
			if (util < alpha) {
				num_pruned += ((moves_after.size()) - counter);
				return util;
			}
			if (util < beta) {
				beta = util;
			}
			
		}
		
		return util;
		
		
	}
	public float utility(Board b, ArrayList<Moves> move, boolean color) {
		Tiles[][] tiles = b.tilesAfterMove(move);
		int num_of_moves = b.MovesAfterGetMoves(color, true, move).size();//number of moves heuristic.
		
		if (b.getMoves(color, true).size() == 0) {// no more moves available
			if (b.isChecked(color, move)) {
				if (color == this.color) {
					return Float.NEGATIVE_INFINITY;
				}
				else {
					return Float.POSITIVE_INFINITY;
				}
			}
			return Float.NEGATIVE_INFINITY;
		}
		
		float w_val = 0, b_val = 0;
		
		if (color == ChessPieces.WHITE) {
			w_val = num_of_moves;
		}
		else {
			b_val = num_of_moves;
		}
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (tiles[i][j].isOccupied) {
					if (tiles[i][j].piece.color == ChessPieces.WHITE) {
						w_val += tiles[i][j].piece.value;
						/** Un-comment these and the below switch table to use the positional heuristics.
						switch (tiles[i][j].piece.toString()) {
							case "P":
								w_val += pawnTable[i][j];
								break;
							case "N":
								w_val += knightTable[i][j];
								break;
							case "B":
								w_val += bishopTable[i][j];
								break;
							case "K":
								w_val += kingTable[i][j];
								break;
							case "Q":
								w_val += queenTable[i][j];
								break;
							case "R":
								w_val += rookTable[i][j];
								break;
						}
						**/
						
					}
					else {
						b_val += tiles[i][j].piece.value;
						/**
						switch (tiles[i][j].piece.toString()) {
						case "p":
							b_val += pawnTable[i][j];
							break;
						case "n":
							b_val += knightTable[i][j];
							break;
						case "b":
							b_val += bishopTable[i][j];
							break;
						case "k":
							b_val += kingTable[i][j];
							break;
						case "q":
							b_val += queenTable[i][j];
							break;
						case "r":
							b_val += rookTable[i][j];
							break;
					}
					**/
					
					}
				}
			}
		}
		if (color == ChessPieces.WHITE) {
			return w_val-b_val;
		}
		else {
			return b_val-w_val;
		}
		
	}
	
	public Moves decision(Board b) {
		ArrayList<Moves> moves = b.getMoves(color, true);
		ArrayList<Moves> state = new ArrayList<Moves>();
		float[] costs = new float[moves.size()];
		for (int i = 0; i < moves.size(); i++) {
			state.add(moves.get(i));
			float cost = minVal(b, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY, 1, state);
			costs[i] = cost;
			state.remove(state.lastIndexOf(moves.get(i)));
		}
		int idx = -99;
		float max = Float.NEGATIVE_INFINITY;
		for (int i = 0; i < moves.size(); i++) {
			if (costs[i] >= max) {
				max = costs[i];
				idx = i;
			}
		}
		if (idx != -99) {
			return moves.get(idx);
		}
		else {
			return null;
		}
		
	}
}
