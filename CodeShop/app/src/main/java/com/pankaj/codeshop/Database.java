package com.pankaj.codeshop;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.*;


public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="SavedPagesMeta.db";
    public static final String TABLE_NAME="Main";
    public static final String COL1="ID";
    public static final String COL2="TITLE";
    public static final String COL3="DATE";
    public static final String COL4="LYRICS";
    public static final String COL5="IMAGE";

    public Database(Context context) {
        //check this code
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT,DATE TEXT,LYRICS TEXT,IMAGE BLOB)";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String title , String date, String lyrics,byte[] img) {

        SQLiteDatabase dataBase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL2, title);
        values.put(COL3, date);
        values.put(COL4, lyrics);
        values.put(COL5, img);
        long result= dataBase.insert(TABLE_NAME, null, values);
        if(result==-1)
            return false;
        else
            return true;
    }


    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

}
