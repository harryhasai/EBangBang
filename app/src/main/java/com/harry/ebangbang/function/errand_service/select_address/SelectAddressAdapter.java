package com.harry.ebangbang.function.errand_service.select_address;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.R;
import com.harry.ebangbang.network.entity.AddressManagementEntity;

/**
 * Created by Harry on 2018/12/14.
 */
public class SelectAddressAdapter extends BaseQuickAdapter<AddressManagementEntity.DataBean, BaseViewHolder> {

    public SelectAddressAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressManagementEntity.DataBean item) {
        helper.setText(R.id.tv_address, item.site)
                .setText(R.id.tv_name, item.name)
                .setText(R.id.tv_phone, item.phone);
    }
}
