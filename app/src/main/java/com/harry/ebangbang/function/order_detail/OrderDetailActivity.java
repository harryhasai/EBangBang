package com.harry.ebangbang.function.order_detail;

import com.harry.ebangbang.R;
import com.harry.ebangbang.base.BaseActivity;
import com.harry.ebangbang.base.presenter.BasePresenter;

import java.util.ArrayList;

/**
 * Created by Harry on 2018/12/7.
 * 订单详情页面
 */
public class OrderDetailActivity extends BaseActivity {

    @Override
    protected int setupView() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }
}
