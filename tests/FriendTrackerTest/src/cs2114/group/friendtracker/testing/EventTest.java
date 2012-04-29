package cs2114.group.friendtracker.testing;

import android.test.AndroidTestCase;
import cs2114.group.friendtracker.*;

/**
 * Test cases for the Event class
 *
 * @author  Chris Schweinhart (schwein)
 * @author  Tianyu Geng (tony1)
 * @author  Elena Nadolinski (elena)
 * @version 2012.04.27
 */
public class EventTest
    extends AndroidTestCase
{
    // Instance fields
    private Event event;
    private char[] days = {'*', 'M', '*', 'W', '*', '*', '*'};

    /**
     * Constructor for the test case
     */
    public EventTest()
    {
        // Constructor left blank for testing
    }

    /**
     * Method called before each test run
     */
    public void setUp()
    {
        event = new Event(1, "CS 2114", 2, "1220", "1310", "20120105",
            "20120509", days);
    }

    /**
     * Tests all the field setters and getters
     */
    public void testSettersAndGetters()
    {
        assertEquals(event.getId(), 1);
        assertEquals(event.getName(), "CS 2114");
        assertEquals(event.getOwner(), 2);
        assertEquals(event.getStartTime(), "1220");
        assertEquals(event.getEndTime(), "1310");
        assertEquals(event.getStartDate(), "20120105");
        assertEquals(event.getEndDate(), "20120509");
        assertEquals(event.getDays(), days);
    }

    /**
     * Tests the get time interval method and its helper
     */
    public void testGetTimeInterval()
    {
        assertEquals(event.getTimeInterval(), "12:20 PM - 1:10 PM");
    }

    /**
     * Tests the to string method
     */
    public void testToString()
    {
        assertEquals(event.toString(), "CS 2114 12:20 PM - 1:10 PM  Mon Wed ");
    }
}
