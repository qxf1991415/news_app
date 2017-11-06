package com.qxf.newsapp.regist;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;

import com.qxf.newsapp.R;
import com.qxf.newsapp.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistActivity extends com.qxf.newsapp.base.BaseActivity implements RegistContract.View {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
        setTitle("注册账号");
        registPresenter = new RegistPresenter(this, this);
    }

    @OnClick({R.id.no_regist, R.id.ok_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.no_regist:
                registPresenter.JumpToRegist(LoginActivity.class);
                break;
            case R.id.ok_regist:
                registPresenter.JumpToRegist(LoginActivity.class);
                break;
        }
    }
}
