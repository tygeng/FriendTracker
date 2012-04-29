/**
 *
 */
package cs2114.group.friendtracker.testing.gui;

import cs2114.group.friendtracker.Person;

import android.view.KeyEvent;

import cs2114.group.friendtracker.testing.DatabaseFiller;

import cs2114.group.friendtracker.data.DataSource;

import static android.view.KeyEvent.*;

import cs2114.group.friendtracker.R;

import android.widget.Button;

import cs2114.group.friendtracker.FriendTrackerActivity;

import student.AndroidTestCase;

/**
 *
 * @author Tianyu Geng (tony1)
 * @author Elena Nadolinski (elena)
 * @author Chris Schweinhart (schwein)
 * @version Apr 28, 2012
 */
public class FriendTrackerActivityTest extends
        AndroidTestCase<FriendTrackerActivity> {
    private Button addBtn;

    private DataSource src;
    private FriendTrackerActivity activity;

    /**
     * Constructor for FriendTrackerActivityTest.
     */
    public FriendTrackerActivityTest() {
        super(FriendTrackerActivity.class);
    }

    /**
     * Set up for the test cases.
     */
    public void setUp() {
        activity = getActivity();
        src = new DataSource(activity);
        addBtn = getView(Button.class, R.id.addFriend);

    }

    /**
     * Bring the database back to the original state.
     */
    public void tearDown() {
        DatabaseFiller.staticFill(src);

    }

    /**
     * Test buttons.
     *
     * @throws InterruptedException
     *             the exception throw from Thread.sleep()
     *
     *
     */
    public void testAll() throws InterruptedException {

        sendKeys(KEYCODE_DPAD_DOWN);
        longClickDpadCenter();
        Thread.sleep(500);
        sendKeys(KEYCODE_DPAD_CENTER, KEYCODE_N, KEYCODE_E, KEYCODE_W,
                KEYCODE_SPACE, KEYCODE_DPAD_DOWN, KEYCODE_DPAD_DOWN,
                KEYCODE_DPAD_DOWN, KEYCODE_DPAD_DOWN, KEYCODE_DPAD_CENTER);

        src.open();
        assertEquals("new person1", src.getPerson(1).getName());
        src.close();

        // ----test add button;
        click(addBtn);

        sendKeys(KEYCODE_P, KEYCODE_2, KEYCODE_DPAD_DOWN,
                KEYCODE_DPAD_DOWN, KEYCODE_DPAD_RIGHT,
                KEYCODE_DPAD_CENTER);

        src.open();
        Person p2 = src.getPerson(2);
        assertEquals("p2", p2.getName());
        src.close();

        sendKeys(KEYCODE_DPAD_DOWN);
        longClickDpadCenter();
        Thread.sleep(500);
        sendKeys(KEYCODE_DPAD_DOWN, KEYCODE_DPAD_CENTER);
        src.open();
        assertEquals(1, src.getAllPersons().size());
        src.close();

        longClickDpadCenter();
        Thread.sleep(500);
        sendKeys(KEYCODE_DPAD_DOWN, KEYCODE_DPAD_DOWN,
                KEYCODE_DPAD_CENTER);
    }

    /**
     * Helper class to perform a long click.
     *
     * @throws InterruptedException
     *             the exception throw from Thread.sleep()
     *
     */
    public void longClickDpadCenter() throws InterruptedException {
        getInstrumentation().sendKeySync(
                new KeyEvent(KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DPAD_CENTER));
        Thread.sleep(500);
        getInstrumentation().sendKeySync(
                new KeyEvent(KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DPAD_CENTER));
    }

}
