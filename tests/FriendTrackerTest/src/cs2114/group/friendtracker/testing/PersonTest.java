package cs2114.group.friendtracker.testing;

import android.test.AndroidTestCase;
import cs2114.group.friendtracker.*;

/**
 * Test cases for the person class
 *
 * @author  Chris Schweinhart (schwein)
 * @author  Tianyu Geng (tony1)
 * @author  Elena Nadolinski (elena)
 * @version 2012.04.27
 */
public class PersonTest
    extends AndroidTestCase
{
    // Instance fields
    private Person person;

    /**
     * Constructor for this test case
     */
    public PersonTest()
    {
        // Constructor left blank for testing
    }

    /**
     * Called before each test
     */
    public void setUp()
    {
        person = new Person(1, "Chris", 123456789);
    }

    /**
     * Tests the fields setters and getters
     */
    public void testSettersAndGetters()
    {
        assertEquals(person.getId(), 1);
        assertEquals(person.getName(), "Chris");
        assertEquals(person.getPhoneNumber(), 123456789);
    }

    /**
     * Tests the to string method
     */
    public void testToString()
    {
        assertEquals(person.toString(), "Chris\nphone: 123456789");
    }
}
