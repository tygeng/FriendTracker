package cs2114.group.friendtracker;

import android.util.Log;
import android.content.Context;
import cs2114.group.friendtracker.data.DataSource;
import java.util.GregorianCalendar;
import java.util.List;
import static java.util.GregorianCalendar.*;

/**
 * This class serves to model a day schedule.
 *
 * @author  Chris Schweinhart (schwein)
 * @author  Tianyu Geng (tony1)
 * @author  Elena Nadolinski (elena)
 * @version 2012.04.27
 */
public class DayModel
{
    // Instance fields
    private Person owner;
    private GregorianCalendar gc;
    private List<Event> events;
    private DataSource src;
    private static final String[] DAYS = {null,"Sun","Mon","Tue",
            "Wed","Thu","Fri","Sat"};

    // /**
    // * The constructor for DayModel.
    // *
    // * @param events
    // * the list of events on this day
    // */
    // public DayModel(List<Event> events) {
    // assert events != null;
    // this.events = events;
    // gc = new GregorianCalendar();
    // src = null;
    // owner = new Person(1, "TONY", 555555555);
    //
    //
    // }

    /**
     * The constructor for day model. It will create a day model for the current
     * date.
     *
     * @param c
     *            the context where this model the created
     * @param ownerId
     *            the id for the owner of this day model
     */
    public DayModel(Context c, long ownerId) {
        this(c, ownerId, new GregorianCalendar());

    }

    /**
     * The constructor for day model. It will create a day model by the calendar
     * specified.
     *
     * @param c
     *            the context where this model the created
     * @param ownerId
     *            the id for the owner of this day model
     * @param gc
     *            the specified calendar
     */
    public DayModel(Context c, long ownerId, GregorianCalendar gc) {

        this.gc = gc;
        src = new DataSource(c);
        src.open();
        this.owner = src.getPerson(ownerId);
        Log.d("DayModel","Person="+this.owner);
        src.close();
        updateEvents();

    }

    /**
     * Update the event list by the latest date and owner.
     */
    public void updateEvents() {
        if (owner == null) {
            return;
        }
        src.open();
        events =
                src.getEventsForDay(owner.getId(), getDateForQuery(),
                        gc.get(DAY_OF_WEEK));
        Log.d("DayModel","events.size="+events.size());
        src.close();
    }

    /**
     * Get the owner's name.
     *
     * @return the owner's name
     */
    public String getOwnerName() {
        if (owner == null) {
            return null;
        }
        return owner.getName();
    }

    /**
     * Get the date String used to query the database.
     *
     * @return the date String
     */
    private String getDateForQuery() {
        return gc.get(YEAR) + converter(gc.get(MONTH) + 1)
                + converter(gc.get(DAY_OF_MONTH));
    }

    /**
     * Get the human friendly date String.
     *
     * @return the date String
     */
    public String getDate() {
        return (gc.get(MONTH) + 1) + "/" + gc.get(DAY_OF_MONTH) + "/"
                + gc.get(YEAR) + " " + DAYS[gc.get(DAY_OF_WEEK)];
    }

    /**
     * Convert an integer returned by Calendar to a String that can be queried.
     *
     * @param i
     *            the integer
     * @return the month String
     */
    private String converter(int i) {

        if (i < 10) {
            return "0" + i;
        }
        return Integer.toString(i);

    }

    /**
     * Move the calendar to the next day and update the event list.
     */
    public void nextDay() {

        gc.add(DATE, 1);
        updateEvents();
    }

    /**
     * Move the calendar to the previous day and update the event list.
     */
    public void previousDay() {
        gc.add(DATE, -1);
        updateEvents();
    }

    /**
     * Move the calendar to today and update the event list.
     */
    public void today() {
        gc = new GregorianCalendar();
        updateEvents();
    }

    /**
     * Getter for events.
     *
     * @return the events
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Setter for events.
     *
     * @param events
     *            the events
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

}
