/**
 *
 */
package cs2114.group.friendtracker;

import android.graphics.Color;

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
    private Paint timeText;
    private Paint thickLines;
    private Paint thinLines;

    private static final int HOUR_HEIGHT = 80;

    public DayView(Context c) {
        super(c);
        setWillNotDraw(false);
        context = c;

        dm = getResources().getDisplayMetrics();
        initializePaint();
    }

    public void onMeasure(int width, int height) {

        setMeasuredDimension(width, (int) (24 * HOUR_HEIGHT * dm.density));
    }

    public void initializePaint() {
        timeText = new Paint(Paint.ANTI_ALIAS_FLAG);
        thickLines = new Paint();
        thinLines = new Paint();

        timeText.setColor(Color.WHITE);
        timeText.setTextSize(18);
        thickLines.setColor(Color.WHITE);
        thinLines.setColor(Color.GRAY);
        thickLines.setStrokeWidth(2*(int)dm.density);
        thinLines.setStrokeWidth(0);
    }

    public void onDraw(Canvas canvas) {
        int width = getWidth();
        int textXOffset = Math.round(dm.density*5);
        int textYOffset = Math.round(dm.density*(timeText.getTextSize()));
        for (int i=0; i<24;i++) {
            int y = Math.round(dm.density*i*HOUR_HEIGHT);
            int y2 = Math.round(dm.density*(i+0.5f)*HOUR_HEIGHT);
            canvas.drawLine(0, y, width, y, thickLines);
            canvas.drawText(formatTime(i), textXOffset, y+textYOffset, timeText);
            canvas.drawLine(0, y2, width, y2, thinLines);
        }

    }
    private String formatTime(int hour) {

        String aux = " AM";
        if (hour > 11) {
            aux = " PM";
            if (hour > 12) {
                hour -= 12;
            }
        }
        return hour+":00"+aux;
    }

}
