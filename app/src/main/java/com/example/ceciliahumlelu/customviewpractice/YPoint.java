package com.example.ceciliahumlelu.customviewpractice;

import android.graphics.PointF;

public class YPoint {
    public float x, y, mc;
    public PointF left,right;

    public YPoint(float x, float y, float mc) {
        this.x = x;
        this.y = y;
        this.mc = mc;
        if(left==null){
            left = new PointF();
        }
        if(right==null){
            right = new PointF();
        }
        right.x = x + mc;
        left.x = x - mc;
        left.y = y;
        right.y = y;
    }

    public void setX(float x) {
        this.x = x;
        right.x = x + mc;
        left.x = x - mc;
    }

    public void setY(float y) {
        this.y = y;
        left.y = y;
        right.y = y;
    }

    public void setMc(float mc) {
        this.mc = mc;
        right.x = x + mc;
        left.x = x - mc;
    }

    public void setLeftX(float leftX){
        left.x = leftX;
        x = (left.x + right.x) / 2;
    }

    @Override
    public String toString() {
        return "YPoint{" +
                "x=" + x +
                ", y=" + y +
                ", mc=" + mc +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
