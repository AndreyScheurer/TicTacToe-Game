
package ch.github.andreyscheurer.tictactoe;

/**
 * Represents the state of a cell in the QuadStrike game board.
 */
public enum Cell {
	EMPTY(" "), 
	CROSS("X"), // Player 1
	CIRCLE("O"), // Player 2
	WINNING("#"); // When a game is over each cell forming a winning row is replaced by '#'
	private final String symbol;

	/**
	 * Constructor: assign symbol to each cell state
	 * 
	 * @param symbol the symbol representing the cell state
	 */
	Cell(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return symbol;
	}
}
