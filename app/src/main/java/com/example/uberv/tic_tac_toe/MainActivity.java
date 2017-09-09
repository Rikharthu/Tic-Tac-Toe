package com.example.uberv.tic_tac_toe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.uberv.tic_tac_toe.tictac.Board;
import com.example.uberv.tic_tac_toe.tictac.GameState;
import com.example.uberv.tic_tac_toe.tictac.Seed;
import com.example.uberv.tic_tac_toe.views.TicTacBoardView;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity
        implements TicTacBoardView.TicTacBoardClickListener, TicTacContract.View {

    @BindView(R.id.board)
    TicTacBoardView mBoard;

    private TicTacPresenter mPresenter;
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

        mPresenter = new TicTacPresenter(this);
    }

    @Override
    public void onBoardItemClick(int row, int column, @Seed int currentStatus) {
        Timber.d("row: " + row + ", column: " + column + ", status: " + currentStatus);
////        mTicTacBot.onHumanTurn(row, column);
//        if (currentStatus == TicTacBoardView.NONE) {
//            // empty, can do a turn
//            if (mIsHumanTurn) {
//                mIsHumanTurn = false;
//                mBoard.setStatus(row, column, TicTacBoardView.CIRCLE);
//            } else {
//                mIsHumanTurn = true;
//                mBoard.setStatus(row, column, TicTacBoardView.CROSS);
//            }
//        } else {
//            // занято
//        }

        mPresenter.onPlayerMoved(row, column);
    }

    @Override
    public void updateUI(Board board) {
        Timber.d("Updating UI for board: " + board.toString());
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                mBoard.setStatus(row, column, board.getCellAt(row, column).getContent());
            }
        }
    }

    @Override
    public void onResult(int currentState) {
        switch (currentState) {
            case GameState.CROSS_WON:
                Toast.makeText(this, "Cross won", Toast.LENGTH_SHORT).show();
                break;
            case GameState.NOUGHT_WON:
                Toast.makeText(this, "Nought won", Toast.LENGTH_SHORT).show();
                break;
            case GameState.DRAW:
                Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
