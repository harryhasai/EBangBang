package com.harry.ebangbang.function.order_manage.order_status;

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
import com.harry.ebangbang.function.order_detail.OrderDetailActivity;
import com.harry.ebangbang.network.entity.OrderManageEntity;
import com.harry.ebangbang.utils.SwipeRefreshLayoutRefreshingUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/12/13.
 * 单一状态的列表
 * 订单状态  -1未付款 2 待配送  4待收货  5 订单已完成未评价   6退返货    7完成并已评价
 */
public class OrderStatusActivity extends BaseActivity<OrderStatusPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private int pageNum = 1;
    private boolean isLoadMore;
    private OrderStatusAdapter adapter;
    private String status;

    @Override
    protected int setupView() {
        return R.layout.activity_order_status;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        status = getIntent().getStringExtra("status");
        switch (status) {
            case "-1":
                tvTitle.setText("待付款");
                break;
            case "2":
                tvTitle.setText("待配送");
                break;
            case "4":
                tvTitle.setText("待收货");
                break;
            case "5":
                tvTitle.setText("待评价");
                break;
            case "6":
                tvTitle.setText("退换货");
                break;
        }

        initRecyclerView();
        mPresenter.getOrderList(status, pageNum);
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.ORDER_MANAGE_ACTIVITY_GET_ORDER_LIST);
        return tags;
    }

    @Override
    protected OrderStatusPresenter bindPresenter() {
        return new OrderStatusPresenter();
    }

    @OnClick({R.id.ll_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
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
                mPresenter.getOrderList(status, pageNum);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrderStatusAdapter(R.layout.item_order_status);
//        adapter = new OrderManageAdapter(R.layout.item_order_manage, mList);
        recyclerView.setAdapter(adapter);

        adapter.setPreLoadNumber(1);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                adapter.setEnableLoadMore(true);
                pageNum++;
                isLoadMore = true;
                mPresenter.getOrderList(status, pageNum);

            }
        }, recyclerView);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List data = adapter.getData();
                OrderManageEntity.DataBean bean = (OrderManageEntity.DataBean) data.get(position);
                Intent intent = new Intent(OrderStatusActivity.this, OrderDetailActivity.class);
                intent.putExtra("orderId", String.valueOf(bean.id));
                startActivity(intent);
            }
        });
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

}
