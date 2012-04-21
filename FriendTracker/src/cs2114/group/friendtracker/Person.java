package cs2114.group.friendtracker;

import java.io.Serializable;
import java.util.List;

/**
 * This class represents any person in a contact list for the friend tracker app
 * for CS 2114.
 *
 * @author Chris Schweinhart (schwein)
 * @version 2012.04.15
 */
public class Person {
    // Instance fields
    private final long id;
    private String name;
    private long phoneNumber;
    private List<Event> events;

    /**
     * Constructor for the person class
     *
     * @param id
     *            The unique id of this person
     * @param name
     *            The name of this person
     * @param phoneNumber
     *            The phone number of this person
     */
    public Person(long id, String name, long phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Getter for the id field
     *
     * @return id The unique id of this person
     */
    public long getId() {
        return id;
    }

    /**
     * Setter for the name field
     *
     * @param name
     *            The new name for this person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the name field
     *
     * @return name The name of this person
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the phone number field
     *
     * @param phoneNumber
     *            The new phone number for this person
     */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Getter for the phone number field
     *
     * @return phoneNumber The phone number of this person
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Adds a new event for this person
     *
     * @param event
     *            The event to add
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Getter for the events field
     *
     * @return events The list of this person's events
     */
    public List<Event> getEvents() {
        return events;
    }

    public String toString() {
        return "Name: "+name + "\n" + "Phone number: " + phoneNumber;
        //return "id="+id+" name="+ name;
    }
}
