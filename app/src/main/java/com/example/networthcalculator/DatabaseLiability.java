package com.example.networthcalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseLiability extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LiabilitiesDatabase";
    public static final String LIABILITY_TABLE = "liabilities";
    public static final String COLUMN_LIABILITY_NAME = "liabilityName";
    public static final String COLUMN_LIABILITY_VALUE = "liabilityValue";


    public DatabaseLiability(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //creating the table for liabilities

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LIABILITY_TABLE = "CREATE TABLE " +
                LIABILITY_TABLE + "("
                + COLUMN_LIABILITY_NAME + " TEXT ," + COLUMN_LIABILITY_VALUE
                + " TEXT " + ")";
        db.execSQL(CREATE_LIABILITY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + LIABILITY_TABLE);
        onCreate(db);

    }

    //adding values in liability table

    public void addLiability(String liabilityName, String liabilityValue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LIABILITY_NAME, liabilityName);
        values.put(COLUMN_LIABILITY_VALUE, liabilityValue);
        db.insert(LIABILITY_TABLE, null, values);
        db.close();
    }

    //deleting all rows

    public boolean deleteAllLiability(){
        boolean result = false;
       SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL("DELETE FROM " + LIABILITY_TABLE);
        db.close();

        return true;

    }

    //deleting a single row

    public boolean deleteLiability(String liabilityName){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + LIABILITY_TABLE + " WHERE " + COLUMN_LIABILITY_NAME + " = \" " + liabilityName + "\" ";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            String data = cursor.getString(0);
            db.delete(LIABILITY_TABLE, data, null);
            cursor.close();
            result = true;
        }

        db.close();
        return result;

    }
    //reteriving all rows from db

    public Cursor getLiabilities(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + LIABILITY_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;

    }

    //counting the number of rows

    public int liabilityCount(){

        String countQuery = "SELECT  * FROM " + LIABILITY_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;

    }

}
