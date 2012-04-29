/**
 *
 */
package cs2114.group.friendtracker.testing.gui;

import cs2114.group.friendtracker.testing.DatabaseFiller;

import cs2114.group.friendtracker.data.DataSource;

import cs2114.group.friendtracker.R;

import android.widget.Button;

import android.widget.EditText;

import cs2114.group.friendtracker.EditFriendActivity;

import student.AndroidTestCase;

/**
 *
 * @author Tianyu Geng (tony1)
 * @version Apr 29, 2012
 */
public class AddFriendActivityTest extends
        AndroidTestCase<EditFriendActivity> {
    private EditText name;
    private EditText phone;
    private Button done;
    private EditFriendActivity activity;
    private DataSource src;

    /**
     * Constructor for AddFriendActivityTest.
     */
    public AddFriendActivityTest() {
        super(EditFriendActivity.class);

    }

    public void setUp() {
        activity = getActivity();
        src = new DataSource(activity);
        name = getView(EditText.class, R.id.editTextName);
        phone = getView(EditText.class, R.id.editTextPhoneNumber);
        done = getView(Button.class, R.id.doneButton);
    }

    /**
     * Bring the database back to the original state.
     */
    public void tearDown() {
        DatabaseFiller.staticFill(src);

    }

    public void testAddFriend() {
        assertTrue(name.getText().toString().isEmpty());
        assertTrue(phone.getText().toString().isEmpty());

        enterText(name, "new person");
        enterText(phone, "540540540");

        click(done);

        src.open();
        assertEquals("new person", src.getPerson(2).getName());
        assertEquals(540540540l, src.getPerson(2).getPhoneNumber());

        src.close();
    }

}
