/*
 * Copyright (c) 2024 by Bern University of Applied Sciences
 * All rights reserved. Unauthorized reproduction and distribution of this file
 * for purposes other than personal educational use is strictly prohibited. This
 * prohibition includes uploading this file, in whole or in part, to any AI services,
 * including but not limited to ChatGPT.
 */
package ch.bfh.bti3001.quadstrike;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    // Alice: 0/0, 1/-1, 2/-1,  3/0, 2/-2, 4/-1, 5/-2, 6/-3
    // Bob  : 1/0,  2/0, 3/-1, 1/-2, 3/-3,  4/0,  2/1
    private final static Move move01 = new Move(0, 0);
    private final static Move move02 = new Move(1, 0);
    private final static Move move03 = new Move(1, -1);
    private final static Move move04 = new Move(2, 0);
    private final static Move move05 = new Move(2, -1);
    private final static Move move06 = new Move(3, -1);
    private final static Move move07 = new Move(3, 0);
    private final static Move move08 = new Move(1, -2);
    private final static Move move09 = new Move(2, -2);
    private final static Move move10 = new Move(3, -3);
    private final static Move move11 = new Move(4, -1);
    private final static Move move12 = new Move(4, 0);
    private final static Move move13 = new Move(5, -2);
    private final static Move move14 = new Move(2, 1);
    private final static Move move15 = new Move(6, -3);

    @Test
    public void testConstructor() {
        var player1 = "Alice";
        var player2 = "Bob";
        var game = new Game(player1, player2);
        assertEquals(player1, game.getPlayerName1());
        assertEquals(player2, game.getPlayerName2());
        assertEquals(player1, game.getCurrentPlayer());
        assertEquals(0, game.getNumberOfMoves());
        assertTrue(game.getMoves().isEmpty());
        assertFalse(game.gameOver());
        assertTrue(game.checkMove(new Move(0, 0)));
        assertFalse(game.checkMove(new Move(0, 1)));
        assertFalse(game.checkMove(new Move(1, 0)));
        assertFalse(game.checkMove(new Move(0, -1)));
        assertFalse(game.checkMove(new Move(-1, 0)));
    }

    @Test
    public void testPlay() {
        var player1 = "Alice";
        var player2 = "Bob";
        var game = new Game(player1, player2);
        var currentPlayer = player1;

        int counter = 0;
        for (var move : List.of(move01, move02, move03, move04, move05, move06, move07, move08, move09, move10, move11, move12, move13, move14)) {
            assertTrue(game.checkMove(move));
            game.play(move);
            counter++;
            currentPlayer = counter % 2 == 0 ? player1 : player2;
            assertEquals(currentPlayer, game.getCurrentPlayer());
            assertEquals(counter, game.getNumberOfMoves());
            assertEquals(counter, game.getMoves().size());
            assertTrue(game.getMoves().contains(move));
            assertFalse(game.checkMove(move));
            assertFalse(game.gameOver());
        }
        game.play(move15);
        assertEquals(15, game.getNumberOfMoves());
        assertTrue(game.gameOver());
        assertEquals(player1, game.getCurrentPlayer());
    }

    @Test
    public void testPlayList() {
        var player1 = "Alice";
        var player2 = "Bob";
        var game = new Game(player1, player2);
        var moves = List.of(move01, move02, move03, move04, move05, move06, move07, move08, move09, move10, move11, move12, move13, move14, move15);
        game.play(moves);
        assertEquals(15, game.getNumberOfMoves());
        assertTrue(game.gameOver());
        assertEquals(player1, game.getCurrentPlayer());
    }

    @Test
    void restart() {
        var player1 = "Alice";
        var player2 = "Bob";
        var game = new Game(player1, player2);
        var moves = List.of(move01, move02, move03, move04, move05, move06, move07, move08, move09, move10, move11, move12, move13, move14, move15);
        game.play(moves);
        game.restart();
        assertEquals(player1, game.getPlayerName1());
        assertEquals(player2, game.getPlayerName2());
        assertEquals(player1, game.getCurrentPlayer());
        assertEquals(0, game.getNumberOfMoves());
        assertTrue(game.getMoves().isEmpty());
        assertFalse(game.gameOver());
        assertTrue(game.checkMove(new Move(0, 0)));
    }

    @Test
    void checkMove() {
        var player1 = "Alice";
        var player2 = "Bob";
        var game = new Game(player1, player2);
        var moves = List.of(move01, move02, move03, move04, move05, move06, move07, move08, move09, move10, move11, move12, move13, move14);
        game.play(moves);
        for (var move : moves) {
            assertFalse(game.checkMove(move));
        }
        var playableMoves = List.of(
                new Move(-1, -1),
                new Move(-1, 0),
                new Move(-1, 1),
                new Move(0, 1),
                new Move(1, 1),
                new Move(1, 2),
                new Move(2, 2),
                new Move(3, 2),
                new Move(3, 1),
                new Move(4, 1),
                new Move(5, 1),
                new Move(5, 0),
                new Move(5, -1),
                new Move(6, -1),
                new Move(6, -2),
                new Move(6, -3),
                new Move(5, -3),
                new Move(4, -3),
                new Move(4, -2),
                new Move(3, -2),
                new Move(4, -4),
                new Move(3, -4),
                new Move(2, -4),
                new Move(2, -3),
                new Move(1, -3),
                new Move(0, -3),
                new Move(0, -2),
                new Move(0 , -1),
                new Move(-1, -1));
        for (var move : playableMoves) {
            assertTrue(game.checkMove(move));
        }
        for (int x = -2; x <= 8; x++) {
            for (int y = -5; y <= 3; y++) {
                var move = new Move(x, y);
                if (!moves.contains(move) && !playableMoves.contains(move)) {
                    assertFalse(game.checkMove(move));
                }
            }
        }
    }

}