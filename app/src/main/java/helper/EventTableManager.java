package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import set.EventSet;

/**
 * Created by harsu on 6/24/2015.
 */
public class EventTableManager {

    public static final String KEY_ID = "_id";
    public static final String KEY_EVENT_ID = "EventID";
    //public static final String KEY_TAG = "EventTag";
    public static final String KEY_TAB = "EventTab";
    public static final String KEY_NAME = "Name";
    //public static final String KEY_START_TIME = "StartTime";
    //public static final String KEY_END_TIME = "EndTime";
    //public static final String KEY_VENUE = "Venue";
    public static final String KEY_DETAILS = "Details";
    public static final String KEY_CONTACTS = "Contacts";
    public static final String KEY_IMAGE_LINK = "ImageLink";
    public static final String KEY_IMAGE_DOWNLOAD = "ImageDownloaded";
    //public static final String KEY_COST = "Cost";
    public static final String KEY_FAVOURITE = "Favourite";


    public static final String TAG = "TransactionTable";

    private static final String DATABASE_TABLE = "EVENTLIST";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "EVENTDatabase";
    private Context context;
    private DBHelper ourHelper;
    private SQLiteDatabase ourDatabase;

    public EventTableManager(Context c) {
        context = c;
    }

    public EventTableManager open() {
        ourHelper = new DBHelper(context);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        ourHelper.close();
        ourDatabase.close();
    }

    public ArrayList<String> getDistinctTabs() {
        ArrayList<String> temp=new ArrayList<>();
        open();
        Cursor cursor = ourDatabase.rawQuery("SELECT DISTINCT " + KEY_TAB + " FROM " + DATABASE_TABLE ,
                null);
        if (cursor.moveToFirst())
            do{
                temp.add(cursor.getString(0));
            }while (cursor.moveToNext());
        cursor.close();
//        close();
        return  temp;
    }

    public Cursor getEvents(String tab) {
        ArrayList<EventSet> events=new ArrayList<>();
        open();
        Cursor cursor = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE +
                        " WHERE "+KEY_TAB+" = '"+tab+"' " ,
                null);
//        close();
        return cursor;
    }

    public Cursor getFavourites() {
        ArrayList<EventSet> events=new ArrayList<>();
        open();
        Cursor cursor = ourDatabase.rawQuery("SELECT "+KEY_ID+", " +KEY_EVENT_ID+", "+ KEY_NAME +", " + KEY_IMAGE_LINK +", "+KEY_IMAGE_DOWNLOAD+", "+KEY_FAVOURITE+" FROM " + DATABASE_TABLE +
                " WHERE "+KEY_FAVOURITE+" = 1", null);

       /* if (cursor.moveToFirst())
            do{

                EventSet eventSet=new EventSet(
                        cursor.getInt(0),
                        cursor.getLong(2),
                        cursor.getString(1),
                        cursor.getString(4),
                        cursor.getString(3),
                        cursor.getInt(5),
                        cursor.getInt(6)==1

                );
                events.add(eventSet);
            }while (cursor.moveToNext());
        cursor.close();
        close();*/
        return cursor;
    }

    public void imageDownloaded(int id, String path) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_IMAGE_LINK, path);
        contentValues.put(KEY_IMAGE_DOWNLOAD,1);
        open();
        ourDatabase.update(DATABASE_TABLE, contentValues, KEY_EVENT_ID + " = " + id, null);
//        close();
    }

    public Boolean checkFavourite(int event_id) {
        Boolean result=false;
        open();
        Cursor cursor = ourDatabase.rawQuery("SELECT " +KEY_FAVOURITE+" FROM " + DATABASE_TABLE +" WHERE "+KEY_EVENT_ID+" = "+event_id+" " ,
                null);
        if(cursor.moveToFirst()){
            if(cursor.getInt(0)==1)
                result=true;
        }
//        close();
        return result;
    }

    public boolean toggleFavourite(int event_id) {
        ContentValues cv=new ContentValues();
        boolean result;
        if(checkFavourite(event_id)){
            cv.put(KEY_FAVOURITE,0);
            result=false;
        }
        else {
            cv.put(KEY_FAVOURITE,1);
            result=true;
        }
        open();
        ourDatabase.update(DATABASE_TABLE, cv, KEY_EVENT_ID + " = " + event_id, null);
//        close();
        return result;
    }


    public String getEventName(int event_id) {
        String name="";
        open();
        Cursor cursor = ourDatabase.rawQuery("SELECT " + KEY_NAME + " FROM " + DATABASE_TABLE +
                        " WHERE "+KEY_EVENT_ID+" = "+event_id+" " ,
                null);
        if (cursor.moveToFirst())
            name=cursor.getString(0);
        cursor.close();
//        close();
        return name;
    }

    public Cursor getEventData(int event_id) {
        open();
        Cursor cursor = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE +
                        " WHERE "+KEY_EVENT_ID+" = "+event_id+" " ,
                null);
        return cursor;

    }

    public boolean dataPresent() {
        boolean result=false;
        open();
        Cursor cursor=ourDatabase.rawQuery("SELECT * FROM "+DATABASE_TABLE,null);
        if(cursor.moveToFirst()){
            result=true;
        }
//        close();
        return result;
    }


    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String query = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + " (" +
                    KEY_ID              + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_EVENT_ID        + " INTEGER UNIQUE NOT NULL, " +
                    KEY_TAB             + " TEXT NOT NULL, " +
                    KEY_NAME            + " TEXT NOT NULL, " +
                    KEY_DETAILS         + " TEXT NOT NULL,  " +
                    KEY_CONTACTS        + " TEXT NOT NULL,  " +
                    KEY_IMAGE_LINK      + " TEXT,  " +
                    KEY_IMAGE_DOWNLOAD  + " TEXT,  " +
                    KEY_FAVOURITE       + " INTEGER NOT NULL); ";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public long addEntry( int       id,
                          String    tab,
                          String    name,
                          String    details,
                          String    contacts,
                          String    image_link) {
        long success=-1;

        ContentValues cv = new ContentValues();

        cv.put(KEY_EVENT_ID        ,id);
        cv.put(KEY_TAB             ,tab);
        cv.put(KEY_NAME            ,name);
        cv.put(KEY_DETAILS         ,details);
        cv.put(KEY_CONTACTS        ,contacts);
        cv.put(KEY_IMAGE_LINK      ,image_link);
        cv.put(KEY_IMAGE_DOWNLOAD  ,0);
        cv.put(KEY_FAVOURITE       ,0);

        open();
        success = ourDatabase.insert(DATABASE_TABLE, null, cv);
//        close();

        return success;
    }
}
