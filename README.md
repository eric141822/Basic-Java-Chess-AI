# Basic Chess AI

Originally developed for a course in university. A primitive, text-based chess AI that utilises alpha-beta pruning to decide a move.

## Heuristics

Using the standard value x 100 for each pieces, i.e. pawn = 100, queen = 900 etc. (King has a huge value to encourage check.)

Since each position for each type of piece may be valued differently as well (i.e. the 4 corners are generally unfavorable positions for a piece, e.g. bishop at corner is terrible.), consider using a set of values for each type of piece representing how favorable is each tile for the type as another heuristic. (May cause bad decisions if not valued right, be cautious).

These positional heuristic are included in AlphaBeta.java, and can be un-commented if you intend to use them.

## To run

Simply import the codes into your preferred IDE (e.g. Eclipse) and run Play.java to start the program.
