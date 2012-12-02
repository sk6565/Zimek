package com.SystemInfo_Report;

import java.util.ArrayList;
import java.util.List;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class SystemInfoHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "SystemInfoReports";
 
    // Contacts table name
    private static final String TABLE_REPORTS = "Reports";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_VIPSERIAL = "VipSerial";
    private static final String KEY_ZVACSERIAL = "ZvacSerial";
    private static final String KEY_OPLCSERIAL = "OplcSerial";
    private static final String KEY_CUSTOMER = "Customer";
    private static final String KEY_DELIVERYDATE = "DeliveryDate";
    private static final String KEY_WARRANTY = "Warranty";
    private static final String KEY_OTHER = "Other";

    
 
    public SystemInfoHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REPORTS_TABLE = "CREATE TABLE " + TABLE_REPORTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_VIPSERIAL + " TEXT,"
                + KEY_ZVACSERIAL + " TEXT," + KEY_OPLCSERIAL + " TEXT," + KEY_CUSTOMER + " TEXT,"
                + KEY_DELIVERYDATE + " TEXT," + KEY_WARRANTY + " TEXT," + KEY_OTHER + " TEXT" + ")";
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
    void addReport(SystemInfoReports report) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_VIPSERIAL, report.getVipSerial()); // Contact Name
        values.put(KEY_ZVACSERIAL, report.getZvacSerial()); // Contact Phone
        values.put(KEY_OPLCSERIAL, report.getOplcSerial()); // Contact Name
        values.put(KEY_CUSTOMER, report.getCustomer()); // Contact Phone
        values.put(KEY_DELIVERYDATE, report.getDeliveryDate()); // Contact Phone
        values.put(KEY_WARRANTY, report.getWarranty()); // Contact Phone
        values.put(KEY_OTHER, report.getOther()); // Contact Phone
       
 
        // Inserting Row
        db.insert(TABLE_REPORTS, null, values);
        db.close(); // Closing database connection
    }
 
    // Getting single contact
    public SystemInfoReports getReport(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_REPORTS, new String[] { KEY_ID,
                KEY_VIPSERIAL, KEY_ZVACSERIAL, KEY_OPLCSERIAL, KEY_CUSTOMER, KEY_DELIVERYDATE, KEY_WARRANTY, KEY_OTHER  }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        SystemInfoReports contents = new SystemInfoReports(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), 
                cursor.getString(5), cursor.getString(6), cursor.getString(7));
        // return contact
        return contents;
    }
 
    // Getting All Contacts
    public List<SystemInfoReports> getAllReports() {
        List<SystemInfoReports> reportsList = new ArrayList<SystemInfoReports>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_REPORTS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SystemInfoReports report = new SystemInfoReports();
                report.setID(Integer.parseInt(cursor.getString(0)));
                report.setVipSerial(cursor.getString(1));
                report.setZvacSerial(cursor.getString(2));
                report.setOplcSerial(cursor.getString(3));
                report.setCustomer(cursor.getString(4));
                report.setDeliveryDate(cursor.getString(5));
                report.setWarranty(cursor.getString(6));
                report.setOther(cursor.getString(7));
 
                // Adding contact to list
                reportsList.add(report);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return reportsList;
    }
 
    // Updating single contact
    public int updateContents(SystemInfoReports contents) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_VIPSERIAL, contents.getVipSerial());
        values.put(KEY_ZVACSERIAL, contents.getZvacSerial());
        values.put(KEY_OPLCSERIAL, contents.getOplcSerial());
        values.put(KEY_CUSTOMER, contents.getCustomer());
        values.put(KEY_DELIVERYDATE, contents.getDeliveryDate());
        values.put(KEY_WARRANTY, contents.getWarranty());
        values.put(KEY_OTHER, contents.getOther());
        
        // updating row
        return db.update(TABLE_REPORTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contents.getID()) });
    }
 
    // Deleting single contact
    public void deleteContents(SystemInfoReports contents) {
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