package com.example.dmitry.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Toast;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class ActivityCalendar extends ActionBarActivity {

    DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_calendar);

        datePicker = (DatePicker)findViewById(R.id.datePicker1);
        datePicker.getDayOfMonth();
        datePicker.getMonth();
        datePicker.getYear();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //navDrawer
        new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.drawer_header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_calendar).withIcon(FontAwesome.Icon.faw_calendar),
                        new SecondaryDrawerItem().withName(R.string.action_settings).withIcon(FontAwesome.Icon.faw_android)

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        switch (position){
                            case 1:{
                                drawMessage("Вы уже здесь");
                                break;
                            }
                            case 2:
                            {
                               goToSetting();
                            }
                        }
                    }
                })
                .build();
    }

    String date;long i;
    public void onClickListDo(View v)
    {
        date = String.valueOf(datePicker.getDayOfMonth()+ "."+datePicker.getMonth()+"."+datePicker.getYear());


        Intent intent = new Intent(this, DayActivity.class);


        try {
            intent.putExtra("date", date);
            startActivity(intent);
        }
        catch (Exception ex) {
        ex.getMessage();
        }
    }

    public void drawMessage(String message)
    {
        Toast t = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        t.show();
    }

    public void goToSetting()
    {
        Intent intent = new Intent(this, AboutUs.class);
        startActivity(intent);
    }
}
