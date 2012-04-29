/**
 *
 */
package cs2114.group.friendtracker.testing.gui;

import cs2114.group.friendtracker.testing.DatabaseFiller;

import android.util.Log;

import android.database.sqlite.SQLiteDatabase;

import android.content.Context;

import cs2114.group.friendtracker.R;

import android.widget.Button;

import android.widget.TextView;

import java.util.GregorianCalendar;
import android.content.Intent;
import cs2114.group.friendtracker.Person;
import cs2114.group.friendtracker.data.DataSource;
import student.AndroidTestCase;
import cs2114.group.friendtracker.DayActivity;
import static java.util.Calendar.*;
import static java.util.GregorianCalendar.*;
import android.test.mock.MockContext;

/**
 *
 * @author Tianyu Geng (tony1)
 * @version Apr 28, 2012
 */
public class DayActivityTest extends AndroidTestCase<DayActivity> {
    private DayActivity activity;
    private GregorianCalendar gc;
    private TextView dateText;
    private TextView nameText;
    private Button preDayBtn;
    private Button nxDayBtn;
    private Button addBtn;
    private Button todayBtn;
    private DataSource src;
    private static final String[] DAYS = { null, "Sun", "Mon", "Tue",
            "Wed", "Thu", "Fri", "Sat" };
    private static final String TAG = "DayActivityTest";

    /**
     * Constructor for DayActivityTest.
     */
    public DayActivityTest() {
        super(DayActivity.class);
    }

    public void setUp() {
        gc = new GregorianCalendar();
        Intent i =
                new Intent(getInstrumentation().getContext(),
                        DayActivity.class);
        i.putExtra("id", 1);
        setActivityIntent(i);
        activity = getActivity();
        src = new DataSource(activity.getApplicationContext());

        dateText = (TextView) getView(TextView.class, R.id.dateText);
        nameText = (TextView) getView(TextView.class, R.id.nameText);
        preDayBtn = (Button) getView(Button.class, R.id.previousButton);
        nxDayBtn = (Button) getView(Button.class, R.id.nextButton);
        addBtn = (Button) getView(Button.class, R.id.addButton);
        todayBtn = (Button) getView(Button.class, R.id.todayButton);

    }

    public void tearDown() {

        DatabaseFiller.staticFill(src);
    }

    /**
     * Get the human friendly date String.
     *
     * @return the date String
     */
    private String getDate() {
        return (gc.get(MONTH) + 1) + "/" + gc.get(DAY_OF_MONTH) + "/"
                + gc.get(YEAR) + " " + DAYS[gc.get(DAY_OF_WEEK)];
    }

    public void testButtons() {
        assertEquals("person1", nameText.getText());
        assertEquals(getDate(), dateText.getText());
        click(nxDayBtn);
        gc.add(DATE, 1);
        assertEquals(getDate(), dateText.getText());
        click(todayBtn);
        gc.add(DATE, -1);
        assertEquals(getDate(), dateText.getText());
        click(preDayBtn);
        gc.add(DATE, -1);
        assertEquals(getDate(), dateText.getText());
    }

}
