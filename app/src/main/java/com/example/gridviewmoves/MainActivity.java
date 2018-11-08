package com.example.gridviewmoves;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout layout = findViewById(R.id.layout);
        final Block blockUnder = new Block(this, 0);
        layout.addView(blockUnder);
        blockUnder.setAdapter(new BlockAdapter(this, 0));

        final Block blockOver = new Block(this, 1);
        layout.addView(blockOver);
        blockOver.setAdapter(new BlockAdapter(this, 1));

    }

}
