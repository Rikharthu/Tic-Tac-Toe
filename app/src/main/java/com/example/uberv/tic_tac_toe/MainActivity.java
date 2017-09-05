package com.example.uberv.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.uberv.tic_tac_toe.views.TicTacBoard;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements TicTacBoard.TicTacBoardClickListener {

    @BindView(R.id.board)
    TicTacBoard mBoard;

    private TicTacBot mTicTacBot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mBoard.setStatus(0, 0, TicTacBoard.TicTacStatus.CIRCLE);
        mBoard.setStatus(0, 1, TicTacBoard.TicTacStatus.CROSS);
        mBoard.setStatus(1, 1, TicTacBoard.TicTacStatus.CROSS);
        mBoard.setStatus(2, 2, TicTacBoard.TicTacStatus.CIRCLE);

        mBoard.setBoardClickListener(this);

        mTicTacBot = new TicTacBot();
    }

    @Override
    public void onBoardItemClick(int row, int column, @TicTacBoard.TicTacStatus int currentStatus) {
        Timber.d("row: " + row + ", column: " + column + ", status: " + currentStatus);
        mTicTacBot.onHumanTurn(row, column);
    }
}
