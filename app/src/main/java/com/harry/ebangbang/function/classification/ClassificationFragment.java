package com.harry.ebangbang.function.classification;

import android.view.View;

import com.harry.ebangbang.R;
import com.harry.ebangbang.base.BaseFragment;
import com.harry.ebangbang.base.presenter.BasePresenter;

import java.util.ArrayList;

/**
 * Created by Harry on 2018/11/6.
 * 分类页面
 */
public class ClassificationFragment extends BaseFragment {

    @Override
    protected int setupView() {
        return R.layout.fragment_classification;
    }

    @Override
    protected void initView(View view) {

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
