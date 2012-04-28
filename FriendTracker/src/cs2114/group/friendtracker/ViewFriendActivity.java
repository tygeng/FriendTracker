package cs2114.group.friendtracker;

import cs2114.group.friendtracker.data.DataSource;
import android.widget.ArrayAdapter;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;

// -------------------------------------------------------------------------
/**
 * this is the view friend activity that will display all the user's information
 *
 * @author Elena Nadolinski (elena)
 * @author Chris Schweinhart (schwein)
 * @author Tianyu Geng (tony1)
 * @version Apr 27, 2012
 */
public class ViewFriendActivity
    extends Activity
{
    private DataSource          src;

    private TextView            textViewName;
    private TextView            textViewPhoneNumber;
    private ListView            listViewEvents;
    private ArrayAdapter<Event> adapter;


    /**
     * takes in the ownerId from the previous activity, and based off the id
     * recreates the user's info and displays that on the screen
     */
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewfriend);

        src = new DataSource(this);
        src.open();

        textViewName = (TextView)findViewById(R.id.textViewName);
        textViewPhoneNumber = (TextView)findViewById(R.id.textViewPhoneNumber);
        listViewEvents = (ListView)findViewById(R.id.listViewEvents);

        Intent i = getIntent();
        // Receiving the Data
        Integer ownerId = Integer.parseInt(i.getStringExtra("id"));

        Person selectedPerson = src.getPerson(ownerId);

        adapter =
            new ArrayAdapter<Event>(
                this,
                R.layout.mylist,
                src.getEventsForPerson(selectedPerson.getId()));

        textViewName.setText(selectedPerson.getName());
        textViewPhoneNumber.setText("" + selectedPerson.getPhoneNumber());
        listViewEvents.setAdapter(adapter);
        src.close();

    }


    // ----------------------------------------------------------
    /**
     * when the activity is done, it closes the database, and finishes the
     * activity
     *
     * @param v
     *            the View
     */
    public void onClickDone(View v)
    {

        finish();

    }
}
