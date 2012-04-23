/**
 *
 */
package cs2114.group.friendtracker;

import android.util.Log;

import android.app.Activity;

import android.view.MotionEvent;

import android.graphics.Rect;

import android.graphics.Canvas;

import android.graphics.Paint.Style;

import android.R.color;

import android.graphics.Paint;

import android.util.AttributeSet;

import android.content.Context;

import android.widget.RelativeLayout;

import android.util.DisplayMetrics;

import android.view.View;

/**
 *
 * @author Tianyu Geng (tony1)
 * @version Apr 21, 2012
 */
public class DayView extends View {

    private DisplayMetrics dm;
    private Context context;

    private static final int HOUR_HEIGHT = 60;

    public DayView(Context c) {
        super(c);
        setWillNotDraw(false);
        context = c;

        dm = getResources().getDisplayMetrics();

    }

    public void onMeasure(int width, int height) {

        setMeasuredDimension(width, (int) (24 * HOUR_HEIGHT * dm.density));

    }


    public void onDraw(Canvas canvas) {

    }

}
