package cs2114.group.friendtracker;

import android.app.ListActivity;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.widget.ListAdapter;
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

// -------------------------------------------------------------------------
/**
 * This activity is responsible for editing an existing friend (such as
 * modifying events or adding new ones, or changing name/phone number) or adding
 * a new friend
 *
 * @author Elena Nadolinski
 * @version Apr 27, 2012
 */
public class EditFriendActivity extends ListActivity {
    private TextView title;
    private EditText editTextName;
    private EditText editTextPhoneNumber;
    private Person person;
    private DataSource src;
    private ListView listView;
    private ArrayAdapter<Event> adapter;
    private boolean edit;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfriend);

        title = (TextView) findViewById(R.id.title);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPhoneNumber =
                (EditText) findViewById(R.id.editTextPhoneNumber);

        listView = (ListView) findViewById(R.id.eventList);

        src = new DataSource(this);
        src.open();

        adapter = new ArrayAdapter<Event>(this, R.layout.mylist);
        Intent i = getIntent();
        // Receiving the Data
        // edit = false;

        edit = (i.getStringExtra("id") != null);
        if (edit) {
            Integer ownerId = Integer.parseInt(i.getStringExtra("id"));
            person = src.getPerson(ownerId);

            adapter =
                    new ArrayAdapter<Event>(this, R.layout.mylist,
                            src.getEventsForPerson(person.getId()));

            editTextName.setText(person.getName());
            editTextPhoneNumber.setText(person.getPhoneNumber() + "");

            title.setText(person.getName());
            listView.setAdapter(adapter);

        }

        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        src.close();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        menu.add(Menu.NONE, 1, 1, "Delete");
        menu.add(Menu.NONE, 0, 0, "Edit");

    }

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
            viewScreen.putExtra("id", selectedEvent.getId() + "");
            viewScreen.putExtra("personId", person.getId() + "");
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

    // ----------------------------------------------------------
    /**
     * if this activity is modifying a person, the person must be updated in the
     * database. then the activity finishes and the last activity is brought
     * back on the screen
     *
     * @param v
     *            the view
     */
    public void onClickDone(View v) {
        src.open();
        if (edit) {
            person =
                    new Person(person.getId(), editTextName.getText()
                            .toString(),
                            Integer.parseInt(editTextPhoneNumber
                                    .getText().toString()
                                    .replaceAll(" ", "")));
            src.updatePerson(person);
        }
        src.close();
        finish();

    }

    // ----------------------------------------------------------
    /**
     * called if the person isn't created (aka if the user is adding a new
     * person). this creates a new person and puts him in the database
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

    // ----------------------------------------------------------
    /**
     * called when the add Events button is clicked. this adds the previously
     * inputed event into the database by calling createNewEvent() helper
     * method, then clears all the entries for more events to be added
     *
     * @param v
     *            the View
     */
    public void onClickAddMoreEvents(View v) {

        if (person == null) {
            createPerson();
        }

        Intent viewScreen =
                new Intent(getApplicationContext(),
                        EditEventActivity.class);
        viewScreen.putExtra("personId", person.getId() + "");

        startActivity(viewScreen);

    }

    /**
     * for resuming the database
     */
    public void onResume() {
        super.onResume();
        if (person != null) {
            adapter.clear();
            src.open();
            adapter.addAll(src.getEventsForPerson(person.getId()));
            src.close();
            listView.postInvalidate();
        }
    }

    // ----------------------------------------------------------
    /**
     * Since the listview can't expand when items are added to it when embedded
     * in scrollview, this method manually sets the size of the listview so the
     * listview expands when events are added
     *
     * @param listView
     */
    public static void
            setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth =
                MeasureSpec.makeMeasureSpec(listView.getWidth(),
                        MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height =
                totalHeight
                        + (listView.getDividerHeight() * (listAdapter
                                .getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
