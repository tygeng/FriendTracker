/**
 *
 */
package cs2114.group.friendtracker;

import java.util.List;

/**
 * The DayModel to model a day schedule.
 *
 * @author Tianyu Geng (tony1)
 * @version Apr 20, 2012
 */
public class DayModel {
    private List<Event> events;

    /**
     * The constructor for DayModel.
     *
     * @param events
     *            the list of events on this day
     */
    public DayModel(List<Event> events) {
        assert events != null;
        this.events = events;

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
