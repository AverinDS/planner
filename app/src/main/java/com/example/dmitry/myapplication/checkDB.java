package com.example.dmitry.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dmitry.myapplication.data.Contract;
import com.example.dmitry.myapplication.data.DbHelper;

public class checkDB extends AppCompatActivity {

    TextView editText;
    private DbHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {

            mDbHelper = new DbHelper(this, Contract.doing.TABLE_NAME, null, 1);//создание таблицы
            setContentView(R.layout.activity_check_db);
            editText = (TextView) findViewById(R.id.editText4);
            insertDefault();
            displayDatabaseInfo();
        }
        catch (Exception ex) {
            Toast t = Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG);
            t.show();
        }
    }

    public void displayDatabaseInfo(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        //String path = this.getDatabasePath("do").getAbsolutePath();
        //Condition of getting
        String[] projection = {
                Contract.doing._ID,
                Contract.doing.DATE_OF_EXE ,
                Contract.doing.TIME,
                Contract.doing.DOING};

        //make query
        String query = "SELECT * FROM "+Contract.doing.TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        try {
            //some information for user
            editText.setText("Таблица содержит " + cursor.getCount() + "записей\n\n");
            editText.append(
                    Contract.doing._ID + " - " +
                            Contract.doing.DATE_OF_EXE  + " - " +
                            Contract.doing.TIME + " - " +
                            Contract.doing.DOING + "\n");
            //Get index every column
            int index_id = cursor.getColumnIndex(Contract.doing._ID);
            int index_date = cursor.getColumnIndex(Contract.doing.DATE_OF_EXE );
            int index_time = cursor.getColumnIndex(Contract.doing.TIME);
            int index_doing = cursor.getColumnIndex(Contract.doing.DOING);

            //go on all rows
            while (cursor.moveToNext()) {
                int id = cursor.getInt(index_id);
                String date = cursor.getString(index_date);
                String time = cursor.getString(index_time);
                String doing = cursor.getString(index_doing);
                editText.append("\n"
                        + id + " - "
                        + date + " - "
                        + time + " - "
                        + doing);
            }
        }
        catch (Exception ex)
        {
            Toast t = Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG);
            t.show();
        }
        finally {
            cursor.close();
        }
    }

    public void insertDefault()
    {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.doing.DATE_OF_EXE , "15.02.2012");
        values.put(Contract.doing.TIME, "15:00");
        values.put(Contract.doing.DOING, "настройка БД успешна");
        long i = db.insert(Contract.doing.TABLE_NAME,null,values);
        //db.close();
        String query = "INSERT INTO "+ Contract.doing.TABLE_NAME +
                " ("+ Contract.doing.DATE_OF_EXE +", "+Contract.doing.TIME+", "+Contract.doing.DOING+")"+
                " VALUES('15.02.2012', '15:00', 'настройка БД успешна');";
        db.execSQL(query);
    }
}
