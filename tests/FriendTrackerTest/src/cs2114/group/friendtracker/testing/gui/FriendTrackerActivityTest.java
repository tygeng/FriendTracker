/**
 *
 */
package cs2114.group.friendtracker.testing.gui;

import android.widget.EditText;

import android.test.ActivityInstrumentationTestCase2;

import android.test.ActivityInstrumentationTestCase;

import android.content.Intent;

import android.util.Log;

import android.test.TouchUtils;

import android.test.ActivityUnitTestCase;

import cs2114.group.friendtracker.R;

import cs2114.group.friendtracker.data.DataSource;

import android.widget.Button;

import android.widget.ListView;

import cs2114.group.friendtracker.FriendTrackerActivity;

import student.AndroidTestCase;

/**
 *
 * @author Tianyu Geng (tony1)
 * @version Apr 28, 2012
 */
public class FriendTrackerActivityTest extends
        AndroidTestCase<FriendTrackerActivity> {
    private Button addBtn;
    private ListView list;
    private DataSource src;
    private FriendTrackerActivity activity;
    private EditText nameEditText;

    /**
     * Constructor for FriendTrackerActivityTest.
     */
    public FriendTrackerActivityTest() {
        super(FriendTrackerActivity.class);
    }

    public void setUp() {
        activity = getActivity();

        addBtn = (Button) getView(Button.class, R.id.addFriend);
        list = (ListView) getView(ListView.class, android.R.id.list);

        src = new DataSource(activity);
    }

    public void testAddButton() {
        click(addBtn);

    }

}
