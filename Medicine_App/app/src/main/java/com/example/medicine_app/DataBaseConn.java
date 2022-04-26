package com.example.medicine_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseConn extends SQLiteOpenHelper {
    public DataBaseConn(Context context){
        super(context,"MedicineDB",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table MDTable(medicineName TEXT,date TEXT,time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insertvalues(String name,String date,String time){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("medicineName",name);
        contentValues.put("date",date);
        contentValues.put("time",time);
        long res=database.insert("MDTable",null,contentValues);
        if (res==-1)
            return false;
        else
            return true;
    }
    public Cursor fetchdata(String date,String time){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor c= database.rawQuery("Select * from MDTable where date = '" +date+ "'and time = '"+time+"'",null);
        return c;
    }
}