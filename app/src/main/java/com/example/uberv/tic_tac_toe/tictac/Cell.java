package com.example.uberv.tic_tac_toe.tictac;

public class Cell {

    @Seed
    private int mContent;
    private int row, column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        clear();
    }

    public Cell(int content, int row, int column) {
        mContent = content;
        this.row = row;
        this.column = column;
    }

    public void clear() {
        mContent = Seed.EMPTY;
    }

    @Seed
    public int getContent() {
        return mContent;
    }

    public void setContent(@Seed int content) {
        mContent = content;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
