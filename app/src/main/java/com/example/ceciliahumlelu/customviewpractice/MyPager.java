package com.example.ceciliahumlelu.customviewpractice;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyPager extends ViewPager {
    private int startColor,endColor;
    private float[][] f;
    private float[] result;
    private Paint mPaintCircle,mClickPaint,mPaint;
    private int duration;
    private float radius,scale,mc,ratio;
    private  int clickColor,circleColor;
    private int[] roundColors = new int[4];
    private final double c = 0.552284749831;
    private int mWidth,mHeight,tabNum = 0;
    private float div;
    private float startX,startY,totalOff;
    private int currentPos,toPos;
    private final int r = 1;
    private YPoint p1,p3;
    private XPoint p2,p4;
    private ValueAnimator animator;

    public MyPager(@NonNull Context context) {
        super(context);

    }

    public MyPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MyPager);
        roundColors[0] = typedArray.getColor(R.styleable.MyPager_color1,getResources().getColor(R.color.color1));
        roundColors[1] = typedArray.getColor(R.styleable.MyPager_color2,getResources().getColor(R.color.color2));
        roundColors[2] = typedArray.getColor(R.styleable.MyPager_color3,getResources().getColor(R.color.color3));
        roundColors[3] = typedArray.getColor(R.styleable.MyPager_color4,getResources().getColor(R.color.color4));
        clickColor = typedArray.getColor(R.styleable.MyPager_clickColor, Color.WHITE);
        circleColor = typedArray.getColor(R.styleable.MyPager_circleColor,Color.GRAY);
        radius = typedArray.getDimension(R.styleable.MyPager_radius,50);
        duration = typedArray.getInteger(R.styleable.MyPager_duration,1000);
        scale = typedArray.getFloat(R.styleable.MyPager_scale,0.8f);
        typedArray.recycle();
        
        init();
    }
    
    private void init(){
        f = new float[roundColors.length][3];
        result = new float[3];
        ratio = radius;
        mc = (float)(c * ratio);
        mPaintCircle = new Paint();
        mPaintCircle.setColor(circleColor);
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setStrokeWidth(3);

        mClickPaint = new Paint();
        mClickPaint.setColor(clickColor);
        mClickPaint.setStyle(Paint.Style.STROKE);
        mClickPaint.setAntiAlias(true);
        mClickPaint.setStrokeWidth(radius / 2);

        mPaint = new Paint();
        startColor = roundColors[0];
        mPaint.setColor(startColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        div = (mWidth - 2* radius * tabNum) / (tabNum +1);
        startX = div + radius ; 
        startY = mHeight / 2;
        totalOff = (tabNum -1) *(2 * radius + div) - radius;
        
        if(currentPos == 0){
            radius = r * ratio;
            mc = (float) (c * ratio);
            p1 = new YPoint(0,radius,mc);
            p3 = new YPoint(0,-radius,mc);
            p2 = new XPoint(radius,0,mc);
            p4 = new XPoint(-radius,0,mc);
        }
        super.onSizeChanged(w,h,oldw,oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        if(x > div + 2 * radius && x <(div + 2*radius)*tabNum){
            if(animator !=null){
                animator.cancel();
            }
            int toPos = (int) (x / (div + 2 * radius));
            if(toPos!=currentPos && toPos<=tabNum){
                startAnimTo(currentPos,toPos);
            }

        }else if(x > div && x < div + 2* radius){
            if(animator!=null) animator.cancel();
            if(currentPos!=0) startAnimTo(0,currentPos);
        }
        return super.onTouchEvent(ev);
    }

    private boolean startAnimTo(int currentPos, int toPos) {
        this.currentPos = currentPos;
        this.toPos = toPos;
        if(currentPos==toPos) return true;
        startColor = roundColors[(this.currentPos) % 4];
        endColor = roundColors[toPos % 4];
        resetP();

        startX = div + radius + (this.currentPos) * (div + 2 * radius);
        distance = (toPos - currentPos) * (div + 2* radius) + (toPos > currentPos? -radius:radius);
    }
}































