package cs2114.group.friendtracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * The helper class to create a SQLite database.
 *
 * @author Tianyu Geng (tony1)
 * @version Apr 16, 2012
 */
public class H extends SQLiteOpenHelper {

    public static final String TABLE_EVENTS = "events";
    public static final String E_ID = "_id";
    public static final String E_NAME = "name";
    public static final String E_OWNER = "ownerID";
    public static final String E_STIME = "starttime";
    public static final String E_ETIME = "endtime";
    public static final String E_SDATE = "startdate";
    public static final String E_EDATE = "enddate";
    public static final String[] E_DAYS = { null,"sunday", "monday",
            "tuesday", "wednesday", "thursday", "friday", "saturday" };

    public static final int NO_ID = 0;
    public static final int NO_NAME = 1;
    public static final int NO_OWNER_PHONE = 2;
    public static final int NO_STIME = 3;
    public static final int NO_ETIME = 4;
    public static final int NO_SDATE = 5;
    public static final int NO_EDATE = 6;
    public static final int[] NO_DAYS = {0,7,8,9,10,11,12,13};

    public static final String TABLE_PERSONS = "persons";
    public static final String P_ID = "_id";
    public static final String P_NAME = "name";
    public static final String P_PHONE = "phonenumber";

    // public static final String

    private static final String DATABASE_NAME = "friendtracker.db";
    private static final int DATABASE_VERSION = 3;

    // Database creation sql statement
    private static final String CREATE_EVENT_TABLE = "create table "
            + TABLE_EVENTS + "( " + E_ID
            + " integer primary key autoincrement, " + E_NAME
            + " text not null, " + E_OWNER + " integer not null, "
            + E_STIME + " text not null, " + E_ETIME + " text not null, "
            + E_SDATE + " text not null, " + E_EDATE + " text not null, "
            + E_DAYS[1] + " integer, "
            + E_DAYS[2] + " integer, "
            + E_DAYS[3] + " integer, "
            + E_DAYS[4] + " integer, "
            + E_DAYS[5] + " integer, "
            + E_DAYS[6] + " integer, "
            + E_DAYS[7] + " integer"
            + ");";

    private static final String CREATE_PERSON_TABLE = "create table "
            + TABLE_PERSONS + "( " + P_ID + " integer primary key autoincrement, "
            + P_NAME + " text not null, " + P_PHONE + " integer" + ");";

    /**
     * The constructor for this helper.
     *
     * @param context
     */
    public H(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_EVENT_TABLE);
        database.execSQL(CREATE_PERSON_TABLE);
    }

    @Override
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