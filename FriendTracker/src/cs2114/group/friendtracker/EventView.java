/**
 *
 */
package cs2114.group.friendtracker;

import java.util.Random;

import android.util.Log;

import android.graphics.Color;

import android.util.AttributeSet;

import android.R.color;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

import android.view.View;

/**
 *
 * @author Tianyu
 * @version Apr 22, 2012
 */
public class EventView extends View {
    private Event e;
    private Paint fill;
    private Paint stroke;
    private Paint titleText;
    private Paint timeText;
    private DisplayMetrics dm;
    public static final int HOUR_HEIGHT = 60;
    public static final int LEFT_MARGIN = 40;

    public EventView(Context c, Event e) {
        super(c);
        this.e = e;
        if (e == null) {
            throw new IllegalStateException(
                    "EventView Cannot be initialized with null Event.");
        }
        dm = getResources().getDisplayMetrics();

        initializePaints();
    }

    public int leftMargin() {
        return (int) (LEFT_MARGIN * dm.density);
    }

    private void initializePaints() {

        Random rand = new Random(e.getOwner());

        int h = rand.nextInt(360);
        //Log.d("Float Hue", "ownerId=" + e.getOwner() + " h=" + h);
        fill = new Paint();
        stroke = new Paint();
        titleText = new Paint(Paint.ANTI_ALIAS_FLAG);
        timeText = new Paint(Paint.ANTI_ALIAS_FLAG);

        fill.setColor(Color.HSVToColor(200,new float[] { h, 0.5f, 0.9f }));
        fill.setStyle(Style.FILL);
        int c = Color.HSVToColor(new float[] { h, 0.8f, 0.3f });
        stroke.setColor(c);
        stroke.setStyle(Style.STROKE);
        stroke.setStrokeWidth(3);

        titleText.setTextSize(24);
        timeText.setTextSize(18);
        titleText.setColor(c);
        timeText.setColor(c);
    }

    public void onMeasure(int width, int height) {

        setMeasuredDimension(Math.max(width, (int) (180 * dm.density)),
                eventHeight());

        // Log.d("Tracker-Test", "input width=" + width + " height="
        // + height);
        //Log.d("Tracker-Test", "output width=" + width + " height="
        //        + eventHeight());

    }

    public void onDraw(Canvas canvas) {
        if (e == null) {
            return;
        }

        canvas.drawRect(0, 0, getWidth(), getHeight(), fill);
        canvas.drawRect(0, 0, getWidth(), getHeight(), stroke);

        canvas.drawText(e.getName(), 5 * dm.density, 20 * dm.density,
                titleText);
        canvas.drawText(e.getTimeInterval(),
                5 * dm.density, 40 * dm.density, timeText);
        //Log.d("Tracker-Test", "inside onDraw");

    }

    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_UP) {
         //   Log.d("EventTouch", "Event is touched" + e.toString());
            return false;
        }
        return true;
    }

    public int eventPos() {
        float sMin = Integer.parseInt(e.getStartTime().substring(2));
        float sHour = Integer.parseInt(e.getStartTime().substring(0, 2));
        return Math.round((sHour + sMin / 60) * HOUR_HEIGHT * dm.density);
    }

    public int eventHeight() {
        float sMin = Integer.parseInt(e.getStartTime().substring(2));
        float eMin = Integer.parseInt(e.getEndTime().substring(2));
        float sHour = Integer.parseInt(e.getStartTime().substring(0, 2));
        float eHour = Integer.parseInt(e.getEndTime().substring(0, 2));
        // Log.d("Tracker-Test",
        // "sMin="+sMin+" eMin="+eMin+" sHour"+sHour+" eHour"+eHour);
        float deltaT = eHour - sHour + (eMin - sMin) / 60;
        return Math.round(deltaT * HOUR_HEIGHT * dm.density);
    }
}