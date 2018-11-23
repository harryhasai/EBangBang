package com.harry.ebangbang.function.classification;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.network.entity.CommonEntity;

import java.util.List;

/**
 * Created by Harry on 2018/11/22.
 */
public class ClassificationChildCategoryAdapter extends BaseQuickAdapter<CommonEntity, BaseViewHolder> {

    public ClassificationChildCategoryAdapter(int layoutResId, @Nullable List<CommonEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommonEntity item) {

    }
}
