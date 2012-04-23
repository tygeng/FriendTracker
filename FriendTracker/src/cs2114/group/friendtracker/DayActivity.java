/**
 *
 */
package cs2114.group.friendtracker;

import android.view.ViewGroup;

import android.util.DisplayMetrics;

import android.widget.CheckBox;

import android.widget.Button;

import android.widget.EditText;

import android.widget.TextView;

import android.widget.ScrollView;

import android.widget.RelativeLayout;

import android.widget.LinearLayout;

import android.util.Log;

import java.util.List;

import android.os.Bundle;

import cs2114.group.friendtracker.testhelper.TH;

import android.app.Activity;

/**
 *
 * @author Tianyu
 * @version Apr 22, 2012
 */
public class DayActivity extends Activity {
    private DayModel model;
    private RelativeLayout rl;
    private DayView dv;
    private ScrollView sv;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new DayModel(TH.getEvents());
        sv = new ScrollView(this);
        dv = new DayView(this);
        rl = new RelativeLayout(this);
        sv.addView(rl);

        this.setContentView(sv);
        fillEvents();


    }

    private void fillEvents() {
        rl.removeAllViews();
        rl.addView(dv);
        for (Event e : model.getEvents()) {
            // Log.d("Tracker-Test", "event="+e);
            EventView ev = new EventView(this, e);
            RelativeLayout.LayoutParams lp =
                    new RelativeLayout.LayoutParams(100000, 0);
            lp.setMargins(ev.leftMargin(), ev.eventPos(), 0, 0);
            rl.addView(ev, lp);
        }
    }
}