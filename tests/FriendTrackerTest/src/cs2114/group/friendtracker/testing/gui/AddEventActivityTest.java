/**
 *
 */
package cs2114.group.friendtracker.testing.gui;

import android.view.View;

import cs2114.group.friendtracker.testing.DatabaseFiller;

import cs2114.group.friendtracker.R;

import android.content.Intent;

import cs2114.group.friendtracker.data.DataSource;

import android.widget.Button;

import android.widget.CheckBox;

import android.widget.EditText;

import student.AndroidTestCase;
import cs2114.group.friendtracker.EditEventActivity;

/**
 *
 * @author Tianyu Geng (tony1)
 * @version Apr 29, 2012
 */
public class AddEventActivityTest extends
        AndroidTestCase<EditEventActivity> {
    private EditText editName;
    private EditText editSTime;
    private EditText editETime;
    private EditText editSDate;
    private EditText editEDate;

    private CheckBox checkBoxM;
    private CheckBox checkBoxT;
    private CheckBox checkBoxW;
    private CheckBox checkBoxTh;
    private CheckBox checkBoxF;
    private CheckBox checkBoxSat;
    private CheckBox checkBoxSun;

    private Button doneButton;

    private EditEventActivity activity;

    private DataSource src;

    public AddEventActivityTest() {
        super(EditEventActivity.class);
    }

    public void setUp() {
        Intent i =
                new Intent(getInstrumentation().getContext(),
                        EditEventActivity.class);
        i.putExtra("personId", 1);
        setActivityIntent(i);
        activity = getActivity();
        src = new DataSource(activity.getApplicationContext());

        editName =
                (EditText) getView(EditText.class, R.id.editTextEventName);
        editSTime =
                (EditText) getView(EditText.class, R.id.editTextStartTime);
        editETime =
                (EditText) getView(EditText.class, R.id.editTextEndTime);
        editSDate =
                (EditText) getView(EditText.class, R.id.editTextStartDate);
        editEDate =
                (EditText) getView(EditText.class, R.id.editTextEndDate);

        checkBoxM = (CheckBox) getView(CheckBox.class, R.id.checkBoxM);
        checkBoxT = (CheckBox) getView(CheckBox.class, R.id.checkBoxT);
        checkBoxW = (CheckBox) getView(CheckBox.class, R.id.checkBoxW);
        checkBoxTh = (CheckBox) getView(CheckBox.class, R.id.checkBoxTh);
        checkBoxF = (CheckBox) getView(CheckBox.class, R.id.checkBoxF);
        checkBoxSat =
                (CheckBox) getView(CheckBox.class, R.id.checkBoxSat);
        checkBoxSun =
                (CheckBox) getView(CheckBox.class, R.id.checkBoxSun);

        doneButton = (Button) getView(Button.class, R.id.eventDoneButton);
    }

    /**
     * Bring the database back to the original state.
     */
    public void tearDown() {
        DatabaseFiller.staticFill(src);

    }

    public void testAddEvent() {
        assertTrue(editName.getText().toString().isEmpty());
        assertTrue(editSTime.getText().toString().isEmpty());
        assertTrue(editETime.getText().toString().isEmpty());
        assertTrue(editSDate.getText().toString().isEmpty());
        assertTrue(editEDate.getText().toString().isEmpty());

        assertFalse(checkBoxM.isChecked());
        assertFalse(checkBoxT.isChecked());
        assertFalse(checkBoxW.isChecked());
        assertFalse(checkBoxTh.isChecked());
        assertFalse(checkBoxF.isChecked());
        assertFalse(checkBoxSat.isChecked());
        assertFalse(checkBoxSun.isChecked());

        enterText(editName, "new event");

        enterText(editSTime, "1500");
        enterText(editETime, "1600");
        enterText(editSDate, "20120101");
        enterText(editEDate, "20121231");

        click(checkBoxM);
        click(checkBoxT);
        click(checkBoxW);
        click(checkBoxTh);
        click(checkBoxF);
        click(checkBoxSat);
        click(checkBoxSun);

        click(doneButton);

        src.open();
        assertEquals("new event", src.getEvent(3).getName());
        assertEquals("1500", src.getEvent(3).getStartTime());
        assertEquals("1600", src.getEvent(3).getEndTime());
        assertEquals("20120101", src.getEvent(3).getStartDate());
        assertEquals("20121231", src.getEvent(3).getEndDate());
        assertEquals("1111111", new String(src.getEvent(3).getDays()));
        src.close();

    }

}
