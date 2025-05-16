
package ch.github.andreyscheurer.tictactoe;

/**
 * Represents a move in the QuadStrike game. A move is defined by its position
 * on the game board, using an x-coordinate (column index) and a y-coordinate
 * (row index).
 */
public class Move {

	private final int x; // The x-coordinate of the move (column index)
	private final int y; // The y-coordinate of the move (row index)

	/**
	 * Creates a new instance of this class for the given x- and y-coordinates.
	 *
	 * @param x The x-coordinate of the move (column index)
	 * @param y The y-coordinate of the move (row index)
	 */
	public Move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the x-coordinate (column index) of this move.
	 * 
	 * @return The x-coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the y-coordinate (row index) of this move.
	 * 
	 * @return The y-coordinate
	 */
	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return x + "/" + y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Move move = (Move) obj;
		return x == move.x && y == move.y;
	}
}
