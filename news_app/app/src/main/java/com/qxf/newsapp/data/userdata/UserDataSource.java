package com.qxf.newsapp.data.userdata;

import java.util.List;

/**
 * Created by Administrator on 2017/11/7.
 */

public interface UserDataSource {

    List<User> getUserInfo(String userName);
    void deleteUserInfo(String userName);
    void updateUserInfo(User user);
    void insertUserInfo(User user);
}
