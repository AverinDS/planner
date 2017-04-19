package com.example.dmitry.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dmitry.myapplication.data.Contract;
import com.example.dmitry.myapplication.data.DbHelper;

public class checkDB extends AppCompatActivity {

    TextView editText;
    private DbHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_db);
        editText = (TextView)findViewById(R.id.editText4);
    }

    public void displayDatabaseInfo(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //Condition of getting
        String[] projection = {
                Contract.doing._ID,
                Contract.doing.DOING,
                Contract.doing.TIME,
                Contract.doing.DATE};


        Cursor cursor = db.query(
                Contract.doing.TABLE_NAME, //table
                projection, //columns
                null, //columns for where
                null, // value for condition "Where"
                null, // Don't group rows
                null,// Don't filter by row groups
                null); //order by
    }
}
