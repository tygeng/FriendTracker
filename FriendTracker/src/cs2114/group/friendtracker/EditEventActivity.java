package cs2114.group.friendtracker;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import cs2114.group.friendtracker.data.DataSource;
import android.os.Bundle;
import android.app.Activity;

// -------------------------------------------------------------------------
/**
 * This activity edits the selected event
 *
 * @author Elena Nadolinski
 * @version Apr 27, 2012
 */
public class EditEventActivity
    extends Activity
{
    private EditText   editTextEventName;
    private EditText   editTextStartTime;
    private EditText   editTextEndTime;
    private EditText   editTextStartDate;
    private EditText   editTextEndDate;

    private DataSource src;

    private CheckBox   checkBoxM;
    private CheckBox   checkBoxT;
    private CheckBox   checkBoxW;
    private CheckBox   checkBoxTh;
    private CheckBox   checkBoxF;
    private CheckBox   checkBoxSat;
    private CheckBox   checkBoxSun;

    private Event      eventToEdit;

    private boolean    edit;

    private Integer    ownerId;


    /**
     * this declares all the needed fields, and gets the id of the event to set
     * the event that needs to be modified
     */
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editevent);

        editTextEventName = (EditText)findViewById(R.id.editTextEventName);
        editTextStartTime = (EditText)findViewById(R.id.editTextStartTime);
        editTextEndTime = (EditText)findViewById(R.id.editTextEndTime);
        editTextStartDate = (EditText)findViewById(R.id.editTextStartDate);
        editTextEndDate = (EditText)findViewById(R.id.editTextEndDate);

        checkBoxM = (CheckBox)findViewById(R.id.checkBoxM);
        checkBoxT = (CheckBox)findViewById(R.id.checkBoxT);
        checkBoxW = (CheckBox)findViewById(R.id.checkBoxW);
        checkBoxTh = (CheckBox)findViewById(R.id.checkBoxTh);
        checkBoxF = (CheckBox)findViewById(R.id.checkBoxF);
        checkBoxSat = (CheckBox)findViewById(R.id.checkBoxSat);
        checkBoxSun = (CheckBox)findViewById(R.id.checkBoxSun);

        src = new DataSource(this);
        src.open();

        Intent i = getIntent();

        ownerId = Integer.parseInt(i.getStringExtra("personId"));

        edit = !(i.getStringExtra("id") == null);

        if (edit)
        {
            Integer eventId = Integer.parseInt(i.getStringExtra("id"));
            eventToEdit = src.getEvent(eventId);

            fillInfo();

        }
        src.close();

    }


    // ----------------------------------------------------------
    /**
     * called with the Done button is pressed. this updates the edited event
     *
     * @param v
     */
    public void doneButton(View v)
    {

        createNewEvent();

        finish();

    }


    // ----------------------------------------------------------
    /**
     * creates a new Event from the user's input in the GUI
     */
    public void createNewEvent()
    {

        char[] days = new char[7];
        for (int i = 0; i < days.length; i++)
        {
            days[i] = '*';
        }
        if (checkBoxSun.isChecked())
        {
            days[0] = '1';
        }
        if (checkBoxM.isChecked())
        {
            days[1] = '1';
        }
        if (checkBoxT.isChecked())
        {
            days[2] = '1';
        }
        if (checkBoxW.isChecked())
        {
            days[3] = '1';
        }
        if (checkBoxTh.isChecked())
        {
            days[4] = '1';
        }
        if (checkBoxF.isChecked())
        {
            days[5] = '1';
        }
        if (checkBoxSat.isChecked())
        {
            days[6] = '1';
        }

        if (!edit)
        {
            src.open();
            src.createEvent(
                editTextEventName.getText().toString(),
                ownerId,
                editTextStartTime.getText().toString(),
                editTextEndTime.getText().toString(),
                editTextStartDate.getText().toString(),
                editTextEndDate.getText().toString(),
                days);
            src.close();
        }
        else
        {
            src.open();
            eventToEdit =
                new Event(
                    eventToEdit.getId(),
                    editTextEventName.getText().toString(),
                    ownerId,
                    editTextStartTime.getText().toString(),
                    editTextEndTime.getText().toString(),
                    editTextStartDate.getText().toString(),
                    editTextEndDate.getText().toString(),
                    days);
            src.updateEvent(eventToEdit);
            src.close();
        }

    }


    // ----------------------------------------------------------
    /**
     * fills the info of the event to be edited
     */
    public void fillInfo()
    {
        editTextEventName.setText(eventToEdit.getName());
        editTextStartTime.setText(eventToEdit.getStartTime());
        editTextEndTime.setText(eventToEdit.getEndTime());
        editTextStartDate.setText(eventToEdit.getStartDate());
        editTextEndDate.setText(eventToEdit.getEndDate());

        char[] days = eventToEdit.getDays();

        if (days[0] != '*')
        {
            checkBoxSun.setChecked(true);
        }
        if (days[1] != '*')
        {
            checkBoxM.setChecked(true);
        }
        if (days[2] != '*')
        {
            checkBoxT.setChecked(true);
        }
        if (days[3] != '*')
        {
            checkBoxW.setChecked(true);
        }
        if (days[4] != '*')
        {
            checkBoxTh.setChecked(true);
        }
        if (days[5] != '*')
        {
            checkBoxF.setChecked(true);
        }
        if (days[6] != '*')
        {
            checkBoxSat.setChecked(true);
        }
    }
}