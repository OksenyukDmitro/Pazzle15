package com.example.a123.pazzle15;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.Dimension;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by 123 on 13.07.2018.
 */

public class SquareLayout extends LinearLayout {
    public SquareLayout(Context context) {
        super(context);
    }

    public SquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // This is used to make square layouts.


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        if (widthMeasureSpec < heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        } else {
       super.onMeasure(heightMeasureSpec, heightMeasureSpec);
        }


    }
}



