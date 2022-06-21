package chessminimax;

import java.util.*;

import chessminimax.Tiles;
import chessminimax.Bishop;
import chessminimax.King;
import chessminimax.Knight;
import chessminimax.Pawn;
import chessminimax.Queen;
import chessminimax.Rook;

/**
 * Capitalized for white pieces, lower case for black pieces, * for empty tiles.
 * P/p = Pawn, N/n = Knight, B/b = Bishop, Q/q = Queen, K/k = King. R/r = Rook;
 * Board:
 * 8   r n b q k b n r
 * 7   p p p p p p p p
 * 6   * * * * * * * *
 * 5   * * * * * * * *
 * 4   * * * * * * * *
 * 3   * * * * * * * *
 * 2   P P P P P P P P
 * 1   R N B Q K B N R
 * 
 *     a b c d e f g h
 *
 *
 * Note that in the following board initiation,  tiles[j][i] because a-h is the x axis.
 * This is for the convenience of checking Moves.
 * 
 * Heuristics:
 * 
 * 
 * Using the standard value x 100 for each pieces, i.e. pawn = 100, queen = 900 etc. (King has a huge value to encourage check.)
 * 
 * Since each position for each type of piece may be valued differently as well (i.e. the 4 corners are generally 
 * unfavorable positions for a piece, e.g. bishop at corner is terrible.),
 * consider using a set of values for each type of piece representing how favorable is each tile for the type as another
 * heuristic. (May cause bad decisions if not valued right, be cautious).
 * Can maybe be discussed in the report, how more heuristics doesn't generally lead to better movements if heuristics are either
 * not designed well just unnecessary (most of the times, both.)d
 * 
 * Also uses number of moves available as another heuristic. (more moves -> more control).
 * 
 * 
 * 
 */



public class Board {
	final int a = 0, b = 1, c = 2, d = 3, e = 4, f = 5, g = 6, h = 7;
	
	Tiles[][] tiles;
	
