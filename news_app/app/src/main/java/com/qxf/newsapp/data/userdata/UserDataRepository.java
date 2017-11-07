package com.qxf.newsapp.data.userdata;

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

        String[] projection = {

        };

        Cursor c = db.query(
                userDbHelper.TABLE_NAME, projection, null, null, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                String password = c.getString(c.getColumnIndexOrThrow(USER_PASSWORD));
                User user = new User(userName, password);
                users.add(user);
            }
        }
        if (c != null) {
            c.close();
        }

        db.close();
        return users;
    }

    @Override
    public void deleteUserInfo(String userName) {

    }

    @Override
    public void updateUserInfo(String userName) {

    }
}
