package com.qxf.newsapp.regist;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.qxf.newsapp.R;
import com.qxf.newsapp.base.BaseActivity;
import com.qxf.newsapp.data.AppInjection;
import com.qxf.newsapp.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistActivity extends BaseActivity implements RegistContract.View {

    @BindView(R.id.user)
    TextInputEditText user;
    @BindView(R.id.layout_user)
    TextInputLayout layoutUser;
    @BindView(R.id.password)
    TextInputEditText password;
    @BindView(R.id.layout_password)
    TextInputLayout layoutPassword;
    @BindView(R.id.re_password)
    TextInputEditText rePassword;
    @BindView(R.id.layout_re_password)
    TextInputLayout layoutRePassword;
    @BindView(R.id.no_regist)
    Button noRegist;
    @BindView(R.id.ok_regist)
    Button okRegist;
    private RegistPresenter registPresenter;
    private String mUserName;
    private String mPassword;
    private String mRePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_regist);
        ButterKnife.bind(this);
        setTitle("注册账号");
        registPresenter = new RegistPresenter(this, this, AppInjection.provideUserDataSource());
        start();
    }

    @Override
    public void start() {
        inintUserInfo();
        initError();
    }

    @Override
    public void inintUserInfo() {
        mUserName = user.getText().toString().trim();
        mPassword = password.getText().toString().trim();
        mRePassword = rePassword.getText().toString().trim();
    }

    @Override
    public void initError() {
        registPresenter.chechInput(user, layoutUser, "用户名应该在6到20位之间");
        registPresenter.chechInput(password, layoutPassword, "密码应该在6到20位之间");
    }

    @OnClick({R.id.no_regist, R.id.ok_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.no_regist:
                registPresenter.JumpToRegist(LoginActivity.class);
                break;
            case R.id.ok_regist:
                inintUserInfo();
                boolean checkUserInfo = registPresenter.checkUserInfo(mUserName, mPassword, mRePassword);
                if (checkUserInfo) {
                    registPresenter.registUser(mUserName, mPassword);
                    registPresenter.initHuanXinAccount(mUserName, mPassword);
                } else {
                    user.setText("");
                    password.setText("");
                    rePassword.setText("");
                }

                break;
        }
    }

}
