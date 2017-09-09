package com.example.uberv.tic_tac_toe;

import com.example.uberv.tic_tac_toe.tictac.Board;

public abstract class TicTacContract {
    public static interface View {
        void updateUI(Board board);

        void onResult(int currentState);
    }
}
