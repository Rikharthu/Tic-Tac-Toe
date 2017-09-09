package com.example.uberv.tic_tac_toe.tictac;

import java.util.Arrays;

import timber.log.Timber;

public class Board {

    public static final int ROWS = 3;
    public static final int COLUMNS = 3;

    private Cell[][] mCells;
    private int mCurrentRow, mCurrentColumn;

    public Board() {
        mCells = new Cell[ROWS][COLUMNS];
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                mCells[row][column] = new Cell(row, column);
            }
        }
    }

    public void init() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                mCells[row][column].clear();
            }
        }
    }

    /**
     * Return true if it is a draw (i.e., no more EMPTY cells left)
     */
    public boolean isDraw() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (mCells[row][column].getContent() == Seed.EMPTY) {
                    // has empty cell
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Return true if the player with "theSeed" has won after placing at
     * (currentRow, currentCol)
     */
    public boolean hasWon(@Seed int theSeed) {
        return (    // 3-in-the-row
                mCells[mCurrentRow][0].getContent() == theSeed
                        && mCells[mCurrentRow][1].getContent() == theSeed
                        && mCells[mCurrentRow][2].getContent() == theSeed
                        ||  // 3-in-the-column
                        mCells[0][mCurrentColumn].getContent() == theSeed
                                && mCells[1][mCurrentColumn].getContent() == theSeed
                                && mCells[2][mCurrentColumn].getContent() == theSeed
                        ||
                        mCurrentRow == mCurrentColumn // 3-in-the-diagonal
                                && mCells[0][0].getContent() == theSeed
                                && mCells[1][1].getContent() == theSeed
                                && mCells[2][2].getContent() == theSeed
                        ||
                        mCurrentRow + mCurrentColumn == 2 // 3-in-the-opposite-diagonal
                                && mCells[0][2].getContent() == theSeed
                                && mCells[1][1].getContent() == theSeed
                                && mCells[2][0].getContent() == theSeed);
    }

    public void paint() {
        // TODO implement
    }

    public void setCell(Cell cell) {
        if (mCells[cell.getRow()][cell.getColumn()].getContent() == Seed.EMPTY) {
            mCells[cell.getRow()][cell.getColumn()].setContent(cell.getContent());
        } else {
            // TODO error, occupied
            Timber.d("ERROR");
        }
    }

    public Cell getCellAt(int row, int column) {
        return mCells[row][column];
    }

    @Override
    public String toString() {
        return "Board{" +
                "mCells=" + Arrays.toString(mCells) +
                ", mCurrentRow=" + mCurrentRow +
                ", mCurrentColumn=" + mCurrentColumn +
                '}';
    }
}
