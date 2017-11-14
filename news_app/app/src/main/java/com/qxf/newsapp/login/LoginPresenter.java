package com.qxf.newsapp.login;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.qxf.newsapp.data.userdata.User;
import com.qxf.newsapp.data.userdata.UserDataSource;

import java.util.List;

/**
 * Created by Administrator on 2017/11/6.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginActivity activity;
    private LoginContract.View loginView;
    private UserDataSource userDataSource;

    @Override
    public void start() {
    }

    public LoginPresenter(LoginContract.View loginView, Activity activity, UserDataSource userDataSource) {
        this.loginView = loginView;
        this.activity = (LoginActivity) activity;
        this.userDataSource = userDataSource;
    }

    @Override
    public void JumpToRegist(Class<? extends Activity> clazz, boolean finish) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
        if (finish) {
            activity.finish();
        }
    }

    @Override
    public boolean checkUserInfo(String mUserName, String mPassword) {
        if (mUserName == null || mPassword == null || mUserName.length() <= 0 || mPassword.length() <= 0) {
            Toast.makeText(activity, "请输入用户名或者密码！", Toast.LENGTH_SHORT).show();
            return false;
        }
        List<User> userInfos = userDataSource.getUserInfo(mUserName);
        if (userInfos.size() == 0) {
            Toast.makeText(activity, "用户不存在，请确认后输入！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!mPassword.equals(userInfos.get(0).getPassword())) {
            Toast.makeText(activity, "密码错误！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
