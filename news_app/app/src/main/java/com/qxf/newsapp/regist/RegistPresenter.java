package com.qxf.newsapp.regist;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/11/6.
 */

public class RegistPresenter implements RegistContract.Presenter {

    private RegistActivity activity;
    private RegistContract.View registView;

    @Override
    public void start() {
    }

    public RegistPresenter(RegistContract.View registView, Activity activity) {
        this.registView = registView;
        this.activity = (RegistActivity) activity;
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
}