	public Board(){//start of game board initialization.
		boolean color = ChessPieces.WHITE;
		this.tiles = new Tiles[8][8];
		for (int i = 0; i < 8; i++) { //i represents a to h.
			tiles[i][1] = new Tiles(new Pawn(color));
		}
		tiles[a][0] = new Tiles(new Rook(color));
		tiles[b][0] = new Tiles(new Knight(color));
		tiles[c][0] = new Tiles(new Bishop(color));
		tiles[d][0] = new Tiles(new Queen(color));
		tiles[e][0] = new Tiles(new King(color));
		tiles[f][0] = new Tiles(new Bishop(color));
		tiles[g][0] = new Tiles(new Knight(color));
		tiles[h][0] = new Tiles(new Rook(color));
		
		color = ChessPieces.BLACK;
		for (int i = 0; i < 8; i++) {
			tiles[i][6] = new Tiles(new Pawn(color));
		}
		tiles[a][7] = new Tiles(new Rook(color));
		tiles[b][7] = new Tiles(new Knight(color));
		tiles[c][7] = new Tiles(new Bishop(color));
		tiles[d][7] = new Tiles(new Queen(color));
		tiles[e][7] = new Tiles(new King(color));
		tiles[f][7] = new Tiles(new Bishop(color));
		tiles[g][7] = new Tiles(new Knight(color));
		tiles[h][7] = new Tiles(new Rook(color));
		
		//empty spaces.
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 8; j++) { // j represents a to h.
				tiles[j][i] = new Tiles();
			}
		}	
	}
	
	public Board(Tiles[][] tiles) {
		this.tiles = tiles;
	}
	
	public String toString() {
		String res = "";
		for (int i = 7; i >= 0; i--) {
			res += (i+1 + "  ");
			for (int j = 0; j < 8; j++) {
				res += tiles[j][i] + " ";
			}
			res += '\n';
		}
		res += "\n   ";
		res += "A B C D E F G H";
		
		return res;
	}

	public void move(Moves m) {
		Tiles before = tiles[m.x1][m.y1];
		tiles[m.x2][m.y2]= tiles[m.x1][m.y1];
		tiles[m.x1][m.y1]= new Tiles(); 
		
	}
	
	public ArrayList<Moves> MovesAfterGetMoves(boolean color, boolean checkMoves, ArrayList<Moves> moves){
		Tiles[][] tmp = new Tiles[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				tmp[i][j] = new Tiles(this.tiles[i][j]);
			}
		}
		Board b_after = new Board(tmp);
		
		for (int i = 0; i < moves.size(); i++) {
			b_after.move(moves.get(i));
		}
		ArrayList<Moves> after = b_after.getMoves(color, checkMoves);
		return after;
	}
	
	public ArrayList<Moves> getMoves(boolean color, boolean checkMoves){
		ArrayList<Moves> moves = new ArrayList<Moves>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (tiles[i][j].isOccupied && tiles[i][j].piece.color == color) {
					moves.addAll(tiles[i][j].piece.getMovesList(this, i, j));
				}
			}
		}
		//need to check if any move is horrible, i.e. put self into check.
		if (checkMoves) {
			int x = -99;
			int y = -99;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (this.tiles[i][j].isOccupied && this.tiles[i][j].piece.color == color &&
							this.tiles[i][j].piece.toString().equalsIgnoreCase("K")) {
						x = i;
						y = j;
					}
				}
			}
			
			ArrayList<Moves> toRemove = new ArrayList<Moves>();
			for (int i = 0; i < moves.size(); i++) {
				ArrayList<Moves> check = new ArrayList<Moves>(moves.subList(i, i+1)); 
				ArrayList<Moves> enemyFutureMoves = MovesAfterGetMoves(!color, false, check);
				int x_after = x, y_after = y;
				if (check.get(0).x1 == x && check.get(0).y1 == y) {//get kings position after "check" move.
					x_after = check.get(0).x2; 
					y_after = check.get(0).y2;
				}
				
				for (int j = 0; j < enemyFutureMoves.size(); j++) {
					if (enemyFutureMoves.get(j).x2 == x_after && enemyFutureMoves.get(j).y2 == y_after) {
						toRemove.add(check.get(0));
					}
				}
				
			}
			
			moves.removeAll(toRemove);
		}
		
		return moves;
		
	}
	public Tiles[][] tilesAfterMove(ArrayList<Moves> moves){
		Tiles[][] tmp = new Tiles[8][8];
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				//System.out.println("i = " + i + ", j = " + j);
				tmp[i][j] = new Tiles(this.tiles[i][j]);
			}
		}
		Board b = new Board(tmp);
		for (int i = 0; i < moves.size(); i++) {
			b.move(moves.get(i));
		}
		Tiles[][] res = new Tiles[8][8]; 
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				res[i][j] = new Tiles(b.tiles[i][j]);
			}
		}
		
		return res;
	}
	
	public boolean isChecked(boolean color, ArrayList<Moves> moves) {
		Tiles[][] tilesAfter = tilesAfterMove(moves);
		
		int x = 0, y = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (tilesAfter[i][j].isOccupied) {
					if (tilesAfter[i][j].piece.toString().equalsIgnoreCase("K") && tilesAfter[i][j].piece.color == color) {
						x = i;
						y = j;
					}
				}
			}
		}
		ArrayList<Moves> enemyFutureMoves = MovesAfterGetMoves(!color, false, moves);
		for (int i = 0; i < enemyFutureMoves.size(); i++) {
			if (enemyFutureMoves.get(i).x2 == x && enemyFutureMoves.get(i).y2 == y) {
				return true;
			}
		}
		return false;
		
	}
	
	public boolean simpleKingCheck(boolean color) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (tiles[i][j].isOccupied) {
					if (tiles[i][j].piece.toString().equalsIgnoreCase("K") && tiles[i][j].piece.color == color) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
