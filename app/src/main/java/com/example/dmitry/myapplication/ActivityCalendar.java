package com.example.dmitry.myapplication;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.GregorianCalendar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

public class ActivityCalendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CalendarView calendar = (CalendarView)findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = dayOfMonth+"."+ month+"."+year;
            }
        });
    }

    String date;
    public void onClickListDo(View v)
    {

        Intent intent = new Intent(this, dayActivity.class);

        try {
            intent.putExtra("date", date);
            startActivity(intent);
        }
        catch (Exception ex) {
        ex.getMessage();
        }
    }
}
