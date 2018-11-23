package com.harry.ebangbang.function.home;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.R;
import com.harry.ebangbang.network.entity.HomeEntity;

import java.util.List;

/**
 * Created by Harry on 2018/11/20.
 */
public class HomeBottomListAdapter extends BaseQuickAdapter<HomeEntity.DataBean, BaseViewHolder> {

    public HomeBottomListAdapter(int layoutResId, @Nullable List<HomeEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeEntity.DataBean item) {
        if (helper.getAdapterPosition() == 0) {
            helper.setGone(R.id.tv_text, true);
        }
//        helper.setText(R.id.tv_business, item.name)
//                .setText(R.id.tv_start_price, item.upToPar)
//                .setText(R.id.tv_freight, item.upToPar)
//                .setText(R.id.tv_start_price, item.upToPar)
//                .setText(R.id.tv_start_price, item.upToPar);
    }
}
