package com.example.uberv.tic_tac_toe;

import android.app.Application;

import timber.log.Timber;

public class TicTacApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }
}
