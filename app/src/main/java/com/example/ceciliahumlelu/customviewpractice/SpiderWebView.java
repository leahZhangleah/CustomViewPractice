package com.example.ceciliahumlelu.customviewpractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SpiderWebView extends View {
    Paint mPaint,textPaint;
    public SpiderWebView(Context context) {
        super(context);
        init();
    }

    public SpiderWebView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SpiderWebView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        textPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth()/2,getHeight()/2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2f);
        //mPaint.setColor(Color.BLACK);
        //canvas.drawCircle(0,0,250,mPaint);

        mPaint.setColor(Color.GRAY);
        Path fLine = new Path();
        fLine.moveTo(-250,0);
        fLine.lineTo(250,0);

        canvas.drawPath(fLine,mPaint);

        Path sLine = new Path();
        sLine.moveTo(-125,(float) (-Math.sqrt(3.0)*125));
        sLine.lineTo(125,(float) (Math.sqrt(3.0)*125));

        canvas.drawPath(sLine,mPaint);

        Path tLine = new Path();
        tLine.moveTo(-125,(float) (Math.sqrt(3.0)*125));
        tLine.lineTo(125,(float) (-Math.sqrt(3.0)*125));

        canvas.drawPath(tLine,mPaint);

        Path path = new Path();
        path.moveTo(250,0);
        //path.lineTo(0,250);
        path.lineTo(125,(float) (Math.sqrt(3.0)*125));
        path.lineTo(-125,(float) (Math.sqrt(3.0)*125));
        path.lineTo(-250,0);
        path.lineTo(-125,(float) (-Math.sqrt(3.0)*125));
        path.lineTo(125,(float) (-Math.sqrt(3.0)*125));
        path.close();
        canvas.drawPath(path,mPaint);


        int m=6,n=5;
        for(int i=0;i<4;i++){
            m--;
            n--;
            float ratio = (float)n/m;
            canvas.scale(ratio,ratio);
            canvas.drawPath(path,mPaint);
        }

        canvas.scale(5.0f,5.0f);

        Path custom = new Path();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setARGB(125,255,255,0);
        float firstP = 240f,secondP=220f,thirdP=200f,fourthP=180f,fifthP=160f,sixthP=140f;
        custom.moveTo(firstP,0);
        custom.lineTo(secondP/2,(float)Math.sqrt(3.0)*secondP/2);
        custom.lineTo(-thirdP/2,(float)Math.sqrt(3.0)*thirdP/2);
        custom.lineTo(-fourthP,0);
        custom.lineTo(-fifthP/2,(float)-Math.sqrt(3.0)*fifthP/2);
        custom.lineTo(sixthP/2,(float)-Math.sqrt(3.0)*sixthP/2);
        custom.close();
        canvas.drawPath(custom,mPaint);

    }



}
