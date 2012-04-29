package cs2114.group.friendtracker.testing;

import java.util.List;

import cs2114.group.friendtracker.Event;

import cs2114.group.friendtracker.data.DataSource;

import cs2114.group.friendtracker.Person;

import android.test.AndroidTestCase;

/**
 * Test cases for the data source SQLite database.
 *
 * @author Tianyu Geng (tony1)
 * @author Chris Schweinhart (schwein)
 * @author Elena Nadolinski (elena)
 * @version 2012.04.29
 */
public class DataSourceTest extends AndroidTestCase {
    // Instance fields
    private DataSource src;

    /**
     * Standard setUp().
     */
    public void setUp() {
        src = new DataSource(getContext());
        src.open();
        src.clearAll();
    }

    /**
     * Standard tearDown(). (Called after every test case)
     */
    public void tearDown() {
        src.close();
        DatabaseFiller.staticFill(src);
    }

    /**
     * Test basic operations related to event.
     */
    public void testEventRelated() {
        Event e1 =
                src.createEvent("event1", 1, "1000", "1200", "20120101",
                        "20121231", "*!*!*!*");
        assertEquals("event1", e1.getName());
        Event e2 = src.getEventsForDay(1, "20120427", 2).get(0);
        assertEquals("event1", e2.getName());
        Event e3 = src.getEvent(e1.getId());
        assertEquals("event1", e3.getName());
        e1.setName("newEvent1");
        src.updateEvent(e1);
        assertEquals("newEvent1", e1.getName());
        src.deleteEvent(e1);
        assertNull(src.getEvent(e1.getId()));
        assertEquals(0, src.getEventsForDay(1, "20120427", 2).size());

    }

    /**
     * Test the extended operations on events. Especially the orders of the
     * query result by day.
     */
    public void testEventExtended() {

        src.createEvent("event1", 1, "1000", "1200", "20120101",
                "20121231", "*!*!*!*");
        src.createEvent("event2", 1, "2000", "2200", "20120101",
                "20121231", "*!*!*!*");
        src.createEvent("event3", 1, "1200", "1400", "20120101",
                "20121231", "*!*!*!*");
        src.createEvent("event1", 1, "1200", "1400", "20130101",
                "20131231", "*!*!*!*");
        src.createEvent("event1", 1, "1200", "1400", "20120101",
                "20121231", "***!*!*");
        List<Event> es = src.getEventsForDay(1, "20120427", 2);
        assertEquals(3, es.size());
        assertEquals("event1", es.get(0).getName());
        assertEquals("event3", es.get(1).getName());
        assertEquals("event2", es.get(2).getName());
    }

    /**
     * Test basic operations related to persons.
     */
    public void testPersonRelated() {
        Person p1 = src.createPerson("person1", 123456789);
        assertEquals("person1", p1.getName());
        Person p3 = src.getAllPersons().get(0);
        assertEquals("person1", p3.getName());

        Person p2 = src.getPerson(p1.getId());
        assertEquals("person1", p2.getName());
        p1.setName("newPerson1");
        src.updatePerson(p1);
        assertEquals("newPerson1", p1.getName());
        src.deletePerson(p1);
        assertNull(src.getPerson(p1.getId()));
        assertEquals(0, src.getAllPersons().size());
    }
}
