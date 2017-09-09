package com.example.uberv.tic_tac_toe.tictac;

public @interface GameState {
    int PLAYING = 0;
    int DRAW = 1;
    int CROSS_WON = 2;
    int NOUGHT_WON = 3;
}
