package com.example.dmitry.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dmitry.myapplication.data.Contract;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        TextView aboutUs = (TextView)findViewById(R.id.about);
        aboutUs.setText("Если есть вопросы и пожелания, писать сюда: Planner@yandex.ru");

        TextView aboutProgr = (TextView)findViewById(R.id.versionProgram);
        aboutProgr.setText("Версия программы: 1.0, версия базы данных: "+ Contract.DATABASE_VERSION);

    }
}
