package com.example.monopoly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.FontsContract;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MonopolySQL";
    public static final int VERSION = 1;
    public static final String TABLE_NAME = "playerTable";
    public static final String TABLE_NAME2 = "companyTable";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MONEY = "money";
    public static final String COLUMN_POSITION= "position";
    public static final String COLUMN_ORDER = "turn";
    public static final String COLUMN_CREDIT = "credit";
    public static final String COLUMN_OWNER = "owner";
    public static final String COLUMN_RENT = "rent";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+ TABLE_NAME +"("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT," + COLUMN_MONEY + " INTEGER,"  +
                COLUMN_POSITION + " INTEGER," +  COLUMN_CREDIT + " NUMERIC,"+ COLUMN_ORDER + " NUMERIC" + ");");

        db.execSQL("CREATE TABLE "+ TABLE_NAME2 +"("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT," + COLUMN_POSITION + " INTEGER," + COLUMN_MONEY + " INTEGER," + COLUMN_RENT + " INTEGER," + COLUMN_OWNER + " TEXT" + ");");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    public void addPlayer(String name, int money, int position, boolean credit, boolean order) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_MONEY, money);
        values.put(COLUMN_POSITION, position);
        values.put(COLUMN_CREDIT, credit);
        values.put(COLUMN_ORDER, order);

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    public void addCompanies(String name, int money, int position, int rent, String owner) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_MONEY, money);
        values.put(COLUMN_POSITION, position);
        values.put(COLUMN_RENT, rent);
        values.put(COLUMN_OWNER, owner);

        // Inserting Row
        db.insert(TABLE_NAME2, null, values);
        db.close(); // Closing database connection
    }

    public String getCompany (int position){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME2,null, COLUMN_POSITION + " = " + position,
                 null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Log.d("DB", cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            String result = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            cursor.close();
            return result;
        }
        return null;

    }

    public String getCompanyOwner (int position){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME2,null, COLUMN_POSITION + " = " + position,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Log.i("DB","owner =" +cursor.getString(cursor.getColumnIndex(COLUMN_OWNER)));
            String result = cursor.getString(cursor.getColumnIndex(COLUMN_OWNER));
            cursor.close();
            return result;
        }
        return null;

    }

    public int getCompanyCost (int position){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME2,null, COLUMN_POSITION + " = " + position,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int result = cursor.getInt(cursor.getColumnIndex(COLUMN_MONEY));
            cursor.close();
            return result;
        }
        return -1;
    }

    public int getCompanyRent (int position){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME2,null, COLUMN_POSITION + " = " + position,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int result = cursor.getInt(cursor.getColumnIndex(COLUMN_RENT));
            cursor.close();
            return result;
        }
        return -1;
    }

    public void updateCompanyOwner (int position, String owner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_OWNER, owner);
        db.update(TABLE_NAME2, values, COLUMN_POSITION + " = " + position, null);
    }

    public int getPlayerPosition (String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null, COLUMN_NAME + " = " + name,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int result = cursor.getInt(cursor.getColumnIndex(COLUMN_POSITION));
            cursor.close();
            return result;
        }
        return -1;
    }

    public void updatePlayerPosition (int position, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_POSITION, position);
        db.update(TABLE_NAME, values, COLUMN_NAME + " = " + name, null);
    }

    public int getPlayerMoney (String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null, COLUMN_NAME + " = " + name,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int result = cursor.getInt(cursor.getColumnIndex(COLUMN_MONEY));
            cursor.close();
            return result;
        }
        return -1;
    }

    public void updatePlayerMoney (int money, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MONEY, money);
        db.update(TABLE_NAME, values, COLUMN_NAME + " = " + name, null);
    }

    public boolean getPlayerCredit (String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null, COLUMN_NAME + " = " + name,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            boolean result = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_MONEY)));
            cursor.close();
            return result;
        }
        return false;
    }

    public void updatePlayerCredit (boolean credit, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CREDIT, credit);
        db.update(TABLE_NAME, values, COLUMN_NAME + " = " + name, null);
    }

    public boolean getPlayerOrder (String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null, COLUMN_NAME + " = " + name,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            boolean result = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER)));
            cursor.close();
            return result;
        }
        return false;
    }

    public void updatePlayerOrder (boolean order, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDER, order);
        db.update(TABLE_NAME, values, COLUMN_NAME + " = " + name, null);
    }

    public String getPlayerCompanies (String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME2,null, COLUMN_OWNER + " = ?" ,new String[]{String.valueOf(name)},
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            String items="";

            if (cursor.moveToFirst()) {

                int nameColIndex = cursor.getColumnIndex(COLUMN_NAME);
                do {
                    items+=(cursor.getString(nameColIndex))+"\n";
                }
                while (cursor.moveToNext());

            }
            cursor.close();
            return items;
        }
        return null;

    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.delete(TABLE_NAME,null,null);
        db.execSQL("delete from " + TABLE_NAME);
        db.execSQL("delete from " + TABLE_NAME2);
        //db.execSQL("TRUNCATE table" + TABLE_NAME);
        //db.close();
    }
}

