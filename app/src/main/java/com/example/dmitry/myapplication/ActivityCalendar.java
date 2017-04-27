package com.example.dmitry.myapplication;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;

public class ActivityCalendar extends AppCompatActivity {

    DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        //CalendarView calendar = (CalendarView)findViewById(R.id.calendarView);
       // calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                date = dayOfMonth+"."+ month+"."+year;
//            }
//        });

        //i = calendar.getDate();
        datePicker = (DatePicker)findViewById(R.id.datePicker1);
        datePicker.getDayOfMonth();
        datePicker.getMonth();
        datePicker.getYear();
    }

    String date;long i;
    public void onClickListDo(View v)
    {
        date = String.valueOf(datePicker.getDayOfMonth()+ "."+datePicker.getMonth()+"."+datePicker.getYear());


        Intent intent = new Intent(this, dayActivity.class);
       // date = String.valueOf(i);

        try {
            intent.putExtra("date", date);
            startActivity(intent);
        }
        catch (Exception ex) {
        ex.getMessage();
        }
    }
}
