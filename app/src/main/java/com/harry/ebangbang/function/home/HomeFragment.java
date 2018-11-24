package com.harry.ebangbang.function.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.BaseFragment;
import com.harry.ebangbang.network.entity.HomeBannerEntity;
import com.harry.ebangbang.network.entity.HomeEntity;
import com.harry.ebangbang.utils.SwipeRefreshLayoutRefreshingUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Harry on 2018/11/6.
 * 主页
 */
public class HomeFragment extends BaseFragment<HomePresenter> {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;

    private HomeAdapter adapter;

    @Override
    protected int setupView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        initRecyclerView();

        mPresenter.getBanner();
        mPresenter.getList();
    }

    private void initRecyclerView() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getList();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        List<HomeMultiItem> mList = new ArrayList<>();
        mList.add(new HomeMultiItem(HomeMultiItem.TOP));
        mList.add(new HomeMultiItem(HomeMultiItem.CENTER));
        mList.add(new HomeMultiItem(HomeMultiItem.BOTTOM));
        adapter = new HomeAdapter(mList, mActivity);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.HOME_FRAGMENT_GET_LIST);
        tags.add(DisposableFinal.HOME_FRAGMENT_GET_BANNER);
        return tags;
    }

    @Override
    protected HomePresenter bindPresenter() {
        return new HomePresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setRefreshing(boolean refreshing) {
        if (refreshing) {
            SwipeRefreshLayoutRefreshingUtil.setRefreshing(swipeRefreshLayout);
        } else {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    public void getList(List<HomeEntity.DataBean> list) {
        adapter.setupRecyclerView(list);
    }

    /**
     * @param homeBannerEntity Banner的实体类
     */
    public void getBanner(HomeBannerEntity homeBannerEntity) {
        adapter.setupBanner(homeBannerEntity);
    }
}
