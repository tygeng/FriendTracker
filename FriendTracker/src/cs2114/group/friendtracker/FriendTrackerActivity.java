package cs2114.group.friendtracker;

import android.content.Intent;
import android.widget.Button;
import android.widget.AdapterView;
import android.view.MenuItem;
import android.view.Menu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ContextMenu;
import android.widget.ListView;
import cs2114.group.friendtracker.data.DataSource;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.view.View.OnClickListener;

/**
 * This is the main activity. This displays the list of friends. Long click on a
 * friend gives options of editing, viewing, and deleting a friend. Clicking on
 * the add button allows the user to add a new friend
 *
 * @author Tianyu Geng (tony1)
 * @author Chris Schweinhart (schwein)
 * @author Elena Nadolinski (elena)
 * @version 2012.04.29
 */
public class FriendTrackerActivity extends ListActivity {
    // Instance fields
    private DataSource           src;

    private ArrayAdapter<Person> adapter;
    private ListView             list;
    private Button               addFriend;


    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState  The state for the parent
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        list = (ListView)findViewById(android.R.id.list);

        src = new DataSource(this);

        addFriend = (Button)findViewById(R.id.addFriend);

        registerForContextMenu(list);
        addFriend.setOnClickListener(new OnClickListener() {
            public void onClick(View v)
            {
                Intent viewScreen =
                    new Intent(
                        getApplicationContext(),
                        EditFriendActivity.class);
                startActivity(viewScreen);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(
                AdapterView<?> parent,
                View view,
                int position,
                long id)
            {
                Intent viewDay =
                    new Intent(getApplicationContext(), DayActivity.class);

                viewDay.putExtra(
                    "id",
                    ((Person)(parent.getItemAtPosition(position))).getId());
                startActivity(viewDay);

            }
        });
    }


    /**
     * Resumes the database
     */
    public void onResume()
    {
        super.onResume();
        src.open();

        adapter =
            new ArrayAdapter<Person>(
                this,
                android.R.layout.simple_list_item_1,
                src.getAllPersons());
        src.close();
        list.setAdapter(adapter);
        list.postInvalidate();

    }

    /**
     * Creates the long click menu.
     *
     * @param menu      The context menu
     * @param v         The view calling this method
     * @param menuInfo  The info for the menu
     */
    @Override
    public void onCreateContextMenu(
        ContextMenu menu,
        View v,
        ContextMenuInfo menuInfo)
    {
        menu.add(Menu.NONE, 0, 0, "Edit");
        menu.add(Menu.NONE, 1, 1, "Delete");
        menu.add(Menu.NONE, 2, 2, "View");
    }


    /**
     * Sets actions for when the menu options are pressed. If the menu option 0
     * is pressed, the user pressed "edit" so this will take the user to
     * AddEditFriendActivity. If the menu option 1 was pressed, the user pressed
     * "delete" so that contact will be deleted. If the menu option 2 is pressed
     * the user pressed "view" so this will take the user to ViewFriendActivity
     *
     * @param item  The menu item pressed
     */
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info =
            (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        Person selectedPerson1 = adapter.getItem(info.position);
        // to edit
        if (item.getItemId() == 0)
        {
            Intent viewScreen =
                new Intent(getApplicationContext(), EditFriendActivity.class);

            viewScreen.putExtra("id", selectedPerson1.getId());
            startActivity(viewScreen);
            return true;
        }

        // to delete
        if (item.getItemId() == 1)
        {
            src.open();
            adapter.remove(selectedPerson1);
            src.deletePerson(selectedPerson1);
            src.close();
            return true;
        }

        // to view
        if (item.getItemId() == 2)
        {

            Intent viewScreen =
                    new Intent(getApplicationContext(),
                            ViewFriendActivity.class);
            viewScreen.putExtra("id", selectedPerson1.getId());
            startActivity(viewScreen);

            return true;
        }
        return false;
    }
}
