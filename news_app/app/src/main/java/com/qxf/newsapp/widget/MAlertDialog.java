package com.qxf.newsapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.qxf.newsapp.R;


public class MAlertDialog extends Dialog implements OnClickListener {

    protected Button btnLeft;
    protected Button btnRight;
    protected Button btnMiddle;
    protected TextView alertTitle;
    protected TextView alertMsg;
    private View contentView;
    private DialogClickListener mDialogClickListener;
    private int sumButtons;
    public static final int NO_BUTTON = 1;
    public static final int SINGLE_BUTTON = 2;
    public static final int DOUBLE_BUTTON = 3;
    private static final String TAG = MAlertDialog.class.getSimpleName();
    private TextView tvCancle;

    public interface DialogClickListener {
        void onClick(View view);
    }

    public MAlertDialog(Context context, int buttons) {
        super(context, R.style.CustomAlertDialog);
        this.setCancelable(true);
        this.sumButtons = buttons;
    }

    public MAlertDialog(Context context, int buttons, DialogClickListener listener) {
        super(context, R.style.CustomAlertDialog);
        mDialogClickListener = listener;
        this.setCancelable(true);
        this.sumButtons = buttons;
    }

    public MAlertDialog(Context context, int buttons, View contentView, DialogClickListener
            listener) {
        super(context, R.style.CustomAlertDialog);
        mDialogClickListener = listener;
        this.setCancelable(true);
        this.sumButtons = buttons;
        this.contentView = contentView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        switch (sumButtons) {
            case NO_BUTTON:
                setContentView(R.layout.alertdialog_no_btn);
                alertTitle = (TextView) findViewById(R.id.alert_dialog_title);
                alertMsg = (TextView) findViewById(R.id.alert_dialog_msg);
                tvCancle = (TextView) findViewById(R.id.cancle);
                alertMsg.setOnClickListener(this);
                alertTitle.setOnClickListener(this);
                tvCancle.setOnClickListener(this);
                break;
            case SINGLE_BUTTON:
                setContentView(R.layout.alertdialog_single_btn);
                alertTitle = (TextView) findViewById(R.id.alert_dialog_title);
                alertMsg = (TextView) findViewById(R.id.alert_dialog_msg);
                btnRight = (Button) findViewById(R.id.right_btn);
                btnRight.setOnClickListener(this);
                break;
            case DOUBLE_BUTTON:
                setContentView(R.layout.alertdialog_double_btn);
                alertTitle = (TextView) findViewById(R.id.alert_dialog_title);
                alertMsg = (TextView) findViewById(R.id.alert_dialog_msg);
                btnLeft = (Button) findViewById(R.id.left_btn);
                btnRight = (Button) findViewById(R.id.right_btn);
                btnLeft.setOnClickListener(this);
                btnRight.setOnClickListener(this);
                break;
            default:
                break;
        }
    }

    /**
     * 将对话框设为全局性对话框
     */
    public void setSystemAlertType() {
        getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
    }

    @Override
    public void onClick(View v) {
        this.dismiss();
        // add by jiaruiping for v(2.0.0) 2016.3.28 避免空指针异常
        if (mDialogClickListener != null) {
            mDialogClickListener.onClick(v);
        }
    }

    public Button getLeftButton() {
        return btnLeft;
    }

    public Button getRightButton() {
        return btnRight;
    }

    public Button getMiddleButton() {
        return btnMiddle;
    }

    public TextView getTitle() {
        return alertTitle;
    }

    public TextView getMsg() {
        return alertMsg;
    }

    public void setDialogClickListener(DialogClickListener dialogClickListener) {
        this.mDialogClickListener = dialogClickListener;
    }

    public TextView getTvCancle() {
        return tvCancle;
    }

    public void setTvCancle(TextView tvCancle) {
        this.tvCancle = tvCancle;
    }

    @Override
    public void show() {
        try {
            super.show();
        } catch (Exception e) {
            Log.e(TAG, "show dialog error the message is " + e.getMessage());
        }
    }
}
