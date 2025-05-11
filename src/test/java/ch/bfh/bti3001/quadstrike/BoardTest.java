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

public class BoardTest {

    @Test
    public void testEmptyBoard() {
        Board board = new Board();
        assertEquals(1, board.getWidth());
        assertEquals(1, board.getHeight());
        assertEquals(0, board.getMinX());
        assertEquals(0, board.getMaxX());
        assertEquals(0, board.getMinY());
        assertEquals(0, board.getMaxY());
        assertFalse(board.gameOver());

        assertTrue(board.isEmpty(0, 0));
        assertFalse(board.isEmpty(1, 0));
        assertFalse(board.isEmpty(0, 1));
        assertFalse(board.isEmpty(-1, 0));
        assertFalse(board.isEmpty(0, -1));

        assertFalse(board.isMarked(0, 0));
        assertFalse(board.isMarked(1, 0));
        assertFalse(board.isMarked(0, 1));
        assertFalse(board.isMarked(-1, 0));
        assertFalse(board.isMarked(0, -1));

        assertFalse(board.isCrossed(0, 0));
        assertFalse(board.isCrossed(1, 0));
        assertFalse(board.isCrossed(0, 1));
        assertFalse(board.isCrossed(-1, 0));
        assertFalse(board.isCrossed(0, -1));

        assertFalse(board.isCircled(0, 0));
        assertFalse(board.isCircled(1, 0));
        assertFalse(board.isCircled(0, 1));
        assertFalse(board.isCircled(-1, 0));
        assertFalse(board.isCircled(0, -1));

        assertFalse(board.isOutside(0, 0));
        assertTrue(board.isOutside(1, 0));
        assertTrue(board.isOutside(0, 1));
        assertTrue(board.isOutside(-1, 0));
        assertTrue(board.isOutside(0, -1));

        assertTrue(board.isPlayable(0, 0));
        assertFalse(board.isPlayable(1, 0));
        assertFalse(board.isPlayable(0, 1));
        assertFalse(board.isPlayable(-1, 0));
        assertFalse(board.isPlayable(0, -1));

        var toString = """
                +–––+
                |   | 0
                +–––+
                  0""";
        assertEquals(toString, board.toString().trim());
    }

    @Test
    public void testBoardAfterFirstMove() {
        Board board = new Board();
        board.playCross(0, 0);
        assertEquals(3, board.getWidth());
        assertEquals(3, board.getHeight());
        assertEquals(-1, board.getMinX());
        assertEquals(1, board.getMaxX());
        assertEquals(-1, board.getMinY());
        assertEquals(1, board.getMaxY());
        assertFalse(board.gameOver());

        assertFalse(board.isEmpty(0, 0));
        assertTrue(board.isEmpty(1, 0));
        assertTrue(board.isEmpty(0, 1));
        assertTrue(board.isEmpty(-1, 0));
        assertTrue(board.isEmpty(0, -1));
        assertTrue(board.isEmpty(1, 1));
        assertTrue(board.isEmpty(1, -1));
        assertTrue(board.isEmpty(-1, 1));
        assertTrue(board.isEmpty(-1, -1));
        assertFalse(board.isEmpty(2, 0));
        assertFalse(board.isEmpty(0, 2));
        assertFalse(board.isEmpty(-2, 0));
        assertFalse(board.isEmpty(0, -2));

        assertTrue(board.isMarked(0, 0));
        assertFalse(board.isMarked(1, 0));
        assertFalse(board.isMarked(0, 1));
        assertFalse(board.isMarked(-1, 0));
        assertFalse(board.isMarked(1, 1));
        assertFalse(board.isMarked(1, -1));
        assertFalse(board.isMarked(-1, 1));
        assertFalse(board.isMarked(-1, -1));
        assertFalse(board.isMarked(0, -1));
        assertFalse(board.isMarked(2, 0));
        assertFalse(board.isMarked(0, 2));
        assertFalse(board.isMarked(-2, 0));
        assertFalse(board.isMarked(0, -2));

        assertTrue(board.isCrossed(0, 0));
        assertFalse(board.isCrossed(1, 0));
        assertFalse(board.isCrossed(0, 1));
        assertFalse(board.isCrossed(-1, 0));
        assertFalse(board.isCrossed(0, -1));
        assertFalse(board.isCrossed(1, 1));
        assertFalse(board.isCrossed(1, -1));
        assertFalse(board.isCrossed(-1, 1));
        assertFalse(board.isCrossed(-1, -1));
        assertFalse(board.isCrossed(2, 0));
        assertFalse(board.isCrossed(0, 2));
        assertFalse(board.isCrossed(-2, 0));
        assertFalse(board.isCrossed(0, -2));

        assertFalse(board.isCircled(0, 0));
        assertFalse(board.isCircled(1, 0));
        assertFalse(board.isCircled(0, 1));
        assertFalse(board.isCircled(-1, 0));
        assertFalse(board.isCircled(0, -1));
        assertFalse(board.isCircled(1, 1));
        assertFalse(board.isCircled(1, -1));
        assertFalse(board.isCircled(-1, 1));
        assertFalse(board.isCircled(-1, -1));
        assertFalse(board.isCircled(2, 0));
        assertFalse(board.isCircled(0, 2));
        assertFalse(board.isCircled(-2, 0));
        assertFalse(board.isCircled(0, -2));

        assertFalse(board.isOutside(0, 0));
        assertFalse(board.isOutside(1, 0));
        assertFalse(board.isOutside(0, 1));
        assertFalse(board.isOutside(-1, 0));
        assertFalse(board.isOutside(0, -1));
        assertFalse(board.isOutside(1, 1));
        assertFalse(board.isOutside(1, -1));
        assertFalse(board.isOutside(-1, 1));
        assertFalse(board.isOutside(-1, -1));
        assertTrue(board.isOutside(2, 0));
        assertTrue(board.isOutside(0, 2));
        assertTrue(board.isOutside(-2, 0));
        assertTrue(board.isOutside(0, -2));

        assertFalse(board.isPlayable(0, 0));
        assertTrue(board.isPlayable(1, 0));
        assertTrue(board.isPlayable(0, 1));
        assertTrue(board.isPlayable(-1, 0));
        assertTrue(board.isPlayable(0, -1));
        assertTrue(board.isPlayable(1, 1));
        assertTrue(board.isPlayable(1, -1));
        assertTrue(board.isPlayable(-1, 1));
        assertTrue(board.isPlayable(-1, -1));
        assertFalse(board.isPlayable(2, 0));
        assertFalse(board.isPlayable(0, 2));
        assertFalse(board.isPlayable(-2, 0));
        assertFalse(board.isPlayable(0, -2));

        var toString = """
                +–––+–––+–––+
                |   |   |   | 1
                +–––+–––+–––+
                |   | X |   | 0
                +–––+–––+–––+
                |   |   |   | -1
                +–––+–––+–––+
                 -1   0   1""";
        assertEquals(toString, board.toString().trim());
    }

