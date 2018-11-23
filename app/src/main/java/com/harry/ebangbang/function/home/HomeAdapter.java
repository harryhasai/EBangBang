package com.harry.ebangbang.function.home;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.R;
import com.harry.ebangbang.network.entity.HomeBannerEntity;
import com.harry.ebangbang.network.entity.HomeEntity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harry on 2018/11/20.
 */
public class HomeAdapter extends BaseMultiItemQuickAdapter<HomeMultiItem, BaseViewHolder> {

    private Banner banner;
    private RecyclerView recyclerView;
    private List<HomeEntity.DataBean> mList;
    private Activity mActivity;
    private HomeBottomListAdapter bottomListAdapter;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeAdapter(List<HomeMultiItem> data, Activity activity) {
        super(data);
        mList = new ArrayList<>();
        addItemType(HomeMultiItem.TOP, R.layout.item_home_top);
        addItemType(HomeMultiItem.CENTER, R.layout.item_home_center);
        addItemType(HomeMultiItem.BOTTOM, R.layout.item_home_bottom);
        this.mActivity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeMultiItem item) {
        switch (helper.getItemViewType()) {
            case HomeMultiItem.TOP:  //上部分title

                break;
            case HomeMultiItem.CENTER://中部banner
                setupBanner(helper);
                break;
            case HomeMultiItem.BOTTOM://底部列表
                mList.add(new HomeEntity.DataBean());
                mList.add(new HomeEntity.DataBean());
                mList.add(new HomeEntity.DataBean());
                recyclerView = helper.getView(R.id.recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
                bottomListAdapter = new HomeBottomListAdapter(R.layout.item_home_data_list, mList);
                recyclerView.setAdapter(bottomListAdapter);
                break;
        }
    }

    public void setupRecyclerView(List<HomeEntity.DataBean> list) {
//        mList.clear();
//        mList.addAll(list);
//        bottomListAdapter.notifyDataSetChanged();
    }

    public void setupBanner(HomeBannerEntity homeBannerEntity) {
        List<String> images = new ArrayList<>();
        for (int i = 0; i < homeBannerEntity.data.size(); i++) {
            images.add(homeBannerEntity.attachmentPath + homeBannerEntity.data.get(i).bannerLink);
        }
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private void setupBanner(BaseViewHolder helper) {
        banner = helper.getView(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new BannerImageLoader());
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Accordion);
        //设置标题集合（当banner样式有显示title时）
        //banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
    }

}
