package org.alexcode.crazysnakes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

//TODO: need to be changed to remote database

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CrazySnakes.db";
    private static final String TABLE_NAME = "users";
    private static final String COL_2 = "name";
    private static final String COL_3 = "email";
    private static final String COL_4 = "password";
    private static final String COL_5 = "gender";
    private static final String COL_6 = "games_played";
    private static final String COL_7 = "games_win";
    private static final String COL_8 = "games_lost";


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT UNIQUE NOT NULL, " +
                "email TEXT NOT NULL, " +
                "password TEXT NOT NULL, " +
                "gender TEXT NOT NULL, " +
                "games_played INTEGER NOT NULL, " +
                "games_win INTEGER NOT NULL, " +
                "games_lost INTEGER NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertNewUser(String name, String email, String pass, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, pass);
        contentValues.put(COL_5, gender);
        contentValues.put(COL_6, 0);
        contentValues.put(COL_7, 0);
        contentValues.put(COL_8, 0);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor checkLogin(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT name, password FROM " + TABLE_NAME + " WHERE name='" + name + "';",null);
    }

}
