package com.example.ceciliahumlelu.customviewpractice;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class TagsLayout extends ViewGroup {
    private static final String TAG = "TagsLayout";
    private int childHorizontalSpace,childVerticalSpace;
    public TagsLayout(Context context) {
        super(context);
    }

    public TagsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams(context, attrs);
    }

    public TagsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(context, attrs);
    }

    private void initParams(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.TagsLayout);
        if(typedArray!=null){
            childVerticalSpace = typedArray.getDimensionPixelSize(R.styleable.TagsLayout_tagVerticalSpace,0);
            childHorizontalSpace = typedArray.getDimensionPixelSize(R.styleable.TagsLayout_tagHorizontalSpace,0);
            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        Log.e(TAG,"sizedWidth is: " + sizeWidth + " sizedHeight is: " + sizeHeight + " modeWidth is: " + modeWidth + " modeHeight is: " + modeHeight);
        int width = 0,height=0;
        int lineWidth = 0,lineHeight=0;
        int count = getChildCount();
        int left = getPaddingLeft();
        int top = getPaddingTop();
       Log.e(TAG,"paddingleft is: "+left+" paddingtop is: "+top+" paddingright is: "+getPaddingRight()+" paddingbottom is: "+getPaddingBottom());
        for(int i=0;i<count;i++){
            View child = getChildAt(i);
            if(child.getVisibility()==GONE){
                continue;
            }
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin+childHorizontalSpace;
            int childHeight = child.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin+childVerticalSpace;
            Log.e(TAG,"childwidth is: "+childWidth+" childheight is: "+childHeight);
            if(lineWidth+childWidth > sizeWidth-left-getPaddingRight()){
                width = Math.max(lineWidth,childWidth);
                lineWidth = childWidth;
                height +=lineHeight;
                lineHeight = childHeight;
                child.setTag(new Location(left,top+height,lineWidth+left-childHorizontalSpace,top+height+child.getMeasuredHeight()));
                Log.e(TAG,"new line: width is: "+width+" linewidth is: "+lineWidth+" height is: "+height+" lineheight is: "+lineHeight);
            }else{
                child.setTag(new Location(lineWidth+left,top+height,lineWidth+left+childWidth-childHorizontalSpace,top+height+child.getMeasuredHeight()));
                lineWidth+=childWidth;
                lineHeight = Math.max(lineHeight,childHeight);
                Log.e(TAG,"not new line: width is: "+width+" linewidth is: "+lineWidth+" height is: "+height+" lineheight is: "+lineHeight);
            }
        }
        width = Math.max(width, lineWidth) + getPaddingLeft() + getPaddingRight();
        height += lineHeight;
        sizeHeight += getPaddingTop() + getPaddingBottom();
        height += getPaddingTop() + getPaddingBottom();
        setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth : width, (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : height);
        Log.e(TAG,"final width is: "+width+" linewidth is: "+lineWidth+" height is: "+height+" lineheight is: "+lineHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE)
                continue;
            Location location = (Location) child.getTag();
            child.layout(location.left, location.top, location.right, location.bottom);
        }
    }
}
