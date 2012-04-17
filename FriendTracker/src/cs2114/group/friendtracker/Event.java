package cs2114.group.friendtracker;

/**
 * This class represents any single or recurring event for the friend tracker
 * app for CS 2114.
 *
 * @author Chris Schweinhart (schwein)
 * @version 2012.04.15
 */
public class Event {
    // Instance fields
    private final long id;
    private String name;

    private Person owner;

    private String startTime;

    private String endTime;

    private String startDate;

    private String endDate;

    /**
     * "*" represents false, and any other chars represent true. For example,
     * {*,M,*,W,*,F,*,*} means the event should be turned on on Monday
     * Wednesday, and Friday.
     */
    private char[] days;

    /**
     * Constructor for the event class
     *
     * @param id
     *            The unique id of this event
     * @param name
     *            The name of this event
     * @param owner
     *            The person who owns this event
     * @param startTime
     *            The time when this event starts
     * @param endTime
     *            The time when this event ends
     * @param startDate
     *            The first date of this event
     * @param endDate
     *            The last date of this event
     * @param days
     *            The days of the week this event runs
     */

    public Event(long id, String name, Person owner, String startTime,
            String endTime, String startDate, String endDate, char[] days) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
    }

    /**
     * Getter for field id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Getter for field name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the field name.
     *
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for field owner.
     *
     * @return the owner
     */
    public Person getOwner() {
        return owner;
    }

    /**
     * Setter for the field owner.
     *
     * @param owner
     *            the owner to set
     */
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    /**
     * Getter for field startTime.
     *
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Setter for the field startTime.
     *
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter for field endTime.
     *
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Setter for the field endTime.
     *
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Getter for field startDate.
     *
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Setter for the field startDate.
     *
     * @param startDate
     *            the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter for field endDate.
     *
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Setter for the field endDate.
     *
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Getter for field days.
     *
     * @return the days
     */
    public char[] getDays() {
        return days;
    }

    /**
     * Setter for the field days.
     *
     * @param days
     *            the days to set
     */
    public void setDays(char[] days) {
        this.days = days;
    }

}
