/**
 *
 */
package cs2114.group.friendtracker.testing.gui;

import cs2114.group.friendtracker.testing.DatabaseFiller;
import cs2114.group.friendtracker.R;
import android.widget.Button;
import android.widget.TextView;
import java.util.GregorianCalendar;
import android.content.Intent;
import cs2114.group.friendtracker.data.DataSource;
import student.AndroidTestCase;
import cs2114.group.friendtracker.DayActivity;
import static android.view.KeyEvent.*;
import static java.util.Calendar.*;

/**
 * Test class for DayActivity.
 *
 * @author Tianyu Geng (tony1)
 * @author Elena Nadolinski (elena)
 * @author Chris Schweinhart (schwein)
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

    /**
     * Constructor for DayActivityTest.
     */
    public DayActivityTest() {
        super(DayActivity.class);
    }

    /**
     * Set up for the test cases.
     */

    public void setUp() {
        gc = new GregorianCalendar();
        Intent i =
                new Intent(getInstrumentation().getContext(),
                        DayActivity.class);
        i.putExtra("id", 1);
        setActivityIntent(i);
        activity = getActivity();
        src = new DataSource(activity.getApplicationContext());

        dateText = getView(TextView.class, R.id.dateText);
        nameText = getView(TextView.class, R.id.nameText);
        preDayBtn = getView(Button.class, R.id.previousButton);
        nxDayBtn = getView(Button.class, R.id.nextButton);
        addBtn = getView(Button.class, R.id.addButton);
        todayBtn = getView(Button.class, R.id.todayButton);

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

    /**
     * Test all the buttons.
     */
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
        click(addBtn);
        sendKeys(KEYCODE_E, KEYCODE_1, KEYCODE_DPAD_DOWN,
                KEYCODE_DPAD_DOWN, KEYCODE_DPAD_DOWN, KEYCODE_DPAD_DOWN,
                KEYCODE_DPAD_DOWN, KEYCODE_DPAD_DOWN, KEYCODE_DPAD_DOWN,
                KEYCODE_DPAD_CENTER);
        src.open();
        assertEquals("e1", src.getEvent(3).getName());
        src.close();

    }



}
