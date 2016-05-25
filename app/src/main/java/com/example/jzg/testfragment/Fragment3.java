package com.example.jzg.testfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by jzg on 2016/3/30.
 */
public class Fragment3 extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        tv.setLayoutParams(layoutParams);
        tv.setText("这是Fragment3");
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(Color.parseColor("#ff6e00"));
        return tv;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public String getValue() {

        return "从Fragment3得到的值";
    }
}
