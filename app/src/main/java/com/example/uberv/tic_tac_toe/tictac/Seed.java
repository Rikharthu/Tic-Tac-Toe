package com.example.uberv.tic_tac_toe.tictac;

import android.support.annotation.IntDef;

import static com.example.uberv.tic_tac_toe.tictac.Seed.CROSS;
import static com.example.uberv.tic_tac_toe.tictac.Seed.EMPTY;
import static com.example.uberv.tic_tac_toe.tictac.Seed.NOUGHT;

@IntDef({EMPTY, CROSS, NOUGHT})
public @interface Seed {
    int EMPTY = 0;
    int CROSS = 1;
    int NOUGHT = 2;
}
