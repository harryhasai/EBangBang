package com.harry.ebangbang.function.my_coupon;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.application.EBangBangApplication;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.function.main.MainActivity;
import com.harry.ebangbang.network.entity.MyCouponEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/12/15.
 * 查询我的优惠券页面
 */
public class MyCouponActivity extends BaseActivity<MyCouponPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private MyCouponAdapter adapter;

    @Override
    protected int setupView() {
        return R.layout.activity_my_coupon;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        tvTitle.setText("我的代金券");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyCouponAdapter(R.layout.item_my_coupon);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_submit:
                        finish();
                        break;
                }
            }
        });

        mPresenter.getCouponList();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.MY_COUPON_ACTIVITY_GET_COUPON_LIST);
        return tags;
    }

    @Override
    protected MyCouponPresenter bindPresenter() {
        return new MyCouponPresenter();
    }

    @OnClick(R.id.ll_back)
    public void onViewClicked() {
        finish();
    }

    public void getCouponList(List<MyCouponEntity.DataBean> data) {
        adapter.setNewData(data);
    }
}
