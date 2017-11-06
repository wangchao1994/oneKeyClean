package com.go.onekeyclean.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.go.onekeyclean.dialogs.ProgressDialogFragment;
import com.go.onekeyclean.utils.T;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * Created by wangchao on 17-11-6.
 */

public abstract  class BaseActivity extends AppCompatActivity {
    public static final String TAG = "BaseActivity";
    protected Context mContext;
    private ActivityTack activityTack = ActivityTack.getInstanse();
    private ProgressDialogFragment mProgressDialogFragment;
    private static String mDialogTag = "basedialog";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        //屏幕info
        GetWindowInfo();
    }

    /**
     * 获取屏幕信息
     * */
    private void GetWindowInfo() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int heightPixels = displayMetrics.heightPixels;
        int widthPixels = displayMetrics.widthPixels;
        float density = displayMetrics.density;
        activityTack.addActivity(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        // TODO Auto-generated method stub
        super.setContentView(layoutResID);
        ButterKnife.inject(this);

    }

    /** 通过Class跳转界面 **/
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /** 含有Bundle通过Class跳转界面 **/
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mContext, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /** 通过Action跳转界面 **/
    protected void startActivity(String action) {
        startActivity(action, null);
    }

    /** 含有Bundle通过Action跳转界面 **/
    protected void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    // /**
    // * 吐司
    // *
    // * @param message
    // */
    protected void showShort(String message) {
        T.showShort(mContext, message);
    }

    protected void showLong(String message) {
        T.showLong(mContext, message);
    }

    @Override
    public void finish() {
        super.finish();
        activityTack.removeActivity(this);

    }

    public void showDialogLoading() {
        showDialogLoading(null);
    }

    public void showDialogLoading(String msg) {
        if (mProgressDialogFragment == null) {
            mProgressDialogFragment = ProgressDialogFragment.newInstance(0,
                    null);
        }
        if (msg != null) {
            mProgressDialogFragment.setMessage(msg);
        }
        mProgressDialogFragment.show(getFragmentManager(), mDialogTag);

    }

    public void dismissDialogLoading() {
        if (mProgressDialogFragment != null) {
            mProgressDialogFragment.dismiss();
        }
    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


}
