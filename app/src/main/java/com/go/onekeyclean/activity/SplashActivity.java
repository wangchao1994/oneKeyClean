package com.go.onekeyclean.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.go.onekeyclean.MainActivity;
import com.go.onekeyclean.R;
import com.go.onekeyclean.base.BaseActivity;
import com.go.onekeyclean.service.CleanerService;
import com.go.onekeyclean.service.CoreService;
import com.go.onekeyclean.utils.SharedPreferencesUtils;

import java.util.Random;

public class SplashActivity extends BaseActivity {

    private ImageView image;
    private Animation animation_in;
    private Animation animation_scale;
    private Animation animation_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initAnimation();
        initSetListener();
    }


    private void initAnimation() {
        animation_in = AnimationUtils.loadAnimation(this,R.anim.welcome_fade_in);
        animation_in.setDuration(500);

        animation_scale = AnimationUtils.loadAnimation(this,R.anim.welcome_fade_in_scale);
        animation_scale.setDuration(2000);

        animation_out = AnimationUtils.loadAnimation(this,R.anim.welcome_fade_out);
        animation_out.setDuration(2000);

        image.startAnimation(animation_in);
    }

    private void initView() {
        image = (ImageView) findViewById(R.id.image);
        Random random = new Random();
        int i = random.nextInt(2);
        if (i == 1){
            image.setImageResource(R.mipmap.entrance3);
        }else{
            image.setImageResource(R.mipmap.entrance2);
        }

        startService(new Intent(this, CoreService.class));
        startService(new Intent(this, CleanerService.class));

        if (!SharedPreferencesUtils.isShortCut(this)){
            createShortCut();
        }
    }
    private void createShortCut() {
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "一键加速");
        intent.putExtra("duplicate", false);
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round));
        Intent i = new Intent();
        i.setAction("com.onekey.shortcut");
        i.addCategory("android.intent.category.DEFAULT");
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, i);
        sendBroadcast(intent);
        SharedPreferencesUtils.setIsShortCut(mContext, true);
    }
    private void initSetListener() {
        animation_in.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image.startAnimation(animation_scale);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation_scale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image.startAnimation(animation_out);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation_out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(MainActivity.class);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
