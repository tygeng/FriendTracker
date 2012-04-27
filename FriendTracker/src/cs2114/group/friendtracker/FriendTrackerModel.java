package cs2114.group.friendtracker;

import java.util.List;

/**
 * The main model for the friend tracker app for CS 2114.
 *
 * @author Chris Schweinhart (schwein)
 * @version 2012.04.15
 */
public class FriendTrackerModel
{
    // Instance fields
    private List<Person> contacts;
    private Person       self;


    /**
     * Constructor for the friend tracker model
     *
     * @param contacts
     *            The set of contacts for the model
     * @param self
     *            The user's information
     */
    public FriendTrackerModel(List<Person> contacts, Person self)
    {
        this.contacts = contacts;
        this.self = self;
    }


    /**
     * Adds a contact to the list
     *
     * @param addition
     *            The person to add to the list of contacts
     */
    public void addContact(Person addition)
    {
        contacts.add(addition);
    }


    /**
     * Getter for the list of contacts
     *
     * @return contacts The list of contacts
     */
    public List<Person> getContacts()
    {
        return contacts;
    }


    /**
     * Setter for the user's information
     *
     * @param self
     *            The new user information
     */
    public void setSelf(Person self)
    {
        this.self = self;
    }


    /**
     * Getter for the user's information
     *
     * @return self The user's information
     */
    public Person getSelf()
    {
        return self;
    }


}
