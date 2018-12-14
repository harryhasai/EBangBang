package com.harry.ebangbang.function.address_management;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.ebangbang.R;
import com.harry.ebangbang.network.entity.AddressManagementEntity;

/**
 * Created by Harry on 2018/12/14.
 */
public class AddressManagementAdapter extends BaseQuickAdapter<AddressManagementEntity.DataBean, BaseViewHolder> {

    public AddressManagementAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressManagementEntity.DataBean item) {
        helper.setText(R.id.tv_address, item.site)
                .setText(R.id.tv_name, item.name)
                .setText(R.id.tv_phone, item.phone)
                .addOnClickListener(R.id.iv_modify);
        if (item.isDefault == 1) {
            helper.setGone(R.id.tv_default, true);
        } else {
            helper.setGone(R.id.tv_default, false);
        }
    }
}
