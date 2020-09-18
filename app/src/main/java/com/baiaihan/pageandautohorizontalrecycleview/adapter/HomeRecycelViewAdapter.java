/*--------------------------------------------------
 * Copyright (C) 2015 The Android Y-CarPlus Project
 *                http://www.yesway.cn/
 * 创建时间：2017年3月29日
 * 内容说明：
 *
 * 编号                日期                     担当者             内容
 * -------------------------------------------------
 *
 * -------------------------------------------------- */
package com.baiaihan.pageandautohorizontalrecycleview.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.baiaihan.pageandautohorizontalrecycleview.R;
import com.baiaihan.pageandautohorizontalrecycleview.bean.Module;

/**
 * 功能module adapter
 */
public class HomeRecycelViewAdapter extends BaseRecycelerAdapter<Module, HomeRecycelViewAdapter.MyViewHolder> {

    private final RecyclerView mRecyclerView;

    public HomeRecycelViewAdapter(Context context, RecyclerView recyclerView) {
        super(context);
        this.mRecyclerView = recyclerView;
    }

    @Override
    public int onBindLayout() {
        return R.layout.item_common_module;
    }

    @Override
    public MyViewHolder onCreateViewHolder(View view) {

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(position);
                }
            });
        }
        convert(holder, position);
    }

    @Override
    public void convert(final MyViewHolder holder, int position) {

        try {
            Module module = mList.get(position);
            holder.mTvTitle.setText(module.moduleName);
            Drawable drawable = mContext.getResources().getDrawable(module.imgId);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            holder.mTvTitle.setCompoundDrawables(null, drawable, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_common_module, parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_item);

        }
    }
}
