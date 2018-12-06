package com.harry.ebangbang.function.shop_detail.view;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.R;

import java.util.List;

/**
 * Created by Harry on 2018/12/6.
 */
public class ComplexPopupAdapter extends BaseQuickAdapter<ComplexPopupEntity, BaseViewHolder> {

    public ComplexPopupAdapter(int layoutResId, @Nullable List<ComplexPopupEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ComplexPopupEntity item) {
        helper.setText(R.id.tv_dialog_name, item.goodsName)
                .setText(R.id.tv_dialog_price, "¥" + item.price)
                .setText(R.id.tv_dialog_count, String.valueOf(item.count))
                .addOnClickListener(R.id.iv_dialog_reduce)
                .addOnClickListener(R.id.iv_dialog_plus);
    }
}
