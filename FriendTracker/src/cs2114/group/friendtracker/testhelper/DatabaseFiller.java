/**
 *
 */
package cs2114.group.friendtracker.testhelper;

import static java.util.Calendar.*;
import java.util.GregorianCalendar;

import android.content.Context;

import cs2114.group.friendtracker.data.DataSource;

import java.util.Random;

/**
 *
 * @author Tianyu
 * @version Apr 22, 2012
 */
public class DatabaseFiller {
    private DataSource src;
    private GregorianCalendar gc;
    private int numOfDays;
    private Random rand;
    /**
     * Person Strings
     */
    public static final String[] PERSONS = { "Adam", "Brandon", "Claire",
            "Dolton", "Ethan", "Frank", "Grant" };// 8 names
    /**
     * Minutes Strings
     */
    public static final String[] MINUTES = { "00", "15", "30", "45" };
    /**
     * Events Strings
     */
    public static final String[] EVENTS = { "Engl 3764", "CS 2114",
            "CS 2505", "CS 2104", "Math 2224", "Math 3124", "Math 3144",
            "Stat 4714" };// 8 events
    private long[] ids;

    /**
     * The constructor for the filler.
     *
     * @param c
     *            the context (Activity) used to be passed to the DataSource
     *            class
     * @param gc
     *            the date that events will be generated around
     * @param numOfDays
     *            roughly the number of days the events will be around the
     *            specified date.
     */
    public DatabaseFiller(Context c, GregorianCalendar gc, int numOfDays) {
        src = new DataSource(c);
        this.gc = gc;
        this.numOfDays = numOfDays / 2;
        rand = new Random();
    }

    /**
     * The constructor for the filler.
     *
     * @param c
     *            the context (Activity) used to be passed to the DataSource
     *            class
     * @param gc
     *            the date that events will be generated around
     * @param numOfDays
     *            roughly the number of days the events will be around the
     *            specified date.
     * @param seed
     *            the random seed for events generating
     */
    public DatabaseFiller(Context c, GregorianCalendar gc, int numOfDays,
            int seed) {
        this(c, gc, numOfDays);
        rand.setSeed(seed);
    }

    /**
     * The constructor for the filler.
     *
     * @param c
     *            the context (Activity) used to be passed to the DataSource
     *            class
     */
    public DatabaseFiller(Context c) {
        this(c, new GregorianCalendar(), 10);
    }

    /**
     * Fill the database with specified requirements in the constructor.
     */
    public void fill() {
        src.open();
        src.clearAll();
        // fill person
        ids = new long[8];
        for (int i = 0; i < PERSONS.length; i++) {
            ids[i] =
                    src.createPerson(PERSONS[i], (i + 1) * 123456789)
                            .getId();
        }
        // fill events
        gc.add(DATE, numOfDays);
        String eDate = toDateString(numOfDays);
        gc.add(DATE, -2 * numOfDays);
        String sDate = toDateString(-numOfDays);
        String dayPattern[] = { "*!*!*!*", "**!*!**" };
        int dayStart = 8;
        int dayEnd = 18;
        for (int i = 0; i < ids.length; i++) {
            for (int j = 0; j < dayPattern.length; j++) {
                for (int k = dayStart; k < dayEnd; k++) {
                    int name = rand.nextInt(8);
                    k = k + rand.nextInt((dayEnd - k) / 2 + 1) + 1;

                    String sMin = MINUTES[rand.nextInt(4)];
                    String dMin = MINUTES[rand.nextInt(4)];
                    src.createEvent(EVENTS[name], ids[i], converter(k)
                            + sMin, converter(k + 1) + dMin, sDate,
                            eDate, dayPattern[j]);
                }
            }
        }

        src.close();
    }

    /**
     * Get the persons ID.
     *
     * @param i
     *            the person's index
     * @return the person's id
     */
    public long getPersonId(int i) {
        return ids[i];
    }

    /**
     * Convert an int to a String for query.
     *
     * @param i
     *            the int
     * @return the converted String
     */
    private String converter(int i) {

        if (i < 10) {

            return "0" + i;

        }
        return Integer.toString(i);

    }

    /**
     * Create a String for query by an offset of days from the calendar.
     *
     * @param offsetDay
     *            the number of days offset from the date in the calendar
     * @return the date String for query
     */
    private String toDateString(int offsetDay) {
        gc.add(DATE, offsetDay);
        String result =
                gc.get(YEAR) + converter(gc.get(MONTH) + 1)
                        + converter(gc.get(DAY_OF_MONTH));
        gc.add(DATE, -offsetDay);
        return result;
    }
}
