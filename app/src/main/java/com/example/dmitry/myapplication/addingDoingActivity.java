package com.example.dmitry.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

public class addingDoingActivity extends AppCompatActivity {

    TimePicker time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_doing);
        time = (TimePicker)findViewById(R.id.timePicker);//установка 24-часового формата
        time.setIs24HourView(true);

    }

    public void onTapEditText(View v)//отвечает за автоматическое стирание текста
    {
        EditText text = (EditText)v;
        if(text.getText().equals("Введите название...")){//почему-то не работает!!!
            text.setText("");
        }
    }
}
