package com.example.uberv.tic_tac_toe.views;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.Size;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.uberv.tic_tac_toe.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class TicTacBoard extends LinearLayout {

    private int mCrossColor;
    private int mCircleColor;
    @Size(3)
    @TicTacStatus
    private int[][] mStatuses;
    private TicTacBoardClickListener mBoardClickListener;

    public TicTacBoard(Context context) {
        super(context);
        init();
    }

    public TicTacBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO check which is smalled and pass further
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    private void init() {
        // Configure
        // TODO does <merge> tag passes information here?
        // TODO make it to be squared
        setOrientation(LinearLayout.VERTICAL);
//        set

        // Inflate view
        LayoutInflater.from(getContext()).inflate(R.layout.tic_tac_board, this, true);

        // Initialize fields
        mStatuses = new int[3][3];
        LinearLayout row;
        View image;
        for (int i = 0; i < 3; i++) {
            row = (LinearLayout) getChildAt(i);
            for (int j = 0; j < 3; j++) {
                image = row.getChildAt(j);
                image.setOnClickListener(createFieldClickListener(i, j));
                mStatuses[i][j] = TicTacStatus.NONE;
            }
        }

        mCrossColor = Color.rgb(255, 0, 0);
        mCircleColor = Color.rgb(0, 0, 255);

        adjustImages();
        drawGrid();
    }

    private void drawGrid() {
//        setBackground(R.drawable.grid);
        setBackgroundResource(R.drawable.grid);
    }

    private OnClickListener createFieldClickListener(final int row, final int column) {
        return new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBoardClickListener != null) {
                    mBoardClickListener.onBoardItemClick(row, column, mStatuses[row][column]);
                }
            }
        };
    }

    private void adjustImages() {
        LinearLayout row;
        ImageView image;
        int imageResource, tintColor;
        for (int i = 0; i < 3; i++) {
            row = (LinearLayout) getChildAt(i);
            for (int j = 0; j < 3; j++) {
                image = (ImageView) row.getChildAt(j);
                switch (mStatuses[i][j]) {
                    case TicTacStatus.CIRCLE:
                        imageResource = R.drawable.circle;
                        tintColor = mCircleColor;
                        break;
                    case TicTacStatus.CROSS:
                        imageResource = R.drawable.cross;
                        tintColor = mCrossColor;
                        break;
                    case TicTacStatus.NONE:
                    default:
                        imageResource = -1;
                        tintColor = -1;
                        break;
                }
                if (imageResource == -1) {
                    image.setImageDrawable(null);
                    image.setImageResource(R.mipmap.ic_launcher);
                } else {
                    image.setImageResource(imageResource);
                    image.getDrawable().setTint(tintColor);
                }
            }
        }
    }

    public void setStatus(int row, int column, @TicTacStatus int status) {
        mStatuses[row][column] = status;
        // TODO calculate()
        adjustImages();
    }

    public TicTacBoardClickListener getBoardClickListener() {
        return mBoardClickListener;
    }

    public void setBoardClickListener(TicTacBoardClickListener boardClickListener) {
        mBoardClickListener = boardClickListener;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TicTacStatus.NONE, TicTacStatus.CROSS, TicTacStatus.CIRCLE})
    public static @interface TicTacStatus {
        int CROSS = 0;
        int CIRCLE = 1;
        int NONE = 2;
    }

    public static interface TicTacBoardClickListener {
        void onBoardItemClick(@IntRange(from = 0, to = 2) int row, @IntRange(from = 0, to = 2) int column, @TicTacStatus int currentStatus);
    }
}
