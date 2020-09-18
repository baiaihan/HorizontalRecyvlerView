package com.baiaihan.pageandautohorizontalrecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.baiaihan.pageandautohorizontalrecycleview.adapter.BaseRecycelerAdapter;
import com.baiaihan.pageandautohorizontalrecycleview.adapter.HomeRecycelViewAdapter;
import com.baiaihan.pageandautohorizontalrecycleview.bean.Module;
import com.baiaihan.pageandautohorizontalrecycleview.indicator.JIndicator;
import com.baiaihan.pageandautohorizontalrecycleview.indicator.PageIndicatorView;
import com.baiaihan.pageandautohorizontalrecycleview.pagerrecycelview.GridAutoFitItemDecoration;
import com.baiaihan.pageandautohorizontalrecycleview.pagerrecycelview.GridAutofitLayoutManager;
import com.baiaihan.pageandautohorizontalrecycleview.pagerrecycelview.HorizontalPageGridLayoutManager;
import com.baiaihan.pageandautohorizontalrecycleview.pagerrecycelview.PagingItemDecoration;
import com.baiaihan.pageandautohorizontalrecycleview.pagerrecycelview.PagingScrollHelper;
import com.baiaihan.pageandautohorizontalrecycleview.utils.PageUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements BaseRecycelerAdapter.OnItemClickListener, PagingScrollHelper.OnPageChangeListener {

    @BindView(R.id.recycler_page)
    RecyclerView mRecyclerPage;

    @BindView(R.id.recycler_autogrid)
    RecyclerView mRecyclerAutogrid;

    @BindView(R.id.jindicator)
    JIndicator mJIndicator;

    @BindView(R.id.pindicator)
    PageIndicatorView mPIndicator;

    private PagingScrollHelper mPagingScrollUtil;
    private HomeRecycelViewAdapter homeRecycelViewAdapter;
    final static int mRows = 2;
    final static int mColumns = 4;
    private Unbinder unbinder;
    /**
     * 功能列表
     */
    private Module[] mModuleList = new Module[]{
            new Module(R.drawable.module_carlife, R.string.module_name, Module.MODULE_NAVIGATION),
            new Module(R.drawable.module_carlife, R.string.module_name, Module.MODULE_CARLIFE_MAINTAINCOUPON),
            new Module(R.drawable.module_carlife, R.string.module_name, Module.MODULE_REPAIR_LIST),
            new Module(R.drawable.module_carlife, R.string.module_name, Module.MODULE_CARLIFE),
            new Module(R.drawable.module_carlife, R.string.module_name, Module.MODULE_RESCUE),
            new Module(R.drawable.module_carlife, R.string.module_name, Module.MODULE_TRAVEL),
            new Module(R.drawable.module_carlife, R.string.module_name, Module.MODULE_CARLIFE_MAINTAINCOUPON),
            new Module(R.drawable.module_carlife, R.string.module_name, Module.MODULE_REPAIR_LIST),
            new Module(R.drawable.module_carlife, R.string.module_name, Module.MODULE_CARLIFE),
            new Module(R.drawable.module_carlife, R.string.module_name, Module.MODULE_RESCUE),
            new Module(R.drawable.module_carlife, R.string.module_name, Module.MODULE_TRAVEL),
            new Module(R.drawable.module_carlife, R.string.module_name, Module.MODULE_CARLIFE_MAINTAINCOUPON),
            new Module(R.drawable.module_carlife, R.string.module_name, Module.MODULE_REPAIR_LIST),
            new Module(R.drawable.module_carlife, R.string.module_name, Module.MODULE_CARLIFE),
            new Module(R.drawable.module_carlife, R.string.module_name, Module.MODULE_RESCUE)

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        List<Module> resultList = new ArrayList<>(mModuleList.length);
        Collections.addAll(resultList, mModuleList);
        homeRecycelViewAdapter = new HomeRecycelViewAdapter(this, mRecyclerPage);
        homeRecycelViewAdapter.addAll(resultList);
        homeRecycelViewAdapter.setOnItemClickListener(this);
        /*
         *横向分页
         */
        HorizontalPageGridLayoutManager gridPageLayoutManager = new HorizontalPageGridLayoutManager(mRows, mColumns);

        mPagingScrollUtil = new PagingScrollHelper();
        mPagingScrollUtil.setUpRecycleView(mRecyclerPage);
        mPagingScrollUtil.updateLayoutManger();
        mPagingScrollUtil.setOnPageChangeListener(this);

        mPIndicator.initIndicator(PageUtils.getPageSize(mModuleList.length, mRows, mColumns));
        mRecyclerPage.setLayoutManager(gridPageLayoutManager);
        mRecyclerPage.addItemDecoration(new PagingItemDecoration(gridPageLayoutManager));
        mRecyclerPage.setAdapter(homeRecycelViewAdapter);
        //横向适配
        GridAutofitLayoutManager gridLayoutManager = new GridAutofitLayoutManager(this, 2, 4);
        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        mJIndicator.bindRecyclerView(mRecyclerAutogrid);
        mRecyclerAutogrid.setLayoutManager(gridLayoutManager);
        mRecyclerAutogrid.addItemDecoration(new GridAutoFitItemDecoration(gridLayoutManager));
        mRecyclerAutogrid.setAdapter(homeRecycelViewAdapter);
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onPageChange(int index) {
        mPIndicator.setSelectedPage(index);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
