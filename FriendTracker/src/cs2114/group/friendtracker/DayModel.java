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
 * @author Tianyu Geng (tony1)
 * @author Chris Schweinhart (schwein)
 * @author Elena Nadolinski (elena)
 * @version 2012.04.29
 */
public class DayModel {
    // Instance fields
    private Person owner;
    private GregorianCalendar gc;
    private List<Event> events;
    private DataSource src;
    private static final String[] DAYS = { null, "Sun", "Mon", "Tue",
            "Wed", "Thu", "Fri", "Sat" };

    /**
     * The constructor for day model. It will create a day model for the current
     * date.
     *
     * @param c        The context where this model was created
     * @param ownerId  The id for the owner of this day model
     */
    public DayModel(Context c, long ownerId) {
        this(c, ownerId, new GregorianCalendar());
    }

    /**
     * The constructor for day model. It will create a day model by the calendar
     * specified.
     *
     * @param c        The context where this model the created
     * @param ownerId  The id for the owner of this day model
     * @param gc       The specified calendar
     */
    public DayModel(Context c, long ownerId, GregorianCalendar gc) {
        this.gc = gc;
        src = new DataSource(c);
        src.open();
        this.owner = src.getPerson(ownerId);
        Log.d("DayModel", "Person=" + this.owner);
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
        Log.d("DayModel", "events.size=" + events.size());
        src.close();
    }

    /**
     * Get the owner's name.
     *
     * @return  The owner's name
     */
    public String getOwnerName() {
        if (owner == null) {
            return null;
        }
        return owner.getName();
    }

    /**
     * Get the owner's Id.
     *
     * @return  The owner's id
     */
    public long getOwnerId() {
        if (owner == null) {
            return 0;
        }
        return owner.getId();
    }

    /**
     * Get the date String used to query the database.
     *
     * @return  The date String for database
     */
    private String getDateForQuery() {
        return gc.get(YEAR) + converter(gc.get(MONTH) + 1)
                + converter(gc.get(DAY_OF_MONTH));
    }

    /**
     * Get the human friendly date String.
     *
     * @return  The human friendly date String
     */
    public String getDate() {
        return (gc.get(MONTH) + 1) + "/" + gc.get(DAY_OF_MONTH) + "/"
                + gc.get(YEAR) + " " + DAYS[gc.get(DAY_OF_WEEK)];
    }

    /**
     * Convert an integer returned by Calendar to a String that can be queried.
     *
     * @param i  The integer for query
     * @return   The month String
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
     * @return The events
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Setter for events.
     *
     * @param events  The events
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

}
