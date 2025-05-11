/*
 * Copyright (c) 2024 by Bern University of Applied Sciences
 * All rights reserved. Unauthorized reproduction and distribution of this file
 * for purposes other than personal educational use is strictly prohibited. This
 * prohibition includes uploading this file, in whole or in part, to any AI services,
 * including but not limited to ChatGPT.
 */
package ch.bfh.bti3001.quadstrike;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the game board for the QuadStrike game. The board dynamically
 * adjusts its size based on crosses and circles added to the board during the
 * game and tracks the state of each cell. The QuadStrike game always starts
 * from a board of size 1x1 with a single empty cell, and it ends when a row of
 * 4 crosses or circles is achieved by one of the players.
 */
public class Board {

	/**
	 * Inner class that represents a coordinate. Is used for the dictionary that
	 * represents the board
	 */
	static class Coordinate {
		private final int x;
		private final int y;

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null || getClass() != obj.getClass())
				return false;
			Coordinate other = (Coordinate) obj;
			return x == other.x && y == other.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}

	/*
	 * A dictionary that represents the board.
	 * At each coordinate(key) a cell(value) is stored.
	 */
	private Map<Coordinate, Cell> board = new HashMap<>();

	private int minX, maxX, minY, maxY;
	private boolean gameOver;

	// This method changes the board size if needed based on the given coordinates.
	private void adjustBoardSize(int x, int y) {
		// Create new boundaries for the board

		// Adjust if the cell is on a column that is on the border
		if (x == getMinX()) {
			minX--;
		}
		if (x == getMaxX()) {
			maxX++;
		}
		// Adjust if the cell is on a row that is on the border
		if (y == getMinY()) {
			minY--;
		}
		if (y == getMaxY()) {
			maxY++;
		}
	}

	/**
	 * Get a specific cell of the board
	 *
	 * @param x The x-coordinate of the cell to retrieve.
	 * @param y The y-coordinate of the cell to retrieve.
	 * @return The cell at the specified coordinates, or {@code Cell.EMPTY} if the
	 *         coordinates are outside the board.
	 */
	protected Cell getCell(int x, int y) {
		// Get cell value (returns EMPTY if the cell is not set)
		return board.getOrDefault(new Coordinate(x, y), Cell.EMPTY);
	}

	/**
	 * Checks if the game is over after a move at the specified coordinates (x, y)
	 * by the specified cell type. A game is considered over if there is a line of
	 * at least 4 consecutive cells of the same type in any of the following
	 * directions: - Horizontal - Vertical - Diagonal (bottom-right) - Diagonal
	 * (top-right)
	 *
	 * If a winning line is detected, the gameOver flag is set to true
	 * If a winning line is detected, the cells forming the line are replaced with
	 * '#'.
	 *
	 * @param x    The x-coordinate of the last move.
	 * @param y    The y-coordinate of the last move.
	 * @param cell The type of cell that was just placed (CROSS or CIRCLE).
	 */
	private void checkGameOver(int x, int y, Cell cell) {
		if (checkAndMarkWinningDirection(x, y, cell, 1, 0) || // Horizontal
				checkAndMarkWinningDirection(x, y, cell, 0, 1) || // Vertical
				checkAndMarkWinningDirection(x, y, cell, 1, 1) || // Diagonal (bottom-right)
				checkAndMarkWinningDirection(x, y, cell, 1, -1)) { // Diagonal (top-right)
			gameOver = true;
		}
	}

	/**
	 * Checks if there is a line of at least 4 consecutive cells of the specified
	 * type cell starting from the given coordinates (x, y) in the specified
	 * direction (dx, dy) and its opposite direction (-dx, -dy).
	 * 
	 * @param x    The x-coordinate of the starting cell.
	 * @param y    The y-coordinate of the starting cell.
	 * @param cell The type of cell to check (CROSS or CIRCLE).
	 * @param dx   The step in the x-direction for the line check.
	 * @param dy   The step in the y-direction for the line check.
	 * @return {@code true} if a line of 4 or more consecutive cells of the same
	 *         type exists, {@code false} otherwise.
	 */
	private boolean checkAndMarkWinningDirection(int x, int y, Cell cell, int dx, int dy) {
		// Find the total count of consecutive cells in both directions
		int count = 1 + countInDirection(x, y, cell, dx, dy) + countInDirection(x, y, cell, -dx, -dy);

		// If we found a winning line, mark the cells
		if (count >= 4) {
			markWinningCells(x, y, cell, dx, dy);
			return true;
		}
		return false;
	}

	// Move towards one direction and see how many of the specified cell can be
	// found one after another
	private int countInDirection(int x, int y, Cell cell, int dx, int dy) {
		int count = 0;
		while (getCell(x + dx, y + dy) == cell) {
			x += dx;
			y += dy;
			count++;
		}
		return count;
	}

	/**
	 * Marks the winning cells forming the line with a special marker '#'.
	 *
	 * @param x    The x-coordinate of the starting cell.
	 * @param y    The y-coordinate of the starting cell.
	 * @param cell The type of cell that forms the winning line.
	 * @param dx   The step in the x-direction for the line check.
	 * @param dy   The step in the y-direction for the line check.
	 */
	private void markWinningCells(int x, int y, Cell cell, int dx, int dy) {
		// Mark the starting cell itself
		board.put(new Coordinate(x, y), Cell.WINNING);

		// Move in the positive direction (dx, dy)
		int tempX = x;
		int tempY = y;
		while (getCell(tempX + dx, tempY + dy) == cell) {
			tempX += dx;
			tempY += dy;
			board.put(new Coordinate(tempX, tempY), Cell.WINNING);
		}

		// Move in the negative direction (-dx, -dy)
		tempX = x;
		tempY = y;
		while (getCell(tempX - dx, tempY - dy) == cell) {
			tempX -= dx;
			tempY -= dy;
			board.put(new Coordinate(tempX, tempY), Cell.WINNING);
		}
	}

	/**
	 * Initializes an empty QuadStrike game board of size 1x1 with a single unmarked
	 * cell at coordinates 0/0.
	 */
	public Board() {
		clear();
	}

	/**
	 * Gets the current minimum x-coordinate.
	 *
	 * @return The current minimum x-coordinate.
	 */
	public int getMinX() {
		return minX;
	}

	/**
	 * Gets the current maximum x-coordinate.
	 *
	 * @return The current maximum x-coordinate.
	 */
	public int getMaxX() {
		return maxX;
	}

	/**
	 * Gets the current minimum y-coordinate.
	 *
	 * @return The current minimum y-coordinate.
	 */
	public int getMinY() {
		return minY;
	}

	/**
	 * Gets the current maximum y-coordinate.
	 *
	 * @return The current maximum y-coordinate.
	 */
	public int getMaxY() {
		return maxY;
	}

	/**
	 * Gets the current width of the board, calculated as the number of columns.
	 *
	 * @return The current width of the board
	 */
	public int getWidth() {
		return getMaxX() - getMinX() + 1;
	}

	/**
	 * Gets the current height of the board, calculated as the number of rows.
	 *
	 * @return The current height of the board
	 */
	public int getHeight() {
		return getMaxY() - getMinY() + 1;
	}

	/**
	 * Determines if the cell with specified coordinates is playable. Generally, a
	 * cell is playable if it is within the current board size, if it is still
	 * unmarked, if it has a marked neighbor, and if the game is not over yet. In
	 * the particular case of an empty game board, the single cell is also playable.
	 *
	 * @param x The given x-coordinate
	 * @param y The given y-coordinate
	 * @return {@code true} If the cell is playable, {@code false} otherwise
	 */
	public boolean isPlayable(int x, int y) {
		/*
		 * The initial board that is a 1x1 cell has no marked neighbour.
		 * Therefore, if the board is the initial board, then skip the check for marked
		 * neighbours.
		 */
		if (board.size() == 1) {
			return !gameOver && !isOutside(x, y) && isEmpty(x, y);
		}
		return !gameOver && !isOutside(x, y) && isEmpty(x, y) && hasMarkedNeighbor(x, y);
	}

	/**
	 * Adds a cross to the cell with specified coordinates, but only if the
	 * corresponding cell is playable. If necessary, the board size is adjusted. If
	 * the cell is not playable, an exception is thrown.
	 *
	 * @param x The given x-coordinate
	 * @param y The given y-coordinate
	 * @throws RuntimeException if the cell is not playable
	 */
	public void playCross(int x, int y) {
		if (!isPlayable(x, y)) {
			throw new RuntimeException("Invalid move: Cell is not playable.");
		}
		play(x, y, Cell.CROSS);
	}

	/**
	 * Adds a circle to the cell with specified coordinates, but only if the
	 * corresponding cell is playable. If necessary, the board size is adjusted. If
	 * the cell is not playable, an exception is thrown.
	 *
	 * @param x The given x-coordinate
	 * @param y The given y-coordinate
	 * @throws RuntimeException if the cell is not playable
	 */
	public void playCircle(int x, int y) {
		if (!isPlayable(x, y)) {
			throw new RuntimeException("Invalid move: Cell is not playable.");
		}
		play(x, y, Cell.CIRCLE);
	}

	/**
	 * Determines if the game is over.
	 *
	 * @return {@code true} if the game is over, {@code false} otherwise
	 */
	public boolean gameOver() {
		return gameOver;
	}

	/**
	 * Determines if the cell with specified coordinates is empty, i.e., if the cell
	 * is within the current board size and not marked with a cross or a circle.
	 *
	 * @param x The given x-coordinate
	 * @param y The given y-coordinate
	 * @return {@code true} If the cell is empty, {@code false} otherwise
	 */
	public boolean isEmpty(int x, int y) {
		// The cell is unavailable if out of bounds, meaning it will not be empty
		if (isOutside(x, y))
			return false;
		return getCell(x, y) == Cell.EMPTY;
	}

	/**
	 * Determines if the cell with specified coordinates is marked with a cross.
	 *
	 * @param x The given x-coordinate
	 * @param y The given y-coordinate
	 * @return {@code true} If the cell is marked with a cross, {@code false}
	 *         otherwise
	 */
	public boolean isCrossed(int x, int y) {
		return getCell(x, y) == Cell.CROSS;
	}

	/**
	 * Determines if the cell with specified coordinates is marked with a circle.
	 *
	 * @param x The given x-coordinate
	 * @param y The given y-coordinate
	 * @return {@code true} If the cell is marked with a circle, {@code false}
	 *         otherwise
	 */
	public boolean isCircled(int x, int y) {
		return getCell(x, y) == Cell.CIRCLE;
	}

	/**
	 * Determines if the cell with the specified coordinate is marked with a '#'
	 *
	 * @param x The given x-coordinate
	 * @param y The given y-coordinate
	 * @return {@code true} If the cell is marked with a hashtag, {@code false}
	 *         otherwise
	 */
	private boolean isWinningCell(int x, int y) {
		return getCell(x, y) == Cell.WINNING;
	}

	/**
	 * Determines if the cell with specified coordinates is marked with either a
	 * cross or a circle.
	 *
	 * @param x The given x-coordinate
	 * @param y The given y-coordinate
	 * @return {@code true} If the cell is marked with either a cross or a circle,
	 *         {@code false} otherwise
	 */
	public boolean isMarked(int x, int y) {
		return isCrossed(x, y) || isCircled(x, y) || isWinningCell(x, y);
	}

	/**
	 * Checks if the cell at the given coordinates has at least one marked
	 * neighboring cell.
	 *
	 * @param x The x-coordinate of the cell to check.
	 * @param y The y-coordinate of the cell to check.
	 * @return {@code true} if at least one neighboring cell is marked,
	 *         {@code false} otherwise.
	 */
	private boolean hasMarkedNeighbor(int x, int y) {
		// Iterate over all neighbors (including diagonals)
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				// This will give the coordinate of the cell being checked. In other words not a
				// neighbour.
				if (dx == 0 && dy == 0) {
					continue; // Skip the current cell
				}
				// Check if the neighbor is marked
				if (isMarked(x + dx, y + dy)) {
					return true;
				}
			}
		}
		return false; // No marked neighbors found
	}

	/**
	 * Determines if the cell with specified coordinates exists outside the current
	 * board size.
	 *
	 * @param x The given x-coordinate
	 * @param y The given y-coordinate
	 * @return {@code true} If the cell is outside the current board size,
	 *         {@code false} otherwise
	 */
	public boolean isOutside(int x, int y) {
		return x > getMaxX() || x < getMinX() || y > getMaxY() || y < getMinY();
	}

	/**
	 * Resets the board to its initial state.
	 */
	public void clear() {
		minX = maxX = minY = maxY = 0;
		gameOver = false;
		board = new HashMap<>();
		board.put(new Coordinate(0, 0), Cell.EMPTY);
	}

	/**
	 * Adds the specified cell type (cross or circle) to the board at the given
	 * coordinates. Validates that the cell is playable, adjusts the board size if
	 * necessary, and updates the game state.
	 *
	 * @param x    The x-coordinate of the cell to mark.
	 * @param y    The y-coordinate of the cell to mark.
	 * @param cell The type of cell to add (CROSS or CIRCLE).
	 */
	private void play(int x, int y, Cell cell) {
		if (!isPlayable(x, y)) {
			System.out.println("Cell not playable");
			return; // cancel method
		}

		// Adjust the board size if the specified cell is on the border of the board
		if (x == getMaxX() || x == getMinX() || y == getMaxY() || y == getMinY()) {
			adjustBoardSize(x, y);
		}

		// mark the cell on the board
		board.put(new Coordinate(x, y), cell);

		// check if this move ends the game
		checkGameOver(x, y, cell);
	}

	// Create a representation of the Board as a String
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		// Print all rows and their corresponding y value
		for (int y = getMaxY(); y >= getMinY(); y--) {
			for (int x = getMinX(); x <= getMaxX(); x++) {
				result.append("+–––");
			}
			result.append("+");
			result.append("\n");
			for (int x = getMinX(); x <= getMaxX(); x++) {
				result.append("| ").append(getCell(x, y) == Cell.EMPTY ? " " : getCell(x, y));
				result.append(" ");
			}
			result.append("| ").append(y);
			result.append("\n");
		}
		for (int x = getMinX(); x <= getMaxX(); x++) {
			result.append("+–––");
		}
		result.append("+");
		result.append("\n");

		// Print the values of all the x positions at the bottom
		for (int x = getMinX(); x <= getMaxX(); x++) {
			result.append(" ");
			String xString = String.valueOf(x);
			if ("null".equals(xString)) {
				xString = " ";
			}
			// Print the value of the x center-aligned in 3 spaces
			if (xString.length() == 3) {
				result.append(xString);
			} else if (xString.length() == 2) {
				result.append(xString).append(" ");
			} else {
				result.append(" ").append(xString).append(" ");
			}
		}

		return result.toString();
	}

}
