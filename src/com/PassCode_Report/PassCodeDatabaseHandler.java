package com.PassCode_Report;

import java.util.ArrayList;
import java.util.List;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class PassCodeDatabaseHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "PassCodeReports";
 
    // Contacts table name
    private static final String TABLE_REPORTS = "Reports";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_DEFAULTPASSCODE = "defaultPassCode";
    private static final String KEY_NEWPASSCODE = "newPassCode";
    private static final String KEY_DEFAULTPASSCODEOPS = "defaultPassCodeOPS";
    private static final String KEY_NEWPASSCODEOPS = "NewPassCodeOPS";
    private static final String KEY_PASSCODEOPSACTIVATED = "PassCodeOPSActivated";


 
    public PassCodeDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REPORTS_TABLE = "CREATE TABLE " + TABLE_REPORTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DEFAULTPASSCODE + " TEXT,"
                + KEY_NEWPASSCODE + " TEXT," + KEY_DEFAULTPASSCODEOPS + " TEXT,"
                + KEY_NEWPASSCODEOPS + " TEXT," + KEY_PASSCODEOPSACTIVATED + " TEXT" + ")";
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
    void addReport(PassCodeReports report) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_DEFAULTPASSCODE, report.getdefaultPassCode()); // Contact Name
        values.put(KEY_NEWPASSCODE, report.getnewPassCode()); // Contact Phone
        values.put(KEY_DEFAULTPASSCODEOPS, report.getdefaultPassCodeOPS());
        values.put(KEY_NEWPASSCODEOPS, report.getNewPassCodeOPS());
        values.put(KEY_PASSCODEOPSACTIVATED, report.getPassCodeOPSActivated());

        
        // Inserting Row
        db.insert(TABLE_REPORTS, null, values);
        db.close(); // Closing database connection
    }
 
    // Getting single contact
    public PassCodeReports getReport(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_REPORTS, new String[] { KEY_ID,
                KEY_DEFAULTPASSCODE, KEY_NEWPASSCODE, KEY_DEFAULTPASSCODEOPS, KEY_NEWPASSCODEOPS, KEY_PASSCODEOPSACTIVATED  }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        PassCodeReports contents = new PassCodeReports(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2) , cursor.getString(3), cursor.getString(4) , cursor.getString(5));
        // return contact
        return contents;
    }
 
    // Getting All Contacts
    public List<PassCodeReports> getAllReports() {
        List<PassCodeReports> reportsList = new ArrayList<PassCodeReports>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_REPORTS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PassCodeReports report = new PassCodeReports();
                report.setID(Integer.parseInt(cursor.getString(0)));
                report.setdefaultPassCode(cursor.getString(1));
                report.setnewPassCode(cursor.getString(2));
                report.setdefaultPassCodeOPS(cursor.getString(3));
                report.setNewPassCodeOPS(cursor.getString(4));
                report.setPassCodeOPSActivated(cursor.getString(5));
   
 
                
                // Adding contact to list
                reportsList.add(report);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return reportsList;
    }
 
    // Updating single contact
    public int updateContents(PassCodeReports contents) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_DEFAULTPASSCODE, contents.getdefaultPassCode());
        values.put(KEY_NEWPASSCODE, contents.getnewPassCode());
        values.put(KEY_DEFAULTPASSCODEOPS, contents.getdefaultPassCodeOPS());
        values.put(KEY_NEWPASSCODEOPS, contents.getNewPassCodeOPS());
        values.put(KEY_PASSCODEOPSACTIVATED, contents.getPassCodeOPSActivated());
        
        // updating row
        return db.update(TABLE_REPORTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contents.getID()) });
    }
 
    // Deleting single contact
    public void deleteContents(PassCodeReports contents) {
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
