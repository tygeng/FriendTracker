/**
 *
 */
package cs2114.group.friendtracker;

import android.app.Activity;

import android.content.Intent;

import java.util.Random;
import android.graphics.Color;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

/**
 *
 * @author Tianyu Geng (tony1)
 * @author Chris Schweinhart (schwein)
 * @author Elena Nadolinski (elena)
 * @version Apr 22, 2012
 */
public class EventView extends View {
    private Event e;
    private Paint fill;
    private Paint stroke;
    private Paint titleText;
    private Paint timeText;
    private DisplayMetrics dm;
    private Activity context;

    /**
     * Constructor for EventView
     *
     * @param c
     *            the context
     * @param e
     *            the event this EventView is associated with
     */
    public EventView(Activity c, Event e) {
        super(c);
        context = c;
        this.e = e;
        if (e == null) {
            throw new IllegalStateException(
                    "EventView Cannot be initialized with null Event.");
        }
        dm = getResources().getDisplayMetrics();

        initializePaints();
    }

    /**
     * The left margin desired for this EventView
     *
     * @return the left margin
     */
    public int leftMargin() {
        return (int) (DayActivity.EVENT_LEFT_MARGIN * dm.density);
    }

    /**
     * Initialize the Paint objects.
     */
    private void initializePaints() {

        Random rand = new Random(e.getOwner());

        int h = rand.nextInt(360);
        // Log.d("Float Hue", "ownerId=" + e.getOwner() + " h=" + h);
        fill = new Paint();
        stroke = new Paint();
        titleText = new Paint(Paint.ANTI_ALIAS_FLAG);
        timeText = new Paint(Paint.ANTI_ALIAS_FLAG);

        fill.setColor(Color
                .HSVToColor(200, new float[] { h, 0.5f, 0.9f }));
        fill.setStyle(Style.FILL);
        int c = Color.HSVToColor(new float[] { h, 0.8f, 0.2f });
        stroke.setColor(c);
        stroke.setStyle(Style.STROKE);
        stroke.setStrokeWidth(3 * dm.density);

        titleText.setTextSize(16 * dm.density);
        timeText.setTextSize(12 * dm.density);
        titleText.setColor(c);
        timeText.setColor(c);
    }

    /**
     * Standard onMeasure
     *
     * @param width
     *            width
     * @param height
     *            height
     */
    public void onMeasure(int width, int height) {

        setMeasuredDimension(Math.max(width, (int) (180 * dm.density)),
                eventHeight());

        // Log.d("Tracker-Test", "input width=" + width + " height="
        // + height);
        // Log.d("Tracker-Test", "output width=" + width + " height="
        // + eventHeight());

    }

    /**
     * Standard onDraw.
     *
     * @param canvas
     *            the canvas
     */
    public void onDraw(Canvas canvas) {
        if (e == null) {
            return;
        }

        canvas.drawRect(0, 0, getWidth(), getHeight(), fill);
        canvas.drawRect(0, 0, getWidth(), getHeight(), stroke);

        canvas.drawText(e.getName(), 5 * dm.density, 20 * dm.density,
                titleText);
        canvas.drawText(e.getTimeInterval(), 5 * dm.density,
                40 * dm.density, timeText);
        // Log.d("Tracker-Test", "inside onDraw");

    }

    /**
     * Standard onTouchEvent
     *
     * @param motionEvent
     *            the MotionEvent
     * @return whether the event is handled or not
     */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            Intent editEvent =
                    new Intent(context.getApplicationContext(),
                            EditEventActivity.class);
            editEvent.putExtra("id", e.getId() );
            editEvent.putExtra("personId", e.getOwner());
            context.startActivity(editEvent);

            return false;
        }
        return true;
    }

    /**
     * The position of this event
     *
     * @return the distance in pixels between the top edge of the screen and the
     *         top edge of this EventView
     */
    public int eventPos() {
        float sMin = Integer.parseInt(e.getStartTime().substring(2));
        float sHour = Integer.parseInt(e.getStartTime().substring(0, 2));
        return Math.round((sHour + sMin / 60) * DayActivity.HOUR_HEIGHT
                * dm.density);
    }

    /**
     * The height of this EventView.
     *
     * @return the height in pixels of this EventView
     */
    public int eventHeight() {
        float sMin = Integer.parseInt(e.getStartTime().substring(2));
        float eMin = Integer.parseInt(e.getEndTime().substring(2));
        float sHour = Integer.parseInt(e.getStartTime().substring(0, 2));
        float eHour = Integer.parseInt(e.getEndTime().substring(0, 2));
        // Log.d("Tracker-Test",
        // "sMin="+sMin+" eMin="+eMin+" sHour"+sHour+" eHour"+eHour);
        float deltaT = eHour - sHour + (eMin - sMin) / 60;
        return Math.round(deltaT * DayActivity.HOUR_HEIGHT * dm.density);
    }
}