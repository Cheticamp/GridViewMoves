package com.example.gridviewmoves;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.GridView;

public class Block extends GridView {

    public Block(Context context) {
        super(context);
        init(0);
    }

    public Block(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(0);
    }

    public Block(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Block(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(0);
    }

    public Block(Context context, int blockType) {
        super(context);
        init(blockType);
    }

    private void init(int blockType) {
        setNumColumns(2);
        setColumnWidth(dpToPx(CELL_WIDTH_DP));
        MarginLayoutParams lp =
            new GridView.MarginLayoutParams(dpToPx(BLOCK_WIDTH_DP), dpToPx(BLOCK_HEIGHT_DP));
        if (blockType == 1) {
            lp.leftMargin = dpToPx(CELL_WIDTH_DP);
        }
        setLayoutParams(lp);
    }

    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                                               getResources().getDisplayMetrics());
    }

    private float mLastTouchX;
    private float mLastTouchY;
    private boolean mIsSelected;

    public void setIsSelecteded(boolean isSelecteded) {
        mIsSelected = isSelecteded;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!mIsSelected) {
            return false;
        }

        final int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                mLastTouchX = ev.getRawX();
                mLastTouchY = ev.getRawY();
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                final float x = ev.getRawX();
                final float y = ev.getRawY();

                final float dx = x - mLastTouchX;
                final float dy = y - mLastTouchY;

                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) getLayoutParams();
                lp.leftMargin += dx;
                lp.topMargin += dy;
                setLayoutParams(lp);
                mLastTouchX = x;
                mLastTouchY = y;

                break;
            }

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                mIsSelected = false;
                break;
            }
        }
        return super.onTouchEvent(ev);
    }

    public final static int CELL_WIDTH_DP = 100;
    public final static int CELL_HEIGHT_DP = 100;
    public final static int BLOCK_WIDTH_DP = 2 * CELL_WIDTH_DP;
    public final static int BLOCK_HEIGHT_DP = 3 * CELL_WIDTH_DP;
}
