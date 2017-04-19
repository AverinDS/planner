package com.example.dmitry.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

public class dayActivity extends AppCompatActivity {

    ListView list;
    TimePicker timePicker;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        list = (ListView)findViewById(R.id.listDoing);
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        editText = (EditText)findViewById(R.id.editText);

        //adding default information in list
        final String[] list_do = getResources().getStringArray(R.array.doing);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_do);
        list.setAdapter(adapter);


    }

    public void onClickAdd_doing(View v)
    {
        Intent intent = new Intent(this,addingDoingActivity.class);
        startActivity(intent);
    }

    public void onClickCheckDB(View v)
    {

    }

    public void addingInDB()
    {

    }
}
