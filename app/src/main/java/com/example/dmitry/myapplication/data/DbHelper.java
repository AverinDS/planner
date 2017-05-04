package com.example.dmitry.myapplication.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by dmitry on 19.04.17.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static  final  String LOG_TAG = DbHelper.class.getSimpleName();//what is this var?
    private static final String DATABASE_NAME = "database.db";
   // private static final int DATABASE_VERSION = 1;


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

    public String[] getTimeDoing(String conditionOfChoose)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM "+ Contract.doing.TABLE_NAME;
        if(conditionOfChoose != "") query+= " WHERE "+ conditionOfChoose+";";


            Cursor cursor = db.rawQuery(query, null);
            String[] rows = new String[cursor.getCount()];

            int indexTime = cursor.getColumnIndex(Contract.doing.TIME);
            int indexDoing = cursor.getColumnIndex(Contract.doing.DOING);

            int indexRows = 0;

        try
        {
            while (cursor.moveToNext())
            {
                rows[indexRows] = cursor.getString(indexTime) + " " + cursor.getString(indexDoing);
                indexRows++;
            }
        }
        catch (Exception ex)
        {
            //??
        }
        finally {
            cursor.close();
        }

        return rows;
    }

    public String insertInDB(String date, String time, String doing)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "INSERT INTO " + Contract.doing.TABLE_NAME +
                    " (" + Contract.doing.DATE_OF_EXE + ", " + Contract.doing.TIME + ", " + Contract.doing.DOING + ")" +
                    " VALUES('" + date + "', '" + time + "', '" + doing + "');";
            db.execSQL(query);
            return "Создание дело завершено успешно!";
        }
        catch (Exception ex)
        {
            return  ex.getMessage();
        }

    }

    public String deleteFromDb(String date, String time, String doing)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "DELETE FROM " + Contract.doing.TABLE_NAME +
                    " WHERE " + Contract.doing.DATE_OF_EXE + " = '" + date + "' AND " +
                    Contract.doing.TIME + " = '" + time + "' AND " +
                    Contract.doing.DOING + " = '" + doing + "';";
            db.execSQL(query);
            return "Успешно удалено!";
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }
    }

    public String updateInDB(String date, String oldTime, String newTime, String oldDoing, String newDoing)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "UPDATE " + Contract.doing.TABLE_NAME + " SET " +
                    Contract.doing.TIME + " = '" + newTime + "', " +
                    Contract.doing.DOING + " = '" + newDoing + "' " +
                    "WHERE " + Contract.doing.TIME + " = '" + oldTime + "' AND " + Contract.doing.DOING + " = '" + oldDoing+"';";
            db.execSQL(query);
            return "Успешно изменено!";
        }
        catch (Exception ex)
        {
            return  ex.getMessage();
        }

    }
}
