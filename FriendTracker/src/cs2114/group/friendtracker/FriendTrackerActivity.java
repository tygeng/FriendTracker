package cs2114.group.friendtracker;

import java.util.ArrayList;
import android.widget.EditText;
import cs2114.group.friendtracker.data.DataSource;
import java.util.List;
import java.util.Random;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class FriendTrackerActivity
    extends ListActivity
{
    private DataSource           datasource;
    private FriendTrackerModel   model;
    private Person               self;

    private EditText             textBox;   // FOR TESTING PURPOSES ONLY (will
// be
// replaced with add friend activity)

    private ArrayAdapter<Person> adapter;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textBox = (EditText)findViewById(R.id.textBox);

        datasource = new DataSource(this);
        datasource.open();

        self = new Person(0, "", 0);

        model = new FriendTrackerModel(datasource.getAllPersons(), self);

        // Use the SimpleCursorAdapter to show the
        // elements in a ListView
        adapter =
            new ArrayAdapter<Person>(
                this,
                android.R.layout.simple_list_item_1,
                model.getContacts());
        setListAdapter(adapter);
    }


    public void onClickAdd(View view)
    {
        @SuppressWarnings("unchecked")
        Person person =
            datasource.createPerson(textBox.getText().toString(), 0);
        adapter.add(person);

        adapter.notifyDataSetChanged();
    }


    public void onClickDelete(View view)
    {
        if (getListAdapter().getCount() > 0)
        {
            // *******************for now only deletes the first person.
            // need to put in toggle switches or something to select a person
            Person person = (Person)getListAdapter().getItem(0);
            datasource.deletePerson(person);
            adapter.remove(person);
        }

    }

}
