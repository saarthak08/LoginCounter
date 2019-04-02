package com.sg.logincounter.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sg.logincounter.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 3;
    public static int ide;


    private static final String DATABASE_NAME = "users";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(User.CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        onCreate(db);
    }


    public long insertUser(String name, String email,String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.COLUMN_NAME, name);
        values.put(User.COLUMN_EMAIL, email);
        values.put(User.COLUMN_PASSWORD,password);
        values.put(User.COLUMN_COUNTER,1);
        long id = db.insert(User.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public boolean checkUserEmail(String email)
    {
       SQLiteDatabase db=this.getReadableDatabase();
       Cursor cursor=db.query(User.TABLE_NAME,new String[]{User.COLUMN_ID,User.COLUMN_NAME,User.COLUMN_EMAIL,User.COLUMN_PASSWORD},
               User.COLUMN_EMAIL+ "=?",new String[]{email},null,null,null,null);
        int cursorCount=cursor.getCount();
        cursor.close();
        if(cursorCount>0)
        {
            return true;
        }
        else
        {return false;}
    }
    public boolean checkUserEmailPassword(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(User.TABLE_NAME,
                new String[]{User.COLUMN_ID, User.COLUMN_NAME, User.COLUMN_EMAIL, User.COLUMN_PASSWORD},
                User.COLUMN_EMAIL + "=?" + " AND " +User.COLUMN_PASSWORD + "=?",
                new String[]{email,password}, null, null, null, null);

        int cursorCount=cursor.getCount();
        cursor.close();
        if(cursorCount>0)
        {
            return true;
        }
        else
        {return false;}
    }

    public User getUser(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + User.TABLE_NAME.trim()
                + " WHERE " + User.COLUMN_EMAIL.trim() + "='" + email.trim() + "'";
        Cursor cursor = db.rawQuery(sql, null);
        User user = new User();

        if (cursor.moveToFirst()) {
            user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(User.COLUMN_ID))));
            user.setName(cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)));
            user.setCounter(Integer.parseInt(cursor.getString(cursor.getColumnIndex(User.COLUMN_COUNTER))));
        }
        cursor.close();
        db.close();
        return user;
    }

    public List<User> getAllUsers() {
        String[] columns = {
                User.COLUMN_ID,
                User.COLUMN_NAME,
                User.COLUMN_EMAIL,
                User.COLUMN_PASSWORD,
                User.COLUMN_COUNTER
        };

        String sortOrder =
                User.COLUMN_NAME + " ASC";
        List<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(User.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(User.COLUMN_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD)));
                user.setCounter(Integer.parseInt(cursor.getString(cursor.getColumnIndex(User.COLUMN_COUNTER))));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return userList;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.COLUMN_NAME, user.getName());
        values.put(User.COLUMN_EMAIL, user.getEmail());
        values.put(User.COLUMN_PASSWORD, user.getPassword());
        values.put(User.COLUMN_COUNTER,user.getCounter());
        db.update(User.TABLE_NAME, values, User.COLUMN_ID+ " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(User.TABLE_NAME, User.COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

}
