package com.qxf.newsapp.data.userdata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/11/7.
 */

public class UserDbHelper extends SQLiteOpenHelper implements UserDataConstant {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "users.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    USER_NAME_ID + BOOLEAN_TYPE + " PRIMARY KEY NOT NULL" + COMMA_SEP +
                    USER_NAME + TEXT_TYPE + " NOT NULL" + COMMA_SEP +
                    USER_PASSWORD + TEXT_TYPE + " NOT NULL" +
                    " )";

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
