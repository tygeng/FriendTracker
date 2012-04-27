/**
 *
 */
package cs2114.group.friendtracker;

import android.graphics.Color;

import android.graphics.Canvas;

import android.graphics.Paint;

import android.content.Context;

import android.util.DisplayMetrics;

import android.view.View;

/**
 * The background for day schedule view.
 *
 * @author Tianyu Geng (tony1)
 * @author Chris Schweinhart (schwein)
 * @author Elena Nadolinski (elena)
 * @version Apr 21, 2012
 */
public class DayViewBgd extends View {

    private DisplayMetrics dm;

    private Paint timeText;
    private Paint thickLines;
    private Paint thinLines;



    /**
     * Constructor for DayViewBgd.
     *
     * @param c
     *            the context
     */
    public DayViewBgd(Context c) {
        super(c);
        setWillNotDraw(false);

        dm = getResources().getDisplayMetrics();
        initializePaint();
    }

    /**
     * Standard onMeasure.
     *
     * @param width
     *            width
     * @param height
     *            height
     */
    public void onMeasure(int width, int height) {

        setMeasuredDimension(width, (int) (24 * DayActivity.HOUR_HEIGHT * dm.density));
    }

    /**
     * Initialize all the paint objects.
     */
    public void initializePaint() {
        timeText = new Paint(Paint.ANTI_ALIAS_FLAG);
        thickLines = new Paint();
        thinLines = new Paint();

        timeText.setColor(Color.WHITE);
        timeText.setTextSize(12*dm.density);
        thickLines.setColor(Color.WHITE);
        thinLines.setColor(Color.GRAY);
        thickLines.setStrokeWidth(2*(int)dm.density);
        thinLines.setStrokeWidth(0);
    }

    /**
     * Standard onDraw.
     *
     * @param canvas
     *            the canvas
     */
    public void onDraw(Canvas canvas) {
        int width = getWidth();
        int textXOffset = Math.round(dm.density * 5);
        int textYOffset =
                Math.round(dm.density * (timeText.getTextSize()));
        for (int i = 0; i < 24; i++) {
            int y = Math.round(dm.density * i * DayActivity.HOUR_HEIGHT);
            int y2 = Math.round(dm.density * (i + 0.5f) * DayActivity.HOUR_HEIGHT);
            canvas.drawLine(0, y, width, y, thickLines);
            canvas.drawText(formatTime(i), textXOffset, y + textYOffset,
                    timeText);
            canvas.drawLine(0, y2, width, y2, thinLines);
        }

    }

    /**
     * Format the time needed.
     *
     * @param hour
     * @return
     */
    private String formatTime(int h) {
        int hour = h;
        String aux = " AM";
        if (hour > 11) {
            aux = " PM";
            if (hour > 12) {
                hour -= 12;
            }
        }
        return hour + ":00" + aux;
    }

}
