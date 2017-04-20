package com.example.dmitry.myapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dmitry on 19.04.17.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static  final  String LOG_TAG = DbHelper.class.getSimpleName();//what is this var?
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;


    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE = "CREATE TABLE "+ Contract.doing.TABLE_NAME+ " ("
                + Contract.doing._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.doing.DATE_OF_EXE  + " TEXT NOT NULL, "
                + Contract.doing.TIME + " TEXT NOT NULL, "
                + Contract.doing.DOING + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+ Contract.doing.TABLE_NAME);
        onCreate(db);

    }
}
