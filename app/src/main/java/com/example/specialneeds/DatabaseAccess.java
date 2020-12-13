package com.example.specialneeds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database = null;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if (instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.database = openHelper.getWritableDatabase();
    }

    public void close(){
        if (database != null){
            this.database.close();
        }
    }

    public List<String> getData(){
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM User", null);
        cursor.moveToNext();

        while(!cursor.isAfterLast()){
            list.add(cursor.getString(2));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public void addUser(String name, String email, String password){
        ContentValues initialValues = new ContentValues();

        initialValues.put("User_Name", name);
        initialValues.put("User_Email", email);
        initialValues.put("User_Password", password);
        database.insert("User", null, initialValues);
    }
}
