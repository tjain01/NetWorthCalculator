package com.example.networthcalculator;

/* Creating the ASSET_TABLE*/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseAsset extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AssetsAndLiabilitiesDatabase";
    public static final String COLUMN_ASSET_NAME = "assetName";
    public static final String COLUMN_ASSET_VALUE = "assetValue";
    public static final String ASSET_TABLE = "assets";
    public static final String LIABILITY_TABLE = "liabilities";
    public static final String COLUMN_LIABILITY_NAME = "liabilityName";
    public static final String COLUMN_LIABILITY_VALUE = "liabilityValue";

   String whichTable;

    public DatabaseAsset(Context context, String assetsOrLiabilities){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        whichTable = assetsOrLiabilities;

    }

    //creating the ASSET_TABLE
    @Override
    public void onCreate(SQLiteDatabase db) {

        if(whichTable == "Assets"){

            String CREATE_ASSETS_TABLE = "CREATE TABLE " +
                    ASSET_TABLE + "("
                    + COLUMN_ASSET_NAME + " TEXT ," + COLUMN_ASSET_VALUE
                    + " TEXT " + ")";
            db.execSQL(CREATE_ASSETS_TABLE);

        }

        if (whichTable == "Liabilities"){
            String CREATE_LIABILITY_TABLE = "CREATE TABLE " +
                    LIABILITY_TABLE + "("
                    + COLUMN_LIABILITY_NAME + " TEXT ," + COLUMN_LIABILITY_VALUE
                    + " TEXT " + ")";
            db.execSQL(CREATE_LIABILITY_TABLE);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(whichTable == "Assets")
        {
            db.execSQL("DROP TABLE IF EXISTS " + ASSET_TABLE);
            onCreate(db);
        }else {
            db.execSQL("DROP TABLE IF EXISTS " + LIABILITY_TABLE);
            onCreate(db);
        }
    }

    //adding values in the table

    public void addAsset(String assetName, String assetValue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ASSET_NAME, assetName);
        values.put(COLUMN_ASSET_VALUE, assetValue);
        db.insert(ASSET_TABLE, null, values);
        db.close();
    }

    //retreiving the values from the table

    public Cursor getAssets(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + ASSET_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;

    }

    //To delete a single asset
    public boolean deleteAsset(String assetName){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + ASSET_TABLE + " WHERE " + COLUMN_ASSET_NAME + " = \" " + assetName + "\" ";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            String data = cursor.getString(0);
            db.delete(ASSET_TABLE, data, null);
            cursor.close();
            result = true;
        }

        db.close();
        return result;

    }

    //to delete all the rows in the table
    public boolean deleteAllAsset(){
        //boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + ASSET_TABLE);
        db.close();

        return true;

    }

    //counting the number of rows in the table

    public int assetCount(){

        String countQuery = "SELECT  * FROM " + ASSET_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;

    }


}
