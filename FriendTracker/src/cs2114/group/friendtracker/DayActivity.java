package cs2114.group.friendtracker;



import android.view.View;

import android.widget.TextView;

import android.widget.ScrollView;

import android.widget.RelativeLayout;

import android.os.Bundle;

import android.app.Activity;

/**
 * DayActivity to show the day schedule view.
 *
 * @author Tianyu Geng (tony1)
 * @version Apr 23, 2012
 */
public class DayActivity extends Activity {
    private DayModel model;
    private RelativeLayout rl;
    private DayViewBgd dv;
    private ScrollView sv;
    private TextView nameText;
    private TextView dateText;
    /**
     * The height of an hour.
     */
    public static final int HOUR_HEIGHT = 80;
    /**
     * The left margin for the events.
     */
    public static final int EVENT_LEFT_MARGIN = 60;

    /**
     * Called when the activity is created.
     *
     * @param savedInstanceState
     *            state
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//         DatabaseFiller filler = new DatabaseFiller(this);
//         filler.fill();
//         long ownerId = filler.getPersonId(1);

        long ownerId = getIntent().getLongExtra("ownerId", 1);
        model = new DayModel(this, ownerId);

        // initialize the GUI
        this.setContentView(R.layout.dayview);
        sv = (ScrollView) findViewById(R.id.sv);
        sv.setSmoothScrollingEnabled(true);

        // for names and date
        nameText = (TextView) findViewById(R.id.nameText);
        dateText = (TextView) findViewById(R.id.dateText);

        // inside scrollview
        rl = new RelativeLayout(this);
        sv.addView(rl);
        dv = new DayViewBgd(this);
        fillContents();

    }

    /**
     * Fill the content of the RelativeLayout with the dayViewBgd as the
     * background and EventViews.
     */
    private void fillContents() {
        rl.removeAllViews();
        rl.addView(dv);
        nameText.setText(model.getOwnerName());
        dateText.setText(model.getDate());
        if (model.getEvents() != null && !model.getEvents().isEmpty()) {

            final EventView headEv =
                    new EventView(this, model.getEvents().get(0));
            RelativeLayout.LayoutParams lp =
                    new RelativeLayout.LayoutParams(100000, 0);
            lp.setMargins(headEv.leftMargin(), headEv.eventPos(), 0, 0);

            rl.addView(headEv, lp);
            for (int i = 1; i < model.getEvents().size(); i++) {
                // Log.d("DayActivity", "event="+e);

                EventView ev =
                        new EventView(this, model.getEvents().get(i));
                RelativeLayout.LayoutParams lp2 =
                        new RelativeLayout.LayoutParams(100000, 0);
                lp2.setMargins(ev.leftMargin(), ev.eventPos(), 0, 0);
                rl.addView(ev, lp2);
            }
            // Log.d("DayActivity", "Scroll Y=" + headEv.eventPos());
            // Scroll to the first Event.
            sv.post(new Runnable() {
                @Override
                public void run() {
                    sv.smoothScrollTo(0,
                            headEv.eventPos() - headEv.leftMargin());
                }
            });

        }

    }

    /**
     * Standard onResume
     *
     * @param savedBundle
     */
    public void onResume(Bundle savedBundle) {
        fillContents();
    }

    /**
     * The addButton is clicked.
     *
     * @param addButton
     *            the Button
     */
    public void addButtonClicked(View addButton) {
        // do something
    }

    /**
     * The previousDay Button is clicked.
     *
     * @param previousButton
     *            the button
     */
    public void previousButtonClicked(View previousButton) {
        model.previousDay();
        fillContents();
    }

    /**
     * The nextDay Button is clicked.
     *
     * @param nextButton
     *            the button
     */
    public void nextButtonClicked(View nextButton) {
        model.nextDay();
        fillContents();
    }

    /**
     * The today button is clicked.
     *
     * @param todayButton
     *            the button
     */
    public void todayButtonClicked(View todayButton) {
        model.today();
        fillContents();
    }
}