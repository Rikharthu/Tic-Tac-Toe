package com.example.uberv.tic_tac_toe;

import com.example.uberv.tic_tac_toe.tictac.Board;
import com.example.uberv.tic_tac_toe.tictac.Cell;
import com.example.uberv.tic_tac_toe.tictac.GameState;
import com.example.uberv.tic_tac_toe.tictac.Seed;

import timber.log.Timber;

public class TicTacPresenter {

    private TicTacContract.View mView;
    private Board mBoard;
    @GameState
    private int mCurrentState;
    @Seed
    private int mCurrentPlayer;


    public TicTacPresenter(TicTacContract.View view) {
        mView = view;

        mBoard = new Board();

        initGame();
        startGame();
    }

    private void initGame() {
        mBoard.init();  // clear the board contents
        mCurrentPlayer = Seed.CROSS;       // CROSS plays first
        mCurrentState = GameState.PLAYING; // ready to play
    }

    /**
     * Initialize the game-board contents and the current states
     */
    public void startGame() {
//        do {
//            playerMove(mCurrentPlayer);
//            // TODO draw board.paint();
//            updateGame(mCurrentPlayer);
//
//            // Print message if game-over
//            if (mCurrentState == GameState.CROSS_WON) {
//                // TODO
//            } else if (mCurrentState == GameState.NOUGHT_WON) {
//                // TODO
//            } else if (mCurrentState == GameState.DRAW) {
//                // TODO
//            }
//
//            mCurrentPlayer = (mCurrentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
//        } while (mCurrentState == GameState.PLAYING); // repeat until game-over
    }

    public void onPlayerMoved(int row, int column) {
        Timber.d("on player moved");
        // TODO check if is valid
        mBoard.setCell(new Cell(mCurrentPlayer, row, column));
        updateGame(mCurrentPlayer);
        updateUI();
        if(mCurrentState!=GameState.PLAYING){
            mView.onResult(mCurrentState);
        }


        mCurrentPlayer = (mCurrentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
    }

    private void updateGame(@Seed int seed) {
        if (mBoard.hasWon(seed)) {
            mCurrentState = (seed == Seed.CROSS) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
        } else if (mBoard.isDraw()) {
            mCurrentState = GameState.DRAW;
        }
    }

    private void updateUI() {
        mView.updateUI(mBoard);
    }
}
