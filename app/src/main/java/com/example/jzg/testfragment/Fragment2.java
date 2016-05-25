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
public class Fragment2 extends Fragment {

    private static Fragment2 myFragment;

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
        tv.setText("我是Fragment2");
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(Color.parseColor("#2e7bef"));
        return tv;
    }


    public static Fragment2 newInstance(String string) {
        myFragment = new Fragment2();

        Bundle args = new Bundle();
        args.putString("fragment2", string);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public String getValue() {

        return "从Fragment2得到的值" + "和" + getArguments().getString("fragment2");
    }
}
