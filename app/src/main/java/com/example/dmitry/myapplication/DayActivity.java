package com.example.dmitry.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dmitry.myapplication.data.Contract;
import com.example.dmitry.myapplication.data.DbHelper;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class DayActivity extends AppCompatActivity {

    ListView list;
    EditText editText;
    DbHelper mDbHelper;
    String date;
    private static final  int ID_NOTIFY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        list = (ListView)findViewById(R.id.listDoing);
        editText = (EditText)findViewById(R.id.editText);
        mDbHelper = new DbHelper(this, Contract.doing.TABLE_NAME,null, Contract.DATABASE_VERSION) ;

        Intent intent = getIntent();
        date = intent.getStringExtra("date");

        registerForContextMenu(list);//register context menu for listView

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

        //
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
                                goToCalendar();
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



    @Override
    public void onResume()
    {
        super.onResume();

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

    @Override
    public  void  onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contex_menu, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.edit:
            {
                ///edit
                Toast t = Toast.makeText(this,"EDIT", Toast.LENGTH_SHORT);
                t.show();
                String[] rows = mDbHelper.getTimeDoing(Contract.doing.DATE_OF_EXE + " = '" + date+"'");
                String row = rows[info.position];//get needed row
                String time = row.substring(0, row.indexOf(' '));
                String doing = row.substring(row.indexOf(' ')+1, row.length());
                Intent intent = new Intent(this, AddingDoingActivity.class);
                intent.putExtra("time", time);
                intent.putExtra("doing", doing);
                intent.putExtra("date", date);
                startActivity(intent);

                return true;
            }
            case  R.id.delete:
            {
                String row = list.getItemAtPosition(info.position).toString();

                ///delete with image info
                Toast t = Toast.makeText(this,mDbHelper.deleteFromDb(date,row.substring(0,5),row.substring(6, row.length())), Toast.LENGTH_SHORT);
                t.show();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                        mDbHelper.getTimeDoing(Contract.doing.DATE_OF_EXE + " = '" + date+"'"));
                list.setAdapter(adapter);
                return true;
            }
            default:
            {
                return  super.onContextItemSelected(item);
            }
        }
    }
    public void onClickAdd_doing(View v)
    {
        Intent intent = new Intent(this,AddingDoingActivity.class);
        intent.putExtra("date", date);
        startActivity(intent);
    }

    public void onClickCheckDB(View v)
    {
        Intent intent = new Intent(this, CheckDB.class);
        startActivity(intent);

    }



    public void goToSetting()
    {
        Intent intent = new Intent(this, AboutUs.class);
        startActivity(intent);
    }
    public void goToCalendar()
    {
        Intent intent = new Intent(this, ActivityCalendar.class);
        startActivity(intent);
    }

    public void onClickMakeNotify(View v)
    {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Намерение для запуска второй активности
        Intent intent = new Intent(this, this.getClass());
        intent.putExtra("date", date);
        //PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        //Строим нормальную дату
        String dateCopy = date;
        String dateNormal = "";
        //день
        if (Integer.parseInt(dateCopy.substring(0, dateCopy.indexOf('.'))) < 10)
        {
            dateNormal += "0";
        }

        dateNormal += dateCopy.substring(0, dateCopy.indexOf('.')+1);
        dateCopy = dateCopy.substring(dateCopy.indexOf('.')+1, dateCopy.length());

        //месяц
        if (Integer.parseInt(dateCopy.substring(0, dateCopy.indexOf('.'))) < 9)
        {
            dateNormal += "0";
        }
            int mounth = Integer.parseInt(dateCopy.substring(0, dateCopy.indexOf('.')));
            mounth++;
            dateNormal += String.valueOf(mounth);

        dateCopy = dateCopy.substring(dateCopy.indexOf('.'), dateCopy.length());
        //год
        dateNormal+=dateCopy;




        // Строим уведомление
        Notification builder = new Notification.Builder(this)
                .setTicker("Новая закладка")
                .setContentTitle("Закладка")
                .setContentText("Вы сделали закладку на "+ dateNormal)
                .setSmallIcon(R.drawable.ic_launcher).setContentIntent(pIntent)
                .build();

        // убираем уведомление, когда его выбрали
        builder.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(ID_NOTIFY, builder);

    }

}
