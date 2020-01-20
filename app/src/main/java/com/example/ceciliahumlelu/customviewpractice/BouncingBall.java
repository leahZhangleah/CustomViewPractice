package com.example.ceciliahumlelu.customviewpractice;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;


public class BouncingBall extends View {
    private PointF left,top,right,bottom,tlControl1,tlControl2,trControl1,trControl2,blControl1,blControl2,brControl1,brControl2;
    private int centerX,centerY;
    private Paint circlePaint;
    private static final float C = 0.551915024494f;
    private float radius = 100;
    private float controlDis = radius * C;
    private boolean animated = false;
    private long totalDuration = 1000;
    private long count = 100;
    private long interval = 2;
    public BouncingBall(Context context) {
        super(context);
        init();
    }

    public BouncingBall(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BouncingBall(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        centerX = 0;
        centerY = 0;
        left = new PointF(centerX-radius,centerY);
        top = new PointF(centerX,centerY-radius);
        right = new PointF(centerX+radius,centerY);
        bottom = new PointF(centerX,centerY+radius);
        tlControl1 = new PointF(centerX-radius,centerY-controlDis);
        tlControl2 = new PointF(centerX-controlDis,centerY-radius);
        trControl1 = new PointF(centerX+controlDis,centerY-radius);
        trControl2 = new PointF(centerX+radius,centerY-controlDis);

        blControl1 = new PointF(centerX-controlDis,centerY+radius);
        blControl2 = new PointF(centerX-radius,centerY+controlDis);
        brControl1 = new PointF(centerX+radius,centerY+controlDis);
        brControl2 = new PointF(centerX+controlDis,centerY+radius);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 4;
        centerY = h / 2;
        System.out.println("onSizeChanged");
        System.out.println(tlControl1);
        System.out.println(tlControl2);
        System.out.println(trControl1);
        System.out.println(trControl2);
        System.out.println(blControl1);
        System.out.println(blControl2);
        System.out.println(brControl1);
        System.out.println(brControl2);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(centerX,centerY);
        circlePaint.setColor(Color.RED);
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        Path path = new Path();
        path.moveTo(left.x,left.y);
        path.cubicTo(tlControl1.x,tlControl1.y,tlControl2.x,tlControl2.y,top.x,top.y);
        path.cubicTo(trControl1.x,trControl1.y,trControl2.x,trControl2.y,right.x,right.y);
        path.cubicTo(brControl1.x,brControl1.y,brControl2.x,brControl2.y,bottom.x,bottom.y);
        path.cubicTo(blControl1.x,blControl1.y,blControl2.x,blControl2.y,left.x,left.y);
        canvas.drawPath(path,circlePaint);
        bounce();
    }

    public void bounce(){
        //System.out.println(intermediateValue);
        animated = true;

        if(right.x <radius+80 && centerX == getWidth() / 4){
            right.x += interval;
            trControl2.x += interval;
            brControl1.x += interval;
        }else if(left.x> -radius-80){
                left.x -= interval;
                tlControl1.x -= interval;
                blControl2.x -= interval;
                centerX +=interval;
        }
        
        invalidate();
    }
    public void bounceBack(){
        animated = false;
        centerX = getWidth() / 4;
        invalidate();
    }

    public boolean getAnimatedStatus(){
        return animated;
    }



}
