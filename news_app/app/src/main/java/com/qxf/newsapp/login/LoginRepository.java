package com.qxf.newsapp.login;

/**
 * Created by Administrator on 2017/11/6.
 */

public class LoginRepository implements LoginDataSource {

    private static LoginRepository INSTANCE = null;

    public static LoginRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoginRepository();
        }
        return INSTANCE;
    }
}
