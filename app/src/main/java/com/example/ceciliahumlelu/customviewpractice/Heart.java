package com.example.ceciliahumlelu.customviewpractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Heart extends View {
    private PointF left,top,right,bottom,tlControl1,tlControl2,trControl1,trControl2,blControl1,blControl2,brControl1,brControl2;
    private int centerX,centerY;
    private Paint mPaint,circlePaint;
    private static final float C = 0.551915024494f;
    private float radius = 200;
    private float controlDis = radius * C;
    private int totalDuration = 2000;
    private int count = 100;
    private int current = 0;
    private int interval = totalDuration / count;
    private float[] points,controlPoints;
    public Heart(Context context) {
        super(context);
        init();
    }

    public Heart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Heart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        circlePaint = new Paint();
        mPaint.setAntiAlias(true);
        circlePaint.setAntiAlias(true);
        left = new PointF(-radius,0);
        top = new PointF(0,-radius);
        right = new PointF(radius,0);
        bottom = new PointF(0,radius);
        tlControl1 = new PointF(-radius,-controlDis);
        tlControl2 = new PointF(-controlDis,-radius);
        trControl1 = new PointF(controlDis,-radius);
        trControl2 = new PointF(radius,-controlDis);

        blControl1 = new PointF(-controlDis,radius);
        blControl2 = new PointF(-radius,controlDis);
        brControl1 = new PointF(radius,controlDis);
        brControl2 = new PointF(controlDis,radius);
        centerX = 0;
        centerY = 0;
        points = new float[]{left.x,left.y,top.x,top.y,right.x,right.y,bottom.x,bottom.y};
        controlPoints = new float[]{tlControl1.x,tlControl1.y,tlControl2.x,tlControl2.y,trControl1.x,trControl1.y,trControl2.x,trControl2.y,
                blControl1.x,blControl1.y,blControl2.x,blControl2.y,brControl1.x,brControl1.y,brControl2.x,brControl2.y};
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5f);
        mPaint.setColor(Color.BLACK);
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        canvas.translate(centerX,centerY);
        canvas.drawPoints(points,mPaint);
        canvas.drawPoints(controlPoints,mPaint);

        //canvas.scale(1,-1);

        circlePaint.setColor(Color.RED);
        circlePaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4f);
        Path path = new Path();
        path.moveTo(left.x,left.y);
        path.cubicTo(tlControl1.x,tlControl1.y,tlControl2.x,tlControl2.y,top.x,top.y);
        path.cubicTo(trControl1.x,trControl1.y,trControl2.x,trControl2.y,right.x,right.y);
        path.cubicTo(brControl1.x,brControl1.y,brControl2.x,brControl2.y,bottom.x,bottom.y);
        path.cubicTo(blControl1.x,blControl1.y,blControl2.x,blControl2.y,left.x,left.y);
        canvas.drawPath(path,circlePaint);

        current+=interval;
        if(current<totalDuration){
            blControl2.x += 0 / count;
            blControl1.y -= 100 / count;
            brControl2.y -= 100 / count;
            brControl1.x -= 20 / count;
            top.y += 120 / count;

            postInvalidateDelayed(interval);
        }
    }


}
