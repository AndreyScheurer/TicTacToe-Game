/*
 * Copyright (c) 2024 by Bern University of Applied Sciences
 * All rights reserved. Unauthorized reproduction and distribution of this file
 * for purposes other than personal educational use is strictly prohibited. This
 * prohibition includes uploading this file, in whole or in part, to any AI services,
 * including but not limited to ChatGPT.
 */
package ch.github.andreyscheurer.tictactoe;

import java.util.List;

/**
 * Represents a QuadStrike game session between two players. Tracks player
 * names, moves, the current state of the game board, and the current player.
 */
public class Game {

	private final String playerName1; // Name of Player 1 (for example Alice)
	private final String playerName2; // Name of Player 2 (for example Bob)
	private final Board board; // The game board
	private boolean isPlayer1Turn; // Tracks whose turn it is
	private final List<Move> moves; // List of moves played so far

	/**
	 * Initializes a new game with the given player names and an empty game board.
	 * Player 1 will always be the first to play.
	 *
	 * @param playerName1 The name of the first player
	 * @param playerName2 The name of the second player
	 */
	public Game(String playerName1, String playerName2) {
		this.playerName1 = playerName1;
		this.playerName2 = playerName2;
		this.board = new Board();
		this.isPlayer1Turn = true; // Player 1 starts
		this.moves = new java.util.ArrayList<>();
	}

	/**
	 * Restarts the game by clearing all moves and resetting the board and current
	 * player.
	 */
	public void restart() {
		board.clear();
		moves.clear();
		isPlayer1Turn = true;
	}

	/**
	 * Returns the game board in its current state.
	 *
	 * @return The game board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Gets the name of the first player.
	 *
	 * @return The name of Player 1
	 */
	public String getPlayerName1() {
		return playerName1;
	}

	/**
	 * Gets the name of the second player.
	 *
	 * @return The name of Player 2.
	 */
	public String getPlayerName2() {
		return playerName2;
	}

	/**
	 * Gets the name of the player whose turn it is to play, or the name of the
	 * winner if the game is over.
	 *
	 * @return The name of the current player or winner
	 */
	public String getCurrentPlayer() {
		if (board.gameOver()) {
			return isPlayer1Turn ? playerName2 : playerName1; // Last player won
		}
		return isPlayer1Turn ? playerName1 : playerName2; // Current player
	}

	/**
	 * Gets the number of moves played so far.
	 *
	 * @return the number of moves
	 */
	public int getNumberOfMoves() {
		return moves.size();
	}

	/**
	 * Plays a list of moves in sequence.
	 *
	 * @param moves The list of moves to play
	 */
	public void play(List<Move> moves) {
		for (Move move : moves) {
			play(move);
			if (board.gameOver()) {
				break; // Stop playing if the game is over
			}
		}
	}

	/**
	 * Plays a single move for the current player. Updates the game board and
	 * switches to the next player if the game is not over.
	 *
	 * @param move The move to play
	 * @throws RuntimeException if the move is invalid or not playable
	 */
	public void play(Move move) {
		if (!checkMove(move)) {
			throw new RuntimeException("Invalid move: Cell is not playable.");
		}
		if (isPlayer1Turn) {
			board.playCross(move.getX(), move.getY());
		} else {
			board.playCircle(move.getX(), move.getY());
		}
		moves.add(move);
		isPlayer1Turn = !isPlayer1Turn; // Switch turn
	}

	/**
	 * Checks if a given move can be played.
	 *
	 * @param move The move to check
	 * @return {@code true} if the move is playable, {@code false} otherwise
	 */
	public boolean checkMove(Move move) {
		return board.isPlayable(move.getX(), move.getY());
	}

	/**
	 * Gets the list of moves played so far in the game.
	 *
	 * @return The list of moves
	 */
	public List<Move> getMoves() {
		return moves;
	}

	/**
	 * Checks if the game is over.
	 *
	 * @return {@code true} if the game is over, {@code false} otherwise
	 */
	public boolean gameOver() {
		return board.gameOver();
	}
}
