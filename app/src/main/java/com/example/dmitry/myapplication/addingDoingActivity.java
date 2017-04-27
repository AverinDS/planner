package com.example.dmitry.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dmitry.myapplication.data.Contract;
import com.example.dmitry.myapplication.data.DbHelper;

public class addingDoingActivity extends AppCompatActivity {

    TimePicker time;
    EditText text;
    String date;
    DbHelper mDbHelper;
    int hour = 0;
    int minutes= 0;
    String valText ="";
    String doing ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_doing);
        time = (TimePicker)findViewById(R.id.timePicker);
        text = (EditText) findViewById(R.id.editText) ;

        // /установка 24-часового формата
        time.setIs24HourView(true);
        time.setCurrentHour(00);
        time.setCurrentMinute(00);

        Intent intent = getIntent();
        date = intent.getStringExtra("date");


        mDbHelper = new DbHelper(this, Contract.doing.TABLE_NAME,null, Contract.DATABASE_VERSION);
        time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                minutes = minute;
            }
        });



    }


    public void saveInDb(View v)
    {
        String s = text.getText().toString();
        if(s.equals(valText))
        {
            Toast t = Toast.makeText(this,"Вы не заполнили название дела", Toast.LENGTH_SHORT);
            t.show();
            return;
        }
        try {

            String fix_hour ="";//this block for correct image and work some algoritms
            String fix_minute = "";
            if(hour<10) fix_hour = "0";
            if(minutes<10) fix_minute= "0";

            mDbHelper.insertInDB(date, fix_hour+hour + ":" +fix_minute+ minutes, text.getText().toString());
            Toast t = Toast.makeText(this,"Сохранено", Toast.LENGTH_SHORT);
            t.show();
            this.finish();

        }
        catch (Exception ex)
        {
            Toast t = Toast.makeText(this,ex.getMessage(), Toast.LENGTH_LONG);
            t.show();

        }
    }

}
