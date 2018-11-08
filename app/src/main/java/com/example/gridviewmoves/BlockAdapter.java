package com.example.gridviewmoves;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class BlockAdapter extends BaseAdapter implements View.OnTouchListener {
    private final int mBlockType;

    private final int[][] mBlockDef = new int[][]
        {
            {Color.BLUE, Color.BLUE,
                Color.BLUE, Color.TRANSPARENT,
                Color.BLUE, Color.TRANSPARENT
            },
            {Color.TRANSPARENT, Color.RED,
                Color.TRANSPARENT, Color.RED,
                Color.RED, Color.RED
            }};

    private final Context mContext;

    public BlockAdapter(Context context, int blockType) {
        mContext = context;
        mBlockType = blockType;
    }

    @Override
    public int getCount() {
        return mBlockDef[mBlockType].length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        int bgColor = mBlockDef[mBlockType][position];

        if (convertView != null) {
            view = convertView;
        } else {
            view = new View(mContext);
            GridView.LayoutParams lp =
                new GridView.LayoutParams(dpToPx(Block.CELL_WIDTH_DP), dpToPx(Block.CELL_HEIGHT_DP));
            view.setLayoutParams(lp);
        }
        if (bgColor == Color.TRANSPARENT) {
            view.setVisibility(View.GONE);
            view.setOnTouchListener(null);
        } else {
            view.setVisibility(View.VISIBLE);
            view.setBackgroundColor(bgColor);
            view.setOnTouchListener(this);
        }
        return view;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                                               mContext.getResources().getDisplayMetrics());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ((Block) v.getParent()).setIsSelecteded(true);
        return false;
    }
}
