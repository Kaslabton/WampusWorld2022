package com.mygdx.game;

public class Location {
    private int row;
    private int col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public boolean equals(Location other){
        return row == other.row && col == other.col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
