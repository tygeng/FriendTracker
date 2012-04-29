/**
 *
 */
package cs2114.group.friendtracker.testing.gui;

import static android.view.KeyEvent.*;
import cs2114.group.friendtracker.testing.DatabaseFiller;

import cs2114.group.friendtracker.data.DataSource;

import cs2114.group.friendtracker.R;

import android.widget.Button;

import android.widget.EditText;

import cs2114.group.friendtracker.EditFriendActivity;

import student.AndroidTestCase;

/**
 * Test class for AddFriendActivity.
 *
 * @author Tianyu Geng (tony1)
 * @author Elena Nadolinski (elena)
 * @author Chris Schweinhart (schwein)
 * @version Apr 29, 2012
 */
public class AddFriendActivityTest extends
        AndroidTestCase<EditFriendActivity> {
    private EditText name;
    private EditText phone;
    private Button done;
    private Button addBtn;
    private EditFriendActivity activity;
    private DataSource src;

    /**
     * Constructor for AddFriendActivityTest.
     */
    public AddFriendActivityTest() {
        super(EditFriendActivity.class);

    }

    /**
     *
     */
    public void setUp() {
        activity = getActivity();
        src = new DataSource(activity);
        name = getView(EditText.class, R.id.editTextName);
        phone = getView(EditText.class, R.id.editTextPhoneNumber);
        done = getView(Button.class, R.id.doneButton);
        addBtn = getView(Button.class, R.id.addEventButton);
    }

    /**
     * Bring the database back to the original state.
     */
    public void tearDown() {
        DatabaseFiller.staticFill(src);

    }

    /**
     * Test add a friend.
     */
    public void testAddFriend() {
        assertTrue(name.getText().toString().isEmpty());
        assertTrue(phone.getText().toString().isEmpty());

        enterText(name, "new person");
        enterText(phone, "540540540");

        click(addBtn);
        sendKeys(KEYCODE_E, KEYCODE_1, KEYCODE_DPAD_DOWN,
                KEYCODE_DPAD_DOWN, KEYCODE_DPAD_DOWN, KEYCODE_DPAD_DOWN,
                KEYCODE_DPAD_DOWN, KEYCODE_DPAD_DOWN, KEYCODE_DPAD_DOWN,
                KEYCODE_DPAD_CENTER);
        src.open();
        assertEquals("e1", src.getEvent(3).getName());
        src.close();

        click(done);

        src.open();
        assertEquals("new person", src.getPerson(2).getName());
        assertEquals(540540540l, src.getPerson(2).getPhoneNumber());

        src.close();


    }

}
