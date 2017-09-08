package com.qxf.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.qxf.newsapp.main.MainActivity;

public class SplashActivity extends AppCompatActivity {
    private TextView tv_time;
    private MyCountDownTimer mc;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置Activity为全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //去标题状态栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        tv_time = (TextView) findViewById(R.id.tv_time);
        btn = (Button) findViewById(R.id.btn);
        mc = new MyCountDownTimer(6000, 1000);
        mc.start();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
            }
        });
    }

    //页面跳转的方法
    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();//完成跳转后销毁闪屏页（从栈内移除）
    }

    class MyCountDownTimer extends CountDownTimer {
        /**
         * @param millisInFuture    表示以毫秒为单位 倒计时的总数
         *                          例如 millisInFuture=1000 表示1秒
         * @param countDownInterval 表示 间隔 多少微秒 调用一次 onTick 方法
         *                          例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public void onFinish() {
            startMainActivity();
        }

        public void onTick(long millisUntilFinished) {
            tv_time.setText(millisUntilFinished / 1000 +"");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mc.cancel();
    }

}

