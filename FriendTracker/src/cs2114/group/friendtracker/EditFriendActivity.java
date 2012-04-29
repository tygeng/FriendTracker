package cs2114.group.friendtracker;

import android.app.ListActivity;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ListView;
import android.view.View;
import android.content.Intent;
import cs2114.group.friendtracker.data.DataSource;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;

/**
 * This activity is responsible for editing an existing friend (such as
 * modifying events or adding new ones, or changing name/phone number) or adding
 * a new friend
 *
 * @author Tianyu Geng (tony1)
 * @author Chris Schweinhart (schwein)
 * @author Elena Nadolinski (elena)
 * @version 2012.04.29
 */
public class EditFriendActivity extends ListActivity {
    // Instance fields
    private TextView title;
    private EditText editTextName;
    private EditText editTextPhoneNumber;
    private Person person;
    private DataSource src;
    private ListView listView;
    private ArrayAdapter<Event> adapter;
    private boolean edit;

    /**
     * This declares all the needed fields. If the user clicked edit friend then
     * the id of that friend will be caught from the intent of the previous
     * activity, and will be used to fill in the information on the screen
     * for the user to easily edit that friend
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfriend);

        title = (TextView) findViewById(R.id.title);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPhoneNumber =
                (EditText) findViewById(R.id.editTextPhoneNumber);

        listView = (ListView) findViewById(R.id.eventList);

        src = new DataSource(this);

        adapter = new ArrayAdapter<Event>(this, R.layout.mylist);

        Intent i = getIntent();
        // Receiving the Data
        // edit = false;

        edit = (i.getLongExtra("id",0) != 0);
        if (edit) {

            long ownerId = i.getLongExtra("id",0);

            src.open();
            person = src.getPerson(ownerId);

            src.close();
            editTextName.setText(person.getName());
            editTextPhoneNumber.setText(person.getPhoneNumber() + "");

            title.setText(person.getName());
            listView.setAdapter(adapter);

        }
        registerForContextMenu(listView);
    }

    /**
     * Method to create a context menu for options
     *
     * @param menu      The context menu
     * @param v         The view calling this method
     * @param menuInfo  The info for the menu
     */
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        menu.add(Menu.NONE, 1, 1, "Delete");
        menu.add(Menu.NONE, 0, 0, "Edit");

    }

    /**
     * Determines which context menu choice was pressed and takes action
     *
     * @param item  The menu item chosen
     * @return      Whether or not the event was handled
     */
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Event selectedEvent = adapter.getItem(info.position);
        // to delete
        if (item.getItemId() == 1) {
            adapter.remove(selectedEvent);
            src.open();
            src.deleteEvent(selectedEvent);
            src.close();
            return true;
        }

        // to edit
        if (item.getItemId() == 0) {

            Intent viewScreen =
                    new Intent(getApplicationContext(),
                            EditEventActivity.class);
            viewScreen.putExtra("id", selectedEvent.getId());
            viewScreen.putExtra("personId", person.getId());
            startActivity(viewScreen);
            src.open();
            adapter =
                    new ArrayAdapter<Event>(this, R.layout.mylist,
                            src.getEventsForPerson(person.getId()));
            src.close();
            return true;

        }
        return false;
    }

    /**
     * If this activity is modifying a person, the person must be updated in the
     * database. Then the activity finishes and the last activity is brought
     * back on the screen
     *
     * @param v  The view
     */
    public void onClickDone(View v) {
        src.open();
        if (edit) {
            int phone = 0;
            if (!editTextPhoneNumber.getText().toString().isEmpty()) {
                phone =
                        Integer.parseInt(editTextPhoneNumber.getText()
                                .toString().replaceAll("\\D", ""));
            }
            person =
                    new Person(person.getId(), editTextName.getText()
                            .toString(), phone);
            src.updatePerson(person);
        }
        else {
            createPerson();
        }
        src.close();
        finish();
    }

    /**
     * Called if the person isn't created (if the user is adding a new
     * person). This creates a new person and puts him in the database
     */
    public void createPerson() {
        int phoneNumber = 0;
        try {
            phoneNumber =
                    Integer.parseInt(editTextPhoneNumber.getText()
                            .toString());
        }
        catch (NumberFormatException e) {
            phoneNumber = 0;
        }
        src.open();
        person =
                src.createPerson(editTextName.getText().toString(),
                        phoneNumber);
        src.close();
    }

    /**
     * Called when the add event button is clicked. This adds the previously
     * inputed event into the database by calling createNewEvent() helper
     * method, then clears all the entries for more events to be added
     *
     * @param v  The View
     */
    public void onClickAddMoreEvents(View v) {
        if (person == null) {
            edit = true;
            createPerson();

        }

        Intent viewScreen =
                new Intent(getApplicationContext(),
                        EditEventActivity.class);
        viewScreen.putExtra("personId", person.getId());

        startActivity(viewScreen);
    }

    /**
     * Method for resuming the database
     */
    public void onResume() {
        super.onResume();
        if (edit) {
            src.open();
            adapter =
                    new ArrayAdapter<Event>(this, R.layout.mylist,
                            src.getEventsForPerson(person.getId()));
            src.close();
            listView.setAdapter(adapter);
        }
    }
}
