package com.baiaihan.pageandautohorizontalrecycleview.pagerrecycelview;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.baiaihan.pageandautohorizontalrecycleview.utils.DisplayUtil;


public class PagingItemDecoration extends RecyclerView.ItemDecoration {
private HorizontalPageGridLayoutManager mHorizontalPageGridLayoutManager;
    public PagingItemDecoration(HorizontalPageGridLayoutManager LayoutManager) {
        this.mHorizontalPageGridLayoutManager=LayoutManager;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int itemPosition = parent.getChildAdapterPosition(view);
        //第一列,最后一列处理
        if(mHorizontalPageGridLayoutManager.isFirstColum(itemPosition)){
            outRect.set(0, DisplayUtil.dp2px(view.getContext(), 5), DisplayUtil.dp2px(view.getContext(), 5), DisplayUtil.dp2px(view.getContext(), 5));
        }else if (mHorizontalPageGridLayoutManager.isLastColum(itemPosition)){
            outRect.set(DisplayUtil.dp2px(view.getContext(), 5), DisplayUtil.dp2px(view.getContext(), 5), 0, DisplayUtil.dp2px(view.getContext(), 5));
        }else{
            outRect.set(DisplayUtil.dp2px(view.getContext(), 5), DisplayUtil.dp2px(view.getContext(), 5), DisplayUtil.dp2px(view.getContext(), 5), DisplayUtil.dp2px(view.getContext(), 5));
        }
    }

}
