package com.qxf.newsapp.login;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.qxf.newsapp.R;
import com.qxf.newsapp.main.MainActivity;
import com.qxf.newsapp.regist.RegistActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {


    @BindView(R.id.user)
    TextInputEditText user;
    @BindView(R.id.layout_user)
    TextInputLayout layoutUser;
    @BindView(R.id.password)
    TextInputEditText password;
    @BindView(R.id.layout_password)
    TextInputLayout layoutPassword;
    @BindView(R.id.regist)
    Button regist;
    @BindView(R.id.login)
    Button login;

    private LoginPresenter mPresrenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresrenter = new LoginPresenter(this, this);
    }

    @OnClick({R.id.regist, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.regist:
                mPresrenter.JumpToRegist(RegistActivity.class);
                break;
            case R.id.login:
                mPresrenter.JumpToRegist(MainActivity.class);
                break;
        }
    }
}
