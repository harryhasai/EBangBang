package com.harry.ebangbang.function.choose_coupon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.harry.ebangbang.R;
import com.harry.ebangbang.app_final.ConstantFinal;
import com.harry.ebangbang.app_final.DisposableFinal;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.network.entity.ChooseCouponEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/11/30.
 * 选择商家代金券
 */
public class ChooseCouponActivity extends BaseActivity<ChooseCouponPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<ChooseCouponEntity.DataBean> mList;
    private ChooseCouponAdapter adapter;

    @Override
    protected int setupView() {
        return R.layout.activity_choose_coupon;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mList = new ArrayList<>();
        tvTitle.setText("商家代金券");

        initRecyclerView();

        String total = getIntent().getStringExtra("total");
        mPresenter.getCouponList(total);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChooseCouponAdapter(R.layout.item_choose_coupon, mList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ChooseCouponEntity.DataBean bean = mList.get(position);
                Intent intent = new Intent();
                intent.putExtra("money", bean.money);
                setResult(ConstantFinal.COUPON_RESULT_CODE, intent);
                finish();
            }
        });
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.CHOOSE_COUPON_ACTIVITY_GET_COUPON_LIST);
        return tags;
    }

    @Override
    protected ChooseCouponPresenter bindPresenter() {
        return new ChooseCouponPresenter();
    }

    public void getCouponList(List<ChooseCouponEntity.DataBean> data) {
        mList.clear();
        mList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.ll_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }
}
