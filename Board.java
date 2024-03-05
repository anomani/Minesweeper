package org.cis1200.minesweeper;
import javax.swing.*;
import java.util.Random;
public class Board {
    private Cell [][] board;
    private boolean gameOver;

    private boolean hasWon;

    public Board() {
        reset();

    }

    public void setBombs() {
        int counter = 10;
        int bombsPlaced = 0;
        for (int i = 0; i < Minesweep.WIDTH; i++) {
            for (int j = 0; j < Minesweep.HEIGHT; j++) {
                board[i][j] = new Cell(false);
            }
        }
        Random rand = new Random();
        while (bombsPlaced < 10) {
            int row = rand.nextInt(10);
            int col = rand.nextInt(10);

            if (!board[row][col].hasBomb()) {
                board[row][col] = new Cell(true);
                bombsPlaced++;
            }
        }


    }




    public void nearby() {
        for (int i = 0; i < Minesweep.WIDTH; i++) {
            for (int j = 0; j < Minesweep.HEIGHT; j++) {
                if (!board[i][j].hasBomb()) {
                    int counter = 0;

                    if (i > 0 && j > 0 && board[i - 1][j - 1].hasBomb()) { //Top Left
                        counter++;
                    }
                    if (j > 0 && board[i][j - 1].hasBomb()) { //Left
                        counter++;
                    }
                    if (i < Minesweep.WIDTH - 1 && j > 0 && board[i + 1][j - 1].hasBomb()) {
                        counter++;
                    }
                    if (i > 0 && board[i - 1][j].hasBomb()) { //Above
                        counter++;
                    }
                    if (i < Minesweep.WIDTH - 1 && board[i + 1][j].hasBomb()) { //Below
                        counter++;
                    }
                    if (j < Minesweep.WIDTH - 1 && i > 0 && board[i - 1][j + 1].hasBomb()) {
                        counter++;
                    }
                    if (j < Minesweep.WIDTH - 1 && board[i][j + 1].hasBomb()) { //Right
                        counter++;
                    }
                    if (i < Minesweep.WIDTH - 1 && j < Minesweep.WIDTH - 1 &&
                            board[i + 1][j + 1].hasBomb()) {
                        counter++;
                    }

                    board[i][j].setNearbyBomb(counter);
                }

            }


        }




    }

    public void reset() {
        board = new Cell[Minesweep.WIDTH][Minesweep.HEIGHT];
        hasWon = false;
        gameOver = false;
        setBombs();
        nearby();
    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }


    public void playTurn(int x, int y) {
        Cell temp = getCell(x,y);
        if (temp.hasBomb()) {
            gameOver = true;
        }
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void setWin(boolean win) {
        hasWon = win;
    }

    public boolean getWin() {
        return hasWon;
    }


    public void print() {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j].getNearbyBomb() + ", ");
            }
            System.out.println();
        }

    }


}
