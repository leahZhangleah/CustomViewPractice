package com.example.ceciliahumlelu.customviewpractice;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.view.Gravity.CENTER;

public class PercentView extends View {
    public static final String TAG = PercentView.class.getName();;
    private  int backgroundColor,progressColor,progress;
    private static final int LEFT = 0,TOP=1,CENTER=2,RIGHT=3,BOTTOM=4;
    private int gravity = CENTER;
    private float radius,centerX=0,centerY =0;
    private Paint mPaint;
    private RectF oval;


    public PercentView(Context context) {
        super(context);
        init();
    }

    public PercentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initParams(context, attrs);

    }

    public PercentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(context, attrs);
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        oval = new RectF();
    }

    private void initParams(Context context,AttributeSet attrs){
        init();
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.PercentView);
        if(typedArray!=null){
            backgroundColor = typedArray.getColor(R.styleable.PercentView_percent_background_color,Color.GRAY);
            progressColor = typedArray.getColor(R.styleable.PercentView_percent_progress_color,Color.BLUE);
            radius = typedArray.getDimension(R.styleable.PercentView_percent_circle_radius, 0);
            progress = typedArray.getInt(R.styleable.PercentView_percent_circle_progress, 0);
            gravity = typedArray.getInt(R.styleable.PercentView_percent_circle_gravity, CENTER);
            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.d(TAG,"onMeasure--widthMode--"+widthMode);
        Log.e(TAG,"get measured widt--"+getMeasuredWidth()+"get mesured height--"+getMeasuredHeight());
        switch (widthMode) {
            case MeasureSpec.EXACTLY:

                break;
            case MeasureSpec.AT_MOST:

                break;
            case MeasureSpec.UNSPECIFIED:

                break;
        }
        Log.e(TAG, "onMeasure--widthSize-->" + widthSize);
        Log.e(TAG, "onMeasure--heightMode-->" + heightMode);
        Log.e(TAG, "onMeasure--heightSize-->" + heightSize);
        int width = getWidth();
        int height = getHeight();
        Log.e(TAG, "onDraw---->" + width + "*" + height);
        centerX = width/2;
        centerY=width/2;
        switch (gravity) {
            case LEFT:
                centerX = radius + getPaddingLeft();
                break;
            case TOP:
                centerY = radius + getPaddingTop();
                break;
            case CENTER:
                break;
            case RIGHT:
                centerX = width - radius - getPaddingRight();
                break;
            case BOTTOM:
                centerY = height - radius - getPaddingBottom();
                break;
        }
        float left = centerX - radius;
        float top = centerY - radius;
        float right = centerX + radius;
        float bottom = centerY + radius;
        oval.set(left, top, right, bottom);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG,"onlayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(backgroundColor);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(centerX,centerY,radius,mPaint);
        mPaint.setColor(progressColor);
        double percent = progress * 1.0 / 100;
        int angle = (int) (percent * 360);
        canvas.drawArc(oval,270,angle,true,mPaint);
    }
}
