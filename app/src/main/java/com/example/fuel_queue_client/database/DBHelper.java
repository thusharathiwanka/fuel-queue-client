package com.example.fuel_queue_client.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fuel_queue_client.models.user.User;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "database.db", null, 1);
    }

    //create the user table inside sqllite db
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE user (id integer PRIMARY KEY AUTOINCREMENT, userId text, username text, role text)");
    }

    //drop already exist table and recreates it
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS user");
        onCreate(DB);
    }

    //close the db helper
    public void close(DBHelper dbHelper) {
        dbHelper.close();
    }

    /***
     * save a user inside the database
     * @param userid = id of the user in MongoDB
     * @param username = user name of the user
     * @param role  = type of the user (station-owner or customer)
     * @return = boolean value
     */
    public boolean saveUser(String userid, String username, String role){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("userId", userid);
        cv.put("username", username);
        cv.put("role", role);
        long result = db.insert("user",null, cv);

        return result != -1;
    }

    /***
     * delete user from local database
     * @param userId = id of the user in local database
     * @return = a boolean value
     */
    public boolean deleteOne(int userId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + "user" + " where id='" + userId + "'");
        db.close();

        return true;
    }

    /***
     * retrieve user object from local database
     * @return user object
     */
    public User getSingleUser(){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query("user", new String[] { "*" },
                null,
                null, null, null, null, null);
        User user = null;
        if(cursor != null)
        {
            if (cursor.moveToFirst()) {
                user = new User(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)

                );
            }
            cursor.close();
        }
        db.close();
        return user;
        }
    }
