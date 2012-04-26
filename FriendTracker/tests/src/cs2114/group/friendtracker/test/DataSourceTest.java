/**
 *
 */
package cs2114.group.friendtracker.test;

import cs2114.group.friendtracker.data.DataSource;

import android.util.Log;


import cs2114.group.friendtracker.Person;

import android.test.AndroidTestCase;

/**
 *
 * @author Tianyu Geng (tony1)
 * @version Apr 17, 2012
 */
public class DataSourceTest extends AndroidTestCase {
    private DataSource src;
    public final String TAG = "Tracker-Test";

    public void setUp() {
        src = new DataSource(getContext());
    }

    public void testAll() {
        src.open();
        src.clearAll();
        Person p = src.createPerson("PERSON1", 12345);
        Person p2 = src.createPerson("PERSON2", 12345);
        src.createEvent("event1", p.getId(), "1010", "1200", "20120101",
                "20121231", "*M*W*F*");
        src.createEvent("event2", p.getId(), "0610", "1200", "20120101",
                "20121231", "*M*W*F*");
        src.createEvent("event3", p.getId(), "1510", "1200", "20120101",
                "20121231", "*M*W*F*");
        src.createEvent("event4", p.getId(), "0910", "1200", "20120101",
                "20121231", "*M*W*F*");
        src.createEvent("event5", p.getId(), "2310", "1200", "20120501",
                "20121231", "*M*W*F*");
        Log.d(TAG, src.getAllPersons().toString());
        Log.d(TAG,
                "Events for day"
                        + src.getEventsForDay(p.getId(), "20120405", 2)
                                .toString());
        src.deletePerson(p);
        Log.d(TAG,"idToPerson(p2) = "+src.getPerson(p2.getId()));
        Log.d(TAG, src.getAllPersons().toString());
        src.close();

    }
}
