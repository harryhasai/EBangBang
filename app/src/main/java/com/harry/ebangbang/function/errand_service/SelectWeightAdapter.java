package com.harry.ebangbang.function.errand_service;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.R;

import java.util.List;

/**
 * Created by Harry on 2018/12/15.
 */
public class SelectWeightAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SelectWeightAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_weight, item);
    }
}
