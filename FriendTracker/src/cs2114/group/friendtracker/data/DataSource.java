package cs2114.group.friendtracker.data;

import cs2114.group.friendtracker.Person;

import cs2114.group.friendtracker.Event;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataSource {

    // Database fields
    private SQLiteDatabase database;
    private H dbHelper;
    private String[] allEventColumns = { H.E_ID, H.E_NAME, H.E_STIME,
            H.E_ETIME, H.E_SDATE, H.E_EDATE, H.E_DAYS, H.E_OWNER };
    private String[] allPersonColumns = { H.P_ID, H.P_NAME, H.P_PHONE };

    public DataSource(Context context) {
        dbHelper = new H(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    /**
     * @param name
     * @param phoneNumber
     * @return
     */
    public Person createPerson(String name, int phoneNumber) {
        ContentValues values = new ContentValues();
        values.put(H.P_NAME, name);
        values.put(H.P_PHONE, phoneNumber);
        long personId = database.insert(H.TABLE_PERSONS, null, values);
        return new Person(personId, name, phoneNumber);

    }

    public void deletePerson(Person e) {
        long id = e.getId();
        System.out.println("Person deleted with id: " + id);
        database.delete(H.TABLE_PERSONS, H.P_ID + " = " + id, null);
    }

    public void updatePerson(Person p) {
        long personId = p.getId();

        database.update(H.TABLE_PERSONS, personToValue(p), H.P_ID + " = "
                + personId, null);
    }

    /**
     * @param p
     * @return
     */
    private ContentValues personToValue(Person p) {
        ContentValues values = new ContentValues();
        values.put(H.P_NAME, p.getName());
        values.put(H.P_PHONE, p.getPhoneNumber());
        return values;
    }

    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<Person>();

        Cursor cursor =
                database.query(H.TABLE_PERSONS, allPersonColumns, null,
                        null, null, null, H.P_NAME);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            persons.add(cursorToPerson(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return persons;
    }

    private Person cursorToPerson(Cursor cursor) {
        Person p =
                new Person(cursor.getLong(H.NO_ID),
                        cursor.getString(H.NO_NAME),
                        cursor.getLong(H.NO_OWNER_PHONE));
        return p;
    }

    public Event
            createEvent(String name, Person owner, String startTime,
                    String endTime, String startDate, String endDate,
                    String days) {
        ContentValues values = new ContentValues();
        values.put(H.E_NAME, name);
        values.put(H.E_OWNER, owner.getId());
        values.put(H.E_STIME, startTime);
        values.put(H.E_ETIME, endTime);
        values.put(H.E_SDATE, startDate);
        values.put(H.E_EDATE, endDate);
        values.put(H.E_DAYS, days);
        long eventId = database.insert(H.TABLE_EVENTS, null, values);

        return new Event(eventId, name, owner, startTime, endTime,
                startDate, endDate, days.toCharArray());

    }

    public Event
            createEvent(String name, Person owner, String startTime,
                    String endTime, String startDate, String endDate,
                    char[] days) {

        return createEvent(name, owner, startTime, endTime, startDate,
                endDate, new String(days));
    }

    public void deleteEvent(Event e) {
        long id = e.getId();
        System.out.println("Event deleted with id: " + id);
        database.delete(H.TABLE_EVENTS, H.E_ID + " = " + id, null);
    }

    public void updateEvent(Event e) {
        long eventId = e.getId();

        database.update(H.TABLE_PERSONS, eventToValue(e), H.P_ID + " = "
                + eventId, null);
    }

    private ContentValues eventToValue(Event e) {
        ContentValues values = new ContentValues();
        values.put(H.E_NAME, e.getName());
        values.put(H.E_OWNER, e.getOwner().getId());
        values.put(H.E_STIME, e.getStartTime());
        values.put(H.E_ETIME, e.getEndTime());
        values.put(H.E_SDATE, e.getStartDate());
        values.put(H.E_EDATE, e.getEndDate());
        values.put(H.E_DAYS, new String(e.getDays()));
        return values;
    }

    /**
     * @param day
     *            0: Sunday, 1: Monday ... 6:Saturady.
     * @return
     */
    public List<Event> getEventsByDay(Person p, int day, String date) {
        // database.query(H.TABLE_EVENTS, allEventColumns, H.E_OWNER + " = " +
        // p.getId() , null, null, null/*having*/, H.E_STIME);


        return null;
    }
}