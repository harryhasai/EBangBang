package com.harry.ebangbang.function.order_manage;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.event.AddCommentEvent;
import com.harry.ebangbang.function.order_detail.OrderDetailActivity;
import com.harry.ebangbang.network.entity.OrderManageEntity;
import com.harry.ebangbang.utils.SwipeRefreshLayoutRefreshingUtil;
import com.ruffian.library.RTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/12/10.
 * 订单管理页面
 */
public class OrderManageActivity extends BaseActivity<OrderManagePresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_text1)
    RTextView tvText1;
    @BindView(R.id.tv_text2)
    RTextView tvText2;
    @BindView(R.id.tv_text3)
    RTextView tvText3;
    @BindView(R.id.tv_text4)
    RTextView tvText4;
    @BindView(R.id.tv_text5)
    RTextView tvText5;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    private int pageNum = 1;
    private OrderManageAdapter adapter;
    private boolean isLoadMore;

    @Override
    protected int setupView() {
        return R.layout.activity_order_manage;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        tvTitle.setText("我的订单");
        initRecyclerView();
        mPresenter.getOrderList(null, pageNum);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.ORDER_MANAGE_ACTIVITY_GET_ORDER_LIST);
        return tags;
    }

    @Override
    protected OrderManagePresenter bindPresenter() {
        return new OrderManagePresenter();
    }

    private void initRecyclerView() {
        // 设置下拉进度的背景颜色，默认就是白色的
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.setEnableLoadMore(false);
                pageNum = 1;
                isLoadMore = false;
                mPresenter.getOrderList(null, pageNum);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrderManageAdapter(R.layout.item_order_manage);
//        adapter = new OrderManageAdapter(R.layout.item_order_manage, mList);
        recyclerView.setAdapter(adapter);

        adapter.setPreLoadNumber(1);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                adapter.setEnableLoadMore(true);
                pageNum++;
                isLoadMore = true;
                mPresenter.getOrderList(null, pageNum);

            }
        }, recyclerView);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List data = adapter.getData();
                OrderManageEntity.DataBean bean = (OrderManageEntity.DataBean) data.get(position);
                Intent intent = new Intent(OrderManageActivity.this, OrderDetailActivity.class);
                intent.putExtra("orderId", String.valueOf(bean.id));
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.ll_back, R.id.tv_text1, R.id.tv_text2, R.id.tv_text3, R.id.tv_text4, R.id.tv_text5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_text1://待付款
                break;
            case R.id.tv_text2://待配送
                break;
            case R.id.tv_text3://待收货
                break;
            case R.id.tv_text4://待评价
                break;
            case R.id.tv_text5://退换货
                break;
        }
    }

    public void setRefreshing(boolean refreshing) {
        if (swipeRefreshLayout != null) {
            if (refreshing) {
                SwipeRefreshLayoutRefreshingUtil.setRefreshing(swipeRefreshLayout);
            } else {
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    public void getOrderList(List<OrderManageEntity.DataBean> data) {
        if (data.size() != 0) {
            if (isLoadMore) {
                adapter.addData(data);
                adapter.loadMoreComplete();
            } else {
                adapter.setNewData(data);
            }
        }
    }

    public void loadMoreEnd() {
        adapter.loadMoreEnd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销注册
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void paySuccessEvent(AddCommentEvent addCommentEvent) {
        //接收消息, 刷新列表, (在评价过后)
        pageNum = 1;
        isLoadMore = false;
        mPresenter.getOrderList(null, pageNum);
    }
}
