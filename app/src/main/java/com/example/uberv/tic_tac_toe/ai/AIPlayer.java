package com.example.uberv.tic_tac_toe.ai;

public class AIPlayer {
    public static final int NUM_ROWS = 3;
    public static final int NUM_COLS = 3;

    protected Cell[] mCells;
    protected Seed mSeed;
    protected Seed mOpponentSeed;

    public AIPlayer(Board board) {
        mCells = board.getCells();
    }

    public void setSeed(Seed seed) {
        mSeed = seed;a
        mOpponentSeed = (mSeed == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS:
    }

    abstract int[] move();
}
