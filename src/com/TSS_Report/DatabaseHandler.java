package com.TSS_Report;

import java.util.ArrayList;
import java.util.List;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DatabaseHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "TSSReports";
 
    // Contacts table name
    private static final String TABLE_REPORTS = "Reports";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_LOCATIONID = "locationID";
    private static final String KEY_MYDATE = "myDate";
    private static final String KEY_MYTIME = "myTime";
    private static final String KEY_MISTTIME = "MistTime";
    private static final String KEY_DWELLTIME = "DwellTime";
    private static final String KEY_ZVACTIME = "ZvacTime";
    private static final String KEY_TOTALTIME = "TotalTime";
    private static final String KEY_ZVACSERIAL = "ZvacSerial";
    private static final String KEY_APPRESULT = "AppResult";
    private static final String KEY_TYPEAPPLICATION = "typeApplication";
    private static final String KEY_TYPECONFIGURE = "typeConfigure";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REPORTS_TABLE = "CREATE TABLE " + TABLE_REPORTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LOCATIONID + " TEXT,"
                + KEY_MYDATE + " TEXT," + KEY_MYTIME + " TEXT," + KEY_MISTTIME + " TEXT,"
                + KEY_DWELLTIME + " TEXT," + KEY_ZVACTIME + " TEXT," + KEY_TOTALTIME + 
                " TEXT," + KEY_ZVACSERIAL + " TEXT," + KEY_APPRESULT + " TEXT," + KEY_TYPEAPPLICATION + 
                " TEXT," + KEY_TYPECONFIGURE + " TEXT" + ")";
        db.execSQL(CREATE_REPORTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPORTS);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    public // Adding new contact
    void addReport(TreatmentReports report) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_LOCATIONID, report.getLocation()); // Contact Name
        values.put(KEY_MYDATE, report.getmyDate()); // Contact Phone
        values.put(KEY_MYTIME, report.getmyTime()); // Contact Name
        values.put(KEY_MISTTIME, report.getMistTime()); // Contact Phone
        values.put(KEY_DWELLTIME, report.getDwellTime()); // Contact Phone
        values.put(KEY_ZVACTIME, report.getZvacTime()); // Contact Phone
        values.put(KEY_TOTALTIME, report.getTotalTime()); // Contact Phone
        values.put(KEY_ZVACSERIAL, report.getZvacSerial()); // Contact Phone
        values.put(KEY_APPRESULT, report.getAppResult()); // Contact Phone
        values.put(KEY_TYPEAPPLICATION, report.gettypeApplication());
        values.put(KEY_TYPECONFIGURE, report.gettypeConfigure());
 
        // Inserting Row
        db.insert(TABLE_REPORTS, null, values);
        db.close(); // Closing database connection
    }
 
    // Getting single contact
    public TreatmentReports getReport(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_REPORTS, new String[] { KEY_ID,
                KEY_LOCATIONID, KEY_MYDATE, KEY_MYTIME, KEY_MISTTIME, KEY_DWELLTIME, KEY_ZVACTIME, KEY_TOTALTIME, KEY_ZVACSERIAL, KEY_APPRESULT, KEY_TYPEAPPLICATION, KEY_TYPECONFIGURE  }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        TreatmentReports contents = new TreatmentReports(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), 
                cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), 
                cursor.getString(9), cursor.getString(10), cursor.getString(11));
        // return contact
        return contents;
    }
 
    // Getting All Contacts
    public List<TreatmentReports> getAllReports() {
        List<TreatmentReports> reportsList = new ArrayList<TreatmentReports>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_REPORTS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TreatmentReports report = new TreatmentReports();
                report.setID(Integer.parseInt(cursor.getString(0)));
                report.setLocation(cursor.getString(1));
                report.setmyDate(cursor.getString(2));
                report.setmyTime(cursor.getString(3));
                report.setMistTime(cursor.getString(4));
                report.setDwellTime(cursor.getString(5));
                report.setZvacTime(cursor.getString(6));
                report.setTotalTime(cursor.getString(7));
                report.setZvacSerial(cursor.getString(8));
                report.setAppResult(cursor.getString(9));
                report.settypeApplication(cursor.getString(10));
                report.settypeConfigure(cursor.getString(11));
 
                
                // Adding contact to list
                reportsList.add(report);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return reportsList;
    }
 
    // Updating single contact
    public int updateContents(TreatmentReports contents) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_LOCATIONID, contents.getLocation());
        values.put(KEY_MYDATE, contents.getmyDate());
        values.put(KEY_MYTIME, contents.getmyTime());
        values.put(KEY_MISTTIME, contents.getMistTime());
        values.put(KEY_DWELLTIME, contents.getDwellTime());
        values.put(KEY_ZVACTIME, contents.getZvacTime());
        values.put(KEY_ZVACSERIAL, contents.getZvacSerial());
        values.put(KEY_APPRESULT, contents.getAppResult());
        values.put(KEY_TYPEAPPLICATION, contents.gettypeApplication());
        values.put(KEY_TYPECONFIGURE, contents.gettypeConfigure());
 
        // updating row
        return db.update(TABLE_REPORTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contents.getID()) });
    }
 
    // Deleting single contact
    public void deleteContents(TreatmentReports contents) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_REPORTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contents.getID()) });
        db.close();
    }
 
    // Getting contacts Count
    public int getReportsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_REPORTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();
 
        // return count
        return cursor.getCount();
       
    }
    
 
}