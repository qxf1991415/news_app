package com.qxf.newsapp.login;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qxf.newsapp.R;
import com.qxf.newsapp.base.AppConstant;
import com.qxf.newsapp.data.AppInjection;
import com.qxf.newsapp.main.MainActivity;
import com.qxf.newsapp.regist.RegistActivity;
import com.qxf.newsapp.utils.SPUtils;

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
    @BindView(R.id.unpass)
    TextView unpass;

    private LoginPresenter mPresrenter;
    private String mUserName;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresrenter = new LoginPresenter(this, this, AppInjection.provideUserDataSource());
    }

    @Override
    public void getUserInput() {
        mUserName = user.getText().toString().trim();
        mPassword = password.getText().toString().trim();
    }

    @OnClick({R.id.regist, R.id.login, R.id.unpass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.regist:
                mPresrenter.JumpToRegist(RegistActivity.class, false);
                break;
            case R.id.login:
                // TODO: 2017/11/14 屏蔽登陆逻辑，直接跳转
                getUserInput();
                boolean checkUserInfo = mPresrenter.checkUserInfo(mUserName, mPassword);
                if (checkUserInfo) {
                    mPresrenter.JumpToRegist(MainActivity.class, true);
                    SPUtils.getInstance().put(AppConstant.USER_NAME, mUserName);
                    SPUtils.getInstance().put(AppConstant.PASSWORD, mPassword);
                    Toast.makeText(this, "登陆成功！", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    user.setText("");
                    password.setText("");
                }
                mPresrenter.JumpToRegist(MainActivity.class, true);
                break;
            case R.id.unpass:
                break;
        }
    }
}
