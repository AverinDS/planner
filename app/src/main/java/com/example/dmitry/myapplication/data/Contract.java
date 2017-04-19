package com.example.dmitry.myapplication.data;

import android.provider.BaseColumns;
import android.text.format.DateFormat;

import java.sql.Time;
import java.util.Date;

/**
 * Created by dmitry on 19.04.17.
 */

public class Contract {

    public static final class doing implements BaseColumns
    {
        public static final  String DATABASE_NAME = "do";

        public static final String _ID = BaseColumns._ID;
        public static final String DATE = "date";
        public static final String TIME = "time";
        public static final String DOING = "doing";


    }
}
