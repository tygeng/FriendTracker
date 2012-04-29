package cs2114.group.friendtracker.testing;

import java.util.GregorianCalendar;
import static java.util.GregorianCalendar.*;
import cs2114.group.friendtracker.data.DataSource;
import android.test.AndroidTestCase;
import cs2114.group.friendtracker.*;

/**
 * Test cases for the day model class
 *
 * @author Chris Schweinhart (schwein)
 * @author Tianyu Geng (tony1)
 * @author Elena Nadolinski (elena)
 * @version 2012.04.27
 */
public class DayModelTest extends AndroidTestCase {
    // Instance fields
    private DataSource src;
    private DayModel model;
    private Person person;
    private Event event;
    private static final String[] DAYS = { null, "Sun", "Mon", "Tue",
            "Wed", "Thu", "Fri", "Sat" };

    /**
     * Constructor for the test case
     */
    public DayModelTest() {
        // Constructor left blank for testing
    }

    /**
     * Called before each test method
     */
    public void setUp() {
        src = new DataSource(getContext());
        src.open();
        src.clearAll();
        person = src.createPerson("PERSON1", 12345);
        event =
                src.createEvent("event1", person.getId(), "1010", "1200",
                        "20120101", "20121231", "*M*W*FS");
        src.close();
        model =
                new DayModel(getContext(), person.getId(),
                        new GregorianCalendar(2012, 3, 28));
    }

    /**
     * Tests the setters and getters of various fields
     */
    public void testSettersAndGetters() {
        assertEquals(model.getOwnerName(), person.getName());
        assertEquals(model.getOwnerId(), person.getId());
        assertEquals(model.getDate(), "4/28/2012 Sat");
        assertEquals(model.getEvents().get(0).toString(),
                event.toString());
    }

    /**
     * Tests various day movement capabilities
     */
    public void testMovement() {
        model.nextDay();
        assertEquals(model.getDate(), "4/29/2012 Sun");

        model.previousDay();
        model.previousDay();
        assertEquals(model.getDate(), "4/27/2012 Fri");

        model.today();
        GregorianCalendar gc = new GregorianCalendar();
        assertEquals(model.getDate(), (gc.get(MONTH)+1) + "/" + gc.get(DATE)
                + "/" + "2012 " + DAYS[gc.get(DAY_OF_WEEK)]);
    }
}
