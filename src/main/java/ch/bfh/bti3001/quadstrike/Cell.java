/*
 * Copyright (c) 2024 by Bern University of Applied Sciences
 * All rights reserved. Unauthorized reproduction and distribution of this file
 * for purposes other than personal educational use is strictly prohibited. This
 * prohibition includes uploading this file, in whole or in part, to any AI services,
 * including but not limited to ChatGPT.
 */
package ch.bfh.bti3001.quadstrike;

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
