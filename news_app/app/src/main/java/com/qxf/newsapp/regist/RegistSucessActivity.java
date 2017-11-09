package com.qxf.newsapp.regist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import com.qxf.newsapp.R;
import com.qxf.newsapp.base.BaseActivity;
import com.qxf.newsapp.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistSucessActivity extends BaseActivity {

    @BindView(R.id.tv_warn)
    TextView tvWarn;
    @BindView(R.id.btn_jump)
    Button btnJump;
    private MyCountDown myCountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_regist_sucess);
        ButterKnife.bind(this);
        setTitle("注册成功");
        myCountDown = new MyCountDown(6000, 1000);
        myCountDown.start();
    }

    @OnClick(R.id.btn_jump)
    public void onViewClicked() {
        toLogin();

    }



    private void toLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
        myCountDown.cancel();
    }

    class MyCountDown extends CountDownTimer {

        public MyCountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @SuppressLint("StringFormatInvalid")
        @Override
        public void onTick(long l) {
            long remain = l/ 1000;
            if (remain != 1) {
                tvWarn.setText(String.format(getString(R.string.regist_sucess), String.valueOf(remain)));
            }else{
                tvWarn.setText(getString(R.string.jumping));
            }
        }

        @Override
        public void onFinish() {
            toLogin();
        }
    }
}
