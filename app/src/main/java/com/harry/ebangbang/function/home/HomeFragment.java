package com.harry.ebangbang.function.home;

import android.view.View;

import com.harry.ebangbang.R;
import com.harry.ebangbang.base.BaseFragment;
import com.harry.ebangbang.base.presenter.BasePresenter;

/**
 * Created by Harry on 2018/11/6.
 * 主页
 */
public class HomeFragment extends BaseFragment {

    @Override
    protected int setupView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }
}
