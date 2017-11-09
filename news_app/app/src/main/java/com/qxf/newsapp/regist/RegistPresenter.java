package com.qxf.newsapp.regist;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.qxf.newsapp.data.userdata.User;
import com.qxf.newsapp.data.userdata.UserDataSource;

import java.util.List;

/**
 * Created by Administrator on 2017/11/6.
 */

public class RegistPresenter implements RegistContract.Presenter {

    private RegistActivity activity;
    private RegistContract.View registView;
    private UserDataSource userDataSource;

    @Override
    public void start() {
    }

    public RegistPresenter(RegistContract.View registView, Activity activity, UserDataSource userDataSource) {
        this.registView = registView;
        this.activity = (RegistActivity) activity;
        this.userDataSource = userDataSource;
    }

    @Override
    public void JumpToRegist(Class<? extends Activity> clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    public void chechInput(EditText editText, final TextInputLayout inputLayout, final String errorWarn) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() < 6 || charSequence.length() > 20) {
                    inputLayout.setError(errorWarn);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                int length = editable.toString().length();
                if (length >= 6 && length <= 20) {
                    inputLayout.setError(null);
                }
            }
        });
    }

    @Override
    public boolean checkUserInfo(String mUserName, String mPassword, String mRePassword) {
        if (mUserName == null || mPassword == null || mUserName.length() <= 0 || mPassword.length() <= 0) {
            Toast.makeText(activity, "用户名密码不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (mRePassword.length() <= 0) {
            Toast.makeText(activity, "请确认密码！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!mRePassword.equals(mPassword)) {
            Toast.makeText(activity, "两次输入密码不一致，请确认密码！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (userDataSource.getUserInfo(mUserName).size() != 0) {
            Toast.makeText(activity, "用户名已被注册，请更换并重新注册！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void registUser(String mUserName, String mPassword) {
        List<User> userInfo = userDataSource.getUserInfo(mUserName);
        User user = new User(mUserName, mPassword);
        userDataSource.insertUserInfo(user);
        JumpToRegist(RegistSucessActivity.class);
    }
}
