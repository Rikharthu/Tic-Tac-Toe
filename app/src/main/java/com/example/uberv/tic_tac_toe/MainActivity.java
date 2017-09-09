package com.example.uberv.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.uberv.tic_tac_toe.views.TicTacBoardView;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements TicTacBoardView.TicTacBoardClickListener {

    @BindView(R.id.board)
    TicTacBoardView mBoard;

    private TicTacBot mTicTacBot;
    private boolean mIsHumanTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

//        mBoard.setStatus(0, 0, TicTacBoardView.TicTacStatus.CIRCLE);
//        mBoard.setStatus(0, 1, TicTacBoardView.TicTacStatus.CROSS);
//        mBoard.setStatus(1, 1, TicTacBoardView.TicTacStatus.CROSS);
//        mBoard.setStatus(2, 2, TicTacBoardView.TicTacStatus.CIRCLE);

        mBoard.setBoardClickListener(this);

        mTicTacBot = new TicTacBot();
    }

    @Override
    public void onBoardItemClick(int row, int column, @TicTacBoardView.TicTacStatus int currentStatus) {
        Timber.d("row: " + row + ", column: " + column + ", status: " + currentStatus);
//        mTicTacBot.onHumanTurn(row, column);
        if (currentStatus == TicTacBoardView.NONE) {
            // empty, can do a turn
            if (mIsHumanTurn) {
                mIsHumanTurn = false;
                mBoard.setStatus(row, column, TicTacBoardView.CIRCLE);
            } else {
                mIsHumanTurn = true;
                mBoard.setStatus(row, column, TicTacBoardView.CROSS);
            }
        } else {
            // занято
        }
    }
}
