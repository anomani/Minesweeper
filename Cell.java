package org.cis1200.minesweeper;

public class Cell {


    private boolean bomb;
    private boolean flag;

    private int nearbyBomb;

    private boolean revealed;


    public Cell(boolean hasBomb) {
        bomb = hasBomb;
        flag = false;
        nearbyBomb = 0;
        revealed = false;
    }

    public void setFlag() {
        flag = !flag;
    }

    public boolean hasBomb() {
        return bomb;
    }

    public void setNearbyBomb(int nearby) {
        nearbyBomb = nearby;
    }

    public int getNearbyBomb() {
        return nearbyBomb;
    }

    public void setReveal(boolean reveal) {
        revealed = reveal;
    }

    public boolean getReveal() {
        return revealed;
    }



}
