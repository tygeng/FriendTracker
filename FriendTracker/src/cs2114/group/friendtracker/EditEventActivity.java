package cs2114.group.friendtracker;

import android.widget.Button;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import cs2114.group.friendtracker.data.DataSource;
import android.os.Bundle;
import android.app.Activity;

/**
 * This activity edits the selected event or creates a new one for the selected
 * person
 *
 * @author Tianyu Geng (tony1)
 * @author Chris Schweinhart (schwein)
 * @author Elena Nadolinski (elena)
 * @version 2012.04.29
 */
public class EditEventActivity extends Activity {
    // Instance fields
    private EditText editTextEventName;
    private EditText editTextStartTime;
    private EditText editTextEndTime;
    private EditText editTextStartDate;
    private EditText editTextEndDate;

    private DataSource src;

    private CheckBox checkBoxM;
    private CheckBox checkBoxT;
    private CheckBox checkBoxW;
    private CheckBox checkBoxTh;
    private CheckBox checkBoxF;
    private CheckBox checkBoxSat;
    private CheckBox checkBoxSun;

    private Event eventToEdit;

    private boolean edit;

    private long ownerId;
    private Button deleteButton;

    /**
     * This declares all the needed fields. If the user clicked edit event then
     * the id of that event will be caught from the intent of the previous
     * activity, and will be used to fill in the information on the screen
     * for the user to easily edit that event
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editevent);

        editTextEventName =
                (EditText) findViewById(R.id.editTextEventName);
        editTextStartTime =
                (EditText) findViewById(R.id.editTextStartTime);
        editTextEndTime = (EditText) findViewById(R.id.editTextEndTime);
        editTextStartDate =
                (EditText) findViewById(R.id.editTextStartDate);
        editTextEndDate = (EditText) findViewById(R.id.editTextEndDate);
        deleteButton = (Button) findViewById(R.id.deleteButton);

        checkBoxM = (CheckBox) findViewById(R.id.checkBoxM);
        checkBoxT = (CheckBox) findViewById(R.id.checkBoxT);
        checkBoxW = (CheckBox) findViewById(R.id.checkBoxW);
        checkBoxTh = (CheckBox) findViewById(R.id.checkBoxTh);
        checkBoxF = (CheckBox) findViewById(R.id.checkBoxF);
        checkBoxSat = (CheckBox) findViewById(R.id.checkBoxSat);
        checkBoxSun = (CheckBox) findViewById(R.id.checkBoxSun);

        src = new DataSource(this);
        src.open();

        Intent i = getIntent();

        ownerId = i.getLongExtra("personId", 0);

        edit = (i.getLongExtra("id", 0) != 0);

        if (edit) {
            long eventId = i.getLongExtra("id", 0);

            eventToEdit = src.getEvent(eventId);

            fillInfo();

        }
        else {
            deleteButton.setVisibility(View.GONE);
        }
        src.close();
    }

    /**
     * Method for when the done button is clicked; this updates the edited event
     *
     * @param v  The button
     */
    public void doneButton(View v) {
        createNewEvent();
        finish();
    }

    /**
     * Method for when the delete button is clicked; this deletes the edited
     * event.
     *
     * @param v  The button
     */
    public void deleteButton(View v) {
        src.open();
        src.deleteEvent(eventToEdit);
        src.close();
        finish();
    }

    /**
     * Creates a new Event from the user's input in the GUI. Either updates
     * the selected event to be edited, or creates a new one in the datasource.
     */
    public void createNewEvent() {
        char[] days = new char[7];
        for (int i = 0; i < days.length; i++) {
            days[i] = '*';
        }
        if (checkBoxSun.isChecked()) {
            days[0] = '1';
        }
        if (checkBoxM.isChecked()) {
            days[1] = '1';
        }
        if (checkBoxT.isChecked()) {
            days[2] = '1';
        }
        if (checkBoxW.isChecked()) {
            days[3] = '1';
        }
        if (checkBoxTh.isChecked()) {
            days[4] = '1';
        }
        if (checkBoxF.isChecked()) {
            days[5] = '1';
        }
        if (checkBoxSat.isChecked()) {
            days[6] = '1';
        }

        if (!edit) {
            src.open();
            src.createEvent(editTextEventName.getText().toString(),
                    ownerId, editTextStartTime.getText().toString(),
                    editTextEndTime.getText().toString(),
                    editTextStartDate.getText().toString(),
                    editTextEndDate.getText().toString(), days);
            src.close();
        }
        else {
            src.open();
            eventToEdit =
                    new Event(eventToEdit.getId(), editTextEventName
                            .getText().toString(), ownerId,
                            editTextStartTime.getText().toString(),
                            editTextEndTime.getText().toString(),
                            editTextStartDate.getText().toString(),
                            editTextEndDate.getText().toString(), days);
            src.updateEvent(eventToEdit);
            src.close();
        }
    }

    /**
     * Fills the info of the event to be edited
     */
    public void fillInfo() {
        editTextEventName.setText(eventToEdit.getName());
        editTextStartTime.setText(eventToEdit.getStartTime());
        editTextEndTime.setText(eventToEdit.getEndTime());
        editTextStartDate.setText(eventToEdit.getStartDate());
        editTextEndDate.setText(eventToEdit.getEndDate());

        char[] days = eventToEdit.getDays();

        if (days[0] != '*') {
            checkBoxSun.setChecked(true);
        }
        if (days[1] != '*') {
            checkBoxM.setChecked(true);
        }
        if (days[2] != '*') {
            checkBoxT.setChecked(true);
        }
        if (days[3] != '*') {
            checkBoxW.setChecked(true);
        }
        if (days[4] != '*') {
            checkBoxTh.setChecked(true);
        }
        if (days[5] != '*') {
            checkBoxF.setChecked(true);
        }
        if (days[6] != '*') {
            checkBoxSat.setChecked(true);
        }
    }
}
