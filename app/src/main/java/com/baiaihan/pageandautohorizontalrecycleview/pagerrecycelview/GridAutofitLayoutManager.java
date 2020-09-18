package com.baiaihan.pageandautohorizontalrecycleview.pagerrecycelview;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baiaihan.pageandautohorizontalrecycleview.utils.DisplayUtil;
import com.baiaihan.pageandautohorizontalrecycleview.utils.ScreenUtils;

/**
 * 水平方向不分页的九宫格布局管理
 */
public class GridAutofitLayoutManager extends GridLayoutManager {

    private Context mContext;
    private int mColumnNum;

    public GridAutofitLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
    }

    /**
     * @param context
     * @param spanCount 行数
     * @param columnNum 一屏显示的列数
     */
    public GridAutofitLayoutManager(Context context, int spanCount, int columnNum) {
        super(context, spanCount);
        this.mContext = context;
        this.mColumnNum = columnNum;
    }

    public GridAutofitLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
        this.mContext = context;

    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        if (getItemCount() <= 0) {
            return;
        }
        int screenWidth = ScreenUtils.getScreenWidth(mContext);
        int totaloffsetWidth = DisplayUtil.dp2px(mContext, 10) * 2 + DisplayUtil.dp2px(mContext, 5) * (mColumnNum - 1) * 2;
        int itemWidth = (screenWidth - totaloffsetWidth) / mColumnNum;
        int totaloffsetHeight= DisplayUtil.dp2px(mContext, 5) * getSpanCount() * 2;
        int itemHeight = (getHeight()-totaloffsetHeight) / getSpanCount();
        detachAndScrapAttachedViews(recycler);
        for (int i = 0; i < getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            view.getLayoutParams().width = itemWidth;
            view.getLayoutParams().height= itemHeight;
            addView(view);//因为进行了detach操作，所以现在要重新添加
            measureChildWithMargins(view, 0, 0);//通知测量itemView
        }
        super.onLayoutChildren(recycler, state);
    }

    /**
     * 是否为第一列
     */
    public boolean isFirstColum(int position) {

        if (position < getSpanCount()) {
            return true;
        }
        return false;
    }

    /**
     * 是否为最后一列
     */
    public boolean isLastColum(int position) {
        if (getCurrentColum(position, getSpanCount()) == getTotalColum(getItemCount(), getSpanCount())) {
            return true;
        }
        return false;
    }

    /**
     * 得到总共列数
     */
    public int getTotalColum(int totalCount, int span) {
        return (totalCount % span) == 0 ? (totalCount / span) : (totalCount / span) + 1;
    }

    /**
     * 当前角标所在列数
     */
    public int getCurrentColum(int position, int span) {
        return (position / span) + 1;
    }
}




