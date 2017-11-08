package com.qxf.newsapp.data.userdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/7.
 */

public class UserDataRepository implements UserDataSource, UserDataConstant {

    private Context context;
    private UserDbHelper userDbHelper;
    private static UserDataRepository INSTANCE;

    private UserDataRepository(@NonNull Context context) {
        this.context = context;
        userDbHelper = new UserDbHelper(context);
    }

    public static UserDataRepository getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UserDataRepository(context);
        }
        return INSTANCE;
    }

    @Override
    public List<User> getUserInfo(String userName) {

        List<User> users = new ArrayList<>();
        SQLiteDatabase db = userDbHelper.getReadableDatabase();
        String[] projection = {UserDataConstant.USER_NAME, UserDataConstant.USER_PASSWORD};
        Cursor c = db.query(userDbHelper.TABLE_NAME, projection, "username = ?",
                new String[]{userName}, null, null, null);
        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                String password = c.getString(c.getColumnIndexOrThrow(USER_PASSWORD));
                User user = new User(userName, password);
                users.add(user);
            }
        }
        c.close();
        db.close();
        return users;
    }

    @Override
    public void deleteUserInfo(String userName) {
        SQLiteDatabase db = userDbHelper.getWritableDatabase();
        db.delete(UserDataConstant.TABLE_NAME, UserDataConstant.USER_NAME, new String[]{userName});
        db.close();
    }

    @Override
    public void updateUserInfo(User user) {
        SQLiteDatabase db = userDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserDataConstant.USER_PASSWORD, user.getPassword());
        db.update(UserDataConstant.TABLE_NAME, contentValues, UserDataConstant.USER_NAME, new String[]{user.getUserName()});
        db.close();
    }

    @Override
    public void insertUserInfo(User user) {
        SQLiteDatabase db = userDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserDataConstant.USER_NAME, user.getUserName());
        contentValues.put(UserDataConstant.USER_PASSWORD, user.getPassword());
        db.insert(UserDataConstant.TABLE_NAME, null, contentValues);
        db.close();
    }
}
