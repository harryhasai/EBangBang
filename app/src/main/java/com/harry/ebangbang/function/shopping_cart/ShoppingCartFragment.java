package com.harry.ebangbang.function.shopping_cart;

import android.view.View;

import com.harry.ebangbang.R;
import com.harry.ebangbang.base.BaseFragment;
import com.harry.ebangbang.base.presenter.BasePresenter;

/**
 * Created by Harry on 2018/11/6.
 * 购物车页面
 */
public class ShoppingCartFragment extends BaseFragment {

    @Override
    protected int setupView() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }
}
