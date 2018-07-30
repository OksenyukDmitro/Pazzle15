package com.example.a123.pazzle15;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by 123 on 13.07.2018.
 */

public class SquareButton extends android.support.v7.widget.AppCompatButton {
    public SquareButton(Context context) {
        super(context);
    }

    public SquareButton(Context context, AttributeSet attrs) {
        super(context, attrs);
            }

    // This is used to make square buttons.
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec-13, widthMeasureSpec-13);
    }

}
