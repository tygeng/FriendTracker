package cs2114.group.friendtracker;

/**
 * This class represents any single or recurring event for the friend tracker
 * app for CS 2114.
 *
 * @author Chris Schweinhart (schwein)
 * @author Tianyu Geng (tony1)
 * @author Elena Nadolinski (elena)
 * @version 2012.04.27
 */
public class Event {
    // Instance fields
    private final long id;
    private String name;
    private long owner;
    private String startTime;
    private String endTime;
    private String startDate;
    private String endDate;
    /**
     * Days of a week.
     */
    public static final String[] DAYS = { "Sun ", "Mon ", "Tue ", "Wed ",
            "Thu ", "Fri ", "Sat" };

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
     *            The time when this event starts (Format: HHMM)
     * @param endTime
     *            The time when this event ends (Format: HHMM)
     * @param startDate
     *            The first date of this event (Format: YYYYMMDD)
     * @param endDate
     *            The last date of this event (Format: YYYYMMDD)
     * @param days
     *            The days this event happens
     */
    public Event(long id, String name, long owner, String startTime,
            String endTime, String startDate, String endDate, char[] days) {
        this.id = id;
        setName(name);
        setOwner(owner);
        setStartTime(startTime);
        setEndTime(endTime);
        setStartDate(startDate);
        setEndDate(endDate);
        setDays(days);
    }

    /**
     * Getter for field id.
     *
     * @return The id
     */
    public long getId() {
        return id;
    }

    /**
     * Getter for field name.
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the field name.
     *
     * @param name
     *            The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for field owner
     *
     * @return The owner
     */
    public long getOwner() {
        return owner;
    }

    /**
     * Setter for the field owner
     *
     * @param owner
     *            The owner to set
     */
    public void setOwner(long owner) {
        this.owner = owner;
    }

    /**
     * Getter for field startTime
     *
     * @return The startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Setter for the field startTime
     *
     * @param startTime
     *            The startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter for field endTime
     *
     * @return The endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Setter for the field endTime
     *
     * @param endTime
     *            The endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Getter for field startDate
     *
     * @return The startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Setter for the field startDate
     *
     * @param startDate
     *            The startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter for field endDate
     *
     * @return The endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Setter for the field endDate
     *
     * @param endDate
     *            The endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Getter for field days
     *
     * @return The days
     */
    public char[] getDays() {
        return days;
    }

    /**
     * Setter for the field days
     *
     * @param days
     *            The days to set
     */
    public void setDays(char[] days) {
        this.days = days;
    }

    /**
     * Get the well formatted String for the time interval of this event
     *
     * @return The formated time
     */
    public String getTimeInterval() {
        return formatTime(startTime) + " - " + formatTime(endTime);
    }

    /**
     * Helper method for formatting time
     *
     * @param time
     *            The time to be formatted
     * @return The formated time
     */
    private String formatTime(String time) {
        int hour = 0;
        try {
            hour = Integer.parseInt(time.substring(0, 2));
        }
        catch (StringIndexOutOfBoundsException e) {
            return "null";
        }
        catch (NumberFormatException e) {
            return "null";
        }

        String aux = " AM";
        if (hour == 24) {
            hour = 0;
        }
        if (hour > 11) {
            aux = " PM";
            if (hour > 12) {
                hour -= 12;
            }
        }
        return hour + ":" + time.substring(2) + aux;
    }

    /**
     * Produce a human friendly format for day of week.
     *
     * @return the formatted String
     */
    public String formatDays() {
        StringBuilder result = new StringBuilder("  ");
        for (int i = 0; i < 7; i++) {
            if (days[i] != '*') {
                result.append(DAYS[i]);
            }
        }
        return result.toString();
    }

    /**
     * Standard toString()
     *
     * @return String of this event
     */
    public String toString() {
        return name + " " + getTimeInterval() + formatDays();
    }
}