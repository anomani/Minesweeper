package org.cis1200.minesweeper;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class Test {

    @org.junit.jupiter.api.Test
    public void test10Bombs() {
        Board t = new Board();
        t.setBombs();

        int counter = 0;

        for (int i = 0; i < Minesweep.WIDTH; i++) {
            for (int j = 0; j < Minesweep.HEIGHT; j++) {
                if (t.getCell(j,i).hasBomb()) {
                    counter++;
                }
            }
        }
        assertEquals(10, counter);
    }

    @org.junit.jupiter.api.Test
    public void testNearby() {
        Board t = new Board();
        t.setBombs();
        t.nearby();

        int num = t.getCell(5,5).getNearbyBomb();

        if (!t.getCell(5,5).hasBomb()) {
            int counter = 0;

            if (t.getCell(4, 4).hasBomb()) { //Top Left
                counter++;
            }
            if (t.getCell(5, 4).hasBomb()) { //Left
                counter++;
            }
            if (t.getCell(6, 4).hasBomb()) { //Bottom Left
                counter++;
            }
            if (t.getCell(4, 5).hasBomb()) { //Above
                counter++;
            }
            if (t.getCell(6, 5).hasBomb()) { //Below
                counter++;
            }
            if (t.getCell(4, 6).hasBomb()) { //Top Right
                counter++;
            }
            if (t.getCell(5, 6).hasBomb()) { //Right
                counter++;
            }
            if (t.getCell(6, 6).hasBomb()) { //Bottom Right
                counter++;
            }

            assertEquals(num, counter);


        }
    }

    @org.junit.jupiter.api.Test
    public void testGameOver() {

        Board game = new Board();
        assertFalse(game.getGameOver());
    }

    @org.junit.jupiter.api.Test
    public void testSetupNotNull() {
        Board game = new Board();
        for (int i = 0; i < Minesweep.WIDTH; i++) {
            for (int j = 0; j < Minesweep.HEIGHT; j++) {
                assertNotNull(game.getCell(j,i));
            }
        }
    }

    @org.junit.jupiter.api.Test
    public void testBoard() {
        Board game = new Board();
        assertFalse(game.getWin());

    }




}
