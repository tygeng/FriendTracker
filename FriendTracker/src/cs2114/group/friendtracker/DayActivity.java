package cs2114.group.friendtracker;

import android.widget.ScrollView;

import android.widget.RelativeLayout;

import android.os.Bundle;

import cs2114.group.friendtracker.testhelper.TH;

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

    /**
     * Called when the activity is created.
     *
     * @param savedInstanceState state
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new DayModel(TH.getEvents());

        sv = new ScrollView(this);
        sv.setSmoothScrollingEnabled(true);
        dv = new DayViewBgd(this);
        rl = new RelativeLayout(this);
        sv.addView(rl);
        this.setContentView(sv);

        fillContents();

    }

    /**
     * Fill the content of the RelativeLayout with the dayViewBgd as the
     * background and EventViews.
     */
    private void fillContents() {
        rl.removeAllViews();
        rl.addView(dv);

        if (!model.getEvents().isEmpty()) {

            final EventView headEv =
                    new EventView(this, model.getEvents().get(0));
            RelativeLayout.LayoutParams lp =
                    new RelativeLayout.LayoutParams(100000, 0);
            lp.setMargins(headEv.leftMargin(), headEv.eventPos(), 0, 0);

            rl.addView(headEv, lp);
            for (int i = 1; i < model.getEvents().size(); i++) {
                // Log.d("Tracker-Test", "event="+e);

                EventView ev =
                        new EventView(this, model.getEvents().get(i));
                RelativeLayout.LayoutParams lp2 =
                        new RelativeLayout.LayoutParams(100000, 0);
                lp2.setMargins(ev.leftMargin(), ev.eventPos(), 0, 0);
                rl.addView(ev, lp2);
            }
            // Log.d("Scroll", "Scroll Y=" + headEv.eventPos());
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
}