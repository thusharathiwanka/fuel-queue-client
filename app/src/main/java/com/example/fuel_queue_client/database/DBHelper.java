package com.example.fuel_queue_client.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE user (id integer PRIMARY KEY AUTOINCREMENT, userId text, username text, role text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS user");
        onCreate(DB);
    }

    public void close(DBHelper dbHelper) {
        dbHelper.close();
    }

    public boolean saveUser(String userid, String username, String role){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("userId", userid);
        cv.put("username", username);
        cv.put("role", role);

        long result = db.insert("user",null, cv);

        return result != -1;
    }
}