    @Test
    public void testLargeBoards() {
        Board board = new Board();
        for (int x = 0; x < 10000; x++) {
            board.playCross(x, x % 2);
            board.playCircle(x, (x + 1) % 2);
        }
        assertEquals(10002, board.getWidth());
        assertEquals(4, board.getHeight());
        assertEquals(-1, board.getMinX());
        assertEquals(10000, board.getMaxX());
        assertEquals(-1, board.getMinY());
        assertEquals(2, board.getMaxY());


        board.clear();
        for (int y = 0; y < 10000; y++) {
            board.playCross(y % 2, y);
            board.playCircle((y + 1) % 2, y);
        }
        assertEquals(4, board.getWidth());
        assertEquals(10002, board.getHeight());
        assertEquals(-1, board.getMinX());
        assertEquals(2, board.getMaxX());
        assertEquals(-1, board.getMinY());
        assertEquals(10000, board.getMaxY());

        board.clear();
        for (int i = 0; i < 10000; i = i + 2) {
            board.playCross(i, i);
            board.playCircle(i + 1, i + 1);
        }
        assertEquals(10002, board.getWidth());
        assertEquals(10002, board.getHeight());
        assertEquals(-1, board.getMinX());
        assertEquals(10000, board.getMaxX());
        assertEquals(-1, board.getMinY());
        assertEquals(10000, board.getMaxY());

        board.clear();
        for (int i = 0; i < 10000; i = i + 2) {
            board.playCross(-i, -i);
            board.playCircle(-i - 1, -i - 1);
        }
        assertEquals(10002, board.getWidth());
        assertEquals(10002, board.getHeight());
        assertEquals(-10000, board.getMinX());
        assertEquals(1, board.getMaxX());
        assertEquals(-10000, board.getMinY());
        assertEquals(1, board.getMaxY());
    }

