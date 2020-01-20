package com.example.ceciliahumlelu.customviewpractice;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    BouncingBall bouncingBall;
    Button startAnimBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager,true);

        /*
        TagsLayout imageViewGroup = findViewById(R.id.image_layout);
        bouncingBall = findViewById(R.id.bouncing_ball);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        String[] string={"从我写代码那天起，我就没有打算写代码","从我写代码那天起","我就没有打算写代码","没打算","写代码"};
        for(int i=0; i<string.length;i++){
            TextView textView = new TextView(this);
            textView.setText(string[i]);
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundResource(android.R.color.holo_blue_light);
            imageViewGroup.addView(textView, lp);

        }



        final ValueAnimator animator = ValueAnimator.ofFloat(0,1);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float intermediateValue = (float)animation.getAnimatedValue();
                bouncingBall.bounce();
            }
        });

        startAnimBtn = findViewById(R.id.start_anim_btn);
        startAnimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bouncingBall.getAnimatedStatus()){
                    bouncingBall.bounceBack();
                }else{
                    animator.start();
                }
            }
        });*/
    }




}
