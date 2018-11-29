package com.harry.ebangbang.function.add_address.receiving_address;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.amap.api.services.core.PoiItem;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.R;

import java.util.List;


/**
 * Created by Harry on 2018/11/29.
 */
public class ReceivingAddressAdapter extends BaseQuickAdapter<PoiItem, BaseViewHolder> {

    public ReceivingAddressAdapter(int layoutResId, @Nullable List<PoiItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PoiItem item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_content, item.getSnippet());
    }
}
