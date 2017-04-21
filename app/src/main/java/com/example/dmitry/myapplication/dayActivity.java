package com.example.dmitry.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dmitry.myapplication.data.Contract;
import com.example.dmitry.myapplication.data.DbHelper;

public class dayActivity extends AppCompatActivity {

    ListView list;
    TimePicker timePicker;
    EditText editText;
    DbHelper mDbHelper;
    int hour = -1;
    int minutes = -1;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        list = (ListView)findViewById(R.id.listDoing);
        //timePicker = (TimePicker)findViewById(R.id.timePicker);
        editText = (EditText)findViewById(R.id.editText);
        Intent intent = getIntent();
        date = intent.getStringExtra("date");

        mDbHelper = new DbHelper(this, Contract.doing.TABLE_NAME,null, Contract.DATABASE_VERSION) ;


//        try {
//            timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
//                @Override
//                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
//                    hour = hourOfDay;
//                    minutes = minute;
//                }
//
//            });
//        }
//        catch (Exception ex)
//        {
//            Toast t = Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG);
//            t.show();
//        }


        //adding default information in list
        //final String[] list_do = getResources().getStringArray(R.array.doing);


        //adding information from database
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    mDbHelper.getTimeDoing(Contract.doing.DATE_OF_EXE + " = '" + date+"'"));
            list.setAdapter(adapter);
        }
        catch (Exception ex) {
            Toast t = Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG);
            t.show();
        }


    }

    public void onClickAdd_doing(View v)
    {
        Intent intent = new Intent(this,addingDoingActivity.class);
        startActivity(intent);
    }

    public void onClickCheckDB(View v)
    {
        Intent intent = new Intent(this, checkDB.class);
        startActivity(intent);

    }

    public void addingInDB()
    {

    }


}
