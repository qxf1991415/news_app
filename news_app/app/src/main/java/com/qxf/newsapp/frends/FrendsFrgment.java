package com.qxf.newsapp.frends;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qxf.newsapp.R;
import com.qxf.newsapp.utils.BaseSupportFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/15.
 */

public class FrendsFrgment extends BaseSupportFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frends, container, false);
        view.setOnTouchListener(new DontSpillOnTouchListener());
        ButterKnife.bind(this, view);
        return view;
    }
}
