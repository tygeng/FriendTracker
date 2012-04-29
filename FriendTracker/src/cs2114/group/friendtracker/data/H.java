package cs2114.group.friendtracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * The helper class to create a SQLite database.
 *
 * @author Tianyu Geng (tony1)
 * @author Chris Schweinhart (schwein)
 * @author Elena Nadolinski
 * @version Apr 16, 2012
 */
public class H extends SQLiteOpenHelper {
    /**
     * Constant name for event table
     */
    public static final String TABLE_EVENTS = "events";

    /**
     * Constant name for id column
     */
    public static final String E_ID = "_id";

    /**
     * Constant name for name column
     */
    public static final String E_NAME = "name";

    /**
     * Constant name for owner column
     */
    public static final String E_OWNER = "ownerID";

    /**
     * Constant name for start time column
     */
    public static final String E_STIME = "starttime";

    /**
     * Constant name for end time column
     */
    public static final String E_ETIME = "endtime";

    /**
     * Constant name for start date column
     */
    public static final String E_SDATE = "startdate";

    /**
     * Constant name for end date column
     */
    public static final String E_EDATE = "enddate";

    /**
     * Constant name for days column
     */
    public static final String[] E_DAYS = { null, "sunday", "monday",
            "tuesday", "wednesday", "thursday", "friday", "saturday" };

    /**
     * The serial number for id column
     */
    public static final int NO_ID = 0;

    /**
     * The serial number for name column
     */
    public static final int NO_NAME = 1;

    /**
     * The serial number for owner column
     */
    public static final int NO_OWNER_PHONE = 2;

    /**
     * The serial number for start time column
     */
    public static final int NO_STIME = 3;

    /**
     * The serial number for end time column
     */
    public static final int NO_ETIME = 4;

    /**
     * The serial number for start date column
     */
    public static final int NO_SDATE = 5;

    /**
     * The serial number for end date column
     */
    public static final int NO_EDATE = 6;

    /**
     * The serial number for days column
     */
    public static final int[] NO_DAYS = { 0, 7, 8, 9, 10, 11, 12, 13 };

    /**
     * Constant name for person table
     */
    public static final String TABLE_PERSONS = "persons";

    /**
     * Constant name for id column
     */
    public static final String P_ID = "_id";

    /**
     * Constant name for name column
     */
    public static final String P_NAME = "name";

    /**
     * Constant name for phone number column
     */
    public static final String P_PHONE = "phonenumber";

    /**
     * Constant name for database
     */
    public static final String DATABASE_NAME = "friendtracker.db";

    /**
     * Constant int for database version
     */
    public static final int DATABASE_VERSION = 4;

    /**
     * Database creation SQLite statement for event table
     */
    public static final String CREATE_EVENT_TABLE = "create table "
            + TABLE_EVENTS + "( " + E_ID
            + " integer primary key autoincrement, " + E_NAME
            + " text not null, " + E_OWNER + " integer not null, "
            + E_STIME + " text not null, " + E_ETIME + " text not null, "
            + E_SDATE + " text not null, " + E_EDATE + " text not null, "
            + E_DAYS[1] + " integer, " + E_DAYS[2] + " integer, "
            + E_DAYS[3] + " integer, " + E_DAYS[4] + " integer, "
            + E_DAYS[5] + " integer, " + E_DAYS[6] + " integer, "
            + E_DAYS[7] + " integer" + ");";

    /**
     * Database creation SQLite statement for person table
     */
    public static final String CREATE_PERSON_TABLE = "create table "
            + TABLE_PERSONS + "( " + P_ID
            + " integer primary key autoincrement, " + P_NAME
            + " text not null, " + P_PHONE + " integer" + ");";

    /**
     * The constructor for this helper.
     *
     * @param context  The context for this helper
     */
    public H(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Standard onCreate method
     *
     * @param database  The database for creation
     */
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_EVENT_TABLE);
        database.execSQL(CREATE_PERSON_TABLE);
    }

    /**
     * Method to handle SQLite version upgrades
     *
     * @param db          The database to be updated
     * @param oldVersion  The old version of the database
     * @param newVersion  The new version of the database
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
            int newVersion) {
        Log.w(H.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONS);
        onCreate(db);
    }

}