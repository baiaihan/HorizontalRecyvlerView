package com.baiaihan.pageandautohorizontalrecycleview.indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.baiaihan.pageandautohorizontalrecycleview.R;


public class JIndicator extends View {
    private Paint mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF mBgRect = new RectF();
    private Float mRadius = 0f;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF mRect = new RectF();
    private int viewWidth = 0;
    private int mBgColor = Color.parseColor("#272727");
    private int mIndicatorColor = Color.parseColor("#ff4646");
    Float ratio = 0.5f;

    public JIndicator(Context context) {
        super(context);
    }

    public JIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HIndicator);
        mBgColor = typedArray.getColor(R.styleable.HIndicator_hi_bgColor, mBgColor);
        mIndicatorColor = typedArray.getColor(R.styleable.HIndicator_hi_indicatorColor, mIndicatorColor);
        typedArray.recycle();
        mBgPaint.setColor(mBgColor);
        mBgPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mIndicatorColor);
        mPaint.setStyle(Paint.Style.FILL);

    }

    public JIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public JIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void setRatio(Float value) {
        ratio = value;
        invalidate();
    }

    public Float progress = 0f;    //滑动进度比例

    public void setProgress(Float value) {
        progress = value;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        mBgRect.set(0f, 0f, w * 1f, h * 1f);
        mRadius = h / 2f;
    }

    /**
     * 设置指示器背景进度条的颜色
     *
     * @param color 背景色
     */
    public void setBgColor(@ColorInt int color) {
        mBgPaint.setColor(color);
        invalidate();
    }

    /**
     * 设置指示器的颜色
     *
     * @param color 指示器颜色
     */
    public void setIndicatorColor(@ColorInt int color) {
        mPaint.setColor(color);
        invalidate();
    }

    /**
     * 绑定recyclerView
     */
    public void bindRecyclerView(final RecyclerView recyclerView) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int offsetX = recyclerView.computeHorizontalScrollOffset();
                int range = recyclerView.computeHorizontalScrollRange();
                int extend = recyclerView.computeHorizontalScrollExtent();
                Float progress = offsetX * 1.0f / (range - extend);
                setProgress(progress);//设置滚动距离所占比例
            }
        });
        recyclerView.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                int range = recyclerView.computeHorizontalScrollRange();
                int extend = recyclerView.computeHorizontalScrollExtent();
                Float ratio = extend * 1f / range;
                setRatio(ratio);     //设置指示器所占的长度比例
            }
        });

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制背景
        canvas.drawRoundRect(mBgRect, mRadius, mRadius, mBgPaint);

        //计算指示器的长度和位置
        Float leftOffset = viewWidth * (1f - ratio) * progress;
        Float left = mBgRect.left + leftOffset;
        Float right = left + viewWidth * ratio;
        mRect.set(left, mBgRect.top, right, mBgRect.bottom);
        //绘制指示器
        canvas.drawRoundRect(mRect, mRadius, mRadius, mPaint);
    }
}
