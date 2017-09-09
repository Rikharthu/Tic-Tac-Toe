package com.example.uberv.tic_tac_toe.views;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.Size;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.uberv.tic_tac_toe.R;
import com.example.uberv.tic_tac_toe.tictac.Seed;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class TicTacBoardView extends FrameLayout {

    // Line Statuses
    // FIRST_ROW, SECOND_ROW,THIRD_ROW,FIRST_COLUMN, SECOND_COLUMN,THIRD_COLUMN,FORWARD_SLASH,BACKWARD_SLASH
    public static final int LINE_NONE = 1000;
    public static final int LINE_FIRST_ROW = 1001;
    public static final int LINE_SECOND_ROW = 1002;
    public static final int LINE_THIRD_ROW = 1003;
    public static final int LINE_FIRST_COLUMN = 1004;
    public static final int LINE_SECOND_COLUMN = 1005;
    public static final int LINE_THIRD_COLUMN = 1006;
    public static final int LINE_FORWARD_SLASH = 1007;
    public static final int LINE_BACKWARD_SLASH = 1008;
    public static final int NONE = 2;

    @BindView(R.id.root)
    LinearLayout mLinearRoot;
    @BindView(R.id.line)
    ImageView mLine;

    private int mCrossColor;
    private int mCircleColor;
    @Size(3)
    @Seed
    private int[][] mStatuses;
    private TicTacBoardClickListener mBoardClickListener;

    public TicTacBoardView(Context context) {
        super(context);
        init();
    }

    public TicTacBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO check which is smalled and pass further
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    private void init() {
//        ButterKnife.bind(this);
        // Configure
        // TODO does <merge> tag passes information here?
        // TODO make it to be squared
//        set

        // Inflate view
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_tic_tac_board, this, true);
        ButterKnife.bind(this);

        mLinearRoot.setOrientation(LinearLayout.VERTICAL);
        // Initialize fields
        mStatuses = new int[3][3];
        LinearLayout row;
        View image;
        for (int i = 0; i < 3; i++) {
            row = (LinearLayout) mLinearRoot.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                image = row.getChildAt(j);
                image.setOnClickListener(createFieldClickListener(i, j));
                mStatuses[i][j] = Seed.EMPTY;
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
            row = (LinearLayout) mLinearRoot.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                image = (ImageView) row.getChildAt(j);
                switch (mStatuses[i][j]) {
                    case Seed.NOUGHT:
                        imageResource = R.drawable.circle;
                        tintColor = mCircleColor;
                        break;
                    case Seed.CROSS:
                        imageResource = R.drawable.cross;
                        tintColor = mCrossColor;
                        break;
                    case Seed.EMPTY:
                    default:
                        imageResource = -1;
                        tintColor = -1;
                        break;
                }
                if (imageResource == -1) {
                    image.setImageDrawable(null);
//                    image.setImageResource(R.mipmap.ic_launcher);
                } else {
                    image.setImageResource(imageResource);
                    image.getDrawable().setTint(tintColor);
                }
            }
        }
    }

    public void setStatus(int row, int column, @Seed int status) {
        mStatuses[row][column] = status;
        // TODO calculate()
        adjustImages();

        // TODO for debug
//        int width = getWidth();
//        int height = getHeight();
//        int itemHeight = ((LinearLayout) (mLinearRoot.getChildAt(0))).getChildAt(0).getHeight();
//        Timber.d("width: " + width + ", heigh: " + height);
//        int centerX = width / 2; // TODO maybe use one variable, since width and height should be equal already?
//        int centerY = height / 2;
//        int lineHeight = mLine.getHeight();
////        mLine.animate().translationY(centerY - lineHeight / 2).rotation(45).scaleX(1.2f);
//        mLine.animate().translationY(itemHeight / 2);
    }

    public void setLine(@LineStatus int status) {
        switch (status) {
            case LINE_NONE:
                mLine.setVisibility(GONE);
                break;
            case LINE_FIRST_COLUMN:
                mLine.setVisibility(VISIBLE);
                mLine.animate().translationY(30);
                break;
        }
    }

    public TicTacBoardClickListener getBoardClickListener() {
        return mBoardClickListener;
    }

    public void setBoardClickListener(TicTacBoardClickListener boardClickListener) {
        mBoardClickListener = boardClickListener;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({NONE, LINE_FIRST_ROW, LINE_SECOND_ROW, LINE_THIRD_ROW, LINE_FIRST_COLUMN, LINE_SECOND_COLUMN, LINE_THIRD_COLUMN, LINE_FORWARD_SLASH, LINE_BACKWARD_SLASH})
    @interface LineStatus {
    }


    public static interface TicTacBoardClickListener {
        void onBoardItemClick(@IntRange(from = 0, to = 2) int row, @IntRange(from = 0, to = 2) int column, @Seed int currentStatus);
    }
}