    @Test
    public void testExample() {
        Board board = new Board();
        // Alice: 0/0, 1/-1, 2/-1,  3/0, 2/-2, 4/-1, 5/-2, 6/-3
        // Bob  : 1/0,  2/0, 3/-1, 1/-2, 3/-3,  4/0,  2/1
        var move01 = new Move(0, 0);
        var move02 = new Move(1, 0);
        var move03 = new Move(1, -1);
        var move04 = new Move(2, 0);
        var move05 = new Move(2, -1);
        var move06 = new Move(3, -1);
        var move07 = new Move(3, 0);
        var move08 = new Move(1, -2);
        var move09 = new Move(2, -2);
        var move10 = new Move(3, -3);
        var move11 = new Move(4, -1);
        var move12 = new Move(4, 0);
        var move13 = new Move(5, -2);
        var move14 = new Move(2, 1);
        var move15 = new Move(6, -3);
        var movesAlice = List.of(move01, move03, move05, move07, move09, move11, move13, move15);
        var movesBob = List.of(move02, move04, move06, move08, move10, move12, move14);
        for (int i = 0; i < 7; i++) {
            board.playCross(movesAlice.get(i).getX(), movesAlice.get(i).getY());
            board.playCircle(movesBob.get(i).getX(), movesBob.get(i).getY());
        }
        // before last move
        assertFalse(board.gameOver());
        for (int x = -2; x <= 8; x++) {
            for (int y = -5; y <= 3; y++) {
                if (board.isMarked(x, y) || board.isOutside(x, y)) {
                    assertFalse(board.isPlayable(x, y));
                }
            }
        }
        assertTrue(board.isPlayable(-1, -1));
        assertTrue(board.isPlayable(-1, 0));
        assertTrue(board.isPlayable(-1, 1));
        assertTrue(board.isPlayable(0, 1));
        assertTrue(board.isPlayable(1, 1));
        assertTrue(board.isPlayable(1, 2));
        assertTrue(board.isPlayable(2, 2));
        assertTrue(board.isPlayable(3, 2));
        assertTrue(board.isPlayable(3, 1));
        assertTrue(board.isPlayable(4, 1));
        assertTrue(board.isPlayable(5, 1));
        assertTrue(board.isPlayable(5, 0));
        assertTrue(board.isPlayable(5, -1));
        assertTrue(board.isPlayable(6, -1));
        assertTrue(board.isPlayable(6, -2));
        assertTrue(board.isPlayable(6, -3));
        assertTrue(board.isPlayable(5, -3));
        assertTrue(board.isPlayable(4, -3));
        assertTrue(board.isPlayable(4, -2));
        assertTrue(board.isPlayable(3, -2));
        assertTrue(board.isPlayable(4, -4));
        assertTrue(board.isPlayable(3, -4));
        assertTrue(board.isPlayable(2, -4));
        assertTrue(board.isPlayable(2, -3));
        assertTrue(board.isPlayable(1, -3));
        assertTrue(board.isPlayable(0, -3));
        assertTrue(board.isPlayable(0, -2));
        assertTrue(board.isPlayable(0 , -1));
        assertTrue(board.isPlayable(-1, -1));

        // after last move
        board.playCross(move15.getX(), move15.getY());
        assertTrue(board.gameOver());
        assertEquals(9, board.getWidth());
        assertEquals(7, board.getHeight());
        assertEquals(-1, board.getMinX());
        assertEquals(7, board.getMaxX());
        assertEquals(-4, board.getMinY());
        assertEquals(2, board.getMaxY());
        int counter = 0;
        for (int x = -2; x <= 8; x++) {
            for (int y = -5; y <= 3; y++) {
                assertFalse(board.isPlayable(x, y));
                if (x < board.getMinX() || x > board.getMaxX() || y < board.getMinY() || y > board.getMaxY()) {
                    assertTrue(board.isOutside(x, y));
                }
                if (board.isMarked(x, y)) {
                    counter++;
                }
            }
        }
        assertEquals(15, counter);

        var toString = """
                +–––+–––+–––+–––+–––+–––+–––+–––+–––+
                |   |   |   |   |   |   |   |   |   | 2
                +–––+–––+–––+–––+–––+–––+–––+–––+–––+
                |   |   |   | O |   |   |   |   |   | 1
                +–––+–––+–––+–––+–––+–––+–––+–––+–––+
                |   | X | O | O | # | O |   |   |   | 0
                +–––+–––+–––+–––+–––+–––+–––+–––+–––+
                |   |   | X | X | O | # |   |   |   | -1
                +–––+–––+–––+–––+–––+–––+–––+–––+–––+
                |   |   | O | X |   |   | # |   |   | -2
                +–––+–––+–––+–––+–––+–––+–––+–––+–––+
                |   |   |   |   | O |   |   | # |   | -3
                +–––+–––+–––+–––+–––+–––+–––+–––+–––+
                |   |   |   |   |   |   |   |   |   | -4
                +–––+–––+–––+–––+–––+–––+–––+–––+–––+
                 -1   0   1   2   3   4   5   6   7""";
        assertEquals(toString, board.toString().trim());
    }

    @Test
    public void testExceptions() {
        Board board = new Board();
        assertThrowsExactly(RuntimeException.class, () -> board.playCross(0, -1));
        board.playCross(0, 0);
        board.playCircle(1, 1);
        board.playCross(2, 2);
        board.playCircle(3, 3);
        assertThrowsExactly(RuntimeException.class, () -> board.playCross(4, -1));
        assertThrowsExactly(RuntimeException.class, () -> board.playCross(4, 0));
        assertThrowsExactly(RuntimeException.class, () -> board.playCross(4, 1));
        assertThrowsExactly(RuntimeException.class, () -> board.playCross(3, -1));
        assertThrowsExactly(RuntimeException.class, () -> board.playCross(3, 0));
        assertThrowsExactly(RuntimeException.class, () -> board.playCross(2, -1));
        assertThrowsExactly(RuntimeException.class, () -> board.playCross(-1, -4));
        assertThrowsExactly(RuntimeException.class, () -> board.playCross(-1, 4));
        assertThrowsExactly(RuntimeException.class, () -> board.playCross(0, 4));
        assertThrowsExactly(RuntimeException.class, () -> board.playCross(1, 4));
        assertThrowsExactly(RuntimeException.class, () -> board.playCross(-1, 3));
        assertThrowsExactly(RuntimeException.class, () -> board.playCross(0, 3));
        assertThrowsExactly(RuntimeException.class, () -> board.playCross(-1, 2));
    }

}