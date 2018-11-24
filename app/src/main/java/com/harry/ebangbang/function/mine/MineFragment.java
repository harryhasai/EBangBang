package com.harry.ebangbang.function.mine;

import android.view.View;

import com.harry.ebangbang.R;
import com.harry.ebangbang.base.BaseFragment;
import com.harry.ebangbang.base.presenter.BasePresenter;

import java.util.ArrayList;

/**
 * Created by Harry on 2018/11/6.
 * 我的
 */
public class MineFragment extends BaseFragment<MinePresenter> {

    @Override
    protected int setupView() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected MinePresenter bindPresenter() {
        return new MinePresenter();
    }
}
