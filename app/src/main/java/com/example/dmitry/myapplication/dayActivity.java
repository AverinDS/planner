package com.example.dmitry.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.MenuItemHoverListener;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dmitry.myapplication.data.Contract;
import com.example.dmitry.myapplication.data.DbHelper;

public class dayActivity extends AppCompatActivity {

    ListView list;
    EditText editText;
    DbHelper mDbHelper;
    String date;

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
        Intent intent = new Intent(this,addingDoingActivity.class);
        intent.putExtra("date", date);
        startActivity(intent);
    }

    public void onClickCheckDB(View v)
    {
        Intent intent = new Intent(this, checkDB.class);
        startActivity(intent);

    }




}
