package com.example.ceciliahumlelu.customviewpractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class PieView extends View {
    private static final String TAG = "PieView";
    Paint mPaint;
    RectF rectF;
    int width,height;
    int[] percents;
    int[] colors;
    public PieView(Context context) {
        super(context);
        init();
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        rectF = new RectF();
        percents = new int[]{10,20,15,30,25};
        colors = new int[]{Color.GREEN,Color.YELLOW,Color.RED,Color.BLUE,Color.GRAY};
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL);
        width = getWidth();
        height = getHeight();
        int cx = width/2;
        int cy = height/2;
        Log.e(TAG,"width is: "+width +" height is: "+height);
        Log.e(TAG,"measured width is: "+getMeasuredWidth() +" measured height is: "+getMeasuredHeight());
        int currentAngle = 0;
        rectF.set(50,50,width-50,height-50);
        mPaint.setColor(Color.WHITE);
        canvas.drawRect(rectF,mPaint);
        for(int i=0;i<percents.length;i++){
            mPaint.setColor(colors[i]);
            int angle = percents[i] * 360 / 100;
            Log.e(TAG,"sweep angle for i="+i+"is "+angle);
            canvas.drawArc(rectF,currentAngle,angle,true,mPaint);
            currentAngle +=angle;
        }
    }
}
