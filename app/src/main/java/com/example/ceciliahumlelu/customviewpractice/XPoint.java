package com.example.ceciliahumlelu.customviewpractice;

import android.graphics.PointF;

public class XPoint {
    public float x,y,mc;
    public PointF bottom,top;

    public XPoint(float x, float y, float mc) {
        this.x = x;
        this.y = y;
        this.mc = mc;
        if (bottom == null)
            bottom = new PointF();
        if (top == null)
            top = new PointF();
        bottom.y = y + mc;
        top.y = y - mc;
        bottom.x = x;
        top.x = x;
    }

    public void setMc(float mc) {
        this.mc = mc;
        bottom.y = y + mc;
        top.y = y - mc;
    }

    public void setY(float y) {
        this.y = y;
        bottom.y = y + mc;
        top.y = y - mc;
    }

    public void setX(float x) {
        this.x = x;
        bottom.x = x;
        top.x = x;
    }

    @Override
    public String toString() {
        return "XPoint{" +
                "x=" + x +
                ", y=" + y +
                ", mc=" + mc +
                ", bottom=" + bottom +
                ", top=" + top +
                '}';
    }
}
