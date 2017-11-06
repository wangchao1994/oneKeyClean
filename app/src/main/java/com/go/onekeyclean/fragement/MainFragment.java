package com.go.onekeyclean.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.go.onekeyclean.R;
import com.go.onekeyclean.base.BaseFragment;

/**
 * Created by wangchao on 17-11-6.
 */

public class MainFragment extends BaseFragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_main,container,false);
        return view;
    }
}
